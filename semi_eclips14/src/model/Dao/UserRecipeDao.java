package model.Dao;

public interface UserRecipeDao {
	String SelectAllSql = " SELECT * FROM USER_RECIPE ORDER BY RECIPE_NO DESC";
	String InsertSql = " INSERT INTO USER_RECIPE VALUES(ADMIN_RECIPE_SEQ.NEXTVAL,(SELECT MEMBER_NO FROM MEMBER WHERE MEMBER_NO=?),?, ?, ?, ?, SYSDATE,0,'N',DEFAULT,?,?,?)  ";
	String DeletSql = " DELETE FROM USER_RECIPE WHERE RECIPE_NO=? ";
	String UpdateSql = " UPDATE USER_RECIPE SET RECIPE_TITLE=?, RECIPE_INGREDIENT=?, RECIPE_RECIPE_TEXT=?, RECIPE_IMAGE_NAME=?,  RECIPE_IMAGE_REALNAME=?, RECIPE_IMAGE_UPLOADPATH=? WHERE RECIPE_NO=? ";
	String SelectOneSql = " SELECT * FROM USER_RECIPE WHERE RECIPE_NO =? ";
	
	String addHits = " UPDATE USER_RECIPE SET BOARD_HITS = BOARD_HITS + 1 WHERE RECIPE_NO=? ";
	
	String countSql	 = " SELECT COUNT(*) FROM USERBOARD_INFO ";
	
	String addAnswer = " INSERT INTO ANSWER_USERRECIPE VALUES((SELECT RECIPE_NO FROM USER_RECIPE WHERE RECIPE_NO=?),ANSWER_USERRECIPE_SEQ.NEXTVAL,?,?, SYSDATE) ";
	String answerList = " SELECT * FROM ANSWER_USERRECIPE WHERE RECIPE_NO = ? ";
	String deleteAnswer = " DELETE FROM ANSWER_USERRECIPE WHERE RECIPE_NO=(SELECT RECIPE_NO FROM USER_RECIPE WHERE RECIPE_NO=?)  AND ANSWER_NO=? ";
	String answerSelectOne = " SELECT * FROM ANSWER_USERRECIPE WHERE RECIPE_NO=(SELECT RECIPE_NO FROM USER_RECIPE WHERE RECIPE_NO=?)  AND ANSWER_NO=? ";
	String updateAnswer = " UPDATE ANSWER_USERRECIPE SET ANSWER_WRITER=?, ANSWER_CONTENTE=? WHERE RECIPE_NO=(SELECT RECIPE_NO FROM USER_RECIPE WHERE RECIPE_NO=?)  AND ANSWER_NO=? ";
	
	String save		 = " UPDATE USER_RECIPE SET BOARD_SAVE='Y' WHERE RECIPE_WRITER=? AND RECIPE_NO=? ";

	String bestRecipeSql = " SELECT * FROM ( SELECT RECIPE_NO, MEMBER_NO, RECIPE_TITLE, ROWNUM RNO FROM USER_RECIPE ORDER BY BOARD_HITS DESC ) WHERE RNO < 4 ";
}