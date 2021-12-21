package com.together.semiprj.board.model.dao;

import static com.together.semiprj.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.together.semiprj.board.model.vo.Board;
import com.together.semiprj.board.model.vo.BoardImage;

public class BoardDAO222 {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public BoardDAO222() {
		String filePath = BoardDAO222.class.getResource("/com/together/semiprj/sql/board-query222.xml").getPath();                    
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 일반게시판 삽입
	 * @param conn
	 * @param boardTitle
	 * @param boardContent
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, String boardTitle, String boardContent, int memberNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		return result;
	}

	/** 다음 게시글 번호 조회
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn) throws Exception{
		
		int boardNo = 0;
		
		try {
			String sql = prop.getProperty("nextBoardNo");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				boardNo = rs.getInt(1);
			}

		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardNo;
	}

	/** 사진게시판 삽입
	 * @param board
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(Board board, Connection conn) throws Exception{
		int result = 0;

		try {
			String sql = prop.getProperty("insertBoard");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board.getBoardNo());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getMemberNo());

			result = pstmt.executeUpdate();
			

		}finally {
			close(pstmt);
		}

		return result;
	}

	/** 게시글 이미지 정보 삽입
	 * @param img
	 * @param conn
	 * @return
	 */
	public int insertBoardImage(BoardImage img, Connection conn) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("insertBoardImage");

			pstmt = conn.prepareStatement(sql);
			

			pstmt.setString(1, img.getImgPath());
			pstmt.setString(2, img.getImgName());
			pstmt.setString(3, img.getImgOriginal());
			pstmt.setInt(4, img.getImgLevel());
			pstmt.setInt(5, img.getBoardNo());

			result = pstmt.executeUpdate();
			

		}finally {
			close(pstmt);
		}


		return result;
	}
}
