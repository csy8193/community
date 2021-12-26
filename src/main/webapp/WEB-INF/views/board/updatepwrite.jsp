<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet" href="${contextPath}/resources/css/pwrite.css">
</head>
<body>
	
	<jsp:include page="../common/header.jsp"></jsp:include>
	<main>
	    <div id="main">
	    	<form action="pupdate"  method="post" enctype="multipart/form-data" role="form" onsubmit="return insertValidate();">
		        <h1 class="board">
		        	${board.boardName}
					<input type="hidden" name="boardCd" value="${board.boardCode}">
					<input type="hidden" name="boardNo" value="${board.boardNo}">
					<input type="hidden" name="cp" value="${cp}" >
		        </h1>
		        <div class="image-reg">
		            <h4 class="title">이미지 등록<span> *</span></h4>
		            <button type="button" id="image-upload">이미지 업로드</button>
		            <ul class="image-preview">
		            <c:if test="${!empty boardImage}">
		            	<c:forEach var="img" items="${boardImage}" varStatus="status">
			            	<li class="boardImg" onclick="thumbnail(this, ${img.imgLevel})">
			                    <img src="${contextPath}${img.imgPath}${img.imgName}">
			                    <div onclick="removeImage(this, ${img.imgLevel});">
			                        <i class="fas fa-trash-alt"></i>
			                    </div>
			                </li>
			                <c:if test="${status.last}">
			                	<c:set var="last" value="${img.imgLevel}"></c:set>
			                </c:if>
			            </c:forEach>
		            </c:if>
		            
	                <!-- <li class="boardImg">
		                    <img>
		                    <div onclick="removeImage(this);">
		                        <i class="fas fa-trash-alt"></i>
		                    </div>
		                </li> -->
		            </ul>
		        </div>
		        
		        <div id="fileArea">
			        <c:forEach var="img" items="${boardImage}">
		            	<input type="text" id="input-img${img.imgLevel}" name="img${img.imgLevel}" onchange="loadImg(this)" value="${img.imgPath}${img.imgName}">
		            </c:forEach>
		        	<!-- <input type="file" id="input-img'+index+'" name="img'+index+'" onchange="loadImg(this)"> -->
				</div>
				
		        <h4 class="title">글 작성<span> *</span></h4>
		        <textarea id="write-content" name="boardContent" placeholder="내용을 입력해주세요">${board.boardContent}</textarea>
		        <div class="btns">
		            <button id="reg-btn">수정하기</button>
		            <button type="button" id="cancel-btn" onclick="history.back();">취소하기</button>
		        </div>
	    	</form>
	    </div>
	</main>
	
	<jsp:include page="../common/footer.jsp"/>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
	
		(function(){
			const boardImg = $(".boardImg");
			$(boardImg[0]).children("img").css("border", "3px solid #4facfe");
		})()
		
		let index = (${last}+1);
	
		$(function() {
			$("#image-upload").on("click", function() {
				if($(".boardImg").length == 5){
					alert("이미지는 5개까지만 업로드 가능합니다.");
				}else{
					$("#fileArea").append('<input type="file" id="input-img'+index+'" name="img'+index+'" onchange="loadImg(this)">')
					$("#input-img"+index).click();
					index++;
				}
			});
	
		});
		
		function loadImg(value) {

			if (value.files && value.files[0]) {
				var reader = new FileReader();
				reader.readAsDataURL(value.files[0]);
				reader.onload = function(e) {
		            const boardImg = $("<li class='boardImg' onclick='thumbnail(this, "+(index-1)+");'>");
		            const div = $("<div onclick='removeImage(this, "+(index-1)+");'>");
		            div.append("<i class='fas fa-trash-alt'></i>");
		            boardImg.append("<img src='"+e.target.result+"'>", div);
		            
		            $(".image-preview").append(boardImg);
		            if((index-1) == 0){
		            	$(".boardImg > img").css("border", "3px solid #4facfe");
		            }
				}

			}
		}
		
		function removeImage(value, idx){
			$(value).parent().remove();
			$("#input-img"+idx).remove();

		}
		
		function thumbnail(value, idx){
			const input = $("#fileArea > input");
			
			for(let i=0; i<input.length; i++){
				$(input[i]).attr("name", "img"+(i+1))
			}
			
			$("#input-img"+idx).attr("name", "img0");
			
			
			$(".boardImg").children("img").css("border", "3px solid #e7e7e7");
			$(value).children("img").css("border", "3px solid #4facfe");
		}
		
		
		function insertValidate(){
			if($("#write-content").val().trim().length == 0){
				alert("내용을 입력해주세요.");
				return false;
			}
			
			if($(".boardImg").length == 0){
				alert("이미지를 첨부해주세요.");
				return false;
			}
			
			if($("[name=img0]").length == 0){
				alert("썸네일을 지정해주세요.");
				return false;
			}
		}
		
	
	</script>
</body>
</html>