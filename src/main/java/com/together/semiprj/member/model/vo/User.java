package com.together.semiprj.member.model.vo;

import java.sql.Date;

public class User {

	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberNm;
	private String memberEmail;
	private Date enrollDt;
	private int statusCd;
	private int gradeCd;
	private String profilePath;
	
	public User() { }

	public User(String memberId, String memberPw, String memberNm, String memberEmail) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberNm = memberNm;
		this.memberEmail = memberEmail;
	}

	// 이메일 인증
	public User(String memberId, String memberEmail) {
		this.memberId = memberId;
		this.memberEmail = memberEmail;
	}
	
	// 비밀번호 변경
	public User(String memberId, String memberEmail, String memberPw) {
		this.memberId = memberId;
		this.memberEmail = memberEmail;
		this.memberPw = memberPw;
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

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberNm() {
		return memberNm;
	}

	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public Date getEnrollDt() {
		return enrollDt;
	}

	public void setEnrollDt(Date enrollDt) {
		this.enrollDt = enrollDt;
	}

	public int getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(int statusCd) {
		this.statusCd = statusCd;
	}

	public int getGradeCd() {
		return gradeCd;
	}

	public void setGradeCd(int gradeCd) {
		this.gradeCd = gradeCd;
	}
	

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	@Override
	public String toString() {
		return "User [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberNm="
				+ memberNm + ", memberEmail=" + memberEmail + ", enrollDt=" + enrollDt + ", statusCd=" + statusCd
				+ ", gradeCd=" + gradeCd + ", profilePath=" + profilePath + "]";
	}
	
}
