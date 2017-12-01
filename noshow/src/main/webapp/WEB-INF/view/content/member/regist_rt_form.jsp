<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1> 식당등록 </h1>
	<form method="post" action="${initParam.rootPath}/join_rt.do" enctype="multipart/form-data">
	<input type="hidden" name="businessId" value="<sec:authentication property='principal.memberId'/>">
사업자 번호 :	<input type="number" name="rtNum" placeholder="- 를 빼고 입력해주세요"><br>
매장 이름 : <input type="text" name="rtName" placeholder="매장 이름을 입력하세요"><br>
매장 TEL :	<input type="number" name="rtTel" placeholder="매장 전화번호를 입력하세요"><br>
업종선택 :
	<div class="rtField">
		<label>한식</label> <input type="radio" name="rtField" class="radio" value="koreanFood">
		<label>중식</label> <input type="radio" name="rtField" class="radio" value="chineseFood">
		<label>일식</label> <input type="radio" name="rtField" class="radio" value="japaneseFood">
		<label>분식</label> <input type="radio" name="rtField" class="radio" value="snack">
		<label>치킨</label> <input type="radio" name="rtField" class="radio" value="chicken">
		<label>피자</label> <input type="radio" name="rtField" class="radio" value="pizza">
		<label>족발</label> <input type="radio" name="rtField" class="radio" value="koreanMeat">
	</div>
휴무일 :	<input type="date" name="rtHoliday" placeholder="휴무일을 입력하세요(바꿀것)"><br>
오픈시간 :	<input type="date" name="rtOpen" placeholder="오픈시간을 입력하세요"><br>
마감시간 :	<input type="date" name="rtClose" placeholder="마감시간을 입력하세요"><br>
테이블 이용시간 :	<input type="number" name="rtTerm" placeholder="테이블 이용시간을 입력하세요"><br>
매장 사진 :	<input type="file" name="rtPicture" placeholder="매장 사진을 등록하세요"><br>
매장 위치 :	<input type="text" name="rtAddress" placeholder="매장 위치를 입력하세요(지도API가져올 것!)"><br>
수용가능한 인원 :	<input type="number" name="rtCapacity" placeholder="수용가능한 인원을 입력하세요"><br>
예약금 :	<input type="number" name="rtDeposit" placeholder="예약시 1인당 예약금을 입력하세요"><br>
	
	<p>
	<input type="submit" value="매장등록하기"> 
	</p>
	
	<sec:csrfInput/>
	</form>