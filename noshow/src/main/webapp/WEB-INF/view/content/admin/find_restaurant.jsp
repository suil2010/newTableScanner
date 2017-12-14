<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
	<h2>가입된 정보</h2>
	<div class="col-sm-12" style="background: #fff; min-height: 600px; margin-bottom: 10px; padding-top: 20px;">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>사업자번호</th>
					<th>매장 이름</th>
					<th>매장 전화번호</th>
					<th>매장 업종</th>
					<th>휴무일</th>
					<th>오픈시간</th>
					<th>마감시간</th>
					<th>테이블 이용시간</th>
					<th>음식점 위치</th>
					<th>수용가능 인원</th>
					<th>1인당 예약 금액</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.rt}" var="item">
					<tr>
						<td>${item.rtNum }</td>
						<td>${item.rtName }</td>
						<td>${item.rtTel }</td>
						<td>${item.field.fieldVal }</td>
						<td>${item.holiday.holidayVal}</td>
						<td>${item.rtOpen }</td>
						<td>${item.rtClose }</td>
						<td>${item.term.termVal }</td>
						<td>${item.rtAddress }</td>
						<td>${item.rtCapacity }</td>
						<td>${item.rtDeposit }</td>
					</tr>
				</c:forEach>
			</tbody>
			<sec:csrfInput />
		</table>
	</div>
</div>