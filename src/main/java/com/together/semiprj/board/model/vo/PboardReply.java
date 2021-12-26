package com.together.semiprj.board.model.vo;

public class PboardReply {
	
	private int replyNo;
   private String replyContent;
   private String replyCreateDt;
   private int boardNo;
   private int memberNo;
   private String memberNm;
   private int statusCd;
   private String statusNm;
   private int feedbackReplyNo;
   private boolean profileExist;
   private String memberId;
   private String animalImgPath;
   private String animalImgNm;
   private String animalMainImgPath;
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
		return statusCd;
	}


	public void setStatusCd(int statusCd) {
		this.statusCd = statusCd;
	}


	public String getStatusNm() {
		return statusNm;
	}


	public void setStatusNm(String statusNm) {
		this.statusNm = statusNm;
	}


	public int getFeedbackReplyNo() {
		return feedbackReplyNo;
	}


	public void setFeedbackReplyNo(int feedbackReplyNo) {
		this.feedbackReplyNo = feedbackReplyNo;
	}


	
	
	public boolean isProfileExist() {
		return profileExist;
	}


	public void setProfileExist(boolean profileExist) {
		this.profileExist = profileExist;
	}


	
	
	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getAnimalImgPath() {
		return animalImgPath;
	}


	public void setAnimalImgPath(String animalImgPath) {
		this.animalImgPath = animalImgPath;
	}


	public String getAnimalImgNm() {
		return animalImgNm;
	}


	public void setAnimalImgNm(String animalImgNm) {
		this.animalImgNm = animalImgNm;
	}
	
	

	public String getAnimalMainImgPath() {
		return animalMainImgPath;
	}


	public void setAnimalMainImgPath(String animalMainImgPath) {
		this.animalMainImgPath = animalMainImgPath;
	}


	@Override
	public String toString() {
		return "PboardReply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyCreateDt=" + replyCreateDt
				+ ", boardNo=" + boardNo + ", memberNo=" + memberNo + ", memberNm=" + memberNm + ", statusCd="
				+ statusCd + ", statusNm=" + statusNm + ", feedbackReplyNo=" + feedbackReplyNo + ", profileExist="
				+ profileExist + ", memberId=" + memberId + ", animalImgPath=" + animalImgPath + ", animalImgNm="
				+ animalImgNm + ", animalMainImgPath=" + animalMainImgPath + "]";
	}
}
