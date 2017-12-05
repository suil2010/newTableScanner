<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>TableScanner</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${initParam.rootPath }/resource/css/common.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/css/wickedpicker.min.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/css/morris.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/bootstrap/css/bootstrap-theme.min.css">
<script src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/jquery-ui.min.js"></script>
<script src="${initParam.rootPath }/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/wickedpicker.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/morris.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/raphael-min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b8d7bc1472b16a686520346ba668407c&libraries=services,clusterer,drawing"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b8d7bc1472b16a686520346ba668407c"></script>


<script type="text/javascript">
	$(document).ready(function() {

		$(".tab > a > div").on("mouseover", function() {
			$(this).css("border-right", "3px solid red");
		});
		$(".tab > a > div").on("mouseout", function() {
			$(this).css("border-right", "1px solid #E0E0E0");
		});
		$(".ownersubmit").on("mouseover", function() {
			$(this).css({
				"background" : "#fff",
				"color" : "#000",
				"border" : "2px solid #0B9BA9"
			});
		});
		$(".ownersubmit").on("mouseout", function() {
			$(this).css({
				"background" : "#0B9BA9",
				"color" : "#fff",
				"border" : "0"
			});
		});
		$("header > a > span").on("mouseover", function() {
			$(this).css({
				"border-bottom" : "1px solid red"
			});
		});
		$("header > a > span").on("mouseout", function() {
			$(this).css({
				"border-bottom" : "0"
			});
		});
	});
</script>



<style type="text/css">
header {
	width: 100%;
	height: 60px;
	border-bottom: 1px solid #E0E0E0;
}

header>a>span {
	float: left;
	font-size: 20px;
	margin-left: 40px;
	line-height: 58px;
	font-family: NanumGothic;
}

.logo {
	width: 250px;
	height: 100%;
	float: left;
}

.tab {
	float: left;
	margin: 0;
	padding: 0;
	border-right: 1px solid #E0E0E0;
}

.tab>a>div {
	width: 100%;
	height: 100px;
	border-bottom: 1px solid #E0E0E0;
	cursor: pointer;
}

.tab>a>div>span {
	line-height: 100px;
	font-size: 26px;
	font-family: HoonWhitecatR;
}
</style>


</head>
<body>
	<header>
		<div class="logo">
			<a href="${initParam.rootPath }/index.do"> <span style="line-height: 60px; font-size: 32px;">TableScanner</span>
			</a>
		</div>
		<a href="#"> <span>음식점 관리</span></a> 
		<a href="#"> <span>예약 관리</span></a> 
		<a href="${initParam.rootPath}/selectSales.do"> <span>매출 관리</span></a>

	</header>

	<div class="col-sm-2 col-xs-12 tab">
		<a href="ownerInfo.do">
			<div class="tab1">
				<span>음식점 정보수정</span>
			</div>
		</a> 
		<a href="#">
			<div class="tab2">
				<span>음식점 메뉴수정</span>
			</div>
		</a> 
		<a href="${initParam.rootPath }/selectTable.do">
			<div class="tab3">
				<span>음식점 테이블수정</span>
			</div>
		</a> 
		<a href="#">
			<div class="tab4">
				<span>음식점 정보삭제</span>
			</div>
		</a>
	</div>

	<section class="col-sm-10 col-xs-12" style="height: 100%;">
		<tiles:insertAttribute name="content" />
	</section>

</body>
</html>