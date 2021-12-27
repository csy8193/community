package com.together.semiprj.board.model.service;
import static com.together.semiprj.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.together.semiprj.board.model.dao.PboardDAO;
import com.together.semiprj.board.model.vo.Nboard;
import com.together.semiprj.board.model.vo.Pagination;
import com.together.semiprj.board.model.vo.Pboard;
import com.together.semiprj.board.model.vo.PboardImage;

public class PboardService {
	PboardDAO dao = new PboardDAO();
	
	
	/** 사진 게시글 전체 게시글 수 조회
	 * @param cp
	 * @param boardCate
	 * @return 
	 * @throws Exception
	 */
	public Pagination getPagination(int cp, int boardCate) throws Exception{
		
		Connection conn = getConnection();
		int listCount = dao.getListCount(conn, boardCate);
		
		close(conn);
		
		return new Pagination(listCount, cp);
	}


	/** 사진 게시글 목록 조회
	 * @param pagination
	 * @param memberNo
	 * @return boardList
	 * @throws Exception
	 */
	public List<Pboard> selectBoardList(Pagination pagination, int memberNo, int boardCd) throws Exception {
		Connection conn = getConnection();
		List<Pboard> pboardList = dao.selectBoardList(conn, pagination, memberNo, boardCd);
		for(Pboard temp : pboardList) {
			List<PboardImage> imgList = dao.selectBoardImageList(temp.getBoardNo(), conn);
			
			temp.setPBoardImgList(imgList);
		}
		close(conn);
		
		return pboardList;
	}


	/** 게시글 상세 조회
	 * @param boardNo
	 * @param memberNo
	 * @return board(없으면 null)
	 * @throws Exception
	 */
	public Pboard selectBoard(int boardNo, int memberNo) throws Exception{

		Connection conn = getConnection();
		
		Pboard board = dao.selectBoard(boardNo, conn, memberNo);
		
		List<PboardImage> imgList = dao.selectBoardImageList(boardNo, conn);
		
		board.setPBoardImgList(imgList);
		
		if(board != null && board.getMemberNo() != memberNo) {
			int result = dao.increaseReadCount(boardNo, conn);
			
			if(result > 0) {
				commit(conn);
				
				board.setReadCount(board.getReadCount() + 1);
			}else rollback(conn);
		}
		
		close(conn);
		
		return board;
	}


	/** board 이름 가져오기
	 * @param boardCd
	 * @return result(1 성공)
	 * @throws Exception
	 */
	public String boardNmCh(int boardCd) throws Exception {

		Connection conn = getConnection();
		
		String boardName = dao.boardNmCh(conn, boardCd);
		
		close(conn);
		
		return boardName;
	}


	/** 좋아요 증가
	 * @param memberNo
	 * @param boardNo
	 * @return result(1 성공, 0 실패)
	 * @throws Exception
	 */
	public int plusLike(int memberNo, int boardNo) throws Exception{
		
		int result = 0;
		Connection conn = getConnection();
		result = dao.plusLike(conn, memberNo, boardNo);
		
		if(result > 0) {
			commit(conn);
			
			result = dao.likeSelect(boardNo,conn);
		}
		else rollback(conn);
		
		
		close(conn);
		return result;
	}


	/** 좋아요 삭제
	 * @param memberNo
	 * @param boardNo
	 * @return result(1 성공, 0 실패)
	 * @throws Exception
	 */
	public int unLike(int memberNo, int boardNo) throws Exception {
		int result = 0;
		Connection conn = getConnection();
		result = dao.unLike(conn, memberNo, boardNo);
		
		if(result > 0) {
			commit(conn);
			
			result = dao.likeSelect(boardNo, conn);
		}
		else rollback(conn);
		
		
		close(conn);
		return result;
	}

}
