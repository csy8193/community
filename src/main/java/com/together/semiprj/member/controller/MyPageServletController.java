package com.together.semiprj.member.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.together.semiprj.member.model.service.UserService;
import com.together.semiprj.member.model.vo.Animal;
import com.together.semiprj.member.model.vo.AnimalCategory;
import com.together.semiprj.member.model.vo.User;





@WebServlet("/member/*")
public class MyPageServletController extends HttpServlet{
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			UserService service = new UserService();
			
			// 데이터 전달 방식 저장용 변수
			String method = req.getMethod();
			
			// 요청 주소 뒷부분을 잘라내어 구분 방법 만들기
			String uri = req.getRequestURI();
			String contextPath = req.getContextPath();
			
			String command = uri.substring(  (contextPath + "/member/").length()  );
			// -> 요청 주소에서 /semi/board/ 의 길이만큼 잘라낸 후 
			//    나머지 문자열을 command 변수에 저장	
			
			
			String path = null;
			RequestDispatcher dispatcher = null;
			String message = null;
			
			HttpSession session = req.getSession();
			
			try {
				
				// BoardService service = new BoardService();
				
				// ***(중요)***
				// 게시판 관련 기능 수행 시 "현재 페이지"는 여러 의미로 많이 사용되기 때문에
				// 미리 얻어와서 준비 시켜두는 것이 좋다
				int cp = req.getParameter("cp") == null ? 1 : Integer.parseInt(req.getParameter("cp"));     
				
				User loginMember = (User)req.getSession().getAttribute("loginMember");
				
				
				if(command.equals("mypage")) {
					
					if(method.equals("GET")) {
						
						if(loginMember != null) {
							
							List<AnimalCategory> animalCategory = service.selectAnimalCategory();
							
							req.setAttribute("animalCategory", animalCategory);
							
							path = "/WEB-INF/views/member/mypage.jsp";

						}else {
							message = "로그인 후 이용해주세요";
							path = "/WEB-INF/views/member/login.jsp";
							session.setAttribute("message",message);
						}
							
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
						
					}else { // post 방식

					}
	

				}
				
				// 반려동물 리스트 목록
				else if(command.equals("selectAnimal")) {
					int memberNo = Integer.parseInt(req.getParameter("memberNo"));
					List<Animal> aniList = service.selectanimalList(memberNo);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
		
}
