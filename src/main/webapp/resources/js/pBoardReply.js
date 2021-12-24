/*function selectReplyList(){
	
	$.ajax({
		url:contextPath + "/reply/select",
		data: {"boardNo":boardNo},
		dataType:"Json",
		success: function(rList){
			console.log(rList);
			$("#commentArea").empty();
			
			$.each(rList, function(index, reply){
				
				const li = $('<li class="mine">');
				
				const div = $('<div class="profile-img">');
				
				const div2 = $('<div class="user-img2">');
				
				div.append(div2);
				
				const div3 = $('<div class="comment-wrapper">');
				
				const div4 = $('<div class="profile">');
				
				const div5 = $('<div class="comment">');
				
				const div6 = $('<div class="comment-text">');
				
				const pre = $('<pre id="reply-text">').html(reply.replyContent);
				
				const strong = $('<strong class="userName">').text(reply.memberNm);
				
				pre.append(strong);
				div6.append(pre);
				
				const div7 = $('<div class="comment-item">');
				const ul = $("<ul>");
				const li = $("<li>").text(replyCreateDt);
				ul.append(li);
				const ul2 = $("<ul>");
				const li2 = $("<li>").text(댓글달기);
				
				if(reply.memberNo == loginMemberNo){
					const li3 = $("<li>")
					
				} 
			})
		}
	})	
}
*/
function addReply(){
	
	if(loginMemberNo == ""){
		alert("로그인 후 이용해 주세요");
	}else{
		
		if($("#commentArea").val().trim().length == 0){
			alert("댓글을 작성한 후 등록해 주세요.");
			$("#commentArea").focus();
		}else{
			$.ajax({
				url : contextPath + "/reply/insert",
				data : {"memberNo" : loginMemberNo, "boardNo" : boardNo, "commentArea" : $("#commentArea").val() },
				type : "POST",
				success : function(result){
					console.log(result);
					
					if(result > 0){
						alert("댓글 등록 성공");
						$("#commentArea").val("");
						
						selectReplyList();
					}else{
						alert("댓글 등록 실패")
					}
				},
				error: function(req, status, error){
					console.log("댓글 목록 조회 실패");
					console.log(req.responseText);
				}
			});
		}
	}
}

function showUpdateReply(replyNo, el){
	if ($(".replyUpdateContent").length > 0) {
        
        if(confirm("확인 클릭 시 수정된 내용이 사라지게 됩니다.")){
            $(".replyUpdateContent").eq(0).parent().html(beforeReplyRow);
        }else{
            return;
        }
    }
// 댓글 수정화면 출력 전 요소를 저장해둠.
    beforeReplyRow = $(el).parent().parent().html();
    //console.log(beforeReplyRow);


    // 작성되어있던 내용(수정 전 댓글 내용) 
    let beforeContent = $(el).parent().prev().html();


    // 이전 댓글 내용의 크로스사이트 스크립트 처리 해제, 개행문자 변경
    // -> 자바스크립트에는 replaceAll() 메소드가 없으므로 정규 표현식을 이용하여 변경
    beforeContent = beforeContent.replace(/&amp;/g, "&");
    beforeContent = beforeContent.replace(/&lt;/g, "<");
    beforeContent = beforeContent.replace(/&gt;/g, ">");
    beforeContent = beforeContent.replace(/&quot;/g, "\"");

    beforeContent = beforeContent.replace(/<br>/g, "\n");


    // 기존 댓글 영역을 삭제하고 textarea를 추가 
    $(el).parent().prev().remove();
    const textarea = $("<textarea>").addClass("replyUpdateContent").attr("rows", "3").val(beforeContent);
    $(el).parent().before(textarea);


    // 수정 버튼
    const updateReply = $("<button>").addClass("btn btn-primary btn-sm ml-1 mb-4").text("댓글 수정").attr("onclick", "updateReply(" + replyNo + ", this)");

    // 취소 버튼
    const cancelBtn = $("<button>").addClass("btn btn-primary btn-sm ml-1 mb-4").text("취소").attr("onclick", "updateCancel(this)");

    const replyBtnArea = $(el).parent();

    $(replyBtnArea).empty();
    $(replyBtnArea).append(updateReply);
    $(replyBtnArea).append(cancelBtn);


}

//-----------------------------------------------------------------------------------------
//댓글 수정폼에서 취소 시 원래대로 돌아가기

function updateCancel(el) {
	// el : 클릭된 취소 버튼

	$(el).parent().parent().html(beforeReplyRow);
}

function updateReply(replyNo, el) {

    // el : 댓글 수정 버튼 요소
    // 댓글 수정 버튼의 부모의 이전 요소의 값
    const replyContent = $(el).parent().prev().val();

    // if(replyContent.trim().length == 0){
    //     alert("댓글을 입력해주세요");
    // }

    $.ajax({
        url : contextPath + "/reply/update",
        data : {
            "replyNo" : replyNo,
            "replyContent" : replyContent
        },
        type : "POST",
        success : function(result){
            if(result > 0){
                alert("댓글이 수정되었습니다");
                selectReplyList();
            }else{
                alert("댓글 수정 실패했습니다");
            }
        },
        error : function(req, status, error){
            console.log("댓글 수정 실패");
            console.log(req.responseText);
        }

    })
}

//댓글 삭제
function deleteReply(replyNo) {
    $.ajax({
        url : contextPath + "/reply/delete",
        data : {"replyNo" : replyNo},
        type : "POST",
        success : function(result){
            if(result > 0){
                alert("댓글이 삭제되었습니다");
                selectReplyList();

            }else{
                alert("댓글 삭제 실패했습니단");
            }

        },
        error : function(req, status, error){
            console.log("댓글 삭제 실패");
            console.log(req.responseText);
        }
    })

}

		