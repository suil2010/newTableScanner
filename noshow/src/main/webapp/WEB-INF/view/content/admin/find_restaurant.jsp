<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>가입된 정보</h2>
<c:forEach items="${requestScope.rt}" var="item">


<div>
사업자번호 : ${item.rtNum } <br>
매장 이름 : ${item.rtName } <br>
매장 전화번호 : ${item.rtTel } <br>
매장 업종 : ${item.field.fieldVal } <br>
휴무일 : ${item.holiday.holidayVal} <br> 
오픈시간 : ${item.rtOpen }<br>
마감시간 : ${item.rtClose } <br>
테이블 이용시간 : ${item.term.termVal } <br>
매장사진 : <img src="${initParam.rootPath }/rtPicture/${item.rtPicture}" width="350px"> <br>
음식점 위치 : ${item.rtAddress } <br>
수용가능 인원 : ${item.rtCapacity } <br>
1인당 취소금액 : ${item.rtDeposit } <br>
</div>
<hr>
</c:forEach>