<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
#droppable>div {
	margin: 0;
	width: 100px;
	height: 100px;
	background: rgba(0, 0, 0, 0.1);
	position: absolute;
}
</style>


<script type="text/javascript">
	$(document).ready(function() {
		$("#droppable > div").draggable({
			containment : "#droppable",
			scroll : false,

		});
		// 그전기록을 불러올때 그거에 부여할것
		$(".draggable").on("dragstop", function() {
			var position = $(this).position();
			$(".x").val(position.left);
			$(".y").val(position.top);
		});

		$("#droppable").droppable({
			accept : "#draggable"
		});
		$("#tablecreate").on("click", $(".draggable"), function() {
			var text = $(".people").val();
			$("#droppable").append("<div class=draggable><span>" + text + "</span><p><button class='btn btn-default removebtn'>삭제</button></div>");
			$("#droppable > div").draggable({
				containment : "#droppable",
				scroll : false,
			});
		});
		$(".form_submit").on("click", function() {

			$(".draggable").each(function(index, item) {
				var divposition = $(this).position();
				var divvalue = $(this).children("span").text();
				var String1 = "<input type=hidden name=tableXY value="+ divposition.left + ">";
				var String2 = "<input type=hidden name=tableXY value="+ divposition.top + ">";
				var String3 = "<input type=hidden name=tableXY value="+ divvalue + "> <br>";
				$(".tableform").append(String1);
				$(".tableform").append(String2);
				$(".tableform").append(String3);

			});

		});

		$("#droppable").on("click", ".removebtn" ,function() {   
			$(this).parent().parent().detach();
		});

		
	});
</script>
<div class="container" style="max-width: 700px; padding-top: 50px; width: 100%; padding-left: 0; padding-right: 0;">
	<div class="col-sm-12" style="height: 500px; width: 700px; padding: 0;">
		<div id="droppable" style="width: 100%; height: 100%; border: 1px solid #000; position: relative;">
			<c:forEach items="${requestScope.Table}" var="item">
				<div class="draggable" style="top: ${item.yLocation}px; left: ${item.xLocation}px;" >
					<span>${item.tablePeople}</span>
					<p>
					<button class="btn btn-default removebtn">삭제</button>
				</div>

			</c:forEach>
		</div>
	</div>
	<div class="col-sm-12">
		<div class="form-group">
			<label class="control-label col-sm-3">인원수</label>
			<div class="input-group col-sm-6" style="float: left;">
				<input type="number" name="people" class="people form-control">
			</div>
			<button id="tablecreate" class="btn btn-default col-sm-3">생성</button>
			<form class="tableform" method="post" action="${initParam.rootPath}/insertTable.do">

				<input type="submit" value="완료" class="form_submit btn btn-default">
				<sec:csrfInput />
			</form>

		</div>
	</div>
</div>
