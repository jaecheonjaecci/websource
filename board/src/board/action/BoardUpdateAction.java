package board.action;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import board.domain.BoardDTO;
import board.domain.PageDTO;
import board.domain.SearchDTO;
import board.service.BoardUpdateService;
import board.util.UploadUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardUpdateAction implements BoardAction {

	private String path;

	@Override
	public BoardActionForward execute(HttpServletRequest request) throws Exception {

		// 해당 request를 uploadutil에 넘기기
		UploadUtil uploadUtil = new UploadUtil();
		Map<String, String> map = uploadUtil.requestParse(request);

		// map에서 넘긴 값 가져오기
		BoardDTO insertDto = new BoardDTO();
		insertDto.setBno(Integer.parseInt(map.get("bno")));
		insertDto.setName(map.get("name"));
		insertDto.setTitle(map.get("title"));
		insertDto.setContent(map.get("content"));
		insertDto.setPassword(map.get("password"));
		insertDto.setAttach(map.get("attach"));

		// 페이지 나누기 후 추가로 받음
		String page = map.get("page");
		String amount = map.get("amount");
		String criteria = map.get("criteria");
		String keyword = URLEncoder.encode(map.get("keyword"),"utf-8");
		//------------------------------------------------
		
		BoardUpdateService service = new BoardUpdateService();
		boolean updateFlag = service.update(insertDto);

		if (updateFlag) {
			// path = "/read.do"에 붙이기 위함
			path += "?page="+page+"&amount="+amount+"&criteria="+criteria
					+"&keyword="+keyword+"&bno=" + insertDto.getBno();
		} else {
			path = "/modify.do?page="+page+"&amount="+amount+"&criteria="+criteria 
							+"&keyword="+keyword+"&bno="+ insertDto.getBno();
		}

		return new BoardActionForward(path, true);
	}

}
