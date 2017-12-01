<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<div>
<h2>등록된 식당정보</h2>
사업자번호 : ${requestScope.rt.rtNum } <br>
매장 이름 : ${requestScope.rt.rtName } <br>
매장 전화번호 : ${requestScope.rt.rtTel } <br>
매장 업종 : ${requestScope.rt.rtField } <br>
휴무일 : ${requestScope.rt.rtHoliday } <br>
오픈시간 : ${requestScope.rt.rtOpen } <br>
마감시간 : ${requestScope.rt.rtClose } <br>
테이블 이용시간 : ${requestScope.rt.rtTerm } <br>
매장사진 : <img src="${initParam.rootPath }/rtPicture/ ${requestScope.rt.rtPicture}" width="350px"> <br>
음식점 위치 : ${requestScope.rt.rtClose } <br>
수용가능 인원 : ${requestScope.rt.rtClose } <br>
1인당 취소금액 : ${requestScope.rt.rtClose } <br>
<sec:csrfInput/>
</div>