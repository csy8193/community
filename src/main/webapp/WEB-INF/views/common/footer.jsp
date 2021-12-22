<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Footer -->
<footer>
	 <div id="footer">

     </div>
</footer>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


<script>
	
	// 마이페이지 제이쿼리
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
	
	// 헤더 검색창 제이쿼리
	$("#search").on("click",function(){
	     if($("#search-wrap").css("display") == "none"){// #img4가 화면에 안보이는 상태
	         $(this).addClass("borderhover");
	         $("#search-wrap").show();
	     }else{
	         //$("#search-wrap").hide();
	         $(this).removeClass("borderhover");
	     }
	 });
	
</script>
