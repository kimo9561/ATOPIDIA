package model.Biz;

import java.sql.Connection;

import java.util.List;


import model.Dao.DiaryDao;
import model.Dao.DiaryDaoImpl;
import model.Dto.AdminInfoDto;
import model.Dto.AdminRecipeDto;
import model.Dto.DiaryDto;
import model.Dto.UserInfoDto;
import model.Dto.UserRecipeDto;
import model.Dto.MyInfoDto;
import model.Dto.MyRecipeDto;

import static common.JDBCTemplate.*;

public class DiaryBizImpl implements DiaryBiz{
	private DiaryDto dto = new DiaryDto();
	private DiaryDaoImpl dao = new DiaryDaoImpl();
	@Override
	public List<DiaryDto> selectAllDiary(int member_No) {
		Connection con = getConnection();
		List<DiaryDto> res = dao.selectAllDiary(con, member_No);
		close(con);
		return res;
	}

	@Override
	public DiaryDto selectOneDiary(int diary_No) {
		Connection con = getConnection();
		DiaryDto res = dao.selectOneDiary(con, diary_No);
		close(con);
		return res;
	}
	

	@Override
	public int insertDiary(DiaryDto dto) {
		Connection con = getConnection();
		int res = dao.insertDiary(con, dto);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}	
		
		return res;
	}
	
	@Override
	public int deleteAllDiary(String[] chks) {
		Connection con = getConnection();
		int res = dao.deleteAllDiary(con, chks);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}	
		return res;
	}
	
	@Override
	public int updateDiary(DiaryDto dto) {
		Connection con = getConnection();
		int res = dao.updateDiary(con, dto);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		return res;
	}
	

	@Override
	public int selectdiaryno() {
		Connection con = getConnection();
		int res =dao.selectdiaryno(con);
		close(con);
		return res;
	}

	public int deleteDiary(int diary_No) {
		Connection con = getConnection();
		int res = dao.deleteOneDiary(con, diary_No);
		System.out.println("biz res : "+res);
		if(res > 0) {
			commit(con);
		}
		close(con);
		return res;
	}

		
}
