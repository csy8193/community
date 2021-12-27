<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp" />
<section>
	<div id="event">
		<div id="event-title">
			<span>진행중인 이벤트</span>
		</div>
		<div id="event-search">
			<input type="text" placeholder="이벤트명을 입력하세요">
			<button id="event-btn">
				<i class="fas fa-search"></i>
			</button>
		</div>
		<div style="display: flex; flex-wrap: wrap;">
			<c:choose>
				<c:when test="${empty boardList}">
					<p>게시글이 존재하지 않습니다</p>

				</c:when>
				<c:otherwise>
					<c:forEach var="board" items="${boardList}">
						<div class="event-one">
							<div class="event-img"
								onclick="location.href='${contextPath}/nboard/view?cp=${pagination.currentPage}&boardNo=${board.boardNo}&boardCd=${boardCd}'">
								<c:choose>
									<c:when test="${empty board.boardPicPath}">
										<img
											src="${contextPath}/resources/images/main/basis-profile-img.png">
									</c:when>
									<c:otherwise>
										<img src="${board.boardPicPath}">
									</c:otherwise>
								</c:choose>
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
	</div>
	<div class="page-number">
		<ul class="page-ul">
			<c:if test="${pagination.startPage != 1}">
				<li><a href="event?cp=1"><i
						class="fas fa-angle-double-left"></i></a></li>
				<li><a href="event?cp=${pagination.prevPage}"><i
						class="fas fa-angle-left"></i></a></li>
			</c:if>

			<c:forEach begin="${pagination.startPage}"
				end="${pagination.endPage}" step="1" var="i">
				<c:choose>
					<c:when test="${i == pagination.currentPage}">
						<li
							style="border: 1px solid #4facfe; border-radius: 50%; background-color: #4facfe;">
							<a style="color: white;">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="event?cp=${i}">${i}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>


			<c:if test="${pagination.endPage != pagination.maxPage}">
				<li><a
					href="event?cp=${pagination.nextPage}"><i
						class="fas fa-angle-right"></i></a></li>
				<li><a
					href="event?cp=${pagination.maxPage}"><i
						class="fas fa-angle-double-right"></i></a></li>
			</c:if>
		</ul>
	</div>
	<c:if test="${!empty loginMember}">
		<div id="write"
			onclick="location.href='${contextPath}/board/nwrite?boardCd=${boardCd}&cp=${pagination.currentPage }';">
			<i class="fas fa-pen-square"></i>
		</div>
	</c:if>
</section>
<jsp:include page="../common/footer.jsp" />
<link rel="stylesheet" href="${contextPath}/resources/css/eventList.css">
</body>
</html>