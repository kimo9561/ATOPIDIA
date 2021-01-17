package model.Biz;

import java.sql.Connection;
import java.util.List;

import model.Dao.AdminInfoDaoImpl;
import model.Dto.AdminInfoAnswerDto;
import model.Dto.AdminInfoDto;
import model.Dto.AdminRecipeAnswerDto;
import model.Dto.UserInfoAnswerDto;

import static common.JDBCTemplate.*;

public class AdminInfoBizImpl implements AdminInfoBiz {
	private AdminInfoDaoImpl dao = new AdminInfoDaoImpl();

	@Override
	public Object selectAll(int startRow, int endRow) {
		Connection con = getConnection();
		Object res = dao.selectAll(con, startRow, endRow);
		close(con);

		return res;
	}

	@Override
	public AdminInfoDto selectOne(int info_No) {
		Connection con = getConnection();
		AdminInfoDto res = dao.selectOne(con, info_No);
		close(con);
		return res;
	}

	@Override
	public boolean insertInfo(AdminInfoDto dto) {
		Connection con = getConnection();
		int res = dao.insertInfo(con, dto);

		if (res > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		return (res > 0) ? true : false;
	}

	@Override
	public boolean deleteInfo(int info_No) {
		Connection con = getConnection();
		int res = dao.deleteInfo(con, info_No);
		if (res > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		return (res > 0) ? true : false;
	}

	@Override
	public boolean updateInfo(AdminInfoDto dto) {
		Connection con = getConnection();
		int res = dao.updateInfo(con, dto);
		if (res > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		return (res > 0) ? true : false;
	}

	public boolean saveInfo(AdminInfoDto dto) {
		Connection con = getConnection();
		int res = dao.saveInfo(con, dto);
		if (res > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		return (res > 0) ? true : false;
	}

	public List<AdminInfoAnswerDto> answerlist(int info_No) {
		Connection con = getConnection();
		List<AdminInfoAnswerDto> res = dao.answerlist(con, info_No);

		close(con);
		return res;
	}

	public boolean addHits(int info_No) {
		Connection con = getConnection();
		int res = dao.addHits(con, info_No);
		if (res > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		return (res > 0) ? true : false;
	}

	@Override
	public List<AdminInfoDto> admin_bestInfo() {
		Connection con = getConnection();
		List<AdminInfoDto> res = dao.admin_bestInfo(con);

		close(con);

		return res;
	}

	@Override
	public int getBoardCount() {
		Connection con = getConnection();
		int res = dao.getBoardCount(con);
		close(con);
		return res;
	}

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		Object res = dao.service(con, obj);
		close(con);
		return res;
	}
	
	@Override
	public int insertAnswer(AdminInfoAnswerDto dto) {
		Connection con = getConnection();
		int res = dao.insertAnswer(con, dto);
		
		if(res>0) {
			commit(con);
		}
		close(con);
		return res;
	}
	
	public boolean deleteAnswer(int info_No, int answer_No) {
		Connection con = getConnection();
		int res = dao.deleteAnswer(con,info_No,answer_No);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}


	public AdminInfoAnswerDto answerSelectOne(int info_No, int answer_No) {
		Connection con = getConnection();
		AdminInfoAnswerDto res = dao.answerSelectOne(con,info_No,answer_No);
		close(con);
		return res;
	}


	public boolean updateAnswer(AdminInfoAnswerDto dto) {
		Connection con = getConnection();
		int res = dao.updateAnswer(con,dto);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}
}
