<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<style>
.container>.form-group>label {
	height: 35px;
	line-height: 35px; 
}

.container>.form-group>.col-sm-3 {
	text-align: right;
}

.container>.form-group>.col-sm-9 {
	text-align: center;
}
</style>
<div class="container" style="max-width: 900px; padding-top: 40px;">
	<div class="form-group">
		<label class="col-sm-3">사업자번호 :</label> <label class="col-sm-9">${requestScope.rt.rtNum }</label>
	</div>
	<div class="form-group">
		<label class="col-sm-3">매장 이름 : </label> <label class="col-sm-9">${requestScope.rt.rtName }</label>
	</div>
	<div class="form-group">
		<label class="col-sm-3">매장 전화번호 :</label> <label class="col-sm-9"> ${requestScope.rt.rtTel } </label>
	</div>
	<div class="form-group">
		<label class="col-sm-3">매장 업종 : </label> <label class="col-sm-9">${requestScope.rt.field.fieldVal }</label>
	</div>
	<div class="form-group">
		<label class="col-sm-3">휴무일 : </label> <label class="col-sm-9">${requestScope.rt.holiday.holidayVal} </label>
	</div>
	<div class="form-group">
		<label class="col-sm-3">오픈시간 : </label> <label class="col-sm-9">${requestScope.rt.rtOpen } </label>
	</div>
	<div class="form-group">
		<label class="col-sm-3">마감시간 :</label> <label class="col-sm-9"> ${requestScope.rt.rtClose } </label>
	</div>
	<div class="form-group">
		<label class="col-sm-3">테이블 이용시간 : </label> <label class="col-sm-9">${requestScope.rt.term.termVal } </label>
	</div>
	<div class="form-group">
		<label class="col-sm-3">매장사진 :</label> <label class="col-sm-9"> <img src="${initParam.rootPath }/rtPicture/${requestScope.rt.rtPicture}"
			width="350px"
		></label>
	</div>
	<div class="form-group">
		<label class="col-sm-3">음식점 위치 : </label> <label class="col-sm-9"> ${requestScope.rt.rtAddress } </label>
	</div>
	<div class="form-group">
		<label class="col-sm-3">수용가능 인원 : </label> <label class="col-sm-9"> ${requestScope.rt.rtCapacity }</label>
	</div>
	<div class="form-group">
		<label class="col-sm-3">1인당 취소금액 :</label> <label class="col-sm-9"> ${requestScope.rt.rtDeposit } </label>
	</div>
	<div class="row" style="float: right;">
		<a href="${initParam.rootPath }/find_rt_update.do"><button class="btn btn-default">수정하기</button></a>
	</div>

</div>

<sec:csrfInput />
