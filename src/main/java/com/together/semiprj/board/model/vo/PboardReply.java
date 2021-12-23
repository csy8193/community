package com.together.semiprj.board.model.vo;

public class PboardReply {
	
	private int replyNo;
   private String replyContent;
   private String replyCreateDt;
   private int boardNo;
   private int memberNo;
   private String memberNm;
   private int StatusCd;
   private String StatusNm;
   
   public PboardReply() { }

	public int getReplyNo() {
		return replyNo;
	}
	
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	
	public String getReplyContent() {
		return replyContent;
	}
	
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	public String getReplyCreateDt() {
		return replyCreateDt;
	}
	
	public void setReplyCreateDt(String replyCreateDt) {
		this.replyCreateDt = replyCreateDt;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	public int getMemberNo() {
		return memberNo;
	}
	
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	
	public String getMemberNm() {
		return memberNm;
	}
	
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	
	public int getStatusCd() {
		return StatusCd;
	}
	
	public void setStatusCd(int statusCd) {
		StatusCd = statusCd;
	}
	
	public String getStatusNm() {
		return StatusNm;
	}
	
	public void setStatusNm(String statusNm) {
		StatusNm = statusNm;
	}

	@Override
	public String toString() {
		return "PboardReply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyCreateDt=" + replyCreateDt
				+ ", boardNo=" + boardNo + ", memberNo=" + memberNo + ", memberNm=" + memberNm + ", StatusCd="
				+ StatusCd + ", StatusNm=" + StatusNm + "]";
	}
   
	   
}
