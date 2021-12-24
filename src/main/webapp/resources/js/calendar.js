    let selectMonth = 0;
	
    window.onload= function(){
		
		displayCal(selectMonth);
    }

    let selectMonth = 0;
    window.onload= function(){
	//	회원 기록 => 카테고리 100 + 해당 회원 기록
    //달력 생성 시 해당하는 월과 기록 넣고 
		displayCal(selectMonth);
    }

	
	const memberWalkHistory = function(start, end){

		const startwalk = getYyyyMmDdMmSsToString(startdate);
		const endwalk = getYyyyMmDdMmSsToString(enddate);
		
		if(loginMemberNo==""){ 
			console.log("로그인필요");
			return; }
		
		$.ajax({
			url : contextPath+"/walk/walkhistory",
			data : {
				"startwalk" : startwalk,
				"loginMemberNo" : loginMemberNo,
				"endwalk" : endwalk
			},
			dataType : "json",
			type : "POST",
			success : function(history){
					
					
					
				
			},
			error : function(req, status, error){
                    console.log("좋아요 에러");
                    console.log(req.responseText);
            }
			
		})
		return;
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
    var dayList =Array('일', '월', '화', '수' , '목' , '금' , '토' );
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

    var enMonthName = new Array('1월','2월','3월','4월','5월','6월',
        '7월','8월','9월','10월','11월','12월');
    var enMonthName = new Array('1월 (January)','2월 (February)','3월 (March)','4월 (April)','5월 (May)','6월 (June)',
        '7월 (July)','8월 (August)','9월 (September)','10월 (October)','11월 (November)','12월 (December)');
    
    	
        const tmonth = document.getElementById("today-month");
        const monthday = document.getElementById("month-day");
        tmonth.innerText = year+"년 " +enMonthName[month-1] 
		
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
									$(td).addClass("walkdone");
							    }
								else{
									$(td).addClass("nonewalk");
								}
							        console.log("됨");
									$(td).addClass("walkdone");
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
	const walktext =  $(el).prev().val("");
	
	$.ajax({
			url : contextPath+"/walk/walkinsert",
			data : {
				"loginMemberNo" : loginMemberNo,
				"walktext" : walktext
			},
			dataType : "json",
			type : "POST",
			success: function(result){
				
				
			},
			error : function(req, status, error){
                    console.log("좋아요 에러");
                    console.log(req.responseText);
            }
			
	})
	
}
const resetWalkText = function(el){
	$(el).prev().prev().val("")
}
/**
 * 
 */