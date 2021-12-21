<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp"/>
    <section>
    <div id="event">
        <div id="event-title">
            <span>진행중인 이벤트</span>
        </div>
        <div id="event-search">
            <input type="text" placeholder="이벤트명을 입력하세요"> <button id="event-btn"><i class="fas fa-search"></i></button>
        </div>
        <div class="event-one">
            <div class="event-img">
                <img src="https://cdn.pixabay.com/photo/2015/11/21/05/25/christmas-1054384_960_720.png" alt="">
            </div>
            <div class="event-name">
                    <h1>12월 월간 입점회</h1>
                    <h3>2021.12.01~12-31</h3>
            </div>
        </div>
        <div class="event-one">
            <div class="event-img">
                <img src="https://cdn.pixabay.com/photo/2017/10/16/19/09/cat-2858202_960_720.jpg" alt="">
            </div>
            <div class="event-name">
                    <h1>댕냥이 겨울나기 노하우</h1>
                    <h3>2021.12.01~12-31</h3>
            </div>
        </div>
        <div class="event-one">
            <div class="event-img">
                <img src="https://cdn.pixabay.com/photo/2018/01/01/13/46/design-3054170_960_720.jpg" alt="">
            </div>
            <div class="event-name">
                    <h1>메리크리스마스 특집</h1>
                    <h3>2021.12.01~12-31</h3>
            </div>
        </div>
        <div class="event-one">
            <div class="event-img">
                <img src="https://cdn.pixabay.com/photo/2015/11/21/05/25/christmas-1054384_960_720.png" alt="">
            </div>
            <div class="event-name">
                    <h1>12월 월간 입점회</h1>
                    <h3>2021.12.01~12-31</h3>
            </div>
        </div>
        <div class="event-one">
            <div class="event-img">
                <img src="https://cdn.pixabay.com/photo/2017/10/16/19/09/cat-2858202_960_720.jpg" alt="">
            </div>
            <div class="event-name">
                    <h1>댕냥이 겨울나기 노하우</h1>
                    <h3>2021.12.01~12-31</h3>
            </div>
        </div>
        <div class="event-one">
            <div class="event-img">
                <img src="https://cdn.pixabay.com/photo/2018/01/01/13/46/design-3054170_960_720.jpg" alt="">
            </div>
            <div class="event-name">
                    <h1>메리크리스마스 특집</h1>
                    <h3>2021.12.01~12-31</h3>
                    
            </div>
        </div>
    </div>
</section>
<jsp:include page="../common/footer.jsp"/>
<link rel="stylesheet" href="${contextPath}/resources/css/eventList.css">
</body>
</html>