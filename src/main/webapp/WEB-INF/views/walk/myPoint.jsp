<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
		<link rel="stylesheet" href="${contextPath}/resources/css/myPoint.css">
		<link rel="stylesheet" href="${contextPath}/resources/css/calendar.css">
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
					    <h1>달력</h1>
    <button onclick="prev()">저번달</button>
    <button onclick="next()">저번달</button>
    <h2>이번달 (<span id="today-month"></span> )</h2>
    <table width=900 height=800>
        <thead>
            <tr>
                <th>일</th>
                <th>월</th>
                <th>화</th>
                <th>수</th>
                <th>목</th>
                <th>금</th>
                <th>토</th>
            </tr>
        </thead>
        <tbody id="month-day">
        </tbody>
    </table>

                </div>
                    
            </div>
    
        </main>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
 <script>
 	const contextPath = "${contextPath}";
 	const loginMemberNo = 4;
 		//"${sessionScope.MemberNo}"
 </script>
 <script src="${contextPath}/resources/js/calendar.js"></script> 
</body>
</html>