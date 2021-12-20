package com.together.semiprj.walk.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/walk/*")
public class WalkController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 데이터 전달 방식 저장용 변수
				String method = req.getMethod();
				
				// 요청 주소 뒷 부분을 잘라내어 구분 방법 만들기
				String uri = req.getRequestURI();
				String contextPath = req.getContextPath();
				
				String command = uri.substring( (contextPath + "/walk/").length() );
												// /semi/board/
				// -> 요청 주소에서 /semi/board/ 의 길이만큼 잘라낸 후 나머지 문자열을 저장
				
				String path = null;
				RequestDispatcher dispatcher = null;
				String message = null;
				
				try {
					
					if(command.equals("ranking")) {
						path = "/WEB-INF/views/walk/ranking.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
						
					}
					
					else if(command.equals("myPoint")) {
						path = "/WEB-INF/views/walk/myPoint.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
						
					}
					
				} catch (Exception e) {
					
					
					
				}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
