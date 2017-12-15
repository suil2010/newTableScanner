<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(document).ready(function() {

		$(".deleteMenu").on("click", function() {
			$.ajax({
				"url" : "${initParam.rootPath }/remove_menu.do",
				"type" : "GET",
				"dataType" : "text",
				"data" : {
					"menuNum" : $(this).val(),
				},
				"success" : function(obj) {
					alert(obj);
					location.reload(true);
				}
			});
		});
		var a =2;
		$("#divnamebtn").click(function() {
			$("#divname").toggle(); //천천히 보이기
			if(a % 2 ==0){
				$("#divnamebtn").text("취소");
			} else{
				$("#divnamebtn").text("메뉴추가"); 
			}
			a++;
		});
	});
</script>

<div class="container">

	<button id="divnamebtn" class="btn btn-default">메뉴추가</button>
	<div id="divname" class="row" style="padding:20px; background: #fff; display: none; margin-top: 10px;">      
		<form method="post" action="${initParam.rootPath}/join_menu.do" enctype="multipart/form-data">
			<input type="hidden" name="businessId" value="<sec:authentication property='principal.memberId'/>">
			<div class="form-group">
				<label class="col-sm-4 control-label" for="menuName" style="height: 34px; margin: 0; text-align: center; line-height: 34px;">메뉴 이름</label>
				<div class="col-sm-8">
					<input type="text" name="menuName" id="menuName" placeholder="메뉴 이름을 입력하세요." class="form-control" autofocus required>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label" for="menuComment" style="height: 34px; margin: 0; text-align: center; line-height: 34px;">메뉴 설명</label>
				<div class="col-sm-8">
					<input type="text" name="menuComment" id="menuComment" placeholder="메뉴에 대한 설명을 적으세요." class="form-control" autofocus required>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label" for="menuPrice" style="height: 34px; margin: 0; text-align: center; line-height: 34px;">메뉴 가격</label>
				<div class="col-sm-8">
					<input type="number" name="menuPrice" id="menuPrice" placeholder="메뉴 가격을 입력하세요." class="form-control" autofocus required>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label" for="menuImage" style="height: 34px; margin: 0; text-align: center; line-height: 34px;">메뉴 사진</label>
				<div class="col-sm-8" style="margin-top: 10px;">  
					<input type="file" name="menuImage" id="menuImage" required>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8" style="margin-top: 10px;">
					<button type="submit" id="menuadd" name="menuadd" class="btn btn-default">메뉴 추가</button>
				</div>
			</div>
			<sec:csrfInput />
		</form>
	</div>

	<style>
.label {
	text-align: left;
}

.mainTemplate {
	background: #fafafa;
}
</style>
	<div class="row">
		<c:forEach items="${requestScope.menu}" var="item">
			<div class="col-md-6" style="margin-top: 10px;">
				<div style="margin-right: 10px; background: #fff; width: 100%; height: 100%; padding: 10px;" class="col-md-12">
					<img src="${initParam.rootPath }/menuPicture/${item.menuPicture}" width="100px" height="100" class="col-sm-3"> <span class="label col-sm-9"
						style="color: #000; font-size: 14px;"
					>이름 : ${item.menuName}</span> <span class="label col-sm-9" style="color: #000; font-size: 14px;">설명 : ${item.menuComment}</span> <span
						class="label col-sm-9" style="color: #000; font-size: 14px;"
					>가격 : ${item.menuPrice}</span>
					<button class="deleteMenu btn btn-default col-sm-3" value="${item.menuNum}">삭제</button>

					<form action="${initParam.rootPath }/getMenuByNum.do" method="post" class="menusubmit col-sm-3">
						<input type="hidden" value="${item.menuNum}" name="menuNum">
						<button type="submit" class="menuNum btn btn-default col-sm-12">수정</button>
						<sec:csrfInput />
					</form>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

