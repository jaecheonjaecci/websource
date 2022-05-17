/**
 * 버튼(로그아웃, 회원탈퇴, 비밀번호 수정) 클릭시 동작하는 스크립트
 */
$(function(){
	$("#modify").click(function(){
		location.href="modifyForm.jsp";
	})
	$("#logout").click(function(){
	// 버튼을 누르면 logout.do로 이동
		location.href="/logout.do";	
	})
$("#leave").click(function() {
      location.href = "leaveForm.jsp";
   });
})