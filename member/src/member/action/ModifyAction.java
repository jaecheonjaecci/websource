package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;
import member.domain.MemberDTO;
import member.domain.UpdateDTO;
import member.service.ModifyService;

@AllArgsConstructor
public class ModifyAction implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request) throws Exception {
		// userid 가져오기 - 로그인 후 움직였기 때문에 session에 담겨있음
		HttpSession session = request.getSession();
		MemberDTO loginDto = (MemberDTO) session.getAttribute("loginDto");
		String userid = loginDto.getUserid();

		// modifyForm.jsp 에서 사용자 입력값 가져오기
		String current_password = request.getParameter("current_password");
		String new_password = request.getParameter("new_password");
		String confirm_password = request.getParameter("confirm_password");

		UpdateDTO updateDto = new UpdateDTO(userid, current_password, new_password, confirm_password);

		// front에서 비밀번호와 비밀번호 확인이 같은 지 확인을 했다해도, 한번 더 back에서 유효성 검증을 하는 작업
		if (updateDto.passwordEqualTo(new_password)) {

			// 비밀번호가 같다면 디비작업
			ModifyService service = new ModifyService();
			boolean updateFlag = service.modify(updateDto);

			// 성공한다면 커밋, 현재 세션 해제, 로그인페이지로 이동
			if (updateFlag) {
				session.invalidate();
//				path="/view/loginForm.jsp";
			} else {
				// 실패한다면 롤백, 비밀번호 변경 페이지로 이동
				path = "/view/modifyForm.jsp";
			}

		} else {
			// 비밀번호가 서로 다르다면 비밀번호 변경 페이지로 이동
			path = "/view/modifyForm.jsp";
		}

		return new ActionForward(path, true);
	}

}
