package com.together.semiprj.member.model.vo;

public class AnimalCategory {
	private int animalCategoryCode;
	private String animalCategoryName;
	
	public AnimalCategory() {
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "AnimalCategory [animalCategoryCode=" + animalCategoryCode + ", animalCategoryName=" + animalCategoryName
				+ "]";
	}
	
	
}
