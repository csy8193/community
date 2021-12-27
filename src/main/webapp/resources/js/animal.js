

//////////////////////반려동물등록/////////////////////////////////////

$(function() {
	$("#animal-profile2").on("click", function() {

		$("input[name=animal-profile]").click();
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
			$("#animal-profile2 > img").addClass('ani-profile-size');
			$("#animal-profile2").children("img").attr("src", e.target.result);
			// div 박스.										이벤트가 발생한 결과값 뱉어 내라 src="여기에 넣어라"
		}

	}
}
///////////////////////////////////////////////////////////////////////


//////////////////////반려동물수정/////////////////////////////////////

// 이미지 영역을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수

$(function() {

	$(".animalProfileImg").on("click", function() {

		$("input[name=animalProfile]").click();
		//$("[name=animalProfile]").eq(test).click();
		// 타입이 file인 요소 중 몇번 인덱스 요소를 선택하여 클릭
	});

});


// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
function updateLoadImg(value) {
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
			$(".animalProfileImg > img").addClass('ani-profile-size');
			$(".animalProfileImg").children("img").attr("src", e.target.result);
		}

	}
}


// 대표프로필 설정
function insertProfile() {

	const animalProfilePath = $("#profile-chk-select option:selected").val();
	//var index = $("#profile-chk-select option").index($("#profile-chk-select option:selected")); 
	//console.log(animalProfilePath);
	$.ajax({
		url: "insertProfile",
		data: { "animalProfilePath": animalProfilePath },
		type: "POST",
		success: function(result) {
			if (result > 0) {
				alert("대표프로필 설정되었습니다.");
			}
		},

	});

}



// 반려동물 등록 ajax
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

	formData.append('uploadFile', $('#animal-profile')[0].files[0]);
	formData.append('animalCategory', animalCategory);
	formData.append('animalVariety', animalVariety);
	formData.append('animalNm', animalNm);
	formData.append('animalGender', animalGender);
	formData.append('aniBirthday', aniBirthday);

	$.ajax({
		url: "addAnimal",
		data: formData,
		processData: false,
		contentType: false,
		type: "POST",
		success: function(result) {

			if (result > 0) {

				alert("반려 동물 " + animalNm + " 등록 완료");
				$("#animal-profile2 > img").attr("src',"+contextPath+"/resources/images/main/basis-profile-img.png");
				$("#kind").val("");
				$("#animalName").val("");
				$(':radio[name="gender"]').prop("checked", false);

				$("#category option:eq(0)").prop("selected", true);
				$("#year option:eq(0)").prop("selected", true);
				$("#month option:eq(0)").prop("selected", true);
				$("#date option:eq(0)").prop("selected", true);

				$('#modal').css("display", "none");

				selectAnimalList();

			} else {
				alert("반려동물 등록 실패");
			}
		},

	});

}


// 수정하기 모달창으로 기존 정보 보여지기
$(document).on("click",".updateAnimalModalBtn",function(){
	// 문서 내에서 .updateAnimalModalBtn 요소가 클릭이 되었을 때
	// -> 이벤트를 감지하는 기준을 요소에서 문서 전체로 바꿨기 떄문에
	//    처음부터 있던 요소, JS로 추가된 동적요소 모두에 이벤트가 적용됩니다.
	
	// 이미지 경로
	const img = $(this).prev().prev().children("img").attr("src");
	
	// 동물 정보
	const pArr = $(this).next().children("p");
	
	// 이미지 경로를 모달창에 넣어라
	$(".animalProfileImg > img").attr("src", img);
	
	// 반려동물 품종
	$(".updateKind").val(pArr.eq(2).text());
	
	// 반려동물 이름
	$(".updateAnimalName").val(pArr.eq(1).text());

	// 반려동물 종류
	$(".updateCategory").val(pArr.eq(5).text());
	
	// 성별
	 $("input[name='updateGender'][value="+pArr.eq(3).text()+"]").prop("checked","checked");
	
	// 생년월일
	$("#year1").val(pArr.eq(4).text().substring(0, 4)).prop("selected", "selected");
	$("#month1").val(pArr.eq(4).text().substring(5, 7)).prop("selected", "selected");
	$("#date1").val(pArr.eq(4).text().substring(8, 10)).prop("selected", "selected");
	
	$("#upBtn").attr("onclick", "updateAnimal(" + pArr.eq(0).text() + ")" );
	
	$("#deleteBtn").attr("onclick", "deleteAnimal(" + pArr.eq(0).text() + ")" );
	
});





// 반려동물 리스트
function selectAnimalList() {

	$.ajax({
		url: contextPath + "/member/selectAnimal",
		type: "GET",
		dataType: "JSON",

		success: function(aniList) {

			var selectedIndex = $("#profile-chk-select option").index($("#profile-chk-select option:selected"));

			$("#animalList").empty();

			$.each(aniList, function(index, ani) {

				const path = contextPath + ani.animalImgPath + ani.animalImgNm;
				const li = $('<li>');
				const span = $('<span>');
				const img = $('<img>').attr('src', path).css('width', '250px');
				span.append(img);

				const p = $('<p>').addClass("animalNm").text(ani.animalNm);
				const button = $('<button type="button">').addClass("updateAnimalModalBtn").text("반려동물 정보 수정");
				
				button.attr("onclick", "updateModal()");
				
				const div = $('<div>').css("display","none");
				const p2 = $('<p>').text(ani.animalNo);
				const p3 = $('<p>').text(ani.animalNm);
				const p4 = $('<p>').text(ani.animalVariety);
				const p5 = $('<p>').text(ani.animalGender);
				const p6 = $('<p>').text(ani.animalBirthday);
				const p7 = $('<p>').text(ani.animalCategoryCode);
				

				div.append(p2,p3,p4,p5,p6,p7);
				
				li.append(span, p, button,div);

				$("#animalList").append(li);

				$("#profile-chk-select option:eq(" + selectedIndex + ")").attr("selected", "selected");
			});

		},

	});
};

