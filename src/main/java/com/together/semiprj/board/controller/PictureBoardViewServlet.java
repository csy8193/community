package com.together.semiprj.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.together.semiprj.board.model.service.PboardReplyService;
import com.together.semiprj.board.model.service.PboardService;
import com.together.semiprj.board.model.vo.Pboard;
import com.together.semiprj.board.model.vo.PboardReply;
import com.together.semiprj.member.model.vo.User;



@WebServlet("/pboard/view")
public class PictureBoardViewServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(req.getParameter("no"));
		
		User loginMember = (User)req.getSession().getAttribute("loginMember");
		
		int memberNo = 0;
		if(loginMember != null) memberNo = loginMember.getMemberNo();
		
		PboardService service = new PboardService();
		
		try {
			Pboard board = service.selectBoard(boardNo, memberNo);
			
			if(board !=null) {
				
				List<PboardReply> rList = new PboardReplyService().selectReplyList(boardNo);
				req.setAttribute("rList", rList);
				
				req.setAttribute("board", board);
				String path = "/WEB-INF/views/board/pic-detail.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
			}else {
				req.getSession().setAttribute("message", "게시글이 존재하지 않습니다.");
				
				resp.sendRedirect("list");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
