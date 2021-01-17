package model.Dao;

import java.sql.Connection;
import java.util.List;

import model.Dto.AdminInfoAnswerDto;
import model.Dto.AdminInfoDto;

public interface AdminInfoDao {
	
	String insert	 = " INSERT INTO ADMINBOARD_INFO VALUES(ADMININFO_SEQ.NEXTVAL,(SELECT MEMBER_NO FROM MEMBER WHERE MEMBER_NO=?), ?,"
			+ " ?, ?, SYSDATE, DEFAULT,'Y', '1',?,?,?) ";	
	String delete	 = " DELETE FROM ADMINBOARD_INFO WHERE INFO_NO=? ";
	String selectOne = " SELECT * FROM ADMINBOARD_INFO WHERE INFO_NO=? ";
	String update	 = " UPDATE ADMINBOARD_INFO SET INFO_TITLE=?, INFO_TEXT=?, INFO_IMAGE_NAME=?, INFO_IMAGE_REALNAME=?, INFO_IMAGE_UPLOADPATH=? WHERE MEMBER_NO=? AND INFO_NO=? ";
	String save		 = " UPDATE ADMINBOARD_INFO SET BOARD_SAVE='Y' WHERE INFO_NO=? ";
	String selectSave= " SELECT * FROM ADMINBOARD_INFO WHERE BOARD_SAVE='Y' AND INFO_NO=? ";
	String selectAllPage = "SELECT * FROM (SELECT ROWNUM RNUM, INFO_NO, MEMBER_NO, INFO_WRITER, INFO_TITLE, INFO_TEXT, INFO_DATE, INFO_HITS, BOARD_SAVE, GRADE_CODE, INFO_IMAGE_NAME, INFO_IMAGE_REALNAME, INFO_IMAGE_UPLOADPATH FROM (SELECT * FROM ADMINBOARD_INFO ORDER BY INFO_NO DESC)) WHERE RNUM BETWEEN ? AND ? ";
	String countSql	 = " SELECT COUNT(*) FROM ADMINBOARD_INFO ";
	
	String answerList = " SELECT * FROM ANSWER_ADMIN_INFO WHERE INFO_NO = ? ";
	String addHits = " UPDATE ADMINBOARD_INFO SET INFO_HITS = INFO_HITS + 1 WHERE INFO_NO=? ";
	String addAnswer = " INSERT INTO ANSWER_ADMIN_INFO VALUES((SELECT INFO_NO FROM ADMINBOARD_INFO WHERE INFO_NO=?),ADMIN_INFO_ANSWER_SEQ.NEXTVAL,?,?, SYSDATE) ";
	String deleteAnswer = " DELETE FROM ANSWER_ADMIN_INFO WHERE INFO_NO=(SELECT INFO_NO FROM ADMINBOARD_INFO WHERE INFO_NO=?)  AND ANSWER_NO=? ";
	String answerSelectOne = " SELECT * FROM ANSWER_ADMIN_INFO WHERE INFO_NO=(SELECT INFO_NO FROM ADMINBOARD_INFO WHERE INFO_NO=?)  AND ANSWER_NO=? ";
	String updateAnswer = " UPDATE ANSWER_ADMIN_INFO SET ANSWER_WRITER=?, ANSWER_CONTENTE=? WHERE INFO_NO=(SELECT INFO_NO FROM ADMINBOARD_INFO WHERE INFO_NO=?)  AND ANSWER_NO=? ";
	
	String bestInfoSql = " SELECT * FROM ( SELECT INFO_NO, MEMBER_NO, INFO_TITLE, ROWNUM RNO FROM ADMINBOARD_INFO ORDER BY INFO_HITS DESC ) WHERE RNO < 4 ";
	
	
	public AdminInfoDto selectOne(Connection con,int info_No);
	public int deleteInfo(Connection con,int info_No);
	public int updateInfo(Connection con,AdminInfoDto dto);
	public int saveInfo(Connection con,AdminInfoDto dto);
	int insertInfo(Connection con, AdminInfoDto dto);
	public List<AdminInfoDto> admin_bestInfo(Connection con);
	public int getBoardCount(Connection con);	
	public Object selectAll(Connection con, int startRow, int endRow);
	Object service(Connection con,Object obj) throws Exception;
	public int insertAnswer(Connection con,AdminInfoAnswerDto dto);
	
	
}
