<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${contextPath}/resources/css/mypage.css">

<!-- Footer -->
<footer>
	<div id="footer-wrap">
            <div id="footer">
            	<div class="left-float">
	            	<h1><img src="${contextPath}/resources/images/main/footer-logo.png"></h1>
	                <ul id="footer-nav">
	                    <li><a href="#">회사소개</a></li>
	                    <li><a href="#">이용약관</a></li>
	                    <li><a href="#">개인정보처리방침</a></li>
	                    <li><a href="#">운영 정책</a></li>
	                    <li><a href="#">고객센터</a></li>
	                    <li><a href="#">회원 혜택</a></li>
	                </ul>
	                <div id="text">
	                    <p>회사명:(주)투개더 | 대표 : 아무개 | 주소: 경기도 아무시 아무구 아무로 1234로 74 <br>
	                        사업자 등록번호 : 1215-2545151[사업자정보확인] | 통신 판매업 신고번호 : 2021- 아무아무B-1111 | 개인정보책임자 : 아무개 </p>
	                    <p>Copyright 2021. WIT. All rights reserved</p>
	                </div>
            	</div>
               
                <div id="social-wrap" class="right-float">
	            	<ul id="social-nav">
	            		<li><a href="#"><img src="${contextPath}/resources/images/main/facebook.png"></a></li>
	            		<li><a href="#"><img src="${contextPath}/resources/images/main/twitter.png"></a></li>
	            		<li><a href="#"><img src="${contextPath}/resources/images/main/instar.png"></a></li>
	            	</ul>
	            </div>
            </div>
            
        </div>
</footer>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


<script>
	
	// 마이페이지 반려동물 등록 모달창 제이쿼리
	const modal = document.getElementById("modal");
	
	document.getElementById("plus").addEventListener("click",function(){
	    modal.style.display = "flex";
	});
	
	modal.addEventListener("click", e => {
	    const evTarget = e.target;
	
	    if(evTarget.classList.contains("modal")) {
	        modal.style.display = "none";
	    }
	
	});


	
	// 헤더 검색창 서브 메뉴 제이쿼리
	$("#search").on("click",function(){
	     if($("#search-wrap").css("display") == "none"){// #img4가 화면에 안보이는 상태
	         $(this).addClass("borderhover");
	         $("#search-wrap").show();
	     }else{
	         //$("#search-wrap").hide();
	         $(this).removeClass("borderhover");
	     }
	 });

	// 서브바 누르면 밑에 게시판 리스트 나오게 하는 제이쿼리
	$(document).ready(function(){

		$('.btn').each(function(i){

			$(this).click(function(){
				
				$('.sub').hide();
				$('.sub').eq(i).show();
			});

		});

	});
	
	// 수정모달창 제이쿼리
	function updateModal(){
		
		   $(".updateModal").css("display", "flex");
		    
		    $(".updateModal").on("click", function(e){
		    	const evTarget = e.target;
		    	
		    	
		    	
		    	if(evTarget.classList.contains("modal")) {
		            this.style.display = "none";
		        }
		    })
				
		}


</script>
