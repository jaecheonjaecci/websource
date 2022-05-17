package member.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import member.domain.MemberDTO;
import member.domain.UpdateDTO;

import static member.persistence.JdbcUtil.*;

public class MemberDAO {

	private Connection con;

	public MemberDAO(Connection con) {
		this.con = con;
	}

	// login => select
	// 사용자가 입력한 아이디와 비밀번호를 디비에 있는지 확인하는 작업이라 select문을 사용

	public MemberDTO isLogin(String userid, String password) {
		MemberDTO loginDto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select userid,name from member where userid=? and password=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginDto = new MemberDTO();
				loginDto.setUserid(rs.getString(1));
				loginDto.setName(rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);

		}
		return loginDto;
	}

	public boolean delete(String userid, String password) {
		boolean deleteFlag = false;
		PreparedStatement pstmt = null;

		try {
			String sql = "delete from member where userid=? and password=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, password);

			int result = pstmt.executeUpdate();

			if (result > 0)
				deleteFlag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteFlag;
	}

	// 비밀번호 변경
	public boolean update(UpdateDTO updateDto) {

		boolean updateFlag = false;
		PreparedStatement pstmt = null;

		try {
			String sql = "update member set password=? where userid =? and password=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, updateDto.getNewPassword());
			pstmt.setString(2, updateDto.getUserid());
			pstmt.setString(3, updateDto.getCurrentPassword());

			int result = pstmt.executeUpdate();

			if (result > 0)
				updateFlag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return updateFlag;
	}

	// 회원가입
	public boolean insert(MemberDTO dto) {
		boolean registerFlag = false;
		PreparedStatement pstmt = null;

		try {
			String sql = "insert into member values(?,?,?,?,?)";
			

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getEmail());

			int result = pstmt.executeUpdate();

			if (result > 0)
				registerFlag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}
		return registerFlag;
	}

	//아이디 중복 검사
	public boolean idCheck(String userid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean dupFlag = true;
		
		try {
			//아이디가 존재한다면 사용할 수 없다는 뜻
			String sql="select * from member where userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs = pstmt.executeQuery();
			
			//rs => sql의 결과가 테이블 형태로 담기는 객체
			//rs에 하나라도 들어있다면 true => 아이디 사용이 불가하다는 뜻 false
			if(rs.next()) {
				dupFlag = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			
		}
		return dupFlag;
	}
}


















