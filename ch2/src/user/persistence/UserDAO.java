package user.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.domain.UserDTO;

public class UserDAO {

	// 드라이버 로드
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 커넥션 생성하기
	public Connection getConnection() {

		// datebase 서버 주소, port 정보
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##java";
		String password = "12345";

		Connection con;
		try {
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	// login(select 구문 사용), 로그인 성공 시 userid,name 리턴
	public UserDTO isLogin(String userid, String password) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO dto = null;

		try {
			con = getConnection();

			String sql = "select userid,name from member where userid=? and password=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, password);
			// java.sql.SQLException: 인덱스에서 누락된 IN 또는 OUT 매개변수:: 2
			// dao ? 해당하는 코드 확인

			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new UserDTO();
				dto.setUserid(rs.getString("userid"));
				dto.setName(rs.getString("name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto;

	}
}
