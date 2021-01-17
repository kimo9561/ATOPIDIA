package model.Biz;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.Dao.MemberDao;
import model.Dao.MemberDaoImpl;
import model.Dto.MemberDto;

public class MemberBiz {
	MemberDaoImpl dao = new MemberDaoImpl();

	// 회원 전체 조회
	public List<MemberDto> selectAll() {
		Connection con = getConnection();
		List<MemberDto> res = dao.selectAll(con);
		close(con);
		return res;
	}

	public MemberDto selectOne(int Member_No) {
		Connection con = getConnection();
		MemberDto res = dao.selectOne(con, Member_No);
		close(con);
		return res;
	}

	// 회원가입
	public boolean InsertUser(MemberDto dto) {
		Connection con = getConnection();
		int res = dao.InsertUser(con, dto);

		if (res > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);
		return (res > 0) ? true : false;
	}

	// 로그인
	public MemberDto Login(String member_Id, String member_Pw) {
		Connection con = getConnection();
		MemberDto dto = dao.Login(con, member_Id, member_Pw);
		close(con);
		return dto;
	}

	public boolean IdChk(String member_Id) {
		Connection con = getConnection();
		System.out.println("Biz " + member_Id);
		String rs = dao.IdChk(con, member_Id);
		boolean res = true;
		if (rs != null) {
			res = false;
		}
		close(con);
		return res;

	}

	// 정보 수정
	public boolean UpdateUser(MemberDto dto) {
		Connection con = getConnection();
		int res = dao.UpdateUser(con, dto);
		if (res > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return (res > 0) ? true : false;
	}

	// 회원탈퇴
	public boolean DeleteUser(int memberno) {
		Connection con = getConnection();
		int res = dao.DeleteUser(con, memberno);
		if (res > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		return (res > 0) ? true : false;
	}

	public boolean multiDelete(String[] num) {
		Connection con = getConnection();
		boolean res = dao.multiDelete(con, num);
		if (res) {
			commit(con);
		} else {
			rollback(con);
		}
		return res;
	}

	public MemberDto SearchId(String member_Name, String member_Email) {
		Connection con = getConnection();

		MemberDto rs = dao.SearchId(con, member_Name, member_Email);

		close(con);
		return rs;

	}

	public MemberDto searchPw(String id, String rrn) {
	      Connection con = getConnection();
	      
	      MemberDto res = dao.searchPw(con, id, rrn);
	      
	      close(con);
	      
	      return res;
	   }

	public String selectEmail(String member_Email) {
		Connection con = getConnection();
		String res = dao.SelectEmail(con, member_Email);

		return res;
	}

}