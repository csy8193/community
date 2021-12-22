<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/write.css">
</head>
	
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<main>
		<div id="main">
			<form action="ninsert" method="post" onsubmit="return boardValidate();">
				<h1 class="board">게시판 이름</h1>
				<h4 class="title">글 제목<span> *</span></h4>
				<input type="text" id="boardTitle" name="boardTitle" placeholder="글 제목을 입력하세요">
				<h4 class="title">글 작성<span> *</span></h4>
				<textarea id="summernote" name="boardContent"></textarea>
				
	            <ul class="image-preview">
                	<!-- <li class="boardImg">
	                    <img>
	                    <div onclick="removeImage(this);">
	                        <i class="fas fa-trash-alt"></i>
	                    </div>
	                </li> -->
	            </ul>
			
				<div class="btns">
				    <button id="reg-btn">등록하기</button>
				    <button id="cancel-btn">취소하기</button>
			    </div>
			</form>
		</div>
	</main>
       
    <script>
		const contextPath = "${contextPath}";
		let index = 0;
		let length = 0;
    
           $(document).ready(function() {
               $('#summernote').summernote({ // summernote를 사용하기 위한 선언
                   height: 400,
				callbacks: { // 콜백을 사용
                       // 이미지를 업로드할 경우 이벤트를 발생
				    onImageUpload: function(files, editor, welEditable) {
		              sendFile(files[0], this)
					},
					onChange: function(contents, $editable){
						const img = $(".note-editable img");
						const board = $(".boardImg");
						
						if(length != img.length){
							if(length < img.length){
								const boardImg = $("<li class='boardImg' onclick='thumbnail(this, "+(index)+");'>");
					            boardImg.append("<img src='"+$(img[length]).attr("src")+"'>");
					            
					            $(".image-preview").append(boardImg);
					            if(index == 0){
					            	$(".boardImg > img").css("border", "3px solid #4facfe");
					            }
					            index += 1;
								
							}else{
								$(board[img.length]).remove();
							}
							length = img.length;
						}
					}
				}
			});
		});
           
	</script>
	<script src="${contextPath}/resources/js/boardWrite.js"></script>
</body>
</html>