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
		var selCheck = document.resForm;
		var sel_type = null;
		for (var i = 0; i < payment.length; i++) {
			if (payment[i].checked == true) {

				if (selCheck.businessId.value == 'none') {
					alert('음식점을 선택해주세요!!');
					selCheck.businessId.focus();
					return false;
				} else {
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
			<div class="form-group">
				<label for="resNum">예약 번호</label> <input type="number" name="resNum"
					placeholder="예약번호는 나중에 안받지~" min="1" required>
			</div>
			<div class="form-group">
				<label for="date">예약 희망 날짜</label> <input type="date" name="resDate"
					placeholder="예약희망 날짜를 입력하세용" required>
			</div>
			<div class="form-group">
				<label for="resStartTime">예약 희망 시간</label> <input type="datetime"
					name="resStartTime" placeholder="몇시에 오실건가요?" required>
			</div>
			<div class="form-group">
				<label for="resPeople">인원</label> <input type="number"
					name="resPeople" placeholder="몇명이오는지알려줭" min="1" required>
			</div>
			<div id="payment">
				<label class="radio-inline">카드결제</label> <input type="radio"
					name="resPayStatement" class="radio" value="카드결제"> <label
					class="radio-inline">무통장입금</label> <input type="radio"
					name="resPayStatement" class="radio" value="무통장입금">
			</div>
			<!-- 나중엔 사용자가 고른 list 에서 받아올 값 select로 테스트 -->
			<div class="form-group">
				<select name="tableSeq" class="form-control">
				<option value="none">테이블을 선택하세요</option>
					<c:forEach items="${requestScope.tableList }" var="table">
						<option value="${table.tableSeq }">테이블 번호 : ${table.tableNum }, 인원 : ${table.tablePeople }</option>		
					</c:forEach>
			</select>
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