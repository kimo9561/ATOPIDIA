 package model.Dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.UserException;

import model.Dto.AdminRecipeDto;
import model.Dto.UserInfoAnswerDto;
import model.Dto.UserRecipeAnswerDto;
import model.Dto.UserRecipeDto;

import static common.JDBCTemplate.*;

public class UserRecipeDaoImpl implements UserRecipeDao{
	// 레시피 전체 선택
	public List<UserRecipeDto> selectAll(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<UserRecipeDto> res = new ArrayList<UserRecipeDto>();
		System.out.println("selectAll");
		
		try {
			pstm = con.prepareStatement(SelectAllSql);
			System.out.println("03.query 준비: "+SelectAllSql);
			
			rs = pstm.executeQuery(SelectAllSql);
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				UserRecipeDto tmp = new UserRecipeDto();
				tmp.setRecipe_No (rs.getInt(1));
				tmp.setMember_No (rs.getInt(2));
				tmp.setRecipe_Writer(rs.getString(3));
				tmp.setRecipe_Title(rs.getString(4));
				tmp.setRecipe_Ingredient(rs.getString(5));
				tmp.setRecipe_Text(rs.getString(6));
				tmp.setRecipe_Date(rs.getDate(7));
				tmp.setRecipe_Hits(rs.getInt(8));
				tmp.setRecipe_Save(rs.getString(9));
				tmp.setGrade_Code(rs.getInt(10));
				tmp.setRecipe_Image_Name(rs.getString(11));
				tmp.setRecipe_Image_Realname(rs.getString(12));
				tmp.setRecipe_Image_Uploadpath(rs.getString(13));
				
				
				res.add(tmp);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);

			System.out.println("05.db 종료\n");
		}
		
		
		return res;
	}
	
	// 선택한 레시피 데이터
		public UserRecipeDto selectOne(Connection con,int recipe_No) {
			PreparedStatement pstm = null;
			ResultSet rs = null;
			UserRecipeDto  res = null;
			System.out.println("selectOne");

			 try {
				pstm = con.prepareStatement(SelectOneSql);
				pstm.setInt(1, recipe_No);
				System.out.println("03.query 준비: " + SelectOneSql);
				
				rs = pstm.executeQuery();
				System.out.println("04. query 실행 및 리턴");
				
				while(rs.next()) {
					res = new UserRecipeDto (rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7), rs.getInt(8),rs.getNString(9),rs.getInt(10),rs.getString(11),rs.getString(12),rs.getString(13));
				}
					
							
			} catch (SQLException e) {
				System.out.println("3/4 단계 에러");
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				System.out.println("05. db종료 \n");
			}
				
			return res;
		}

	// 레시피 추가
	public int insertRecipe(Connection con,UserRecipeDto dto) {
			PreparedStatement pstm = null;
			int res = 0;
			System.out.println("insertRecipe");
		
			try {
				pstm = con.prepareStatement(InsertSql);
				pstm.setInt(1, dto.getMember_No());
				pstm.setString(2, dto.getRecipe_Writer());
				pstm.setString(3, dto.getRecipe_Title());
				pstm.setString(4, dto.getRecipe_Ingredient());
				pstm.setString(5, dto.getRecipe_Text());
				pstm.setString(6, dto.getRecipe_Image_Name());
				pstm.setString(7, dto.getRecipe_Image_Realname());
				pstm.setString(8, dto.getRecipe_Image_Uploadpath());
				
				System.out.println("03.query 준비: "+InsertSql);
				res = pstm.executeUpdate();
				System.out.println("04.query 실행 및 리턴");
				
				
			} catch (SQLException e) {
				System.out.println("3/4 단계 에러");
				e.printStackTrace();
			}finally {
				close(pstm);
				System.out.println("05.db 종료\n");
			}
			
			
			return res;
		
		}

	// 레시피 삭제
	public int deleteRecipe(Connection con,int recipe_No) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("deleteRecipe");
		

		try {
			pstm = con.prepareStatement(DeletSql);
			pstm.setInt(1, recipe_No);
			System.out.println("03.query 준비: "+DeletSql);
			
			res =pstm.executeUpdate();
			System.out.println("04.실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		
		}finally{
			close(pstm);
			System.out.println("05.db 종료\n");
		}
		
		return res;
	}
	
	// 레시피 수정 (글 수정 및 저장 여부 변경)
	public int updateRecipe(Connection con,UserRecipeDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("updateRecipe");
		
		
		try {
			pstm = con.prepareStatement(UpdateSql);
			pstm.setString(1, dto.getRecipe_Title());
			pstm.setString(2, dto.getRecipe_Ingredient());
			pstm.setString(3, dto.getRecipe_Text());
			pstm.setString(4, dto.getRecipe_Image_Name());
			pstm.setString(5, dto.getRecipe_Image_Realname()) ;
			pstm.setString(6, dto.getRecipe_Image_Uploadpath());
			pstm.setInt(7, dto.getRecipe_No());
			System.out.println("03.query 준비: "+UpdateSql);
			
			res = pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
		
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally{
			close(pstm);
			System.out.println("05.db 종료\n");
		}
		
		return res;
	}

	
	public int addHits(Connection con,int recipe_No) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("addHits");
		
		
		try {
			pstm = con.prepareStatement(addHits);
			pstm.setInt(1, recipe_No);
			System.out.println("03.query 준비: "+UpdateSql);
			
			res = pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
		
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally{
			close(pstm);
			System.out.println("05.db 종료\n");
		}
		
		return res;
	}

	public int insertAnswer(Connection con,UserRecipeAnswerDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("insertAnswer");
	
		
		try {
			pstm = con.prepareStatement(addAnswer);
			pstm.setInt(1, dto.getRecipe_No());
			pstm.setString(2, dto.getAnswer_Writer());
			pstm.setString(3, dto.getAnswer_Content());
			System.out.println("03.query 준비: "+addAnswer);
			
			res = pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
			
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05.db 종료\n");
		}
		
		
		return res;
	
	}

	public List<UserRecipeAnswerDto> answerlist(Connection con,int recipe_No) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<UserRecipeAnswerDto> res = new ArrayList<UserRecipeAnswerDto>();
		System.out.println("answerlist");

		 try {
			pstm = con.prepareStatement(answerList);
			pstm.setInt(1, recipe_No);
			System.out.println("03.query 준비: " + answerList);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				UserRecipeAnswerDto tmp = new UserRecipeAnswerDto();
				tmp.setRecipe_No(rs.getInt(1));
				tmp.setAnswer_No(rs.getInt(2));
				tmp.setAnswer_Writer(rs.getString(3)); 
				tmp.setAnswer_Content(rs.getString(4));
				tmp.setAnswer_Date(rs.getDate(5));
			
				res.add(tmp);
				
			}
			
						
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {;
			close(rs);
			close(pstm);
			System.out.println("05. db종료 \n");
		}
			
		return res;
	}

	public int deleteAnswer(Connection con, int recipe_No, int answer_No) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("deleteAnswer");
		
		try {
			pstm = con.prepareStatement(deleteAnswer);
			pstm.setInt(1, recipe_No);
			pstm.setInt(2, answer_No);
			System.out.println("03.query 준비: " + deleteAnswer);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
		
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05. 종료\n");
		}
		
		return res;
	}

	public UserInfoAnswerDto answerSelectOne(Connection con, int recipe_No, int answer_No) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UserInfoAnswerDto res = new UserInfoAnswerDto();
		System.out.println("answerSelectOne");
		
		try {
			pstm = con.prepareStatement(answerSelectOne);
			pstm.setInt(1, recipe_No);
			pstm.setInt(2, answer_No);
			System.out.println("03.query 준비: " + answerSelectOne);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				res.setInfo_No(rs.getInt(1));
				res.setAnswer_No(rs.getInt(2));
				res.setAnswer_Writer(rs.getString(3));
				res.setAnswer_Content(rs.getString(4));
				res.setAnswer_Date(rs.getDate(5));
			}
			
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}
		
		
		return res;
	}

	public int updateAnswer(Connection con, UserRecipeAnswerDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("updateAnswer");
		try {
			pstm = con.prepareStatement(updateAnswer);
			pstm.setString(1, dto.getAnswer_Writer());
			pstm.setString(2, dto.getAnswer_Content());
			pstm.setInt(3, dto.getRecipe_No());
			pstm.setInt(4, dto.getAnswer_No());
			System.out.println("03.query 준비: " + updateAnswer);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05.db 종료\n");
		}
		
		return res;
	}

	public int saveInfo(Connection con, UserRecipeDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(save);
			pstm.setInt(1, dto.getRecipe_No());
			System.out.println(save);
			
			res = pstm.executeUpdate();
			System.out.println("query 실행 및 준비");
			
			 if(res>0) {
				 commit(con);
			 }
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		return res;
	}
	
	public List<UserRecipeDto> user_bestRecipe(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<UserRecipeDto> res = new ArrayList<UserRecipeDto>();
		
		try {
			pstm = con.prepareStatement(bestRecipeSql);
			System.out.println("3. query 준비:"+bestRecipeSql);
			
			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			
			while(rs.next()) {
				UserRecipeDto tmp = new UserRecipeDto();
				tmp.setRecipe_No(rs.getInt(1));
				tmp.setMember_No(rs.getInt(2));
				tmp.setRecipe_Title(rs.getString(3));
				
				res.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("5. db 종료\n");
		}
		
		return res;
	}
	
}
