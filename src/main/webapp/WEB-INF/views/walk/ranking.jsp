<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet" href="${contextPath}/resources/css/ranking.css">
</head>
<body>
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
                            <img src="https://web-wit.s3.ap-northeast-2.amazonaws.com/images/boardNews/3230/news1639791120_1.jpeg">
                            <p><strong>치즈</strong> / 강아지</p>
                            <p><span>11111</span>point</p>
                        </div>
                        <div class="ranking-list">
                            <img src="https://web-wit.s3.ap-northeast-2.amazonaws.com/images/boardNews/3223/news1639653896_1.jpeg">
                            <p><strong>치즈</strong> / 강아지</p>
                            <p><span>19283</span>point</p>
                        </div>
                        <div class="ranking-list">
                            <img src="https://web-wit.s3.ap-northeast-2.amazonaws.com/images/boardNews/3212/news1639324436_1.jpg">
                            <p><strong>치즈</strong> / 강아지</p>
                            <p><span>11111</span>point</p>
                        </div>
                    </div>

                    <table>
                        <tr class="else-ranking">
                            <td>4</td>
                            <td><strong>뽀삐</strong> / 강아지</td>
                            <td><span>9987</span>point</td>
                        </tr>
                        <tr class="else-ranking">
                            <td>5</td>
                            <td><strong>뽀삐</strong> / 강아지</td>
                            <td><span>9987</span>point</td>
                        </tr>
                        <tr class="else-ranking">
                            <td>6</td>
                            <td><strong>뽀삐</strong> / 강아지</td>
                            <td><span>9987</span>point</td>
                        </tr>
                        <tr class="else-ranking">
                            <td>7</td>
                            <td><strong>뽀삐</strong> / 강아지</td>
                            <td><span>9987</span>point</td>
                        </tr>
                    </table>


                </div>
                    
            </div>
    
        </main>
</body>
</html>