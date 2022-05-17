$(function () {
  $("#get").click(function () {
    // let userid = $("#userid").val();
    // let age = $("#age").val();

    let formData = $("form").serialize();

    $.get({
      url: "sendForm.jsp",
      data:formData,
      // data: {
      //   userid: userid,
      //   age: age,
      // },
      success: function (data) {
        alert(data);
      },
    })
  })
  $("#post").click(function () {
    // let userid = $("#userid").val();
    // let age = $("#age").val();

    let formData = $("form").serialize();

    $.post({
      url: "sendForm.jsp",
      data:formData,
      // data: {
      //   userid: userid,
      //   age: age,
      // },
      success: function (data) {
        alert(data);
      },
  })
})
})
