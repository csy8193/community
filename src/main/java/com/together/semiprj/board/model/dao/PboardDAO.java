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

import com.together.semiprj.board.model.vo.Pagination;
import com.together.semiprj.board.model.vo.Pboard;
import com.together.semiprj.board.model.vo.PboardImage;

public class PboardDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public PboardDAO() {
		String filePath = NboardDAO.class.getResource("/com/together/semiprj/sql/pboard-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 사진 게시글 전체 게시글 수
	 * @param conn
	 * @param boardCate
	 * @return listCount(게시글 갯수)
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int boardCate) throws Exception{
		int listCount = 0;
		try {
			String sql = prop.getProperty("count-all-pboard-list");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardCate);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}


	/** 페이지네이션 해당 게시글 모두 반환
	 * @param conn
	 * @param pagination
	 * @param memberNo
	 * @return pboardList
	 * @throws Exception
	 */
	public List<Pboard> selectBoardList(Connection conn, Pagination pagination, int memberNo) throws Exception {

		List<Pboard> pboardList = new ArrayList<Pboard>();
		
		try {
			String sql = prop.getProperty("selectPboardList");
//			
			int startRow = ( pagination.getCurrentPage() -1)* pagination.getLimit() +1 ;
			int endRow = startRow + pagination.getLimit() -1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Pboard board = new Pboard();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNm(rs.getString("MEMBER_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setBoardName(rs.getString("BOARD_NAME"));
				board.setStatusNm(rs.getString("STATUS_NM"));
				board.setCreateDt(rs.getString("CREATE_DT"));
				board.setReplycount(rs.getInt("REPLY_COUNT"));
				board.setLikecount(rs.getInt("LIKE_COUNT"));
				
				pboardList.add(board);
			}
		}finally {
			
			close(rs);
			close(pstmt);
		}
		return pboardList;
	}


	/** 특정 게시글 이미지 목록 조회
	 * @param boardNo
	 * @param conn
	 * @return imgList
	 * @throws Exception
	 */
	public List<PboardImage> selectBoardImageList(int boardNo, Connection conn) throws Exception{

		List<PboardImage> imgList = new ArrayList<PboardImage>();
		
		try {
			String sql = prop.getProperty("selectPboardImageList");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PboardImage img = new PboardImage();
				
				img.setImgNo(rs.getInt(1));
				img.setImgPath(rs.getString(2));
				img.setImgName(rs.getString(3));
				img.setImgOriginal(rs.getString(4));
				img.setImgLevel(rs.getInt(5));
				img.setBoardNo(boardNo);
				
				imgList.add(img);
				
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return imgList;
	}

}
