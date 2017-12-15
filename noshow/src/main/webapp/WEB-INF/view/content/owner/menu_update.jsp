<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script>
	//사진 변경 처리
	$(document).ready(function() {

		$("#imgChangeBtn").on("click", function() {
			$("#changeSpan").hide().next().show();
		});
		$("#cancelImgChangeBtn").on("click", function() {
			$("#menuImage").val("").parent().hide().prev().show();
		});
	});
</script>

<div class="container">
	<form method="post" action="${initParam.rootPath}/updateMenu.do" enctype="multipart/form-data">
		<sec:csrfInput />
		<input type="hidden" name="menuNum" value="${requestScope.menu.menuNum}" /> <input type="text" name="menuName"
			value="${requestScope.menu.menuName}"
		> <input type="text" name="menuComment" value="${requestScope.menu.menuComment}"> <input type="number" name="menuPrice"
			value="${requestScope.menu.menuPrice}"
		> <input type="hidden" name="businessId" value="<sec:authentication property='principal.memberId'/>">

		<div class="row">
			<div class="col-sm-12">
				<span id="changeSpan">
					<button id="imgChangeBtn" type="button" class="btn btn-success">사진변경</button> <img id="menuPicture"
					src="${initParam.rootPath }/menuPicture/${requestScope.menu.menuPicture}" class="img-responsive" width="350px"
				>
				</span> <span id="cancelSpan" style="display: none;">
					<button id="cancelImgChangeBtn" type="button" class="btn btn-success">사진변경취소</button> <input type='file' name='menuImage' id='menuImage'
					class='form-control'
				>
				</span>
			</div>
		</div>



		<button type="submit" id="menuadd">수정 완료</button>
	</form>
</div>
