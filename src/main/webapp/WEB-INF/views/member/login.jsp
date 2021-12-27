<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%-- 프로젝트의 시작 주소를 간단히 얻어올 수 있도록 application scope로 contextPath라는 변수를 생성함--%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application"/>


<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/header.css"> 
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/login.css"> 
	<title>로그인</title>
	
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div id="all-wrap">
    <div id="content-login">
        <div id="login-form-wrap">
            <div>
                <p><img src="${contextPath}/resources/images/main/logo.png"></p>
                <form action="${contextPath}/member/login" method="post" onsubmit="return loginValidate()">
                    <ul>
                        <li><input type="text" id="memberId" name="memberId" placeholder="아이디" value="${cookie.saveId.value}" required></li>
                        <li><input type="password" id="memberPw" name="memberPw" placeholder="비밀번호" required></li>
                        <li>
                        	
                        	<%-- 쿠키에 saveId 값이 있을 때 --%>
                    <c:if test="${!empty cookie.saveId.value }">
                    	<c:set var="chk" value="checked"/>
                    </c:if>
                        	
                            <input type="checkbox" name="save" id="save" ${chk}>
                            <label for="save">아이디 저장</label>
                        </li>
                        <li><button>로그인</button></li>
                        <li>
                            <a href="#">아이디 찾기</a>
                            <a href="${contextPath}/member/certification">비밀번호 찾기</a>
                            <a href="${contextPath}/member/agree">회원가입</a>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
	<jsp:include page="../common/footer.jsp"/>
</div>
	
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="${contextPath}/resources/js/member.js"></script>
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