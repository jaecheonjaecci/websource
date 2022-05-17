package board.service;

import board.domain.BoardDTO;
import board.persistence.BoardDAO;

import static board.persistence.JdbcUtil.*;

import java.sql.Connection;

//비지니스 로직 처리 : db에 있는 특정 메소드를 실행시킴
public class BoardInsertService {
	public boolean boardInsert(BoardDTO insertDto) {
		Connection con = getConnection();
		BoardDAO dao = new BoardDAO(con);
		
		boolean insertFlag = dao.insert(insertDto);
		
		if(insertFlag) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return insertFlag;
	}
}
