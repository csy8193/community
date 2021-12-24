package com.together.semiprj.member.model.service;

import static com.together.semiprj.common.JDBCTemplate.*;

import java.sql.Connection;

import com.together.semiprj.member.model.dao.MemberDAO;
import com.together.semiprj.member.model.vo.Member;



public class MemberService {
	
	private MemberDAO dao = new MemberDAO();
	
	/** 회원 가입
	 * @param member
	 * @return result(1 성공)
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception {
		// 1) DBCP에서 Connection 얻어오기
		Connection conn = getConnection();
		
		// 2) 회원 가입 DAO 수행 후 결과 반환 받기
		int result = dao.signUp(member, conn);
		
		// 3) 트랜잭션 제어
		if(result > 0) 	commit(conn);
		else			rollback(conn);
		
		// 4) Connection 반환
		close(conn);
		
		return result;
	}
	
	/** 아이디 중복 확인
	 * @param inputId
	 * @return result(1 중복, 0 사용 가능)
	 * @throws Exception
	 */
	public int idDupCheck(String inputId) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.idDupCheck(inputId, conn);
		
		close(conn);
		
		return result;
	}

	/** 이메일 중복 확인
	 * @param inputEmail
	 * @return result(1 중복, 0 사용 가능)
	 * @throws Exception
	 */
	public int emailDupCheck(String inputEmail) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.emailDupCheck(inputEmail, conn);
		
		close(conn);
		
		return result;
	}

	/** 로그인 서비스
	 * @param memberId
	 * @param memberPw
	 * @return loginMember(실패시 null 반환)
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw) throws Exception {
		Connection conn = getConnection();
		
		Member loginMember = dao.login(memberId, memberPw, conn);
		
		close(conn);
		
		return loginMember;
	}
	

}
