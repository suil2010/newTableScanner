<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="http://malsup.github.com/jquery.form.js"></script>
<style type="text/css">
#droppable>div {
	margin: 0;
	width: 100px;
	height: 100px;
	background: rgba(0, 0, 0, 0.1);
	position: absolute;
}

.label {
	text-align: left;
}

.mainTemplate {
	background: #fafafa;
}
</style>

<script>
	// chaneResInfo() - 날짜,시간 ajax 처리
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
	// 즐겨찾기 추가
	function addBookmark() {
		$.ajax({
			"url" : "/noshow/addBookmark.do",
			"type" : "get",
			"data" : {
				"businessId" : "${requestScope.restaurant.businessId }"
			},
			"dataType" : "json",
			"success" : function(result) {
				if (result == 1) {
					var txt = "<button id='deleteBookmarkBtn' class='btn btn-default btn-sm' name='bookmartBtn'>"
						+ "<span class='glyphicon glyphicon-heart'></span>" + " 즐겨찾기 삭제" + "</button>"
				} else {
					var txt = "<button id='bookmarkBtn' class='btn btn-default btn-sm' name='bookmartBtn'>"
						+ "<span class='glyphicon glyphicon-heart-empty'></span>" + " 즐겨찾기 추가" + "</button>"
				}
				$("#orderDiv").html(txt);
			}, //end of success
			"error" : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
			}
		});
	}

	//즐겨찾기 삭제
	function deleteBookmark() {
		var a = $(".token").children().val();
		$.ajax({
			"url" : "/noshow/deleteBookmark.do",
			"type" : "post",
			"data" : {
				"businessId" : "${requestScope.restaurant.businessId }",
				"_csrf" : a
			},
			"dataType" : "json",
			"success" : function(result) {
				if (result == 1) {
					var txt = "<button id='bookmarkBtn' class='btn btn-default btn-sm' name='bookmartBtn'>"
						+ "<span class='glyphicon glyphicon-heart-empty'></span>" + " 즐겨찾기 추가" + "</button>"
				} else {
					var txt = "<button id='deleteBookmarkBtn' class='btn btn-default btn-sm' name='bookmartBtn'>"
						+ "<span class='glyphicon glyphicon-heart'></span>" + " 즐겨찾기 삭제" + "</button>"
				}
				$("#orderDiv").html(txt);
			}, //end of success
			"error" : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
			}
		});
	}

	/* function registReview() {
		alert("dddd");
		$("#reviewForm").ajaxForm({
			"dataType":"json", 
			"success":function(res){
				alert(res);
				var reviewList = eval('('+res+')')
				alert("리뷰 등록 성공!!");
				var txt = "리뷰 재 조회~";
				$.each(reviewList, function() {
					txt += "<div class='col-md-6' style='margin-top: 10px;'>"
							+"<div style='margin-right: 10px; background: #fff; width: 100%; height: 100%; padding: 10px;' class='col-md-12'>"
							+"<span class='label col-sm-9' style='color: #000; font-size: 14px;'>작성자 : "+ this.memberId + "</span>"
							+"<img src='${initParam.rootPath }/reviewPicture/"+this.reviewImg+"' width='100px' height='100' class='col-sm-3'>" 
							+"<span class='label col-sm-9' style='color: #000; font-size: 18px;'>평점 : "+ this.reviewGrade +"</span>" 
							+"<span class='label col-sm-9' style='color: #000; font-size: 14px;'>내용 : "+ this.reviewText +"</span>"
							+"<span class='label col-sm-9' style='color: #000; font-size: 14px;'>작성 시간 : "+ this.reviewTime +"</span>"
							+"</div></div>";
				});
				$("#reviewListDiv").html(txt);
			},
			"error":function(){
				alert("error");
			}
		});
	} */

	/* 	function registReview() {
	 alert("리뷰 등록!!!");
	 var form = $("#reviewForm")[0];
	 var formData = new FormData(form);
	 //formData.append("reviewImage", $("#reviewPicture")[0].files[0]);
	 $.ajax({
	 "url":"/noshow/registReview.do",
	 "type":"post",
	 "data":formData,
	 "contentType":false,
	
	 "processDate":false,
	 "dataType":"json",
	 "success":function(reviewList){
	 alert("리뷰 등록 성공~~~");
	 var txt = "리뷰 재 조회~";
	 $.each(reviewList, function() {
	 txt += "<div class='col-md-6' style='margin-top: 10px;'>"
	 +"<div style='margin-right: 10px; background: #fff; width: 100%; height: 100%; padding: 10px;' class='col-md-12'>"
	 +"<span class='label col-sm-9' style='color: #000; font-size: 14px;'>작성자 : "+ this.memberId + "</span>"
	 +"<img src='${initParam.rootPath }/reviewPicture/"+this.reviewImg+"' width='100px' height='100' class='col-sm-3'>" 
	 +"<span class='label col-sm-9' style='color: #000; font-size: 18px;'>평점 : "+ this.reviewGrade +"</span>" 
	 +"<span class='label col-sm-9' style='color: #000; font-size: 14px;'>내용 : "+ this.reviewText +"</span>"
	 +"<span class='label col-sm-9' style='color: #000; font-size: 14px;'>작성 시간 : "+ this.reviewTime +"</span>"
	 +"</div></div>";
	 });
	 $("#reviewListDiv").html(txt);
	 },
	 "error": function(request, status, error) {
	 alert("code:" + request.status + "\n" + "message:"
	 + request.responseText + "\n" + "error:"
	 + error);
	 }
	
	 });
	 } */

	function changeResPeople() {
		alert("인원 변경되었다!!");
		$.ajax({
			"url" : "${initParam.rootPath}/totalResPrice.do",
			"type" : "get",
			"data" : {
				"resPeople" : $("#resPeople").val(),
				"businessId" : "${requestScope.restaurant.businessId }"
			},
			"success" : function(totalPrice) {
				alert("인원 변경->재계산 성공!");
				var txt = "총 예약 금액 : " + "<span style='color: red; float: center; font-size: 30px'><b>" + totalPrice + "</b></span>";

				$("#totalPriceDiv").html(txt);
			},
			"error" : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
			}

		});
	}

	$(document)
		.ready(
			function() {
				$("#input-id").rating();
				$("#input-id").rating({
					min : 1,
					max : 5,
					step : 0.5,
					glyphicon : true
				});

				$("#resDate").on("change", chaneResInfo);
				/* $("#resStartTime").on("Select", chaneResInfo); */
				$("#orderDiv").on("click", "#bookmarkBtn", addBookmark);
				$("#orderDiv").on("click", "#deleteBookmarkBtn", deleteBookmark);
				$("#resPeople").on("change", changeResPeople);

				$("#reviewBtn")
					.on(
						"click",
						function() {
							alert("click????");
							var option = {
								url : "${initParam.rootPath}/registReview.do",
								enctype : "multipart/form-data",
								dataType : "json",
								success : function(reviewList) {
									$
										.each(
											reviewList,
											function() {
												var txt = "<div class='col-md-12 box1' style='background: #fff; min-height: 100px; padding: 10px; margin-top: 10px;'>"
													+ "<img src='${initParam.rootPath }/reviewPicture/"
													+ this.reviewImg
													+ "' class='col-md-3' style='height: 100%;'>"
													+ "<div style='float: right;' class='col-md-9'>"
													+ "<span class='label col-sm-9' style='color: #000; font-size: 14px;'>작성자 : "
													+ this.memberId
													+ "</span>"
													+ "<span class='label col-sm-9' style='color: #000; font-size: 14px;'>평점 : "
													+ this.reviewGrade
													+ "</span>"
													+ "<span class='label col-sm-9' style='color: #000; font-size: 14px;'>내용 : "
													+ this.reviewText
													+ "</span>"
													+ "<span class='label col-sm-9' style='color: #000; font-size: 14px;'>작성 시간 : "
													+ this.reviewTime + "</span>" + "</div></div>";

												$("#reviewListDiv").append(txt);
											});
								},
								"error" : function() {
									alert("error");
								}
							};
							$("#reviewForm").ajaxForm(option);
							$("#reviewForm").submit();
							$("#reviewForm")[0].reset(); //ajax 처리 후 리뷰작성 폼 초기화
						});

				$("#resStartTime").timepicker({
					timeFormat : 'HH:mm',
					interval : 30,
					startTime : "${requestScope.restaurant.rtOpen}",
					minTime : "${requestScope.restaurant.rtOpen}",
					maxTime : "${requestScope.restaurant.rtClose}",
					dynamic : false,
					dropdown : true,
					scrollbar : true,
					change : chaneResInfo
				});
			})

	// formCheck() - 결제 , 테이블선택 유무를 체크
	function formCheck() {
		var payment = $('input:radio[name="resPayStatement"]:checked').val();
		var tableCheck = $('input:checkbox[name="tableList"]:checked').val();
		if (payment == null) {
			alert("결제방식을 선택하세요.");
			return false;
		} else {
			if (tableCheck == null) {
				alert("예약하실 테이블을 선택하세요.");
				return false;
			} else {
				return true;
			}
		}

	}
