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
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/login.css"> 
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/header.css"> 
	<title></title>
	
</head>
<body>
    <div id="all-wrap">
        <div id="login-wrap">
            <div id="login">
                <ul>
                    <li><a href="${contextPath}/member/login">로그인</a></li>
                    <li><a href="${contextPath}/member/signup">회원가입</a></li>
                </ul>
            </div>
        </div>
        <div id="header_wrap">
           
            <div id="header">
                <div id="logo">
                    <a href="#"><img src="img/logo2.png"></a>
                </div>
                <div id="nav_wrap">
                    <ul id="nav">
                        <li class="nav-li">
                            <a href="#">반려동물</a>
                            <div>
                                <ul class="nav-sub">
                                    <li><a href="#">왕왕냥냥</a></li>
                                    <li><a href="#">멍부냥조</a></li>
                                    <li><a href="#">자랑게시판</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-li"> 
                            <a href="#">유기동물</a>
                            <div>
                                <ul class="nav-sub">
                                    <li><a href="#">신고하기</a></li>
                                    <li><a href="#">보호소추천</a></li>
                                    <li><a href="#">아이들근황</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-li">
                          <a href="#">동물병원</a>
                        </li>
                        <li class="nav-li">
                            <a href="#">산책</a>
                            <div>
                                <ul class="nav-sub">
                                    <li><a href="#">산책일지</a></li>
                                    <li><a href="#">산책랭킹</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-li">
                            <a href="#">공지사항</a>
                            <div>
                                <ul class="nav-sub">
                                    <li><a href="#">1:1 문의하기</a></li>
                                    <li><a href="#">자주하는 질문</a></li>
                                    <li><a href="#">이벤트</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-li"><a href="#">마이페이지</a></li>
                    </ul>
                </div>
            </div>
        </div> 
        <div id="content">
            <div id="login-form-wrap">
                <div>
                    <p><img src="img/logo.png"></p>
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
                                <a href="#">비밀번호 찾기</a>
                                <a href="${contextPath}/member/signup">회원가입</a>
                            </li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
        <div id="footer">
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="${contextPath}/resources/js/member.js"></script>

</body>
</html>