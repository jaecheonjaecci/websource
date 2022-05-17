//dataset.html - 서버에서 dataset.xml 데이터 요청 후 body에 보여주기
$(function(){
  $.get({
    url:"/data/dataset.xml",
    success: function(data){
      
      let output="<table><tr><th>id</th><th>name</th><th>email</th><th>gender</th></tr>";

      $(data).find("record").each(function(){
        output +="<tr><td>"+$(this).find("id").text()+"</td>";
        output +="<td>"+$(this).find("name").text()+"</td>";
        output +="<td>"+$(this).find("email").text()+"</td>";
        output +="<td>"+$(this).find("gender").text()+"</td></tr>";
      })

      output += "</table>";
      $("body").append(output);
    },
    error:function(xhr,textStatus,error){
      $("body").append("데이터없음");
    }
  })
})