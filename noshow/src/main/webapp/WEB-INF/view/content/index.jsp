<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script>
	$(document).ready(
		function() {

			$('#myTab a').click(function(e) {
				e.preventDefault()
				$(this).tab('show')
			});

			//date
			var options = {
				now : "12:00", //hh:mm 24 hour format only, defaults to current time 
				twentyFour : true, //Display 24 hour format, defaults to false 
				upArrow : 'wickedpicker__controls__control-up', //The up arrow class selector to use, for custom CSS 
				downArrow : 'wickedpicker__controls__control-down', //The down arrow class selector to use, for custom CSS 
				close : 'wickedpicker__close', //The close class selector to use, for custom CSS 
				hoverState : 'hover-state', //The hover state class to use, for custom CSS 
				title : 'TabelScanner', //The Wickedpicker's title, 
				showSeconds : false, //Whether or not to show seconds, 
				secondsInterval : 1, //Change interval for seconds, defaults to 1  , 
				minutesInterval : 1, //Change interval for minutes, defaults to 1 
				beforeShow : null, //A function to be called before the Wickedpicker is shown 
				show : null, //A function to be called when the Wickedpicker is shown 
				clearable : false, //Make the picker's input clearable (has clickable "x")  
			};
			$('.timepicker').wickedpicker(options);

			$(function() {
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
			//test 
			$(".gpsbtn").on("click",function() {

					//좌표생성 
					$(function() {
						if (navigator.geolocation) { // GPS를 지원하면
							navigator.geolocation.getCurrentPosition(function(position) {
								var lat = position.coords.latitude;
								var lon = position.coords.longitude;
								//좌표 변환기

								var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
								mapOption = {
									center : new daum.maps.LatLng(lat, lon), // 지도의 중심좌표
									level : 1
								// 지도의 확대 레벨
								};

								// 지도를 생성합니다    
								var map = new daum.maps.Map(mapContainer, mapOption);

								// 주소-좌표 변환 객체를 생성합니다
								var geocoder = new daum.maps.services.Geocoder();

								var marker = new daum.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
								infowindow = new daum.maps.InfoWindow({
									zindex : 1
								}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

								// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
								searchAddrFromCoords(map.getCenter(), displayCenterInfo);

								// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
								daum.maps.event.addListener(map, 'click', function(mouseEvent) {
									searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
										if (status === daum.maps.services.Status.OK) {
											var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name
												+ '</div>' : '';
											detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';

											var content = '<div class="bAddr">' + '<span class="title">법정동 주소정보</span>' + detailAddr + '</div>';

										}
									});
								});

								// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
								daum.maps.event.addListener(map, 'idle', function() {
									searchAddrFromCoords(map.getCenter(), displayCenterInfo);
								});  

								function searchAddrFromCoords(coords, callback) {
									// 좌표로 행정동 주소 정보를 요청합니다
									geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
								}

								// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
								function displayCenterInfo(result, status) {
									if (status === daum.maps.services.Status.OK) {
										var infoDiv = document.getElementById('centerAddr');

										for (var i = 0; i < result.length; i++) {
											// 행정동의 region_type 값은 'H' 이므로
											if (result[i].region_type === 'H') {
												infoDiv.innerHTML = result[i].address_name;
												$(".gps").val(infoDiv.innerHTML = result[i].address_name);
												break;
											}
										}
									}
								}
							}, function(error) {
								console.error(error);
							}, {
								enableHighAccuracy : false,
								maximumAge : 0,
								timeout : Infinity
							});
						} else {
							alert('GPS를 지원하지 않습니다');
						}

					});

				});
			//test

		});
</script>
<ul class="nav nav-tabs" role="tablist" id="myTab">
	<li role="presentation" class="active"><a href="#S1" aria-controls="home" role="tab" data-toggle="tab">조건검색</a></li>
	<li role="presentation"><a href="#S2" aria-controls="profile" role="tab" data-toggle="tab">이름검색</a></li>
</ul>

<div class="tab-content">

	<!-- 조건 검색바 -->
	<div role="tabpanel" class="tab-pane active" id="S1">
		<form class="navbar-form" role="search" method="post" action="${initParam.rootPath}/searchRestaurant.do">
			<div class="form-group col-sm-12">
				<div class="input-group col-sm-1 col-xs-1">
					<button type="button" class="gpsbtn btn" style="float: right; border: 1px solid #000; background: #fff;">
						<span class="glyphicon glyphicon-map-marker"></span>
					</button>
				</div>
				<div class="input-group col-sm-3 col-xs-12">
					<input type="text" class="form-control gps" placeholder="위치" name="resPlace">
				</div>
				<div class="input-group col-sm-2 col-xs-12">
					<input type="date" class="form-control " placeholder="날짜" name="resDate">
				</div>
				<div class="input-group col-sm-2 col-xs-12">
					<input type="text" class="form-control timepicker " placeholder="시간" name="resTime">
				</div>
				<div class="input-group col-sm-2 col-xs-12">
					<input type="number" class="form-control" placeholder="인원" name="resPeople">
				</div>
				<div class="input-group col-sm-1">
					<button type="submit" class="btn" style="border: 1px solid #000; background: #fff;">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</div>
			</div>
			<sec:csrfInput />
		</form>
	</div>


	<!-- 이름으로 검색바 -->
	<div role="tabpanel" class="tab-pane" id="S2">

		<form class="navbar-form" role="search" method="post" action="${initParam.rootPath}/searchRestaurant.do">
			<div class="form-group col-sm-12 text-center">
				<div class="input-group col-sm-1 col-xs-1">
					<button type="button" class="btn" style="float: right; border: 1px solid #000; background: #fff;">
						<span class="glyphicon glyphicon-map-marker"></span>
					</button>
				</div>
				<div class="input-group col-sm-3 col-xs-12 ">
					<input type="text" class="form-control " placeholder="위치" name="resPlace">
				</div>
				<div class="input-group col-sm-3 col-xs-12">
					<input type="text" class="form-control " placeholder="이름" name="resName">
				</div>

				<div class="input-group col-sm-1">
					<button type="submit" class="btn" style="border: 1px solid #000; background: #fff; float: left;">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</div>
			</div>
			<sec:csrfInput />
		</form>
	</div>
	<style>
.map_wrap {
	position: relative;
	width: 100%;
	height: 500px;
	top: 200px;
}

.title {
	font-weight: bold;
	display: block;
}

.hAddr {
	position: absolute;
	left: 10px;
	top: 10px;
	border-radius: 2px;
	background: #fff;
	background: rgba(255, 255, 255, 0.8);
	z-index: 1;
	padding: 5px;
}

#centerAddr {
	display: block;
	margin-top: 2px;
	font-weight: normal;
}

.bAddr {
	padding: 5px;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}
</style>
	<div class="map_wrap">
		<div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
		<div class="hAddr">
			<span class="title">지도중심기준 행정동 주소정보</span> <span id="centerAddr"></span>
		</div>
	</div>

</div>







