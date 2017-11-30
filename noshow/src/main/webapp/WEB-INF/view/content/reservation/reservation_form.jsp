<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
	function formCheck() {
		// 결제방식이 선택되었는지 체크
		var payment = document.getElementsByName('resPayStatement');
		var selCheck = document.resForm;
		var sel_type = null;
		for(var i=0; i<payment.length;i++) {
			if (payment[i].checked == true) {
				
				if (selCheck.businessId.value == '') {
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
<form method="post" name="resForm" action="${initParam.rootPath}/addReservation.do" onsubmit="return formCheck()">
 	  <div class="form-group">
 	  	<label for="resNum">예약 번호</label>
 	  	<input type="number" name="resNum" placeholder="예약번호는 나중에 시퀀스로 할거에요~" min="1" required>
 	  </div>
 	  <div class="form-group">
 	  	<label for="date">예약 희망 날짜</label>
      <input type="date" name="resDate" placeholder="예약희망 날짜를 입력하세용" required>
 	  </div>
 	   <div class="form-group">
 	  	<label for="resStartTime">예약 희망 시간</label>
      <input type="datetime" name="resStartTime" placeholder="몇시에 오실건가요?" required> 
 	  </div>
 	  <div class="form-group">
 	  	<label for="resPeople">인원</label>
      <input type="number" name="resPeople" placeholder="몇명이오는지알려줭" min="1"   required>
 	  </div>
      <div id="payment">
         <label class="radio-inline">카드결제</label> <input type="radio" name="resPayStatement" class="radio" value="카드결제"> 
         <label class="radio-inline">무통장입금</label> <input type="radio" name="resPayStatement" class="radio" value="무통장입금">
      </div>
      <!-- 나중엔 사용자가 고른 list 에서 받아올 값 select로 테스트 -->
      <div class="form-group">
      	<label for="businessId">음식점</label> 
      	<select name="businessId" class="form-control">
				<option value="">음식점을 선택하세요</option>
				<option value="id-1">현준이네중국집</option>
				<option value="id-2">동웅이네숯불갈비</option>
				<option value="id-3">지수네파스타</option>
				<option value="id-4">수찬이네스시</option>
			</select>
		</div>
  	 <div class="form-group">
 	  	<label for="tableSeq">테이블번호</label>
      <input type="number" name="tableSeq" placeholder="어디앉을거야?" min="1"   required>
 	  </div>
      <input type="submit" class="btn btn-info" value="예약하기">
      <sec:csrfInput/>
   </form>
</div>
 
</body>
</html>