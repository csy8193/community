<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
                            <p><strong>${rankList[1].rank} 위! ${rankList[1].animalName}</strong> / 
                            ${rankList[1].animalCategoryNm} </p>
                            <p><span>${rankList[1].totalP}</span>point</p>
                        </div>
                        <div class="ranking-list">
                            <img src="${contextPath}${rankList[0].animalImg}">
                            <p><strong>${rankList[0].rank} 위! ${rankList[0].animalName}</strong> / 
                            ${rankList[0].animalVariety}</p>
                            <p><span>${rankList[0].totalP}</span>point</p>
                        </div>
                        <div class="ranking-list">
                            <img src="${contextPath}${rankList[2].animalImg}">
                            <p><strong>${rankList[2].rank} 위! ${rankList[2].animalName}</strong> / ${rankList[2].animalCategoryNm}</p>
                            <p><span>${rankList[2].totalP}</span>point</p>
                        </div>
                    </div>

                    <table>
                        <tr class="else-ranking">
                            <td>${rankList[3].rank} 위!</td>
                            <td><strong>${rankList[3].animalName}</strong> / ${rankList[3].animalVariety}</td>
                            <td><span>${rankList[3].totalP}</span>point</td>
                        </tr>
                        <tr class="else-ranking">
                            <td>${rankList[4].rank} 위!</td>
                            <td><strong>${rankList[4].animalName}</strong> / ${rankList[4].animalVariety}</td>
                            <td><span>${rankList[4].totalP}</span>point</td>
                        </tr>
                        <tr class="else-ranking">
                            <td>${rankList[5].rank} 위!</td>
                            <td><strong>${rankList[5].animalName}</strong> / ${rankList[5].animalVariety}</td>
                            <td><span>${rankList[5].totalP}</span>point</td>
                        </tr>
                        <tr class="else-ranking">
                            <td>${rankList[6].rank} 위!</td>
                            <td><strong>${rankList[6].animalName}</strong> / ${rankList[6].animalVariety}</td>
                            <td><span>${rankList[6].totalP}</span>point</td>
                        </tr>
                        <tr class="else-ranking">
                            <td>${rankList[7].rank} 위!</td>
                            <td><strong>${rankList[7].animalName}</strong> / ${rankList[7].animalVariety}</td>
                            <td><span>${rankList[7].totalP}</span>point</td>
                        </tr>
                        <tr class="else-ranking">
                            <td>${rankList[8].rank} 위!</td>
                            <td><strong>${rankList[8].animalName}</strong> / ${rankList[8].animalVariety}</td>
                            <td><span>${rankList[8].totalP}</span>point</td>
                        </tr>
                        <tr class="else-ranking">
                            <td>${rankList[9].rank} 위!</td>
                            <td><strong>${rankList[9].animalName}</strong> / ${rankList[9].animalVariety}</td>
                            <td><span>${rankList[9].totalP}</span>point</td>
                        </tr>
                    </table>
                </div>
            </div>
        </main>
</body>
</html>