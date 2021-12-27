package com.together.semiprj.member.model.dao;

import static com.together.semiprj.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import com.together.semiprj.member.model.vo.Animal;
import com.together.semiprj.member.model.vo.AnimalCategory;
import com.together.semiprj.member.model.vo.AnimalProfile;
import com.together.semiprj.member.model.vo.Board;
import com.together.semiprj.member.model.vo.User;

	
public class UserDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;

	
	public UserDAO() { 
		try {
			prop = new Properties();
			
			String filePath
			= UserDAO.class.getResource("/com/together/semiprj/sql/member-query.xml").getPath();
						
			prop.loadFromXML(new FileInputStream(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/** 회원 가입
	 * @param member
	 * @param conn
	 * @return result(1 성공)
	 * @throws Exception
	 */
	public int signUp(User member, Connection conn) throws Exception {
		// 1) 결과 저장용 변수 선언
		int result = 0;
		
		try {
			// 2) SQL 얻어오기
			String sql = prop.getProperty("signUp");
			
			// 3) pstmt 객체 생성 및 sql 적재
			pstmt = conn.prepareStatement(sql);
			
			// 4) 위치홀더에 알맞은 값 세팅
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberNm());
			pstmt.setString(4, member.getMemberEmail());
			
			// 5) SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		}finally {
			// 6) 사용한 JDBC 자원 반환
			close(pstmt);
		}
		
		// 7) 결과 반환
		return result;
	}




	/** 아이디 중복 확인
	 * @param inputId
	 * @param conn
	 * @return result(1 중복, 0 사용 가능)
	 * @throws Exception
	 */
	public int idDupCheck(String inputId, Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("idDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, inputId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}



	/** 이메일 중복 확인
	 * @param inputEmail
	 * @param conn
	 * @return result(1 중복, 0 사용 가능)
	 * @throws Exception
	 */
	public int emailDupCheck(String inputEmail, Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("emailDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, inputEmail);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}



	/** 로그인 서비스
	 * @param memberId
	 * @param memberPw
	 * @param conn
	 * @return loginMember(실패시 null)
	 * @throws Exception
	 */
	public User login(String memberId, String memberPw, Connection conn) throws Exception {
		
		User loginMember = null;
		
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new User();
				
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberId(memberId);
				loginMember.setMemberNm(rs.getString("MEMBER_NM"));
				loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				loginMember.setEnrollDt(rs.getDate("ENROLL_DT"));
				loginMember.setStatusCd(rs.getInt("STATUS_CD"));
				loginMember.setGradeCd(rs.getInt("GRADE_CD"));
				loginMember.setProfilePath(rs.getString("ANIMAL_PROFILE_IMG"));
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return loginMember;
	}

	/** 비밀번호 찾기(회원 정보 검색)
	 * @param member
	 * @param conn
	 * @return result(1 있음, 0 없음)
	 * @throws Exception
	 */
	public int certification(User member, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("certification");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberEmail());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}finally{
			close(rs);
			close(pstmt);
		}
		return result;
}

	/** 비밀번호 찾기(변경)
	 * @param member
	 * @param conn
	 * @return result(1 성공, 0 실패)
	 * @throws Exception
	 */
	public int pwUpdate(User member, Connection conn) throws Exception{
		int result = 0;
		
		try {
			// 2) SQL 얻어오기
			String sql = prop.getProperty("pwUpdate");
			
			// 3) pstmt 객체 생성 및 sql 적재
			pstmt = conn.prepareStatement(sql);
			
			// 4) 위치홀더에 알맞은 값 세팅
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberId());
			pstmt.setString(3, member.getMemberEmail());
			
			// 5) SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		}finally {
			// 6) 사용한 JDBC 자원 반환
			close(pstmt);
		}
		
		// 7) 결과 반환
		return result;
	}
	
	
	/** 반려동물 목록 리스트
	 * @param memberNo
	 * @param conn
	 * @return aniList
	 * @throws Exception
	 */
	public List<Animal> selectanimalList(int memberNo, Connection conn) throws Exception{
		
		List<Animal> aniList = new ArrayList<Animal>();
		
		try {
			
			String sql = prop.getProperty("selectanimalList");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Animal animal = new Animal();
				
				animal.setAnimalNo(rs.getInt("ANIMAL_NO"));
				animal.setAnimalNm(rs.getString("ANIMAL_NAME"));
				animal.setAnimalVariety(rs.getString("ANIMAL_VARIETY"));
				animal.setAnimalGender(rs.getString("ANIMAL_SEX"));
				animal.setAnimalBirthday(rs.getString("ANIMAL_BIRTH"));
				animal.setAnimalCategoryCode(rs.getInt("ANIMAL_CATEGORY_CD"));
				animal.setAnimalCategoryName(rs.getString("ANIMAL_CATEGORY_NM"));
				animal.setMemberNo(rs.getInt("MEMBER_NO"));
				animal.setAnimalImgPath(rs.getString("ANIMAL_IMG_PATH"));
				animal.setAnimalImgNm(rs.getString("ANIMAL_IMG_NM"));
				
				aniList.add(animal);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return aniList;
		
}
	
	/** 반려동물 카테고리 조회
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<AnimalCategory> selectAnimalCategory(Connection conn) throws Exception{
		
		List<AnimalCategory> AnimalCategory = new ArrayList<AnimalCategory>();
		try {
			String sql = prop.getProperty("selectAnimalCategory");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AnimalCategory ac = new AnimalCategory();
				
				ac.setAnimalCategoryCode(rs.getInt("ANIMAL_CATEGORY_CD"));
				ac.setAnimalCategoryName(rs.getString("ANIMAL_CATEGORY_NM"));
				
				AnimalCategory.add(ac); 
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return AnimalCategory;
	}



	/** 반려동물 등록
	 * @param animal
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int addAnimal(Animal animal, Connection conn) throws Exception{
		
		System.out.println(5);
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("addAnimal");
			
			pstmt = conn.prepareStatement(sql);
					
			pstmt.setInt(1,animal.getAnimalNo());
			pstmt.setString(2,animal.getAnimalNm());
			pstmt.setString(3,animal.getAnimalVariety());
			pstmt.setString(4,animal.getAnimalGender());
			pstmt.setString(5,animal.getAnimalBirthday());			
			pstmt.setInt(6,animal.getMemberNo());
			pstmt.setInt(7,animal.getAnimalCategoryCode());
			
			result = pstmt.executeUpdate();
			
			System.out.println(6);
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	/** 다음 반려동물 번호 조회
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int nextAnimalNo(Connection conn) throws Exception{
		
		int animalNo = 0;
	
		try {
			String sql = prop.getProperty("nextAnimalNo");

			stmt = conn.createStatement();
			
			rs= stmt.executeQuery(sql);

			if(rs.next()) {
				animalNo = rs.getInt(1);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return animalNo;
	}



	/** 반려동물 프로필사진 삽입
	 * @param aniPro
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int insertaniProfile(AnimalProfile aniPro, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertaniProfile");
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,aniPro.getAnimalImgPath());
			pstmt.setString(2,aniPro.getAnimalImgNm());
			pstmt.setString(3,aniPro.getAnimalImgOriginal());
			pstmt.setInt(4,aniPro.getAnimalNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	/** 프로필 경로 지정
	 * @param profilePath
	 * @param conn
	 * @return int
	 * @throws Exception
	 */
	public int insertDelegateProfile(String profilePath, int memberNo, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertDelegateProfile");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,profilePath);
			pstmt.setInt(2,memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}


	
	/** 프로필 정보 수정
	 * @param animal
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int updateAnimal(Animal animal, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateAnimal");
			
			pstmt = conn.prepareStatement(sql);
					
			
			pstmt.setString(1,animal.getAnimalNm());
			pstmt.setString(2,animal.getAnimalVariety());
			pstmt.setString(3,animal.getAnimalGender());
			pstmt.setString(4,animal.getAnimalBirthday());			
			pstmt.setInt(5,animal.getAnimalCategoryCode());
			pstmt.setInt(6,animal.getAnimalNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	/** 반려동물 프로필 수정
	 * @param animalNo
	 * @param aniPro
	 * @param conn
	 * @return 
	 * @throws Exception
	 */
	public int updateAniProfile(AnimalProfile aniPro, Connection conn) throws Exception{
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateAniProfile");
			
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1,aniPro.getAnimalImgPath());
			pstmt.setString(2,aniPro.getAnimalImgNm());
			pstmt.setString(3,aniPro.getAnimalImgOriginal());
			pstmt.setInt(4,aniPro.getAnimalNo());

			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/** 반려동물 삭제
	 * @param animalNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int deleteAnimal(int animalNo, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteAnimal");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,animalNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	/** 비밀번호 변경
	 * @param updatePw2
	 * @param nowPw
	 * @param memberNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int mypagePwUpdate(String updatePw2, String nowPw, int memberNo, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("mypagePwUpdate");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatePw2);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, nowPw);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	/** 탈퇴하기
	 * @param nowPw
	 * @param memberNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int mypagePwDelete(String nowPw, int memberNo, Connection conn)  throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("mypagePwDelete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, nowPw);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	public List<Board> selectBoardList(int memberNo,int boardCd, Connection conn) throws Exception{
		
		List<Board> bList = null;
		
		try {
			
			String sql = prop.getProperty("selectBoardList");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,memberNo);
			pstmt.setInt(2,boardCd);			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				bList = new ArrayList<Board>();
				
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setBoardName(rs.getString("BOARD_NAME"));
				board.setBoardCode(rs.getInt("BOARD_CD"));
				board.setBoardStatusCode(rs.getInt("STATUS_CD"));
				
				bList.add(board);
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return bList;
	}
	
}
