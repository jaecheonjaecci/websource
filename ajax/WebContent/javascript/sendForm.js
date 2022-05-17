//바디가 로드되면
window.onload = function () {
  document.getElementById("get").onclick = function () {
    //보낼 데이터 가져오기
    let userid = document.getElementById("userid").value;
    let age = document.getElementById("age").value;

    //데이터 전송하기(비동기식)
    let xhr = new XMLHttpRequest();
    xhr.open("get", "sendForm.jsp?userid=" + userid + "&age=" + age);
    xhr.send();
  };
  document.getElementById("post").onclick = function () {
    //보낼 데이터 가져오기
    let userid = document.getElementById("userid").value;
    let age = document.getElementById("age").value;

    //데이터 전송하기(비동기식)
    let xhr = new XMLHttpRequest();
    xhr.open("post", "sendForm.jsp");
    
    //데이터 전송시 기본값, 이 방식을 사용함
    xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xhr.send("userid=" + userid + "&age=" + age);
  };
};
