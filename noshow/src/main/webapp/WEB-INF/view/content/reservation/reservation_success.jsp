<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>예약이 성공적으로 완료되었습니다!</h1>
	<div class="container">
		<h2>예약 3/3 - 예약 정보</h2>
		<table border="1" class="table table-bordered">
			<thead>
				<tr>
					<td>예약 번호</td>
					<td>음식점 명</td>
					<td>예약자 ID</td>
					<td>예약 날짜</td>
					<td>예약 시간</td>
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
					<td>${requestScope.restaurantName }</td>
					<td>${requestScope.reservation.memberId }</td>
					<td>${requestScope.reservation.resDate }</td>
					<td>${requestScope.reservation.resStartTime }</td>
					<td>${requestScope.reservation.resPeople }</td>
					<td>
					<c:forEach items="${requestScope.tableList }" var="tables" varStatus="cnt">
						${tables }<br>
					</c:forEach>
					</td>
					<td>${requestScope.reservation.resPaidTime }</td>
					<td>${requestScope.reservation.resPayStatement }</td>
					<td>${requestScope.reservation.resPrice }</td>
				</tr>

			</tbody>
		</table>
	</div>



</body>
</html>