<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript">
	$(function() {
		/* header menu */
		$(".mainmenu > li").on("mouseover", function() {
			$(this).children(".submenu").stop().slideDown();
		});
		$(".mainmenu > li").on("mouseleave", function() {
			$(this).children(".submenu").stop().slideUp();
		});

		/* header member 로그아웃 */
		$(".logout").on("click", function() { 
			$("#logoutForm").submit();
		});

		$(".open").on("click", function() {
			$(".menu2").css({
				"display" : "block"
			});
		});
		$(".close").on("click", function() {
			$(".menu2").css({
				"display" : "none"
			});
		});

	});
</script>

<style type="text/css">
/* 공통 */
* {
	margin: 0;
	padding: 0;
	list-style: none;
	text-decoration: none;
}

/* header menu*/
.mainmenu>li {
	float: left;
	height: 40px;
	text-align: center;
	line-height: 40px;
	position: relative;
	padding: 0;
	font-size: 20px;
}

.mainmenu>li>a {
	color: #1e1e1e;
}

.submenu {
	padding-left: 10px;
	padding-right: 10px;
	width: 100%;
	position: absolute;
	z-index: 1000;
	display: none;
}

.submenu>li {
	width: 100%;
	font-size: 12px;
	background: #fff;
}

.submenu>li>a {
	color: #1e1e1e;
}

@media ( min-width :1199px) {
	.header1 {
		display: block;
	}
	.header2 {
		display: none;
	}
}

@media ( max-width :1199px) {
	.header1 {
		display: none;
	}
	.header2 {
		display: block;
	}
}

.menu2>ul>li {
	width: 100%;
	height: 100px;
	text-align: center;
	background: rgba(0, 0, 0, 0.7);
	float: right;
}

.menu2>ul>li>a {
	color: #fff;
	line-height: 100px;
	font-size: 16px;
}

.menu2>ul>li:hover {
	background: rgba(0, 0, 0, 1);
}

.li {
	text-align: left !important;
	color: #fff !important;
	height: 50px !important;
	line-height: 50px;
	font-size: 20px;
	padding-left: 20px;
	background: rgba(0, 0, 0, 1) !important;
}
</style>
<!-- 1200px 미만 display -->
<form id="logoutForm" action="${initParam.rootPath }/logout.do" method="post" style="display: none">
	<sec:csrfInput />
</form>

<header class="navbar-static-top header2" style="height: 60px;">
	<div class="col-md-10 logo col-xs-10" style="height: 100%;">
		<a href="${initParam.rootPath }/index.do"><span style="font-size: 32px; line-height: 60px; color: #28B78D; text-align: center;">TableScanner</span></a>
	</div>
	<button type="button" class="btn btn-default btn-lg open" style="float: right; margin-top: 8px; margin-right: 15px;">
		<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	</button>
	<div class="menu2" style="height: 100%; width: 250px; right: 0; position: absolute; z-index: 100; display: none;">
		<ul>

			<li class="close" style="height: 50px; background: black !important;"><span class="glyphicon glyphicon-remove"
				style="color: #fff; font-size: 30px; margin-top: 5px;"
			></span></li>
			<!-- 개발자 -->
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="li">회원관리</li>
				<li><a href="${initParam.rootPath }/find_authority_admin.do">admin 회원 보기</a></li>
				<li><a href="${initParam.rootPath }/find_authority_member.do">member 회원 보기</a></li>
				<li><a href="${initParam.rootPath }/find_authority_owner.do">owner 회원 보기</a></li>
				<li><a href="${initParam.rootPath }/find_drop_member.do">탈퇴 회원 보기</a></li>
				<li><a href="${initParam.rootPath }/find_rt.do">음식점 정보 보기</a></li>
			</sec:authorize>

			<!-- 비회원 -->
			<sec:authorize access="!isAuthenticated()">
				<li><a href="${initParam.rootPath }/board_list.do">공지사항</a></li>
				<li><a href="#">추천랭킹</a></li>
			</sec:authorize>

			<!-- 회원 -->
			<sec:authorize access="isAuthenticated()">
				<li class="col-sm-2"><a href="${initParam.rootPath }/board_list.do">공지사항</a></li>
			</sec:authorize>
			
			<!-- 일반회원 -->
			<sec:authorize access="hasRole('ROLE_MEMBER')">
				<li><a href="#">추천랭킹</a></li>
				<li><a href="${initParam.rootPath }/myBookmarkList.do">즐겨찾기</a></li>
			</sec:authorize>

			<!-- 사업자회원 -->
			<sec:authorize access="hasRole('ROLE_OWNER')">

				<li class="li">음식점관리</li>
				<li><a href="${initParam.rootPath}/find_rt_byid.do">음식점 정보수정</a></li>
				<li><a href="${initParam.rootPath }/selectTable.do">테이블 수정</a></li>
				<li><a href="${initParam.rootPath }/menu_businessId.do">메뉴 수정</a></li>

				<li class="li">예약관리</li>
				<li><a href="${initParam.rootPath }/owner/reservation_info.do">예약조회</a></li>
				<li><a href="${initParam.rootPath }/ownerRestaurantInfo.do">예약등록</a></li>

				<li><a href="#">리뷰</a></li>
				<li><a href="${initParam.rootPath }/ownerMyQuestion.do">문의</a></li>
			</sec:authorize>

		</ul>

		<!-- 개발자  -->
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="#" class="logout"><button class="btn btn-default">로그아웃</button></a>
		</sec:authorize>

		<!-- 비회원 -->
		<sec:authorize access="!isAuthenticated()">
			<a href="${initParam.rootPath }/login_form.do"><button class="btn btn-default">로그인</button></a>
			<a href="${initParam.rootPath }/join_member_form.do"><button class="btn btn-default">회원가입</button></a>
		</sec:authorize>

		<!-- 일반회원 -->
		<sec:authorize access="hasRole('ROLE_MEMBER')">
			<a href="#" class="logout"><button class="btn btn-default">로그아웃</button></a>
			<a href="${initParam.rootPath }/mypage/member_info.do"><button class="btn btn-default">마이페이지</button></a>
		</sec:authorize>

		<!-- 사업자회원 -->
		<sec:authorize access="hasRole('ROLE_OWNER')">
			<a href="#" class="logout"><button class="btn btn-default">로그아웃</button></a>
			<a href="${initParam.rootPath }/mypage/member_info.do"><button class="btn btn-default">마이페이지</button></a>
		</sec:authorize>








	</div>

