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
						
						int cd = Integer.parseInt(req.getParameter("cd"));
						
						Pagination pagination = service.getPagination(cp, cd);
						
						List<Board> boardList = service.selectBoardList(pagination, cd);
						
						req.setAttribute("pagination", pagination);
						req.setAttribute("boardList", boardList);

						path = "/WEB-INF/views/board/notice.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
					}
					
					
					
					
					else if(command.equals("nwrite")) {
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
						
						System.out.println("얻어온 name : " + name);
						System.out.println("변환된 파일명 : " + mReq.getFilesystemName(name));
						System.out.println("원본 파일명 : " + mReq.getOriginalFileName(name));
						
						resp.getWriter().print(mReq.getFilesystemName(name));
						
						
					}
					
					
					
					else if(command.equals("ninsert")) {
						String boardTitle = req.getParameter("boardTitle");
						String boardContent = req.getParameter("boardContent");
						
						HttpSession session = req.getSession();
						
						int memberNo = ((User)session.getAttribute("loginMember")).getMemberNo();
						
						int result = service.insertBoard(boardTitle, boardContent, memberNo);
						
						
					}
					
					else if(command.equals("pwrite")) {
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
//						int categoryCode = Integer.parseInt( mReq.getParameter("categoryCode") );
						
						int memberNo = ((User)session.getAttribute("loginMember")).getMemberNo();
						
						Board board = new Board();
//						board.setBoardTitle(boardTitle);
						board.setBoardContent(boardContent);
//						board.setCategoryCode(categoryCode);
						board.setMemberNo(memberNo);
						
						// 2) 파일 형식의 파라미터
						Enumeration<String> files = mReq.getFileNames();
						// Enumeration == iterator (ResultSet과 비슷)
						// -> 폼에서 전달된 모든 input type="file" 요소의 name 속성을 반환
						// 		-> 파일이 업로드되지 않아도 모든 요소를 얻어옴
						
						// 업로된 이미지 정보를 담을 List 생성
						List<BoardImage> imgList = new ArrayList<BoardImage>();
						
						while(files.hasMoreElements()) {
							// 다음 요소(name)가 있으면 true
							
							String name = files.nextElement(); // 다음 요소값(name) 얻어오기
							
							System.out.println("얻어온 name : " + name);
							System.out.println("변환된 파일명 : " + mReq.getFilesystemName(name));
							System.out.println("원본 파일명 : " + mReq.getOriginalFileName(name));
							
							// 현재 요소에 업로드된 파일이 있을경우
							if(mReq.getFilesystemName(name) != null) {
								BoardImage temp = new BoardImage();

								temp.setImgName(mReq.getFilesystemName(name));
								temp.setImgOriginal(mReq.getOriginalFileName(name));
								temp.setImgPath(filePath);

								// name(img0~img3)에서 숫자를 제외한 "img" 문자열을 제거
								temp.setImgLevel(  Integer.parseInt( name.replace("img", "")  ) );
								
								// imgList에 추가
								imgList.add(temp);
								
							} // end if
							
						} // end while
						
						// board, imgList를 DB에 삽입하는 서비스 호출 후 결과 반환
						int result = service.insertBoard(board, imgList);
//
//						if(result > 0) { // 성공
//							message = "게시글이 등록 되었습니다.";
//
//							// 상세 조회 redirect 주소
//							path = "view?no="+result+"&cp=1";
//						}else { // 실패
//
//							message = "게시글 등록 중 문제가 발생했습니다.";
//
//							// 다시 게시글 작성 화면 redirect 주소
//							path = "insert";
//
//						}
//						session.setAttribute("message", message);
//						resp.sendRedirect(path);
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
