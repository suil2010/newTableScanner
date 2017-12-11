<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	function chaneResInfo() {
		if ($("#resDate").val() == "") {
			alert("날짜를 먼저 지정해주세요~");
			$("#resDate").focus();
			return false;
		}

		if ($("#resStartTime").val() == "") {
			alert("시간도 지정해주셔야 테이블 검색이 되요~");
			$("#resStartTime").focus();
			return false;
		}
		$
			.ajax({
				"url" : "/noshow/reSearchTable.do",
				"type" : "get",
				"data" : {
					"resDate" : $("#resDate").val(),
					"resStartTime" : $("#resStartTime").val(),
					"businessId" : "${requestScope.restaurant.businessId }"
				},
				"dataType" : "json",
				"beforeSend" : function() {
					var txt = "시간 정해주세요 정하라고!";
					if ($("#resStartTime").val() == "") {
						alert("시간을 지정해주세요!!");
						$("#resStartTime").focus();
						$("#selectTableDiv").html(txt);
						return false;
					}
				}, //end of beforeSend
				"success" : function(tableList) {
					var txt = "<br><h3>예약 가능한 테이블</h3><br>";
					$
						.each(
							tableList,
							function() {
								txt += "<label>테이블 번호 :"
									+ this.tableNum
									+ ", 최대 인원 : "
									+ this.tablePeople
									+ "<input type='checkbox' name='tableList' value='" + this.tableSeq
							+ "'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>";
							});
					$("#selectTableDiv").html(txt);
				}, //end of success
				"error" : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
				}
			});
	}

	$(document).ready(function() {

		$("#resDate").on("change", chaneResInfo);
		/* $("#resStartTime").on("Select", chaneResInfo); */

		$("#resStartTime").timepicker({
			timeFormat : 'HH:mm',
			interval : 30,
			dynamic : false,
			dropdown : true,
			scrollbar : true,
			change : chaneResInfo
		});
	});

	// formCheck() - 결제 , 테이블선택 유무를 체크
	function formCheck() {
		var tableCheck = $('input:checkbox[name="tableList"]:checked').val();

		if (tableCheck == null) {
			alert("예약하실 테이블을 선택하세요.");
			return false;
		} else {
			return true;
		}

	}
</script>

<div class="container" style="max-width: 800px;">

	<form name="resForm" action="${initParam.rootPath}/ownerAddReservation.do" onsubmit="return formCheck()" method="post">

		<c:set scope="page" var="current" value="<%=new Date(System.currentTimeMillis())%>" />
		<div class="form-group">
			<label>예약 식당</label> <input type="text" class="form-control" name="restaurantName" value="${requestScope.restaurant.rtName }" readonly>
		</div>

		<div class="form-group">
			<label for="date">예약 희망 날짜</label> <input type="date" id="resDate" class="form-control" name="resDate" min="${current }" max="2019-12-31"
				placeholder="예약희망 날짜를 입력하세용" required
			>
		</div>
		<div class="form-group">
			<label for="resStartTime">예약 희망 시간</label> <input class="form-control timepicker" name="resStartTime" id="resStartTime" placeholder="몇시에 오실건가요?"
				required
			>
		</div>
		<div class="form-group">
			<label for="resPeople">인원</label> <input type="number" class="form-control" name="resPeople" placeholder="몇명이오는지알려줭" min="1" required>
		</div>
		<div id="payment">
			<input type="hidden" name="resPayStatement" value="현장결제">
		</div>

		<c:forEach items="${requestScope.restaurant.usableTable }" var="tables" varStatus="cnt">
			<c:if test="${cnt.index % 2 == 0 }">
				<br>
			</c:if>
			<label>테이블 번호 : ${tables.tableNum }, 최대인원 : ${tables.tablePeople } <input type="checkbox" name="tableList" value="${tables.tableSeq }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</label>
		</c:forEach>
		<sec:csrfInput />

		<div>
			<input type="submit" class="btn btn-info" value="예약하기">
			<button type="reset" class="btn btn-default">초기화</button>
		</div>
	</form>

</div>
