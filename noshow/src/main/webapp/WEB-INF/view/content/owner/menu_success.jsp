<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div>
<h2>메뉴 추가 완료</h2>
메뉴 이름 : ${requestScope.menu.menuName } <br>
메뉴 설명 : ${requestScope.menu.menuComment }	<br>
메뉴 가격 : ${requestScope.menu.menuPrice }<br>
사진 : <img src="${initParam.rootPath }/menuPicture/${requestScope.menu.menuPicture}" width="350px"><br>
사업자 id : ${requestScope.menu.businessId }
</div>

<form  method="post" action="${initParam.rootPath}/menu_businessId.do" enctype="multipart/form-data">
<input type="hidden" name="businessId" value="<sec:authentication property='principal.memberId'/>">
<sec:csrfInput/>
<input type="submit" value="나의 음식점 메뉴 보기">

</form>
 
	  