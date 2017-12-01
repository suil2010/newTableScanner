<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>내 예약 조회</h1>
	<div class="container">
	<h2>예약 내역</h2>
	<table class="table table-bordered">
		<thead>
			<tr>
			<th>예약 번호</th>
			<th>음식점 명</th>
			<th>예약 날짜 & 시간</th>
			<th>결제 완료 시간</th>
			<th>예약 인원</th>
			<th>예약 금액</th>
			<th>예약 테이블</th>
			<th>결제 방법</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.reservationList }" var="reservation">
				<tr>
					<td>${reservation.resNum }</td>
					<td>${reservation.restaurant.rtName }</td>
					<td>${reservation.resStartTime }</td>
					<td><fmt:formatDate value="${reservation.resPaidTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>					
					<td>${reservation.resPeople }</td>
					<td>${reservation.resPrice }</td>
					<td>
					<c:forEach items="${reservation.orderTable }" var="table">
						${table.tableSeq }
					</c:forEach>
					</td>
					<td>${reservation.resPayStatement }</td>
				</tr>
			</c:forEach>
		</tbody>
		<sec:csrfInput/>
	</table>
	</div>
</body>
</html>