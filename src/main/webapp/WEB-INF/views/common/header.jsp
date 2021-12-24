<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 시작 주소를 간단히 얻어올 수 있도록 application scope로 contextPath라는 변수를 생성함--%>
<c:set var="contextPath" value ="${pageContext.servletContext.contextPath}" scope="application"/>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>반려동물 커뮤니티 Together</title>
<!-- 공용 CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
</head>
<body>

	<div id="header_wrap">
		<div id="login-wrap">
		    <div id="login">
		        <ul>
		        <c:choose>
						<c:when test="${ empty sessionScope.loginMember }">
							<%-- 로그인이 되어있지 않을 때 --%>
		               		<li><a href="${contextPath}/member/login">로그인</a></li>
		               		<li><a href="${contextPath}/member/agree">회원가입</a></li>
						</c:when>
						<c:otherwise>
							<%-- 로그인이 되어 있을 때 --%>
							<li class="nav-item active"><a class="nav-link" href="${contextPath}/member/myPage">${sessionScope.loginMember.memberNm}</a></li>
							<li class="nav-item active"><a class="nav-link" href="${contextPath}/member/logout">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
		            
		        </ul>
	       	</div> 
	    </div>
	    <div id="header">
	        <div id="logo">
	            <a href="${contextPath}"><img src="${contextPath}/resources/images/main/logo2.png"></a>
	        </div>
	        <div id="nav_wrap">
	            <ul id="nav">
	                <li class="nav-li">
	                    <a href="#">반려동물</a>
	                    <div class="sub-hover">
	                        <ul class="nav-sub">
	                            <li><a href="${contextPath}/nboard/list?boardCd=10">자유게시판</a></li>
	                            <li><a href="${contextPath}/nboard/list?boardCd=20">노하우게시판</a></li>
	                            <li><a href="${contextPath}/pboard/list?boardCd=70">자랑게시판</a></li>
	                        </ul>
	                    </div>
	                </li>
	                <li class="nav-li"> 
	                    <a href="#">유기동물</a>
	                    <div class="sub-hover">
	                        <ul class="nav-sub">
	                            <li><a href="${contextPath}/pboard/list?boardCd=80">신고하기</a></li>
	                            <li><a href="${contextPath}/nboard/list?boardCd=30">보호소추천</a></li>
	                            <li><a href="${contextPath}/pboard/list?boardCd=90">아이들근황</a></li>
	                        </ul>
	                    </div>
	                </li>
	                <li class="nav-li">
	                  <a href="#">동물병원</a>
	                </li>
	                <li class="nav-li">
	                    <a href="#">산책</a>
	                    <div class="sub-hover">
	                        <ul class="nav-sub">
	                            <li><a href="#">산책일지</a></li>
	                            <li><a href="#">산책랭킹</a></li>
	                        </ul>
	                    </div>
	                </li>
	                <li class="nav-li">
	                    <a href="#">공지사항</a>
	                    <div class="sub-hover">
	                        <ul class="nav-sub">
	                            <li><a href="#">1:1 문의하기</a></li>
	                            <li><a href="${contextPath}/board/notice">자주하는 질문</a></li>
	                            <li><a href="${contextPath}/board/event">이벤트</a></li>
	                        </ul>
	                    </div>
	                </li>
	                <li class="nav-li"><a href="${contextPath}/member/mypage">마이페이지</a></li>
	            </ul>
	            <%-- nav end --%>
	        </div>
	        <%-- nav-wrap end --%>
	    </div>
	    <%-- header end--%>
	</div>
	<%-- haeder-wrap end --%>
</body>