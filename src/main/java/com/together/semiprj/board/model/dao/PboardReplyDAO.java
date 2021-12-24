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

import com.together.semiprj.board.model.vo.PboardReply;

public class PboardReplyDAO {
	
	private Statement stmt;
	   private PreparedStatement pstmt;
	   private ResultSet rs;
	   
	   private Properties prop;
	   
	   public PboardReplyDAO() {
		   String filePath = PboardReplyDAO.class.getResource("/com/together/semiprj/sql/pboard-reply-query.xml").getPath(); 	      
	      try {
	         prop = new Properties();
	         prop.loadFromXML(new FileInputStream(filePath));
	         
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	   }

	/** 댓글 조회
	 * @param boardNo
	 * @param conn
	 * @return rList
	 * @throws Exception
	 */
	public List<PboardReply> selectReplyList(int boardNo, Connection conn) throws Exception {
		List<PboardReply> rList = new ArrayList<PboardReply>();
		
		try {
			String sql = prop.getProperty("selectReplyList");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PboardReply reply = new PboardReply();
				
				reply.setReplyNo(rs.getInt("REPLY_NO"));
				reply.setReplyContent(rs.getString("REPLY_CONTENT"));
				reply.setReplyCreateDt(rs.getString("REPLY_CREATE_DT"));
				reply.setBoardNo(rs.getInt("BOARD_NO"));
				reply.setMemberNo(rs.getInt("MEMBER_NO"));
				reply.setMemberNm(rs.getString("MEMBER_NM"));
				reply.setStatusCd(rs.getInt("STATUS_CD"));
				reply.setStatusNm(rs.getString("STATUS_NM"));
				
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
	public int insertReply(PboardReply reply, Connection conn) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertReply");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getBoardNo());
			pstmt.setInt(3, reply.getMemberNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 댓글 수정
	 * @param conn
	 * @param replyNo
	 * @param replyContent
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(Connection conn, int replyNo, String replyContent) throws Exception {
		int result = 0;
	      
	      try {
	         String sql = prop.getProperty("updateReply");
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, replyContent);
	         pstmt.setInt(2, replyNo);
	         
	         result = pstmt.executeUpdate();
	         
	      } finally {
	         close(pstmt);
	         
	      }
	      return result;
	   }

	/** 댓글 삭제
	 * @param conn
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(Connection conn, int replyNo) throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("deleteReply");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyNo);
	 
			result = pstmt.executeUpdate();
	     
		} finally {
			close(pstmt);
		}
		return result;
	}

}
