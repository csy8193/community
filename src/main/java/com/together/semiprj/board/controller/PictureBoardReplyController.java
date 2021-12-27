package com.together.semiprj.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
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
			if(command.equals("select")) {
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
 				List<PboardReply> rList = service.selectReplyList(boardNo);
 				
				new Gson().toJson(rList,resp.getWriter());
				
				
				
			}else if(command.equals("insert")) {
				int memberNo = Integer.parseInt(req.getParameter("memberNo"));
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				int feedbackNo = Integer.parseInt(req.getParameter("feedbackNo"));
				String replyContent = req.getParameter("replyContent");
				
				PboardReply reply = new PboardReply();
				
				reply.setMemberNo(memberNo);
				reply.setBoardNo(boardNo);
				reply.setReplyContent(replyContent);
				reply.setFeedbackReplyNo(feedbackNo);
				
				int result = service.insertReply(reply);
				resp.getWriter().print(result);
				
			}else if(command.equals("update")) {
				
				 int replyNo = Integer.parseInt(req.getParameter("replyNo"));
	             String replyContent = req.getParameter("replyContent");
	             
	             int result = service.updateReply(replyNo, replyContent);
	             
	             resp.getWriter().print(result);

			}else if(command.equals("delete")) {
				int replyNo = Integer.parseInt(req.getParameter("delReplyNo"));
	             
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
