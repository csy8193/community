let feedbackReplyStatus = false;
let updateRepStatus = false;
let prevReplyWriterNo = 0;
let prevUpdateReplyClickNo = 0;
let tempText ="";
//등록 버튼 클릭시 댓글 달림
	
(function(){
	selectReplyList(); 
})()

document.getElementById("addReply").addEventListener("click", function(){
			addReply(0);
});


function addReply(feedbackNo) {
	const replyValue = document.getElementById("commentArea");
	if(loginMemberNo ==""){
	        //로그인 안된 경우
	        alert("로그인 후 이용해주세요");
			$("#commentArea").val("");
			$("#feedback-text").val("");
			return;
    }
	let recontent;
	if(feedbackNo==0){
		 if(replyValue.value.trim().length ==0 ){
            alert("댓글을 작성한 후 버튼을 클릭해주세요.")
            replyValue.focus();
			return;
        }
		recontent= replyValue.value;
	}
	else{
		 if($("#feedback-text").val().trim().length ==0 ){
            alert("댓글을 작성한 후 버튼을 클릭해주세요.")
            $("#feedback-text").focus();
			return;
        }
		recontent =$("#feedback-text").val();
	}
		$.ajax({
			url : contextPath + "/reply/insert",
			data : {
				"memberNo" : loginMemberNo,
				"boardNo" : boardNo,
				"replyContent" : recontent ,
				"feedbackNo" : feedbackNo
			},
			type : "POST",
			success : function(result){
                    console.log(result);
                    if(result>0){
                        alert("댓글 삽입 성공");
                        replyValue.value ="";
						$("#feedback-text").text("");
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
        url : contextPath + "/reply/select",
        data : {"boardNo": boardNo},
        type : "GET",
        dataType : "JSON",  // 반환되는 데이터 형식 지정 -> 응답 받은 후 형변환 진행
		
		success : function(rList){
            console.log(rList);
            $("#all-reply").empty(); // 기존 댓글 내용 모두 삭제
			console.log("성공");
			
            $.each( rList , function(index, reply){
				if(reply.feedbackReplyNo==0){
	                const li = $('<li class="one-reply">');
					const onerep = makereply(reply,true)
					//피드백 존재하는지 검사
						$.each( rList , function(index2, checkfeedback){
							if(checkfeedback.feedbackReplyNo == reply.replyNo){
								onerep.append(makereply(checkfeedback,false));
							}
						});
					li.append(onerep)
					$("#all-reply").append(li);
				}
			})
		},
		error : function(req, status, error){
            console.log("댓글 목록 조회 실패");
            console.log(req.responseText);
		}
		
		});
};
function makereply(reply, checkoriginal){

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
					$(img).attr("src",contextPath+reply.animalImgPath+reply.animalImgNm);
					p.append(img);
				}
				else{
					const img =$('<img>');
					$(img).attr("src", contextPath+reply.animalMainImgPath);
					p.append(img);
				}
				div.append(p);
				
				const div2 = $('<div class="reply-user">');
				const i = $('<i class="fas fa-level-up-alt">');
				const span1 = $('<span>');
				const span2 = $('<span>');
				
				span1.text(reply.memberNm);
				span2.text(reply.replyCreateDt);
				if(!checkoriginal){div2.append(i);}
				div2.append(span1,span2);
				
				const div3 = $('<div class="reply-content">');
				const span3 = $('<span>');
				if(reply.statusCd==2){
					$(span3).text("삭제된 댓글입니다");
					div3.append(span3);
				}
				else{
					$(span3).text(reply.replyContent);
					const br3 = $('<br>');
					div3.append(span3,br3);
					const button1 = $('<button onclick="createReplyArea(this)">');
					button1.attr("value",reply.replyNo);
					button1.html('<i class="far fa-comment-dots"></i>댓글달기');
					if(loginMemberNo == reply.memberNo){
						const button3 = $('<button onclick="updateReply(this)">');
						button3.text('수정');
						const button4 = $('<button onclick="deleteNrep(this)">');
						button4.text('삭제');
						div3.append(button1,button3, button4);
					}else{
						div3.append(button1);
					}
				}
				//const button2 = $('<button>');
				//button2.html('<i class="fas fa-bullhorn"></i>신고하기');
				
				div.append(p, div2, div3);
           		return div;
}
function createReplyArea(el){
	if(!feedbackReplyStatus){
		const div = $('<div class="feedback" id="feedbackwrite">');
		const textarea = $('<textarea>');
		textarea.attr("id","feedback-text");
		textarea.attr("placeholder","댓글을 입력해주세요");
		const div2 = $('<div>');
		div2.css("width","610px");
		div2.css("margin-bottom","10px");
		
		const temp = $(el).parents(".original").children(".reply-content").children("button:first");
		const originalRepl = temp.val();
		console.log(originalRepl);
		
		const feedbackbtn = $('<button id="feedbackbtn">');
		feedbackbtn.attr("onclick", "addReply("+originalRepl+")");
		feedbackbtn.text("등록");
		

		const feedbackcancel = $('<button id="feedbackcancelbtn">');
		feedbackcancel.attr("onclick", "removeFeedback(this)");
		feedbackcancel.text("취소");
		div2.append(feedbackbtn,feedbackcancel);
		
		div.append(textarea,div2);
		$(el).parent().parent().after(div); 

		feedbackReplyStatus= true;
		prevReplyWriterNo = $(el).val();
	}
	else{
		let nowReplyWriterNo = $(el).val();
			$("#feedbackwrite").remove();
			feedbackReplyStatus=false;
		
		if(prevReplyWriterNo!=nowReplyWriterNo){
			createReplyArea(el)
		}
	}
}

function removeFeedback(el){
	if(confirm("내용이 지워집니다. 댓글 등록을 취소하시겠습니까? ")){
		$("#feedbackwrite").remove();
		console.log(feedbackReplyStatus);
		feedbackReplyStatus=false;
	}
}

function deleteNrep(el){
	if(confirm("댓글을 삭제하시겠습니까?")){
		
	const  delReplyNo = $(el).prev().prev().val();
	$.ajax({
		url : contextPath + "/reply/delete",
		data : {
			"delReplyNo" : delReplyNo
		},
		type : "POST",
		success : function(result){
			if(result>0){
				selectReplyList();
				
			}
		},
		error : function(req, status, error){
            console.log("좋아요 에러");
            console.log(req.responseText);
        }
		
	});
	}
}
function updateReply(el){
	if(updateRepStatus==false){
			
		tempText = $(el).siblings("span").text();
		$(el).siblings("span").text("");
		const textarea = $('<textarea id ="nboardUpdateRep">');
		textarea.text(tempText);
		textarea.css("width", "100%");
		textarea.css("height", "100px");
		textarea.css("resize", "none");
		textarea.css("position", "relative");
	
		const button1 = $('<button id="updateRep">');
		button1.attr("onclick","updateRep(this)");
		button1.text("등록");
		const button2 = $('<button id="updateRepCancel">');
		button2.attr("onclick","cancelUpdateRep(this)");
		button2.text("취소");
		
		$(el).siblings("span").after(textarea,button1,button2);
	
		updateRepStatus= true;
		prevUpdateReplyClickNo = $(el).prev().val();
	}
	else{
		cancelUpdateRep(el);
		let nowUpdateReplyClickNo = $(el).prev().val();
		
		if(prevUpdateReplyClickNo!=nowUpdateReplyClickNo){
			updateReply(el);
		}
	}
}


function updateRep(el){
	const contents = $("#nboardUpdateRep").val();
	console.log(contents);
	const replyNo = $(el).next().next().next().val();
	console.log(replyNo);
	
  $.ajax({
        url : contextPath + "/reply/update",
        data : {"replyNo": replyNo,
				"replyContent" : contents
			},
        type : "POST",
		success : function(result){
			if(result) selectReplyList();
			else alert("수정 실패")
		},
		error : function(req, status, error){
            console.log("댓글 목록 조회 실패");
            console.log(req.responseText);
		}
		
		});
}
function cancelUpdateRep(el){

			$("#nboardUpdateRep").prev().text(tempText);
			$("#nboardUpdateRep").next().remove();
			$("#nboardUpdateRep").next().remove();
			$("#nboardUpdateRep").remove();
			updateRepStatus=false;
}
