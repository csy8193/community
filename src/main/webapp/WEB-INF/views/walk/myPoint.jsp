<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
		<link rel="stylesheet" href="${contextPath}/resources/css/myPoint.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<main>
            <div id="main">
                <h2>My 포인트</h2>
                <div class="rank-point">
                    <div class="btn-wrap">
                        <a href="${contextPath }/walk/ranking">전체 랭킹</a>
                        <a href="${contextPath }/walk/myPoint">My 포인트</a>
                    </div>
                </div>
                <div class="welcome-text">
                    <p>반가워요, <strong>뽀삐</strong>주인님!</p>
                    <p>아직 모으신 산책 포인트가 없어요</p>
                    <p>산책 포인트를 모아 매달 새로워지는 상품과 교환하세요!</p>
                    <a href="#">자세히 보기</a>
                </div>

                <div class="progress">
                    <progress value="0" max="4"></progress>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div class="progress-level">
                        <div>1.Lv</div>
                        <div>2.Lv</div>
                        <div>3.Lv</div>
                        <div>4.Lv</div>
                        <div>5.Lv</div>
                    </div>
                </div>

                <div class="my-status">
                    <div>
                        <h2>산책레벨</h2>
                        <p>1.Lv</p>
                    </div>
                    <div>
                        <h2>산책포인트</h2>
                        <p>0P</p>
                    </div>
                    <div>
                        <h2>전체 랭킹</h2>
                        <p>536<span>위</span></p>
                    </div>
                </div>

                <div class="calendar">


                </div>
                    
            </div>
    
        </main>
</body>
</html>