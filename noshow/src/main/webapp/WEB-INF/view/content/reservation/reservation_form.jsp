<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<style type="text/css">
#droppable>div {
	margin: 0;
	width: 100px;
	height: 100px;
	background: rgba(0, 0, 0, 0.1);
	position: absolute;
}
</style>
<script>
	$(document).ready(function() {
		
		$('.timepicker').timepicker({
			timeFormat: 'HH:mm',
			interval: 30,
			startTime:'07:00',
			dynamic:false,
			dropdown:true,
			scrollbar:true
		});	
		//date

		/* var options = {
			now : "${requestScope.resTime}", //hh:mm 24 hour format only, defaults to current time 
			twentyFour : true, //Display 24 hour format, defaults to false 
			upArrow : 'wickedpicker__controls__control-up', //The up arrow class selector to use, for custom CSS 
			downArrow : 'wickedpicker__controls__control-down', //The down arrow class selector to use, for custom CSS 
			close : 'wickedpicker__close', //The close class selector to use, for custom CSS 
			hoverState : 'hover-state', //The hover state class to use, for custom CSS 
			title : 'TabelScanner', //The Wickedpicker's title, 
			secondsInterval : 1, //Change interval for seconds, defaults to 1  , 
			minutesInterval : 1, //Change interval for minutes, defaults to 1 
		};
		$('.timepicker').wickedpicker(options); */

		$("#datepicker").datepicker({
			constrainInput : true,
			currentText : "Now",
			dayNames : ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"],
			monthNames : ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
			selectOtherMonths : true,
			numberOfMonths : 2,
			showCurrentAtPos : 0,
			minDate : new Date(),
			maxDate : "+3m"

		});
	});
	function formCheck() {
		// 결제방식이 선택되었는지 체크
		var payment = document.getElementsByName('resPayStatement');
		var tableCheck = document.resForm.tableList;
		var cnt = 0;
		for (var i = 0; i < payment.length; i++) {
			if (payment[i].checked == true) {

				// 결제방식 선택 후, 테이블이 선택되었는지 체크
				for (var j = 0; j < tableCheck.length; j++) {
					if (tableCheck[j].checked) { // checkbox 체크되어있다면~
						alert(tableCheck[j].value);
						cnt++;
					}
				}
				if (cnt == 0) {
					alert('테이블은 최소 하나 이상 선택해주세요!');
					return false;
				} else {
					alert('최종 예약으로 진행합니다.');
					return true;
				}

			} else {
				alert('결제수단을 선택하세요!!');
				return false;
			}
		}
	}
</script>



<div class="container">
	<div class="row">
		<div class="col-sm-6">
			<img src="..." alt="식당 이미지">
		</div>
		<div class="col-sm-6">설명 설명 설명</div>
	</div>

	<div class="row">
		<div class="col-sm-8" style="min-height: 1350px;">
			<ul class="nav nav-tabs" role="tablist" id="myTab">
				<li class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">메뉴</a></li>
				<li><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">리뷰</a></li>
				<li><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">문의</a></li>
				<li><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">테이블정보</a></li>
			</ul>

			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="home">1</div>
				<div role="tabpanel" class="tab-pane" id="profile">2</div>
				<div role="tabpanel" class="tab-pane" id="messages">3</div>
				<div role="tabpanel" class="tab-pane" id="settings">
					<div id="droppable" style="width: 700px; height: 500px; border: 1px solid #000; position: relative; margin-top: 30px;">
						<c:forEach items="${requestScope.allTable}" var="alltables">
							<div class="draggable" style="top: ${alltables.yLocation}px; left: ${alltables.xLocation}px;">
								<span>${alltables.tableNum }번 테이블</span>
								<p>
									<span>${alltables.tablePeople}명</span>
							</div>

						</c:forEach>
					</div>
				</div>
			</div>

		</div>

		<form method="post" name="resForm" action="${initParam.rootPath}/addReservation.do" onsubmit="return formCheck()" class="col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">주문표</h3>
				</div>
				<div class="panel-body">
					<c:set scope="page" var="current" value="<%=new Date(System.currentTimeMillis())%>" />
					<div class="form-group">
						<label>예약 식당</label> <input type="text" class="form-control" name="restaurantName" value="${requestScope.restaurantName }" readonly>
					</div>
					<input type="hidden" name="businessId" value="${requestScope.businessId }">

					<div class="form-group">
						<label for="date">예약 희망 날짜</label> 
						<c:choose>
							<c:when test="${requestScope.resDate == null }">
								<input type="date" class="form-control" name="resDate" min="${current }"max="2019-12-31" placeholder="예약희망 날짜를 입력하세용" required>
							</c:when>
							<c:otherwise>
								<input type="date" value="${requestScope.resDate }" class="form-control" name="resDate" min="${current }"max="2019-12-31" placeholder="예약희망 날짜를 입력하세용" required>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<label for="resStartTime">예약 희망 시간</label>
						<c:choose>
							<c:when test="${requestScope.resTime == null }">
								<input type="text" class="form-control timepicker" name="resStartTime" placeholder="몇시에 오실건가요?" required>
							</c:when>
							<c:otherwise>
								<input type="text" class="form-control timepicker" name="resStartTime" value="${requestScope.resTime }" placeholder="몇시에 오실건가요?" required>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<label for="resPeople">인원</label>
						<c:choose>
							<c:when test="${requestScope.resPeople == null }">
								<input type="number"  class="form-control" name="resPeople" placeholder="몇명이오는지알려줭" min="1" required>
							</c:when>
							<c:otherwise>
								<input type="number" value="${requestScope.resPeople }" class="form-control" name="resPeople" placeholder="몇명이오는지알려줭" min="1" required>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="payment">
						<label class="radio-inline"><input type="radio" name="resPayStatement" value="카드결제"> 카드결제 </label> <label class="radio-inline">
							<input type="radio" name="resPayStatement" value="무통장입금">무통장입금
						</label>
					</div>
					<!-- 나중엔 사용자가 고른 list 에서 받아올 값 select로 테스트 -->
					<div class="form-group">

						<br>
						<h3>예약 가능한 테이블</h3>
						<br>
						<c:choose>
							<c:when test="${requestScope.tableList == null}">
								예약을 진행하고싶으면 예약내용으로 검색하세요 일단...여긴 추가구현할게요..
								<%-- <c:forEach items="${requestScope.allTable }" var="tables" varStatus="cnt">
									<c:if test="${cnt.index % 2 == 0 }">
										<br>
									</c:if>
									<label>테이블 번호 : ${tables.tableNum }, 최대인원 : ${tables.tablePeople } 
									<input type="checkbox" name="tableList" value="${tables.tableSeq }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</label>
								</c:forEach> --%>
							</c:when>
							<c:otherwise>
								<c:forEach items="${requestScope.tableList }" var="tables" varStatus="cnt">
									<c:if test="${cnt.index % 2 == 0 }">
										<br>
									</c:if>
									<label>테이블 번호 : ${tables.tableNum }, 최대인원 : ${tables.tablePeople } 
									<input type="checkbox" name="tableList" value="${tables.tableSeq }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</label>
								</c:forEach>
								<div class="panel-footer">
									<input type="submit" class="btn btn-info" value="예약하기">
									<button type="reset" class="btn btn-default">초기화</button>
								</div>
							</c:otherwise>
						</c:choose>
						
					</div>
					
					<sec:csrfInput />
				</div>

			</div>
		</form>

	</div>
</div>
