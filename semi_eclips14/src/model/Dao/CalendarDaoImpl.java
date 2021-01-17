package model.Dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Dto.CalendarDiaryDto;
import model.Dto.CalendarDto;

public class CalendarDaoImpl implements CalendarDao {

	@Override
	public List<CalendarDto> selectAll(Connection con, int member_no) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalendarDto> res = new ArrayList<CalendarDto>();
		try {
			pstm = con.prepareStatement(selectAll);
			pstm.setInt(1, member_no);
			System.out.println("03.query 준비: "+selectAll);
			rs=pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			while(rs.next()) {
				CalendarDto tmp = new CalendarDto();
				tmp.setMember_no(rs.getInt(1));
				tmp.setCalendar_no(rs.getInt(2));
				tmp.setCalendartitle(rs.getString(3));
				tmp.setCalendardate(rs.getString(4));
				tmp.setContent(rs.getString(5));
				
				res.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("04/03 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}
		return res;
	}

	@Override
	public boolean insertCalendar(Connection con, CalendarDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(insert);
			pstm.setInt(1, dto.getMember_no());
			pstm.setString(2, dto.getCalendartitle());
			pstm.setString(3, dto.getCalendardate());
			pstm.setString(4, dto.getContent());
			System.out.println("03.query 준비: "+insert);
			res = pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
			System.out.println(res);
		} catch (SQLException e) {
			System.out.println("04/03 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
		}
		return (res>0) ? true : false;
	}

	@Override
	public boolean deleteCalendar(Connection con, int member_no, int calendar_No) {
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(delete);
			pstm.setInt(1, member_no);
			pstm.setInt(2, calendar_No);
			System.out.println("03.query 준비: "+delete);
			res = pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
			System.out.println(res);
		} catch (SQLException e) {
			System.out.println("04/03 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
		}
		return (res>0) ? true : false;
	}

	@Override
	public boolean updateCalendar(Connection con, int member_no, int calendar_No, String calendardate) {
	      PreparedStatement pstm = null;
	      int res = 0;
	      System.out.println(calendar_No+"번호"+member_no);
	      System.out.println(calendardate);
	      try {
	         pstm = con.prepareStatement(update);
	         pstm.setString(1, calendardate);
	         pstm.setInt(2, member_no);
	         pstm.setInt(3, calendar_No);
	         System.out.println("03.query 준비: "+update);
	         res = pstm.executeUpdate();
	         System.out.println("04.query 실행 및 리턴");
	         System.out.println(res);
	      } catch (SQLException e) {
	         System.out.println("04/03 에러");
	         e.printStackTrace();
	      }finally {
	         close(pstm);
	      }
	      
	      return (res>0) ? true : false;
	   }
	@Override
	public boolean insertCDiary(Connection con, CalendarDiaryDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(Cdiaryinsert);
			pstm.setInt(1, dto.getMember_no());
			pstm.setInt(2, dto.getDiary_no());
			pstm.setString(3, dto.getCalendartitle());
			pstm.setString(4, dto.getCalendardate());
			pstm.setString(5, dto.getUrl());
			System.out.println("03.query 준비: "+Cdiaryinsert);
			res = pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
			System.out.println(res);
		} catch (SQLException e) {
			System.out.println("04/03 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
		}
		return (res>0) ? true : false;
	}

	@Override
	public List<CalendarDiaryDto> selectD(Connection con, int member_no) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalendarDiaryDto> res = new ArrayList<CalendarDiaryDto>();
		try {
			pstm = con.prepareStatement(selectD);
			pstm.setInt(1, member_no);
			System.out.println("03.query 준비: "+selectD);
			rs=pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			while(rs.next()) {
				CalendarDiaryDto tmp = new CalendarDiaryDto(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
				
				res.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("04/03 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}
		return res;
	}

	@Override
	public boolean deleteCalendarDiary(Connection con, int diary_No) {
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(deleteD);
			pstm.setInt(1, diary_No);
			System.out.println("03.query 준비: "+deleteD);
			res = pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
			System.out.println(res);
		} catch (SQLException e) {
			System.out.println("04/03 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
		}
		return (res>0) ? true : false;
	}

	@Override
	public boolean deleteChkDiary(Connection con, String[] chks) {
		PreparedStatement pstm=null;
   		int res = 0;
		int[] cnt = null;
		try {
			pstm=con.prepareStatement(deleteD);
			for(int i=0; i<chks.length; i++) {
				pstm.setString(1, chks[i]);
				pstm.addBatch();
				System.out.println("03.query 준비: "+deleteD+"(삭제할 번호: "+chks[i]+")");
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
		return (res==chks.length)? true : false;
	}

	
}
