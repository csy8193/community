	 	
// 마이페이지 제이쿼리
const modal = document.getElementById("modal");

document.getElementById("plus").addEventListener("click",function(){
    modal.style.display = "flex";
});

modal.addEventListener("click", e => {
    const evTarget = e.target;

    if(evTarget.classList.contains("modal")) {
        modal.style.display = "none";
    }

});

$("#search").on("click",function(){
        if($("#search-wrap").css("display") == "none"){// #img4가 화면에 안보이는 상태
            $("#search-wrap").show();
        }else{
            $("#search-wrap").hide();
        }
  });