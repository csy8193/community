package com.together.semiprj.board.model.vo;

public class NboardReply {
	private int replyNo;
	private String replyContent;
	private String replyCreateDate;
	private int nboardNo;
	private int memberNo;
	private int boardNo;
	private String memberId;
	private int statusCode;
	private int feedbackReplyNo;
	private String statusNm;
	private boolean profileExist;
	private String animalImgPath;
	private String animalImgNm;
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
	public String getReplyCreateDate() {
		return replyCreateDate;
	}
	public void setReplyCreateDate(String replyCreateDate) {
		this.replyCreateDate = replyCreateDate;
	}
	public int getNboardNo() {
		return nboardNo;
	}
	public void setNboardNo(int nboardNo) {
		this.nboardNo = nboardNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public int getFeedbackReplyNo() {
		return feedbackReplyNo;
	}
	public void setFeedbackReplyNo(int feedbackReplyNo) {
		this.feedbackReplyNo = feedbackReplyNo;
	}
	public String getStatusNm() {
		return statusNm;
	}
	public void setStatusNm(String statusNm) {
		this.statusNm = statusNm;
	}

	public boolean isProfileExist() {
		return profileExist;
	}
	public void setProfileExist(boolean profileExist) {
		this.profileExist = profileExist;
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
	@Override
	public String toString() {
		return "NboardReply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyCreateDate="
				+ replyCreateDate + ", nboardNo=" + nboardNo + ", memberNo=" + memberNo + ", boardNo=" + boardNo
				+ ", memberId=" + memberId + ", statusCode=" + statusCode + ", feedbackReplyNo=" + feedbackReplyNo
				+ ", statusNm=" + statusNm + ", profileExist=" + profileExist + ", animalImgPath=" + animalImgPath
				+ ", animalImgNm=" + animalImgNm + "]";
	}
	
	
}
