package member.action;

import javax.servlet.http.HttpServletRequest;

//Action 클래스가 동일한 형태를 갖추기 위해서 사용하는 인터페이스

public interface Action {
	public ActionForward execute(HttpServletRequest request) throws Exception;
}
