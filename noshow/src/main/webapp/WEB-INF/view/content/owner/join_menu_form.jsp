<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1> 메뉴추가 </h1>
   <form method="post" action="${initParam.rootPath}/join_menu.do" enctype="multipart/form-data">
   	<div class="form-group">
      <input type="number" name="menuNum" placeholder="나중에는 자동 처리"/>
	</div>
	<div class="form-group">
	  <input type="text" name="menuName" placeholder="메뉴 이름을 입력하세요">
	</div>
	  <input type="text" name="menuComment" placeholder="메뉴에 대한 설명을 적으세요"><br>
	  <input type="number" name="menuPrice" placeholder="메뉴 가격을 입력하세요."><br>
	  <input type="file" name="menuImage" placeholder="메뉴 사진 이름"><br>
	  <input type="hidden" name="businessId" value="<sec:authentication property='principal.memberId'/>">

      <input type="submit" value="메뉴추가">
      <sec:csrfInput/>
   </form>