<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(document).ready(function() {
		BoardlistComment();

		$("#btnComment").on("click", function() {
			$.ajax({
				"url" : "${initParam.rootPath }/insertCommen.do",
				"type" : "get",
				"data" : {
					"boardNum" : $('#boardNum').text(),
					"commenText" : $("#commenText").val(),
					'${_csrf.parameterName}' : '${_csrf.token}'
				},
				"dataType" : "text",
				success : function() {
					alert("댓글이 등록되었습니다.");
					BoardlistComment();
					document.getElementById("commenText").value = "";
				},
				"error" : function() {
					alert("오류 발생");

				}
			});
		});
	});

	function BoardlistComment() {
		$.ajax({
			"url" : "${initParam.rootPath }/commen_list.do",
			"type" : "get",
			"data" : {
				"boardNum" : $('#boardNum').text()
			},
			"dataType" : "json",
			"success" : function(list) {
				var txt = "";
				$.each(list, function() {   
					txt += "<div class='listComment'><input type='hidden' value="+ this.commenNum + ">" + '작성자 : ' + this.writerId + ' (' + this.commenWritingTime + ')'
					+ "<p> 내용 : " + this.commenText + "</p>"
					+ '</div>';
				});
				$("#BoardCommentList").html(txt);
			},
			"error" : function(a, b, c) {
				alert("아 개같네");
			}

		});
	}

	function formatJSONDate(jsonDate) {
		var newDate = dateFormat(jsonDate, "mm/dd/yyyy");
		return newDate;
	}
</script>

<style>
	.listComment{
		min-height: 60px;
		    border-top: 1px solid #dcdcdc;
		    padding: 10px; 
	}
</style>

<div class="container" style="width: 800px; padding-top: 40px;">
	<div class="row" style="border-top: 2px solid #dababa; background-color: #f2e6e6; height: 33.4px; line-height: 33.4px;">

		<!-- 글번호 -->
		<div class="col-sm-2" id="boardNum" style="text-align: center;">${requestScope.board.boardNum}</div>

		<!-- 날짜 -->
		<div class="col-sm-6" style="text-align: center;">
			<fmt:formatDate value="${requestScope.board.boardTime}" pattern="yyyy-MM-dd hh:mm" />
		</div>

		<!-- 작성자 -->
		<div class="col-sm-2" style="text-align: center;">${requestScope.board.memberId}</div>
		<div class="col-sm-2"></div>
	</div>

	<!-- 제목 -->
	<div class="row" style="font-family: Dotum, 돋움, Helvetica; font-size: 14pt; font-weight: bold; color: brown; text-align: center; padding: 40px;">
		<div class="col-sm-12">${requestScope.board.boardSubject}</div>
	</div>

	<!-- 내용 -->
	<div class="row" style="min-height: 420px;">${requestScope.board.boardText}</div>

	<!-- 수정,삭제버튼 -->
	<div class="row" style="padding: 20px;">
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.memberId" var="Id" />

			<c:if test='${requestScope.board.memberId == Id}'>
				<div class="col-sm-2" style="float: right;">
					<form action="${initParam.rootPath }/updateBoard_form.do" method="post">
						<input type="hidden" value="${requestScope.board.boardNum}" name="boardNum">
						<button type="submit" id="btnUpdete" class="btn btn-default">수정</button>
						<sec:csrfInput />
					</form>
				</div>
				<div class="col-sm-2" style="float: right; padding: 0;">
					<form action="${initParam.rootPath }/deleteBoardByNum.do" method="post">
						<input type="hidden" value="${requestScope.board.boardNum}" name="boardNum">
						<button type="submit" id="btnDelete" style="float: right;" class="btn btn-default">삭제</button>
						<sec:csrfInput />
					</form>
				</div>
			</c:if>

			<c:if test="${Id == 'admin'}">
				<form action="${initParam.rootPath }/deleteBoardByNum.do" method="post">
					<input type="hidden" value="${requestScope.board.boardNum}" name="boardNum">
					<button type="submit" id="btnDelete">삭제</button>
					<sec:csrfInput />
				</form>
			</c:if>
		</sec:authorize>
	</div>



	<div id="BoardCommentList" style="float: left; width: 100%;"></div>

	<div class="row" style="padding: 20px;">
		<textarea name="commenText" id="commenText" cols="20" rows="4" placeholder="명예훼손,개인정보유출 , 분쟁유발 ,허위사실유포등의 글은 제제는 물론 법률에 의해 처벌받을수 있습니다."
			class="col-sm-9 col-xs-9" style="resize: none; padding: 10px;"
		></textarea>
		<div class="col-sm-1"></div>
		<button type="button" id="btnComment" class="btn btn-default col-sm-2 col-xs-2" style="height: 82px; margin-top: 10px;">등록</button>
	</div>

	<sec:csrfInput />

</div>