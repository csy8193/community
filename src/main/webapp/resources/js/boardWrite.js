  function sendFile(file, editor) {
    data = new FormData()
    data.append("img", file)

    $.ajax({
      data: data,
      type: "POST",
      url: "uploadFile",
      cache: false,
      contentType: false,
      enctype: "multipart/form-data",
      processData: false,
      success: function (result) {
		  $(editor).summernote('editor.insertImage', ""+contextPath+"/resources/images/board/"+result+"");

      const img = $("img");

      if(img.outerWidth() > 800){
        img.css("width", "800px");
      }
		
      },
    })
  }

function boardValidate() {
	if($("#boardTitle").val().trim().length == 0){
		alert("제목을 입력해주세요.");
		$("#boardTitle").focus();
		
		return false;
	}
	if($("#summernote").val().trim().length == 0){
		alert("내용을 입력해주세요.");
		$("#summernote").focus();
		
		return false;

	}
}

// (function(){
//   const div = document.getElementsByClassName("note-editable");

//   div[0].setAttribute("name", "boardContent");
// })();