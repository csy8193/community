package com.together.semiprj.member.model.service;

import static com.together.semiprj.common.JDBCTemplate.*;


import java.sql.Connection;
import java.util.List;

import com.together.semiprj.member.model.vo.Animal;
import com.together.semiprj.member.model.vo.AnimalCategory;

import com.together.semiprj.member.model.dao.UserDAO;
import com.together.semiprj.member.model.vo.User;


public class UserService {
	
	private UserDAO dao = new UserDAO();
	
	/** 회원 가입
	 * @param member
	 * @return result(1 성공)
	 * @throws Exception
	 */
	public int signUp(User member) throws Exception {
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
	public User login(String memberId, String memberPw) throws Exception {
		Connection conn = getConnection();
		
		User loginMember = dao.login(memberId, memberPw, conn);
		
		close(conn);
		
		return loginMember;
	}

	/** 비밀번호 찾기(회원 정보 검색)
	 * @param memberId
	 * @param memberEmail
	 * @return result(1 있음, 0 없음)
	 * @throws Exception
	 */
	public int certification(User member) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.certification(member, conn);
		
		close(conn);
		
		return result;
	}

	/** 비밀번호 찾기(변경)
	 * @param member
	 * @return result(1 성공, 0 실패)
	 * @throws Exception
	 */
	public int pwUpdate(User member) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.pwUpdate(member, conn);
		
		if(result > 0) 	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
	

	/** 반려동물 목록 리스트
	 * @param memberNo
	 * @return aniList
	 * @throws Exception
	 */
	public List<Animal> selectanimalList(int memberNo) throws Exception  {
		Connection conn = getConnection();
		
		List<Animal> aniList = dao.selectanimalList(memberNo, conn);
		
		close(conn);
		
		return aniList;
	}

	/** 카테고리 목록 리스트
	 * @return AnimalCategory
	 * @throws Exception
	 */
	public List<AnimalCategory> selectAnimalCategory() throws Exception{
		
		Connection conn = getConnection();
		
		List<AnimalCategory> AnimalCategory = dao.selectAnimalCategory(conn);
		
		close(conn);
		
		return AnimalCategory;
	}



}
