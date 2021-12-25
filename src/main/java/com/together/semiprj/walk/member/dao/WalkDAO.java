package com.together.semiprj.walk.member.dao;
import static com.together.semiprj.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.together.semiprj.board.model.dao.NboardReplyDAO;
import com.together.semiprj.board.model.vo.Nboard;
import com.together.semiprj.walk.member.vo.Mypoint;
import com.together.semiprj.walk.member.vo.WalkRank;

public class WalkDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public WalkDAO() {
		String filePath = NboardReplyDAO.class.getResource("/com/together/semiprj/sql/walk-rank-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<WalkRank> pointRank(Connection conn) throws Exception{
		List<WalkRank> rankList= null;
		WalkRank rank = null;
		try {
			String sql = prop.getProperty("pointRank");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			rankList = new ArrayList<WalkRank>();
			
			while(rs.next()) {
				rank = new WalkRank();
				rank.setRank(rs.getInt("ROWNUM"));
				rank.setMemberNo(rs.getInt("MEMBER_NO"));
				rank.setTotalP(rs.getInt("TOTALP"));
				rank.setAnimalName(rs.getString("ANIMAL_NAME"));
				rank.setAnimalVariety(rs.getString("ANIMAL_VARIETY"));
				rank.setAnimalGender(rs.getString("ANIMAL_SEX"));
				rank.setAnimalBirth(rs.getString("ANIMAL_BIRTH"));
				rank.setAnimalCategoryNm(rs.getString("ANIMAL_CATEGORY_NM"));
				rank.setAnimalImg(rs.getString("ANIMAL_PROFILE_IMG"));
				rankList.add(rank);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return rankList;
	}
	/** 한달간의 산책기록 가져오기
	 * @param conn
	 * @param memberNo
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Integer> getWalkHistory(Connection conn, int memberNo, int start, int end) throws Exception{
		List<Integer> history = null;
		try {
			String sql = prop.getProperty("getWalkHistory");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			history= new ArrayList<Integer>();
			
			while(rs.next()) {
				if(rs.getInt("COUNT")>0) history.add(rs.getInt("DAY"));
			}
		}
		finally {
			close(rs);
			close(stmt);
		}
		
		return history;
	}
	public List<Mypoint> myPoint(Connection conn, int loginMember) throws Exception{
		List<Mypoint> myPointList= null;
		Mypoint mypoint = null;
		try {
			String sql = prop.getProperty("myPointAndRank");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember);
			rs = pstmt.executeQuery();
			myPointList = new ArrayList<Mypoint>();
			while(rs.next()) {
				mypoint = new Mypoint();
				mypoint.setMemberNo(rs.getInt("MEMBER_NO"));
				mypoint.setTotalp(rs.getInt("TOTALP"));
				mypoint.setAnimalName(rs.getString("ANIMAL_NAME"));
				myPointList.add(mypoint);
			}
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return myPointList;
	}
	/** 이번달 포인트 순위
	 * @param conn
	 * @param loginMember
	 * @return
	 */
	public int myPointRank(Connection conn, int loginMember) throws Exception{
		int result =0;
		try {
			String sql = prop.getProperty("mymonthrank");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("MYRANK");
			}
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	public int walkinsert(Connection conn, int memberNo, String walktext) throws Exception {
		
		int result = 0 ;
		try {
			String sql = prop.getProperty("walkinsert");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, walktext);
			pstmt.setInt(2, memberNo);
			result = pstmt.executeUpdate();
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return result;
	}
	public List<Nboard> selectWalkList(Connection conn, int memberNo) throws Exception{
		List<Nboard> walkList = null;
		try {
			String sql = prop.getProperty("selectWalkList");
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rs= pstmt.executeQuery();
			
			walkList = new ArrayList<Nboard>();
			
			while(rs.next()) {
				Nboard nboard = new Nboard();
				nboard.setBoardNo(rs.getInt("BOARD_NO"));
				nboard.setBoardContent(rs.getString("BOARD_CONTENT"));
				nboard.setCreateDt(rs.getString("CREATE_DT"));
				walkList.add(nboard);
			}
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return walkList;
	}
	public int walkPointGet(Connection conn, int memberNo, int getPoint) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("walkPointGet");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getPoint);
			pstmt.setInt(2, memberNo);
			result = pstmt.executeUpdate();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public int checkduplPoint(Connection conn, int memberNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("duplpointcheck");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("POINTDUPL");
				System.out.println(result+"왜 1나옴?");
			}
		}
		finally {
			close(pstmt);
		}
		return result;
		
		
	}
}
