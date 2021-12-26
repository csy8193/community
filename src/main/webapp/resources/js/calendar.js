 let selectMonth = 0;
 let continueWalk = 0; 
    window.onload= function(){
		
	displayCal(selectMonth);
	
	
    }
const dayList = function(el){
	const day = $(el).text();
	//loginMemberNo

	const checky = $("#today-month").text().split('/')[0];
	const checkm = $("#today-month").text().split('/')[1];
	$.ajax({
		url : contextPath+"/walk/walkdayshow",
			data : {
				"loginMemberNo" : loginMemberNo,
				"day" : day,
				"checky" : checky,
				"checkm" : checkm
			},
			dataType : "json",
			type : "POST",
			success : function(dayhistory){
				$("#mydaywalk").text("");
				$("#mywalklist").css("display","block");
				$.each( dayhistory , function(index1, checkday){
					const li = $("<li>");
					const span1 = $("<span>");
					span1.text(index1+1);
					const span2 = $("<span>");
					span2.text(checkday.boardContent);
					const span3 = $("<span>");
					span3.text(checkday.createDt);
					li.append(span1,span2,span3);
					$("#mydaywalk").append(li);
				})
								
				console.log(dayhistory);
			},
			error : function(req, status, error){
                console.log("에러");
                console.log(req.responseText);
            }
	})
}

	function getYyyyMmDdMmSsToString(date)
{
			var dd = date.getDate();
			var mm = date.getMonth()+1; //January is 0!
		
			var yyyy = date.getFullYear();
			if(dd<10){dd='0'+dd} if(mm<10){mm='0'+mm}
			
			yyyy = yyyy.toString();
			mm = mm.toString();
			dd = dd.toString();
		
			var s1 = yyyy+''+mm+''+dd;
			return s1;
}
	
    const displayCal = function(){
    let temp = new Date()
    let today = new Date(
        temp.getFullYear(),temp.getMonth()+selectMonth ,temp.getDate(), 
        temp.getHours() , temp.getMinutes() , temp.getSeconds());
    let year = today.getFullYear();
    let month = today.getMonth()+1;
    let date = today.getDate();
    let firstDay = new Date(year, month-1 , 1).getDay();
    let day = today.getDay();
    var monthDay = Array(31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    day7 = firstDay;
    /* 2월의 마지막 날 설정 */
    if (year % 400 == 0)
        monthDay[1] = 29;
    else if (year % 100 == 0)
        monthDay[1] = 28;
    else if (year % 4 == 0)
        monthDay[1] = 29;
    else
        monthDay[1] = 28;

    let lastDate =  monthDay[month-1];

    var enMonthName = new Array('1','2','3','4','5','6',
        '7','8','9','10','11','12');
    
    	
        const tmonth = document.getElementById("today-month");
        const monthday = document.getElementById("month-day");
        tmonth.innerText = year+"/" +enMonthName[month-1] 
		
        let count = 1;
        let week =1;
        let notLast= true; //마지막 날짜 검사 boolean;
		
		let startdate = getYyyyMmDdMmSsToString(new Date(year, month-1 , 1));
		let enddate =  getYyyyMmDdMmSsToString(new Date(year, month-1 , lastDate));
					console.log(startdate);
					console.log(enddate);
		//----------------------------------------------------------
			$.ajax({
			url : contextPath+"/walk/walkhistory",
			data : {
				"startwalk" : startdate,
				"loginMemberNo" : loginMemberNo,
				"endwalk" : enddate
			},
			dataType : "json",
			type : "POST",
			success : function(history){
				let arrcheckday = []; 
				let i =0 ;
				 $.each( history , function(index3, checkday){
						arrcheckday[i] = checkday;
						i=i+1;
				})
				if(arrcheckday.length != 0){
					continueWalk=1
					for(let pl =arrcheckday.length-1 ; pl > 0; pl--){
						let nowval  = arrcheckday[pl];
						let prevval = arrcheckday[pl-1];
						if(nowval-prevval ==1){
							continueWalk ++
						}
						else{
							break;
						}
					}
				}
				$("#continueCheck").text(continueWalk);
				console.log(arrcheckday);
				while(notLast){
	            const tr = document.createElement("tr");
	            for(let i = 0 ; i<7 ; i++){
	                const td = document.createElement("td");
	                    //첫번째 주는 해당 요일에서부터 1이 들어감
	                    if(week==1&&i<firstDay){
	                        td.innerText = " ";
	                    }
	                    else{
	                        td.innerText = count;

							arrcheckday.forEach(function(element){
							    if(element==count) {
									console.log("같음");
									$(td).addClass("walkdone");
									$(td).attr("onclick","dayList(this)");
							    }
								else{
									$(td).addClass("nonewalk");
								}
							});
	                        let tempDate = new Date(year, month-1 ,count);
	                        //console.log(count+"번째" +" tempDate: " +tempDate);
	                            count= count+1;
	                    }
	                    tr.append(td);
	                    tr.style.textAlign = "center";
	                    //해당 월의 마지막 날짜까지 표기됐는지 검사
	                    if(count == lastDate+1) {
	                        console.log("완료");
	                        notLast = false;
	                        break;
	                    }
	            }
	            week = week+1;//for문 1번 == 1주일
	            monthday.append(tr);
	        	}
			},
			error : function(req, status, error){
                    console.log("달력 에러");
                    console.log(req.responseText);
            }
			
		})
		//------------------------------------------------------

}
const prev = function(){
        selectMonth=selectMonth - 1;
        const monthday = document.getElementById("month-day");
        monthday.innerHTML = "";
        displayCal();
    }

const next = function(){
        selectMonth=selectMonth +1;
        const monthday = document.getElementById("month-day");
        monthday.innerHTML = "";
        displayCal();
    }

const insertWalkHistory = function(el){
	const temp =  $("#walktext");
	if(loginMemberNo==0){
		alert("로그인 후 이용해주세요!");
		temp.val("");
		return;
	}
	walktext = temp.val();
	if(walktext.trim().length==0){
		alert("내용을 작성해주세요!");
		return;
	}
	$.ajax({
			url : contextPath+"/walk/walkinsert",
			data : {
				"loginMemberNo" : loginMemberNo,
				"walktext" : walktext,
				"continueWalk" : continueWalk+1,
			},
			dataType : "json",
			type : "POST",
			success: function(nboardList){
				console.log(nboardList);
				if(nboardList ==null){
					//이미 체크
					alert("산책일지 작성 완료! 오늘 포인트는 모두 획득했습니다.");
				}
				else{
					const getpoint = 20 + 20*(continueWalk+1)
					alert("산책일지 작성 완료! "+getpoint+"점 획득!");
					const continueCheck = $("#continueCheck").val();
					$("#continueCheck").val(continueCheck+1);
				}
				temp.val("");
			    monthday.innerHTML = ""
				displayCal(selectMonth);
				setTimeout(function() {
				  $(".walkdone:last").click();
				}, 50);
			},
			error : function(req, status, error){
                    console.log("에러");
                    console.log(req.responseText);
            }
			
	})
    const monthday = document.getElementById("month-day");

}

const resetWalkText = function(el){
	$(el).prev().prev().val("")
}

const selecDatewalkList = function(date){
	$("#walkwrite").html("");
		$.ajax({
			url : contextPath+"/walk/walkList",
			data : {
				"loginMemberNo" : loginMemberNo,
				"selectDay" : continueWalk+1
			},
			dataType : "json",
			type : "POST",
			success: function(nboardList){
				console.log(nboardList);
				if(nboardList ==null){
					//이미 체크
					alert("산책일지 작성 완료! 오늘 포인트는 모두 획득했습니다.");
					
				}
				else{
					const getpoint = 20 + 20*(continueWalk+1)
					alert("산책일지 작성 완료! "+getpoint+"점 획득!");
					const continueCheck = $("#continueCheck").val();
					$("#continueCheck").val(continueCheck+1);
				}
				temp.val("");
			},
			error : function(req, status, error){
                    console.log("에러");
                    console.log(req.responseText);
            }
			
	})
}
$("#addwalkimg").on("click",function(){
/*	if(loginMemberNo==0){
		alert("로그인 후 이용해주세요!");
		temp.val("");
		return;
	}*/
	document.getElementById("walkfile").click();
	console.log("이미지 삽입!");
})

const loadImg = function(el){
	if (el.files[0]) {
		var reader = new FileReader();
		reader.readAsDataURL(el.files[0]);
		reader.onload = function(e) {
			$("#addwalkimg").html("");
			const img = $("<img>");
			img.css("width","100%");
			img.css("height","400px");
			img.attr("src", e.target.result);
			$("#addwalkimg").append(img);
		}
	}
}
