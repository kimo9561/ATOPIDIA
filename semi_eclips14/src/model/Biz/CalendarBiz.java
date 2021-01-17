package model.Biz;

import java.sql.Connection;
import java.util.List;

import model.Dao.CalendarDao;
import model.Dao.CalendarDaoImpl;
import model.Dto.CalendarDiaryDto;
import model.Dto.CalendarDto;
import static common.JDBCTemplate.*;

public class CalendarBiz {
	private CalendarDao dao = new CalendarDaoImpl();
	public List<CalendarDto> selectAll(int member_no) {
		Connection con = getConnection();		
		List<CalendarDto> res = dao.selectAll(con, member_no);		
		close(con);
		System.out.println("05.db 종료\n");
		return res;
	}
	
	public boolean updateCalendar(int member_no, int calendar_No, String calendardate) {
		Connection con = getConnection();
		System.out.println(calendar_No);
		boolean res = dao.updateCalendar(con, member_no, calendar_No, calendardate);
		if(res) {
			commit(con);
		}
		close(con);
		System.out.println("05.db 종료\n");
		System.out.println(res);
		return res;
	}
	
	public boolean insertCalendar(CalendarDto dto) {
		Connection con = getConnection();
		boolean res = dao.insertCalendar(con, dto);
		if(res) {
			commit(con);
		}
		close(con);
		System.out.println("05.db 종료\n");
		System.out.println(res);
		return res;
	}
	
	public boolean deleteCalendar(int member_no, int calendar_No) {
		Connection con = getConnection();
		boolean res = dao.deleteCalendar(con, member_no, calendar_No);
		if(res) {
			commit(con);
		}
		close(con);
		System.out.println("05.db 종료\n");
		System.out.println(res);
		return res;
	}

	public boolean insertCDiary(CalendarDiaryDto dto) {
		Connection con = getConnection();
		boolean res = dao.insertCDiary(con, dto);
		if(res) {
			commit(con);
		}
		close(con);
		System.out.println("05.db 종료\n");
		System.out.println(res);
		return res;
	}

	public List<CalendarDiaryDto> selectD(int member_no) {
		Connection con = getConnection();		
		List<CalendarDiaryDto> res = dao.selectD(con, member_no);	
		close(con);
		System.out.println("05.db 종료\n");
		return res;
	}
	
	public boolean deleteCalendarDiary(int diary_No) {
		Connection con = getConnection();
		boolean res = dao.deleteCalendarDiary(con, diary_No);
		if(res) {
			commit(con);
		}
		close(con);
		System.out.println("05.db 종료\n");
		System.out.println(res);
		return res;
	}

	public boolean deleteChkDiary(String[] chks) {
		Connection con = getConnection();
		boolean res = dao.deleteChkDiary(con, chks);
		if(res) {
			commit(con);
		}
		close(con);
		System.out.println("05.db 종료\n");
		System.out.println(res);
		return res;
	}
	
}
