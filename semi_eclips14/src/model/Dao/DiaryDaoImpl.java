package model.Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Dto.DiaryDto;

import static common.JDBCTemplate.*;
public class DiaryDaoImpl implements DiaryDao{

	@Override
	public List<DiaryDto> selectAllDiary(Connection con, int member_No) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<DiaryDto> list = new ArrayList<DiaryDto>();
		
		try {
			pstm = con.prepareStatement(SelectAllSql);
			pstm.setInt(1, member_No);
			System.out.println("03.Query 준비 : "+SelectAllSql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				DiaryDto tmp = new DiaryDto();
				tmp.setDiary_No(rs.getInt(1));
				tmp.setMember_No(rs.getInt(2));
				tmp.setDiary_Title(rs.getString(3));
				tmp.setDiary_Content_Morning(rs.getNString(4));
				tmp.setDiary_Content_Lunch(rs.getNString(5));
				tmp.setDiary_Content_Recipe(rs.getNString(6));
				tmp.setDiary_Content_Dinner(rs.getNString(7));
				tmp.setDiary_Date(rs.getDate(8));
				tmp.setDiary_Image_Name(rs.getString(9));
				tmp.setDiary_Image_RealName(rs.getString(10));
				tmp.setDiary_UploadPath(rs.getString(11));
				list.add(tmp);
			}
			System.out.println("04.Query 실행 및 리턴");
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		return list;
	}

	@Override
	public DiaryDto selectOneDiary(Connection con, int diary_No) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		DiaryDto dto = new DiaryDto();
		
		try {
			pstm = con.prepareStatement(SelectOneSql);
			System.out.println("03.Query 준비 : "+SelectOneSql);
			pstm.setInt(1, diary_No);
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto.setDiary_No(rs.getInt(1));
				dto.setMember_No(rs.getInt(2));
				dto.setDiary_Title(rs.getString(3));
				dto.setDiary_Content_Morning(rs.getString(4));
				dto.setDiary_Content_Lunch(rs.getNString(5));
				dto.setDiary_Content_Dinner(rs.getNString(6));
				dto.setDiary_Content_Recipe(rs.getString(7));
				dto.setDiary_Date(rs.getDate(8));
				dto.setDiary_Image_Name(rs.getNString(9));
				dto.setDiary_Image_RealName(rs.getNString(10));
				dto.setDiary_UploadPath(rs.getString(11));				
			}
			System.out.println("04.Query 실행 및 리턴");
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		return dto;
	}
	
	@Override
	public int deleteAllDiary(Connection con, String[] chks) {
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;
		
		try {
			pstm = con.prepareStatement(DeleteDiarySql);
			for(int i=0; i<chks.length; i++) {
				pstm.setString(1, chks[i]);
				pstm.addBatch();
			}
			System.out.println("03.Query 준비 : "+DeleteDiarySql);
			cnt = pstm.executeBatch(); 
			for(int i=0; i<cnt.length; i++) {
				if(cnt[i]==-2) {
					res++;
				}	
			}
			if(res == chks.length) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
		}
		return res;
	}
	
	@Override
	public int deleteOneDiary(Connection con, int diary_No) {
		PreparedStatement pstm = null;
	      int res = 0;
	      try {
	         pstm = con.prepareStatement(DeleteDiarySql);
	         pstm.setInt(1, diary_No);
	         System.out.println("03. Query 준비 : "+DeleteDiarySql);
	         res = pstm.executeUpdate();
	         System.out.println("sql res"+res);
	         System.out.println("04. Query 실행 및 리턴");
	      } catch (SQLException e) {
	         System.out.println("3/4단계 오류");
	         e.printStackTrace();
	      }
	      return res;
	}
	
	
	@Override
	public int insertDiary(Connection con, DiaryDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(InsertDiarySql);
			pstm.setInt(1, dto.getMember_No());
			pstm.setString(2, dto.getDiary_Title());
			pstm.setString(3, dto.getDiary_Content_Morning());
			pstm.setString(4, dto.getDiary_Content_Lunch());
			pstm.setString(5, dto.getDiary_Content_Dinner());
			pstm.setString(6, dto.getDiary_Content_Recipe());
			pstm.setString(7, dto.getDiary_Image_Name());
			pstm.setString(8, dto.getDiary_Image_RealName());
			pstm.setString(9, dto.getDiary_UploadPath());
			System.out.println("03.Query 준비 "+InsertDiarySql);
			
			res = pstm.executeUpdate();
			System.out.println("04.Query 실행 및 리턴");
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		} finally {
			close(pstm);
		}
		return res;
	}
	
	@Override
	public int updateDiary(Connection con, DiaryDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(UpdateSql);
			pstm.setString(1, dto.getDiary_Title());
			pstm.setString(2, dto.getDiary_Content_Morning());
			pstm.setString(3, dto.getDiary_Content_Lunch());
			pstm.setString(4, dto.getDiary_Content_Dinner());
			pstm.setString(5, dto.getDiary_Content_Recipe());
			pstm.setString(6, dto.getDiary_Image_Name());
			pstm.setString(7, dto.getDiary_Image_RealName());
			pstm.setString(8, dto.getDiary_UploadPath());
			pstm.setInt(9, dto.getDiary_No());
			System.out.println("03.Query 준비 :"+UpdateSql);
			res = pstm.executeUpdate();
			System.out.println("04.Query 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		} finally {
			close(pstm);
		}
		return res;
		
	}

	public int selectdiaryno(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String res1 = "";
		try {
			pstm = con.prepareStatement(diaryno);
			System.out.println("03.query 준비:"+diaryno);
			
			rs = pstm.executeQuery(diaryno);
			System.out.println("04.실행 및 리턴");
			while (rs.next()) {
				res = rs.getInt(8)-1;
			}
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			close(rs);
			close(pstm);
		}
		return res;
	}




	


	
		


	
}
