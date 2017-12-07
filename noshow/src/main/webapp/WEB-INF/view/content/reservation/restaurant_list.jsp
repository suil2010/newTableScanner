<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
	$(document).ready(function() {
		$(".item").on("click", function() {
			$(".businessId").val($(this).find("input[name=businessId]").val());      
			$(".resPeople").val($(this).find("input[name=resPeople]").val());
			$(".resDate").val($(this).find("input[name=resDate]").val());
			$(".resTime").val($(this).find("input[name=resTime]").val());
			$("#submitform").submit();  
		});   
	});
</script>
</head>
<body>
	<h1>TEST - 음식점 목록</h1>

		<h2>예약 1/3</h2>
	<c:choose>
		<c:when test="${requestScope.restaurantList == null}">
			<form name="restaurantList" action="${initParam.rootPath }/index.do"  id="submitform">
	  			<input type="hidden" name="businessId" class="businessId"/>
				<sec:csrfInput />
			</form>
		</c:when>
		<c:when test="${requestScope.resPeople == null }">
			<form method="post" name="restaurantList" action="${initParam.rootPath }/restaurantListByName.do"  id="submitform">
	  			<input type="hidden" name="businessId" class="businessId"/>
				<sec:csrfInput />
			</form>
		</c:when>
		<c:otherwise>
			<form method="post" name="restaurantList" action="${initParam.rootPath }/restaurantList.do"  id="submitform">
	  			<input type="hidden" name="businessId" class="businessId"/>
				<input type="hidden" name="resPeople" class="resPeople"/> 
				<input type="hidden" name="resDate" class="resDate"/> 
				<input type="hidden" name="resTime" class="resTime"/>
				<sec:csrfInput />
			</form>
		</c:otherwise>
	</c:choose>
	
	
	
	<c:choose>
		<c:when test="${requestScope.restaurantList == null }">
			<div class="item clearfix col-md-6 col-sm-12" style="height: 140px; padding: 10px;">
					<div style="border: 1px solid #000; cursor: pointer; height: 100%;"> 
						<span><b>${requestScope.notfountRestaurant }</b></span><br>	
						<h4>클릭하시면 홈으로 이동합니다. 다시 검색해 주세요!	</h4>
					</div>
				</div>
		</c:when>
		<c:otherwise>
			<c:forEach items="${requestScope.restaurantList }" var="restaurant">
				<div class="item clearfix col-md-6 col-sm-12" style="height: 140px; padding: 10px;">
					<div style="border: 1px solid #000; cursor: pointer; height: 100%;"> 
						<span><b>${restaurant.rtName }</b></span><br>
						위치 : ${restaurant.rtAddress}<br>
						업종 : ${restaurant.rtField}<br>
						휴무 : 매주 ${restaurant.rtHoliday }요일<br>
						영업시간 : ${restaurant.rtOpen} ~ ${restaurant.rtClose} 
						<input type="hidden" name="businessId" value="${restaurant.businessId }" /> 
						<input type="hidden" name="resPeople" value="${requestScope.resPeople }"/> 
						<input type="hidden" name="resDate" value="${requestScope.resDate }" /> 
						<input type="hidden" name="resTime" value="${requestScope.resTime }" />
					</div>
				</div>

			</c:forEach>
		</c:otherwise>
	</c:choose>




</body>
</html>