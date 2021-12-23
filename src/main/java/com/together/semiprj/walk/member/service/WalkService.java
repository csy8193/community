package com.together.semiprj.walk.member.service;
import static com.together.semiprj.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.together.semiprj.walk.member.dao.WalkDAO;
import com.together.semiprj.walk.member.vo.WalkRank;

public class WalkService {
	
	WalkDAO dao = new WalkDAO();
	
	
	/** 랭크 반환
	 * @return
	 * @throws Exception
	 */
	public List<WalkRank> pointRank() throws Exception{
		Connection conn = getConnection();
		
		List<WalkRank> walkrank = dao.pointRank(conn);
		
		close(conn);
		
		return walkrank;
	}

	public List<Integer> getWalkHistory(int memberNo, int start, int end) throws Exception {
		Connection conn = getConnection();
		
		List<Integer> history = dao.getWalkHistory(conn,memberNo,start,end);
		
		close(conn);
		
		return history;
	}

}
