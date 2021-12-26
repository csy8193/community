package com.together.semiprj.member.model.vo;

public class AnimalProfile {
	private int animalImgNo;
	private String animalImgPath;
	private String animalImgNm;
	private String  animalImgOriginal;
	private int animalNo;
	
	public AnimalProfile() {
	}

	public AnimalProfile(String animalImgPath, String animalImgNm, String animalImgOriginal) {
		super();
		this.animalImgPath = animalImgPath;
		this.animalImgNm = animalImgNm;
		this.animalImgOriginal = animalImgOriginal;
	}

	public int getAnimalImgNo() {
		return animalImgNo;
	}

	public void setAnimalImgNo(int animalImgNo) {
		this.animalImgNo = animalImgNo;
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

	public String getAnimalImgOriginal() {
		return animalImgOriginal;
	}

	public void setAnimalImgOriginal(String animalImgOriginal) {
		this.animalImgOriginal = animalImgOriginal;
	}

	public int getAnimalNo() {
		return animalNo;
	}

	public void setAnimalNo(int animalNo) {
		this.animalNo = animalNo;
	}

	@Override
	public String toString() {
		return "AnimalProfile [animalImgNo=" + animalImgNo + ", animalImgPath=" + animalImgPath + ", animalImgNm="
				+ animalImgNm + ", animalImgOriginal=" + animalImgOriginal + ", animalNo=" + animalNo + "]";
	}

	
}
