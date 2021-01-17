package model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import model.Dto.MemberDto;

public class MemberDaoImpl extends JDBCTemplate implements MemberDao {

   // 전체 회원 확인
   public List<MemberDto> selectAll(Connection con) {
   
	   PreparedStatement pstm = null;
	      ResultSet rs = null;
	      List<MemberDto> res = new ArrayList<MemberDto>();
	      
	      try {
	    	  pstm = con.prepareStatement(SelectAllSql);
	         System.out.println("03. query 준비:"+SelectAllSql);
	         
	         rs = pstm.executeQuery();
	         System.out.println("04. query 실행 및 리턴");
	         
	         while(rs.next()) {
	            MemberDto dto = new MemberDto();
	            dto.setMember_No(rs.getInt(1));
	            dto.setMember_Id(rs.getString(2));
	            dto.setMember_Pw(rs.getString(3));
	            dto.setMember_Name(rs.getString(4));
	            dto.setMember_Email(rs.getString(5));
	            dto.setMember_RRN(rs.getString(6));
	            dto.setGrade_Code(rs.getInt(7));
	            
	            res.add(dto);
	         }
	      } catch (SQLException e) {
	         System.out.println("3/4 단계 오류");
	         e.printStackTrace();
	      } finally {
	         close(rs);
	         close(pstm);
	   
	         System.out.println("05. db 종료");
	      }
	      
	      return res;
	   
   }
  public MemberDto selectOne(Connection con, int Member_No) {
	  PreparedStatement pstm =null;
	  ResultSet rs= null;
	 MemberDto res=new MemberDto();
	  try {
		pstm=con.prepareStatement(selectOneSql);
		pstm.setInt(1, Member_No);
		
		System.out.println("03.query준비:"+selectOneSql);
		
		rs=pstm.executeQuery();
		
		System.out.println("04.query실행 및 리턴");
		while(rs.next()) {
			 res.setMember_No(rs.getInt(1));
			 res.setMember_Id(rs.getString(2));
			 res.setMember_Pw(rs.getString(3));
			 res.setMember_Name(rs.getString(4));
			 res.setMember_Email(rs.getString(5));
			 res.setMember_RRN(rs.getString(6));
			 res.setGrade_Code(rs.getInt(7));
		}
	} catch (SQLException e) {
		System.out.println("3/4단계 오류");
		e.printStackTrace();
	}finally {
		close(pstm);
		close(rs);
	}
	  
	  return res ;
  }

   // 회원 가입
   public int InsertUser(Connection con,MemberDto dto) {
      PreparedStatement pstm=null;
      int res=0;
      
      try {
         pstm=con.prepareStatement(InsertSql);
         
      
         pstm.setString(1, dto.getMember_Id());
         pstm.setString(2,dto.getMember_Pw());
         pstm.setString(3, dto.getMember_Name());
         pstm.setString(4, dto.getMember_Email());
         pstm.setString(5, dto.getMember_RRN());
         
         System.out.println("03.query준비:"+InsertSql);
         res=pstm.executeUpdate();
         System.out.println("04.query실행 및 리턴");
      
         
         
      } catch (SQLException e) {
         System.out.println("3/4단계 에러");
         e.printStackTrace();
      }finally {
         close(pstm);
         System.out.println("05.db종료");
      }
      
      return res;
   }
   
   // 로그인
   public MemberDto Login(Connection con,String member_Id,String member_Pw ) {
      PreparedStatement pstm=null;
      ResultSet rs=null;
      MemberDto res= new MemberDto();
      System.out.println(member_Id+member_Pw);
      try {
         pstm=con.prepareStatement(LoginSql);
         pstm.setString(1, member_Id);
         pstm.setString(2, member_Pw );
         System.out.println("03.query준비:"+LoginSql);
         
         rs=pstm.executeQuery();
         System.out.println("04.query실행 및 리턴");
      
         while(rs.next()) {
            res.setMember_No(rs.getInt(1));
            res.setMember_Id(rs.getString(2));
            res.setMember_Pw(rs.getString(3));
            res.setMember_Name(rs.getString(4));
            res.setMember_Email(rs.getString(5));
            res.setMember_RRN(rs.getString(6));
            res.setGrade_Code(rs.getInt(7));
            
         }
         
      } catch (SQLException e) {
         System.out.println("3/4단계 에러");
         e.printStackTrace();
      }finally {
         close(rs);
         close(pstm);

         System.out.println("05.db종료\n");
         System.out.println(res.toString());
      }
      return res;
   }
   
   // 아이디 중복
   public String IdChk(Connection con,String member_Id) {
      PreparedStatement pstm=null;
      ResultSet rs= null;
      String  res=null;
      System.out.println("Dao "+member_Id);
      try {
         pstm=con.prepareStatement(IdChkSql);
         pstm.setString(1, member_Id);
         System.out.println("03.query준비:"+IdChkSql);
         
         rs=pstm.executeQuery();
         System.out.println("04.query실행 및 리턴");
         
         while(rs.next()) {
            res=rs.getString(2);
         }
         
      } catch (SQLException e) {
         System.out.println("3/4단계에러");
         e.printStackTrace();
      }finally {
         close(rs);
         close(pstm);
         System.out.println("05.db종료\n");
      }
      return res;
   }
   

