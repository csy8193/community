package com.together.semiprj.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.together.semiprj.member.model.service.UserService;
import com.together.semiprj.member.model.vo.User;


@WebServlet("/memberUpdate/*")
public class MypageMemberUpdateServletController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserService service = new UserService();
		
		// 데이터 전달 방식 저장용 변수
		String method = req.getMethod();
		
		// 요청 주소 뒷부분을 잘라내어 구분 방법 만들기
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		String command = uri.substring(  (contextPath + "/memberUpdate/").length()  );
		// -> 요청 주소에서 /semi/board/ 의 길이만큼 잘라낸 후 
		//    나머지 문자열을 command 변수에 저장	
		
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		
		HttpSession session = req.getSession();
		
		try {
			
			User loginMember = (User)req.getSession().getAttribute("loginMember");
			
			if(command.equals("mypagePwUpdate")) {
				
				if(method.equals("POST")) {
					
					String nowPw = req.getParameter("nowPw");
					String updatePw2 = req.getParameter("userPw2");
					
					System.out.println(nowPw);
					System.out.println(updatePw2);

					
				}
				
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
