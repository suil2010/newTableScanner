<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.mainTemplate {
	background: #FAFAFA;
}
</style>    
<script>
var curDate = new Date();

var curTime = curDate.getFullYear() + "-" + (curDate.getMonth() + 1) + "-" + curDate.getDate() + " " + curDate.getHours() + ":" + curDate.getMinutes() + ":" + curDate.getSeconds();

</script>
<div class="col-sm-10" style="background: #fff; min-height: 600px; margin-bottom: 10px; padding-top: 20px;"> 

	<table class="table table-hover">    
		<thead>
			<tr>
				<th>예약 번호</th>
				<th>음식점 명</th>
				<th>예약자ID</th>
				<th>예약 날짜 & 시간</th>
				<th>결제 완료 시간</th>
				<th>예약 인원</th>
				<th>예약 금액</th>
				<th>예약 테이블</th>
				<th>결제 방법</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<c:set scope="page" var="currentDate" value="<%=new Date(System.currentTimeMillis())%>"/>
			<fmt:formatDate value="${currentDate }" pattern="yyyy-MM-dd HH:mm:ss" var="sysDate"/>
			<c:forEach items="${requestScope.reservationList }" var="reservation">
				<tr>
					<td>${reservation.resNum }</td>
					<td>${reservation.restaurant.rtName }</td>
					<td>${reservation.memberId }</td>
					<td>${reservation.resStartTime }</td>
					<td><fmt:formatDate value="${reservation.resPaidTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${reservation.resPeople }명</td>
					<td>${reservation.resPrice }원</td> 
					<td><c:forEach items="${reservation.orderTable }" var="tables">
						${tables.table.tableNum }번   
					</c:forEach></td>
					<td>${reservation.resPayStatement }</td>
					<td>
						<c:choose>
							<c:when test="${reservation.resStartTime >= sysDate }">
							<!-- action="${initParam.rootPath}/cancelReservation.do" -->
								<form action="${initParam.rootPath}/cancelReservation.do" method="post">
									<input type="submit" value="직권 예약취소" onclick="return confirm('정말 예약을 취소하시겠습니까?')">
									<input type="hidden" name="resNum" value="${reservation.resNum }">
									<sec:csrfInput />
								</form>
							</c:when>
							<c:otherwise>
							예약 확정
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<sec:csrfInput />
	</table>
</div>