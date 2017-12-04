<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	$(document).ready(function() {
		//date
		var options = { 
				now: "12:00", //hh:mm 24 hour format only, defaults to current time 
				twentyFour: true, //Display 24 hour format, defaults to false 
				upArrow: 'wickedpicker__controls__control-up', //The up arrow class selector to use, for custom CSS 
				downArrow: 'wickedpicker__controls__control-down', //The down arrow class selector to use, for custom CSS 
				close: 'wickedpicker__close', //The close class selector to use, for custom CSS 
				hoverState: 'hover-state', //The hover state class to use, for custom CSS 
				title: 'TabelScanner', //The Wickedpicker's title, 
				showSeconds: false, //Whether or not to show seconds, 
				secondsInterval: 1, //Change interval for seconds, defaults to 1  , 
				minutesInterval: 1, //Change interval for minutes, defaults to 1 
				beforeShow: null, //A function to be called before the Wickedpicker is shown 
				show: null, //A function to be called when the Wickedpicker is shown 
				clearable: false, //Make the picker's input clearable (has clickable "x")  
			}; 
		$('.timepicker').wickedpicker(options);
		
		 $( function() {
			    $( "#datepicker" ).datepicker();
		 });
	});
</script>
<style type="text/css">
#droppable>div {
	margin: 0;
	width: 100px;
	height: 100px;
	background: rgba(0, 0, 0, 0.1);
	position: absolute;
}
</style>
<script>
	function formCheck() {
		// 결제방식이 선택되었는지 체크
		var payment = document.getElementsByName('resPayStatement');
		var tableCheck = document.resForm.tableList;
		var cnt = 0;
		for (var i = 0; i < payment.length; i++) {
			if (payment[i].checked == true) {
				
				// 결제방식 선택 후, 테이블이 선택되었는지 체크
				for(var j = 0; j < tableCheck.length; j++) {
					if (tableCheck[j].checked) {	// checkbox 체크되어있다면~
						cnt++;
					} 
				}
				if (cnt == 0) {
					alert('테이블은 최소 하나 이상 선택해주세요!');
					return false;
				} else {
					alert('최종 예약으로 진행합니다.');
					return true;
				}
				
			} else {
				alert('결제수단을 선택하세요!!');
				return false;
			}
		}
	}
</script>

</head>
<body>
	<c:set scope="page" var="current" value="<%=new Date(System.currentTimeMillis()) %>" />
	<h1>음식점 예약하기</h1>
	<div class="container">
		<form method="post" name="resForm"
			action="${initParam.rootPath}/addReservation.do"
			onsubmit="return formCheck()">
			<h2>예약 2/3</h2>
			<div class="form-group">
				<label>예약 식당</label> 
				<input type="text" name="restaurantName" value="${requestScope.restaurantName }" readonly>
			</div>
				<input type="hidden" name="businessId" value="${requestScope.businessId }">
		<!-- 	<div class="form-group">
				<label for="resNum">예약 번호</label> <input type="number" name="resNum"
					placeholder="예약번호는 나중에 안받지~" min="1" required>
			</div> -->
			<div class="form-group">
				<label for="date">예약 희망 날짜</label> 
				<input type="date" name="resDate" min="${current }" max="2019-12-31"
					value="${requestScope.resDate }" required>
			</div>
			<div class="form-group">
				<label for="resStartTime">예약 희망 시간</label> 
				<input type="text" class="form-control timepicker col-sm-2"
					name="resStartTime" value="${requestScope.resTime }" required>
			</div>
			<div class="form-group">
				<label for="resPeople">인원</label> <input type="number"
					name="resPeople" value="${requestScope.resPeople }" min="1" required>
			</div>
			<div id="payment">
				<label class="radio-inline"><input type="radio" name="resPayStatement" value="카드결제"> 카드결제 </label>
				<label class="radio-inline">
				<input type="radio" name="resPayStatement" value="무통장입금">무통장입금 </label>
			</div>
			<!-- 나중엔 사용자가 고른 list 에서 받아올 값 select로 테스트 -->
			<div class="form-group">
				
				<br><h3>테이블을 선택하세요</h3><br>
					<c:forEach items="${requestScope.tableList }" var="table" varStatus="cnt">
						<c:if test="${cnt.index % 2 == 0 }">
							<br>
						</c:if>
						<label>테이블 번호 : ${table.tableNum }, 최대인원 : ${table.tablePeople }
						<input type="checkbox" name="tableList" value="${table.tableSeq }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</label>
					</c:forEach>
			</div>
			<input type="submit" class="btn btn-info" value="예약하기">
			<sec:csrfInput />
		</form>
		
		<div id="droppable" style="width: 700px; height: 500px; border: 1px solid #000; position: relative; margin-top: 30px;">
		<c:forEach items="${requestScope.table}" var="item">
					<div class ="draggable" style="top: ${item.yLocation}px; left: ${item.xLocation}px;" >
						<span>${item.tableNum }번 테이블</span> <p>
						<span>${item.tablePeople}명</span> 
						    
					</div>
			
		</c:forEach>
		</div>
		
		
	</div>

</body>
</html>