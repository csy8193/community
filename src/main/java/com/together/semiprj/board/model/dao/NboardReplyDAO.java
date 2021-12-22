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

import com.together.semiprj.board.model.vo.NboardReply;

public class NboardReplyDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public NboardReplyDAO() {
		String filePath = NboardReplyDAO.class.getResource("/com/together/semiprj/sql/nboard-reply-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**댓글 조회 
	 * @param boardNo
	 * @param conn
	 * @return
	 */
	public List<NboardReply> selectReplyList(int boardNo, Connection conn) throws Exception{
		List<NboardReply> rList = null;
		
		try {
			String sql = prop.getProperty("selectReplyList");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			rList = new ArrayList<NboardReply>();
			while(rs.next()) {
				NboardReply reply = new NboardReply();
				
				reply.setReplyNo(rs.getInt("REPLY_NO"));
				reply.setReplyContent(rs.getString("REPLY_CONTENT"));
				reply.setReplyCreateDate(rs.getString("REPLY_CREATE_DT"));
				reply.setBoardNo(rs.getInt("BOARD_NO"));
				reply.setMemberNo(rs.getInt("MEMBER_NO"));
				reply.setMemberId(rs.getString("MEMBER_ID"));
				reply.setStatusCode(rs.getInt("STATUS_CD"));
				reply.setStatusNm(rs.getString("STATUS_NM"));
				reply.setAnimalImgPath(rs.getString("ANIMAL_IMG_PATH"));
				reply.setAnimalImgNm(rs.getString("ANIMAL_IMG_NM"));
				reply.setFeedbackReplyNo(rs.getInt("FEEDBACK"));
				if(rs.getInt("PROFILEEXIST")==1) {reply.setProfileExist(true);}
				else {reply.setProfileExist(false);}
				rList.add(reply);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return rList;
	}
	/** 댓글 삽입
	 * @param reply
	 * @param conn
	 * @return result 
	 * @throws Exception
	 */
	public int insertReply(NboardReply reply, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertReply");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getBoardNo());
			pstmt.setInt(3, reply.getMemberNo());
			if(reply.getFeedbackReplyNo()==0) {pstmt.setString(4, null);}
			else {pstmt.setInt(4, reply.getFeedbackReplyNo());}
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	/** 댓글 상태 삭제로 변경
	 * @param delReplyNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int deleteReply(int delReplyNo, Connection conn) throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("deleteReply");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, delReplyNo);
			result = pstmt.executeUpdate();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
}
