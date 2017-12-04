<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script>
	$(document).ready(function() {

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
					<button type="button" class="btn" style="float: right; border: 1px solid #000; background: #fff;">
						<span class="glyphicon glyphicon-map-marker"></span>
					</button>
				</div>
				<div class="input-group col-sm-3 col-xs-12">
					<input type="text" class="form-control " placeholder="위치" name="resPlace">
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
 
 

</div>






  