   // 개인정보 수정
   public int UpdateUser(Connection con,MemberDto dto) {
      PreparedStatement pstm=null;
      int res=0;
      
      try {
         pstm=con.prepareStatement(UpdateSql);
        
         pstm.setString(1, dto.getMember_Pw());
         pstm.setInt(2, dto.getMember_No());
         System.out.println("03.query준비:"+UpdateSql);
         res=pstm.executeUpdate();
         System.out.println("04.query 실행 및 리턴");
      
      } catch (SQLException e) {
         System.out.println("3/4단계 에러");
         e.printStackTrace();
      }finally {
         close(pstm);
         System.out.println("05.db종료");
      }
      
      return res;
   }
   

   // 회원 탈퇴
      public int DeleteUser(Connection con,int memberno) {
         PreparedStatement pstm=null;
         int res=0;
         try {
            pstm=con.prepareStatement(DeleteSql);
            pstm.setInt(1, memberno);
            System.out.println("03.query준비:"+DeleteSql);
            res=pstm.executeUpdate();
            System.out.println("04.query실행 및 리턴");
         } catch (SQLException e) {
            System.out.println("3/4단계 에러");
            e.printStackTrace();
         }finally {
            close(pstm);
            System.out.println("05.db종료\n");
         }
         
         return res;
      }
   

   // 아이디 찾기
   public MemberDto SearchId(Connection con,String member_Name, String member_Email) {
	   PreparedStatement pstm=null;
	   ResultSet rs=null;
	   MemberDto res=new MemberDto();
	   try {
		pstm=con.prepareStatement(SearchIdSql);
		pstm.setString(1, member_Name);
		pstm.setString(2, member_Email);
		
		System.out.println("03.query준비:"+SearchIdSql);
		
		rs=pstm.executeQuery();
		
		System.out.println("04.query실행 및 리턴");
		
		while(rs.next()) {
			res.setMember_No(rs.getInt(1));
            res.setMember_Id(rs.getString(2));
            res.setMember_Pw(rs.getString(3));
            res.setMember_Name(rs.getString(4));
            res.setMember_Email(rs.getString(5));
            res.setMember_RRN(rs.getString(6));
            res.setGrade_Code(rs.getInt(7));
			
		}
		
		
	} catch (SQLException e) {
		System.out.println("3/4단계 에러");
		e.printStackTrace();
	}finally {
		close(pstm);
		close(rs);
		System.out.println("05.db종료\n");
	}
	   System.out.println(res.getGrade_Code());
	   return res;
   }
      
   // 비밀번호 찾기
   public MemberDto searchPw(Connection con, String id, String rrn) {
	   PreparedStatement pstm = null;
	   ResultSet rs = null;
	   MemberDto res = new MemberDto();
	   
	   try {
	      pstm = con.prepareStatement(searchPwSql);
	      pstm.setString(1, id);
	      pstm.setString(2, rrn);
	      System.out.println("3. query 준비:"+searchPwSql);
	      
	      rs = pstm.executeQuery();
	      System.out.println("4. query 실행 및 리턴");
	      
	      while(rs.next()) {
	         res.setMember_No(rs.getInt(1));
	            res.setMember_Id(rs.getString(2));
	            res.setMember_Pw(rs.getString(3));
	            res.setMember_Name(rs.getString(4));
	            res.setMember_Email(rs.getString(5));
	            res.setMember_RRN(rs.getString(6));
	            res.setGrade_Code(rs.getInt(7));
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
	@Override
   	public boolean multiDelete(Connection con, String[] num) {
   		PreparedStatement pstm=null;
   		int res = 0;
		int[] cnt = null;
		try {
			pstm=con.prepareStatement(DeleteSql);
			for(int i=0; i<num.length; i++) {
				pstm.setString(1, num[i]);
				pstm.addBatch();
				System.out.println("03.query 준비: "+DeleteSql+"(삭제할 번호: "+num[i]+")");
			}
			cnt = pstm.executeBatch();
			//성공: -2, 실패:-3
			for(int i=0; i<cnt.length; i++) {
				if(cnt[i]==-2) {
					res++;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return (res==num.length)? true : false;
   	}
	@Override
	public String SelectEmail(Connection con, String member_Email) {
		PreparedStatement pstm=null;
		ResultSet rs= null;
		String res=null;
		
		try {
			pstm=con.prepareStatement(SelectEmail);
			pstm.setString(1,member_Email);
			
			System.out.println("03.query준비:"+SelectEmail);
			
			rs=pstm.executeQuery();
			System.out.println("04.query실행 및 리턴");
			while(rs.next()) {
				res=rs.getString(5);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(rs);
			System.out.println("05.db종료\n");
		}
		
		return res;
	}

		
	}







   

















