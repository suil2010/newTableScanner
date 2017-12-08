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
		
		
        $("#divname").hide(); 
        $("#divnamebtn").click(function() {
            $("#divname").show('3000'); //천천히 보이기
        });
	});
</script>


<button id="divnamebtn">메뉴추가하기</button>
<div id="divname">
	<h6>메뉴추가</h6>
	<form method="post" action="${initParam.rootPath}/join_menu.do" enctype="multipart/form-data">
		<input type="hidden" name="menuNum" value="1" />
		<input type="text" name="menuName" placeholder="메뉴 이름을 입력하세요">
		<input type="text" name="menuComment" placeholder="메뉴에 대한 설명을 적으세요">
		<input type="number" name="menuPrice" placeholder="메뉴 가격을 입력하세요.">
		<input type="file" name="menuImage" placeholder="메뉴 사진 이름">
		<input type="hidden" name="businessId" value="<sec:authentication property='principal.memberId'/>">
		<sec:csrfInput />
		<button type="submit" id="menuadd">메뉴 추가</button>
	</form>

</div>
<div>
	<sec:csrfInput />
	<h1>VIEW - 메뉴 보기</h1>

	<table id="menu_tb">
		<thead id="thead">
			<tr>				
				<td>이미지</td>
				<td>메뉴이름</td>
				<td>메뉴 설명</td>
				<td>메뉴가격</td>
				<td>삭제</td>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach items="${requestScope.menu}" var="item">
				<tr>
					<td><img src="${initParam.rootPath }/menuPicture/${item.menuPicture}" width="100px" height="100"></td>
					<td>${item.menuName}</td>
					<td>${item.menuComment}</td>
					<td>${item.menuPrice}</td>
					<td>
						<button class="deleteMenu" name="deleteMenu" value="${item.menuNum}">삭제</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>