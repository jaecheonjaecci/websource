/**
 * 
 */
 		function gethtml() {
		//페이지가 로드되자마자 서버가 가지고 있는 html 페이지를 가져와 div안에 보여주기 
		//=> 비동기식 방법으로 처리하기
		
		//xmlhttprequest 객체 생성
		let httpRequest = new XMLHttpRequest();
		
		//생성된 객체를 통해 서버에게 보낼 요청 설정
		httpRequest.open("get","/data/data.html");
		
		//서버로 전송-get 방식일때는 null 또는 비워두기 / post는 전송할 데이터 포함
		httpRequest.send(null);
		
		//서버 응답 : 200(성공), 400(404), 500(서버에러)
		//httpRequest.onreadystatechange : 서버의 응답 이벤트 감지
		httpRequest.onreadystatechange = function(){
			if(httpRequest.readyState == 4){
				if(httpRequest.status == 200){
					let div = document.querySelector("div:last-of-type");
					div.innerHTML = httpRequest.responseText;
					}
				}
			}
		
		
		}