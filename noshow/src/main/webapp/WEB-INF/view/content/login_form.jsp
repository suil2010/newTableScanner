<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TableScanner</title>
<style type="text/css">
		.login_form{
			width: 400px;
			height: 30px;
			margin-top: 5px;
		}
		.login_form_submit{
			width: 400px;
			height: 40px;
			margin-left: 20px;
			margin-right: 20px;
			background: #39BAE8;
			border: 0;
			outline: 0;
			color: #fff;
			font-weight: bold;
			font-size: 18px;
			margin-top: 10px;
			cursor: pointer;

		}
		.join_div {
			width: 90%;
			text-align: center;
			height: 100px;
			line-height: 100px;
		}
		.join_div > span {
			font-size: 17px;
		}
		.join_button {
			float: right;
			height: 50px;
			width: 100px;
			background: #39BAE8;
			color: #fff;
			margin-top: 25px;
			font-weight: bold;
			font-size: 16px;
			outline: 0;
			border: 0;
			cursor: pointer;
		}
		.Search{
			width: 400px;
		}


		@media screen and (max-width: 768px){ /*화면이 웹 스크린이고 width가 750px 이하가 되면 아래의 css 설정이 적용됨.*/
			.content{
				width: 100%;
			} 
		}
	</style>
</head>
<body>

<form action="${initParam.rootPath}/login.do" method="post" class="login">
	<sec:csrfInput/>
	<input type="text" name="memberId" autofocus="autofocus" placeholder="ID를 입력해주세요." class="login_form">
	<br>
	<input type="password" name="memberPassword" placeholder="Password를 입력해주세요." class="login_form">
	<div class="Search">
				<a href="#" class="SearchId">아이디 찾기 </a> 
				<a href="#" class="SearchPassword">비밀번호찾기 </a>
	</div>
	<input type="submit" class="login_form_submit" value="로그인">
</form>

</body>
</html>