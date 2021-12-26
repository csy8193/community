<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<link rel="stylesheet" href="${contextPath}/resources/css/ranking.css">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<main>
            <div id="main">
                <h2>산책랭킹</h2>
                 <div class="rank-point">
                    <div class="btn-wrap">
                        <a href="${contextPath }/walk/ranking">전체 랭킹</a>
                        <a href="${contextPath }/walk/myPoint">My 포인트</a>
                    </div>
                </div>
                <div class="ranking-wrap">
                    <div class="ranking123">
                        <div class="ranking-list">
                            <img src="${contextPath}${rankList[1].animalImg}">
                            <p><strong>${rankList[1].rank} 위! ${rankList[1].memberId}</strong> / 
                            ${rankList[1].animalName} </p>
                            <p><span>${rankList[1].totalP}</span>point</p>
                        </div>
                        <div class="ranking-list">
                            <img src="${contextPath}${rankList[0].animalImg}">
                            <p><strong>${rankList[0].rank} 위! ${rankList[0].memberId}</strong> / 
                            ${rankList[0].animalName}</p>
                            <p><span>${rankList[0].totalP}</span>point</p>
                        </div>
                        <div class="ranking-list">
                            <img src="${contextPath}${rankList[2].animalImg}">
                            <p><strong>${rankList[2].rank} 위! ${rankList[2].memberId}</strong> / 
                            ${rankList[2].animalName}</p>
                            <p><span>${rankList[2].totalP}</span>point</p>
                        </div>
                    </div>

                    <table>
                    <c:forEach var="downrank" items="${rankList}" varStatus="vst">
                    	<c:if test="${vst.index>2}">
                    		<tr class="else-ranking">
                            <td>${downrank.rank} 위!</td>
                            <td><strong>${downrank.memberId}</strong> / ${downrank.animalName}</td>
                            <td><span>${downrank.totalP}</span>point</td>
                        </tr>
                    	</c:if>
                    </c:forEach>
                    </table>
                </div>
            </div>
        </main>
</body>
</html>