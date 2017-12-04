<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<h1>TEST - 음식점 목록</h1>
	<form method="post" name="restaurantList"
		action="${initParam.rootPath }/restaurantList.do"
		onsubmit="return selectCheck()">
		<h2>예약 1/3</h2>
		<div class="form-group">
			<label for="businessId">음식점</label> <select name="businessId"
				class="form-control">
				<option value="none">음식점을 선택하세요</option>
				<c:choose>
					<c:when test="${requestScope.restaurantList == null }">
						<option value="nothing">검색 조건에 맞는 음식점이 없습니다.</option>
					</c:when>
					<c:otherwise>
						<c:forEach items="${requestScope.restaurantList }" var="restaurant">
							<option value="${restaurant.businessId }">${restaurant.rtName }</option>
						</c:forEach>
					</c:otherwise>
				</c:choose>

			</select>
		</div>
		<input type="hidden" name="resPeople" value="${requestScope.resPeople }"/>
		<input type="hidden" name="resDate" value="${requestScope.resDate }"/>
		<input type="hidden" name="resTime" value="${requestScope.resTime }"/>
		<input type="submit" class="btn btn-info" value="예약 진행">
		<sec:csrfInput />
	</form>
	
	
	
	
	
</body>
</html>