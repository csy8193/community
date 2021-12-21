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

@WebServlet("/member/pwupdate")
public class pwUpdateServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String memberId = (String)session.getAttribute("memberId");
		String memberEmail = (String)session.getAttribute("memberEmail");
		String memberPw =req.getParameter("pw1");
		
		User member = new User(memberId, memberEmail, memberPw);
		
		try {
			
			UserService service = new UserService();
			
			int result = service.pwUpdate(member);
			
			String message = null;
			
			if(result > 0) {
				message = "비밀번호 변경 성공!";
			}else {
				message = "비밀번호 변경 실패!";
			}
			
			session.removeAttribute("memberId");
			session.removeAttribute("memberEmail");
			session.setAttribute("message", message);
			
			resp.sendRedirect(req.getContextPath());
			
		}catch(Exception e) {
			e.printStackTrace();
			
			String errorMessage = "회원가입 중 문제가 발생했습니다.";
			
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("e", e); 
			
			String path = "/WEB-INF/views/common/error.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		}
	}
}
