<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp"/>
	<section>
	    <div id="nboard-wrapper">
        <h1 id = "nboard-category">${boardList[0].boardName}</h1>
				
            	<c:choose>
            	<c:when test="${empty boardList}">
           			<div class="content-category">게시글이 존재하지 않습니다.</div>
           		</c:when>
           		<c:otherwise>
           			<c:forEach items="${boardList}" var="board">
			         <div class="nboard-style">
           				  <div class="nboard-pic">
                				<img src="https://cdn.pixabay.com/photo/2019/11/08/11/56/cat-4611189_960_720.jpg" alt=""> 
           				 </div>
		            	<div class="nboard-content" >
	            		 <div class="content-category" >${board.boardName}</div>
	            		 <div class="content-title" onclick="location.href ='view?cp=${pagination.currentPage}&boardNo=${board.boardNo}&statusCd=${board.statusCd}'" style="cursor:pointer;">
	            		 ${board.boardTitle}</div>
	            		 <div class="content-text">${board.boardContent}</div>
	            		 <div>
     							<div class="nboard-info">
	                        		<img src="https://cdn.pixabay.com/photo/2016/07/01/22/34/people-1492052_960_720.jpg" alt="">
	                    		</div>
	                    		<div class="nboard-info">
	                        		<a href="">${board.memberId}</a>
			                        <i class="far fa-heart"></i><span>${board.likecount}</span>
			                        <i class="far fa-comment-dots"></i><span>${board.replycount}</span>
			                        <i class="far fa-eye fa-2x"></i><span>${board.readCount}</span>
			                        <i class="fas fa-calendar-alt"></i><span>${board.createDt}</span>
			                    </div>
	            		 	</div>
		            	</div>
			        </div>
           			</c:forEach>
            	</c:otherwise>
            	</c:choose>
        <div id="paging">
        	<c:if test="${pagination.startPage !=1}">
	           	<a href="list?cp=1"><i class="fas fa-angle-double-left"></i></a>
	            <a href="list?cp=${pagination.prevPage}"><i class="fas fa-angle-left"></i></a>
            </c:if>
            <span class="pagenum">
				<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1" var="i">
					<c:choose>
						<c:when test="${i== pagination.currentPage}">
							<a style="color : black; font-weigt: 700; "> ${i}</a>
						</c:when>
						<c:otherwise>
							<a href="list?cp=${i}"> ${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>			
            </span>
            <c:if test="${pagination.maxPage !=pagination.endPage}">
	             <a href="list?cp=${pagination.nextPage}"><i class="fas fa-angle-right"></i></a>
	             <a href="list?cp=${pagination.maxPage}"><i class="fas fa-angle-double-right"></i></a>
             </c:if>
        </div>
 <!--       <c:if test="${empty loginMember}">  -->
		    <div id="write">
		        <i class="pen"><span>글 작성</span></i><i class="fas fa-pencil-alt fa-5x"></i>
		    </div>
  <!--       </c:if>    로그인 구현후 제거 -->
    </div>
	</section>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>