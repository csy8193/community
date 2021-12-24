$(".imgBtnChange").on("click", function(){
	const img = $(this).children().attr("src");
	$("#photo").attr("src", img);
});

$(document).on("click", "#like_btn", function(){
	if(loginMemberNo==""){
		alert("로그인 후 이용해주세요");
	}
	else{ 
		if(loginMemberNo==memberNo){
				alert("본인의 글은 좋아요를 누를 수 없습니다!");
		}else{
			
			$.ajax({
				url : contextPath + "/pboard/unlike",
				data : {
					"boardNo" : boardNo,
					"loginMemberNo" : loginMemberNo==null? 0 : loginMemberNo,
					"likecount" : likecount
				},
				type :"POST",
				success : function(result){
					if(result>0){
						alert("좋아요를 해제하셨습니다.")
						$(".fa-heart").css("color", "grey");
						$("#like_btn").attr("id","unlike_btn");
						$("#like-area > span:nth-child(2)").text("좋아요 " + result);
					}
				},
				error : function(req, status, error){
		            console.log("좋아요 에러");
		            console.log(req.responseText);
		    	}
				
			});
		}
	}
});


$(document).on("click", "#unlike_btn", function(){
	if(loginMemberNo==""){
		alert("로그인 후 이용해주세요");
	} else {
		 if(loginMemberNo==memberNo){
			alert("본인의 글은 좋아요를 누를 수 없습니다!");
		}else{
			$.ajax({
				url : contextPath + "/pboard/like",
				data : {
					"boardNo" : boardNo,
					"loginMemberNo" : loginMemberNo==null? 0 : loginMemberNo,
					"likecount" : likecount
				},
				type :"POST",
				success : function(result){
					if(result>0){
						alert("좋아요를 누르셨습니다!")
						$(".fa-heart").css("color", "red");
						$("#unlike_btn").attr("id","like_btn");
						$("#like-area > span:nth-child(2)").text("좋아요 " + result);
					}
				},
				error : function(req, status, error){
	                console.log("좋아요 에러");
	                console.log(req.responseText);
	        	}
					
			});
		}
	}
});




