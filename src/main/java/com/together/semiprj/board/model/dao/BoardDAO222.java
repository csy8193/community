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
import com.together.semiprj.board.model.vo.BoardImage;
import com.together.semiprj.board.model.vo.Pagination;

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
			String sql = prop.getProperty("picInsertBoard");

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

	public int getListCount(Connection conn, int cd) throws Exception{
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("getListCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
				
			}
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		return listCount;
	}

	/** 게시글 목록 조회
	 * @param pagination
	 * @param conn
	 * @param cd 
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Pagination pagination, Connection conn, int cd) throws Exception{
List<Board> boardList = new ArrayList<Board>();
		
		try {
			String sql = prop.getProperty("selectBoardList");
			
			// 조회 하려는 범위의 시작/끝 행 계산
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int endRow = startRow + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cd);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				// 게시글 번호, 제목, 작성자명, 조회수, 카테고리명, 작성일, 게시글 상태명
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberName(rs.getString("MEMBER_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setBoardName(rs.getString("BOARD_NAME"));
				board.setStatusName(rs.getString("STATUS_NM"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				
				boardList.add(board);
			}
			
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		return boardList;
	}

	/** 일반게시판 이미지 경로 삽입
	 * @param conn
	 * @param picPath
	 * @return
	 * @throws Exception
	 */
	public int insertBoardImage(Connection conn, String picPath) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertImage");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, picPath);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
			
		}
		return result;
	}
}
