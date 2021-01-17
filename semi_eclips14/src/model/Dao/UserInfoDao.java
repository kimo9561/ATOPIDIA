package model.Dao;

import java.sql.Connection;
import java.util.List;

import model.Dto.UserInfoAnswerDto;
import model.Dto.UserInfoDto;
import model.Dto.UserRecipeAnswerDto;

public interface UserInfoDao {
	String selectOne = " SELECT * FROM USERBOARD_INFO WHERE INFO_NO=? ";
	String insert	 = " INSERT INTO USERBOARD_INFO VALUES(USERINFO_SEQ.NEXTVAL,(SELECT MEMBER_NO FROM MEMBER WHERE MEMBER_NO=?), ?, "
						+ " ?, ?, SYSDATE, DEFAULT,'N', '1',?,?,?) ";
	String delete	 = " DELETE FROM USERBOARD_INFO WHERE INFO_NO=? ";
	String update	 = " UPDATE USERBOARD_INFO SET INFO_TITLE=?, INFO_TEXT=?, INFO_IMAGE_NAME=?, INFO_IMAGE_REALNAME=?, INFO_IMAGE_UPLOADPATH=? WHERE MEMBER_NO=? AND INFO_NO=? ";
	String save		 = " UPDATE USERBOARD_INFO SET BOARD_SAVE='Y' WHERE INFO_NO=? ";
	String countSql	 = " SELECT COUNT(*) FROM USERBOARD_INFO ";
	String selectAllPage = "SELECT * FROM (SELECT ROWNUM RNUM, INFO_NO, MEMBER_NO, INFO_WRITER, INFO_TITLE, INFO_TEXT, INFO_DATE, INFO_HITS, BOARD_SAVE, GRADE_CODE, INFO_IMAGE_NAME, INFO_IMAGE_REALNAME, INFO_IMAGE_UPLOADPATH FROM (SELECT * FROM USERBOARD_INFO ORDER BY INFO_NO DESC)) WHERE RNUM BETWEEN ? AND ? ";

	String selectSave= " SELECT * FROM USERBOARD_INFO WHERE BOARD_SAVE='Y' AND INFO_NO=? ";
	String answerList = " SELECT * FROM ANSWER_USERINFO WHERE INFO_NO = ? ";
	String addHits = " UPDATE USERBOARD_INFO SET INFO_HITS = INFO_HITS + 1 WHERE INFO_NO=? ";
	String addAnswer = " INSERT INTO ANSWER_USERINFO VALUES((SELECT INFO_NO FROM USERBOARD_INFO WHERE INFO_NO=?),USERINFOANSWER_SEQ.NEXTVAL,?,?, SYSDATE) ";
	String deleteAnswer = " DELETE FROM ANSWER_USERINFO WHERE INFO_NO=(SELECT INFO_NO FROM USERBOARD_INFO WHERE INFO_NO=?)  AND ANSWER_NO=? ";
	String answerSelectOne = " SELECT * FROM ANSWER_USERINFO WHERE INFO_NO=(SELECT INFO_NO FROM USERBOARD_INFO WHERE INFO_NO=?)  AND ANSWER_NO=? ";
	String updateAnswer = " UPDATE ANSWER_USERINFO SET ANSWER_WRITER=?, ANSWER_CONTENTE=? WHERE INFO_NO=(SELECT INFO_NO FROM USERBOARD_INFO WHERE INFO_NO=?)  AND ANSWER_NO=? ";
	
	String bestInfoSql = " SELECT * FROM ( SELECT INFO_NO, MEMBER_NO, INFO_TITLE, ROWNUM RNO FROM USERBOARD_INFO ORDER BY INFO_HITS DESC ) WHERE RNO < 4 ";
	
	
	
	public UserInfoDto selectOne(Connection con,int info_No);
	public int deleteInfo(Connection con,int info_No);
	public int updateInfo(Connection con,UserInfoDto dto);
	public int saveInfo(Connection con,UserInfoDto dto);
	int insertInfo(Connection con, UserInfoDto dto);
	public List<UserInfoDto> user_bestInfo(Connection con);
	public int insertAnswer(Connection con,UserInfoAnswerDto dto);
	public int getBoardCount(Connection con);	//totalRow
	public Object selectAll(Connection con, int startRow, int endRow);
	Object service(Connection con, Object obj) throws Exception;
}
