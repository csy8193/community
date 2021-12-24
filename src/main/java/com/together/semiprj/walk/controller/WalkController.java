package com.together.semiprj.walk.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.together.semiprj.member.model.vo.User;
import com.together.semiprj.walk.member.service.WalkService;
<<<<<<< HEAD
import com.together.semiprj.walk.member.vo.Mypoint;
=======
>>>>>>> 62daa807be32e3dee20aac2ddeb315b330bdca7b
import com.together.semiprj.walk.member.vo.WalkRank;

@WebServlet("/walk/*")
public class WalkController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 데이터 전달 방식 저장용 변수
				String method = req.getMethod();
				// 요청 주소 뒷 부분을 잘라내어 구분 방법 만들기
				String uri = req.getRequestURI();
				String contextPath = req.getContextPath();
				
				String command = uri.substring( (contextPath + "/walk/").length() );
												// /semi/board/
				// -> 요청 주소에서 /semi/board/ 의 길이만큼 잘라낸 후 나머지 문자열을 저장
				
				String path = null;
				RequestDispatcher dispatcher = null;
				String message = null;
				
				try {
					
					if(command.equals("ranking")) {
						
						WalkService service = new WalkService();
						List<WalkRank> rankList = new ArrayList<WalkRank>();
						rankList = service.pointRank();
						
						req.setAttribute("rankList", rankList);
						path = "/WEB-INF/views/walk/ranking.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
						
					}
					
					else if(command.equals("myPoint")) {
						
<<<<<<< HEAD
						HttpSession session = req.getSession();
						User loginUser = (User)session.getAttribute("loginMember");
						int loginMember =0;
						if(loginUser!=null) {
							loginMember = loginUser.getMemberNo();
						}
						WalkService service = new WalkService();
						List<Mypoint> rankList = new ArrayList<Mypoint>();
						rankList = service.myPoint(loginMember);
						req.setAttribute("rankList", rankList);
=======
						WalkService service = new WalkService();
						List<WalkRank> rankList = new ArrayList<WalkRank>();
						rankList = service.pointRank();
						
>>>>>>> 62daa807be32e3dee20aac2ddeb315b330bdca7b
						path = "/WEB-INF/views/walk/myPoint.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
						
					}
					else if(command.equals("walkhistory")) {
						int memberNo = Integer.parseInt(req.getParameter("loginMemberNo"));
						String startday2 = req.getParameter("startwalk");
						String endwalk2 = req.getParameter("endwalk");
						int start = Integer.parseInt(startday2);
						int end = Integer.parseInt(endwalk2);
						
						WalkService service = new WalkService();
						
						List<Integer> history = service.getWalkHistory(memberNo,start,end);
		 				
						resp.getWriter().print((new Gson()).toJson(history));
					}
<<<<<<< HEAD
					else if(command.equals("walkinsert")) {
						int memberNo = Integer.parseInt(req.getParameter("loginMemberNo"));
						String walktext = req.getParameter("walktext");
						
						WalkService service = new WalkService();
						
						int result = service.walkinsert(memberNo,walktext);
						
						
						//resp.getWriter().print((new Gson()).toJson(history));
					}
=======
>>>>>>> 62daa807be32e3dee20aac2ddeb315b330bdca7b
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
