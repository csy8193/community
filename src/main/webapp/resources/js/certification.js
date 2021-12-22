// 각 입력 값이 유효성 검사를 진행했는지 기록할 객체
const checkObj = {
	"id" : false,
	"email" : false,
	"confirmEmail" : false
}

function confirm(){
	for( key in checkObj){
        
        // signUpCheckObj 객체의 속성 중 키가 key인 속성의 value를 얻어와 
        // !를 붙여서 조건이 참인지 확인
        if( !checkObj[key]){
            
            let message;

            switch(key){
				case "id" : message = "아이디가 유효하지 않습니다."; break;
				case "email" : message = "이메일이 유효하지 않습니다."; break;
				case "confirmEmail" : message = "이메일 인증 번호가 일치하지 않습니다."; break;
				
            }
            alert(message);
			;
            // 유효하지 않은 input 요소로 포커스 이동
            document.getElementById(key).focus();

            // form태그 submit 기본 이벤트 제거
            return false;
        }
    }
}

document.getElementById("id").addEventListener("input", function(){

    const inputId = this.value;
    const regExp = /^[a-zA-Z\d]{6,12}$/;
   
    if(inputId.length == 0){ // 빈칸일 경우 출력 내용을 모두 지우기   
        checkObj.id = false; // 유효 x
    }
    else if(regExp.test(inputId)){ // 입력 받은 아이디가 정규식에 유효하다면 
        checkObj.id = true;
    }else{
        checkObj.id = false; // 유효 x
    }
});

document.getElementById("email").addEventListener("input", function(){

    const inputEmail = this.value;

    const regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;

    if(inputEmail.length == 0){
        checkObj.email = false;
    }else if(regExp.test(inputEmail)){
        checkObj.email = true;
    }else{
        checkObj.email = false;
    }
	
});

document.getElementById("certification-btn").addEventListener("click", function(){
	
	$("#id").attr("readonly",true);
	$("#email").attr("readonly",true);
	const id = document.getElementById("id").value;
	const email = document.getElementById("email").value;
	const confirmContent =document.getElementById("confirm");
	
	$(this).hide();		
	$.ajax({
		url:"certification",
		type:"POST",
		data : {"id" : id, "email" : email},
		success : function(data){
			console.log(data);
			if(data != ""){
				alert(email + "로 인증번호를 전송했습니다.");
				confirmContent.style.display = "block";
				document.getElementById("confirm-btn").addEventListener("click", function(){
					const confirmNum = document.getElementById("confirmEmail").value;
					const confirmCheck = document.getElementById("confirmCheck");
					if(confirmNum.trim().length == 0){
						confirmCheck.innerText = "";
						checkObj.confirmEmail = false;
					}else if(confirmNum == data){
						confirmCheck.innerText = "인증 번호가 일치합니다.";
						confirmCheck.style.color = "green";
						checkObj.confirmEmail = true;
					}else{
						confirmCheck.innerText = "인증 번호가 불일치합니다.";
						confirmCheck.style.color = "red";
						checkObj.confirmEmail = false;
					}
				});
			}else{
				$("#certification-btn").show();
			}
		},
		error : function(request, status, error){
                
            if( request.status == 404){
                console.log("ajax 요청 주소가 올바르지 않습니다.")
            }else if(request.status == 500){
                console.log("서버 내부 에러 발생")
                console.log(request.responseText);
            }

   		},

   		complete : function () { 
           
            console.log("complete 수행");
        
      	}
	});
});



