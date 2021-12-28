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
	 * @param boardNo 
	 * @param boardTitle
	 * @param boardContent
	 * @param memberNo
	 * @param boardCd 
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, int boardNo, String boardTitle, String boardContent, int memberNo, int boardCd) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, boardTitle);
			pstmt.setString(3, boardContent);
			pstmt.setInt(4, boardCd);
			pstmt.setInt(5, memberNo);
			
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
	 * @param boardCd 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(Board board, int boardCd, Connection conn) throws Exception{
		int result = 0;

		try {
			String sql = prop.getProperty("picInsertBoard");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board.getBoardNo());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, boardCd);
			pstmt.setInt(4, board.getMemberNo());

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

	public int getListCount(Connection conn, int bc) throws Exception{
		int listCount = 0;
		
		try {
			String sql = prop.getProperty("getListCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bc);
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
				board.setBoardPicPath(rs.getString("BOARD_PIC_PATH"));
				
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
	 * @param boardNo 
	 * @return
	 * @throws Exception
	 */
	public int insertBoardImage(Connection conn, String picPath, int boardNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertImage");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, picPath);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
			
		}
		return result;
	}

	/** 게시판 이름 조회
	 * @param conn 
	 * @param boardCd
	 * @return
	 * @throws Exception
	 */
	public String selectBoardName(Connection conn, int boardCd) throws Exception{
		String boardName = null;
		
		try {
			String sql = prop.getProperty("selectBoardName");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardCd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardName = rs.getString(1);
			}
		} finally {
			close(pstmt);
		}
		
		return boardName;
	}

	/** 일반게시글 수정하기 전 데이터 조회
	 * @param conn
	 * @param boardCd
	 * @param boardNo
	 * @param contextPath 
	 * @return
	 * @throws Exception
	 */
	public Board selectBoardUpdate(Connection conn, int boardCd, int boardNo, String contextPath) throws Exception{
		Board board = null;
		
		try {
			String sql = prop.getProperty("selectBoardUpdate");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, boardCd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT").replaceAll("/resources/", contextPath+"/resources/"));
				board.setBoardCode(rs.getInt("BOARD_CD"));
				board.setBoardPicPath(rs.getString("BOARD_PIC_PATH"));
				board.setBoardName(rs.getString("BOARD_NAME"));
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		return board;
	}

	/** 일반게시판 글쓰기 수정
	 * @param conn
	 * @param boardTitle
	 * @param boardContent
	 * @param memberNo
	 * @param boardCd
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, String boardTitle, String boardContent, int memberNo,
			int boardCd, int boardNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoard");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, boardNo);
			pstmt.setInt(4, boardCd);
			pstmt.setInt(5, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 일반 게시판 이미지 수정
	 * @param conn
	 * @param boardCd
	 * @param picPath
	 * @return
	 * @throws Exception
	 */
	public int updateImage(Connection conn, int boardNo, String picPath) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateImage");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, picPath);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}

	/** 사진 게시판 수정 전 데이터 조회
	 * @param conn
	 * @param boardNo
	 * @param boardCd
	 * @return
	 * @throws Exception
	 */
	public Board selectPboardUpdate(Connection conn, int boardNo, int boardCd) throws Exception{
		Board board = null;
		
		try {
			String sql = prop.getProperty("selectPboardUpdate");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, boardCd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardCode(rs.getInt("BOARD_CD"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setBoardName(rs.getString("BOARD_NAME"));
			}
			
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		return board;
	}

	/** 사진 게시판 수정 전 이미지 조회
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public List<BoardImage> selectBoardImage(Connection conn, int boardNo) throws Exception{
		List<BoardImage> boardImageList = new ArrayList<BoardImage>();
		
		try {
			String sql = prop.getProperty("selectBoardImage");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardImage boardImage = new BoardImage();
				
				boardImage.setImgPath(rs.getString("BOARD_PIC_PATH"));
				boardImage.setImgName(rs.getString("BOARD_PIC_NM"));
				boardImage.setImgLevel(rs.getInt("BOARD_PIC_LEVEL"));
				
				boardImageList.add(boardImage);
			}
			
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		return boardImageList;
	}

	/** 사진 게시판 내용 수정
	 * @param conn
	 * @param board
	 * @return
	 * @throws Exception
	 */
	public int updatePboardContent(Connection conn, Board board) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePboardContent");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoardContent());
			pstmt.setInt(2, board.getMemberNo());
			pstmt.setInt(3, board.getBoardCode());
			pstmt.setInt(4, board.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}

	/** 사진 게시판 수정 전 이미지 삭제
	 * @param conn
	 * @param board
	 * @return
	 * @throws Exception
	 */
	public int deleteImage(Connection conn, Board board) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteImage");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}

	/** 일반게시글 삭제
	 * @param conn 
	 * @param boardCd
	 * @param boardNo
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int nBoardDelete(Connection conn, int boardCd, int boardNo, int memberNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("nBoardDelete");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}

	/** 검색 페이지네이션
	 * @param conn
	 * @param search
	 * @return
	 */
	public int getListCount2(Connection conn, String search) throws Exception{
		int listCount = 0;
		String search2 = "%" + search + "%";
		
		try {
			String sql = prop.getProperty("getListCount2");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search2);
			pstmt.setString(2, search2);
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

	/** 검색 목록 조회
	 * @param conn
	 * @param pagination
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public List<Board> searchBoard(Connection conn, Pagination pagination, String search) throws Exception{
		List<Board> boardList = new ArrayList<Board>();
		String search2 = "%" + search + "%";
		
		try {
			String sql = prop.getProperty("searchBoard");
			
			// 조회 하려는 범위의 시작/끝 행 계산
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int endRow = startRow + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search2);
			pstmt.setString(2, search2);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				// 게시글 번호, 제목, 작성자명, 조회수, 카테고리명, 작성일, 게시글 상태명
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberName(rs.getString("MEMBER_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setBoardName(rs.getString("BOARD_NAME"));
				board.setStatusName(rs.getString("STATUS_NM"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setBoardPicPath(rs.getString("BOARD_PIC_PATH"));
				board.setBoardPicName(rs.getString("BOARD_PIC_NM"));
				board.setAnimalProfile(rs.getString("ANIMAL_PROFILE_IMG"));
				board.setBoardCode(rs.getInt("BOARD_CD"));
				
				boardList.add(board);
			}
			
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		return boardList;
	}

}
