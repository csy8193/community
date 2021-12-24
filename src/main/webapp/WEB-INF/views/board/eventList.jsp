<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp"/>
    <section>
    <div id="event">
        <div id="event-title">
            <span>진행중인 이벤트</span>
        </div>
        <div id="event-search">
            <input type="text" placeholder="이벤트명을 입력하세요"> <button id="event-btn"><i class="fas fa-search"></i></button>
        </div>
        <c:choose>
        	<c:when test="${empty boardList}">
        		<p>게시글이 존재하지 않습니다</p>
        	
        	</c:when>
        	<c:otherwise>
	        	<c:forEach var="board" items="${boardList}">
			        <div class="event-one">
			            <div class="event-img">
			                <img src="${board.boardPicPath}">
			            </div>
			            <div class="event-name">
		                    <h1>${board.boardTitle}</h1>
		                    <h3>${board.createDate}</h3>
			            </div>
			        </div>
	        	</c:forEach>
        	</c:otherwise>
        </c:choose>
    </div>
</section>
<jsp:include page="../common/footer.jsp"/>
<link rel="stylesheet" href="${contextPath}/resources/css/eventList.css">
</body>
</html>