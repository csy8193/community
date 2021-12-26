package com.together.semiprj.walk.member.service;
import static com.together.semiprj.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.together.semiprj.board.model.vo.Nboard;
import com.together.semiprj.board.model.vo.NboardImage;
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
		System.out.println(myPointList);
		int myrank = dao.myPointRank(conn,loginMember);
		for(Mypoint dd: myPointList) {
			dd.setMyrank(myrank);
		}
		return myPointList;
	}

	public List<Nboard> walkinsert(int memberNo, String walktext, int continueWalk) throws Exception{
		
		Connection conn = getConnection();
		List<Nboard> walkList = null;
		int result = 0;
		int check = 0;
		//당일 포인트 부여 확인
		check = dao.checkduplPoint(conn,memberNo);
		//0보다 크면 이미 부여했습니다
		
		result = dao.walkinsert(conn,memberNo,walktext);
		if(result>0) {
				//삽입 성공 완료시 포인트 부여 여부
				if(check==0) {
					int getPoint = 20+20*continueWalk;
					result = dao.walkPointGet(conn,memberNo, getPoint);
				}
				if(result>0) {
					commit(conn);
					walkList = dao.selectWalkList(conn,memberNo);
				}
				else rollback(conn);
			}
		else {rollback(conn);}
		
		close(conn);
		
		return walkList;
	}

	public List<Nboard> walkdayshow(int memberNo, int year, int month, int day) throws Exception{
		Connection conn = getConnection();
		
		List<Nboard> myPointList = dao.walkdayshow(conn,memberNo,year,month,day);
		
		close(conn);
		
		return myPointList;
	}

}
