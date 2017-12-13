<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
function move(url) {
	location.href=url;
}

function boardUpdateCheck() {
	var form = document.BoardUpdateForm;
	return true;
}
</script>

</style>


	<table summary="글수정 전체 테이블">
		<form name="BoardUpdateForm" method="post" action="" onsubmit="return boardUpdateCheck();" >

		<table summary="테이블 구성" >
		<caption>글 수정하기</caption>	
    		<tr>
				<td>작성자</td>
				<td><input type=text name=name size=10 maxlength=8></td>
			</tr>
    		<tr>
     			<td>제목</td>
     			<td><input type=text name=title></td>
    		</tr>
    		<tr>
     			<td>내용</td>
     			<td><textarea name=content rows ="10" cols="100"></textarea></td>
    		</tr>
    		<tr>
     			<td colspan=2><hr size=1></td>
    		</tr>
    		<tr>
     			<td colspan="2"><div align="center">
     			<input type="submit" value="수정 완료">&nbsp;&nbsp;
				<input type=reset value="다시 수정"> 
         		<input type="button" value="뒤로" onclick="move('board_list.jsp');"></div>
     			</td>
    		</tr> 
		</table>
	</form> 
</table>

