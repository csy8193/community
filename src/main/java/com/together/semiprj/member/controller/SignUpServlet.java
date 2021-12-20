package com.together.semiprj.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.together.semiprj.member.model.service.MemberService;
import com.together.semiprj.member.model.vo.Member;


@WebServlet("/member/signup")
public class SignUpServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/signUp.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	// POST 방식 요청 시 회원 정보 삽입 수행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// POST 방식의 경우 문자 인코딩 처리가 필요하지만
		// EncodingFilter를 생성하였기 때문에 별도 처리가 필요 없다!
		
		// 회원 가입 시 입력 받은 파라미터를 모두 변수에 저장
		String memberId = req.getParameter("id");
		String memberPw = req.getParameter("pw1");
		String memberNm = req.getParameter("name");
		String memberEmail = req.getParameter("email");
		
		
		// 저장된 파라미터를 하나의 Member 객체에 저장
		Member member = new Member(memberId, memberPw, memberNm, memberEmail);
		
		try {
			// MemberService 객체 생성
			MemberService service = new MemberService();
			
			// 회원 가입 Service 수행 후 결과 반환 받기
			int result = service.signUp(member);
			
			// 중간 확인
			//System.out.println("result : " + result);
			
			// 결과에 따라서 화면에 경고창(alert) 출력
			String message = null;
			
			if(result > 0) { // 회원 가입 성공 시
				message = "회원 가입 성공!";
			}
			
			// redirect를 할 예정이기 때문에
			// req를 사용할 값 전달이 불가능함
			// -> 그래서 범위가 한단계 더 높은 session 사용
			
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			
			// 메인 페이지로 돌아갈 예정
			// -> 이미 메인페이지를 보여주는 서비스가 구현되어 있음
			// --> 해당 서비스를 요청
			// ---> 요청 받은 Servlet이 다른 Servlet을 요청 == 재요청(redirect)
			resp.sendRedirect(req.getContextPath());
			
			// redirect 시 기존 req resp 객체 삭제하고
			// 재요청 시 새롭게 생성
			
		}catch(Exception e) {
			e.printStackTrace();
			
			// HTTP 상태 코드 - 500 Internal Server Error
			// - 백엔드에서 발생한 에러
			// - 로직 수행에 사용되는 값, SQL, DB연결 과정, 코드 오타 등
			// 문제가 발생했을 때 나타난 상태 코드
			
			String errorMessage = "회원가입 중 문제가 발생했습니다.";
			
			req.setAttribute("errorMessage", errorMessage); // 에러 메시지
			req.setAttribute("e", e); // 예외관련 정보를 담고 있는 객체
			
			// error.jsp로 요청 위임하여 응답하여 출력하기
			String path = "/WEB-INF/views/common/error.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		}
		
	}
	
}
