package com.together.semiprj.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.together.semiprj.board.model.service.PboardService;
import com.together.semiprj.board.model.vo.Pboard;

@WebServlet("/pboard/like")
public class pictureBoardLikeServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int memberNo = Integer.parseInt(req.getParameter("loginMemberNo"));
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		int likecount = Integer.parseInt(req.getParameter("likecount"));
		PboardService service = new PboardService();
		Pboard board = new Pboard();
		
		try {
			int result = service.plusLike(memberNo, boardNo);
			// System.out.println(result);
			resp.getWriter().print(result);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
