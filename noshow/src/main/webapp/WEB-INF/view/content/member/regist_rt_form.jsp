<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<link href=/>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <style type="text/css">

</style>
<script type="text/javascript" src="jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="wickedpicker.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#timepicker".on("click", function(){
		$.wickedpicker({
			twentyFour:false,
			upArrow:'wickedpicker__controls__control-down',
			close: 'wickedpicker__close',
			hoverState: 'hover-state',
			title: 'Timepicker',
			showSeconds: false,
			secondsInterval: 1,
			minutesInterval: 1,
			beforeShow: null,
			show: null,
			clearable: false,
			
		});
	}));
})-->


</head>
<body>
<h1> 식당등록 </h1>
	<form method="post" action="${initParam.rootPath}/join_rt.do" enctype="multipart/form-data">
	<input type="hidden" name="businessId" value="<sec:authentication property='principal.memberId'/>">
사업자 번호 :	<input type="number" name="rtNum" placeholder="- 를 빼고 입력해주세요"><br>
 매장 이름 : <input type="text" name="rtName" placeholder="매장 이름을 입력하세요"><br>
매장 TEL :	<input type="text" name="rtTel" placeholder="매장 전화번호를 입력하세요"><br>
 업종선택 :
		<select name="rtField">
			<option value=1>한식</option>
			<option value=2>중식</option>
			<option value=3>일식</option>
			<option value=4>분식</option>
			<option value=5>치킨</option>
			<option value=6>피자</option>
			<option value=7>족발</option>
		</select><br>
휴무일 :	
		<select name="rtHoliday">
			<option value=1>월</option>
			<option value=2>화</option>
			<option value=3>수</option>
			<option value=4>목</option>
			<option value=5>금</option>
			<option value=6>토</option>
			<option value=7>일</option>
		</select><br>
<!-- 오픈시간 : <input type="text" id="timepicker" name="rtOpen" class="timepicker" placeholder="오픈시간을 입력하세요"/><br>
마감시간 : <input type="text" id="timepicker" name="rtClose" class="timepicker" placeholder="마감시간을 입력하세요"/><br> -->
오픈시간 :	<input type="date" name="rtOpen" placeholder="오픈시간을 입력하세요"><br>
마감시간 :	<input type="date" name="rtClose" placeholder="마감시간을 입력하세요"><br>
테이블 이용시간 :	
		<select name="rtTerm">
			<option value=1>1시간</option>
			<option value=2>2시간</option>
			<option value=3>3시간</option>
			<option value=4>4시간</option>
			<option value=5>5시간</option>
		</select><br>
		
매장 사진 :	<input type="file" name="rtImg" placeholder="매장 사진을 등록하세요"><br>
매장 위치 :	<input type="text" name="rtAddress" placeholder="매장 위치를 입력하세요(지도API가져올 것!)"><br>
수용가능한 인원 :	<input type="number" name="rtCapacity" placeholder="수용가능한 인원을 입력하세요"><br>
예약금 :	<input type="number" name="rtDeposit" placeholder="1인당 예약금을 입력하세요"><br>  

	<p>
	<input type="submit" value="매장등록하기"> 
	</p>
	
	<sec:csrfInput/>
	</form>
	
</body>
</html>