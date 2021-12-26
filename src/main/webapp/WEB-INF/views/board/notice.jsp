<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet" href="${contextPath}/resources/css/notice.css">
</head>
<body>

	<jsp:include page="../common/header.jsp"></jsp:include>
	<main>
            <div id="main">
                <div class="main-header">
                    <a href="#"><h2>자주하는 질문</h2></a>
                    <h2> | </h2>
                    <a href="#"><h2>공지사항</h2></a>
                </div>
                <div class="main-content">
                    <table class="notice-table">
	                    <c:choose>
	                    	<c:when test="${empty boardList}">
		                    	<tr>
		                    		<td colspan="3">게시글이 존재하지 않습니다.</td>
		                    	</tr>
	                    	</c:when>
	                    	<c:otherwise>
		                        <tr>
		                            <th class="table-hd table-head1">글번호</th>
		                            <th class="table-hd table-head2">제목</th>
		                            <th class="table-hd table-head3">작성일</th>
		                        </tr>
		                        <c:forEach var="board" items="${boardList}">
		                        	<tr>
			                            <td class="table-hd table-data1">${board.boardNo}</td>
			                            <td class="table-hd table-data2"><a href="${contextPath}/nboard/view?cp=${pagination.currentPage}&boardNo=${board.boardNo}&boardCd=${boardCd}">${board.boardTitle}</a></td>
			                            <td class="table-hd table-data3">${board.createDate}</td>
			                        </tr>
		                        </c:forEach>
	                    	</c:otherwise>
	                    </c:choose>
                    </table>
                    <div class="page-number">
                        <ul class="page-ul">
                        	<c:if test="${pagination.startPage != 1}">
	                            <li>
	                                <a href="notice?cd=110&cp=1"><i class="fas fa-angle-double-left"></i></a>
	                            </li>
	                            <li>
	                                <a href="notice?cd=110&cp=${pagination.prevPage}"><i class="fas fa-angle-left"></i></a>
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
			                                <a href="${contextPath}/board/notice?cd=110&cp=${i}">${i}</a>
			                            </li>
	                        		</c:otherwise>
                        		</c:choose>
                        	</c:forEach>
                            
                            
                            <c:if test="${pagination.endPage != pagination.maxPage}">
	                            <li>
	                                <a href="${contextPath}/board/notice?cd=110&cp=${pagination.nextPage}"><i class="fas fa-angle-right"></i></a>
	                            </li>
	                            <li>
	                                <a href="${contextPath}/board/notice?cd=110&cp=${pagination.maxPage}"><i class="fas fa-angle-double-right"></i></a>
	                            </li>
                            </c:if>
                        </ul>
                    </div>
                </div>

            </div>
	   		<c:if test="${!empty loginMember}">
			    <div id="write" onclick="location.href='${contextPath}/board/nwrite?boardCd=${boardCd}&cp=${pagination.currentPage }';">
			        <i class="fas fa-pen-square"></i>
			    </div>
	 		</c:if>
            
        </main>
        
</body>
</html>