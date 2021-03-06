package com.together.semiprj.member.model.service;

import static com.together.semiprj.common.JDBCTemplate.*;


import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import com.together.semiprj.member.model.vo.Animal;
import com.together.semiprj.member.model.vo.AnimalCategory;
import com.together.semiprj.member.model.vo.AnimalProfile;
import com.together.semiprj.member.model.vo.Board;
import com.together.semiprj.common.XSS;
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

	/** 반려동물 등록
	 * @param animal
	 * @return
	 * @throws Exception
	 */
	public int addAnimal(Animal animal, AnimalProfile aniPro) throws Exception{
		
		Connection conn = getConnection();
		
		int animalNo = dao.nextAnimalNo(conn);
		
		animal.setAnimalNo(animalNo);
		
		animal.setAnimalVariety(XSS.replaceParameter(animal.getAnimalVariety()));
		animal.setAnimalNm(XSS.replaceParameter(animal.getAnimalNm()));
		
		animal.setAnimalVariety(animal.getAnimalVariety().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
		animal.setAnimalNm(animal.getAnimalNm().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
		
		int result = dao.addAnimal(animal,conn);
		
		if(result > 0) {
			
			// 이미지 있을때
			if(aniPro.getAnimalImgPath() != null && aniPro.getAnimalImgOriginal() != null) {
			
				aniPro.setAnimalNo(animalNo);
				
				result = dao.insertaniProfile(aniPro,conn);
				
				// 이미지 등록
				if(result > 0) {
					commit(conn);
				}
				// 이미지 등록 실패
				else {
					rollback(conn); 
				}
			} else {
				// 이미지 없어도 등록 가능이면
				//commit(conn);
				
				// 이미지 필수 이면 롤백
				rollback(conn);
			}

		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	/** 반려동물 프로필 경로 설정
	 * @param profilePath
	 * @return
	 * @throws Exception
	 */
	public int insertDelegateProfile(String profilePath,int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.insertDelegateProfile(profilePath,memberNo,conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;

	}


	/** 반려동물 수정하기
	    * @param animal
	    * @param aniPro
	    * @return
	    * @throws Exception
	    */
	public int updateAnimal(Animal animal, AnimalProfile aniPro) throws Exception{
	      
	   Connection conn = getConnection();
	      
	   int animalNo = animal.getAnimalNo();
	      
	   animal.setAnimalVariety(XSS.replaceParameter(animal.getAnimalVariety()));
	   animal.setAnimalNm(XSS.replaceParameter(animal.getAnimalNm()));
	      
	   animal.setAnimalVariety(animal.getAnimalVariety().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
	   animal.setAnimalNm(animal.getAnimalNm().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
	      
	   int result = dao.updateAnimal(animal,conn);
	   
	   if(result > 0) {
 
		  // 이미지 있을때
	      if(aniPro.getAnimalImgPath() != null && aniPro.getAnimalImgOriginal() != null) {
	            
	         aniPro.setAnimalNo(animalNo);
	            
	         result = dao.updateAniProfile(aniPro,conn);
	         
	         // 이미지 수정
	         if(result > 0) commit(conn);
	         // 이미지 수정 실패
	         else rollback(conn);
	         
	      } else {
	    	  // 프로필?이미지 없어도 내용 수정 가능
	    	  commit(conn);
	      }
	   } else {
	      rollback(conn);
	   }
	   return result;
	}


	
	/** 반려동물 삭제
	 * @param animalNo
	 * @return
	 * @throws Exception
	 */
	public int deleteAnimal(int animalNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteAnimal(animalNo,conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	/** 비밀번호 변경
	 * @param updatePw2
	 * @param nowPw
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int mypagePwUpdate(String updatePw2, String nowPw, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.mypagePwUpdate(updatePw2,nowPw,memberNo,conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int mypagePwDelete(String nowPw, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.mypagePwDelete(nowPw,memberNo,conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	/** 게시판 리스트
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectBoardList(int memberNo,int boardCd) throws Exception{
		
		Connection conn = getConnection();
		
		List<Board> bList = dao.selectBoardList(memberNo,boardCd,conn);
		
		close(conn);
		
		return bList;
	}   
	

	}