</header>

<!-- 1200px 이상 display -->
<header class="navbar-static-top header1" style="height: 60px;">
	<div class="col-sm-2 logo" style="height: 100%;">
		<a href="${initParam.rootPath }/index.do"><span style="font-size: 32px; line-height: 60px; color: #28B78D; text-align: center;">TableScanner</span></a>
	</div>
	<div class="col-sm-7 menu" style="height: 100%;">
		<ul class="mainmenu" style="list-style: none; background: red; margin-top: 10px;">

			<!-- 개발자 -->
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="col-sm-2"><a href="#">회원관리</a>
					<ul class="submenu" style="list-style: none;">
						<li><a href="${initParam.rootPath}/find_authority_admin.do">admin 회원 보기</a></li>
						<li><a href="${initParam.rootPath }/find_authority_member.do">member 회원 보기</a></li>
						<li><a href="${initParam.rootPath }/find_authority_owner.do">owner 회원 보기</a></li>
						<li><a href="${initParam.rootPath }/find_drop_member.do">탈퇴 회원 보기</a></li>
						<li><a href="${initParam.rootPath }/find_rt.do">음식점 정보 보기</a></li>
					</ul></li>
			</sec:authorize>

			<!-- 비회원 -->
			<sec:authorize access="!isAuthenticated()">
				<li class="col-sm-2"><a href="${initParam.rootPath }/board_list.do">공지사항</a></li>
				<li class="col-sm-2"><a href="#">추천랭킹</a></li>
			</sec:authorize>
	
			<!-- 회원 -->
			<sec:authorize access="isAuthenticated()">
				<li class="col-sm-2"><a href="${initParam.rootPath }/board_list.do">공지사항</a></li>
			</sec:authorize>
			
			<!-- 일반회원 -->
			<sec:authorize access="hasRole('ROLE_MEMBER')">
				<li class="col-sm-2"><a href="#">추천랭킹</a></li>
				<li class="col-sm-2"><a href="${initParam.rootPath }/myBookmarkList.do">즐겨찾기</a></li>
			</sec:authorize>

			<!-- 사업자회원 -->
			<sec:authorize access="hasRole('ROLE_OWNER')">
				<li class="col-sm-2"><a href="${initParam.rootPath}/find_rt_byid.do"">음식점관리</a>
					<ul class="submenu" style="list-style: none;">
						<li><a href="${initParam.rootPath}/find_rt_byid.do">음식점 정보수정</a></li>
						<li><a href="${initParam.rootPath }/selectTable.do">테이블 수정</a></li>
						<li><a href="${initParam.rootPath }/menu_businessId.do">메뉴 수정</a></li>
					</ul></li>
				<li class="col-sm-2"><a href="${initParam.rootPath }/owner/reservation_info.do">예약관리</a>
					<ul class="submenu" style="list-style: none;">
						<li><a href="${initParam.rootPath }/owner/reservation_info.do">예약조회</a></li>
						<li><a href="${initParam.rootPath }/ownerRestaurantInfo.do">예약등록</a></li>
					</ul></li>
				<li class="col-sm-2"><a href="#">리뷰</a></li>
				<li class="col-sm-2"><a href="#">문의</a></li>
			</sec:authorize>
		</ul>
	</div>
	<div class="col-sm-3 member" style="height: 100%; padding: 10px; text-align: center;">



		<!-- 개발자  -->
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="#" class="logout"><button class="btn btn-default">로그아웃</button></a>
		</sec:authorize>

		<!-- 비회원 -->
		<sec:authorize access="!isAuthenticated()">
			<a href="${initParam.rootPath }/login_form.do"><button class="btn btn-default">로그인</button></a>
			<a href="${initParam.rootPath }/join_member_form.do"><button class="btn btn-default">회원가입</button></a>
		</sec:authorize>

		<!-- 일반회원 -->
		<sec:authorize access="hasRole('ROLE_MEMBER')">
			<a href="#" class="logout"><button class="btn btn-default">로그아웃</button></a>
			<a href="${initParam.rootPath }/mypage/member_info.do"><button class="btn btn-default">마이페이지</button></a>
		</sec:authorize>

		<!-- 사업자회원 -->
		<sec:authorize access="hasRole('ROLE_OWNER')">
			<a href="#" class="logout"><button class="btn btn-default">로그아웃</button></a>
			<a href="${initParam.rootPath }/mypage/member_info.do"><button class="btn btn-default">마이페이지</button></a>
		</sec:authorize>

	</div>
</header>