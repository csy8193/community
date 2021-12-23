package com.together.semiprj.walk.member.vo;

public class Mypoint {
	private int memberNo;
	private int totalp;
	private String animalName;
	private int checkWalk;
	private String checkDate;
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getTotalp() {
		return totalp;
	}
	public void setTotalp(int totalp) {
		this.totalp = totalp;
	}
	public String getAnimalName() {
		return animalName;
	}
	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}
	public int getCheckWalk() {
		return checkWalk;
	}
	public void setCheckWalk(int checkWalk) {
		this.checkWalk = checkWalk;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	@Override
	public String toString() {
		return "Mypoint [memberNo=" + memberNo + ", totalp=" + totalp + ", animalName=" + animalName + ", checkWalk="
				+ checkWalk + ", checkDate=" + checkDate + "]";
	}
	
	
}
