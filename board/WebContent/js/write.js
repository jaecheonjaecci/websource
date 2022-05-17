/**
 * qna_board_write.jsp와 관련된 js
 */

 $(function(){
    $("#list").click(function(){
        $("#actionForm").attr("action","/list.do");
        $("#actionForm").submit();
    })
})