package com.together.semiprj.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.together.semiprj.member.model.service.UserService;
import com.together.semiprj.member.model.vo.User;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/login.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPw");
		
		try {
			
			UserService service = new UserService();
			
			User loginMember = service.login(memberId, memberPw);
			
			HttpSession session = req.getSession();
			
			if(loginMember != null) {
				
				if(loginMember.getStatusCd() == 1) {
					session.setAttribute("loginMember", loginMember);
					
					session.setMaxInactiveInterval(3000);
					
					Cookie cookie = new Cookie("saveId", memberId);
					
					if(req.getParameter("save") != null) {
						
						cookie.setMaxAge(60 * 60 * 24 * 30);
					}else {
						cookie.setMaxAge(0);
					}
					cookie.setPath(req.getContextPath());
					
					resp.addCookie(cookie);
				}else if(loginMember.getStatusCd() == 2) {
					session.setAttribute("message", "탈퇴한 회원 입니다.");
				}else {
					session.setAttribute("message", "정지된 회원 입니다.");
				}
				
			}else {
				session.setAttribute("message", "아이디 또는 비밀번호를 확인해주세요.");
			}
			resp.sendRedirect(req.getContextPath());
		}catch(Exception e) {
			e.printStackTrace();;
			
			String errorMessage = "로그인 중 문제가 발생했습니다.";
			
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("e", e);
			
			String path = "/WEB-INF/views/common/error.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		}
	}
}
