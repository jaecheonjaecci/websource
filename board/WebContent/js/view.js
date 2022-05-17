/**
 * qna_board_view.jsp 스크립트
 */

$(function () {
  let formObj = $("[role='form']");

  //목록보기 버튼 클릭 시
  $("#list").click(function () {
    formObj.attr("action", "/list.do");
    formObj.attr("method", "get");
    
    //목록보기로 나온 뒤에는 bno 값이 필요하지 않기 때문에 지우기
    formObj.find("[name='bno']").remove();

    formObj.submit();
  });
  //삭제버튼 클릭시 - pwdCheck.jsp 보여주고,
  //bno값을 가지고 있는 formObj 같이 보내기
  $("#delete").click(function () {
    //form의 action을 변경하여 페이지를 이동하게 하고,form을 보냄(bno)
    formObj.attr("action", "/view/qna_board_pwdCheck.jsp");
    formObj.submit();
  });

  //수정버튼 클릭시
  $("#modify").click(function () {
    //form의 action을 변경하여 페이지를 이동하게 하고,form을 보냄(bno)
    formObj.attr("action", "/modify.do");
    formObj.submit();
  });

  //답변 버튼 클릭시
  $("#reply").click(function () {
    //form의 action을 변경하여 페이지를 이동하게 하고,form을 보냄(bno)
    formObj.attr("action", "/replyView.do");
    formObj.submit();
  });
});
