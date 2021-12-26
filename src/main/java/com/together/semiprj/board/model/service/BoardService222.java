package com.together.semiprj.board.model.service;

import static com.together.semiprj.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.together.semiprj.board.model.dao.BoardDAO222;
import com.together.semiprj.board.model.vo.Board;
import com.together.semiprj.board.model.vo.BoardImage;
import com.together.semiprj.board.model.vo.Pagination;
import com.together.semiprj.common.XSS;


public class BoardService222 {
	
	BoardDAO222 dao = new BoardDAO222();

	/** 일반게시판 글 삽입
	 * @param boardTitle
	 * @param boardContent
	 * @param memberNo
	 * @param picPath 
	 * @param boardCd 
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(String boardTitle, String boardContent, int memberNo, String picPath, int boardCd) throws Exception{
		Connection conn = getConnection();
		
		int boardNo = dao.nextBoardNo(conn);
		
		int result = dao.insertBoard(conn, boardNo, boardTitle, boardContent, memberNo, boardCd);
		
		if(result > 0) {
			
			result = dao.insertBoardImage(conn, picPath, boardNo);
			
			if(result > 0) {
				commit(conn);
				result = boardNo;
			}
			else rollback(conn);
			
		}
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 사진게시판 글 삽입
	 * @param board
	 * @param imgList
	 * @param boardCd 
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(Board board, List<BoardImage> imgList, int boardCd) throws Exception{
		
		Connection conn = getConnection();

		int boardNo = dao.nextBoardNo(conn);

		board.setBoardNo(boardNo);
		
		board.setBoardContent( XSS.replaceParameter(  board.getBoardContent()  ) );
		String content = board.getBoardContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>")    ;
		board.setBoardContent(content);

		int result = dao.insertBoard(board, boardCd, conn);
		
		if(result > 0) {
			// 3. (이미지가 있을 경우) 이미지 삽입  

			for(BoardImage img : imgList) {
				// + BoardImage에 어떤 게시글의 이미지인지 알려주는 게시글 번호 추가
				img.setBoardNo(boardNo);

				result = dao.insertBoardImage(img, conn);

				if(result == 0) {
					rollback(conn);
					// Service의 메소드는 하나의 트랜잭션을 공유함
					// -> 이미지 정보 삽입 전에 삽입된 게시글 정보도 트랜잭션에 담겨 있음
					break;
				}
			}

			// Board, BoardImage 모두 삽입 성공된 경우
			if(result > 0) { 
				commit(conn);
				result = boardNo;
			}
			else		   rollback(conn);

			// 글 작성 성공 ->  현재 작성한 글 상세 조회 
			// -> 상세 조회 하려면 boardNo가 필요하다
			// -> 삽입 시 사용되던 boardNo가 존재
			// -> boardNo를 controller로 반환하여
			//    상세조회 기능을 재요청 -> 상세 조회로 이동됨

			// 보통 insert 서비스 수행 결과
			// -> 1 성공, 0 실패
			// 이에 대한 조건문 if(result > 0)
			// --> 여기서 실패를 안했다는 것을 알려주기 위해 0보다 큰 값만 반환하면 된다.

			// 0보다 큰값 필요  +  삽입 성공 시 상세조회 해야되는데 ....
			//						-> 게시글 번호(boardNo) 넘겨줘야 되는데 ...
			// 아! 0보다 큰 게시글 번호를 넘겨서 2가지 상황을 모두 만족
			
		}else{ 
			rollback(conn); 
		}
		
		return result;
	}

	/** 페이지 처리
	 * @param cp
	 * @param cd 
	 * @return
	 * @throws Exception
	 */
	public Pagination getPagination(int cp, int bc) throws Exception{
		
		Connection conn = getConnection();
		
		// 전체 게시글 수 조회 DAO 생성
		int listCount = dao.getListCount(conn, bc);
		
		close(conn);
		
		
		return new Pagination(listCount, cp);
	}

	/** 게시글 목록 조회
	 * @param pagination
	 * @param cd 
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Pagination pagination, int cd) throws Exception{
		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectBoardList(pagination, conn, cd);
		
		close(conn);
		
		return boardList;
	}

	/** 게시판 이름 조회
	 * @param boardCd
	 * @return
	 * @throws Exception
	 */
	public String selectBoardName(int boardCd) throws Exception{
		Connection conn = getConnection();
		
		String boardName = dao.selectBoardName(conn, boardCd);
		
		close(conn);
		
		return boardName;
	}

	/** 일반게시글 수정하기 전 데이터 조회
	 * @param boardCd
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public Board selectBoardUpdate(int boardCd, int boardNo) throws Exception{
		Connection conn = getConnection();
		
		Board board = dao.selectBoardUpdate(conn, boardCd, boardNo);
		
		close(conn);
		
		return board;
	}

	/** 일반 게시판 업데이터
	 * @param boardTitle
	 * @param boardContent
	 * @param memberNo
	 * @param picPath
	 * @param boardCd
	 * @param boardNo 
	 * @return
	 * @throws Exception
	 */
	public int updateBoard(String boardTitle, String boardContent, int memberNo, String picPath, int boardCd, int boardNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateBoard(conn, boardTitle, boardContent, memberNo, boardCd, boardNo);
		
		System.out.println(result);
		
		if(result >0) {
			
			 result = dao.updateImage(conn, boardNo, picPath);
			 if(result > 0) {
				 commit(conn);
			 }else { 
				 rollback(conn); 
			}

			
		}
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 사진 게시판 수정 전 데이터 조회
	 * @param boardNo
	 * @param boardCd
	 * @return
	 * @throws Exception
	 */
	public Board selectPboardUpdate(int boardNo, int boardCd) throws Exception{
		Connection conn = getConnection();
		
		Board board = dao.selectPboardUpdate(conn, boardNo, boardCd);
		
		close(conn);
		
		return board;
	}

	/** 사진 게시판 수정 전 이미지 조회
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public List<BoardImage> selectBoardImage(int boardNo) throws Exception{
		Connection conn = getConnection();
		
		List<BoardImage> boardImageList = dao.selectBoardImage(conn, boardNo);
		
		close(conn);
		
		return boardImageList;
	}

	/** 사진 게시판 수정
	 * @param board
	 * @param oldImgList
	 * @param boardCd
	 * @return
	 * @throws Exception
	 */
	public int updateImgBoard(Board board, List<BoardImage> joined, int boardCd) throws Exception {
		Connection conn = getConnection();
		
		String boardContent = XSS.replaceParameter(board.getBoardContent());
		
		boardContent = boardContent.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		
		board.setBoardContent(boardContent);
		
		int result = dao.updatePboardContent(conn, board);
		
		
		if(result > 0) { // 게시글 내용 부분 수정 되었으면
			
			// 이미지 파일 수정 전 이미지 삭제
			result = dao.deleteImage(conn, board);
			
			if (result > 0) { // 이미지 삭제가 완료 되었으면
				
				for(BoardImage img : joined) {
					
					result = dao.insertBoardImage(img, conn);
					
					
				} // end for
				
				if(result > 0 ) commit(conn);
				else	rollback(conn);
				
			}else {
				rollback(conn);
			}
			
		}else {
			rollback(conn);
		}
		
		
		return result;
	}

	/** 이벤트 페이지 목록
	 * @param bc
	 * @return
	 * @throws Exception
	 */
	/*
	 * public List<Board> eventList(int bc) throws Exception{ Connection conn =
	 * getConnection();
	 * 
	 * List<Board> boardList = dao.eventList(conn, bc);
	 * 
	 * close(conn);
	 * 
	 * return boardList; }
	 */

}
