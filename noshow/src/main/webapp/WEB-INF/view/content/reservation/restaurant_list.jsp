<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>TEST - 음식점 목록 </h1>
<form method="post" name="restaurantList" action="${initParam.rootPath }/restaurantList.do">
	<h2>예약 1/3</h2>
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
	<input type="submit" class="btn btn-info" value="예약 진행">
	<sec:csrfInput />
</form>
</body>
</html>