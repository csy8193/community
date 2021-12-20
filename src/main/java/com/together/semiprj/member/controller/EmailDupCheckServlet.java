package com.together.semiprj.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.together.semiprj.member.model.service.MemberService;


@WebServlet("/member/emailDupCheck")
public class EmailDupCheckServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		String inputEmail = req.getParameter("inputEmail");
		try {
			MemberService service = new MemberService();
			
			
			int result = service.emailDupCheck(inputEmail);
			
			PrintWriter out = resp.getWriter();
			
			out.print(result);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			resp.setStatus(500);
			
			resp.getWriter().print(e.getMessage());
		}
	
	}
}
