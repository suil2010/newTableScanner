<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script>
	$(document).ready(function() {
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
	});
</script>
<div class="container" style="max-width: 800px; padding-top: 50px;">  
	<form class="form-horizontal">
		<sec:csrfInput/>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="businessNum">사업자번호 :</label>
			<div class="col-sm-9">
				<input type="text" name="businessNum" placeholder="'-' 빼고 입력해주세요. " id="businessNum" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="businessName">음식점명 :</label>
			<div class="col-sm-9">
				<input type="text" name="businessName" id="businessName" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="businessTel">음식점 전화번호 :</label>
			<div class="col-sm-9">
				<input type="text" name="businessTel" placeholder="'-' 빼고 입력해주세요. " id="businessTel" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="businessField">업종 :</label>
			<div class="col-sm-9">
				<select name="rtField" required>
					<option value=1>한식</option>
					<option value=2>중식</option>
					<option value=3>일식</option>
					<option value=4>분식</option>
					<option value=5>치킨</option>
					<option value=6>피자</option>
					<option value=7>족발</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="businessHoliday">휴무일 : </label>
			<div class="col-sm-9">
				<select name="rtHoliday" required>
					<option value=1>일</option>
					<option value=2>월</option>
					<option value=3>화</option>
					<option value=4>수</option>
					<option value=5>목</option>
					<option value=6>금</option>
					<option value=7>토</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="businessOpen">OPEN : </label>
			<div class="col-sm-9">
				<input type="text" name="businessOpen" id="businessOpen" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="businessClose">CLOSE :</label>
			<div class="col-sm-9">
				<input type="text" name="businessClose" id="businessClose" class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-3 control-label" for="rtTerm">테이블 이용시간 : </label>
			<div class="col-sm-9">
				<select name="businessTerm" id="businessTerm" >
					<option value=1>1시간</option>
					<option value=2>2시간</option>
					<option value=3>3시간</option>
					<option value=4>4시간</option>
					<option value=5>5시간</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-3 control-label">매장 사진 :</label>
			<div class="col-sm-9">
				<input type="file" name="businessImg" placeholder="매장 사진을 등록하세요" >
			</div>
		</div>
		

		<div class="form-group">
			<label class="col-sm-3 control-label" for="businessCapaity">수용가능 인원 : </label>
			<div class="col-sm-9">
				<input type="text" name="businessCapaity" id="businessCapaity" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="businessdeposit">1인당 예약금 : </label>
			<div class="col-sm-9">
				<input type="text" name="businessdeposit" id="businessdeposit" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label">위치 : </label>
			<div class="col-sm-6">
				<input type="text" name="businessdAddress" class="Address form-control">
			</div>
			<button type="button" class="btn btn-default col-sm-2 Search">검색</button> 
		</div>
 
		<div id="map" class="col-sm-12" style="height: 400px;"></div>          

		<div class="form-group">
			<div class="col-sm-12">
				<input type="submit" value="등록" class="ownersubmit">
			</div>
		</div>

	</form>
</div>


