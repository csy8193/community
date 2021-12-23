package com.together.semiprj.walk.member.vo;

public class WalkRank {
	private int rank;
	private int animalNo;
	private int memberNo;
	private int totalP;
	private String animalName;
	private String animalVariety;
	private String animalGender;
	private String animalBirth;
	private String animalImg;
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getAnimalNo() {
		return animalNo;
	}
	public void setAnimalNo(int animalNo) {
		this.animalNo = animalNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getTotalP() {
		return totalP;
	}
	public void setTotalP(int totalP) {
		this.totalP = totalP;
	}
	public String getAnimalName() {
		return animalName;
	}
	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}
	public String getAnimalVariety() {
		return animalVariety;
	}
	public void setAnimalVariety(String animalVariety) {
		this.animalVariety = animalVariety;
	}
	public String getAnimalGender() {
		return animalGender;
	}
	public void setAnimalGender(String animalGender) {
		this.animalGender = animalGender;
	}
	public String getAnimalBirth() {
		return animalBirth;
	}
	public void setAnimalBirth(String animalBirth) {
		this.animalBirth = animalBirth;
	}
	public String getAnimalImg() {
		return animalImg;
	}
	public void setAnimalImg(String animalImg) {
		this.animalImg = animalImg;
	}
	@Override
	public String toString() {
		return "WalkRank [rank=" + rank + ", animalNo=" + animalNo + ", memberNo=" + memberNo + ", totalP=" + totalP
				+ ", animalName=" + animalName + ", animalVariety=" + animalVariety + ", animalGender=" + animalGender
				+ ", animalBirth=" + animalBirth + ", animalImg=" + animalImg + "]";
	}
	
	
}
