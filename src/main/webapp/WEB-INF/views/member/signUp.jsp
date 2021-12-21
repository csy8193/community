<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${contextPath}/resources/css/signUp.css">
	<title></title>
</head>
<body>
	<%-- 상대 경로 --%>
	<jsp:include page="../common/header.jsp"/>
	
    <div id="container">
        <div id="title-container">
            <h1>회원정보 입력</h1>
        </div>
        <div id="content">
            <form method="POST" action="signup" name="signUpForm" onsubmit="return validate();">
                <div class="join-row">
                    <h3 class="join-title"><label for="id">아이디</label></h3>
                    <span class="ps-box">
                        <input type="text" id="id" name="id" placeholder="아이디 입력">
                    </span>
                    <p class="check" id="checkId"></p>
                </div>
                <div class="join-row">
                    <h3 class="join-title"><label for="pw1">비밀번호</label></h3>
                    <span class="ps-box">
                        <input type="password" id="pw1" name="pw1" placeholder="비밀번호 입력">
                    </span>
                    <p class="check" id="checkPw1"></p>
                </div>
                <div class="join-row">
                    <h3 class="join-title"><label for="pw2">비밀번호 확인</label></h3>
                    <span class="ps-box">
                        <input type="password" id="pw2" name="pw2" placeholder="비밀번호 확인 입력">
                    </span>
                    <p class="check" id="checkPw2"></p>
                </div>
                <div class="join-row">
                    <h3 class="join-title"><label for="name">이름</label></h3>
                    <span class="ps-box">
                        <input type="text" id="name" name="name" placeholder="이름 입력">
                    </span>
                    <p class="check" id="checkName"></p>
                </div>
                <div class="join-row">
                    <h3 class="join-title"><label for="email">이메일 인증</label></h3>
                    <span class="ps-box email-box">
                        <input type="email" id="email" name="email" placeholder="이메일 입력">
                    </span>
                    <button id="check-btn" class="check-btn" type="button">인증</button>
                    <p class="check" id="checkEmail"></p>
                    <span></span>
                </div>
                <button id="signup-btn">회원가입</button>
            </form>
        </div>    
    </div>
    
    <%-- 상대 경로 --%>
	<jsp:include page="../common/footer.jsp"/>
	
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="${contextPath}/resources/js/member.js"></script>
</body>
</html>