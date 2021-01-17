package model.Biz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import static common.JDBCTemplate.*;

import model.Dao.AdminRecipeDaoImpl;
import model.Dao.UserRecipeDaoImpl;
import model.Dto.AdminRecipeAnswerDto;
import model.Dto.AdminRecipeDto;
import model.Dto.UserInfoAnswerDto;
import model.Dto.UserRecipeDto;

public class AdminRecipeBiz {
	private AdminRecipeDaoImpl dao = new AdminRecipeDaoImpl();
	
	public List<AdminRecipeDto> selectAll() {
		Connection con = getConnection();
		List<AdminRecipeDto> res = dao.selectAll(con);
		close(con);

		return res;
	}
	
	
	
	public boolean insert(AdminRecipeDto dto) {
		Connection con = getConnection();
		int res = dao.insertRecipe(con, dto);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}


	public AdminRecipeDto selectOne(int recipe_No) {
		Connection con = getConnection();
		AdminRecipeDto res = dao.selectOne(con, recipe_No);
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


	public boolean update(AdminRecipeDto dto) {
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
	
	public boolean insertAnswer(AdminRecipeAnswerDto dto) {
		Connection con = getConnection();
		int res = dao.insertAnswer(con, dto);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}


	public List<AdminRecipeAnswerDto> answerlist(int recipe_No) {
		Connection con = getConnection();
		List<AdminRecipeAnswerDto> res = dao.answerlist(con,recipe_No);
		
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


	public AdminRecipeAnswerDto answerSelectOne(int recipe_No, int answer_No) {
		Connection con = getConnection();
		AdminRecipeAnswerDto res = dao.answerSelectOne(con,recipe_No,answer_No);
		close(con);
		return res;
	}


	public boolean updateAnswer(AdminRecipeAnswerDto dto) {
		Connection con = getConnection();
		int res = dao.updateAnswer(con,dto);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return (res>0)?true:false;
	}


	public boolean saveRecipe(AdminRecipeDto dto) {
		Connection con = getConnection();
		int res = dao.saveRecipe(con,dto);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		
		return (res>0)?true:false;
	}
	
	public List<AdminRecipeDto> admin_bestRecipe() {
		Connection con = getConnection();
		List<AdminRecipeDto> res = dao.admin_bestRecipe(con);
		
		close(con);
		
		return res;
	}


}
