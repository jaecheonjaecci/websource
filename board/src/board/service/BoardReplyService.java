package board.service;

import static board.persistence.JdbcUtil.*;

import java.sql.Connection;

import board.domain.BoardDTO;
import board.persistence.BoardDAO;

public class BoardReplyService {
	public boolean reply(BoardDTO dto) {
		Connection con = getConnection();
		BoardDAO dao = new BoardDAO(con);

		// replyUpdate, 댓글이 없는 경우 이 작업이 일어나지 않기 때문에 무조건 일어나지는 않음
		// 그래서 실행이 되었는지 안되었는지 받아올 필요가 없음
		dao.replyUpdate(dto);
		commit(con);
		
		// insertUpdate, 이 작업은 반드시 일어나기 때문에 값을 받아와야 함
		boolean insertFlag = dao.replyInsert(dto);

		if (insertFlag) {
			commit(con);
		
		} else {
			rollback(con);
		}
		close(con);
		return insertFlag;
	}
}
