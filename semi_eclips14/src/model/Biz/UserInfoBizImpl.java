package model.Biz;

import java.sql.Connection;
import java.util.List;


import model.Dao.UserInfoDao;
import model.Dao.UserInfoDaoImpl;
import model.Dto.AdminInfoAnswerDto;
import model.Dto.UserInfoAnswerDto;
import model.Dto.UserInfoDto;
import model.Dto.UserRecipeAnswerDto;

import static common.JDBCTemplate.*;

public class UserInfoBizImpl implements UserInfoBiz{
	private UserInfoDaoImpl dao = new UserInfoDaoImpl();


	
	@Override
	public Object selectAll(int startRow, int endRow) {
		Connection con = getConnection();
		Object res = dao.selectAll(con, startRow, endRow);
		close(con);
		
 		return res;
	}

	@Override
	public UserInfoDto selectOne(int info_No) {
		Connection con = getConnection();
		UserInfoDto res = dao.selectOne(con, info_No);
		close(con);
		return res;
	}
	@Override
	public boolean insertInfo(UserInfoDto dto) {
		Connection con = getConnection();
		int res = dao.insertInfo(con,dto);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}

	@Override
	public boolean deleteInfo(int info_No) {
		Connection con = getConnection();
		int res = dao.deleteInfo(con, info_No);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}


	

	@Override
	public boolean updateInfo(UserInfoDto dto) {
		Connection con = getConnection();
		int res = dao.updateInfo(con, dto);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		
		return (res>0)?true:false;
	}


	public boolean saveInfo(UserInfoDto dto) {
		Connection con = getConnection();
		int res = dao.saveInfo(con, dto);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}



	public boolean addHits(int info_No) {
		Connection con = getConnection();
		int res = dao.addHits(con,info_No);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		return (res>0)?true:false;
	}

	public List<UserInfoAnswerDto> answerlist(int info_No) {
		Connection con = getConnection();
		List<UserInfoAnswerDto> res = dao.answerlist(con,info_No);
		
		close(con);		
		return res;
	}

	@Override
	public List<UserInfoDto> user_bestInfo() {
		Connection con = getConnection();
		List<UserInfoDto> res = dao.user_bestInfo(con);
		
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
		Connection con = getConnection();
		Object res =dao.service(con, obj);
		close(con);
		return res; 
	}

	@Override
	public int insertAnswer(UserInfoAnswerDto dto) {
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


	public UserInfoAnswerDto answerSelectOne(int info_No, int answer_No) {
		Connection con = getConnection();
		UserInfoAnswerDto res = dao.answerSelectOne(con,info_No,answer_No);
		close(con);
		return res;
	}


	public boolean updateAnswer(UserInfoAnswerDto dto) {
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
