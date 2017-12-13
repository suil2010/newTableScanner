<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(document).ready(function() {
		
		BoardlistComment();
		
		$("#btnComment").on("click",function(){
			$.ajax({                
	            "url": "${initParam.rootPath }/insertCommen.do",
	            "type": "get",
	            "data" : {
	            	"boardNum":$('#boardNum').val(),
	            	"commenText":$("#commenText").val(),
	            	'${_csrf.parameterName}':'${_csrf.token}'
	            },
	            "dataType":"text",
	            success: function(){
	                alert("댓글이 등록되었습니다.");
	                BoardlistComment();
	                document.getElementById("commenText").value="";
	            },
	           	"error":function(){
	           		alert("오류 발생");
	           		
	           	}
	      });
	 });	
});
	
	function BoardlistComment(){
		$.ajax({
        "url" : "${initParam.rootPath }/commen_list.do",
        "type": "get",
        "data" : {"boardNum":$('#boardNum').val()},
        "dataType" : "json",
        "success" : function(list){
            var txt = "";
            $.each(list, function(){ 
            	txt += '<div class="BoardComment">';
                txt += '<div class=listComment>'+ this.commenNum + " " +'작성자 : '+this.writerId+' / 등록 일자 : '+ this.commenWritingTime;
                txt += "<p> 내용 : "+this.commenText +"</p>";
                txt += '</div></div>';
            });
            $("#BoardCommentList").html(txt);
		},
        "error":function(a,b,c){
       		alert("아 개같네");
        }
		
		});
	}	
	
	function formatJSONDate(jsonDate) {
		var newDate = dateFormat(jsonDate, "mm/dd/yyyy");
		return newDate;
	}

</script>

<div class="container" style="width: 800px;">
	<h2>게시글 상세보기</h2>

	<div>
		게시글 번호 : <input type="text" name="boardNum" id="boardNum"
			value="${requestScope.board.boardNum}" readonly="readonly">
	</div>

	<div>작성자 : ${requestScope.board.memberId}</div>
	<div>제목 : ${requestScope.board.boardSubject}</div>
	<div>내용 : ${requestScope.board.boardText}</div>
	<div>
		작성 시간 :
		<fmt:formatDate value="${requestScope.board.boardTime}"
			pattern="yyyy-MM-dd hh:mm" />
	</div>

	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.memberId" var="Id" />

		<c:if test='${requestScope.board.memberId == Id}'>
			<form action="${initParam.rootPath }/updateBoard_form.do"
				method="post">
				<input type="hidden" value="${requestScope.board.boardNum}"
					name="boardNum">
				<button type="submit" id="btnUpdete">수정</button>
				<sec:csrfInput />
			</form>
			<form action="${initParam.rootPath }/deleteBoardByNum.do"
				method="post">
				<input type="hidden" value="${requestScope.board.boardNum}"
					name="boardNum">
				<button type="submit" id="btnDelete">삭제</button>
				<sec:csrfInput />
			</form>
		</c:if>

		<c:if test="${Id == 'admin'}">
			<form action="${initParam.rootPath }/deleteBoardByNum.do"
				method="post">
				<input type="hidden" value="${requestScope.board.boardNum}"
					name="boardNum">
				<button type="submit" id="btnDelete">삭제</button>
				<sec:csrfInput />
			</form>
		</c:if>
	</sec:authorize>

	<p>
	<div id="BoardCommentList" style="float: left; width: 100%;"></div>

	<div style="float: left; width: 100%;">
		<textarea name="commenText" id="commenText" cols="20" rows="5"
			placeholder="댓글을 쓰세요" style="float: left;"></textarea>
		<button type="button" id="btnComment">댓글 등록</button>
	</div>

	<sec:csrfInput />

</div>