<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value ="${pageContext.servletContext.contextPath}" scope="application"/>
<link rel="stylesheet" href="${contextPath}/resources/css/mypage.css">

<jsp:include page="../common/header.jsp"/>
<section>
	<div id="container">
      <div id="title">
                <h1>${loginMember.memberNm}님의 마이페이지
                </h1>
            </div>
            <div id="content-wrap">
                <div id="aside-nav">

                    <h3>반려동물</h3>
                    <div class="btn" onclick="selectBoardList(10);">자유게시판</div>
                    <div class="btn" onclick="selectBoardList(20);">노하우게시판</div>
                    <div class="btn" onclick="selectBoardList(70);">자랑하기</div>

                    <h3>유기동물</h3>
                    <div class="btn" onclick="selectBoardList(80);">신고하기</div>
                    <div class="btn" onclick="selectBoardList(30);">보호소추천</div>
                    <div class="btn" onclick="selectBoardList(90);">아이들근황</div>
                
                    <h3>회원정보</h3>
                    <div class="btn2">
	                    <div style="display:none;">${loginMember}</div>
	                    회원정보 변경
                    </div>
                    <div class="btn2" id="deleteMember">탈퇴하기</div>

                    <h3>고객센터</h3>
                    <div class="btn" onclick="selectBoardList(40);">1:1 문의하기</div>

                </div>
                <div id="content">
                    <div>
                       <h2>반려동물 등록하기</h2>
                    </div>

                     <div id="profile-chk">
                        <span>* 대표 프로필</span>
	                        <select id="profile-chk-select">
	                            <option value="/resources/images/main/basis-profile-img.png">기본 이미지</option>
	                            <c:forEach items="${aniList}" var="ani">
	                            
	                            	<option value="${ani.animalImgPath}${ani.animalImgNm}" 
	                            	
	                            	 <c:set var="test" value="${ani.animalImgPath}${ani.animalImgNm}"/>
						               	<c:if test="${loginMember.profilePath== test}">
						           			selected </c:if>
						           			
						           			>${ani.animalNm}</option>
						           			
	                            </c:forEach>
	                        </select>
	                        <button onclick="insertProfile();">설정</button>
                    </div>

                    <div id="animal-add-wrap">
                        <ul id="animalList" >
                        <c:forEach items="${aniList}" var="ani">
                        	<li>
                                <span>
                                	<img src="${contextPath}${ani.animalImgPath}${ani.animalImgNm}" style="width:250px">
                                </span>
                                <p class="animalNm">${ani.animalNm}</p>
                                <button class="updateAnimalModalBtn" onclick="updateModal();">반려동물 정보 수정</button>
                                
                                <div style="display:none;">
                                	<p>${ani.animalNo}</p>
                                	<p>${ani.animalNm}</p>
                                	<p>${ani.animalVariety}</p>
                                	<p>${ani.animalGender}</p>
                                	<p>${ani.animalBirthday}</p>
                                	<%-- <p>${ani.memberNo}</p> --%>
                                	<p>${ani.animalCategoryCode}</p>	
                                </div>

                            </li>
                        </c:forEach>
                        </ul>
                        <div id="animalAdd">
                             <img src="${contextPath}/resources/images/main/plus.png" id="plus"> 
                        </div>
                    </div>

                    <!-- 동물 등록하기 모달창 시작-->
                    <div class="modal" id="modal">
                        <div class="modal-body">
                        <form action="addAnimal" method="POST" enctype="multipart/form-data" role="form" >
		                    <div id="fileArea">
								<input type="file" name="animal-profile" id="animal-profile" onchange="loadImg(this)"> 
							</div>
                                <p class="title">반려동물 등록하기</p>
                                
                                <table>
                                    <tr>
                                        <td colspan="2">
                                            <div id="animal-profile2">  	
                                          		<img src="${contextPath}/resources/images/main/basis-profile-img.png">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
	                                    <td colspan="2" class="img-size">
	                                    	<p>*정사각형 이미지만 등록해주세요</p>
	                                    </td>
                                    </tr>
                                    <tr>
                                        <th>종류</th>
                                        <td>
                                            <select id="category" name="animalCategory" required>
		                                        <c:forEach items="${animalCategory}" var="ac">
													<option value="${ac.animalCategoryCode}">${ac.animalCategoryName}</option>
												</c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>품종</th>
                                        <td>
                                            <input type="text" name="animalVariety" id="kind" placeholder="반려동물 품종" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>이름</th>
                                        <td>
                                            <input type="text" name="animalNm" id="animalName" placeholder="반려동물 이름" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>생년월일</th>
                                        <td>
                                            <select class="ani-birth" id="year" name="ani-birth" required>
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
                                            <select class="ani-birth margin" id="month"  name="ani-birth" required>
                                                <option>01</option>
                                                <option>02</option>
                                                <option>03</option>
                                                <option>04</option>
                                                <option>05</option>
                                                <option>06</option>
                                                <option>07</option>
                                                <option>08</option>
                                                <option>09</option>
                                                <option>10</option>
                                                <option>11</option>
                                                <option>12</option>
                                            </select>
                                            <select class="ani-birth" id="date"  name="ani-birth" required>
                                                <option>01</option>
                                                <option>02</option>
                                                <option>03</option>
                                                <option>04</option>
                                                <option>05</option>
                                                <option>06</option>
                                                <option>07</option>
                                                <option>08</option>
                                                <option>09</option>
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
                                            <input type="radio" name="gender" id="male" value="수컷" required>
                                            <label for="male" style="margin-right: 50px;">수컷</label>
                                            <input type="radio" name="gender" id="famale" value="암컷" required>
                                            <label for="famale">암컷</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <button class="insertBtn" type="button" onclick="addAnimal();">등록하기</button>
                                        </td>
                                    </tr>
                                </table> 
                            </form>
                        </div>
                    </div>
                    <!-- 동물 등록하기 모달창 끝 -->
                    
                    
                    <!-- 동물 수정하기 모달창 시작-->
                     <div class="modal updateModal">
                        <div class="modal-body">
                         	<div class="fileArea">
								<input type="file" name="animalProfile" class="animalProfileFile" onchange="updateLoadImg(this)"> 
							</div>
							<div>
								<p class="title title2 left-float">반려동물 정보 수정하기</p>
	                            <button class="right-float" id="deleteBtn" onclick="deleteAnimal();">삭제하기</button>
							</div>

                                <table>
                                    <tr>
                                        <td colspan="2">
                                            <div class="animalProfileImg">
                                                <img src="" >
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
	                                    <td colspan="2" class="img-size">
	                                    	<p>*정사각형 이미지만 등록해주세요</p>
	                                    </td>
                                    </tr>
                                    <tr>
                                        <th>종류</th>
                                        <td>
                                            <select class="updateCategory">
                                                 <c:forEach items="${animalCategory}" var="ac">
													<option value="${ac.animalCategoryCode}">${ac.animalCategoryName}</option>
												</c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>품종</th>
                                        <td>
                                            <input type="text" name="updateKind" class="updateKind" placeholder="반려동물 품종" >
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>이름</th>
                                        <td>
                                            <input type="text" name="updateAnimalName" class="updateAnimalName" placeholder="반려동물 이름" >
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>생년월일</th>
                                        <td>
                                            <select class="select ani-birth" id="year1">
                                                <option value="2021">2021</option>
                                                <option value="2020">2020</option>
                                                <option value="2019">2019</option>
                                                <option value="2018">2018</option>
                                                <option value="2017">2017</option>
                                                <option value="2016">2016</option>
                                                <option value="2015">2015</option>
                                                <option value="2014">2014</option>
                                                <option value="2013">2013</option>
                                                <option value="2012">2012</option>
                                                <option value="2011">2011</option>
                                                <option value="2010">2010</option>
                                                <option value="2009">2009</option>
                                                <option value="2008">2008</option>
                                                <option value="2007">2007</option>
                                                <option value="2006">2006</option>
                                                <option value="2005">2005</option>
                                                <option value="2004">2004</option>
                                                <option value="2003">2003</option>
                                                <option value="2002">2002</option>
                                                <option value="2001">2001</option>
                                                <option value="2000">2000</option>
                                            </select>
                                            <select class="select margin ani-birth" id="month1">
                                                <option value="01">01</option>
                                                <option value="02">02</option>
                                                <option value="03">03</option>
                                                <option value="04">04</option>
                                                <option value="05">05</option>
                                                <option value="06">06</option>
                                                <option value="07">07</option>
                                                <option value="08">08</option>
                                                <option value="09">09</option>
                                                <option value="10">10</option>
                                                <option value="11">11</option>
                                                <option value="12">12</option>
                                            </select>
                                            <select class="select ani-birth" id="date1">
                                                <option value="01">01</option>
                                                <option value="02">02</option>
                                                <option value="03">03</option>
                                                <option value="04">04</option>
                                                <option value="05">05</option>
                                                <option value="06">06</option>
                                                <option value="07">07</option>
                                                <option value="08">08</option>
                                                <option value="09">09</option>
                                                <option value="10">10</option>
                                                <option value="11">11</option>
                                                <option value="12">12</option>
                                                <option value="13">13</option>
                                                <option value="14">14</option>
                                                <option value="15">15</option>
                                                <option value="16">16</option>
                                                <option value="17">17</option>
                                                <option value="18">18</option>
                                                <option value="19">19</option>
                                                <option value="20">20</option>
                                                <option value="21">21</option>
                                                <option value="22">22</option>
                                                <option value="23">23</option>
                                                <option value="24">24</option>
                                                <option value="25">25</option>
                                                <option value="26">26</option>
                                                <option value="27">27</option>
                                                <option value="28">28</option>
                                                <option value="29">29</option>
                                                <option value="30">30</option>
                                                <option value="31">31</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>성별</th>
                                        <td>
                                            <input type="radio" name="updateGender" class="updateGender" value="수컷">
                                            <label for="male" style="margin-right: 50px;">수컷</label>
                                            <input type="radio" name="updateGender" class="updateGender" value="암컷">
                                            <label for="famale">암컷</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <button id="upBtn" onclick="updateAnimal();">수정하기</button>
                                        </td>
                                    </tr>
                                    
                            </table>
                        </div>
                    </div>
                    <!--  반려동물 수정하기 끝 -->
                   
                    
                     <!-- 자유게시판 -->
                     <div class="sub" id="sub-board">    
                        
                     </div>
                    <!-- 자유게시판 끝 -->
                 

                    <!-- 회원정보수정 -->
                    <div class="sub2">
                        <h2>회원 정보 변경</h2>
                        <form method="POST" action="mypagePwUpdate" onsubmit="return validate();">
                            <table id="userUpdate">
                                <tr>
                                    <th>이름</th>
                                    <td>${loginMember.memberNm}</td>
                                </tr>
                                <tr>
                                    <th>아이디</th>
                                    <td>${loginMember.memberId}</td>
                                </tr>
                                <tr>
                                    <th rowspan="3">비밀번호</th>
                                    <td>
                                        <label for="nowPw">현재 비밀번호</label>
                                        <input type="password" name="nowPw" id="nowPw" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="userPw2">변경 비밀번호</label>
                                        <input type="password" name="userPw2" id="userPw2" required><br>
                                        <span id="chkPw1"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="userPw3">변경 비밀번호 확인</label>
                                        <input type="password" name="userPw3" id="userPw3" required><br>
                                       	<span id="chkPw2"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <th>이메일</th>
                                    <td>
                                        ${loginMember.memberEmail}
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="center">
                                        <button>회원정보 수정하기</button>
                                    </td>
                                </tr>
                            </table>

                        </form>
                    </div>
                    <!-- 회원정보수정 끝 -->
                    
                    <!-- 탈퇴하기4-->
                    <div class="sub2">
                        <h2>탈퇴하기</h2>
                        <div class="center">
                        	<form method="POST" action="mypagePwDelete" onsubmit="return mypagePwDelete();">
                                <div style="margin:100px 0">
                                    <label style="margin-right: 10px;">비밀번호 입력</label>
                                    <input type="password" name="myPagedelete" id="myPagedelete">
                                </div>
                                <button>탈퇴하기</button>
                            </form>
                        </div>
                    </div>
                    <!-- 탈퇴하기 끝-->

                </div>
            </div>
    
            </div>
</section>

<jsp:include page="../common/footer.jsp"/>
<script>
	const contextPath = "${contextPath}";
	
	//로그인한 회원의 회원 번호, 비로그인 시 "" (빈문자열)
	const MemberNo = "${loginMember.memberNo}";
	
	// 로그인한 회원의 프로필경로와 선택된 옵션과 같으면 selected를 주는 if문을 작성해야한다..

</script>
<script src="${contextPath}/resources/js/myPagememberUpdate.js"></script>
<script src="${contextPath}/resources/js/animal.js"></script>



<%-- session에 message 속성이 존재하는 경우 alert창으로 해당 내용을 출력 --%>
<c:if test="${!empty sessionScope.message }">
	<script>
	$(function(){ // readey() 함수로 페이지 로딩 완료 후 alert 출력
		alert("${message}");
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