package com.together.semiprj.board.model.vo;

public class NboardAnimalImg {
	private int animalNo;
	private int imgNo;
	private String imgPath;
	private String imgName;
	private String imgOriginal;
	private int imgLevel;
	private int memberNo;
	
	public int getAnimalNo() {
		return animalNo;
	}
	public void setAnimalNo(int animalNo) {
		this.animalNo = animalNo;
	}
	public NboardAnimalImg() {
	
	}
	public int getImgNo() {
		return imgNo;
	}
	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgOriginal() {
		return imgOriginal;
	}
	public void setImgOriginal(String imgOriginal) {
		this.imgOriginal = imgOriginal;
	}
	public int getImgLevel() {
		return imgLevel;
	}
	public void setImgLevel(int imgLevel) {
		this.imgLevel = imgLevel;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	@Override
	public String toString() {
		return "NboardAnimalImg [imgNo=" + imgNo + ", imgPath=" + imgPath + ", imgName=" + imgName + ", imgOriginal="
				+ imgOriginal + ", imgLevel=" + imgLevel + ", memberNo=" + memberNo + "]";
	}
	
}
