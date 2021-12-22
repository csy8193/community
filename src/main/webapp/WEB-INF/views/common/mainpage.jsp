<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />
<link rel="stylesheet" href="${contextPath}/resources/css/mainpage.css">
<jsp:include page="header.jsp"/>
<section>
<div id="container">
            <div id="main-search-wrap">
                <div id="search-wrap2">
                    <button id="search-btn"><img src="${contextPath}/resources/images/main/search.png"></button>
                    <input type="text" name="main-search" id="main-search" placeholder="관심있는 내용을 검색해보세요!">
                </div>
            </div>

            <div id="board-wrap">
                <div id="best-wrap" class="left-float">
                    <h1 class="board-title">게시판 베스트</h1>
                    <ul>
                    <%--
                     <li>
                            <div class="left-float">
                                <span class="best-ranking">1</span>
                                <div>
                                    <p>게시판 베스트 제목입니다.</p>
                                    <span class="best-date">2021/11/12 [자유게시판]</span>
                                </div>
                            </div>
                            <div class="right-float">
                                <div class="eyes">
                                    <img src="${contextPath}/resources/images/main/vision.png">
                                    <span>256</span>
                                </div>
                            </div>
                        </li>
                     --%>
                       
                    </ul>
                </div>
                <div id="first-wrap" class="right-float">
                    <div id="free-board" class="free-know">
                        <h1 class="board-title-b">실시간 자유게시판</h1>
                        <ul>
                            <%-- 
                            <li>
                                <a href="#"><p>자유게시판 제목이여라~<span>15:12</span></p></a>
                            </li>
                            --%>
                        </ul>
                    </div>
                    <div id="know-how-board" class="free-know">
                        <h1 class="board-title-b">실시간 노하우게시판</h1>
                        <ul>
                            <%-- 
                            <li>
                                <a href="#"><p>자유게시판 제목이여라~<span>15:12</span></p></a>
                            </li>
                            --%>
                        </ul>
                    </div>
                </div>
            </div>

            <div id="good-ranking-wrap">
                <div>
                    <h2>이달의 좋아요 순위!<br><span>누가 제일 멋쟁이인지 구경할까요?</span></h2>
                    <a href="#">자랑하기 게시판 보러가기 ></a>
                </div>
            </div>

            <div id="event-wrap">
                <h1 class="board-title">산책 포인트 상품 교환 안내</h1>
                <p> 매일 산책일지를 작성하여 포인트를 얻어 <br> 레벨에 따른 상품으로 교환하세요! </p>
                <span>더 자세한 내용은 오른쪽 위 더하기 버튼을 눌러주세요:)</span>
            </div>

            <div id="notice-wrap">
                <div id="qna" class="left-float">
                    <h1 class="board-title-b">자주하는 질문</h1>
                    <ul>
                    	<%--
                    	 <li>
                            <a href="#"><p>자주하는 질문 22222<span>15:12</span></p></a>
                        </li>
                    	 --%>
                    </ul>
                </div>
                <div id="notice" class="right-float">
                    <h1 class="board-title-b">공지사항</h1>
                    <ul>
                    	<%--
                    	<li>
                            <a href="#"><p>자주하는 질문 22222<span>15:12</span></p></a>
                        </li>
                    	 --%>
                    </ul>
                </div>
            </div>
        </div>
</section>
<jsp:include page="footer.jsp"/>

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