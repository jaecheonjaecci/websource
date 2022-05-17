package pattern.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pattern.domain.MemberDTO;
import pattern.service.LoginService;

@AllArgsConstructor
@Getter
public class LoginAction implements Action {
	
	private String path; //index.jsp가 담겨 있음
	

	@Override
	public ActionForward execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
				
		//getParemeter작업 하는 곳
//		String no = request.getParameter("no");
//		System.out.println("no "+no);
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		
		//서비스 작업
		LoginService service = new LoginService();
		MemberDTO loginDto = service.loginService(userid, password);
		
		//결과에 따라 액션포워드 객체 생성, path에 이동할 경로가 담김
		if(loginDto ==null) { //로그인 실패
			path="/view/login.jsp"; //로그인 폼 보여주기 
		}else { //로그인 성공 => 세션의 값 담기
			HttpSession session = request.getSession();
			session.setAttribute("loginDto", loginDto);
			
		}
		
		//로그인 실패 new ActionForward(/view/login.jsp,true);
		//로그인 성공 new ActionForward(/index.jsp,true);
		
		
		return new ActionForward(path,true);
		//false니까 forward 방식으로 insert.jsp로 이동함

		
	}

}


























