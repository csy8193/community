package com.together.semiprj.board.model.vo;

//페이징 처리에 필요한 모든 값들을 저장하고 있는 객체
public class Pagination {
	private int currentPage; //현재페이지
	private int listCount; //전체 게시글 수
	
	private int limit = 5; //한 페이지 목록에 보여지는 게시글 수
	private int pageSize = 5; // 보여질 페이지 번호 개수
	
	private int maxPage; 	//마지막 페이지 번호
	private int startPage;	//보여지는 맨 앞 페이지 번호
	private int endPage;	//보여지는 맨 뒤 페이지 번호
	private int prevPage; //이전페이지의 페이지 번호 맨 끝
	private int nextPage; //다음페이지의 페이지 번호 맨 앞
	
	public Pagination(int listCount, int currentPage) {
		this.listCount = listCount;
		this.currentPage = currentPage;
		// 객체 생성 시 전달 받은 값을 이용해
		// 나머지 필드 값 생성
		makePagination();
	}
	public Pagination(int listCount, int currentPage,int limit, int pageSize) {
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.limit = limit;
		this.pageSize = pageSize;
		// 객체 생성 시 전달 받은 값을 이용해
		// 나머지 필드 값 생성
		makePagination();
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		makePagination();
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
		makePagination();
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}
	private void makePagination() {
		// * maxPage : 최대페이지 == 마지막 페이지 == 총 페이지 수
		
		// 한 페이지에 글이 10개 씩 보여질 경우
		
		// - 게시글이 100개 인 경우 필요한 페이지 수 : 10개 
//		(91~100는 10개)
//		(101~110개는 11개)
//		전체 게시글 수 / 한 페이지에 보여지는 글 수 => 올림 처리
		maxPage = listCount / limit + (listCount % limit >0? 1:0) ;
		
//		startPage : 페이지네이션 목록에서 제일 앞에 보여지는 페이지 번호
//		페이지네이션 목록이 	10개씩 보여진다고 했을 때 
//		현재페이지가 1~10 인 경우 : 1페이지 
//		11~20 인 경우 : 11페이지
//		21~30 인 경우 : 21페이지
		startPage = ((currentPage-1)/pageSize)*pageSize +1;
		
		//* endPage : 페이지네이션 목록에서 가장 끝 페이지번호
//		페이지네이션 목록이 	10개씩 보여진다고 했을 때 
//		현재페이지가 1~10 인 경우 : 10페이지 
//		11~20 인 경우 : 20페이지
//		21~30 인 경우 : 30페이지
		endPage = startPage+pageSize-1;
		
		//** if 계산된 endPage가 전체 페이지 수 (maxPage)를 초과하는 경우
		if(endPage> maxPage) {
			endPage = maxPage; 
		}
		//prevPage : 이전 페이지네이션 목록의 끝번호
		//nextPage : 다음 페이지네이션 목록의 시작번호
		
		if(currentPage <= 10) {
			prevPage =1;
		}
		else {
			prevPage = startPage-1;
		}
		
		if(endPage == maxPage) {
			nextPage= maxPage;
		}
		else {
			nextPage = endPage +1;
		}
		
		
	}
}
