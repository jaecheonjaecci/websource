//조인 폼 회원가입 유효성 검증
$(function(){
	//submit을 누르면 호출됨
    $("#joinform").validate({
        rules:{
            userid:{
                required:true,
				validId:true
            },
            password:{
                required:true,
				validPwd:true
            },
            confirm_password:{
                required:true,
				validPwd:true,
                equalTo:"#password"
            },
            name:{
                required:true
            },
            gender:{
                required:true
            },
            email:{
                required:true,
				email:true
            }

        },
        messages:{
			userid:{
                required:"아이디는 필수 입력 요소입니다."
            },
            password:{
                required:"비밀번호는 필수 입력 요소입니다."
            },
            confirm_password:{
                required:"비밀번호는 필수 입력 요소입니다.",
                equalTo:"이전 비밀번호와 다릅니다."
            },
            name:{
                required:"이름은 필수 입력 요소입니다."
            },
            gender:{
                required:"성별은 필수 입력 요소입니다."
            },
            email:{
                required:"이메일은 필수 입력 요소입니다.",
				email:"이메일 주소를 확인해주세요."
            }
        },
		errorPlacement:function(error,element){
		//경고문구 위치와 색상 지정 : 파란색은 기본 색상임
			$(element).closest("form").find("small[id='"+element.attr("id")+"']")
						.append(error);
		}
    })
})

//사이트 규칙 추가, 이메일은 validation이 가지고 있는 규칙을 그대로 사용할 수도 있고,
//email이라는 이름으로 새로 규칙을 제공할 수도 있음 -> 아래칸에 작성
$.validator.addMethod("validId",function(value){
	 let regId = /^(?=.*[A-Za-z])(?=.*[\d])[A-Za-z\d]{6,12}$/;
    return regId.test(value); 
	//결과가 true or false로 넘어옴
}, "아이디는 영대소문자, 숫자의 조합으로 6~12자리로 만들어야 합니다.")

$.validator.addMethod("validPwd",function(value){
  	let regPWd = /^(?=.*[A-Za-z])(?=.*[\d])(?=.*[!@$*])[A-Za-z\d!@$*]{8,15}$/;
  	return regPWd.test(value);
}, "비밀번호는 영대소문자, 숫자,특수문자의 조합으로 8~15자리로 만들어야 합니다.")










