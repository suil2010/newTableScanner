<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>

</script>

<div class="container">
	<table class="table">
		<thead class="thead-light">
			<tr>
				<th>순위</th>
				<c:if test="${requestScope.avgCnt != null }">
					<th>평점</th>
				</c:if>
				<th>상호</th>
				<th>업종</th>
				<th>지역</th>
				<th>전화번호</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${requestScope.rankingList }" var="rank" varStatus="cnt">
				<tr height="150px">
					<td>${cnt.count }</td>
					<c:if test="${requestScope.avgCnt != null }">
						<td>${rank.avgGrade }</td>
					</c:if>
					<td>
					<img src="${initParam.rootPath }/rtPicture/${rank.restaurant.rtPicture}" alt="식당 이미지" style="width: 100%;">
					<sec:authorize access="!isAuthenticated()">   <!-- 비회원 -->
						<a href="${initParam.rootPath }/login_form.do" >${rank.restaurant.rtName }</a>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<a href="${initParam.rootPath }/restaurantListByName.do?businessId=${rank.businessId}" >${rank.restaurant.rtName }</a>
					</sec:authorize>
					</td>
					<td>${rank.restaurant.field.fieldVal}</td>
					<td>${rank.restaurant.rtAddress }</td>
					<td>${rank.restaurant.rtTel }</td>
				</tr>
				
			</c:forEach>

		</tbody>
	</table>
</div>
