<%@ page contentType="text/html;charset=utf-8"%>

<style>
svg {
	width: 100%;
}
</style>
<script type="text/javascript">
	$(function() {  
		for(var i = 0; i < 7; i++){  
			var day = new Date()
			 day.setDate(day.getDate() - i);  
			
			if(day.getDate().toString().length == 1){
				var day2 = "0"+day.getDate();
			} else{
				var day2 = day.getDate();
			}
			
			var to_date = day.getFullYear() + "-" + day.getMonth() + "-" + day2;     
			       
			  
		}

		var day_data = ${requestScope.sales};
		/* var day_data = [{
			"period" : "2012-09-19",
			"people" : 15
		}, {
			"period" : "2012-09-18",
			"people" : 50
		}, {
			"period" : "2012-09-17",
			"people" : 40
		}, {
			"period" : "2012-09-16",
			"people" : 30
		}, {
			"period" : "2012-09-15",
			"people" : 25
		}, {
			"period" : "2012-09-14",
			"people" : 23
		}, {
			"period" : "2012-09-13",
			"people" : 26
		}, {
			"period" : "2012-09-13",
			"people" : 25    
		}]; */
		Morris.Line({
			element : 'graph',
			data : day_data,
			xkey : 'period',
			ykeys : ['people'],
			labels : ['people']
		});
	});
</script>
<div class="container">

	<div class="row" style="padding-top: 30px;">
		<div id="graph" style="height: 350px;" class="col-sm-12"></div>
	</div>
</div>