<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<!-- Main content -->
<section class="content">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">List Board</h3>
		</div>
		<div class="row">
			<div class="col-md-4">
			<!--글쓰기 버튼 : 버튼을 누르면 다른 페이지로 이동하고 싶은 경우 onclick="location.href=''" 사용-->
			<button type="button" class="btn btn-success">새글작성</button>
			</div>
			<div class="col-md-4 offset-md-4">
			<!--검색 들어갈 부분-->
			<!-- 현재 5개의 값(bno,page,amount,criteria,keyword)을 따라가야지 페이지에서 널포인터입셉션이 안남, 그래서 page와 amount추가 -->
			<form action="/list.do" method="get" id="search">
				<input type="hidden" name="page" value="${pageDto.page}" />
				<input type="hidden" name="amount" value="${pageDto.amount}" />	
			<select name="criteria" id="">
			<!-- 사용자가 선택한 옵션의 값과 검색어가 유지되도록 하기 -->
				<option value="n"  <c:out value="${searchDto.criteria == null?'selected':''}"/>>----------------</option>
				<option value="title"  <c:out value="${searchDto.criteria == 'title'?'selected':''}"/>>title</option>
				<option value="content"  <c:out value="${searchDto.criteria == 'content'?'selected':''}"/>>content</option>
				<option value="name"  <c:out value="${searchDto.criteria == 'name'?'selected':''}"/>>name</option>
			</select>
			<input type="text" name="keyword" id="" value="${searchDto.keyword}"/>
				<button type="button" class="btn btn-primary">검색</button>
			</form>
			</div>
		</div>
		<br>
		<table class="table table-bordered">
			<tr>
				<th class='text-center' style='width:100px'>번호</th>
				<th class='text-center'>제목</th>
				<th class='text-center'>작성자</th>
				<th class='text-center'>날짜</th>
				<th class='text-center' style='width:100px'>조회수</th>
			</tr>
			<c:forEach var="dto" items="${list}">
			<tr><!-- 리스트 목록 보여주기 -->
				<td class='text-center'>${dto.bno}</td><!--번호-->
				<td><!--제목 : 댓글의 경우 한칸씩 안쪽으로 보여주기 위함-->
					<c:if test="${dto.re_lev!=0}">
						<c:forEach begin="0" end="${dto.re_lev*1}"><!-- 가지고 있는 re_lev에 곱하기 1을 하라는 뜻 0=0,1=1,2=2... -->
							&nbsp;<!--&nbsp; : 스페이스바 한번 누르기  -->
						</c:forEach>
					</c:if>
					<!-- search를 해도 기존 게시판 페이지가 유지되는 것을 수정 -->
					<a href="${dto.bno}" class="count">${dto.title}</a>
				</td>
				<td class='text-center'>${dto.name}</td><!--작성자-->
				<td class='text-center'>${dto.regdate}</td><!--날짜-->
				<td class='text-center'><span class="badge badge-pill badge-primary">${dto.readcount}</span></td>
			</tr>		
			</c:forEach>
		</table>
		<div class="container">
			<div class="row  justify-content-md-center">
				<nav aria-label="Page navigation example">
				  <ul class="pagination">
				  <!--하단의 페이지 나누기 부분-->	
						<c:if test="${pageDto.prev}">
						<li class="page-item">
						<!-- 이전페이지도 10 페이지씩 넘어가도록 설정 -->
					      <a class="page-link move" href="${pageDto.page-10}">Previous</a>
					    </li>
						</c:if>
					    <!-- 하단의 1,2,3,4..를 반복시키는 부분 -->			    
					    <c:forEach begin="${pageDto.startPage}" end="${pageDto.endPage}" var="idx">
					    <!-- 사용자가 누른 페이지와 주소줄에 번호가 같다면 그 번호에 active를 줘서 색상을 변경해달라는 뜻 -->
					    <li class="page-item ${pageDto.page==idx?'active':''}" aria-current="page">
					      <a class="page-link move" href="${idx}">${idx}</a>
					    </li>
					    </c:forEach>
					    
					    <c:if test="${pageDto.next}">
					    <li class="page-item">
					    <!-- 다음페이지로 10페이지씩 이동하도록 설정 -->
					      <a class="page-link move" href="${pageDto.page+10}">Next</a>
					    </li>
					    </c:if>
				  </ul>
				</nav>					
			</div>
		</div>
		<div style="height:20px"></div>
	</div>	
</section>
<form action="" method="get" role="form" id="actionForm">
	<input type="hidden" name="page" value="${pageDto.page}" />
	<input type="hidden" name="amount" value="${pageDto.amount}" />
	<input type="hidden" name="criteria" value="${pageDto.searchDto.criteria}" />
	<input type="hidden" name="keyword" value="${pageDto.searchDto.keyword}" />
	<input type="hidden" name="bno" value="" />
</form>
<script src="/js/list.js"></script>
<%@include file="../include/footer.jsp"%>

