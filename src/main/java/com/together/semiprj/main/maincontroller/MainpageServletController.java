package com.together.semiprj.main.maincontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.together.semiprj.main.mainservice.Mainservice;
import com.together.semiprj.main.vo.BoardInfo;
import com.together.semiprj.main.vo.PersonAnimalInfo;

@WebServlet("/main")
public class MainpageServletController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Mainservice service = new Mainservice();
		BoardInfo boardInfo = null;
		List<BoardInfo> boardList = new ArrayList<BoardInfo>();
		List<BoardInfo> currentFree = new ArrayList<BoardInfo>();
		List<BoardInfo> currentKnowhow = new ArrayList<BoardInfo>();
		List<BoardInfo> currentFreQ = new ArrayList<BoardInfo>();
		List<BoardInfo> currentNotice = new ArrayList<BoardInfo>();
		List<PersonAnimalInfo> topLikePeople = new ArrayList<PersonAnimalInfo>();
		
		try {
			
			boardList = service.selectMostView();
			currentFree = service.selectFreqB(10,10);
			currentKnowhow = service.selectFreqB(20,10);
			currentFreQ = service.selectFreqB(30,10);
			currentNotice = service.selectFreqB(110,10);
			
			topLikePeople = service.selectTopLikePeople();
		}catch(Exception e){
			e.printStackTrace();
		}
		req.setAttribute("mostViewList", boardList);
		req.setAttribute("currentFree", currentFree);
		req.setAttribute("currentKnowhow", currentKnowhow);
		req.setAttribute("currentFreQ", currentFreQ);
		req.setAttribute("currentNotice", currentNotice);
		req.setAttribute("topLikePeople", topLikePeople);
		//필요한 정보 set 후 mainpage foward
		String path = "/WEB-INF/views/common/mainpage.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		dispatcher.forward(req, resp);
	}
}
