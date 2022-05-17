package board.service;

import java.sql.Connection;
import java.util.List;

import board.domain.BoardDTO;
import board.domain.PageDTO;
import board.persistence.BoardDAO;

import static board.persistence.JdbcUtil.*;

public class BoardListService {
	public List<BoardDTO> all(PageDTO pageDto) {
		
		//static인 것들은 객체생성을 하지 않고, 이런식으로 어디에서 가져왔는지 명시만 하면됨
		//Connection con = JdbcUtil.getConnection();
		//모든 메소드가 static으로 생성된 경우, 클래스를 static으로 끌어올릴 수 있으며,
		//끌어올리면, 어디에 있는걸 불러왔는지 생략할 수 있음
		Connection con = getConnection();
		BoardDAO dao = new BoardDAO(con);

		List<BoardDTO> list = dao.getList(pageDto);

		close(con);
		return list;
	}
}
