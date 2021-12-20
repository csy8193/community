<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<section>
    <div id="nboard-content">
        <h3 style="color : #4facfe;">${nboard.boardName}</h3>
        <h1 class="nboard-title">${nboard.boardTitle }</h1>
        <div class="nboard-info">
        		<%-- 작성자 프로필 구역 --%>
                <img src="https://cdn.pixabay.com/photo/2014/07/31/22/50/photographer-407068_960_720.jpg" alt="">
                
                
                <div><a id="userId">${nboard.memberId }</a></div>
                <div>
			                <c:choose>
			                <c:when test="${nboard.likeDone}">
			                	<c:set var="heartShape" value ="fas" />
			                </c:when>
			                <c:otherwise><c:set var="heartShape" value ="far" /></c:otherwise>
			                </c:choose>
                   	<i class="${heartShape} fa-heart">
                    </i><span>${nboard.likecount }</span>
                    <i class="far fa-comment-dots"></i><span>${nboard.replycount }</span>
                    <i class="far fa-eye fa-2x"></i><span>${nboard.readCount }</span>
                    <i class="fas fa-calendar-alt"></i><span>${nboard.createDt }</span>
                </div>
        </div>
        <div class="nboard-inner">
            <img src="https://cdn.pixabay.com/photo/2021/09/27/03/19/bichon-6659330_960_720.jpg" alt="">
            <p>
                ${nboard.boardContent}
            </p>
        </div>
        <div class="nboard-reply">
            <div  id="reply-text">
                    <textarea type="text" name="replyText-Area"></textarea>
                    <button id="reply-btn">등록</button>
            </div>
            <ul class="all-reply">
                <li class="one-reply">
                	<div class="original">
                    <img src="https://cdn.pixabay.com/photo/2021/09/27/03/19/bichon-6659330_960_720.jpg" alt="">
                    <div class="reply-user">
                        <span>id입니다</span><span>2021-12-11 17:50:01</span>
                    </div>
                    <div class="reply-content">
                        <span>
                           안녕하세요
                        </span><br>
                            <button><i class="far fa-comment-dots"></i>댓글달기</button><button><i class="fas fa-bullhorn"></i>신고하기</button>
                    </div>
                    </div>
                	<div class="feedback">
                    <img src="https://cdn.pixabay.com/photo/2016/12/30/17/27/cat-1941089_960_720.jpg" alt=""></img>
                    <div class="reply-user">
                        <i class="fas fa-level-up-alt"></i><span>id입니다</span><span>2021-12-11 17:50:01</span>
                    </div>
                    <div class="reply-content">
                        <span>
                           안녕하세요!
                        </span><br>
                           <button><i class="far fa-comment-dots"></i>댓글달기</button><button><i class="fas fa-bullhorn"></i>신고하기</button>
                    </div>
                    </div>
                </li>
            </ul>
        </div>
        <div id="like-btn">
            <div>
                <i class="fas fa-angle-up" title="상단으로"></i>
            </div>
            <div>
                <i class="fas fa-list" title="목록으로 돌아가기"></i>
            </div>
            <div>
                <i class="fas fa-heart" title="좋아요 누르기"></i>
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