package board.action;

import javax.servlet.http.HttpServletRequest;

public interface BoardAction {
	
	//실행을 하다가 exception이 나면 던지겠다는 의미 -> controller가 받음
	public BoardActionForward execute(HttpServletRequest request) throws Exception;
}