// 반려동물 수정하기(db로)
function updateAnimal(animalNo){
	const animalCategory = $(".updateCategory option:selected").val();
	const animalVariety = $(".updateKind").val();
	const animalNm = $(".updateAnimalName").val();
	const animalGender = $(':radio[name="updateGender"]:checked').val();

	const year = $("#year1 option:selected").val();
	const month = $("#month1 option:selected").val();
	const date = $("#date1 option:selected").val();
	
	const aniBirthday = year + "-" + month + "-" + date;
	
	var formData = new FormData();
	
	formData.append('animalNo',animalNo);
	// 수정 필요
	formData.append('uploadFile', $('.animalProfileFile')[0].files[0]);
	
	formData.append('animalCategory', animalCategory);
	formData.append('animalVariety', animalVariety);
	formData.append('animalNm', animalNm);
	formData.append('animalGender', animalGender);
	formData.append('aniBirthday', aniBirthday);

	$.ajax({
		url: "updateAnimal",
		data: formData,
		processData: false,
		contentType: false,
		type: "POST",
		success: function(result) {

			if (result > 0) {
				
				alert("반려 동물 " + animalNm + " 수정 완료");
				
				$('.updateModal').css("display", "none");

				selectAnimalList();

			} else {
				alert("반려동물 수정 실패");
			}
		},
});

}


// 반려동물 삭제
function deleteAnimal(animalNo){
	
	   if (!confirm("정말로 삭제하시겠습니까?")) {
		
			$('.updateModal').css("display", "none");
			
            selectAnimalList();

        } else {
			$.ajax({
				url: "deleteAnimal",
				data: {"animalNo":animalNo},
				type: "POST",
				success: function(result) {
					
				console.log(result);
				
					if (result > 0) {
						
						
						alert("삭제되었습니다.");
						
						$('.updateModal').css("display", "none");
		
						selectAnimalList();
		
					} else {
						alert("반려동물 삭제 실패");
						
						$('.updateModal').css("display", "none");
						
						selectAnimalList();
					}
				},
			});
        }
	
}

function selectBoardList(boardCd){
	
	$.ajax({
			url: "selectBoardList",
			data: {"boardCd":boardCd},
			type: "GET",
			dataType: "JSON",
			
			success: function(bList) {
				
				console.log(bList);
				console.log(boardCd);
				
				
				const h2 = $('<h2>');
				let title = '';
				const p = $('<p>');
				let boardPath = "";
				let th2;
				
				switch(boardCd){
				case 10 :  title = "자유 게시판"; boardPath = "/nboard/view?&boardNo="; th2 = $('<th>').text('제목'); break;
				case 20 :  title = "노하우 게시판"; boardPath = "/nboard/view?&boardNo="; th2 = $('<th>').text('제목'); break;
				case 30 :  title = "유기동물 보호소"; boardPath = "/nboard/view?&boardNo="; th2 = $('<th>').text('제목');break;
				case 40 :  title = "문의하기"; boardPath = "/nboard/view?&boardNo="; th2 = $('<th>').text('제목');break;
				case 70 :  title = "자랑하기"; boardPath = "/pboard/view?&no="; th2 = $('<th>').text('내용'); break;
				case 80 :  title = "유기동물 신고"; boardPath = "/pboard/view?&no="; th2 =$('<th>').text('내용'); break;
				case 90 :  title = "아이들 근황"; boardPath = "/pboard/view?&no="; th2 = $('<th>').text('내용'); break;
						
				}
				
				h2.append(title);
				
				const table = $('<table>').addClass('sub-table');
				
				const tr = $('<tr>').addClass('tr');
				const th1 = $('<th>').text('번호');
				

				const th3 = $('<th>').text('작성일');
				
				tr.append(th1,th2,th3);
				table.append(tr);
				
				$("#sub-board").empty();
				$(".sub2").css("display","none");
				
				if(bList == null){
					p.text("아직 작성된 글이 없어요").css("margin-top","40px");
					$("#sub-board").append(h2,table,p);
				}else{
					
					$.each(bList, function(index,board) {
						console.log(board);
						const tr2 = $('<tr>');
						const test = $('<a>').attr("href",""+contextPath+boardPath+board.boardNo+"&boardCd="+board.boardCode+"");
						
						if(board.boardTitle == null){
							test.text(board.boardContent);
							
						}
						else{
							test.text(board.boardTitle);
						}
						
						const td1 = $('<td>').text(board.boardNo);
						const td2 = $('<td>').append(test);
						const td3 = $('<td>').text(board.createDate);
						
						tr2.append(td1,td2,td3);
						table.append(tr2);
						$("#sub-board").append(h2,table);
						
					});
					
				}

			
			}, // success 		
			
			
		}); //ajax
	
	}



