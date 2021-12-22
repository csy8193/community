<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value ="${pageContext.servletContext.contextPath}" scope="application"/>
<link rel="stylesheet" href="${contextPath}/resources/css/mypage.css">

<jsp:include page="../common/header-search.jsp"/>
<section>
	<div id="container">
      <div id="title">
                <h1>마이페이지</h1>
            </div>
            <div id="content-wrap">
                <div id="aside-nav">

                    <h3>반려동물</h3>
                    <div class="btn ">자유게시판</div>
                    <div class="btn">노하우게시판</div>
                    <div class="btn">자랑하기</div>

                    <h3>유기동물</h3>
                    <div class="btn">신고하기</div>
                    <div class="btn">보호소추천</div>
                    <div class="btn">아이들근황</div>
                
                    <h3>회원정보</h3>
                    <div class="btn">회원정보 변경</div>
                    <div class="btn">탈퇴하기</div>

                    <h3>고객센터</h3>
                    <div class="btn">1:1 문의하기</div>

                </div>
                <div id="content">
                    <div>
                       <h2>반려동물 등록하기</h2>
                    </div>
                    <div id="animal-add">
                        <ul>
                            <!-- 
                            <li>
                                <span>
                                    <img src="img/dog1.jpg" class="animal-profile">
                                </span>
                                <p>몰리</p>
                                <button>반려동물 정보변경</button>
                            </li>
                             -->
                            <li>
                                <img src="${contextPath}/resources/images/main/plus.png" id="plus"> 
                            </li>
                            
                        </ul>
                    </div>

                    <!-- 동물 등록하기 모달창 시작-->
                    <div class="modal" id="modal">
                        <div class="modal-body">
			                    <div id="fileArea">
									<input type="file" name="img" onchange="loadImg(this)"> 
								</div>
                                <p class="title">반려동물 등록하기</p>
                                <table>
                                    <tr>
                                        <td colspan="2">
                                            <div id="animal-profile">  	
                                          		<img>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>종류</th>
                                        <td>
                                            <select id="category" name="animalCategory">
		                                        <c:forEach items="${animalCategory}" var="ac">
													<option value="${ac.animalCategoryCode}">${ac.animalCategoryName}</option>
												</c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>품종</th>
                                        <td>
                                            <input type="text" name="kind" id="kind" placeholder="반려동물 품종">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>이름</th>
                                        <td>
                                            <input type="text" name="animalName" id="animalName" placeholder="반려동물 이름">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>생년월일</th>
                                        <td>
                                            <select class="ani-birth" name="ani-birth">
                                                <option>2021</option>
                                                <option>2020</option>
                                                <option>2019</option>
                                                <option>2018</option>
                                                <option>2017</option>
                                                <option>2016</option>
                                                <option>2015</option>
                                                <option>2014</option>
                                                <option>2013</option>
                                                <option>2012</option>
                                                <option>2011</option>
                                                <option>2010</option>
                                                <option>2009</option>
                                                <option>2008</option>
                                                <option>2007</option>
                                                <option>2006</option>
                                                <option>2005</option>
                                                <option>2004</option>
                                                <option>2003</option>
                                                <option>2002</option>
                                                <option>2001</option>
                                                <option>2000</option>
                                            </select>
                                            <select class="ani-birth margin" name="ani-birth">
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                                <option>4</option>
                                                <option>5</option>
                                                <option>6</option>
                                                <option>7</option>
                                                <option>8</option>
                                                <option>9</option>
                                                <option>10</option>
                                                <option>11</option>
                                                <option>12</option>
                                            </select>
                                            <select class="ani-birth" name="ani-birth">
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                                <option>4</option>
                                                <option>5</option>
                                                <option>6</option>
                                                <option>7</option>
                                                <option>8</option>
                                                <option>9</option>
                                                <option>10</option>
                                                <option>11</option>
                                                <option>12</option>
                                                <option>13</option>
                                                <option>14</option>
                                                <option>15</option>
                                                <option>16</option>
                                                <option>17</option>
                                                <option>18</option>
                                                <option>19</option>
                                                <option>20</option>
                                                <option>21</option>
                                                <option>22</option>
                                                <option>23</option>
                                                <option>24</option>
                                                <option>25</option>
                                                <option>26</option>
                                                <option>27</option>
                                                <option>28</option>
                                                <option>29</option>
                                                <option>30</option>
                                                <option>31</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>성별</th>
                                        <td>
                                            <input type="radio" name="gender" id="male" value="수컷">
                                            <label for="male" style="margin-right: 50px;">수컷</label>
                                            <input type="radio" name="gender" id="famale" value="암컷">
                                            <label for="famale">암컷</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <button onclick="addAnimal();">등록하기</button>
                                        </td>
                                    </tr>
                                </table>
                        </div>
                    </div>
                    <!-- 동물 등록하기 모달창 끝 -->

                    <!-- 자유게시판 -->
                    <div class="sub">
                        <h2>자유게시판</h2>
                        <p>아직 작성한 글이 없어요!</p>
                    </div>
                    <!-- 자유게시판 끝 -->
                    
                     <!-- 노하우게시판 -->
                     <div class="sub">
                        <h2>노하우 게시판</h2>
                        <p>아직 작성한 글이 없어요!</p>
                    </div>
                    <!-- 자유게시판 끝 -->

                     <!-- 자랑하기 게시판 -->
                     <div class="sub ">
                        <h2>자랑하기</h2>
                        <p>아직 작성한 글이 없어요!</p>
                    </div>
                    <!-- 자유게시판 끝 -->

                    <!-- 신고하기 -->
                    <div class="sub">
                        <h2>신고하기</h2>
                        <p>아직 작성한 글이 없어요!</p>
                    </div>
                    <!-- 자유게시판 끝 -->

                    <!-- 보호소추천 -->
                    <div class="sub">
                        <h2>보호소추천</h2>
                        <p>아직 작성한 글이 없어요!</p>
                    </div>
                    <!-- 자유게시판 끝 -->

                    <!-- 아이들 근황 -->
                    <div class="sub">
                        <h2>아이들근황</h2>
                        <p>아직 작성한 글이 없어요!</p>
                    </div>
                    <!-- 자유게시판 끝 -->

                    <!-- 회원정보수정 -->
                    <div class="sub">
                        <h2>회원 정보 변경</h2>
                        <form>
                            <table id="userUpdate">
                                <tr>
                                    <th>이름</th>
                                    <td>정수민</td>
                                </tr>

                                <tr>
                                    <th>생년월일/성별</th>
                                    <td>1996-05-30/여</td>
                                </tr>

                                <tr>
                                    <th>아이디</th>
                                    <td>wjdtnals0530</td>
                                </tr>
                                <tr>
                                    <th rowspan="3">비밀번호</th>
                                    <td>
                                        <label for="nowPw">현재 비밀번호</label>
                                        <input type="password" name="nowPw" id="nowPw">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="userPw2">변경 비밀번호</label>
                                        <input type="password" name="userPw2" id="userPw2">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="userPw3">변경 비밀번호 확인</label>
                                        <input type="password" name="userPw3" id="userPw3"><br>
                                        <span>*8~20자의 영문+숫자+특수문자(!@#$%^+=)만 사용가능</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th>이메일</th>
                                    <td>
                                        <input type="email" name="userEmail" id="userEmail">
                                        <span class="margin" style="color:#000;">@</span>
                                        <input type="email" name="userEmail" id="EmailAddr">
                                        <button id="chk">중복확인</button>
                                    </td>
                                </tr>
                                <tr>
                                    <th>주소</th>
                                    <td>
                                        <input type="text" name="userAddr" id="userAddr">
                                    </td>
                                </tr>
                                <tr>
                                    <th>휴대폰</th>
                                    <td>
                                        <select id="agency" name="phone">
                                            <option value="KT">KT</option>
                                            <option value="SKT">SKT</option>
                                            <option value="LGT">LGT</option>
                                        </select>
                                        <select id="ph1" name="phone">
                                            <option value="010">010</option>
                                            <option value="011">011</option>
                                            <option value="016">016</option>
                                            <option value="017">017</option>
                                            <option value="018">018</option>
                                            <option value="019">019</option>
                                        </select>
                                        <span class="margin" style="color:#000;">-</span>
                                        <input type="tel" id="ph2" name="phone">
                                        <span class="margin" style="color:#000;">-</span>
                                        <input type="tel" id="ph3" name="phone">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="center">
                                        <button>변경내용 저장하기</button>
                                    </td>
                                </tr>
                            </table>

                        </form>
                    </div>
                    <!-- 회원정보수정 끝 -->
                    
                    <!-- 탈퇴하기 -->
                    <div class="sub">
                        <h2>탈퇴하기</h2>
                        <div class="center">
                            <form>
                                <div style="margin:100px 0">
                                    <label style="margin-right: 10px;">비밀번호 입력</label>
                                    <input type="password" name="withdrawPwChk" id="withdrawPwChk">
                                </div>
                                <button>탈퇴하기</button>
                            </form>
                        </div>
                    </div>
                    <!-- 탈퇴하기 끝-->

                    <!-- 1:1 문의하기 -->
                    <div class="sub">
                        <h2>1:1 문의하기</h2>
                        <p>아직 작성한 글이 없어요!</p>
                    </div>
                    <!-- 1:1문의하기 끝 -->

                </div>
            </div>
            </div>
</section>

<jsp:include page="../common/footer.jsp"/>
<script>
	const contextPath = "${contextPath}";
	
	//로그인한 회원의 회원 번호, 비로그인 시 "" (빈문자열)
	const MemberNo = "${loginMember.memberNo}";

</script>
<script src="${contextPath}/resources/js/addAnimal.js"></script>
<%-- session에 message 속성이 존재하는 경우 alert창으로 해당 내용을 출력 --%>
<c:if test="${!empty sessionScope.message }">
	<script>
	$(function(){ // readey() 함수로 페이지 로딩 완료 후 alert 출력
		alert("${message}")
	})
		
		// EL 작성 시 scope를 지정하지 않으면
		// page -> reuest -> session -> application 순서로 검색하여
		// 일치하는 속성이 있으면 출력
	</script>
	
	<%-- message 1회 출력 후 session에서 제거 --%>
	<c:remove var="message" scope="session"/>
</c:if>
</body>
</html>