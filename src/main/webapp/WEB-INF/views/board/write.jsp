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
           <form action="insert" method="post" enctype="multipart/form-data" role="form" onsubmit="return boardValidate();">
               <h1 class="board">게시판 이름</h1>
               <h4 class="title">글 제목<span> *</span></h4>
               <input type="text" id="boardTitle" name="boardTitle" placeholder="글 제목을 입력하세요">
               <h4 class="title">글 작성<span> *</span></h4>
			<textarea id="summernote"></textarea>

			<div class="btns">
                <button id="reg-btn">등록하기</button>
                <button id="cancel-btn">취소하기</button>
            </div>
            <div id="img-test">
            </div>
		</form>
           </div>
       </main>
       
       
       
       <script>
           $(document).ready(function() {
               $('#summernote').summernote({ // summernote를 사용하기 위한 선언
                   height: 400,
				callbacks: { // 콜백을 사용
                       // 이미지를 업로드할 경우 이벤트를 발생
				    onImageUpload: function(files, editor, welEditable) {
				    	/* console.log(files);
					    $.ajax({
					    	data : files[0],
					    	url : "uploadFile",
					    	type : "POST",
				    	 	contentType : false,
				         	processData : false 
					    }) */
		              sendFile(files[0], this)
					}
				}
			});
		});
	</script>
	<script type="text/javascript">
	const contextPath = "${contextPath}";
        /* summernote에서 이미지 업로드시 실행할 함수 */
	 	      function sendFile(file, editor) {
		        data = new FormData()
		        data.append("img", file)
		
		        $.ajax({
		          data: data,
		          type: "POST",
		          url: "uploadFile",
		          cache: false,
		          contentType: false,
		          enctype: "multipart/form-data",
		          processData: false,
		          success: function (result) {
		        	  
		            $(".note-editable").append('<p><img src="'+contextPath+'/resources/images/board/'+result+'"></p>');
		          },
		        })
		      }
	</script>
</body>
</html>