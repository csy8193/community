
//각 입력값이 유효성 검사를 진행했는지 기록할 객체
const signUpCheckObj = { 
    "pwd1" : false,
	"pwd2" : false
}

function validate(){
    // signUpCheckObj의 모든 값을 순차적으로 접근하여
    // false (== 유효하지 않은 경우)를 찾아내는 동작 구현

    for(key in signUpCheckObj){
        
        // signUpCheckObj 객체의 속성 중 키가 key인 속성의 value를 얻어와
        // !를 붙여서 조건이 참인지 확인
        if(!signUpCheckObj[key]){ // == !signUpCheckObj[key] == !false == true
            
            let message;
            
            switch(key){  
            case "pwd1" : message = " 비밀번호가 유효하지 않습니다."; break; 
            case "pwd2" : message = " 비밀번호가 일치하지 않습니다."; break; 
            }

            alert(message);

            // 유효하지 않은 input 요소로 포커스 이동
            //document.getElementById(key).focus();

            // form 태그 submit 기본 이벤트 제거
            return false;
        }
    }
}


// 비밀번호 수정 유효성 검사
document.getElementById("userPw2").addEventListener("input", function(){

	// 수정 비밀번호
    const inputPw = this.value;
	
    const regExp = /^[a-zA-Z\d\!\@\#\-\_]{6,20}$/;
    
    const chkPw = document.getElementById("chkPw1");

    if(inputPw.length == 0){
        chkPw.innerText = "";
		inputPw.focus;
		signUpCheckObj.pwd1 = false;
    }else if(regExp.test(inputPw)){
        chkPw.innerText = "유효한 비밀번호 입니다.";
        chkPw.style.color = "green";
 		signUpCheckObj.pwd1 = true;
    }else{
        chkPw.innerText = "6~20자 영문 대 소문자, 숫자, 특수문자(!,@,#,-,_)를 사용하세요.";
        chkPw.style.color = "red";
 		signUpCheckObj.pwd1 = false;
    }

});


// 비밀번호 확인 유효성 검사 
$("#userPw3").on("input", function(){

    const pw1 = $("#userPw2").val();
    const pw2 = $("#userPw3").val();
    const checkPw2 = $("#chkPw2");
    
    if(pw2.trim().length == 0){ // 비밀번호 확인이 빈칸일 경우
		checkPw2.text("");
		pw2.focus;
		signUpCheckObj.pwd2 = false;
    }else if(pw1 == pw2){ // 유효한 경우
        checkPw2.text("비밀번호가 일치합니다.")
        checkPw2.css("color", "green");
		signUpCheckObj.pwd2 = true;
    }else{  // 유효하지 않은 경우
        checkPw2.text("비밀번호가 불일치합니다.")
        checkPw2.css("color", "red");
		signUpCheckObj.pwd2 = false;
    }
});


function mypagePwDelete(){
	
	// 1) 비밀번호가 입력되어 있지 않으면 false
	const currentPw = document.getElementById("myPagedelete").value;
	
	if(currentPw.trim().length == 0){
		alert("비밀번호를 입력해주세요.");
		return false;
	}
	
	// 3) confirm을 이용하여 정말 탈퇴할 것인지 물어보기
	return confirm("정말로 탈퇴하시겠습니까?");
	
}

	
