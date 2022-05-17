package member.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class JdbcUtil {

	// connection
	   public static Connection getConnection() {
	      try {
	         Context ctx = new InitialContext();
	         // java:comp/env : 이름으로 찾을 때 사용하는 공간의 이름
	         DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");
	         Connection con = ds.getConnection();  
			
			//자바 응용프로그램인 autocommit 상태
			//autocommit을 false로 바꿔주는 부분
			con.setAutoCommit(false);
			return con;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	//autocommit을 막았기 때문에 필요한 작업
	//commit
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//rollback
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	// 자원 해제
	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
