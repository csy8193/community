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
	public List<Pboard> selectBoardList(Pagination pagination, int memberNo) throws Exception {
		Connection conn = getConnection();
		List<Pboard> pboardList = dao.selectBoardList(conn, pagination, memberNo);
		
		for(Pboard temp : pboardList) {
			List<PboardImage> imgList = dao.selectBoardImageList(temp.getBoardNo(), conn);
			
			temp.setPBoardImgList(imgList);
		}
		close(conn);
		
		return pboardList;
	}

}
