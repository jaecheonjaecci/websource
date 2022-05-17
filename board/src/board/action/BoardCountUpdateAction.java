package board.action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import board.service.BoardCountUpdateService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardCountUpdateAction implements BoardAction {

	private String path;

	@Override
	public BoardActionForward execute(HttpServletRequest request) throws Exception {
		// list.do?bno=1 bno 값 가져오기
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		//페이지 나누기 후 추가로 받음
		String page = request.getParameter("page");
		String amount = request.getParameter("amount");
		String criteria = request.getParameter("criteria");
		
		//주소줄로 값을 넘길때 한글이 깨져서 인코딩 작업 실행
		String keyword = URLEncoder.encode(request.getParameter("keyword"), "utf-8");
		
		
		// 조회수 변경하기
		BoardCountUpdateService updateService = new BoardCountUpdateService();
		updateService.readUpdate(bno);
		
		//path +="?bno="+bno;
		//페이지 나누기 후, 필요한 정보들 주소줄에 딸려 보내기
		path +="?page="+page+"&amount="+amount+"&criteria="+criteria+"&keyword="+keyword+"&bno="+bno;
		
		return new BoardActionForward(path,true);
	}

}
