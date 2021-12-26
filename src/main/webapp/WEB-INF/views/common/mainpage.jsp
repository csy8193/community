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
                    <h1 class="board-title">주간 게시판 베스트</h1>
                    <ul>
                     <%-- <li>${mostViewList}</li>  --%>
                    <c:set var="count" value="1" />
                    <c:forEach items="${mostViewList}" var ="most">
                       <c:choose>
                    	<c:when test="${most.boardCode ==10}">
                    		<c:set var="cate" value="nboard/view?boardNo" />
                    		<c:set var="displaywrite" value="${most.boardTitle}" />
                    	</c:when>
                    	<c:when test="${most.boardCode ==20}">
                    		<c:set var="cate" value="nboard/view?boardNo" />
                    		<c:set var="displaywrite" value="${most.boardTitle}" />
                    	</c:when>
                    	<c:when test="${most.boardCode ==30}">
                    		<c:set var="cate" value="nboard/view?boardNo" />
                    		<c:set var="displaywrite" value="${most.boardTitle}" />
                    	</c:when>
                    	<c:when test="${most.boardCode ==70}">
                    		<c:set var="cate" value="pboard/view?no" />
                    		<c:set var="displaywrite" value="${most.boardContent}" />
                    	</c:when>
                    	<c:when test="${most.boardCode ==80}">
                    		<c:set var="cate" value="pboard/view?no" />
                    		<c:set var="displaywrite" value="${most.boardContent}" />
                    	</c:when>
                    	<c:when test="${most.boardCode ==90}">
                    		<c:set var="cate" value="pboard/view?no" />
                    		<c:set var="displaywrite" value="${most.boardContent}" />
                    	</c:when>
                    	</c:choose>
                  	     <li>
                            <div class="left-float">
                                <span class="best-ranking">${count}</span>
                                <c:set var="count" value="${count+1}" />
                                <div>
                                	<p>${most.boardName}</p>
                                	<div id="best-content">
                                   	 <p><a href="${contextPath}/${cate}=${most.boardNo}&boardCd=${most.boardCode}">${displaywrite}</a></p>
                                	</div>
                                    <span class="best-date">${most.createDate} </span>
                                </div>
                            </div>
                            <div class="right-float">
                                <div class="eyes">
                                    <img src="${contextPath}/resources/images/main/vision.png">
                                    <span>${most.readCount}</span>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                    </ul>
                </div>
                <div id="first-wrap" class="right-float">
                    <div id="free-board" class="free-know">
                        <h1 class="board-title-b"><span>실시간 자유게시판</span><a href="${contextPath}/nboard/list?boardCd=10">+</a></h1>
                        <ul>
                        <%-- <li>${currentFree}</li> --%>
                        <c:forEach items="${currentFree}" var ="Free">
                        	<li>
                                <p><a href="${contextPath}/nboard/view?boardNo=${Free.boardNo}&boardCd=10">${Free.boardTitle}</a><span>${Free.createDate}</span></p>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>
                    <div id="know-how-board" class="free-know">
                        <h1 class="board-title-b"><span>실시간 노하우게시판</span><a href="${contextPath}/nboard/list?boardCd=20">+</a></h1>
                        <ul>
                       <%--  <li>${currentKnowhow }</li> --%>
                        <c:forEach items="${currentKnowhow}" var ="Knowhow">
                        	<li>
                                <p><a href="${contextPath}/nboard/view?boardNo=${Knowhow.boardNo}&boardCd=20">${Knowhow.boardTitle}</a><span>${Knowhow.createDate}</span></p>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="good-ranking-wrap">
                <div>
                    <h2>이달의 좋아요 순위!<br><span>누가 제일 멋쟁이인지 구경할까요?</span></h2>
                    <a href="${contextPath}/pboard/list?boardCd=70">자랑하기 게시판 보러가기 ></a><br>
                    <span class ="ranks123" id="ranking2"><img src="${contextPath}${topLikePeople[1].animalImg}">
                    <c:if test="${empty topLikePeople[1].animalName}">익명</c:if>${topLikePeople[1].animalName} / 
                    <c:if test="${empty topLikePeople[1].animalName}">비밀</c:if>${topLikePeople[1].animalCateNM} / ${topLikePeople[1].animalVariety}<br>
                    좋아요 : ${topLikePeople[1].likeCount}  
                    </span>
                    <span class ="ranks123" id="ranking1"><img src="${contextPath}${topLikePeople[0].animalImg}">
                    <c:if test="${empty topLikePeople[0].animalName}">익명</c:if>${topLikePeople[0].animalName} / 
                    <c:if test="${empty topLikePeople[0].animalName}">비밀</c:if>${topLikePeople[0].animalCateNM} / ${topLikePeople[0].animalVariety}<br>
                     좋아요 : ${topLikePeople[0].likeCount} 
                    </span>
                    <span class ="ranks123" id="ranking3"><img src="${contextPath}${topLikePeople[2].animalImg}">
                    <c:if test="${empty topLikePeople[2].animalName}">익명</c:if>${topLikePeople[2].animalName} / 
                    <c:if test="${empty topLikePeople[2].animalName}">비밀</c:if>${topLikePeople[2].animalCateNM} / ${topLikePeople[2].animalVariety}<br>
                     좋아요 : ${topLikePeople[2].likeCount} 
                    </span>
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
                    <%-- <li>${currentFreQ }</li> --%>
                    <c:forEach items="${currentFreQ}" var ="FreQ">
                    	<li>
                            <p><a href="#">${FreQ.boardTitle}</a><span>${FreQ.createDate}</span></p>
                        </li>
                    </c:forEach>
                    </ul>
                </div>
                <div id="notice" class="right-float">
                    <h1 class="board-title-b">공지사항</h1>
                    <ul>
                    	<%-- <li>${currentNotice}</li> --%>
                    <c:forEach items="${currentNotice}" var ="Notice">
                    	<li>
                            <p><a href="#">${Notice.boardTitle}</a><span>${Notice.createDate}</span></p>
                        </li>
                    </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
</section>
<link rel="stylesheet" href="${contextPath}/resources/css/plus2mainpage.css">
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