<%--
  /**
  * Class Name : 
  * Description : bootstrap Tab 템플릿
  * Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2020. 3. 9.            최초 생성
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
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
      
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>부트스트랩 HR_LIST 템플릿</title>

    <!-- 부트스트랩 -->
    <link href=" <c:url value='/css/bootstrap.min.css'/> "  rel="stylesheet">

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
		  <h2>Tab</h2>
		</div>
        <!--// div title -->
		<div class="row text-right">
		    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label"></label>
		    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
				<input  type="button" class="btn btn-primary btn-sm"  value="목록" id="to_list_btn" />
				<input  type="button" class="btn btn-primary btn-sm" value="등록" id="insert_btn" />
			</div>
		</div>
       <!-- div title -->
		<div class="col">
            <ul class="nav nav-tabs">
              <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#board">게시판</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#member">회원</a>
              </li>
            </ul>
            <div class="tab-content">
              <div class="tab-pane fade show active" id="board">
                <pre>사람만이 희망이다
희망찬 사람은
그 자신이 희망이다
길 찾는 사람은
그 자신이 새 길이다

참 좋은 사람은
그 자신이 이미 좋은 세상이다

사람 속에 들어 있다
사람에서 시작된다

다시

사람만이 희망이다
(박노해·시인, 1958-)</pre>
              </div>
              <div class="tab-pane fade" id="member">
                <pre>
+ 신이 내게 소원을 묻는다면

신이 내게 소원을 묻는다면
나는 부나 권력을 달라고 청하지 않겠다.
대신 식지 않는 뜨거운 열정과
희망을 바라볼 수 있는
영원히 늙지 않는 생생한 눈을 달라고 하겠다.
부나 권력으로 인한 기쁨은
시간이 지나가면 시들지만
세상을 바라보는 생생한 눈과
희망은 시드는 법이 없으니까!
(키에르케고르·덴마크 종교철학자, 1813-1855)                
                </pre>
              </div>

            </div>
        </div>
      
 </div>     
 <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
 <script src="<c:url  value='/js/bootstrap.min.js' />"> </script>
 <script type="text/javascript">
    
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	  var target = $(e.target).attr("href") // activated tab
	  //alert(target);
	  if (tab.parent().hasClass('active')) {
		  target.removeClass("display:none;");  
	  }else{
		  
	  }
	  
	}); 
 
 </script>   	 
</body>
</html>