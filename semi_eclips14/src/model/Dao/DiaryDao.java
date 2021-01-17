package model.Dao;

import java.sql.Connection;
import java.util.List;


import model.Dto.DiaryDto;


public interface DiaryDao {
	String InsertDiarySql = " INSERT INTO DIARY VALUES(DIARY_SEQ.NEXTVAL,?, ?, ?, ?, ?, ?,SYSDATE, ?, ?, ?) ";
	String DeleteDiarySql = "DELETE FROM DIARY WHERE DIARY_NO=?";
	String SelectAllSql = "SELECT * FROM DIARY WHERE MEMBER_NO=?";
	String SelectOneSql = "SELECT * FROM DIARY WHERE DIARY_NO=?";
	String UpdateSql = " UPDATE DIARY SET DIARY_TITLE=?, DIARY_CONTENT_MORNING=?, DIARY_CONTENT_LUNCH=?, DIARY_CONTENT_DINNER=?, DIARY_CONTENT_RECIPE=?, DIARY_IMAGE_FILENAME=?, DIARY_IMAGE_FILEREALNAME=?, DIARY_IMAGE_UPLOADPATH=? WHERE DIARY_NO=? ";
	
	String diaryno = "SELECT * FROM USER_SEQUENCES WHERE SEQUENCE_NAME= UPPER('DIARY_SEQ')";
	
	
	public List<DiaryDto> selectAllDiary(Connection con, int member_No);
	public DiaryDto selectOneDiary(Connection con, int diary_No);
	public int deleteAllDiary(Connection con, String[] chksdiary);
	public int deleteOneDiary(Connection con, int diary_No);
	public int insertDiary(Connection con, DiaryDto dto);
	public int updateDiary(Connection con, DiaryDto dto);

	
	public int selectdiaryno(Connection con);
}
