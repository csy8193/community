package com.together.semiprj.board.model.service;

import java.sql.Connection;
import java.util.List;
import static com.together.semiprj.common.JDBCTemplate.*;

import com.together.semiprj.board.model.dao.PboardReplyDAO;
import com.together.semiprj.board.model.vo.PboardReply;
import com.together.semiprj.common.XSS;

public class PboardReplyService {
	
	private PboardReplyDAO dao = new PboardReplyDAO();

	/** 댓글 조회
	 * @param boardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<PboardReply> selectReplyList(int boardNo)  throws Exception{
		
		Connection conn = getConnection();
		
		List<PboardReply> rList = dao.selectReplyList(boardNo, conn);
		
		close(conn);
		
		return rList;
	}

	/** 댓글 삽입
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(PboardReply reply) throws Exception {
		Connection conn = getConnection();
		
		reply.setReplyContent(XSS.replaceParameter(reply.getReplyContent()));
		reply.setReplyContent(reply.getReplyContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
		int result = dao.insertReply(reply, conn);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 댓글 수정
	 * @param replyNo
	 * @param replyContent
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(int replyNo, String replyContent) throws Exception{
		Connection conn = getConnection();
		
		replyContent = XSS.replaceParameter(replyContent);
	    replyContent = replyContent.replace("(\r\n|\r|\n|\n\r)", "<br>");
	    
	    int result = dao.updateReply(conn, replyNo, replyContent);

	    if(result > 0) commit(conn);
	    else rollback(conn);
      
	    return result;
	}

	/** 댓글 삭제
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(int replyNo) throws Exception {
		Connection conn = getConnection();
		 
		int result = dao.deleteReply(conn, replyNo);
		  
		if(result > 0) commit(conn);
		else rollback(conn);
		  
		return result;
	}

}
