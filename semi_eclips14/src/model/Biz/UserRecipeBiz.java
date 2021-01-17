package model.Biz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import static common.JDBCTemplate.*;

import model.Dao.UserRecipeDaoImpl;
import model.Dto.UserInfoAnswerDto;
import model.Dto.UserRecipeAnswerDto;
import model.Dto.UserRecipeDto;

public class UserRecipeBiz {
	private UserRecipeDaoImpl dao = new UserRecipeDaoImpl();
	
	
	public List<UserRecipeDto> selectAll() {
		Connection con = getConnection();
		List<UserRecipeDto> res = dao.selectAll(con);
		close(con);
		
		
		
		return res;
	}
	
	
	public boolean insert(UserRecipeDto dto) {
		Connection con = getConnection();
		int res = dao.insertRecipe(con, dto);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}


	public UserRecipeDto selectOne(int recipe_No) {
		Connection con = getConnection();
		UserRecipeDto res = dao.selectOne(con, recipe_No);
		close(con);
		return res;
	}


	public boolean delete(int recipe_No) {
		Connection con = getConnection();
		int res = dao.deleteRecipe(con, recipe_No);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		
		
		return (res>0)?true:false;
	}


	public boolean update(UserRecipeDto dto) {
		Connection con = getConnection();
		int res = dao.updateRecipe(con, dto);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}
	
	public boolean addHits(int recipe_No) {
		Connection con = getConnection();
		int res = dao.addHits(con, recipe_No);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}
	
	public boolean insertAnswer(UserRecipeAnswerDto dto) {
		Connection con = getConnection();
		int res = dao.insertAnswer(con, dto);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}


	public List<UserRecipeAnswerDto> answerlist(int recipe_No) {
		Connection con = getConnection();
		List<UserRecipeAnswerDto> res = dao.answerlist(con,recipe_No);
		
		close(con);
		return res;
		
	}


	public boolean deleteAnswer(int recipe_No, int answer_No) {
		Connection con = getConnection();
		int res = dao.deleteAnswer(con,recipe_No,answer_No);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}


	public UserInfoAnswerDto answerSelectOne(int recipe_No, int answer_No) {
		Connection con = getConnection();
		UserInfoAnswerDto res = dao.answerSelectOne(con,recipe_No,answer_No);
		close(con);
		return res;
	}


	public boolean updateAnswer(UserRecipeAnswerDto dto) {
		Connection con = getConnection();
		int res = dao.updateAnswer(con,dto);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}


	public boolean saveRecipe(UserRecipeDto dto) {
		Connection con = getConnection();
		int res = dao.saveInfo(con, dto);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	
	}

	
	public List<UserRecipeDto> user_bestRecipe() {
		Connection con = getConnection();
		List<UserRecipeDto> res = dao.user_bestRecipe(con);
		
		close(con);
		
		return res;
	}
}
