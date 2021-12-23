<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	 <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/pic-board.css">
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<title>사진 게시판 목록</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
    <div id ="container">
        <div id="title-header">
            <h1>자랑하기</h1>
        </div>
        
        <div id ="content">
        	<%-- 게시글 목록 출력 --%>
			<c:choose>
				<c:when test="${empty boardList}">
				<%-- 조회된 게시글 목록이 없을 때 --%>
					없다 없어!
				</c:when>
				<c:otherwise>
					<c:forEach items="${boardList}" var="board">
					
					<%-- 조회된 게시글 목록이 있을 때 --%>
						<div class="item">
			                <div class="item-header">
			                    <div class="user-img"></div>
			                    <p>${board.memberNm}</p>
			                </div>
			                <div class="item-pic">
			                	<c:if test="${board.PBoardImgList[0].imgLevel == 0 }">
			                	<div class="item-img" onclick="location.href='${contextPath}/pboard/view?no=${board.boardNo}&cp=${pagination.currentPage}'" style="background : url('${contextPath}${board.PBoardImgList[0].imgPath}${board.PBoardImgList[0].imgName}') center center no-repeat; background-size:cover;"></div>
			                
			                	</c:if>
			                    </div>
			                <div class="item-content">
			                    <div class="click-btn">
			                        <ul>
			                            <li>
			                                <img class="likeBtn" src="https://www.witkorea.kr/web/img/ico_list_like.png">
			                                <span class="likeCnt">${board.likecount}</span>
			                            </li>
			                            <li>
			                                <img class="likeBtn" src="https://www.witkorea.kr/web/img/ico_comment.png">
			                                <span class="likeCnt">${board.replycount}</span>
			                            </li>
			                        </ul>
			                        <span>${board.createDt}</span>
			                    </div>
			                </div>
			                <div class="item-footer">
			                    <p>${board.boardContent}</p>
			                </div>
			            </div>
					</c:forEach>
				</c:otherwise>
			</c:choose>        	
        </div>
        
        <%---------------------- Pagination ----------------------%>
		
		<div class="my-5">
			<ul class="pagination">
				
				
				<c:if test="${pagination.startPage != 1 }">
					<li><a class="page-link" href="list?cp=1">&lt;&lt;</a></li>
					<li><a class="page-link" href="list?cp=${pagination.prevPage}">&lt;</a></li>
				</c:if>
				
				<%-- 페이지네이션 번호 목록 --%>
				<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1"  var="i">
					<c:choose>
						<c:when test="${i == pagination.currentPage}">
							<li><a class="page-link" style="color:black; font-weight:bold;">${i}</a></li>   
						</c:when>
						
						<c:otherwise>
							<li><a class="page-link" href="list?cp=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:if test="${pagination.endPage != pagination.maxPage }">
					<li><a class="page-link" href="list?cp=${pagination.nextPage}">&gt;</a></li>
					<li><a class="page-link" href="list?cp=${pagination.maxPage }">&gt;&gt;</a></li>
				</c:if>
			</ul>
		</div>
        
        
    </div>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>