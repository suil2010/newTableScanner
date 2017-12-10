<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

	<script type="text/javascript">
		$(function(){
			/* header menu */
			$(".mainmenu > li").on("mouseover",function(){
			  $(this).children(".submenu").stop().slideDown();
			});
			$(".mainmenu > li").on("mouseleave",function(){
			  $(this).children(".submenu").stop().slideUp();
			});  

			/* header member 로그아웃 */
			$("#logout").on("click", function() {
				$("#logoutForm").submit();
			});

	
		});
	</script>

<style type="text/css">
 	/* 공통 */
	*{
		margin: 0;
		padding: 0;
		list-style: none;
		text-decoration: none;
	}

	/* header menu*/
	.mainmenu > li {
		float: left;
		height: 40px;
		text-align: center;
		line-height: 40px;
		position: relative;
		padding: 0; 
		font-size: 20px; 
	}
	.mainmenu > li > a{
		color: #1e1e1e;
	}
	.submenu{
		padding-left : 10px;
		padding-right : 10px; 
		width : 100%;
		position: absolute;
		z-index: 1000;
		display: none;
	}
	.submenu >li{
		width: 100%;
		font-size: 12px; 
		background: #fff;  
	}
	.submenu > li > a{
		color: #1e1e1e;  
	}

</style>

<header class="navbar-static-top" style="height: 60px;">
	<div class="col-sm-2 logo" style="height: 100%;">
		<a href="${initParam.rootPath }/index.do"><span style="font-size: 32px; line-height: 60px; color: #28B78D; text-align: center;">TableScanner</span></a>
	</div>
	<div class="col-sm-7 menu" style=" height: 100%;">
		<ul class="mainmenu" style="list-style: none; background: red; margin-top: 10px;">

			<!-- 개발자 -->
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="col-sm-2"><a href="#">공지사항</a></li>
				<li class="col-sm-2"><a href="#">회원관리</a>
					<ul class="submenu" style="list-style: none;">
						<li><a href="${initParam.rootPath}/find_authority_admin.do">admin 회원 보기</a></li>
						<li><a href="${initParam.rootPath }/find_authority_member.do">member 회원 보기</a></li>
						<li><a href="${initParam.rootPath }/find_authority_owner.do">owner 회원 보기</a></li>
						<li><a href="${initParam.rootPath }/find_drop_member.do">탈퇴 회원 보기</a></li>
						<li><a href="${initParam.rootPath }/find_rt.do">음식점 정보 보기</a></li>
					</ul>
				</li>
			</sec:authorize>

			<!-- 비회원 -->
			<sec:authorize access="!isAuthenticated()">
				<li class="col-sm-2"><a href="${initParam.rootPath }/board_list.do">공지사항</a></li>
				<li class="col-sm-2"><a href="#">추천랭킹</a></li>
			</sec:authorize>

			<!-- 일반회원 -->
			<sec:authorize access="hasRole('ROLE_MEMBER')">
				<li class="col-sm-2"><a href="#">공지사항</a></li>
				<li class="col-sm-2"><a href="#">추천랭킹</a></li>
				<li class="col-sm-2"><a href="${initParam.rootPath }/myBookmarkList.do">즐겨찾기</a></li>
			</sec:authorize>

			<!-- 사업자회원 -->
			<sec:authorize access="hasRole('ROLE_OWNER')">
				<li class="col-sm-2"><a href="#">음식점관리</a>
					<ul class="submenu" style="list-style: none;">
						<li><a href="${initParam.rootPath}/find_rt_byid.do">음식점 정보수정</a></li>
						<li><a href="${initParam.rootPath }/selectTable.do">테이블 수정</a></li>
						<li><a href="${initParam.rootPath }/menu_businessId.do">메뉴 수정</a></li>
					</ul>
				</li>
				<li class="col-sm-2"><a href="#">예약관리</a>
					<ul class="submenu" style="list-style: none;">
						<li><a href="#">예약조회</a></li>
						<li><a href="#">예약등록</a></li>
					</ul>
				</li>
				<li class="col-sm-2"><a href="${initParam.rootPath}/selectSales.do">통계</a></li>
				<li class="col-sm-2"><a href="#">리뷰</a></li>
				<li class="col-sm-2"><a href="#">문의</a></li>
				<li class="col-sm-2"><a href="#">공지사항</a></li>
			</sec:authorize>
		</ul>
	</div>
	<div class="col-sm-3 member" style="height: 100%; padding: 10px; text-align: center;">

	<form id="logoutForm" action="${initParam.rootPath }/logout.do" method="post" style="display: none">
		<sec:csrfInput />
	</form>

		<!-- 개발자  -->
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="#" id="logout"><button class="btn btn-default">로그아웃</button></a>
		</sec:authorize>

		<!-- 비회원 -->
		<sec:authorize access="!isAuthenticated()">
			<a href="${initParam.rootPath }/login_form.do"><button class="btn btn-default">로그인</button></a>
			<a href="${initParam.rootPath }/join_member_form.do"><button class="btn btn-default">회원가입</button></a>
		</sec:authorize> 

		<!-- 일반회원 -->
		<sec:authorize access="hasRole('ROLE_MEMBER')">
			<a href="#" id="logout"><button class="btn btn-default">로그아웃</button></a>
			<a href="${initParam.rootPath }/mypage/member_info.do"><button class="btn btn-default">마이페이지</button></a> 
		</sec:authorize> 
		
		<!-- 사업자회원 -->
		<sec:authorize access="hasRole('ROLE_OWNER')">
			<a href="#" id="logout"><button class="btn btn-default">로그아웃</button></a>
			<a href="${initParam.rootPath }/mypage/member_info.do"><button class="btn btn-default">마이페이지</button></a>
		</sec:authorize> 

	</div>
</header>