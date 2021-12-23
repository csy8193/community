package com.together.semiprj.main.mainservice;
import static com.together.semiprj.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.together.semiprj.main.maindao.MainPageDAO;
import com.together.semiprj.main.vo.BoardInfo;
import com.together.semiprj.main.vo.PersonAnimalInfo;

public class Mainservice {
	MainPageDAO dao = new MainPageDAO();

	/**게시판마다 조회수 1등
	 * @return boardList
	 * @throws Exception
	 */
	public List<BoardInfo> selectMostView() throws Exception{
		Connection conn = getConnection();
		
		List<BoardInfo> boardList = dao.selectMostView(conn);
		close(conn);
		
		
		return boardList;
	}

	/** 실시간 게시판 조회하기
	 * @param countNum 
	 * @param boardCode(게시판 코드), countNum(반환게시판갯수)
	 * @return boardList
	 * @throws Exception
	 */
	public List<BoardInfo> selectFreqB(int boardCode, int countNum) throws Exception {
		Connection conn = getConnection();
		
		List<BoardInfo> boardList = dao.selectFreqB(conn,boardCode,countNum);
		close(conn);
		
		return boardList;
	}
	
	/**자랑하기 게시팝 like 탑3
	 * @return boardList
	 * @throws Exception
	 */
	public List<PersonAnimalInfo> selectTopLikePeople() throws Exception {
		Connection conn = getConnection();
		
		List<PersonAnimalInfo> boardList = dao.selectTopLikePeople(conn);
		close(conn);
		
		return boardList;
	}

	
	
}
