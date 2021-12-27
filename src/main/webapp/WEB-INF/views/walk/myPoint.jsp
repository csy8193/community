<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body>
		<jsp:include page="../common/header.jsp"></jsp:include>
		<link rel="stylesheet" href="${contextPath}/resources/css/myPoint.css">
	<main>
          <div id="main">
             <h2>My 포인트 </h2>
                <div class="rank-point">
                    <div class="btn-wrap">
                        <a href="${contextPath }/walk/ranking">전체 랭킹</a>
                        <a href="${contextPath }/walk/myPoint">My 포인트</a>
                    </div>
                </div>
                <div class="welcome-text">
                    <p>반가워요, <strong>${rankList[0].animalName} 
                    <c:if test="${!empty rankList[1].animalName}"> / </c:if>${rankList[1].animalName} </strong>주인님!</p>
                    <c:if test="${empty rankList}">
	                    <p>아직 모으신 산책 포인트가 없어요</p>
	                    <p>산책 포인트를 모아 매달 새로워지는 상품과 교환하세요!</p>
                    </c:if>
                <fmt:parseNumber var= "templevel" integerOnly= "true" value= "${rankList[0].totalp/100}"/>
                    <c:if test="${!empty rankList}">
                    <br><p>레벨업까지 <strong>${(templevel)*100+100 - rankList[0].totalp}</strong> 점 남았습니다!</p>
                    </c:if>
                	<c:set var="templevel" value="${templevel+1}" />
                    <%-- <a href="#">자세히 보기${templevel}</a> --%>
                </div>
                <div class="progress">
	                <progress value="${rankList[0].totalp}" max="${(templevel)*100}" id = "pro-bar"></progress>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div class="progress-level">
                        <div>1 .Lv</div>
                        <div>${templevel+1} .Lv</div>
                    </div>
                </div>

                <div class="my-status">
                    <div>
                        <h2>산책레벨</h2>
                        <p>${templevel}.Lv</p>
                    </div>
                    <div>
                        <h2>산책포인트</h2>
                        <p>${rankList[0].totalp}P</p>
                    </div>
                    <div>
                        <h2>전체 랭킹</h2>
                        <p>${rankList[0].myrank}<span>위</span></p>
                    </div>
                </div>

    <div class="calendar">
    	<h4 style="margin-top: 30px;">이번달 My 산책일지 <br></h4>
	    <div style="margin-top: 30px;">
	    <button class="month-control" onclick="prev()"><i class="fas fa-angle-left"></i></button>
	    <span id="today-month"></span>
	    <button class="month-control" onclick="next()"><i class="fas fa-angle-right"></i></button>
	    </div>
	    <table id = "walkhistory">
	        <thead>
	            <tr>
	                <th>SUN</th>
	                <th>MON</th>
	                <th>TUE</th>
	                <th>WED</th>
	                <th>THU</th>
	                <th>FRI</th>
	                <th>SAT</th>
	             </tr>
	        </thead>
     	    <tbody id="month-day">
	        </tbody>
	    </table>
	  </div>
    <div id="walkwrite">
		<h3>현재 <span id="continueCheck">0 </span>일째 연속 산책 중! <br>산책 일지를 작성해주세요! </h3>
		<textarea rows="" cols="" id="walktext" placeholder="산책일지를 써주세요!"></textarea>
		<div id="reg-btn">
			<button onclick="insertWalkHistory(this)">등록하기</button>
			<button onclick="resetWalkText(this)">작성취소</button>
		</div>
		<div id="mywalklist">
			<h1>산책일지 리스트</h1>
			<ul id="mydaywalk">
			</ul>
		</div>
	</div>
	 </div>
        </main>
        <jsp:include page="../common/footer.jsp"></jsp:include>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
 <script>
 	const contextPath = "${contextPath}";
 	let loginMemberNo ="${sessionScope.loginMember.memberNo}";
 	console.log(loginMemberNo);
 	if(loginMemberNo==""){
 		loginMemberNo=0;
 	}
 		//"${sessionScope.MemberNo}"
 </script>
 <script src="${contextPath}/resources/js/calendar.js"></script> 
</body>
</html>