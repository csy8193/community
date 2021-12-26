<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/write.css">
</head>
	
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<main>
		<div id="main">
			<form action="ninsert" method="post" onsubmit="return insertValidate();">
				<h1 class="board">
					${boardName}
					<input type="text" name="boardCd" value="${boardCd}">
				</h1>
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
	            
	            <div id="fileArea">
				</div>
			
				<div class="btns">
				    <button id="reg-btn">등록하기</button>
				    <button type="button" id="cancel-btn" onclick="history.back()">취소하기</button>
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
						const boardimg = $(".boardImg > img");
						
						// 현재 textarea에 있는 이미지수와 썸네일 부분의 이미지 개수가 다를경우
						if(length != img.length){
							
							// teatarea에 있는 이미지 수가 더 많은 경우(이미지를 추가한경우)
							if(length < img.length){
								$("#fileArea").empty();
								$(".image-preview").empty();
								
								// 썸네일 부분에 이미지를 추가
								for(let i=0; i<img.length; i++){
									const boardImg = $("<li class='boardImg' onclick='thumbnail(this);'>");
						            boardImg.append("<img src='"+$(img[i]).attr("src")+"'>");
						            
						            $(".image-preview").append(boardImg);
								}
								/* const boardImg = $("<li class='boardImg' onclick='thumbnail(this);'>");
					            boardImg.append("<img src='"+$(img[length]).attr("src")+"'>");
					            
					            $(".image-preview").append(boardImg); */
					            
					            /* // 첫번째로 첨부한 이미지일 경우 테두리 색을 변경해준다
					            if(index == 0){
					            	$(".boardImg > img").css("border", "3px solid #4facfe");
						            $("#fileArea").append('<input type="text" id="input-img" value="'+$(img[length]).attr("src")+'" name="input-img">');
					            }
					            
					            index += 1; */
								
					            
					        // 썸네일에 있는 이미지 수가 더 많은 경우(이미지를 삭제한경우)
							}else{
								
								// 썸네일에 있는 이미지수 만큼 반복문
								for(let i=0; i<board.length; i++){
									
									// 썸네일의 src와 textarea의 src가 다른경우
									if($(img[i]).attr("src") != $(boardimg[i]).attr("src")){
										
										// textarea의 src와 썸네일로 설정한 값이 같을경우 썸네일 값을 삭제
										if($(boardimg[i]).attr("src") == $("#fileArea > input").val()){
											$("#fileArea").empty();
										}
										$(board[i]).remove();
										break;
									}
								}
								/* $(board[img.length]).remove(); */
								
								
							}
							length = img.length;
						}
					}
				}
			});
		});
           
        function thumbnail(value){
        	
			$(".boardImg").children("img").css("border", "3px solid #e7e7e7");
			$(value).children("img").css("border", "3px solid #4facfe");
			$("#fileArea").empty();
			$("#fileArea").append('<input type="text" id="input-img" value="'+$(value).children("img").attr("src")+'" name="input-img">');
			
        }
        
        function insertValidate(){
			if($("#boardTitle").val().trim().length == 0){
				alert("제목을 입력해주세요.");
				return false;
			}
			
			if($("#summernote").val().replaceAll("&nbsp;", "").replaceAll("<br>", "").replaceAll("<p>", "").replaceAll("</p>", "").trim().length == 0){
				alert("내용을 입력해주세요.");
				return false;
			}
			
			if($(".boardImg").length >= 1){
				if($("#fileArea > *").length == 0){
					alert("썸네일을 지정해주세요.");
					return false;
				}
			}
		}
           
	</script>
	<script src="${contextPath}/resources/js/boardWrite.js"></script>
</body>
</html>