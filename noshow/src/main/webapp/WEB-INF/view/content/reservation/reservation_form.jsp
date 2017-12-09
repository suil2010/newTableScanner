<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	function chaneResInfo(){
		if ($("#resDate").val() == "" ) {
			alert("날짜를 먼저 지정해주세요~");
			$("#resDate").focus();
			return false;
		}
		
/* 		alert($("#resDate").val()); */
		if($("#resStartTime").val() == "") {
			alert("시간도 지정해주셔야 테이블 검색이 되요~");
			$("#resStartTime").focus();
			return false;
		}
		alert($("#resStartTime").val());
		$.ajax({
			"url":"/noshow/reSearchTable.do",
			"type":"get",
			"data":{"resDate":$("#resDate").val(),"resStartTime":$("#resStartTime").val(),"businessId":"${requestScope.restaurant.businessId }"},
			"dataType":"json",
			"beforeSend":function(){
				var txt = "시간 정해주세요 정하라고!";
				if ($("#resStartTime").val() == "") {
					alert("시간을 지정해주세요!!");
					$("#resStartTime").focus();
					$("#selectTableDiv").html(txt);
					return false;
					}
			}, //end of beforeSend
			"success":function(tableList){
				var txt = "<br><h3>예약 가능한 테이블</h3><br>";
				$.each(tableList,function(){
					txt += "<label>테이블 번호 :"+ this.tableNum
							+ ", 최대 인원 : " + this.tablePeople 
							+ "<input type='checkbox' name='tableList' value='" + this.tableSeq
							+ "'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>";
				});
				$("#selectTableDiv").html(txt);
			},	//end of success
			"error":function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		}); 
	}
	
	function addBookmark() {
		alert("즐겨찾기 추가");
		$.ajax({
			"url":"/noshow/addBookmark.do",
			"type":"get",
			"data":{"businessId":"${requestScope.restaurant.businessId }"},
			"dataType":"json",
			"success":function(result){
				var txt = "<b>즐겨찾기<b><br>";
				if(result == 1) {
					alert("즐겨찾기 추가 성공");
					txt += "<button id='deleteBookmarkBtn' class='btn btn-default btn-sm' name='bookmartBtn'>"
						+ "<span class='glyphicon glyphicon-heart'></span>"
						+ "즐겨찾기 삭제"
						+ "</button>"
				} else {
					alert("즐겨찾기 추가 실패");
					txt += "<button id='bookmarkBtn' class='btn btn-default btn-sm' name='bookmartBtn'>"
						+ "<span class='glyphicon glyphicon-heart-empty'></span>"
						+ "즐겨찾기 추가"
						+ "</button>"
				}
				$("#orderDiv").html(txt);
			},	//end of success
			"error":function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
	
	function deleteBookmark() {
		alert("즐겨찾기 삭제");
		$.ajax({
			"url":"/noshow/deleteBookmark.do",
			"type":"get",
			"data":{"businessId":"${requestScope.restaurant.businessId }"},
			"dataType":"json",
			"success":function(result){
				var txt = "<b>즐겨찾기<b><br>";
				if(result == 1) {
					alert("즐겨찾기 삭제 성공");
					txt += "<button id='bookmarkBtn' class='btn btn-default btn-sm' name='bookmartBtn'>"
						+ "<span class='glyphicon glyphicon-heart-empty'></span>"
						+ "즐겨찾기 추가"
						+ "</button>"
				} else {
					alert("즐겨찾기 삭제 실패");
					txt += "<button id='deleteBookmarkBtn' class='btn btn-default btn-sm' name='bookmartBtn'>"
						+ "<span class='glyphicon glyphicon-heart'></span>"
						+ "즐겨찾기 삭제"
						+ "</button>"
				}
				$("#orderDiv").html(txt);
			},	//end of success
			"error":function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
	
	$(document).ready(function() {
		
		$("#resDate").on("change", chaneResInfo);
		/* $("#resStartTime").on("Select", chaneResInfo); */
		
		
		$("#orderDiv").on("click", "#bookmarkBtn",addBookmark);
		$("#orderDiv").on("click", "#deleteBookmarkBtn", deleteBookmark);
		
		$("#resStartTime").timepicker({
			timeFormat: 'HH:mm',
			interval: 30,
			startTime: "${requestScope.restaurant.rtOpen}",
			minTime: "${requestScope.restaurant.rtOpen}",
			maxTime: "${requestScope.restaurant.rtClose}",
			dynamic:false,
			dropdown:true,
			scrollbar:true,
			change : chaneResInfo
		});	
		
	})
	function formCheck() {
		// 결제방식이 선택되었는지 체크
		var payment = document.getElementsByName('resPayStatement');
		var tableCheck = document.resForm.tableList;
		var tableList = $("input[name=tableList]");
		var cnt = 0;
		alert(tableCheck.length);
		for (var i = 0; i < payment.length; i++) {
			if (payment[i].checked == true) {

				// 결제방식 선택 후, 테이블이 선택되었는지 체크
				if(!tableList.is(":checked")){
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
		<div class="col-sm-8">
			<ul class="nav nav-tabs" role="tablist" id="myTab">
				<li class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">메뉴</a></li>
				<li><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">리뷰</a></li>
				<li><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">문의</a></li>
				<li><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">테이블정보</a></li>
			</ul>

			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="home">1</div>
				<!-- 2017.12.08 - 현준_ 리뷰 TEST -->
				<div role="tabpanel" class="tab-pane" id="profile">
					
				</div>
				<div role="tabpanel" class="tab-pane" id="messages">3</div>
				<div role="tabpanel" class="tab-pane" id="settings">
					<div id="droppable" style="width: 700px; height: 500px; border: 1px solid #000; position: relative; margin-top: 30px;">
						<c:forEach items="${requestScope.restaurant.table}" var="alltables">
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
		<div id="orderDiv">
			<c:choose>
				<c:when test="${requestScope.restaurant.bookmarkCheck == 1 }">
					<button id="deleteBookmarkBtn" class="btn btn-default btn-sm" name="deleteBookmartBtn">
					<span class="glyphicon glyphicon-heart"></span>
						즐겨찾기 삭제
					</button>
				</c:when>
				<c:otherwise>
					<button id="bookmarkBtn" class="btn btn-default btn-sm" name="bookmartBtn"> 
					<span class="glyphicon glyphicon-heart-empty"></span>
						즐겨찾기 추가
					</button>
				</c:otherwise>
			</c:choose>	   
		</div>	
		<form name="resForm" action="${initParam.rootPath}/payment.do" onsubmit="return formCheck()" class="col-sm-4" method="post">
			<div class="panel panel-default">
				<div class="panel-heading" id="orderDiv">
					<h3 class="panel-title">주문표</h3>
				</div>
				<div class="panel-body">
					<c:set scope="page" var="current" value="<%=new Date(System.currentTimeMillis())%>" />
					<div class="form-group">
						<label>예약 식당</label> <input type="text" class="form-control" name="restaurantName" value="${requestScope.restaurant.rtName }" readonly>
					</div>
					<input type="hidden" name="businessId" value="${requestScope.restaurant.businessId }">

					<div class="form-group">
						<label for="date">예약 희망 날짜</label> 
						<c:choose>
							<c:when test="${requestScope.resDate == null }">
								<input type="date" class="form-control" id="resDate" name="resDate" min="${current }"max="2019-12-31" placeholder="예약희망 날짜를 입력하세용" required>
							</c:when>
							<c:otherwise>
								<input type="date" value="${requestScope.resDate }" id="resDate" class="form-control" name="resDate" min="${current }"max="2019-12-31" placeholder="예약희망 날짜를 입력하세용" required>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<label for="resStartTime">예약 희망 시간</label>
						<c:choose>
							<c:when test="${requestScope.resTime == null }">
								<input class="form-control timepicker" name="resStartTime" id="resStartTime" placeholder="몇시에 오실건가요?" required>
							</c:when>
							<c:otherwise>
								<input class="form-control timepicker" name="resStartTime" id="resStartTime" value="${requestScope.resTime }" required>
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
								<input type="number" value="${requestScope.resPeople }" class="form-control" name="resPeople" min="1" required>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="payment">
						<label class="radio-inline"><input type="radio" name="resPayStatement" value="카드결제"> 카드결제 </label> <label class="radio-inline">
							<input type="radio" name="resPayStatement" value="무통장입금">무통장입금
						</label>
					</div>
					<!-- 나중엔 사용자가 고른 list 에서 받아올 값 select로 테스트 -->
					<div class="form-group" id="selectTableDiv">

						<br>
						<h3>예약 가능한 테이블</h3>
						<br>
						<c:choose>
							<c:when test="${requestScope.restaurant.usableTable == null}">
								<h4><b>예약 희망 날짜</b>와 <b>예약 희망 시간</b>을 지정하시면 예약 가능 테이블이 표출됩니다.</h4>
							</c:when>
							<c:otherwise>
								<c:forEach items="${requestScope.restaurant.usableTable }" var="tables" varStatus="cnt">
									<c:if test="${cnt.index % 2 == 0 }">
										<br>
									</c:if>
									<label>테이블 번호 : ${tables.tableNum }, 최대인원 : ${tables.tablePeople } 
									<input type="checkbox" name="tableList" value="${tables.tableSeq }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</label>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						
					</div>
					<div class="panel-footer">
						<input type="submit" class="btn btn-info" value="예약하기">
						<button type="reset" class="btn btn-default">초기화</button>
					</div>
					<sec:csrfInput />
				</div>

			</div>
		</form>

	</div>
</div>
