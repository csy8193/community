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

import com.together.semiprj.board.model.service.NboardService;
import com.together.semiprj.board.model.vo.Nboard;
import com.together.semiprj.board.model.vo.Pagination;
import com.together.semiprj.member.model.vo.User;

@WebServlet("/nboard/list")
public class NormalListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//필요한 정보 set 후 foward
//		1. method 저장
//		String method = req.getMethod();
		HttpSession session = req.getSession();
		User loginmember = (User)session.getAttribute("loginMember");
		int boardCd = Integer.parseInt(req.getParameter("boardCd"));
		
		int memberNo;
		if(loginmember != null) {
			memberNo = loginmember.getMemberNo();
		}
		else {
			memberNo = 1;
		}
		try {
			Nboard nboard = new Nboard();
			int cp = req.getParameter("cp") ==null ? 1 : Integer.parseInt(req.getParameter("cp"));
			//현재페이지 존재?
			
			NboardService service = new NboardService();
			
			//카테고리?
			Pagination pagination = service.getPagination(cp,boardCd);
			//전체 게시글 수 조회 후 페이지네이션 객체에 넣으면 페이지 계산 나옴
			
			List<Nboard> boardList = service.selectBoardList(pagination,memberNo, boardCd);
			//페이지네이션 매개변수로 해당하는 페이지의 글들을 전부 가져옴
			req.setAttribute("pagination", pagination);
			req.setAttribute("boardList", boardList);
			req.setAttribute("boardCd", boardCd);
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String path = "/WEB-INF/views/board/nboard-list.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		dispatcher.forward(req, resp);
	}
}
