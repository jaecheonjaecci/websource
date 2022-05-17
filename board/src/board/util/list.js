/**
 * qna_board_list.jsp
 */
$(function () {
  //새글 작성을 클릭하면 actionForm을 보내기
  $(".btn-success").click(function () {
    $("#actionForm").find("[name='bno']").remove();
    $("#actionForm").attr("action", "view/qna_board_write.jsp");
    $("#actionForm").attr("method", "get");
    $("#actionForm").submit();
  });

  //제목을 클릭하면 actionForm 보내기
  $(".count").click(function (e) {
    e.preventDefault();

    //href에는 bno의 값이 들어있음
    let href = $(this).attr("href");

    //주소줄에 bno와 나머지 페이지,보여줄페이지,검색어,키워드를 같이 넣음
    $("#actionForm").find("[name='bno']").val(href);
    //주소줄의 정보를 포함 시킨 후  원래 가려고 했던 곳으로 보냄
    $("#actionForm").attr("action", "/countUpdate.do");
    $("#actionForm").submit();
  });

  //페이지 번호를 클릭하면 action form 보내기
  //a가 action하지 못하게 막은 후,
  $(".move").click(function (e) {
    e.preventDefault();

    let href = $(this).attr("href");

    $("#actionForm").find("[name='bno']").remove();
    $("#actionForm").find("[name='page']").val(href);
    $("#actionForm").attr("action", "/list.do");
    $("#actionForm").submit();
  });

  //검색어에서 엔터 사용 방지
  //엔터 사용시 criteria값이 넘어오지 않아서 검색이 안될 수 있음
  $(":text").keydown(function (e) {
    if (e.keyCode == 13) {
      e.preventDefault();
    }
  });

  //검색버튼을 누르면
  //검색조건(criteria), 키워드(keyword) 가져온 후
  //내용이 안들어 있으면 메세지 띄우기
  $(".btn-primary").click(function () {
    let criteria = $("[name='criteria']");
    let keyword = $("[name='keyword']");

    //대쉬를 넣어놓았기 때문에 n을 넣어야 함
    if (criteria.val() == "n") {
      alert("검색조건을 선택해주세요");
      criteria.focus();
      return;
    } else if (keyword.val() == "") {
      alert("검색어를 작성해주세요");
      keyword.focus();
      return;
    }
    //검색을 시행한 후 page의 값을 1로 변경해주기
    $("#search").find("[name='page']").val("1");

    $("#search").submit();
  });
});
