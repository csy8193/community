<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/certification.css">
    <title>비밀번호 찾기 인증페이지</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
    <div id="container">
        <div id="title-container">
            <h1>비밀번호 찾기</h1>
        </div>
        <div id="content">
            <form method="POST" action="repw" name="rePwForm" onsubmit="return confirm();">
                <div class="join-row">
                    <h3 class="join-title"><label for="id">아이디</label></h3>
                    <span class="ps-box">
                        <input type="text" id="id" name="id" placeholder="아이디 입력">
                    </span>
                </div>
                <div class="join-row">
                    <h3 class="join-title"><label for="email">이메일 인증</label></h3>
                    <span class="ps-box email-box">
                        <input type="text" id="email" name="email" placeholder="이메일 입력">
                    </span>
                    <button class= "btnbox" id="certification-btn" type="button">인증</button>
                </div>
                <div class="join-row" id="confirm">
                    <h3 class="join-title"><label for="email">인증 번호</label></h3>
                    <span class="ps-box email-box">
                        <input type="text" id="confirmEmail" name="confirmEmail" placeholder="인증번호 입력">
                    </span>
                    <button class= "btnbox" id="confirm-btn" type="button">인증하기</button>
                    <p id="confirmCheck" class="check"></p>
                </div>
                <button id="certificationBtn">다음으로</button>
            </form>
        </div>
    </div>
    <jsp:include page="../common/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="${contextPath}/resources/js/certification.js"></script>
</body>
</html>
