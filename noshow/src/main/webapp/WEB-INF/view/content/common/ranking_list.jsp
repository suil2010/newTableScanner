<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<table class="table" style="padding-top: 30px;">
		<thead class="thead-light">
			<tr>
				<th width="10%;">순위</th>
				<c:if test="${requestScope.avgCnt != null }">
					<th>평점</th>
				</c:if>
				<th width="20%;">이름</th> 
				<th style="width: 10%;">업종</th>
				<th>지역</th>
				<th>전화번호</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${requestScope.rankingList }" var="rank" varStatus="cnt">
				<tr>
					<td>${cnt.count }</td>
					<c:if test="${requestScope.avgCnt != null }">
						<td>${rank.avgGrade }</td>
					</c:if>
					<td>
						<!-- 비회원 --> <sec:authorize access="!isAuthenticated()">
							<a href="${initParam.rootPath }/login_form.do">${rank.restaurant.rtName }</a>
						</sec:authorize> <!-- 회원 --> <sec:authorize access="isAuthenticated()">
							<a href="${initParam.rootPath }/restaurantListByName.do?businessId=${rank.businessId}">${rank.restaurant.rtName }</a>
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
