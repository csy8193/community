package com.together.semiprj.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/repw")
public class rePasswordServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memberId = req.getParameter("id");
		String memberEmail = req.getParameter("email");
		
		HttpSession session = req.getSession();
		session.setAttribute("memberId", memberId);
		session.setAttribute("memberEmail", memberEmail);
		
		String path = "/WEB-INF/views/member/rePassword.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
