<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<script>
	
	$(document).ready(function() {
		//사진 변경처리
		$("#imgChangeBtn").on("click", function(){
			$("#changeSpan").hide().next().show();
		});
		$("#cancelImgChangeBtn").on("click", function(){
			$("#rtImage").val("").parent().hide().prev().show();
		});
		
		//date
		$("#rtOpen, #rtClose").timepicker({
			timeFormat: 'HH:mm',
			interval: 30,
			startTime: "12:00",
			dynamic:false,
			dropdown:true,
			scrollbar:true,
		});	
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};
		// 지도를 생성합니다    
		var map = new daum.maps.Map(mapContainer, mapOption);

		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new daum.maps.services.Geocoder();

		$(".Search").on("click", function() {
			var address = $(".Address").val();
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch(address, function(result, status) {
				// 정상적으로 검색이 완료됐으면 
				if (status === daum.maps.services.Status.OK) {

					var coords = new daum.maps.LatLng(result[0].y, result[0].x);

					// 결과값으로 받은 위치를 마커로 표시합니다
					var marker = new daum.maps.Marker({
						map : map,
						position : coords
					});

					// 인포윈도우로 장소에 대한 설명을 표시합니다

					var infowindow = new daum.maps.InfoWindow({
						content : '<div style="width:150px;text-align:center;padding:6px 0;">' + address + '</div>'
					});
					infowindow.open(map, marker);

					// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					map.setCenter(coords);
				} else {
					alert("검색결과가 없습니다.");
				}
			});
		});
		
		function checktSelect(){
			var field = document.form1.rtField
			var selValue = field.options[field.selectedIndex].value;
		}
		
		
		function btnCheck(){
			if(confirm("수정하시겠습니까?")){
			    document.form.action = "";
			    document.form.submit();
			   }
		};
		
	});
</script>

<div class="container" style="max-width: 800px; padding-top: 50px;">
	<form class="form-horizontal" name="form" id="form" method="post" action="${initParam.rootPath }/update_rt.do" enctype="multipart/form-data" onsubmit="btnCheck()">
		<input type="hidden" name="businessId" id="businessId" value="<sec:authentication property="principal.memberId" />">
		
		<div class="form-group">
			<label class="col-sm-3 control-label" for="rtNum">사업자번호 :</label>
			<div class="col-sm-9">
				<input type="text" name="rtNum" id="rtNum" value="${requestScope.map.rt.rtNum }" class="form-control" readonly>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="rtName">음식점명 :</label>
			<div class="col-sm-9">
				<input type="text" name="rtName" id="rtName" value="${requestScope.map.rt.rtName }" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="rtTel">음식점 전화번호 :</label>
			<div class="col-sm-9">
				<input type="text" name="rtTel" value="${requestScope.map.rt.rtTel }" placeholder="'-' 빼고 입력해주세요. " id="rtTel" class="form-control" required>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label " for="rtField">업종 :</label>
			<div class="col-sm-9" >
				<select name="rtField" required class="form-control">
						<option value="-1"> 업종을 선택하세요. </option>
					<c:forEach items="${requestScope.map.field }" var="field">
						<c:choose>
							<c:when test="${field.fieldVal eq requestScope.map.rt.field.fieldVal }">
								    <option value="${field.fieldName}" selected="selected"> ${field.fieldVal }</option>
							</c:when>
							<c:otherwise>
									<option value="${field.fieldName}"> ${field.fieldVal }</option>
							</c:otherwise>
						</c:choose>	
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="rtHoliday">휴무일 : </label>
			<div class="col-sm-9">
				<select name="rtHoliday" required class="form-control">
						<option value="-1"> 휴무일을 선택하세요. </option>
					<c:forEach items="${requestScope.map.holiday }" var="holiday">	
						<c:choose>
							<c:when test="${holiday.holidayVal eq requestScope.map.rt.holiday.holidayVal }">
								    <option value="${holiday.holidayName}" selected="selected"> ${holiday.holidayVal }</option>
							</c:when>
							<c:otherwise>
									<option value="${holiday.holidayName}"> ${holiday.holidayVal }</option>
							</c:otherwise>
						</c:choose>	
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="businessOpen">OPEN : </label>
			<div class="col-sm-9">
				<input name="rtOpen" value="${requestScope.map.rt.rtOpen }"id="rtOpen" class="form-control timepicker" required>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="businessClose">CLOSE :</label>
			<div class="col-sm-9">
				<input name="rtClose" value="${requestScope.map.rt.rtClose }" id="rtClose" class="form-control timepicker" required>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-3 control-label" for="rtTerm">테이블 이용시간 : </label>
			<div class="col-sm-9">
				<select name="rtTerm" id="rtTerm" required class="form-control">
						<option value="-1"> 이용시간을 선택하세요. </option>
					<c:forEach items="${requestScope.map.term }" var="term">	
						<c:choose>
							<c:when test="${term.termVal eq requestScope.map.rt.term.termVal }">
								    <option value="${term.termName}" selected="selected">  ${term.termVal }</option>
							</c:when>
							<c:otherwise>
									<option value="${term.termName}"> ${term.termVal }</option>
							</c:otherwise>
						</c:choose>	
					</c:forEach>
						
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-3 control-label" for="rtImg">매장 사진 :</label>
			
			<span id="changeSpan">
				<button id="imgChangeBtn" type="button" class="btn btn-success">사진변경</button>
					<img id="rtPicture" src="${initParam.rootPath }/rtPicture/${requestScope.map.rt.rtPicture}" class="img-responsive" width="350px">
			</span>
			<span id="cancelSpan" style="display: none;">
				<button id="cancelImgChangeBtn" type="button" class="btn btn-success">사진변경취소</button>
				<input type='file' name='rtImg' id='rtImg' class='form-control'>
			</span>
		</div>
		


		<div class="form-group">
			<label class="col-sm-3 control-label" for="rtCapaity">수용가능 인원 : </label>
			<div class="col-sm-9">
				<input type="number" name="rtCapacity" value="${requestScope.map.rt.rtCapacity }" id="rtCapacity" class="form-control" required>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="rtDeposit">1인당 예약금 : </label>
			<div class="col-sm-9">
				<input type="number" name="rtDeposit" value="${requestScope.map.rt.rtDeposit }" id="rtDeposit" class="form-control" required>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label">위치 : </label>
			<div class="col-sm-6">
				<input type="text" name="rtAddress" value="${requestScope.map.rt.rtAddress }" class="Address form-control" required>
			</div>
			<button type="button" class="btn btn-default col-sm-2 Search">검색</button> 
		</div>
 
		<div id="map" class="col-sm-12" style="height: 400px;"></div>          

		<div class="form-group">
			<div class="col-sm-12">
				<input type="submit" value="수정하기" class="ownersubmit btn btn-default" id="btnUpdate" style="float: right; margin-top: 10px;">
			</div>
		</div>
		<sec:csrfInput/>
	</form>
	
</div>


