<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.mainTemplate {
	background: #FAFAFA;
}
</style>    
<div class="col-sm-10" style="background: #fff; min-height: 600px; margin-bottom: 10px; padding-top: 20px;"> 

	<table class="table table-hover">    
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
					<td><fmt:formatDate value="${reservation.resPaidTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${reservation.resPeople }명</td>
					<td>${reservation.resPrice }원</td> 
					<td><c:forEach items="${reservation.orderTable }" var="tables">
						${tables.table.tableNum }번   
					</c:forEach></td>
					<td>${reservation.resPayStatement }</td>
				</tr>
			</c:forEach>
		</tbody>
		<sec:csrfInput />
	</table>
</div>
