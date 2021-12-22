package com.together.semiprj.member.model.vo;

import java.sql.Date;

public class Animal {
	private int animalNo;
	private String animalNm;
	private String animalVariety;
	private int animalGender;
	private Date animalBirthday;
	private int memberNo;
	private int animalCategoryCode;
	
	public Animal() {
		
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

	public int getAnimalGender() {
		return animalGender;
	}

	public void setAnimalGender(int animalGender) {
		this.animalGender = animalGender;
	}

	public Date getAnimalBirthday() {
		return animalBirthday;
	}

	public void setAnimalBirthday(Date animalBirthday) {
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
	
	


}
