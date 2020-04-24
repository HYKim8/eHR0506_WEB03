<%--
  /**
  * Class Name : member_mng.jsp
  * Description : 회원관리
  * Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2020. 3. 10.            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.01.06
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>회원관리</title>

<!-- 파비콘-->
<link rel="shortcut icon" type="image/x-icon"
	href="${hContext}/resources/img/main/favicon.ico">
<!-- 부트스트랩 -->
<link href="${hContext}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<!-- div container -->
	<div class="container">
		<!-- div title -->
		<div class="page-header">
			<h1>회원관리</h1>
		</div>
		<!--// div title -->
		<!-- 검색영역 -->
		<div class="row">
			<div class="col-md-12 text-right">
				<form action="${hContext}/member/do_retrieve.do" name="member_frm"
					method="get" class="form-inline">
					<!-- pageNum -->
					<input type="hidden" name="pageNum" id="pageNum"
						value="${param.pageNum }">
					<div class="form-group">
						<select name="pageSize" id="pageSize"
							class="form-control input-sm">
							<option value="10"
								<c:if test="${param.pageSize == '10' }"> selected="selected"</c:if>>10
							</option>
							<option value="20"
								<c:if test="${param.pageSize == '20' }"> selected="selected"</c:if>>20
							</option>
							<option value="50"
								<c:if test="${param.pageSize == '50' }"> selected="selected"</c:if>>50
							</option>
							<option value="100"
								<c:if test="${param.pageSize == '100' }"> selected="selected"</c:if>>100
							</option>
						</select> <select name="searchDiv" id="searchDiv"
							class="form-control input-sm">
							<option value="">전체</option>
							<option value="10"
								<c:if test="${param.searchDiv == '10' }"> selected="selected"</c:if>>ID
							</option>
							<option value="20"
								<c:if test="${param.searchDiv == '20' }"> selected="selected"</c:if>>이름
							</option>
						</select> <input type="text" class="form-control input-sm" id="searchWord"
							name="searchWord" value="${param.searchWord }" placeholder="검색어">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" onclick="javascript:doRetrieve();"
							class="btn btn-primary btn-sm">조회</button>
					</div>
				</form>
			</div>
		</div>
		<!--// 검색영역 -->

		<!-- Grid영역 -->
		<div class="table-responsive">
			<table class="table table-striped table-bordered">
				<!-- hidden-sm hidden-xs 숨기기 -->
				<thead class="bg-primary">
					<th class="text-center col-lg-1 col-md-1 hidden-sm hidden-xs ">번호</th>
					<th class="text-center col-lg-3 col-md-3 col-sm-3 col-xs-3">ID</th>
					<th class="text-center col-lg-2 col-md-2 col-sm-1 col-xs-1">이름</th>
					<th class="text-center col-lg-1 col-md-1 hidden-sm hidden-xs  ">레벨</th>
					<th class="text-center col-lg-3 col-md-3 hidden-sm hidden-xs ">이메일</th>
					<th class="text-center col-lg-2 col-md-2 hidden-sm hidden-xs ">등록일</th>
				</thead>
				<tbody>
					<!-- Data있는 경우: list -->
					<c:choose>
						<c:when test="${list.size()>0 }">
							<c:forEach var="vo" items="${list }">
								<tr>
									<td class="text-center hidden-sm hidden-xs">${vo.num}</td>
									<td class="text-left">${vo.u_id }</td>
									<td class="text-left">${vo.name }</td>
									<td class="text-left hidden-sm hidden-xs">${vo.level }</td>
									<td class="text-left hidden-sm hidden-xs">${vo.email }</td>
									<td class="text-center hidden-sm hidden-xs">${vo.regDt }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="text-center" colspan="99">등록된 게시글이 없습니다.</td>
							</tr>
						</c:otherwise>

					</c:choose>

				</tbody>
			</table>
		</div>
		<!--// Grid영역 -->
		<!-- pagenation -->
        <div id="content"></div>
        <div class="text-center" id="page-selection"></div>
		<!--// pagenation -->
	</div>
	<!--// div container -->



	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${hContext}/resources/js/jquery-migrate-1.4.1.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${hContext}/resources/js/bootstrap.min.js"></script>
	<script src="${hContext}/resources/js/jquery.bootpag.min.js"></script>

	<script type="text/javascript">
		function doRetrieve() {
			//console.log("doRetrieve");  
			var frm = document.member_frm;
			frm.pageNum.value = 1;
			frm.action = "${hContext}/member/do_retrieve.do";
			frm.submit();
		}

        function doSearchPage(pageNum) {
            //console.log("doRetrieve");
            alert("pageNum:"+pageNum);  
            var frm = document.member_frm;
            frm.pageNum.value = pageNum;
            frm.action = "${hContext}/member/do_retrieve.do";
            frm.submit();
        }
        		
		$("#searchWord").on("keydown", function(key) {

			//console.log("searchWord keydown:" + key.keyCode);
			if (key.keyCode == 13) {
				doRetrieve();
			}
		});

		//((maxNum - 1) / rowPerPage) + 1
        $('#page-selection').bootpag({
            total: ${maxPageNo},   <!-- total pages -->
            page: 1,            <!-- current page -->
            maxVisible: 10,       <!-- Links per page -->
            leaps: true,
            firstLastUse: true,
            first: '←',
            last: '→',
            wrapClass: 'pagination',
            activeClass: 'active',
            disabledClass: 'disabled',
            nextClass: 'next',
            prevClass: 'prev',
            lastClass: 'last',
            firstClass: 'first'
        }).on("page", function(event, num){
            $("#pageNum").val(num); // or some ajax content loading...
            doSearchPage(num);
        });        		
	</script>
</body>
</html>





















