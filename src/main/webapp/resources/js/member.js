// 각 입력 값이 유효성 검사를 진행했는지 기록할 객체
const signUpCheckObj = {
    "id" : false,
    "name" : false,
    "email" : false,
    "pw1" : false,
    "pw2" : false
}

function validate(){ // 회원 가입 버튼 클릭시 유효성 검사여부 판단

    // signUpCheckObj의 모든 값을 순차적으로 접근하여
    // false(== 유효하지 않은 경우)를 찾아내는 동작 구현

    for( key in signUpCheckObj){
        
        // signUpCheckObj 객체의 속성 중 키가 key인 속성의 value를 얻어와 
        // !를 붙여서 조건이 참인지 확인
        if( !signUpCheckObj[key]){
            
            let message;

            switch(key){
                case "id" : message = "아이디가 유효하지 않습니다."; break; 
                case "name" : message = "이름이 유효하지 않습니다."; break;
                case "email" : message = "이메일이 유효하지 않습니다"; break;   
                case "pw1" : message = "비밀번호가 유효하지 않습니다."; break;
                case "pw2" : message = "비밀번호가 일치하지 않습니다"; break;
            }
            alert(message);
            // 유효하지 않은 input 요소로 포커스 이동
            document.getElementById(key).focus();

            // form태그 submit 기본 이벤트 제거
            return false;
        }
    }

}



// 아이디 유효성 검사
// - 영어 대/소문자 + 숫자, 총 6~12글자
// document.querySelector("#id");
// $("#id")

document.getElementById("id").addEventListener("input", function(){
   
    // const inputId = e.target.value;
    // const inputId = document.getElementById("id").value; 
    const inputId = this.value;
    
    // 정규 표현식
    const regExp = /^[a-zA-Z\d]{6,12}$/;

    // 검사 결과 출력 요소 선택
    const checkId = document.getElementById("checkId");
   
    // 유효성 검사
    if(inputId.length == 0){ // 빈칸일 경우 출력 내용을 모두 지우기
        checkId.innerText = "";
        signUpCheckObj.id = false; // 유효 x
    }

    // 유효성 검사
    else if(regExp.test(inputId)){ // 입력 받은 아이디가 정규식에 유효하다면 
        // checkId.innerText = "유효한 아이디 입니다.";
        // checkId.style.color = ("green");
        // signUpCheckObj.id = true; // 유효 o
        // ***************************************************************************************************
        // AJAX를 이용한 아이디 중복 검사(비동기 통신)
        $.ajax({ // jQeury 방식의 ajax
            url : "idDupCheck",                         // duplication : 이중, 중복    
                                                        // 어떤 Servlet을 요청할 것인가?
                                                        // 요청 주소 작성 속성(필수!!)

            data : {"inputId" : inputId},               // 요청 시 전달할 값(파라미터)

            tpye : "GET",                               // 데이터 전달 방식(method)
                                                        // 미작성 시 기본값 GET
            
            success : function(result){
                // 비동기 통신 성공 시 수행할 동작(함수)
                // 매개변수 result : 서버로 부터 전달 받은 응답 데이터
                //                  (변수명은 자유)

                // console.log(result);

                if(result == 0){ // 사용 가능
                    checkId.innerText = "사용 가능한 아이디 입니다."
                    checkId.style.color = "green";
                    signUpCheckObj.id = true;
                }else{ // 중복
                    checkId.innerText = "이미 사용중인 아이디 입니다."
                    checkId.style.color = "red";
                    signUpCheckObj.id = false;
                }
            },

            error : function(){
                // 비동기 통신중 서버로부터 에러 응답이 돌아왔을 때 수행

            },

            complete : function(){
                // 비동기 통신이 성공하든 실패하든 통신 완료 시 마지막에 수행
                // (finally와 비슷)
            }
            
        });



        // ***************************************************************************************************
    }else{
        checkId.innerText = "6~12자 영문 대 소문자, 숫자를 사용하세요."
        checkId.style.color = ("red");
        signUpCheckObj.id = false; // 유효 x
    }
});

// 이름 유효성 검사
// - 한글(자음+모음[+받침]), 2~5글자

document.getElementById("name").addEventListener("input", function(){

    const inputName = this.value;

    // 정규 표현식
    const regExp = /^[가-힣]{2,5}$/;

    // 검사 결과 출력 요소 선택
    const checkName = document.getElementById("checkName");

    // 유효성 검사
    if(inputName.length == 0){ //빈칸일 경우 출력 내용을 모두 지우기
        checkName.innerText = "";
        signUpCheckObj.name = false; // 유효 x 
        
    }else if(regExp.test(inputName)){ // 입력 받은 이름이 정규식에 유효한 경우
        checkName.innerText = "유효한 이름 입니다."
        checkName.style.color = ("green");
        signUpCheckObj.name = true; // 유효 o

    }else{ // 입력 받은 이름이 정규식에 유효하징 않은 경우
        checkName.innerText = "유효하지 않은 이름 입니다."
        checkName.style.color = ("red");
        signUpCheckObj.name = false; // 유효 x
    }
});

