<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/rePassword.css">
    <title>비밀번호 재설정</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
    <div id="container">
        <div id="title-container">
            <h1>비밀번호 재설정</h1>
        </div>
        
        <div id="content">
            <form>
                <div class="join-row">
                    <h3 class="join-title"><label for="pw1">비밀번호</label></h3>
                    <span class="ps-box">
                        <input type="password" id="pw1" name="pw1" placeholder="비밀번호 입력">
                    </span>
                </div>
                <div class="join-row">
                    <h3 class="join-title"><label for="pw2">비밀번호 확인</label></h3>
                    <span class="ps-box">
                        <input type="password" id="pw2" name="pw2" placeholder="비밀번호 확인 입력">
                    </span>
                    <p class="check">비밀번호를 확인해주세요.</p>
                </div>
                <button id="rePassword-btn">재설정 하기</button>
            </form>
        </div>
    </div>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>