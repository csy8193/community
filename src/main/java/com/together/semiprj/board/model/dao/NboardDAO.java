package com.together.semiprj.board.model.dao;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.together.semiprj.board.model.vo.Nboard;
import com.together.semiprj.board.model.vo.NboardAnimalImg;
import com.together.semiprj.board.model.vo.NboardImage;
import com.together.semiprj.board.model.vo.Pagination;

import static com.together.semiprj.common.JDBCTemplate.*;
public class NboardDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public NboardDAO() {
		String filePath = NboardDAO.class.getResource("/com/together/semiprj/sql/nboard-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**전체 게시글 수 조회
	 * @param conn
	 * @param boardCate 
	 * @param memberNo 
	 * @return listCount(성공 시 전체 게시글 수)
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int boardCate) throws Exception{
		int listCount =0;
		try {
			String sql = prop.getProperty("count-all-nboard-list");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardCate);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	/**페이지네이션 해당 게시글 모두 반환
	 * @param conn
	 * @param pagination
	 * @return nboardList
	 * @throws Exception
	 */
	public List<Nboard> selectBoardList(Connection conn, Pagination pagination,int memberNo) throws Exception{
		List<Nboard> nboardList;
		Nboard nboard;
		try {
			String sql = prop.getProperty("selectBoardList");
			
			int startRow = ( pagination.getCurrentPage() -1)* pagination.getLimit() +1 ;
			int endRow = startRow + pagination.getLimit() -1;
//			조회하려는 범위의 시작 , 끝
			
//			게시판에 필요한 정보 : 
//			1. 카테고리 이름 ㅇ
//			2. 주제 ㅇ
//			3. 내용 ㅇ
//			4. 작성자 아이디 ㅇ
//			5. 작성자 반려동물 이미지 X
//			6. 게시글 이미지 X 
//			7. 좋아요 수 o
//			8. 댓글 수 o
//			9. 조회 수 O
//			10.작성일 O
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, 10);
			//게시판 카테고리
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rs = pstmt.executeQuery();
			nboardList = new ArrayList<Nboard>();
			
			
			while(rs.next()) {
				nboard = new Nboard();
				nboard.setBoardNo(rs.getInt("BOARD_NO"));
				nboard.setBoardTitle(rs.getString("BOARD_TITLE"));
				nboard.setBoardContent(rs.getString("BOARD_CONTENT"));
				nboard.setReadCount(rs.getInt("READ_COUNT"));
				nboard.setBoardCd(rs.getInt("BOARD_CD"));
				nboard.setCreateDt(rs.getString("CREATE_DT"));
				nboard.setStatusCd(rs.getInt("STATUS_CD"));
				nboard.setMemberNo(rs.getInt("MEMBER_NO"));
				nboard.setBoardName(rs.getString("BOARD_NAME"));
				nboard.setMemberId(rs.getString("MEMBER_ID"));
				nboard.setLikecount(rs.getInt("LIKECOUNT"));
				nboard.setReplycount(rs.getInt("REPLYCOUNT"));
				nboard.setLikeDone(rs.getBoolean("LIKEDONE"));
				nboard.setBoardPicNo(rs.getInt("BOARD_PIC_NO"));
				nboard.setBoardMainImgPath(rs.getString("MAINIMGPATH"));
				nboard.setAnimalImgNo(rs.getInt("ANIMAL_IMG_NO"));
				nboard.setAnimalMainImgPath(rs.getNString("MAINANIMALPATH"));
				
				nboardList.add(nboard);
			}
			}finally {
				close(rs);
				close(pstmt);
			}
		
		return nboardList;
	}
	/**상세조회문
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @return nboard (상세게시판 정보)
	 * @throws Exception
	 */
	public Nboard selectBoardView(Connection conn, int boardNo, int memberNo) throws Exception{
		Nboard nboard = null;
		try {
			String sql = prop.getProperty("selectBoardView");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			//게시판 번호로 조회
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				nboard = new Nboard();
				nboard.setBoardNo(rs.getInt("BOARD_NO"));
				nboard.setBoardTitle(rs.getString("BOARD_TITLE"));
				nboard.setBoardContent(rs.getString("BOARD_CONTENT"));
				nboard.setReadCount(rs.getInt("READ_COUNT"));
				nboard.setBoardCd(rs.getInt("BOARD_CD"));
				nboard.setCreateDt(rs.getString("CREATE_DT"));
				nboard.setStatusCd(rs.getInt("STATUS_CD"));
				nboard.setMemberNo(rs.getInt("MEMBER_NO"));
				nboard.setBoardName(rs.getString("BOARD_NAME"));
				nboard.setMemberId(rs.getString("MEMBER_ID"));
				nboard.setLikecount(rs.getInt("LIKECOUNT"));
				nboard.setReplycount(rs.getInt("REPLYCOUNT"));
				nboard.setLikeDone(rs.getBoolean("LIKEDONE"));
				nboard.setAnimalImgNo(rs.getInt("ANIMAL_IMG_NO"));
				nboard.setAnimalMainImgPath(rs.getNString("MAINANIMALPATH"));
			}
			
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return nboard;
	}
	/** 조회수 증가 
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int readCountPlus(Connection conn, int boardNo) throws Exception{
		int result =0;
		
		try {
			String sql = prop.getProperty("readCountPlus");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
			System.out.println(result);
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	/**특정 게시글의 이미지 목록 조회
	 * @param conn
	 * @param boardNo
	 * @return imgList
	 * @throws Exception
	 */
	public List<NboardImage> selectNboardImgList(Connection conn, int boardNo) throws Exception{
/*		SELECT * FROM IMG
		
*/		
		List<NboardImage> imgList = null; 
		
		try {
			String sql = prop.getProperty("selectBoardImageList");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs= pstmt.executeQuery();
			imgList =new ArrayList<NboardImage>();
			while(rs.next()) {
				NboardImage img = new NboardImage();
				img.setImgNo(rs.getInt(1));
				img.setImgPath(rs.getString(2));
				img.setImgName(rs.getString(3));
				img.setImgOriginal(rs.getString(4));
				img.setImgLevel(rs.getInt(5));
				img.setboardNo(boardNo);
				imgList.add(img);
			}
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return imgList;
	}
	/**특정 회원 반려동물 이미지 목록 조회
	 * @param conn
	 * @param boardNo
	 * @return imgList
	 * @throws Exception
	 */
	public List<NboardAnimalImg> selectNboardAnimalImgList(Connection conn, int memberNo) throws Exception{
		/*		SELECT * FROM IMG
		
		 */		
		List<NboardAnimalImg> animalImgList = null;
		
		try {
			String sql = prop.getProperty("selectNboardAnimalImgList");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			rs= pstmt.executeQuery();
			animalImgList = new ArrayList<NboardAnimalImg>();
			while(rs.next()) {
				NboardAnimalImg img = new NboardAnimalImg();
				img.setAnimalNo(rs.getInt(1));
				img.setImgNo(rs.getInt(3));
				img.setImgPath(rs.getString(4));
				img.setImgName(rs.getString(5));
				img.setImgOriginal(rs.getString(6));
				img.setImgLevel(rs.getInt(7));
				img.setMemberNo(memberNo);
				animalImgList.add(img);
			}
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return animalImgList;
	}
	
	/** 좋아요 증가
	 * @param conn
	 * @param memberNo
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public int plusLike(Connection conn, int memberNo, int boardNo) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateLikeCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
	

}
