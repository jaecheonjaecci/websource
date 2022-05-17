//영화진흥위원회 일일박스 오피스 오픈 api - xml 데이터
$(function () {
  init();

  //버튼을 누르면 사용자가 입력한 날짜에 데이터가 뜰 수 있도록
  //url의 날짜부분을 받아오도록 설정함
  $(":button").click(function () {
    let url =
      "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=f5eef3421c602c6cb7ea224104795888&targetDt=";
    url += $("#txtYear").val() + $("#selMon").val() + $("#selDay").val();

    console.log(url);

    $.get({
      url: url,
      success: function (data) {
        console.log(data);

        if ($(data).find("dailyBoxOffice").text() == "") {
          alert("데이터가 없습니다.");
        } else {
          let resData = "";
          $(data)
            .find("dailyBoxOffice")
            .each(function () {
              //순위
              resData += $(this).find("rank").text() + " 위";

              //증감하는 것에 따라 순위가 증가하면 위로 가는 화살표와 숫자가 표시
              //순위가 떨어지면 아래가는 화살표와 숫자가 표시
              //순위의 변동폭은 자료 자체에서 -1,0 이런식으로 넘어옴
              //괄호안에 기호와 숫자가 들어와야 하기때문에 닫는 괄호를 따로 붙임
              let rankInten = $(this).find("rankInten").text();

              if (rankInten > 0) resData += "(▲";
              else if (rankInten < 0) resData += "(▼";
              else resData += "(";

              resData += rankInten + " )";

              //영화코드
              let movieCd = $(this).find("movieCd").text();

              //영화이름
              let movieNm = $(this).find("movieNm").text();

              resData +=
                "<a href='#' onclick='javascript:show(" +
                movieCd +
                ")'>" +
                movieNm +
                "</a><br>";
            });
          $("#msg").html(resData);
        }
      },
    });
  }); //$(:button) end
}); //$(function()) end
function show(movieCd) {

  //영화상세정보 요청을 위한 url
  let url =
    "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.xml?key=f5eef3421c602c6cb7ea224104795888&movieCd=";
  url += movieCd;

  console.log(url);

  $.get({
    url:url,
    success:function(data){
      console.log(data);

      //영화제목- 한글
      let movieNm = $(data).find("movieNm").text();

      //영화제목 - 영어
      let movieNmEn = $(data).find("movieNmEn").text();

      //상영시간
      let showTm = $(data).find("showTm").text();

      //감독명
      //다른 것과는 다르게 디렉터스 안에 들어있기 때문에 한번 더 들어가서 찾음
      let directorNm = $(data).find("director").find("peopleNm").text();

      //출연배우명 - 한개가 아닌 여러개의 데이터를 가져와야 하기 때문에 for문을 돌림
      //actor를 가져와서 each를 돌리면 됨
      let peopleNm="";

      //출연배우의 수 
      let length = $(data).find("actor").find("peopleNm").length;

      $(data).find("actor").each(function(idx){ //idx = for(var i=0...)에 i에 해당
      if(idx==length-1){
        peopleNm+=$(this).find("peopleNm").text();

      }else{
        peopleNm+=$(this).find("peopleNm").text()+", ";
      }
      })

      //보여주기
      let resData = "<ul>";
      resData +="<li>영화제목(국문) : "+movieNm+"</li>";
      resData +="<li>영화제목(영문) : "+movieNmEn+"</li>";
      resData +="<li>상영시간 : "+showTm+" 분</li>";
      resData +="<li>감독 : "+directorNm+"</li>";
      resData +="<li>출연배우 : "+peopleNm+"</li></ul>";

      $(".box3").html(resData);
    }
  })



} // show() end

function init() {
  //어제 날짜 추출하기
  //new Date() => 날짜 객체에서 가져오기
  let newDate = new Date();
  let year = newDate.getFullYear(); //연도
  let month = newDate.getMonth() + 1; //월, 0부터 시작
  let day = newDate.getDate() - 1; //일, 어제 날짜를 얻기 위해 -1

  //year에 년도 넣기
  $("#txtYear").val(year);

  //1-9가 나오면 앞에 0을 붙이라는 뜻(월,일)
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  //월,일 넣기
  $("#selMon").val(month);
  $("#selDay").val(day);
}
