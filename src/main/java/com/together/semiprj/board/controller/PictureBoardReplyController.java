package com.together.semiprj.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.together.semiprj.board.model.service.PboardReplyService;
import com.together.semiprj.board.model.vo.PboardReply;

@WebServlet("/reply/*")
public class PictureBoardReplyController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method = req.getMethod();
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		String command = uri.substring((contextPath + "/reply/").length());
		
		String temp = null;
		
		PboardReplyService service = new PboardReplyService();
		
		try {
			if(command.equals("slect")) {
				
			}else if(command.equals("insert")) {
				int memberNo = Integer.parseInt(req.getParameter("memberNo"));
				System.out.println(memberNo);
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				String replyContent = req.getParameter("commentArea");
				
				PboardReply reply = new PboardReply();
				reply.setMemberNo(memberNo);
				reply.setBoardNo(boardNo);
				reply.setReplyContent(replyContent);
				
				int result = service.insertReply(reply);
				
				resp.getWriter().print(result);
			}else if(command.equals("update")) {
				
				 int replyNo = Integer.parseInt(req.getParameter("replyNo"));
	             String replyContent = req.getParameter("replyContent");
	             
	             int result = service.updateReply(replyNo, replyContent);
	             
	             resp.getWriter().print(result);

			}else if(command.equals("delete")) {
				int replyNo = Integer.parseInt(req.getParameter("replyNo"));
	             
	             int result = service.deleteReply(replyNo);
	             
	             resp.getWriter().print(result);

			}
		}catch(Exception e) {
			e.printStackTrace();
	         
	         // ajax error 속성 활용을 위한 500에러를 응답으로 전달
	         resp.setStatus(500);
	         resp.getWriter().print(e.getMessage());
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
