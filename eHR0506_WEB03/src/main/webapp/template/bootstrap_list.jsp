<%--
  /**
  * Class Name : 
  * Description : 
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
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set  var="hContext" value="${pageContext.request.contextPath }"></c:set>


<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>부트스트랩 템플릿_LIST</title>
    

    <!-- 부트스트랩 -->  
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">

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
  	    	<h1>게시판 목록</h1>
  	    </div>
  	    <!--// div title -->
  	    <!-- 검색영역 -->
    	<div class="row">
    		<div class="col-md-12 text-right">
	    		<form action="" class="form-inline">
	    			<div class="form-group">
	    				<select class="form-control input-sm">
	    					<option>10</option>
	    					<option>20</option>
	    					<option>50</option>
	    					<option>100</option>
	    				</select>
	    				<select class="form-control input-sm">
	    					<option>전체</option>
	    					<option>ID</option>
	    					<option>제목</option>
	    				</select>
	    				
	    				<input type="text"  class="form-control input-sm"  
	    				id="searchWord" name="searchWord" placeholder="검색어">
	    				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
	    				<button type="button" class="btn btn-primary btn-sm">조회</button> 
	    				<button type="button" class="btn btn-primary btn-sm">등록</button> 				
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
    				<th class="text-center col-lg-1 col-md-1 col-sm-1 hidden-xs ">번호</th>
    				<th class="text-center col-lg-8 col-md-8 col-sm-8 col-xs-8">제목</th>
    				<th class="text-center col-lg-1 col-md-1 col-sm-1 col-xs-1">작성자</th>
    				<th class="text-center col-lg-1 col-md-1 col-sm-1 hidden-xs  ">작성일</th>
    				<th class="text-center col-lg-1 col-md-1 hidden-sm hidden-xs ">조회수</th>
    			</thead>
    			<tbody>
    				<tr>
    					<td class="text-center hidden-sm hidden-xs">1</td>
    					<td class="text-left">제목입니다. 비둘기 아닙니다.</td>
    					<td class="text-center">이상무</td>
    					<td class="text-center hidden-sm hidden-xs  ">2020/03/10</td>
    					<td class="text-right hidden-sm hidden-xs">88</td>
    				</tr>
    				<tr>
    					<td class="text-center">2</td>
    					<td class="text-left">제목입니다. 비둘기 아닙니다.</td>
    					<td class="text-center">이상무</td>
    					<td class="text-center">2020/03/10</td>
    					<td class="text-right">88</td>
    				</tr>    				
    			</tbody>
    		</table>
    	</div>
    	<!--// Grid영역 -->    	
      <!-- pagenation -->
      <div class="text-center">
	       <ul class="pagination">
	        <li><a href="#">&lt;</a></li>
	        <li><a href="#">1</a></li>
	        <li><a href="#">2</a></li>
	        <li><a href="#">3</a></li>
	        <li><a href="#">4</a></li>
	        <li><a href="#">5</a></li>
	        <li><a href="#">&gt;</a></li>
	       </ul>
      </div>
      <!--// pagenation -->    	
    </div>
    <!--// div container -->
    
    
    
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="${hContext}/resources/js/jquery-migrate-1.4.1.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${hContext}/resources/js/bootstrap.min.js"> </script>
  </body>
</html>





















