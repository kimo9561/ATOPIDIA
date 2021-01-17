package model.Dao;

import java.sql.Connection;
import java.util.List;

import model.Dto.MemberDto;

public interface MemberDao {
    String SelectAllSql=" SELECT * FROM MEMBER ORDER BY MEMBER_NO DESC";
    String selectOneSql="SELECT * FROM MEMBER WHERE MEMBER_NO=?";
    String InsertSql="INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL,?,?,?,?,?,DEFAULT)";
    String UpdateSql="UPDATE MEMBER SET MEMBER_PW=? WHERE MEMBER_NO=?";
    String LoginSql="SELECT * FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PW=?";
    String IdChkSql=" SELECT * FROM MEMBER WHERE MEMBER_ID=? ";
    String DeleteSql="DELETE FROM MEMBER WHERE MEMBER_NO=?";
    String SearchIdSql="SELECT * FROM MEMBER WHERE MEMBER_NAME=? AND MEMBER_EMAIL=?";
    String searchPwSql = " SELECT * FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_RRN=? ";
   
    String SelectEmail="SELECT * FROM MEMBER WHERE MEMBER_EMAIL=?";
   public List<MemberDto>selectAll(Connection con);
   public MemberDto selectOne(Connection con,int Member_No);
   public int InsertUser(Connection con,MemberDto dto);
   public int UpdateUser(Connection con,MemberDto dto);
   public int DeleteUser(Connection con,int Member_No);
   public String IdChk(Connection con,String member_Id);
   public MemberDto SearchId(Connection con, String member_Name, String member_Email);
   public MemberDto searchPw(Connection con, String id, String rrn);
   public String SelectEmail(Connection con, String member_Email);
   public boolean multiDelete(Connection con, String[] num);
}