// 이메일 유효성 검사
// - 아이디가 4글자 이상인 이메일 주소 형식

document.getElementById("email").addEventListener("input", function(){

    const inputEmail = this.value;

    const regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
    
    const checkEmail = document.getElementById("checkEmail");

    if(inputEmail.length == 0){
        checkEmail.innerText = "";
        signUpCheckObj.email = false;
    }else if(regExp.test(inputEmail)){
        // checkEmail.innerText = "유효한 이메일 입니다."
        // checkEmail.style.color = "green";
        // signUpCheckObj.email = true;

        // *****************************************************************
        // 이메일 중복 검사(AJAX)

        $.ajax({
            url : "emailDupCheck", // 필수 속성
            type : "GET",
            data : {"inputEmail" : inputEmail},

            success : function(result){
                if(result == 0){ // 사용 가능
                    checkEmail.innerText = "사용 가능한 이메일 입니다."
                    checkEmail.style.color = "green";
                    signUpCheckObj.email = true;
                }else{ // 중복
                    checkEmail.innerText = "이미 사용중인 이메일 입니다."
                    checkEmail.style.color = "red";
                    signUpCheckObj.email = false;
                }
            },
            error : function(request, status, error){
                // console.log(request);
                // console.log(status);
                if( request.status == 404){
                    console.log("ajax 요청 주소가 올바르지 않습니다.")
                }else if(request.status == 500){
                    console.log("서버 내부 에러 발생")
                    console.log(request.responseText);
                }

            },

            complete : function () { 
                // success, error 수행 후
                // ajax 요청/응답 처리가 완료되었을 때
                // (마지막에 무조건 수행)
                console.log("complete 수행");
                
              }

        });




        // *****************************************************************

    }else{
        checkEmail.innerText = "유효하지 않은 이메일 입니다."
        checkEmail.style.color = "red";
        signUpCheckObj.email = false;
    }

});

// 비밀번호 유효성 검사
// - 영어 대/소문자, 숫자, 특수문자(!,@,#,-,_) 6~20글자

document.getElementById("pw1").addEventListener("input", function(){

    const inputPw = this.value;

    const regExp = /^[a-zA-Z\d\!\@\#\-\_]{6,20}$/;
    
    const checkPw1 = document.getElementById("checkPw1");

    if(inputPw.length == 0){
        checkPw1.innerText = "";
        signUpCheckObj.pw1 = false;
    }else if(regExp.test(inputPw)){
        checkPw1.innerText = "유효한 비밀번호 입니다."
        checkPw1.style.color = "green";
        signUpCheckObj.pw1 = true;
    }else{
        checkPw1.innerText = "6~20자 영문 대 소문자, 숫자, 특수문자(!,@,#,-,_)를 사용하세요."
        checkPw1.style.color = "red";
        signUpCheckObj.pw1 = false;
    }

});

// 비밀번호 확인 유효성 검사 ==> pw1이랑 같은 값이면 유효
$("#pw2, #pw1").on("input", function(){

    const pw1 = $("#pw1").val();
    const pw2 = $("#pw2   ").val();
    const checkPw2 = $("#checkPw2");
    
    if(pw2.trim().length == 0){ // 비밀번호 확인이 빈칸일 경우
       checkPw2.text("");
       signUpCheckObj.pw2 = false;
    }else if(pw1 == pw2){ // 유효한 경우
        checkPw2.text("비밀번호가 일치합니다.")
        checkPw2.css("color", "green");
        signUpCheckObj.pw2 = true;
    }else{  // 유효하지 않은 경우
        checkPw2.text("비밀번호가 불일치합니다..")
        checkPw2.css("color", "red");
        signUpCheckObj.pw2 = false;
    }
});

// 로그인 시 유효성 검사
function loginValidate(){

    const memberId = document.getElementById("memberId");
    const memberPw = document.querySelector("#memberPw");

    // 아이디가 입력되지 않은 경우
    // "아이디를 입력해주세요." 경고창 출력 후
    // 아이디 input으로 focus 이동

    if(memberId.value.trim().length == 0){
        alert("아이디를 입력해주세요!!!!");
        memberId.focus();
        return false;
    }
    if(memberPw.value.trim().length == 0){
        alert("비밀번호를 입력해주세요.");
        memberPw.focus();
        return false;
    }
}
