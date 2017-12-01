<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
	function selectCheck() {
		// 음식점이 선택되었는지 체크
		var selCheck = document.restaurantList.businessId;
		var flag = false;
		if (selCheck.value == 'none') {
			alert('음식점을 선택해주세요!');
			selCheck.focus();
			return flag;
		} else {
			alert('예약을 진행합니다');
			flag = true;
			return flag;
		}
	}
</script>
</head>
<body>
<h1>TEST - 음식점 목록 </h1>
<form method="post" name="restaurantList" action="${initParam.rootPath }/restaurantList.do" onsubmit="return selectCheck()">
	<h2>예약 1/3</h2>
	<div class="form-group">
      	<label for="businessId">음식점</label> 
      	<select name="businessId" class="form-control">
				<option value="none">음식점을 선택하세요</option>
				<option value="id-1">현준이네중국집</option>
				<option value="id-2">동웅이네숯불갈비</option>
				<option value="id-3">지수네파스타</option>
				<option value="id-4">수찬이네스시</option>
			</select>
	</div>
	<input type="submit" class="btn btn-info" value="예약 진행">
	<sec:csrfInput />
</form>
</body>
</html>