<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>검색결과</title>
<link rel="stylesheet" href="${contextPath}/resources/css/search.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<main>
		<div id="main">
			<div id="main-search-wrap">
                <div id="search-wrap2">
                	<form action="${contextPath}/board/search">
	                    <button id="search-btn"><img src="${contextPath}/resources/images/main/search.png"></button>
	                    <input type="text" name="main-search" id="main-search" placeholder="관심있는 내용을 검색해보세요!" value="${search}">
                	</form>
                </div>
            </div>
			<h2 class="search-result">"${search}"에 대한 통합검색 결과입니다</h2>
			
			
			<div>
				<c:forEach var="board" items="${boardList}">
					<div class="search-list">
					<c:choose>
						<c:when test="${empty board.boardPicPath && empty board.boardPicName}">
						
						</c:when>
						<c:when test="${!empty board.boardPicPath && empty board.boardPicName}">
							<img src="${contextPath}${board.boardPicPath}">
						</c:when>
						<c:otherwise>
							<img src="${contextPath}${board.boardPicPath}${board.boardPicName}">
						</c:otherwise>					
					</c:choose>
						<div class="search-board">
							<span>${board.boardName}</span>
							<c:choose>
								<c:when test="${empty board.boardTitle}">
									<div onclick="location.href='${contextPath}/pboard/view?no=${board.boardNo}&boardCd=${board.boardCode}'">
										<h3>
											<c:if test="${!empty board.boardTitle}">${board.boardTitle}</c:if>
										</h3>
										<p>${board.boardContent}</p>
									</div>
								</c:when>
								<c:otherwise>
									<div onclick="location.href='${contextPath}/nboard/view?boardNo=${board.boardNo}&boardCd=${board.boardCode}'">
										<h3>
											${board.boardTitle}
										</h3>
										<p>${board.boardContent}</p>
									</div>
								</c:otherwise>
							</c:choose>
							<ul class="board-user-info">
								<li><img src="${contextPath}${board.animalProfile}"></li>
								<li>${board.memberName}</li>
								<li>·</li>
								<li style="color: #989696;"><i class="far fa-calendar-alt"> </i></li>
								<li style="color: #989696;">${board.createDate}</li>
								<li>·</li>
								<li style="color: #989696;"><i class="far fa-eye"></i></li>
								<li style="color: #989696;">${board.readCount}</li>
							</ul>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="page-number">
                  <ul class="page-ul">
                  	<c:if test="${pagination.startPage != 1}">
                       <li>
                           <a href="${contextPath}/board/search?main-search=${search}&cp=1"><i class="fas fa-angle-double-left"></i></a>
                       </li>
                       <li>
                           <a href="${contextPath}/board/search?main-search=${search}&cp=${pagination.prevPage}"><i class="fas fa-angle-left"></i></a>
                       </li>
                  	</c:if>
                  	
                  	<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1" var="i">
                  		<c:choose>
                  			<c:when test="${i == pagination.currentPage}">
                   			<li style="border: 1px solid #4facfe; border-radius: 50%; background-color: #4facfe;">
                             <a style="color: white;">${i}</a>
                         </li>
                  			</c:when>
                        <c:otherwise>
                   			<li>
                             <a href="${contextPath}/board/search?main-search=${search}&cp=${i}">${i}</a>
                         </li>
                   		</c:otherwise>
                  		</c:choose>
                  	</c:forEach>
                      
                      
                      <c:if test="${pagination.endPage != pagination.maxPage}">
                       <li>
                           <a href="${contextPath}/board/search?main-search=${search}&cp=${pagination.nextPage}"><i class="fas fa-angle-right"></i></a>
                       </li>
                       <li>
                           <a href="${contextPath}/board/search?main-search=${search}&cp=${pagination.maxPage}"><i class="fas fa-angle-double-right"></i></a>
                       </li>
                      </c:if>
                  </ul>
              </div>
		</div>
	</main>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>