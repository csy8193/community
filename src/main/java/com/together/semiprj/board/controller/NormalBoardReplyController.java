package com.together.semiprj.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.together.semiprj.board.model.service.NboardReplyService;
import com.together.semiprj.board.model.vo.NboardReply;

@WebServlet("/nboard/view/reply/*")
public class NormalBoardReplyController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
 		String command = uri.substring((contextPath+"/nboard/view/reply/").length() );
 		
 		NboardReplyService service = new NboardReplyService();
 		try {
 			if(command.equals("select")) {
 				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
 				
 				List<NboardReply> rList = service.selectReplyList(boardNo);
 				
 				new Gson().toJson(rList,resp.getWriter());
 			}
 			else if(command.equals("insert")){
 				//파라미터를 얻어와 Reply Vo에 담아서 Service 호출 후 결과 반환 받기
 				int memberNo = Integer.parseInt(req.getParameter("memberNo"));
 				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
 				String replyContent = req.getParameter("replyContent");
 				NboardReply reply  = new NboardReply();
 				reply.setMemberNo(memberNo);
 				reply.setBoardNo(boardNo);
 				reply.setReplyContent(replyContent);
 				int result = service.insertReply(reply);
 				
 				resp.getWriter().print(result);
 			}
 		}
 		catch(Exception e){
 			e.printStackTrace();
 			
 			//ajax error 속성 활용을 위한 500에러를 응답으로 전달.
 			resp.setStatus(500);
 			resp.getWriter().print(e.getMessage());
 		}
 		
 		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
