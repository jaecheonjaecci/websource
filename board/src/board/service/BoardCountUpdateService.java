package board.service;

import java.sql.Connection;

import board.persistence.BoardDAO;

import static board.persistence.JdbcUtil.*;
public class BoardCountUpdateService {
	public boolean readUpdate(int bno) {
		Connection con = getConnection();
		BoardDAO dao = new BoardDAO(con);
		
		boolean updateFlag = dao.readCountUpdate(bno);
		
		if(updateFlag) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return updateFlag;
	}
}
