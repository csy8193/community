package com.together.semiprj.main.maindao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.together.semiprj.common.JDBCTemplate.*;
import com.together.semiprj.board.model.dao.NboardDAO;
import com.together.semiprj.main.vo.BoardInfo;
import com.together.semiprj.main.vo.PersonAnimalInfo;

public class MainPageDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public MainPageDAO() {
		String filePath = NboardDAO.class.getResource("/com/together/semiprj/sql/main-page.xml").getPath();                    
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**게시판마다 조회수 1등
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<BoardInfo> selectMostView(Connection conn) throws Exception{
		List<BoardInfo> boardList = null;
		BoardInfo board = null;
		try {
			String sql = prop.getProperty("selectMaxView");
			stmt = conn.createStatement();
			rs= stmt.executeQuery(sql);
			boardList = new ArrayList<BoardInfo>();
			
			int count = 0;
			while(rs.next()) {
				board = new BoardInfo();
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardName(rs.getString("BOARD_NAME"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCreateDate(rs.getString("CREATE"));
				board.setBoardCode(rs.getInt("BOARD_CD"));
				boardList.add(board);
			}
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}
	
	/** 실시간 게시판 최신 조회
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<BoardInfo> selectFreqB(Connection conn,int boardCode, int countNum) throws Exception{
		List<BoardInfo> boardList = null;
		BoardInfo board = null;
		try {
			String sql = prop.getProperty("selectFreqB");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardCode);
			pstmt.setInt(2, countNum);
			rs= pstmt.executeQuery();
			boardList = new ArrayList<BoardInfo>();
			
			while(rs.next()) {
				board = new BoardInfo();
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardName(rs.getString("BOARD_NAME"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCreateDate(rs.getString("NEWCREATE"));
				boardList.add(board);
			}
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	/** 자랑하기 탑3
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<PersonAnimalInfo> selectTopLikePeople(Connection conn) throws Exception{
		List<PersonAnimalInfo> boardList = null;
		PersonAnimalInfo board = null;
		try {
			String sql = prop.getProperty("selectTopLikePeople");
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			boardList = new ArrayList<PersonAnimalInfo>();
			
			while(rs.next()) {
				board = new PersonAnimalInfo();
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setRanking(rs.getInt("ROWNUM"));
				board.setLikeCount(rs.getInt("LIKECOUNT"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				board.setAnimalNo(rs.getInt("ANIMAL_NO"));
				board.setAnimalName(rs.getString("ANIMAL_NAME"));
				board.setAnimalVariety(rs.getString("ANIMAL_VARIETY"));
				board.setAnimalGender(rs.getString("ANIMAL_SEX"));
				board.setAnimalBirth(rs.getDate("ANIMAL_BIRTH"));
				board.setAnimalImg(rs.getString("ANIMALIMG"));
				boardList.add(board);
			}
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}
	

	
}
