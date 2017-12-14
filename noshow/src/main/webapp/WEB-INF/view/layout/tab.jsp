<%@ page contentType="text/html;charset=utf-8"%>
<style>
.maintab>li {
	width: 100%;
	height: 60px;
	line-height: 60px;
	text-align: center;
	background: #28B78D;
}
.maintab>li>a {
	color: #FAFAFA;
}
</style>   
<div class="container" style="padding-top: 30px; max-width: 1100px;">
	<div class="col-md-2" style="padding: 0;">   
		<ul class="maintab"> 
			<li style="border-bottom: 1px solid #FAFAFA;"><a href="${initParam.rootPath }/mypage/member_info.do">사용자 정보 조회</a></li>
			<li style="border-bottom: 1px solid #FAFAFA;"><a href="${initParam.rootPath }/mypage/update_member_form.do">사용자 정보 수정</a></li>
			<li style="border-bottom: 1px solid #FAFAFA;"><a href="${initParam.rootPath }/myReservation.do">사용자 예약 내역</a></li>
			<li style="border-bottom: 1px solid #FAFAFA;"><a href="${initParam.rootPath }/myReview.do">내가 작성한 리뷰</a></li>
			<li style="border-bottom: 1px solid #FAFAFA;"><a href="${initParam.rootPath }/myQuestion.do">내가 작성한 문의</a></li> 
			<li><a href="${initParam.rootPath }/selectCode.do">음식점 등록</a></li>
		</ul>
	</div>