

// 이미지 영역을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
$(function() {
	$("#animal-profile").on("click", function() {

		$("[type=file]").click();
		// 타입이 file인 요소 중 몇번 인덱스 요소를 선택하여 클릭
	});

});


// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
function loadImg(value) {
	// 매개변수 value == 클릭된 input 요소

	// 파일이 선택된 경우 true
	if (value.files && value.files[0]) {

		var reader = new FileReader();
		// 자바스크립트 FileReader
		// 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 읽을 파일을 가리키는 File 혹은 Blob객체를 이용해 파일의 내용을 읽고 사용자의 컴퓨터에 저장하는 것을 가능하게 해주는 객체

		// 선택된 파일 읽기 시작
		reader.readAsDataURL(value.files[0]);
		// FileReader.readAsDataURL()
		// 지정된의 내용을 읽기 시작합니다. Blob완료되면 result속성 data:에 파일 데이터를 나타내는 URL이 포함 됩니다.

		// FileReader.onload
		// load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료 되었을 때마다 발생합니다.

		// 다 읽은 경우
		reader.onload = function(e) {
			//console.log(e.target.result);
			// e.target.result
			// -> 파일 읽기 동작을 성공한 객체에(fileTag) 올라간 결과(이미지 또는 파일)
			$("#animal-profile > img").addClass('ani-profile-size');
			$("#animal-profile").children("img").attr("src", e.target.result);

			// div 박스.										이벤트가 발생한 결과값 뱉어 내라 src="여기에 넣어라"
		}

	}
}

function selectAnimalList() {

	$.ajax({
		url: contextPath + "/member/selectAnimal",
		type: "GET",
		dataType: "JSON",

		success: function(aniList) {

			console.log(aniList);

			$("#animalList").empty();

			$.each(aniList, function(index, ani) {

				const li = $('<li>');

				const span = $('<span>');
				const p = $('<p>').text(ani.animalNm);
				const button = $('<button>').text("반려동물 정보 수정");

				li.append(span, p, button);

				$("#animalList").prepend(li);

			});

		},

	});
};

function addAnimal() {
	
	const animalCategory = $("#category option:selected").val();
	const animalVariety = $("#kind").val();
	const animalNm = $("#animalName").val();
	const animalGender = $(':radio[name="gender"]:checked').val();

	const year = $("#year option:selected").val();
	const month = $("#month option:selected").val();
	const date = $("#date option:selected").val();

	const aniBirthday = year + "-" + month + "-" + date;

	var formData = new FormData();

	formData.append('uploadFile', $('#animalProfile')[0].files[0]);
	formData.append('animalCategory', animalCategory);
	formData.append('animalVariety', animalVariety);
	formData.append('animalNm', animalNm);
	formData.append('animalGender', animalGender);
	formData.append('aniBirthday', aniBirthday);
	
	$.ajax({
		url: "addAnimal",
		data: formData,
		processData : false,
        contentType : false,
		type: "POST",
		success: function(result) {
			
			if(result > 0){
		         alert("반려 동물 " + animalNm + " 등록 완료");
		
		         $("#kind").val("");
		         $("#animalName").val("");
		         $(':radio[name="gender"]').prop("checked", false);
		
		         $("#category option:eq(0)").prop("selected", true);
		         $("#year option:eq(0)").prop("selected", true);
		         $("#month option:eq(0)").prop("selected", true);
		         $("#date option:eq(0)").prop("selected", true);
		
		         $('#modal').css("display", "none");
		
		         selectAnimalList();
		         }else{
		            alert("반려동물 등록 실패");
		         }
		},


	});


}


