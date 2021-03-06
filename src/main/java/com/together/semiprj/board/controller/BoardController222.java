package com.together.semiprj.board.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.oreilly.servlet.MultipartRequest;
import com.together.semiprj.board.model.service.BoardService222;
import com.together.semiprj.board.model.vo.Board;
import com.together.semiprj.board.model.vo.BoardImage;
import com.together.semiprj.board.model.vo.Pagination;
import com.together.semiprj.common.MyRenamePolicy;
import com.together.semiprj.member.model.vo.User;


@WebServlet("/board/*")
public class BoardController222 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
				String method = req.getMethod();
				
				String uri = req.getRequestURI();
				String contextPath = req.getContextPath();
				
				String command = uri.substring( (contextPath + "/board/").length() );
				
				String path = null;
				RequestDispatcher dispatcher = null;
				String message = null;
				
				try {
					
					BoardService222 service = new BoardService222();
					
					int cp = req.getParameter("cp") == null ? 1 : Integer.parseInt(req.getParameter("cp"));
					

					if(command.equals("notice")) {
						
						int bc = 110;
						
						Pagination pagination = service.getPagination(cp, bc);
						
						List<Board> boardList = service.selectBoardList(pagination, bc);
						
						req.setAttribute("pagination", pagination);
						req.setAttribute("boardList", boardList);
						req.setAttribute("boardCd", bc);

						path = "/WEB-INF/views/board/notice.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
					}
					
					
					
					
					else if(command.equals("nwrite")) {
						int boardCd = Integer.parseInt(req.getParameter("boardCd"));
						
						String boardName = service.selectBoardName(boardCd);
						
						req.setAttribute("boardName", boardName);
						req.setAttribute("boardCd", boardCd);
						
						path = "/WEB-INF/views/board/nwrite.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
					}
					
					else if(command.equals("uploadFile")) {
						
						int maxSize = 1024 * 1024 * 100; // 100MB
						
						HttpSession session = req.getSession();
						
						String root = session.getServletContext().getRealPath("/");
						
						String filePath = "/resources/images/board/";
						
						String realPath = root + filePath;
						
						
						MultipartRequest mReq = new MultipartRequest(req, realPath, maxSize, "UTF-8", new MyRenamePolicy());
						
						Enumeration<String> files = mReq.getFileNames();
						
						BoardImage img = new BoardImage();
						
						String name = files.nextElement();
						
						resp.getWriter().print(mReq.getFilesystemName(name));
						
						
					}
					
					
					
					else if(command.equals("ninsert")) {
						String boardTitle = req.getParameter("boardTitle");
						String boardContent = req.getParameter("boardContent").replaceAll(req.getContextPath(), "");
						String picPath = req.getParameter("input-img");
						int boardCd = Integer.parseInt(req.getParameter("boardCd"));

						HttpSession session = req.getSession();
						
						int memberNo = ((User)session.getAttribute("loginMember")).getMemberNo();
						
						int result = service.insertBoard(boardTitle, boardContent, memberNo, picPath, boardCd);
						
						if(result > 0) { // ??????
							message = "???????????? ?????? ???????????????.";

							// ?????? ?????? redirect ??????
							path = req.getContextPath()+"/nboard/view?cp=1&boardNo="+result+"&boardCd="+boardCd+"";
						}else { // ??????

							message = "????????? ?????? ??? ????????? ??????????????????.";

							// ?????? ????????? ?????? ?????? redirect ??????
							path = req.getContextPath()+"board/nwrite?boardCd="+boardCd+"&cp=1";

						}
						session.setAttribute("message", message);
						resp.sendRedirect(path);
						
						
					}
					
					else if(command.equals("pwrite")) {
						int boardCd = Integer.parseInt(req.getParameter("boardCd"));
						
						String boardName = service.selectBoardName(boardCd);
						
						req.setAttribute("boardName", boardName);
						req.setAttribute("boardCd", boardCd);
						
						path = "/WEB-INF/views/board/pwrite.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
					}
					
					
					else if(command.equals("pinsert")) {
						int maxSize = 1024 * 1024 * 100; // 100MB
						
						HttpSession session = req.getSession();
						
						String root = session.getServletContext().getRealPath("/");
						
						String filePath = "/resources/images/board/";
						
						String realPath = root + filePath;
						
						
						
						MultipartRequest mReq = new MultipartRequest(req, realPath, maxSize, "UTF-8", new MyRenamePolicy());
						
						String boardContent = mReq.getParameter("boardContent");
						int boardCd = Integer.parseInt(mReq.getParameter("boardCd"));
						
						int memberNo = ((User)session.getAttribute("loginMember")).getMemberNo();
						
						Board board = new Board();
						board.setBoardContent(boardContent);
						board.setMemberNo(memberNo);
						
						// 2) ?????? ????????? ????????????
						Enumeration<String> files = mReq.getFileNames();
						// Enumeration == iterator (ResultSet??? ??????)
						// -> ????????? ????????? ?????? input type="file" ????????? name ????????? ??????
						// 		-> ????????? ??????????????? ????????? ?????? ????????? ?????????
						
						// ????????? ????????? ????????? ?????? List ??????
						List<BoardImage> imgList = new ArrayList<BoardImage>();
						
						while(files.hasMoreElements()) {
							// ?????? ??????(name)??? ????????? true
							
							String name = files.nextElement(); // ?????? ?????????(name) ????????????
							
							// ?????? ????????? ???????????? ????????? ????????????
							if(mReq.getFilesystemName(name) != null) {
								BoardImage temp = new BoardImage();

								temp.setImgName(mReq.getFilesystemName(name));
								temp.setImgOriginal(mReq.getOriginalFileName(name));
								temp.setImgPath(filePath);

								// name(img0~img3)?????? ????????? ????????? "img" ???????????? ??????
								temp.setImgLevel(  Integer.parseInt( name.replace("img", "")  ) );
								
								// imgList??? ??????
								imgList.add(temp);
								
							} // end if
							
						} // end while
						
						// board, imgList??? DB??? ???????????? ????????? ?????? ??? ?????? ??????
						int result = service.insertBoard(board, imgList, boardCd);

						if(result > 0) { // ??????
							message = "???????????? ?????? ???????????????.";

							// ?????? ?????? redirect ??????
							path = req.getContextPath()+"/pboard/view?no="+result+"&cp=1&boardCd="+boardCd;
						}else { // ??????

							message = "????????? ?????? ??? ????????? ??????????????????.";

							// ?????? ????????? ?????? ?????? redirect ??????
							path = req.getContextPath()+"board/pwrite?boardCd="+boardCd+"&cp=1";

						}
						session.setAttribute("message", message);
						resp.sendRedirect(path);
					}
					
					else if(command.equals("event")) {
						
						int bc = 60;
						
						Pagination pagination = service.getPagination(cp, bc);
						
						List<Board> boardList = service.selectBoardList(pagination, bc);
						
						req.setAttribute("pagination", pagination);
						req.setAttribute("boardList", boardList);
						req.setAttribute("boardCd", bc);
						
						path = "/WEB-INF/views/board/eventList.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
					}
					
					else if(command.equals("updateForm")) {
						
						int boardCd = Integer.parseInt(req.getParameter("boardCd"));
						int boardNo = Integer.parseInt(req.getParameter("boardNo"));
						
						Board board = service.selectBoardUpdate(boardCd, boardNo, req.getContextPath());
						
						req.setAttribute("board", board);
						
						path = "/WEB-INF/views/board/updatenwrite.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
						
						
					}
					
					else if(command.equals("cancel")) {
						int currentPage = Integer.parseInt(req.getParameter("cp"));
						int boardCd = Integer.parseInt(req.getParameter("boardCd"));
						int boardNo = Integer.parseInt(req.getParameter("boardNo"));
						
						resp.sendRedirect(req.getContextPath()+"/nboard/view?cp-"+currentPage+"&boardNo="+boardNo+"&boardCd="+boardCd);
					}
					
					else if(command.equals("nupdate")) {
						String boardTitle = req.getParameter("boardTitle");
						String boardContent = req.getParameter("boardContent").replaceAll(req.getContextPath(), "");
						String picPath = req.getParameter("input-img");
						int boardCd = Integer.parseInt(req.getParameter("boardCd"));
						int boardNo = Integer.parseInt(req.getParameter("boardNo"));
						

						HttpSession session = req.getSession();
						
						int memberNo = ((User)session.getAttribute("loginMember")).getMemberNo();
						
						int result = service.updateBoard(boardTitle, boardContent, memberNo, picPath, boardCd, boardNo);
						
						if(result > 0) {
							session.setAttribute("message", "???????????? ?????????????????????.");
							resp.sendRedirect(req.getContextPath()+"/nboard/view?boardNo="+boardNo+"&boardCd="+boardCd+"");
							
						}else {
							session.setAttribute("message", "????????? ?????? ?????????????????????.");
							resp.sendRedirect(req.getContextPath()+"/nboard/view?boardNo="+boardNo+"&boardCd="+boardCd+"");
						}
						
					}
					
					else if(command.equals("pupdateForm")) {
						int boardNo = Integer.parseInt(req.getParameter("boardNo"));
						int boardCd = Integer.parseInt(req.getParameter("boardCd"));
						
						Board board = service.selectPboardUpdate(boardNo, boardCd);
						
						if(board != null) {
							List<BoardImage> boardImageList = service.selectBoardImage(boardNo);
							
							
							if(boardImageList != null) {
								req.setAttribute("board", board);
								req.setAttribute("boardImage", boardImageList);
								
							}
							
						}
						
						path = "/WEB-INF/views/board/updatepwrite.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
						
						
					}
					
					
					else if(command.equals("pupdate")) {
						int maxSize = 1024 * 1024 * 100; // 100MB
						
						HttpSession session = req.getSession();
						
						String root = session.getServletContext().getRealPath("/");
						
						String filePath = "/resources/images/board/";
						
						String realPath = root + filePath;
						
						
						
						MultipartRequest mReq = new MultipartRequest(req, realPath, maxSize, "UTF-8", new MyRenamePolicy());
						
						String boardContent = mReq.getParameter("boardContent");
						int boardCd = Integer.parseInt(mReq.getParameter("boardCd"));
						int boardNo = Integer.parseInt(mReq.getParameter("boardNo"));
						
						
						int memberNo = ((User)session.getAttribute("loginMember")).getMemberNo();
						
						Board board = new Board();
						board.setBoardContent(boardContent);
						board.setBoardNo(boardNo);
						board.setBoardCode(boardCd);
						board.setMemberNo(memberNo);
						
						Enumeration<String> names = mReq.getParameterNames();
						
						List<BoardImage> oldImgList = new ArrayList<BoardImage>();
						
						while(names.hasMoreElements()) {
							String name = names.nextElement();
							
							if(name.contains("img")) {
								BoardImage temp = new BoardImage();
								temp.setImgPath(mReq.getParameter(name).substring(0, 24));
								temp.setImgName(mReq.getParameter(name).substring(24));
								temp.setImgLevel(Integer.parseInt(name.replace("img", "")));
								temp.setBoardNo(boardNo);
								
								oldImgList.add(temp);
								
							} // end if
							
						} // end while
						
						// 2) ?????? ????????? ????????????
						Enumeration<String> files = mReq.getFileNames();
						// Enumeration == iterator (ResultSet??? ??????)
						// -> ????????? ????????? ?????? input type="file" ????????? name ????????? ??????
						// 		-> ????????? ??????????????? ????????? ?????? ????????? ?????????
						
						// ????????? ????????? ????????? ?????? List ??????
						List<BoardImage> imgList = new ArrayList<BoardImage>();
						
						
						while(files.hasMoreElements()) {
							// ?????? ??????(name)??? ????????? true
							
							String name = files.nextElement(); // ?????? ?????????(name) ????????????
							
							
							// ?????? ????????? ???????????? ????????? ????????????
							if(mReq.getFilesystemName(name) != null) {
								BoardImage temp = new BoardImage();

								temp.setImgName(mReq.getFilesystemName(name));
								temp.setImgOriginal(mReq.getOriginalFileName(name));
								temp.setImgPath(filePath);

								// name(img0~img3)?????? ????????? ????????? "img" ???????????? ??????
								temp.setImgLevel(  Integer.parseInt( name.replace("img", "")  ) );
								temp.setBoardNo(boardNo);
								
								// imgList??? ??????
								imgList.add(temp);
								
							} // end if
							
						} // end while
						
						List<BoardImage> joined = new ArrayList<BoardImage>();
						
						joined.addAll(oldImgList);
						joined.addAll(imgList);
						
						// board, imgList??? DB??? ???????????? ????????? ?????? ??? ?????? ??????
						int result = service.updateImgBoard(board, joined, boardCd);

						if(result > 0) { // ??????
							message = "???????????? ?????? ???????????????.";

							// ?????? ?????? redirect ??????
							path = req.getContextPath()+"/pboard/view?no="+boardNo+"&boardCd="+boardCd;
						}else { // ??????

							message = "????????? ?????? ??? ????????? ??????????????????.";

							// ?????? ????????? ?????? ?????? redirect ??????
							path = req.getContextPath()+"/pboard/view?no="+boardNo+"&boardCd="+boardCd;

						}
						session.setAttribute("message", message);
						resp.sendRedirect(path);
						
						
					}
					
					
					else if(command.equals("ndelete")) {
						
						int boardCd = Integer.parseInt(req.getParameter("boardCd"));
						int boardNo = Integer.parseInt(req.getParameter("boardNo"));
						
						HttpSession session = req.getSession();
						
						int memberNo = ((User)session.getAttribute("loginMember")).getMemberNo();
						
						
						int result = service.nBoardDelete(boardCd, boardNo, memberNo);
						
						if(result > 0) {
							message = "???????????? ?????????????????????.";
							path = req.getContextPath() + "/nboard/list?boardCd="+boardCd;
							
						}else {
							message = "????????? ????????? ????????? ??????????????????.";
							path = req.getContextPath() + "/nboard/view?&boardNo="+boardNo+"&boardCd="+boardCd;
							
						}
						
						session.setAttribute("message", message);
						resp.sendRedirect(path);
					}
					
					else if(command.equals("pdelete")) {
						
						int boardCd = Integer.parseInt(req.getParameter("boardCd"));
						int boardNo = Integer.parseInt(req.getParameter("boardNo"));
						
						HttpSession session = req.getSession();
						
						int memberNo = ((User)session.getAttribute("loginMember")).getMemberNo();
						
						
						int result = service.nBoardDelete(boardCd, boardNo, memberNo);
						
						if(result > 0) {
							message = "???????????? ?????????????????????.";
							path = req.getContextPath() + "/pboard/list?boardCd="+boardCd;
							
						}else {
							message = "????????? ????????? ????????? ??????????????????.";
							path = req.getContextPath() + "/nboard/view?no="+boardNo+"&boardCd="+boardCd;
							
						}
						
						session.setAttribute("message", message);
						resp.sendRedirect(path);
					}
					
					
					else if(command.equals("search")) {
						
						String search = req.getParameter("main-search");
						
						Pagination pagination = service.getPagination2(cp, search);
						
						List<Board> boardList = service.searchBoard(pagination, search);
						
						req.setAttribute("pagination", pagination);
						req.setAttribute("boardList", boardList);
						req.setAttribute("search", search);
						
						path = "/WEB-INF/views/board/search.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
						
						
					}
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
}