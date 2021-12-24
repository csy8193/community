package com.together.semiprj.walk.member.service;
import static com.together.semiprj.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.together.semiprj.walk.member.dao.WalkDAO;
import com.together.semiprj.walk.member.vo.Mypoint;
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

	/**내 포인트 랭킹
	 * @param loginMember
	 * @return
	 * @throws Exception
	 */
	public List<Mypoint> myPoint(int loginMember)  throws Exception{
		Connection conn = getConnection();
		
		List<Mypoint> myPointList = dao.myPoint(conn,loginMember);
		int myrank = dao.myPointRank(conn,loginMember);
		for(Mypoint dd: myPointList) {
			dd.setMyrank(myrank);
		}
		return myPointList;
	}

	public int walkinsert(int memberNo, String walktext) throws Exception{
		Connection conn = getConnection();
		int result = 0;
		
		result = dao.walkinsert(conn,memberNo,walktext);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

}
