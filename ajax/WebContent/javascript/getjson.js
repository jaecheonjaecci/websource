//getjson.html에서 메뉴 4 클릭시 동작 : 가져오는 데이터가 하나인 경우 이 방법을 이용

//메뉴4와 이벤트 연결
//메뉴 4번 찾아오기 -> 찾아온 코드에 이벤트 부착(클릭이 일어나면 makeRequest 불러오기)
let last_div = document.querySelector(".container div:last-child")
                        .addEventListener('click',makeRequest);


let xhr = new XMLHttpRequest();

function makeRequest(){
    xhr.onreadystatechange = getJson;
    xhr.open("get","/data/data.json");
    xhr.send();
}

//서버가 응답하는 경우 호출되는 함수
function getJson(){

    //서버가 보내준 데이터를 contents영역에 보여주기
    let contents = document.querySelector("#contents");

    if(xhr.readyState == 4){
        if(xhr.status == 200){
        
            //json 데이터를 자바스크립트 객체로 파싱
            let response = JSON.parse(xhr.responseText);

            let resText = "<ul><li>version : "+response.version+"</li>";
            resText += "<li>codename : "+response.codename+"</li></ul>";

            contents.innerHTML = resText;
        }else{
            contents.innerHTML = "가져온 데이터 없음";
        }
    }
}