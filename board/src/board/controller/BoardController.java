package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.BoardAction;
import board.action.BoardActionFactory;
import board.action.BoardActionForward;


@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//가져오는 request에 대한 인코딩 설정 - 실제로는 post 방식의 경우만 필요함
		request.setCharacterEncoding("utf-8");
		
		//어디서 들어오는 요청인지 알기위해 주소줄을 분리함
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		//cmd에는 /insert.do 같이 필요한 주소만 남음
		String cmd = requestUri.substring(contextPath.length());
		
		//cmd에 따라 action을 생성하기
		//BoardActionFactory에 일을 시키기 위해 싱글톤 방법으로 객체를 생성
		BoardActionFactory baf = BoardActionFactory.getInstance();
		BoardAction action = baf.action(cmd);
		
		//생성된 action에게 일 시키기
		BoardActionForward af = null;
		try {
			af = action.execute(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//리턴받은 af에 따라 이동
		if(af.isRedirect()) { //true라면 sendRedirect 방식으로 보내기
							 	//경로는 af에 담겨있음
			response.sendRedirect(af.getPath());
		}else { //false라면 forward 방식으로 보내기
			RequestDispatcher rd = request.getRequestDispatcher(af.getPath());
			rd.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
