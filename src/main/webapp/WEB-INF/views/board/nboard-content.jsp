<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp"/>
<link rel="stylesheet" href="${contextPath}/resources/css/nboardcontent.css">
	<section>
    <div id="nboard-content">
        <h3 style="color : #4facfe;">${nboard.boardName}</h3>
        <h1 class="nboard-title">${nboard.boardTitle }</h1>
        <div class="nboard-info">
        		<%-- 작성자 반려동물 이미지 / 작성자 프로필 구역 --%>
        		<c:if test="${!empty nboard.animalMainImgPath}">
                	<img src="${contextPath}${nboard.animalMainImgPath}" alt="">
        		</c:if>
                <div><a id="userId">${nboard.memberId}</a></div>
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
                <textarea name="replyText-Area" id="nboard-reply-input" placeholder="댓글을 입력해주세요"></textarea>
                <button id="reply-btn">등록</button>
            	</div>
            	<ul id="all-reply">
        	<c:forEach items="${rList}" var="reply">
        		<c:if test="${reply.feedbackReplyNo==0}">
        			<li class="one-reply">
        				<div class="original">
        					<p>
		                    	<c:choose>
									<c:when test="${reply.profileExist}">
										<img src="${contextPath}${reply.animalImgPath}${reply.animalImgNm}" alt="댓글단 회원의 반려동물 이미지">
									</c:when>
									<c:otherwise>
										<img>
									</c:otherwise>
		                    	</c:choose>
							</p>
	                   		 <div class="reply-user">
	                   		 <span>${reply.memberId}</span><span>${reply.replyCreateDate}</span>
                    		</div>
                    		<div class="reply-content">
                    			<c:choose>
                    			<c:when test="${reply.statusCode==2}"><span>삭제된 댓글입니다.</span><br>
                    			</c:when>
                    			<c:when test="${reply.statusCode==3}"><span>블라인드 된 댓글입니다.</span><br>
                    			</c:when>
                    			<c:otherwise><span>${reply.replyContent}</span><br>
                    			</c:otherwise>
                    			</c:choose>
		                        <c:if test="${reply.statusCode==1}">
		                        <button onclick="createReplyArea(this)" value="${reply.replyNo}"><i class="far fa-comment-dots"></i>댓글달기</button>
		                        <%--<button><i class="fas fa-bullhorn"></i>신고하기</button> --%>
		                    	<c:if test="${reply.memberNo == loginMember.memberNo}">
			                    	<button onclick="updateReply(this)">수정</button>
			                    	<button onclick="deleteNrep(this)">삭제</button>
			                    </c:if>
		                    	</c:if>
		                    </div>
		        			<c:forEach items="${rList}" var="checkfeedback">
		        				<c:if test="${checkfeedback.feedbackReplyNo == reply.replyNo}">
		        				<div class="feedback">
			                    	<p>
			                    	<c:choose>
										<c:when test="${checkfeedback.profileExist}">
											<img src="${contextPath}${checkfeedback.animalImgPath}${checkfeedback.animalImgNm}" alt="댓글단 회원의 반려동물 이미지">
										</c:when>
										<c:otherwise>
											<img>
										</c:otherwise>
			                    	</c:choose>
									</p>
			                    	<div class="reply-user">
			                        <i class="fas fa-level-up-alt"></i><span>${checkfeedback.memberId}</span><span>${checkfeedback.replyCreateDate}</span>
			                    	</div>
			                    	<div class="reply-content" >
			                    			<c:choose>
			                    			<c:when test="${checkfeedback.statusCode==2}"><span>삭제된 댓글입니다.</span><br>
			                    			</c:when>
			                    			<c:when test="${checkfeedback.statusCode==3}"><span>블라인드 된 댓글입니다.</span><br>
			                    			</c:when>
			                    			<c:otherwise><span>${checkfeedback.replyContent}</span><br>
			                    			</c:otherwise>
			                    			</c:choose>
		                    			<c:if test="${reply.statusCode==1}">
			                           		<button onclick="createReplyArea(this)" value="${checkfeedback.replyNo}"><i class="far fa-comment-dots"></i>댓글달기</button>
			                           		<%--<button><i class="fas fa-bullhorn"></i>신고하기</button>--%>
					                   		<c:if test="${checkfeedback.memberNo == loginMember.memberNo}">
					                    	<button onclick="updateReply(this)">수정</button>
					                    	<button onclick="deleteNrep(this)">삭제</button>
					                    	</c:if>
				                    	</c:if>
			                   		</div>
		                    	</div>
		        				</c:if>
		        			</c:forEach>
                    	</div>
        		</li>
        		</c:if>
        		</c:forEach>
        		</ul>
        	</div>
        <div id="like-btn">
            <div>
                <i class="fas fa-angle-up" title="상단으로"></i>
            </div>
            <div>
                <i class="fas fa-list" title="목록으로 돌아가기"></i>
            </div>
            <div <c:if test="${nboard.likeDone}">style="background: none; box-shadow:none; color : red"</c:if>>
            	<c:choose>
            		<c:when test ="${nboard.likeDone}" >
                <i class="fas fa-heart" title="좋아요 누르기" id="nboard-like" style="color : red;" ></i>
            		</c:when>
            		<c:otherwise>
            	<i class="fas fa-heart" title="좋아요" id="nboard-like" style="color : white;" ></i>
            		</c:otherwise>
            	</c:choose>
            </div>
        </div>
    </div>
</section>
<script>
const contextPath = "${contextPath}";
const memberNo = "${nboard.memberNo}"
const loginMemberNo ="${loginMember.memberNo}";
const boardNo = "${nboard.boardNo}";
const category = "${param.boardCd}";  
const likedone = "${nboard.likeDone}";
const likecount = "${nboard.likecount}";
// 수정 전 댓글 요소를 저장할 변수 (댓글 수정 시 사용)
let beforeReplyRow;

</script>
<jsp:include page="../common/footer.jsp"/>
<script src="${contextPath}/resources/js/nboardreply.js"></script> 
</body>
</html>