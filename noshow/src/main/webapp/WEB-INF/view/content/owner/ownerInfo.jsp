<%@ page contentType="text/html;charset=utf-8"%>
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
<div class="content">
	<form class="owner">
		<label>사업자번호 :</label> <input type="text" name="businessNum" placeholder="'-' 빼고 입력해주세요. "> <label>음식점명 :</label> <input type="text"
			name="businessName"
		> <label>음식점 전화번호 :</label> <input type="text" name="businessTel" placeholder="'-' 빼고 입력해주세요. "> <label>업종 :</label> <input type="text"
			name="businessField"
		> <label>휴무일 : </label><input type="text" name="businessHoliday"> <label>OPEN : </label><input type="text" name="businessOpen"> <label>CLOSE
			:</label> <input type="text" name="businessClose"> <label>수용가능 인원 : </label><input type="text" name="businessCapaity"> <label>1인당
			예약금 : </label><input type="text" name="businessdeposit"> <label>위치 : </label><input type="text" name="businessdAddress" class="Address"
			style="width: 70%; float: left;"
		>
		<div class="Search"
			style="width: 27%; height: 40px; background: rgb(11, 155, 169); float: left; font-size: 20px; line-height: 40px; color: #fff; margin-left: 10px;"
		>검색</div>
		<!-- test -->
		<div id="map" style="width: 504px; height: 400px; top: 20px;"></div>

		<!-- test -->
		<input type="submit" value="등록" class="ownersubmit"
			style="width: 200px; margin-top: 30px; height: 50px; outline: 0; border: 0; background: #0B9BA9; font-size: 22px; cursor: pointer; font-weight: bold; border-radius: 5px; color: #Fff"
		>
	</form>
</div>