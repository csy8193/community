package com.together.semiprj.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.together.semiprj.board.model.service.NboardService;
import com.together.semiprj.member.model.vo.Member;

@WebServlet("/nboard/view/sub/*")
public class NormalBoardSubFunctionController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
 		String command = uri.substring((contextPath+"/nboard/view/sub/").length() );
 		
 		HttpSession session = req.getSession();
 		//Member loginmember = (Member)session.getAttribute("loginMember");
 		NboardService service = new NboardService();
 		try {
 		if(command.equals("like")) {
 			
 			int memberNo = Integer.parseInt(req.getParameter("loginMemberNo"));
 			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
 			int likecount = Integer.parseInt(req.getParameter("likecount"));
 			
 			int result = service.plusLike(memberNo,boardNo);
 			if(result>0) result = likecount+1;
 			resp.getWriter().print(result);
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
