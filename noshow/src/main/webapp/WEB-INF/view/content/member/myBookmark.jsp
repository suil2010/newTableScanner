<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
	$(document).ready(function() {
		$(".item").on("click", function() {
			$(".businessId").val($(this).find("input[name=businessId]").val());      
			$("#submitform").submit();  
		});   
	});
</script>
<div class="container">

<h1>내 즐겨찾기 목록</h1>
	
	<h2>즐겨찾기 등록 내역</h2>
	<c:choose>
		<c:when test="${requestScope.myBookmarkList == null}">
			<form name="restaurantList" action="${initParam.rootPath }/index.do"  id="submitform">
	  			<input type="hidden" name="businessId" class="businessId"/>
				<sec:csrfInput />
			</form>
		</c:when>
		<c:otherwise>
			<form method="post" name="restaurantList" action="${initParam.rootPath }/restaurantListByName.do"  id="submitform">
	  			<input type="hidden" name="businessId" class="businessId"/>
				<sec:csrfInput />
			</form>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${requestScope.myBookmarkList == null }">
			<div class="item clearfix col-md-6 col-sm-12" style="height: 140px; padding: 10px;">
					<div style="border: 1px solid #000; cursor: pointer; height: 100%;"> 
						<span><b>${requestScope.emptyBookmark }</b></span><br>	
						<h4>클릭하시면 홈으로 이동합니다!	</h4>
					</div>
				</div>
		</c:when>
		<c:otherwise>
			<c:forEach items="${requestScope.myBookmarkList }" var="bookmark">
				<div class="item clearfix col-md-6 col-sm-12" style="height: 140px; padding: 10px;">
					<div style="border: 1px solid #000; cursor: pointer; height: 100%;">

						<span><b>${bookmark.restaurant.rtName }</b></span>
						<span style="color:red;float:right;font-size:30px">♥ </span><br>
						위치 : ${bookmark.restaurant.rtAddress}<br>
						업종 : ${bookmark.restaurant.rtField}<br>
						휴무 : 매주 ${bookmark.restaurant.rtHoliday }요일<br>
						영업시간 : ${bookmark.restaurant.rtOpen} ~ ${bookmark.restaurant.rtClose}
						<input type="hidden" name="businessId" value="${bookmark.restaurant.businessId }" />
					</div>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</div>