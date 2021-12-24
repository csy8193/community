package com.together.semiprj.member.model.vo;

import java.sql.Date;

public class Animal {
	private int animalNo;
	private String animalNm;
	private String animalVariety;
	private String animalGender;
	private String animalBirthday;
	private int memberNo;
	private int animalCategoryCode;
	private String animalCategoryName;
	private String animalImgPath;
	private String animalImgNm;

	public Animal() {
		
	}
	

	public Animal(String animalNm, String animalVariety, String animalGender, String animalBirthday, int memberNo,
			int animalCategoryCode) {
		super();
		this.animalNm = animalNm;
		this.animalVariety = animalVariety;
		this.animalGender = animalGender;
		this.animalBirthday = animalBirthday;
		this.memberNo = memberNo;
		this.animalCategoryCode = animalCategoryCode;
	}


	public int getAnimalNo() {
		return animalNo;
	}

	public void setAnimalNo(int animalNo) {
		this.animalNo = animalNo;
	}

	public String getAnimalNm() {
		return animalNm;
	}

	public void setAnimalNm(String animalNm) {
		this.animalNm = animalNm;
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


	public String getAnimalBirthday() {
		return animalBirthday;
	}

	public void setAnimalBirthday(String animalBirthday) {
		this.animalBirthday = animalBirthday;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	
	
	public int getAnimalCategoryCode() {
		return animalCategoryCode;
	}

	public void setAnimalCategoryCode(int animalCategoryCode) {
		this.animalCategoryCode = animalCategoryCode;
	}



	public String getAnimalCategoryName() {
		return animalCategoryName;
	}

	public void setAnimalCategoryName(String animalCategoryName) {
		this.animalCategoryName = animalCategoryName;
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
		return "Animal [animalNo=" + animalNo + ", animalNm=" + animalNm + ", animalVariety=" + animalVariety
				+ ", animalGender=" + animalGender + ", animalBirthday=" + animalBirthday + ", memberNo=" + memberNo
				+ ", animalCategoryCode=" + animalCategoryCode + ", animalCategoryName=" + animalCategoryName
				+ ", animalImgPath=" + animalImgPath + ", animalImgNm=" + animalImgNm + "]";
	}


}
