<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<style type="text/css">
header {
	width: 100%;
	height: 60px;
	float: left;
	border-bottom: 1px solid #E0E0E0;
	min-width: 400px;
}
li{
float: right;
width: 100px;
height: 100%;
line-height: 60px;
}
ul{
height: 100%;
}
.logo {
	width: 250px;
	height: 100%;
	float: left;
}
.ul2{
	display: none; 
	float: right;
}
.ul2 > .ul2_div{  
width: 40%;
height : 100%;  
position: absolute; 
background: rgba(0,0,0,0.9);
z-index: 5;
float: right;   
}
.ul2 > .ul2_div > li {
	width: 100%; height : 60px;
	float: none;
}  
@media screen and (max-width: 768px){ /*화면이 웹 스크린이고 width가 750px 이하가 되면 아래의 css 설정이 적용됨.*/
	.ul1{
		display: none; 
	}
	.ul2{
		display: block;
		float: left;
	
	}
	.ul2_div > li > a {    
		color: #fff;  
		font-size: 16px;   
	}
	.ul2btn{
		margin-left: 10px;     	
	}
	.logo{
		float: right; 
		margin-right: 50px;
	}

}
	.ul2 > .ul2_div{
		display: none;
	}
</style>
<script>
	$(function(){
		$(".ul2btn").on("click",function(){
			$(".ul2 > .ul2_div").css({"display":"block"})
		});
	});
</script>
<header>
	<div class="logo">
			<a href="index.do"> <span style="line-height: 60px; font-size: 32px;">TableScanner</span></a>
		
	</div>
	<ul class="ul1"> 
		<%--인증 안된(로그인 안한) 사용자 메뉴 : 인증되면 안보여야 하는 메뉴 --%>
		<sec:authorize access="!isAuthenticated()">
			<li><a href="${initParam.rootPath }/login_form.do">로그인</a></li>
			<li><a href="${initParam.rootPath }/join_member_form.do">회원가입</a></li>
		</sec:authorize>
	</ul>
	<ul class="ul2">  
		<%--인증 안된(로그인 안한) 사용자 메뉴 : 인증되면 안보여야 하는 메뉴 --%>   
		<button class="ul2btn" style="width: 50px; height: calc(100% - 10px); margin-top:5px; margin-bottom: 5px; float: right; margin-right: 20px;">더보기</button> 
		<div class="ul2_div">
			<sec:authorize access="!isAuthenticated()">
				<li><a href="${initParam.rootPath }/login_form.do">로그인</a></li>
				<li><a href="${initParam.rootPath }/join_member_form.do">회원가입</a></li>
			</sec:authorize>
		</div>
	</ul>
	<!-- 
		로그아웃전송폼
		+ 로그인/로그아웃은 반드시 POST방식으로 요청하며 csrf 토큰을 보내야 한다.
		+ 로그아웃은 단순 링크이므로 아래와 같이 hidden 폼을 말들고 클릭시 Javascript에서 form을 submit하여 처리한다.
	 -->
	<form id="logoutForm" action="${initParam.rootPath }/logout.do" method="post" style="display: none">
		<sec:csrfInput />
	</form>

	<script>
		$(document).ready(function() {
			$("#logout").on("click", function() {
				$("#logoutForm").submit();
			});
		});
	</script>
</header>