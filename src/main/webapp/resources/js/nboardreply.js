
//등록 버튼 클릭시 댓글 달림
document.getElementById("reply-btn").addEventListener("click", function(){
			addReply();
});
	
document.getElementByClass("")

function addReply() {
	const replyValue = document.getElementById("nboard-reply-input");
	if(loginMemberNo ==""){
        //로그인 안된 경우
        alert("로그인 후 이용해주세요");
    }
	else{
		 if(replyValue.value.trim().length ==0 ){
            alert("댓글을 작성한 후 버튼을 클릭해주세요.")
            replyValue.focus();
        }
	}
	
	
		
		if(replyValue.value.trim().length>0) console.log(replyValue);
		console.log(category);
		
		$.ajax({
			url : contextPath + "/nboard/view/reply/insert",
			data : {
				"memberNo" : loginMemberNo,
				"boardNo" : boardNo,
				"replyContent" : replyValue.value
			},
			type : "POST",
			success : function(result){
                    console.log(result);
                    if(result>0){
                        alert("댓글 삽입 성공");
                        replyValue.value ="";
                        selectReplyList();//댓글 조회 함수 새로 호출
                    }
                    else{
                        alert("댓글 삽입 실패");
					}
			},
            error : function(req, status, error){
                    console.log("댓글 삽입 실패");
                    console.log(req.responseText);
            }
		})
};

function selectReplyList() {
    // ajax를 이용해 비동기로 댓글 목록 조회 및 출력
    $.ajax({
        url : contextPath + "/nboard/view/reply/select",
        data : {"boardNo": boardNo},
        type : "GET",
        dataType : "JSON",  // 반환되는 데이터 형식 지정 -> 응답 받은 후 형변환 진행
		
		success : function(rList){
            console.log(rList);
            $("#all-reply").empty(); // 기존 댓글 내용 모두 삭제
			console.log("성공");
			
            $.each( rList , function(index, reply){
                const li = $('<li class="one-reply">');
				const onerep = makereply(reply,true)
				//피드백 존재하는지 검사
				$.each( rList , function(index, checkfeedback){
					if(checkfeedback.feedbackReplyNo == reply.replyNo){
						onerep.append(makereply(checkfeedback,false));
					}
				});
				
				li.append(onerep)
				$("#all-reply").append(li);
			})
		},
		error : function(req, status, error){
            console.log("댓글 목록 조회 실패");
            console.log(req.responseText);
		}
		
		});
};
function makereply(reply, checkoriginal){
	//console.log(reply.replyContent);
                //const li = $("<li>").addClass("reply-row");
                const div = $('<div>');
				if(checkoriginal){
					div.addClass("original");
				}
				else{
					div.addClass("feedback");
				}
				const p = $('<p>');
				
				if(reply.profileExist){
					const img =$('<img>');
					$(img).attr("src",contextPath+reply.animalImgPath+reply.animalImgNm)
					p.append(img);
				}
				else{
					const img =$('<img>');
					$(img)
					p.append(img);
				}
				div.append(p);
				
				const div2 = $('<div class="reply-user">');
				const i = $('<i class="fas fa-level-up-alt">');
				const span1 = $('<span>');
				const span2 = $('<span>');
				
				span1.text(reply.memberId);
				span2.text(reply.replyCreateDate);
				if(!checkoriginal){div2.append(i);}
				div2.append(span1,span2);
				
				const div3 = $('<div class="reply-content">');
				let replval = reply.replyContent.replace(/&lt;br&gt;/g, "<br>");
				const span3 = $('<span>');
				$(span3).text(replval);
				
				const button1 = $('<button onclick="createReplyArea(this)">');
				button1.html('<i class="far fa-comment-dots"></i>댓글달기');
				
				const button2 = $('<button>');
				button2.html('<i class="fas fa-bullhorn"></i>신고하기');
				const br3 = $('<br>');
				div3.append(span3,br3,button1,button2);
				
				div.append(p, div2, div3);
           		return div;
}
function createReplyArea(el){
	console.log(el);
	
	const div = $('<div class="feedback">');
	const textarea = $('<textarea>');
	div.append(textarea);
	$(el).parent().parent().after(div); 
}

