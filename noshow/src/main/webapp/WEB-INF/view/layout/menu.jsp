<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<ul class="nav nav-stacked">
<%--인증 안된(로그인 안한) 사용자 메뉴 : 인증되면 안보여야 하는 메뉴 --%>
<sec:authorize access="!isAuthenticated()">
	<li><a href="${initParam.rootPath }/login_form.do">로그인</a></li>
	<li><a href="${initParam.rootPath }/join_member_form.do">회원가입</a></li>
</sec:authorize>


</ul>



<!-- 
	로그아웃전송폼
	+ 로그인/로그아웃은 반드시 POST방식으로 요청하며 csrf 토큰을 보내야 한다.
	+ 로그아웃은 단순 링크이므로 아래와 같이 hidden 폼을 말들고 클릭시 Javascript에서 form을 submit하여 처리한다.
 -->
<form id="logoutForm" action="${initParam.rootPath }/logout.do" method="post" style="display:none">
    <sec:csrfInput/>
</form>


<script>
$(document).ready(function(){
	$("#logout").on("click", function(){
		$("#logoutForm").submit();
	});
});
</script>