</script>



<div class="container">
	<div class="token" style="display: none;">
		<sec:csrfInput />
	</div>

	<!-- 음식점목록 1번쨰 row (식당이미지 - 설명) -->
	<div class="row" style="background: #fff; margin-bottom: 20px; margin-top: 20px;">
		<div class="col-sm-6" style="padding: 10px;">
			<img src="${initParam.rootPath }/rtPicture/${requestScope.restaurant.rtPicture}" alt="식당 이미지" style="width: 100%;">
		</div>
		<div class="col-sm-6" style="padding: 30px;">

			<span style="font-size: 34px; font-weight: bold;">${requestScope.restaurant.rtName}</span>
			<p>
				<span style="font-size: 22px;">음식점 전화번호 : ${requestScope.restaurant.rtTel}</span>
			<p>
				<span style="font-size: 22px;">Open : ${requestScope.restaurant.rtOpen}</span> <span style="font-size: 22px;">Close :
					${requestScope.restaurant.rtClose}</span>
			<p>
				<span style="font-size: 22px;">위치 : ${requestScope.restaurant.rtAddress}</span>
			<p>
				<span style="font-size: 22px;">수용가능인원 : ${requestScope.restaurant.rtCapacity}</span>
			<p>
				<span style="font-size: 22px;">1인 금액 : ${requestScope.restaurant.rtDeposit}</span>
			<p>
			<div id="orderDiv">
				<c:choose>
					<c:when test="${requestScope.restaurant.bookmarkCheck == 1 }">
						<button id="deleteBookmarkBtn" class="btn btn-default btn-sm" name="deleteBookmartBtn">
							<span class="glyphicon glyphicon-heart"></span> 즐겨찾기 삭제
						</button>
					</c:when>
					<c:otherwise>
						<button id="bookmarkBtn" class="btn btn-default btn-sm" name="bookmartBtn">
							<span class="glyphicon glyphicon-heart-empty"></span> 즐겨찾기 추가
						</button>
					</c:otherwise>
				</c:choose>
			</div>

		</div>

	</div>

	<!-- 음식점목록 2번쨰 row (메뉴,테이블,문의 리뷰 - 주문표) -->
	<div class="row">
		<div class="col-md-8">
			<ul class="nav nav-tabs" role="tablist" id="myTab">
				<li class="active"><a href="#menu" aria-controls="menu" role="tab" data-toggle="tab">메뉴</a></li>
				<li><a href="#review" aria-controls="review" role="tab" data-toggle="tab">리뷰</a></li>
				<li><a href="#question" aria-controls="question" role="tab" data-toggle="tab">문의</a></li>
				<li><a href="#table" aria-controls="table" role="tab" data-toggle="tab">테이블정보</a></li>
			</ul>

			<div class="tab-content">

				<!-- menu정보 패널 -->
				<div role="tabpanel" class="tab-pane active" id="menu">
					<!-- 2017.12.11 현준 추가 -->
					<div class="row">
						<c:forEach items="${requestScope.restaurant.menuList}" var="item">
							<div class="col-md-3" style="margin-top: 10px;">
								<div style="margin-right: 10px; background: #fff; width: 100%; height: 100%; padding: 10px;" class="col-md-12">
									<img src="${initParam.rootPath }/menuPicture/${item.menuPicture}" class="col-sm-12" style="width: 100%; height: 110px;">
									<div class="col-sm-12" style="margin-top: 10px;">
										<span class="label col-sm-9" style="color: #000; font-size: 14px;"> 이름 : ${item.menuName}</span> <span class="label col-sm-9"
											style="color: #000; font-size: 14px;"
										>설명 : ${item.menuComment}</span> <span class="label col-sm-9" style="color: #000; font-size: 14px;"> 가격 : ${item.menuPrice}</span>

									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>

				<!-- 리뷰 패널 -->
				<div role="tabpanel" class="tab-pane" id="review" style="padding: 10px;">
					<c:if test="${requestScope.restaurant.reservationCheck > 0 }">
						<form id="reviewForm" name="reviewForm" action="${initParam.rootPath }/registReview.do" method="post" enctype="multipart/form-data">
							<div class="col-sm12">
								<div class="col-sm-8">
									<input id="input-id" type="number" class="rating" data-size="sm" name="reviewGrade" id="reviewGrade" required>
								</div>
								<div class="col-sm-4">
									<input type="file" name="reviewPicture" id="reviewPicture">
								</div>
							</div>
							<div class="col-sm-10">
								<textarea class="form-control" rows="3" style="resize: none;" name="reviewText" id="reviewText" required></textarea>
								<input type="hidden" name="businessId" value="${restaurant.businessId }" />
							</div>
							<div class="col-sm-2">
								<input type="button" value="리뷰 작성" id="reviewBtn" class="btn btn-default">
							</div>
							<sec:csrfInput />
						</form>
					</c:if>
					<div class="row" id="reviewListDiv">
						<c:choose>
							<c:when test="${empty requestScope.restaurant.reviewList  }">
								<div class="col-md-6" style="margin-top: 10px;">해당 음식점에 등록된 리뷰가 없습니다.</div>
							</c:when>
							<c:otherwise>
								<c:forEach items="${requestScope.restaurant.reviewList}" var="review">
									<div class="col-md-12 box1" style="background: #fff; min-height: 100px; padding: 10px; margin-top: 10px;">
										<img src="${initParam.rootPath }/reviewPicture/${review.reviewImg}" class="col-md-3" style="height: 100%;">
										<div style="float: right;" class="col-md-9">
											<span class="label col-sm-9" style="color: #000; font-size: 14px;"> 작성자 : ${review.memberId}</span> <span class="label col-sm-9"
												style="color: #000; font-size: 18px;"
											> 평점 : ${review.reviewGrade}</span> <span class="label col-sm-9" style="color: #000; font-size: 14px;">내용 : ${review.reviewText}</span> <span
												class="label col-sm-9" style="color: #000; font-size: 14px;"
											>작성 시간 : ${review.reviewTime}</span>
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>

				</div>

				<!-- 문의 패널 -->
				<div role="tabpanel" class="tab-pane" id="question" style="padding: 10px;">
					<form action="#" method="post">
						<div class="col-sm-10">
							<textarea class="form-control" rows="3" style="resize: none;" name="text"></textarea>
						</div>
						<div class="col-sm-2">
							<button style="width: 100%; height: 74px;" class="btn btn-default">문의작성</button>
						</div>
					</form>
				</div>

				<!-- 테이블정보 패널 -->
				<div role="tabpanel" class="tab-pane" id="table">
					<div id="droppable" style="width: 700px; height: 500px; position: relative; margin-top: 30px; background: #fff;">
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


		<!-- 주문표 -->
		<form name="resForm" action="${initParam.rootPath}/payment.do" onsubmit="return formCheck()" class="col-md-4" method="post">
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
								<input type="date" class="form-control" id="resDate" name="resDate" min="${current }" max="2019-12-31" placeholder="예약희망 날짜를 입력하세용" required>
							</c:when>
							<c:otherwise>
								<input type="date" value="${requestScope.resDate }" id="resDate" class="form-control" name="resDate" min="${current }" max="2019-12-31"
									placeholder="예약희망 날짜를 입력하세용" required
								>
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
							<c:when test="${empty requestScope.resPeople}">
								<input type="number" class="form-control" name="resPeople" id="resPeople" placeholder="몇명이오는지알려줭" min="1" required>
							</c:when>
							<c:otherwise>
								<input type="number" value="${requestScope.resPeople }" class="form-control" name="resPeople" id="resPeople" min="1" required>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="payment">
						<div class="col-sm-12">
							<label class="radio-inline"><input type="radio" name="resPayStatement" value="card">카드결제</label> <label class="radio-inline"><input
								type="radio" name="resPayStatement" value="vbank"
							>무통장입금 </label>
						</div>
						<div class="col-sm-12">
							<label class="radio-inline"><input type="radio" name="resPayStatement" value="trans">실시간계좌이체 </label> <label class="radio-inline"><input
								type="radio" name="resPayStatement" value="phone"
							>휴대폰소액결제 </label>
						</div>
					</div>
					<!-- 나중엔 사용자가 고른 list 에서 받아올 값 select로 테스트 -->
					<div class="form-group" id="selectTableDiv" style="padding-top: 20px;">

						<br>
						<h3>예약 가능한 테이블</h3>
						<br>
						<c:choose>
							<c:when test="${requestScope.restaurant.usableTable == null}">
								<h4>
									<b>예약 희망 날짜</b>와 <b>예약 희망 시간</b>을 지정하시면 예약 가능 테이블이 표출됩니다.
								</h4>
							</c:when>
							<c:otherwise>
								<c:forEach items="${requestScope.restaurant.usableTable }" var="tables" varStatus="cnt">
									<c:if test="${cnt.index % 2 == 0 }">
										<br>
									</c:if>
									<label>테이블 번호 : ${tables.tableNum }, 최대인원 : ${tables.tablePeople } <input type="checkbox" name="tableList"
										value="${tables.tableSeq }"
									>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</label>
								</c:forEach>
							</c:otherwise>
						</c:choose>

					</div>
					<div class="form-group" id="totalPriceDiv">
						총 예약 금액 : <span style="color: red; float: center; font-size: 30px"><b> <fmt:formatNumber
									value="${requestScope.restaurant.rtDeposit * requestScope.resPeople }" pattern="#,### 원"
								/></b></span>
						<sec:csrfInput />
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
