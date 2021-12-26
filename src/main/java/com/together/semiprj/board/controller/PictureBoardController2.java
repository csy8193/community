package com.together.semiprj.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.together.semiprj.board.model.service.PboardService;
import com.together.semiprj.board.model.vo.Pagination;
import com.together.semiprj.board.model.vo.Pboard;
import com.together.semiprj.member.model.vo.User;


@WebServlet("/pboard/list")
public class PictureBoardController2 extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		User loginmember = (User)session.getAttribute("loginMember");
		int boardCd = Integer.parseInt(req.getParameter("boardCd"));
		
		//System.out.println(boardCd);
		
		int memberNo;
		if(loginmember != null) {
			memberNo = loginmember.getMemberNo();
		}else {
			memberNo = 1;
		}
		try {
			Pboard pboard = new Pboard();
			int cp = req.getParameter("cp") ==null ? 1 : Integer.parseInt(req.getParameter("cp"));
			
			PboardService service = new PboardService();
			
			Pagination pagination = service.getPagination(cp, boardCd);
			
			String boardNm = service.boardNmCh(boardCd);
			
			List<Pboard> boardList = service.selectBoardList(pagination, memberNo, boardCd);
			
			req.setAttribute("pagination", pagination);
			req.setAttribute("boardList", boardList);
			req.setAttribute("boardCd", boardCd);
			req.setAttribute("boardNm", boardNm);
			//System.out.println(boardNm);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		String path = "/WEB-INF/views/board/pic-board.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		dispatcher.forward(req, resp);
	}
}
