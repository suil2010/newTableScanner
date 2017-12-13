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

		$("#divnamebtn").click(function() {
			$("#divname").toggle(); //천천히 보이기
		});
	});
</script>

<div class="container">

	<button id="divnamebtn" class="btn btn-default">메뉴추가하기</button>
	<div id="divname" hidden="">
		<h6>메뉴추가</h6>
		<form method="post" action="${initParam.rootPath}/join_menu.do" enctype="multipart/form-data">
			<input type="hidden" name="menuNum" value="1" /> <input type="text" name="menuName" placeholder="메뉴 이름을 입력하세요"> <input type="text"
				name="menuComment" placeholder="메뉴에 대한 설명을 적으세요"
			> <input type="number" name="menuPrice" placeholder="메뉴 가격을 입력하세요."> <input type="file" name="menuImage" placeholder="메뉴 사진 이름">
			<input type="hidden" name="businessId" value="<sec:authentication property='principal.memberId'/>">
			<sec:csrfInput />
			<button type="submit" id="menuadd" class="btn btn-default">메뉴 추가</button>
		</form>

	</div>
	<style>
		.label {
		text-align: left;
		}
		.mainTemplate{
		background: #fafafa;
		}
	</style>
	<div class="row">
		<c:forEach items="${requestScope.menu}" var="item">
			<div class="col-md-6" style="margin-top: 10px; ">           
				<div style="margin-right: 10px; background: #fff; width: 100%; height: 100%; padding: 10px;" class="col-md-12">        
				<img src="${initParam.rootPath }/menuPicture/${item.menuPicture}" width="100px" height="100" class="col-sm-3"> 
				<span class="label col-sm-9" style="color: #000; font-size: 14px;">이름 : ${item.menuName}</span>    
				<span class="label col-sm-9" style="color: #000; font-size: 14px;">설명 : ${item.menuComment}</span> 
				<span class="label col-sm-9" style="color: #000; font-size: 14px;">가격 : ${item.menuPrice}</span>   
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

