<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${initParam.rootPath }/resource/css/wickedpicker.min.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/css/jquery-ui.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/jquery-ui.min.js"></script>
<script src="${initParam.rootPath }/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/wickedpicker.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/morris.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/raphael-min.js"></script> 
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b8d7bc1472b16a686520346ba668407c&libraries=services,clusterer,drawing"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b8d7bc1472b16a686520346ba668407c"></script>
<title>TableScanner</title>

</head>
<body>
	<tiles:insertAttribute name="header" />
	<div class="container-fluid" style="padding-top: 5px; padding-left: 0; padding-right: 0;">
  
		<div class="row" style="margin: 0;">
			<!-- 내용 -->
			<section class="col-sm-12" style="padding: 0;">
				<tiles:insertAttribute name="content" />
			</section>
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>