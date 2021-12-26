<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/pic-detail.css">
<title>사진 상세 게시판</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<c:forEach items="${board.PBoardImgList}" var="img">
		<c:choose>
			<c:when test="${img.imgLevel == 0}">
				<c:set var="img0" value="${contextPath}${img.imgPath}${img.imgName}" />
			</c:when>
			<c:when test="${img.imgLevel == 1}">
				<c:set var="img1" value="${contextPath}${img.imgPath}${img.imgName}" />
			</c:when>
			<c:when test="${img.imgLevel == 2}">
				<c:set var="img2" value="${contextPath}${img.imgPath}${img.imgName}" />
			</c:when>
			<c:when test="${img.imgLevel == 3}">
				<c:set var="img3" value="${contextPath}${img.imgPath}${img.imgName}" />
			</c:when>
			<c:when test="${img.imgLevel == 4}">
				<c:set var="img4" value="${contextPath}${img.imgPath}${img.imgName}" />
			</c:when>
			<c:when test="${img.imgLevel == 5}">
				<c:set var="img0" value="${contextPath}${img.imgPath}${img.imgName}" />
			</c:when>
		</c:choose>
	</c:forEach>

	<div id="container">
		<div id="image-area">
			<div id="img-wrap">
				<img id="photo" src="${img0}">
			</div>
			<div id="img2-wrap">
				<div id="count">
					<div id="count-area">
						<ul class="ul-count">

							<li class="img-count">
								<button class="imgBtnChange">
									<img src="${img0}">
								</button>
							</li>
							<c:if test="${!empty img1 }">
								<li class="img-count">
									<button class="imgBtnChange">
										<img src="${img1}">
									</button>
								</li>
							</c:if>
							<c:if test="${!empty img2 }">
								<li class="img-count">
									<button class="imgBtnChange">
										<img src="${img2}">
									</button>
								</li>
							</c:if>
							<c:if test="${!empty img3 }">
								<li class="img-count">
									<button class="imgBtnChange">
										<img src="${img3}">
									</button>
								</li>
							</c:if>
							<c:if test="${!empty img4 }">
								<li class="img-count">
									<button class="imgBtnChange">
										<img src="${img4}">
									</button>
								</li>
							</c:if>
							<c:if test="${!empty img5 }">
								<li class="img-count">
									<button class="imgBtnChange">
										<img src="${img5}">
									</button>
								</li>
							</c:if>

						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="text-area">
			<div id="text-header">
				<div id="text-title">
					<div class="user-img"
						style="background : url('${contextPath}${board.animalMainImgPath}') center center no-repeat; background-size: cover;"></div>
					<div style="display: flex; justify-content: space-between;">
						<p>${board.memberNm}</p>
						<div class="updateDelete">
							<c:if test="${board.memberNo == loginMember.memberNo}">
								<button onclick="updateForm();">수정</button>
								<button onclick="deleteForm();">삭제</button>
								<form action="#" method="POST" name="requestForm">
									<input type="hidden" name="boardCd" value="${param.boardCd}">
									<input type="hidden" name="boardNo" value="${param.no}">
									<input type="hidden" name="cp" value="${param.cp}">
								</form>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div id="contend-area">
				<div id="contend">
					<div style="word-break: break-all;">${board.boardContent}</div>
					<div id="like-area">

						<c:choose>
							<c:when test="${board.likeDone == 'true'}">
								<i class="far fa-heart" id="like_btn"
									style="color: red; backgroundColor: red"></i>
							</c:when>

							<c:otherwise>
								<i class="far fa-heart" id="unlike_btn"></i>
							</c:otherwise>
						</c:choose>

						<span>좋아요 ${board.likecount}</span> <span class="time">${board.createDt}</span>
					</div>
				</div>

				<!-- 댓글 출력 부분 -->
				<div class="text-comment">
					<ul id="all-reply">
					</ul>
				</div>
			</div>
			<div id="write-area">
				<div id="txt-box">
					<textarea id="commentArea" style="resize: none;"
						name="reply_contents" cols="30" rows="10"
						placeholder="댓글을 작성하려면 위트에 로그인 해주세요."></textarea>
				</div>
				<div id="click_btn">
					<button id="addReply" onclick="addReply();">등록</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp" />
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script>
	const contextPath = "${contextPath}";
	// 로그인한 회원의 회원 번호, 비로그인 시 "" (빈문자열)
	const loginMemberNo = "${loginMember.memberNo}";
	const memberNo = "${board.memberNo}"
	// 현재 게시글 번호
	const boardNo = "${board.boardNo}";
	const likedone = "${board.likeDone}";
	const likecount = "${board.likecount}";
	// 수정 전 댓글 요소를 저장할 변수 (댓글 수정 시 사용)
	let beforeReplyRow;
	let beforeContent;
	let textarea;
	
	function updateForm(){
		document.requestForm.action = contextPath + "/board/pupdateForm";
		document.requestForm.method = "POST";
		document.requestForm.submit();
	}
	
	function deleteForm(){
		document.requestForm.action = contextPath + "/board/pdelete";
		document.requestForm.method = "POST";
		document.requestForm.submit();
	}

</script>
<script src="${contextPath}/resources/js/pBoardReply.js"></script>
<script src="${contextPath}/resources/js/pBoard.js"></script>

</html>