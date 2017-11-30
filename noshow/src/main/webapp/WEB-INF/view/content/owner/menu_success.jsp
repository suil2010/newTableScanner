<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div>
<h2>메뉴 추가 완료</h2>
메뉴 이름 : ${requestScope.menu.menuName } <br>
메뉴 설명 : ${requestScope.menu.menuComment }	<br>
메뉴 가격 : ${requestScope.menu.menuPrice }<br>
사진 : <img src="${initParam.rootPath }/menuPicture/${requestScope.menu.menuPicture}" width="350px">
</div>

 
	  