<%@ page contentType="text/html;charset=utf-8"%>

<style>
 svg{
 width: 100%;
 }
</style>
<script type="text/javascript">
	$(function() {
		var day_data = [{
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
			"period" : "2012-09-12",
			"people" : 27
		}];
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