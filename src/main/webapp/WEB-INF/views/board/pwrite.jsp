<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	    	<form action="pinsert"  method="post" enctype="multipart/form-data" role="form">
		        <h1 class="board">게시판 이름</h1>
		        <div class="image-reg">
		            <h4 class="title">이미지 등록<span> *</span></h4>
		            <button type="button" id="image-upload">이미지 업로드</button>
		            <ul class="image-preview">
		                <!-- <li class="boardImg">
		                    <img>
		                    <div onclick="removeImage(this);">
		                        <i class="fas fa-trash-alt"></i>
		                    </div>
		                </li> -->
		            </ul>
		        </div>
		        
		        <div id="fileArea">
				</div>
				
		        <h4 class="title">글 작성<span> *</span></h4>
		        <textarea id="write-content" name="boardContent" placeholder="내용을 입력해주세요"></textarea>
		        <div class="btns">
		            <button id="reg-btn">등록하기</button>
		            <button id="cancel-btn">취소하기</button>
		        </div>
	    	</form>
	    </div>
	</main>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		let index = 0;
	
		$(function() {
			$("#image-upload").on("click", function() {
						// 현재 클릭된 요소가 .boardImg 중 몇 번째 인덱스인지 반환
					$("#fileArea").append('<input type="file" id="input-img'+index+'" name="img'+index+'" onchange="loadImg(this)">')
					console.log("#input-img"+index);
					$("#input-img"+index).click();
					index++;
				// 타입이 file인 요소 중 몇번째 인덱스 요소를 선택하여 클릭해라
			});
	
		});
		
		function loadImg(value) {
			// 매개변수 value == 클릭된 input 요소
		

			// 파일이 선택된 경우 true
			if (value.files && value.files[0]) {
				var reader = new FileReader();
				// 자바스크립트 FileReader
				// 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 읽을 파일을 가리키는 File 혹은 Blob객체를 이용해 파일의 내용을 읽고 사용자의 컴퓨터에 저장하는 것을 가능하게 해주는 객체

				// 선택된 파일 읽기 시작
				reader.readAsDataURL(value.files[0]);
				// FileReader.readAsDataURL()
				// 지정된의 내용을 읽기 시작합니다. Blob완료되면 result속성 data:에 파일 데이터를 나타내는 URL이 포함 됩니다.

				// FileReader.onload
				// load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료 되었을 때마다 발생합니다.
				reader.onload = function(e) {
					// console.log(e.target.result);
					// e.target.result
					// -> 파일 읽기 동작을 성공한 객체에(fileTag) 올라간 결과(이미지 또는 파일)
		            const boardImg = $("<li class='boardImg'>")
		            const div = $("<div onclick='removeImage(this, "+(index-1)+");'>");
		            div.append("<i class='fas fa-trash-alt'></i>");
		            boardImg.append("<img src='"+e.target.result+"'>", div);
		            
		            $(".image-preview").append(boardImg);
				}

			}
		}
		
		function removeImage(value, idx){
			$(value).parent().remove();
			$("#input-img"+idx).remove();

		}
		/* $(".boardImg > div").on("click", function(){
			$(this).prev().removeAttr("src");
			$(this).parent().css("display", "none");
			count -= 1;
		}) */
	
	</script>
</body>
</html>