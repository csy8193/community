// 각 입력 값이 유효성 검사를 진행했는지 기록할 객체
const checkObj = {
	"pw1" : false,
	"pw2" : false
}


function pwUpdate(){
	for( key in checkObj){
        
        if( !checkObj[key]){
            
            let message;

            switch(key){
				case "pw1" : message = "비밀번호가 유효하지 않습니다."; break;
				case "pw2" : message = "비밀번호가 일치하지 않습니다."; break;
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

// 비밀번호 유효성 검사
// - 영어 대/소문자, 숫자, 특수문자(!,@,#,-,_) 6~20글자

document.getElementById("pw1").addEventListener("input", function(){

    const inputPw = this.value;

    const regExp = /^[a-zA-Z\d\!\@\#\-\_]{6,20}$/;
    
    const checkPw1 = document.getElementById("checkPw1");

    if(inputPw.length == 0){
        checkPw1.innerText = "";
        checkObj.pw1 = false;
    }else if(regExp.test(inputPw)){
        checkPw1.innerText = "유효한 비밀번호 입니다."
        checkPw1.style.color = "green";
        checkObj.pw1 = true;
    }else{
        checkPw1.innerText = "6~20자 영문 대 소문자, 숫자, 특수문자(!,@,#,-,_)를 사용하세요."
        checkPw1.style.color = "red";
        checkObj.pw1 = false;
    }

});

// 비밀번호 확인 유효성 검사 ==> pw1이랑 같은 값이면 유효
$("#pw2, #pw1").on("input", function(){

    const pw1 = $("#pw1").val();
    const pw2 = $("#pw2").val();
    const checkPw2 = $("#checkPw2");
    
    if(pw2.trim().length == 0){ // 비밀번호 확인이 빈칸일 경우
       checkPw2.text("");
       checkObj.pw2 = false;
    }else if(pw1 == pw2){ // 유효한 경우
        checkPw2.text("비밀번호가 일치합니다.")
        checkPw2.css("color", "green");
        checkObj.pw2 = true;
    }else{  // 유효하지 않은 경우
        checkPw2.text("비밀번호가 불일치합니다..")
        checkPw2.css("color", "red");
        checkObj.pw2 = false;
    }
});