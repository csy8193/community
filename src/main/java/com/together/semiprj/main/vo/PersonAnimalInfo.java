package com.together.semiprj.main.vo;

import java.sql.Date;

public class PersonAnimalInfo {
	private int ranking;
	private int boardNo;
	private int likeCount;
	private int memberNo;
	private int animalNo;
	private String animalName;
	private String animalVariety;
	private String animalGender;
	private Date animalBirth;
	private String animalImg;
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getAnimalNo() {
		return animalNo;
	}
	public void setAnimalNo(int animalNo) {
		this.animalNo = animalNo;
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
	public Date getAnimalBirth() {
		return animalBirth;
	}
	public void setAnimalBirth(Date animalBirth) {
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
		return "PersonAnimalInfo [ranking=" + ranking + ", boardNo=" + boardNo + ", likeCount=" + likeCount
				+ ", memberNo=" + memberNo + ", animalNo=" + animalNo + ", animalName=" + animalName
				+ ", animalVariety=" + animalVariety + ", animalGender=" + animalGender + ", animalBirth=" + animalBirth
				+ ", animalImg=" + animalImg + "]";
	}
	
	
	
}
