<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${initParam.rootPath}/login.do" method="post">
	<sec:csrfInput/>
	id : <input type="text" name="memberId" autofocus="autofocus">
	<br>
	password : <input type="password" name="memberPassword">
	<button type = "submit">로그인</button>
</form>

</body>
</html>