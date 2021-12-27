package com.together.semiprj.walk.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.together.semiprj.board.model.vo.Nboard;
import com.together.semiprj.board.model.vo.NboardImage;
import com.together.semiprj.common.MyRenamePolicy;
import com.together.semiprj.member.model.vo.User;
import com.together.semiprj.walk.member.service.WalkService;
import com.together.semiprj.walk.member.vo.Mypoint;
import com.together.semiprj.walk.member.vo.WalkRank;

@WebServlet("/walk/*")
public class WalkController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
				String method = req.getMethod();
				String uri = req.getRequestURI();
				String contextPath = req.getContextPath();
				
				String command = uri.substring( (contextPath + "/walk/").length() );
												// /semi/board/
				
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
						
						System.out.println(rankList);
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
					else if(command.equals("walkinsert")) {

						int memberNo = Integer.parseInt(req.getParameter("loginMemberNo"));
						int continueWalk = Integer.parseInt(req.getParameter("continueWalk"));
						String walktext = req.getParameter("walktext");
						
						
						WalkService service = new WalkService();
						
						int count2 = service.walkinsert(memberNo,walktext,continueWalk);

						resp.getWriter().print(count2);
					}
					else if(command.equals("walkdayshow")) {
						int memberNo = Integer.parseInt(req.getParameter("loginMemberNo"));
						int year = Integer.parseInt(req.getParameter("checky"));
						int month = Integer.parseInt(req.getParameter("checkm"));
						int day = Integer.parseInt(req.getParameter("day"));
						
						WalkService service = new WalkService();
						
						List<Nboard> nboardList = service.walkdayshow(memberNo,year,month,day);
						
						resp.getWriter().print((new Gson()).toJson(nboardList));
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
