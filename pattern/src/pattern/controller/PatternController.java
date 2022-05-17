package pattern.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pattern.action.Action;
import pattern.action.ActionForward;

@WebServlet("*.do")
public class PatternController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//모든 요청이 여기를 거쳐가니 인코딩을 여기에 한번만 작성하면 됨
		request.setCharacterEncoding("utf-8");
		
		//어디서 요청이 왔는지 찾기
		//여러곳에서 요청이 들어오기 때문에 어디에서 왔는지 확인 작업 필요(주소줄을 분석하면 됨)
		// : 어디에서 왔는지에 따라 하는 일이 달라짐
		
		// request.getRequestURI(); => 주소를 가져오는 request임
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String cmd = requestUri.substring(contextPath.length());
		
		//사용자의 요청에 따라서 해당 액션을 담당하는 클래스를 찾음 
		//클래스를 찾아서 액션을 생성
		
		//=>싱글톤 방식이라 객체를 하나만 생성 가능함 
		//=> ActionFactory.getInstance(); = new ActionFactory
		ActionFactory af = ActionFactory.getInstance();
		
		//생성된 액션을 담음
		Action action  = af.action(cmd);
		
		
		
		ActionForward actionForward = null;
		
		try {
			//생성된 액션에게 일을 시킴 => 일이 끝난 후 결과를 액션포워드 객체로 넘겨 받음
			actionForward = action.execute(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//로그인 실패 new ActionForward(/view/login.jsp,true);
		//로그인 성공 new ActionForward(/index.jsp,true);
		
		
		
		//넘겨받은 액션포워드 값에 따라서 페이지를 이동
		//true인지 false인지 확인 후 이동 방식을 지정함
		if(actionForward.isRedirect()) {
			//true = sendredirect로 움직이는 경우 아래처럼 코드를 작성해야함
			response.sendRedirect(actionForward.getPath());
		}else {
			//fales = forward로 움직이는 경우 아래처럼 코드를 작성해야함
			RequestDispatcher rd = request.getRequestDispatcher(actionForward.getPath());
			rd.forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

	
}
