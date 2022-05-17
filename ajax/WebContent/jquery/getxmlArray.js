//getxmlArray.html에서 메뉴 4 클릭시 동작 : xml을 가져올 경우

//메뉴4와 이벤트 연결
//메뉴 4번 찾아오기 -> 찾아온 코드에 이벤트 부착(클릭이 일어나면 makeRequest 불러오기)
$(function(){
  $(".container div:last-child").click(function(){
    $.get({
      url:"/data/schoolArray.xml",
      success: function(data){
        let output = "<ul>";

        //.each(function(idx)) / .each(function()) / .each(function(idx,item))
        $(data).find("subject").each(function(){
          output += "<li>title : " + $(this).find("title").text() + "</li>"
          output += "<li>time : " + $(this).find("time").text() + "</li>"
          output += "<li>teacher : " + $(this).find("teacher").text() + "</li>"
        })
        output += "</ul>";
        $("#contents").html(output);
      },
      error:function(xhr,textStatus,errer){
        $("#contents").html("데이터 없음");
      }
    })
  })
})






// let last_div = document
//   .querySelector(".container div:last-child")
//   .addEventListener("click", makeRequest);

// let xhr = new XMLHttpRequest();

// function makeRequest() {
//   xhr.onreadystatechange = getJson;
//   xhr.open("get", "/data/schoolArray.xml");
//   xhr.send();
// }

// //서버가 응답하는 경우 호출되는 함수
// function getJson() {
//   //서버가 보내준 데이터를 contents영역에 보여주기
//   let contents = document.querySelector("#contents");

//   if (xhr.readyState == 4) {
//     if (xhr.status == 200) {
//       //xml 데이터를 자바스크립트 객체로 파싱
//       let response = xhr.responseXML;
//       console.log(response);

//       //xml 태그 자체로 보여줄 때
//       //  let school = response.getElementsByTagName("school");
//       //   contents.innerText = school[0].innerHTML;

//       let title = response.getElementsByTagName("title");
//       let time = response.getElementsByTagName("time");
//       let teacher = response.getElementsByTagName("teacher");

//       let data = "<ul>";
//       for (var i = 0; i < title.length; i++) {
//         data += "<li>title : " + title[i].innerHTML + "</li>";
//         data += "<li>time : " + time[i].innerHTML + "</li>";
//         data += "<li>teacher : " + teacher[i].innerHTML + "</li>";
//       }
//       data += "</ul>";

//       contents.innerHTML = data;
//     } else {
//       contents.innerHTML = "가져온 데이터 없음";
//     }
//   }
// }
