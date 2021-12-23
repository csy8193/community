package com.together.semiprj.board.model.service;
import static com.together.semiprj.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.together.semiprj.board.model.dao.NboardReplyDAO;
import com.together.semiprj.board.model.vo.Nboard;
import com.together.semiprj.board.model.vo.NboardAnimalImg;
import com.together.semiprj.board.model.vo.NboardReply;

public class NboardReplyService {
	NboardReplyDAO dao = new NboardReplyDAO();
	/**댓글 조회
	 * @param boardNo
	 * @return
	 * @throws Exception 
	 */
	public List<NboardReply> selectReplyList(int boardNo) throws Exception {
		Connection conn  = getConnection();
		
		List<NboardReply> rList = dao.selectReplyList(boardNo, conn);
		//댓글 조회시 댓글 번호에 해당하는 작성자의 프로필 반려동물 이미지를 가져온다.
		
		//List<NboardAnimalImg> animalImgList = dao.selectReplyProfileList(conn,memberNo);
		
		return rList;
	}
	/**댓글 삽입
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(NboardReply reply) throws Exception{
		Connection conn = getConnection();
		
		// XSS 방지처리
		reply.setReplyContent(replaceParameter(reply.getReplyContent()));
		//개행 문자 처리
		reply.setReplyContent(replaceEnter(reply.getReplyContent()));
		
		int result = dao.insertReply(reply,conn);
		
		if(result >0 ) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	/** 댓글 상태 삭제로 변경
	 * @param delReplyNo
	 * @return
	 * @throws Exception
	 */
	public int deleteReply(int delReplyNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteReply(delReplyNo,conn);
		
		if(result >0 ) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	
	private String replaceParameter(String parameter) {
		if(parameter != null) {
			parameter = parameter.replaceAll("&","&amp;");
			parameter = parameter.replaceAll("<","&lt;");
			parameter = parameter.replaceAll(">","&gt;");
			parameter = parameter.replaceAll("\"","&quot;");
		}
		return parameter;
	}
	private String replaceEnter(String parameter) {
		if(parameter != null) {
//			parameter = parameter.replaceAll("정규표현식","&amp;");
			parameter = parameter.replaceAll("(\r\n|\r|\n|\n\r)","<br>");
		}
		return parameter;
	}
	public int updateReply(int replyNo, String contents) throws Exception{
		
		Connection conn = getConnection();
		
		int result = 0;
		
		result = dao.updateReply(conn,replyNo,contents );
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		
		return result;
	}

}
