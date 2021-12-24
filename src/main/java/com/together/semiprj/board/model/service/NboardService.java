package com.together.semiprj.board.model.service;
import static com.together.semiprj.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.together.semiprj.board.model.dao.NboardDAO;
import com.together.semiprj.board.model.vo.Nboard;
import com.together.semiprj.board.model.vo.NboardAnimalImg;
import com.together.semiprj.board.model.vo.NboardImage;
import com.together.semiprj.board.model.vo.Pagination;

public class NboardService {
	NboardDAO dao = new NboardDAO();
	/**전체 게시글 수 조회
	 * @param cp
	 * @param memberNo 
	 * @param i 
	 * @return 게시글 수 대입한 페이지네이션 객체
	 * @throws Exception
	 */
	public Pagination getPagination(int cp, int boardCate) throws Exception{
		Connection conn  = getConnection();
		
		int listCount = dao.getListCount(conn, boardCate);
		//전체 게시글 수 조회 DAO
		
		close(conn);
		
		return new Pagination(listCount, cp);
	}
	/**해당되는 게시글 리스트 반환
	 * @param pagination
	 * @param boardCd 
	 * @return nboardList
	 * @throws Exception
	 */
	public List<Nboard> selectBoardList(Pagination pagination, int memberNo, int boardCd) throws Exception{
		Connection conn = getConnection();
		List<Nboard> nboardList = dao.selectBoardList(conn, pagination, memberNo, boardCd);
		
		for(Nboard aa : nboardList) {
			System.out.println(aa);
		}
		close(conn);
		return nboardList;
	}
	/**해당 게시글 반환 + 조회수 증가
	 * @param boardNo
	 * @param memberNo 
	 * @return nboard
	 * @throws Exception
	 */
	public Nboard selectBoardView(int boardNo, int memberNo) throws Exception{
		Connection conn = getConnection();
		Nboard nboard = dao.selectBoardView(conn, boardNo,memberNo);
		int result = 0 ;
		
		if(memberNo != nboard.getMemberNo()) {
			result = dao.readCountPlus(conn,boardNo);
			
			if(result>0) {
				nboard.setReadCount(nboard.getReadCount()+1);
				commit(conn);
			}
			else rollback(conn);
		}
		List<NboardImage> NboardImgList = dao.selectNboardImgList(conn,boardNo);
		if(NboardImgList!=null) {
			nboard.setnBoardImgList(NboardImgList);
		}
		
		close(conn);
		return nboard;
	}
	/** 좋아요 증가
	 * @param memberNo
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int plusLike(int memberNo, int boardNo) throws Exception{
		int result=0;
		
		Connection conn= getConnection();
		result = dao.plusLike(conn, memberNo,boardNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	public int duplLikeCheck(int memberNo, int boardNo) throws Exception{
		int result=0;
		
		Connection conn= getConnection();
		result = dao.duplLikeCheck(conn, memberNo,boardNo);
		
		close(conn);
		return result;
	}

}
