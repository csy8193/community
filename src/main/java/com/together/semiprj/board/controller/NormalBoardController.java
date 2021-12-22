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
import com.together.semiprj.common.MyRenamePolicy;


@WebServlet("/board/*")
public class NormalBoardController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 데이터 전달 방식 저장용 변수
				String method = req.getMethod();
				
				// 요청 주소 뒷 부분을 잘라내어 구분 방법 만들기
				String uri = req.getRequestURI();
				String contextPath = req.getContextPath();
				
				String command = uri.substring( (contextPath + "/board/").length() );
												// /semi/board/
				// -> 요청 주소에서 /semi/board/ 의 길이만큼 잘라낸 후 나머지 문자열을 저장
				
				String path = null;
				RequestDispatcher dispatcher = null;
				String message = null;
				
				try {
					
					BoardService222 service = new BoardService222();
					
					if(command.equals("notice")) {
						path = "/WEB-INF/views/board/notice.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
						
					}
					
					
					
					
					else if(command.equals("write")) {
						path = "/WEB-INF/views/board/write.jsp";
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
					
					
					
					else if(command.equals("insert")) {
						
						
						
					}
					
				} catch (Exception e) {
					
					
					
				}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
}