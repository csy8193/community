package com.together.semiprj.board.model.vo;

import java.util.List;

public class Nboard {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String createDt;
	private int readCount;
	private int boardCd;
	private int statusCd;
	private int memberNo;
	
	private String memberId;
	private String boardName;
	private String boardStatusName;
	private int likecount;
	private int replycount;
	private boolean likeDone;
	
	//메인 이미지 저장용 객체
	private int boardPicNo;
	private  String boardMainImgPath;
	private int animalImgNo;
	private String animalMainImgPath;
		
	//해당 게시글 이미지들의 주소 목록을 저장할 리스트
	private List<NboardImage> nBoardImgList;
	//해당 회원의 반려동물 이미지 리스트 주소 목록을 저장할 리스트
	private List<NboardAnimalImg> NboardAnimalImg;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getBoardCd() {
		return boardCd;
	}
	public void setBoardCd(int boardCd) {
		this.boardCd = boardCd;
	}
	public int getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(int statusCd) {
		this.statusCd = statusCd;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardStatusName() {
		return boardStatusName;
	}
	public void setBoardStatusName(String boardStatusName) {
		this.boardStatusName = boardStatusName;
	}
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public int getReplycount() {
		return replycount;
	}
	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}
	public boolean isLikeDone() {
		return likeDone;
	}
	public void setLikeDone(boolean likeDone) {
		this.likeDone = likeDone;
	}
	public int getBoardPicNo() {
		return boardPicNo;
	}
	public void setBoardPicNo(int boardPicNo) {
		this.boardPicNo = boardPicNo;
	}
	public String getBoardMainImgPath() {
		return boardMainImgPath;
	}
	public void setBoardMainImgPath(String boardMainImgPath) {
		this.boardMainImgPath = boardMainImgPath;
	}
	public int getAnimalImgNo() {
		return animalImgNo;
	}
	public void setAnimalImgNo(int animalImgNo) {
		this.animalImgNo = animalImgNo;
	}
	public String getAnimalMainImgPath() {
		return animalMainImgPath;
	}
	public void setAnimalMainImgPath(String animalMainImgPath) {
		this.animalMainImgPath = animalMainImgPath;
	}
	public List<NboardImage> getnBoardImgList() {
		return nBoardImgList;
	}
	public void setnBoardImgList(List<NboardImage> nBoardImgList) {
		this.nBoardImgList = nBoardImgList;
	}
	public List<NboardAnimalImg> getNboardAnimalImg() {
		return NboardAnimalImg;
	}
	public void setNboardAnimalImg(List<NboardAnimalImg> nboardAnimalImg) {
		NboardAnimalImg = nboardAnimalImg;
	}
	@Override
	public String toString() {
		return "Nboard [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", createDt=" + createDt + ", readCount=" + readCount + ", boardCd=" + boardCd + ", statusCd="
				+ statusCd + ", memberNo=" + memberNo + ", memberId=" + memberId + ", boardName=" + boardName
				+ ", boardStatusName=" + boardStatusName + ", likecount=" + likecount + ", replycount=" + replycount
				+ ", likeDone=" + likeDone + ", boardPicNo=" + boardPicNo + ", boardMainImgPath=" + boardMainImgPath
				+ ", animalImgNo=" + animalImgNo + ", animalMainImgPath=" + animalMainImgPath + ", nBoardImgList="
				+ nBoardImgList + ", NboardAnimalImg=" + NboardAnimalImg + "]";
	}
	
	
	

	

	
	
}	
