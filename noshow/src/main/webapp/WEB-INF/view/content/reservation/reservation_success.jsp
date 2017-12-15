<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container" style="padding-top: 40px;"> 
	<h2>예약 3/3 - 예약 정보</h2>
	<table border="1" class="table table-bordered">
		<thead>
			<tr>
				<td>예약 번호</td>
				<td>음식점 명</td>
				<td>예약자 ID</td>
				<td>예약 날짜 & 시간</td>
				<td>예약 인원</td>
				<td>예약 테이블</td>
				<td>결제 시각</td>
				<td>결제 수단</td>
				<td>예약 금액</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${requestScope.reservation.resNum }</td>
				<td>${requestScope.reservation.restaurant.rtName }</td>
				<td>${requestScope.reservation.memberId }</td>
				<td>${requestScope.reservation.resStartTime }</td>
				<td>${requestScope.reservation.resPeople }</td>
				<td><c:forEach items="${requestScope.reservation.orderTable }" var="tables" varStatus="cnt">
						${tables.table.tableNum }<br>
					</c:forEach></td>
				<td><fmt:formatDate value="${requestScope.reservation.resPaidTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${requestScope.reservation.resPayStatement }</td>
				<td>${requestScope.reservation.resPrice }</td>
			</tr>

		</tbody>
	</table>
</div>



</body>
</html>