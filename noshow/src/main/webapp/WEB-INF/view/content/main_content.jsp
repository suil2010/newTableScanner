<%@ page contentType="text/html;charset=utf-8"%>
<script>
	$(document).ready(function() {
		//date
		var options = { 
				now: "12:00", //hh:mm 24 hour format only, defaults to current time 
				twentyFour: false, //Display 24 hour format, defaults to false 
				upArrow: 'wickedpicker__controls__control-up', //The up arrow class selector to use, for custom CSS 
				downArrow: 'wickedpicker__controls__control-down', //The down arrow class selector to use, for custom CSS 
				close: 'wickedpicker__close', //The close class selector to use, for custom CSS 
				hoverState: 'hover-state', //The hover state class to use, for custom CSS 
				title: 'TabelScanner', //The Wickedpicker's title, 
				showSeconds: false, //Whether or not to show seconds, 
				secondsInterval: 1, //Change interval for seconds, defaults to 1  , 
				minutesInterval: 1, //Change interval for minutes, defaults to 1 
				beforeShow: null, //A function to be called before the Wickedpicker is shown 
				show: null, //A function to be called when the Wickedpicker is shown 
				clearable: false, //Make the picker's input clearable (has clickable "x")  
			}; 
		$('.timepicker').wickedpicker(options);
		
		 $( function() {
			    $( "#datepicker" ).datepicker();
		 });
	});
</script>
<form class="navbar-form" role="search" method="post">
		<div class="form-group">
		    <input type="text" class="form-control col-sm-3" placeholder="위치" name="place">
		    <input type="text" class="form-control col-sm-3" id="datepicker" placeholder="날짜" name="date">
		    <input type="text" class="form-control timepicker col-sm-2" placeholder="시간" name="time">
		    <input type="number" class="form-control col-sm-2" placeholder="인원" name="people">

		</div>

		<button type="submit" class="btn btn-default col-sm-2" style="float: right;">검색</button>
</form>	