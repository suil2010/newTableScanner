<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${initParam.rootPath }/resource/css/wickedpicker.min.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/css/star-rating.min.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/css/jquery-ui.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/css/timepicker.min.css">
<script src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/jquery-ui.min.js"></script>
<script src="${initParam.rootPath }/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/wickedpicker.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/morris.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/raphael-min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/star-rating.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/timepicker.min.js"></script>
<script src="${initParam.rootPath }/resource/jquery/iamport.payment-1.1.5.js"></script>
<script src="${initParam.rootPath }/resource/jquery/jquery.form.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b8d7bc1472b16a686520346ba668407c&libraries=services,clusterer,drawing"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b8d7bc1472b16a686520346ba668407c"></script>
<title>TableScanner</title>
<style type="text/css">
html, body {
	height: 100%;
	margin: 0;
}
.table>thead>tr>th{ 
	text-align: center;
}
.table > tbody > tr > td{
	text-align: center;  
}
</style>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<div class="container-fluid mainTemplate" style="padding-top: 5px; padding-left: 0; padding-right: 0; min-height: 100%;">
		<div class="row" style="margin: 0;">
			<!-- 내용 -->
			<section class="col-sm-12" style="padding: 0;">

				<c:if test="${not empty requestScope.tabMenu }">
					<tiles:insertAttribute name="tab" />
				</c:if>
				<tiles:insertAttribute name="content" />
			</section>
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>