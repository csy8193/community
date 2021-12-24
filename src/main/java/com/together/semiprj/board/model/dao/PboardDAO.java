package com.together.semiprj.board.model.dao;

import static com.together.semiprj.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.together.semiprj.board.model.vo.Board;
import com.together.semiprj.board.model.vo.Pagination;
import com.together.semiprj.board.model.vo.Pboard;
import com.together.semiprj.board.model.vo.PboardImage;

public class PboardDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public PboardDAO() {
		String filePath = PboardDAO.class.getResource("/com/together/semiprj/sql/pboard-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 사진 게시글 전체 게시글 수
	 * @param conn
	 * @param boardCate
	 * @return listCount(게시글 갯수)
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int boardCate) throws Exception{
		int listCount = 0;
		try {
			String sql = prop.getProperty("count-all-pboard-list");
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


	/** 페이지네이션 해당 게시글 모두 반환
	 * @param conn
	 * @param pagination
	 * @param memberNo
	 * @return pboardList
	 * @throws Exception
	 */
	public List<Pboard> selectBoardList(Connection conn, Pagination pagination, int memberNo, int boardCd) throws Exception {

		List<Pboard> pboardList = new ArrayList<Pboard>();
		
		try {
			String sql = prop.getProperty("selectPboardList");
//			
			int startRow = ( pagination.getCurrentPage() -1)* pagination.getLimit() +1 ;
			int endRow = startRow + pagination.getLimit() -1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardCd);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Pboard board = new Pboard();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setBoardCd(rs.getInt("BOARD_CD"));
				board.setCreateDt(rs.getString("CREATE_DT"));
				board.setStatusCd(rs.getInt("STATUS_CD"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				board.setBoardName(rs.getString("BOARD_NAME"));
				board.setMemberId(rs.getString("MEMBER_ID"));
				board.setLikecount(rs.getInt("LIKECOUNT"));
				board.setReplycount(rs.getInt("REPLYCOUNT"));
				board.setLikeDone(rs.getBoolean("LIKEDONE"));
				board.setMemberNm(rs.getString("MEMBER_NM"));
				board.setAnimalMainImgPath(rs.getNString("ANIMAL_PROFILE_IMG"));
				
				pboardList.add(board);
			}
		}finally {
			
			close(rs);
			close(pstmt);
		}
		return pboardList;
	}


	/** 특정 게시글 이미지 목록 조회
	 * @param boardNo
	 * @param conn
	 * @return imgList
	 * @throws Exception
	 */
	public List<PboardImage> selectBoardImageList(int boardNo, Connection conn) throws Exception{

		List<PboardImage> imgList = new ArrayList<PboardImage>();
		
		try {
			String sql = prop.getProperty("selectPboardImageList");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PboardImage img = new PboardImage();
				
				img.setImgNo(rs.getInt(1));
				img.setImgPath(rs.getString(2));
				img.setImgName(rs.getString(3));
				img.setImgOriginal(rs.getString(4));
				img.setImgLevel(rs.getInt(5));
				img.setBoardNo(boardNo);
				
				imgList.add(img);
				
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return imgList;
	}


	/** 게시글 상세 조회
	 * @param boardNo
	 * @param conn
	 * @return board(없으면 null)
	 * @throws Exception
	 */
	public Pboard selectBoard(int boardNo, Connection conn, int memberNo) throws Exception {

		Pboard board = null;
		
		try {
			String sql = prop.getProperty("selectPboard");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2,boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Pboard();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				board.setMemberNm(rs.getString("MEMBER_NM"));
				board.setCreateDt(rs.getString("CREATE_DT"));
				board.setStatusCd(rs.getInt("STATUS_CD"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setBoardCd(rs.getInt("BOARD_CD"));
				board.setBoardName(rs.getString("BOARD_NAME"));
				board.setLikecount(rs.getInt("LIKECOUNT"));
				board.setLikeDone(rs.getBoolean("LIKEDONE"));
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}


	/** 조회수 증가
	 * @param boardNo
	 * @param conn
	 * @return result(1 성공, 0 실패)
	 * @throws Exception
	 */
	public int increaseReadCount(int boardNo, Connection conn) throws Exception{

		int result = 0;
		
		try {
			String sql = prop.getProperty("increaseReadCount");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	/** board 이름 가져오기
	 * @param conn
	 * @param boardCd
	 * @return result
	 * @throws Exception
	 */
	public String boardNmCh(Connection conn, int boardCd) throws Exception {
		
		String boardNm = null;
		
		try {
			String sql = prop.getProperty("boardNmCh");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardCd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardNm = rs.getString(1);
			}
		}finally {
			close(pstmt);
		}
		
		return boardNm;
	}


	/** 좋아요 증가
	 * @param conn
	 * @param memberNo
	 * @param boardNo
	 * @return result(1 성공, 0 실패)
	 * @throws Exception
	 */
	public int plusLike(Connection conn, int memberNo, int boardNo) throws Exception {
		
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


	/** 좋아요 삭제
	 * @param conn
	 * @param memberNo
	 * @param boardNo
	 * @return result(1 성공, 0 실패)
	 * @throws Exception
	 */
	public int unLike(Connection conn, int memberNo, int boardNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("unLike");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}


	/** 좋아요 조회
	 * @param boardNo
	 * @param conn
	 * @return result(1 성공, 0 실패)
	 * @throws Exception
	 */
	public int likeSelect(int boardNo, Connection conn) throws Exception{

		int result = 0;
		
		try {
			String sql = prop.getProperty("likeselect");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

}
