//getjson.html에서 메뉴 4 클릭시 동작 : 여러개의 데이터가 넘어오는 경우 이 방법을 사용

//메뉴4와 이벤트 연결
//메뉴 4번 찾아오기 -> 찾아온 코드에 이벤트 부착(클릭이 일어나면 makeRequest 불러오기)
$(function(){
  $(".container div:last-child").click(function(){
    $.getJSON({
      url:"/data/dataArray.json",
      success:function(data){
        let resText = "<ul>";
        $(data).each(function(idx,item){
          resText +="<li>version : "+item.version+"</li>";
          resText += "<li>codename : "+item.codename+"</li>";
        })
        resText+="</ul>";

        $("#contents").html(resText);
        },
      error:function(xhr,textStatus,error){
        $("#contents").html("데이터없음");

      }
    })
  })
})


// let last_div = document.querySelector(".container div:last-child")
//                         .addEventListener('click',makeRequest);


// let xhr = new XMLHttpRequest();

// function makeRequest(){
//     xhr.onreadystatechange = getJson;
//     xhr.open("get","/data/dataArray.json");
//     xhr.send();
// }

// //서버가 응답하는 경우 호출되는 함수
// function getJson(){

//     //서버가 보내준 데이터를 contents영역에 보여주기
//     let contents = document.querySelector("#contents");

//     if(xhr.readyState == 4){
//         if(xhr.status == 200){

//             //json 데이터를 자바스크립트 객체로 파싱
//             let response = JSON.parse(xhr.responseText);

//             console.log(response);
			
// 			//li만 반복문에 속하기 때문에 ul은 반복문 밖에서 뺌
//             let resText = "<ul>";
            
//             response.forEach((item)=>{
//                 resText +="<li>version : "+item.version+"</li>";
//                 resText += "<li>codename : "+item.codename+"</li>";
//             })

//             contents.innerHTML = resText+"</ul>";
//         }else{
//             contents.innerHTML = "가져온 데이터 없음";
//         }
//     }
// }