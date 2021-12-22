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

import com.together.semiprj.board.model.service.NboardReplyService;
import com.together.semiprj.board.model.service.NboardService;
import com.together.semiprj.board.model.vo.Nboard;
import com.together.semiprj.board.model.vo.NboardReply;
import com.together.semiprj.member.model.vo.User;

@WebServlet("/nboard/view")
public class NormalBoardViewServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//필요한 정보 set 후 foward
		int cp = req.getParameter("cp") == null ? 1: Integer.parseInt(req.getParameter("cp"));
		int category =  req.getParameter("category") == null ? 10: Integer.parseInt(req.getParameter("category"));
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		int boardCd = Integer.parseInt(req.getParameter("boardCd"));
		HttpSession session = req.getSession();
		User loginmember = (User)session.getAttribute("loginMember");
		int memberNo;
		if(loginmember != null) {
			memberNo = loginmember.getMemberNo();
		}
		else {
			memberNo = -1;
		}
		try {
			//2. 뷰 정보 모두 가져오기(+조회수 증가)
			//3. 사진 가져오기
			NboardService service = new NboardService();
			Nboard nboard = null;
			nboard =service.selectBoardView(boardNo,memberNo);
			
			//글쓴이 대표 반려동물 프로필 이미지 주소
			String writerAnimalProfile = "";
			if(nboard.getNboardAnimalImg() != null) {
				if(nboard.getNboardAnimalImg().get(0).getImgLevel()==0){
					writerAnimalProfile = (nboard.getNboardAnimalImg()).get(0).getImgPath();
					writerAnimalProfile += (nboard.getNboardAnimalImg()).get(0).getImgName();
				}
			}
			//4. 댓글정보 가져오기
			if(nboard != null) {
				//+ 댓글 목록 조회하기
				List<NboardReply> rList = (new NboardReplyService()).selectReplyList(boardNo);
				
				for(NboardReply dd : rList) {
					System.out.println(dd);
				}
				
				req.setAttribute("rList", rList);
				req.setAttribute("nboard", nboard);
				req.setAttribute("writerAnimal", writerAnimalProfile);
				String path = "/WEB-INF/views/board/nboard-content.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
			}
				//조회실패(게시글 없음)
				//게시판 목록 1페이지로 이동
				//"게시글이 존재하지 않습니다." alert
//				절대경로
			else{
				session.setAttribute("message","게시글이 존재하지 않습니다.");
				resp.sendRedirect("list");
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
