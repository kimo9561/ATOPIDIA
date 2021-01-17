package model.Dao;

import java.sql.Connection;
import java.util.List;

import model.Dto.CalendarDiaryDto;
import model.Dto.CalendarDto;

public interface CalendarDao {
	String selectAll = " SELECT * FROM CALENDAR WHERE MEMBER_NO=? ";
	String insert = " INSERT INTO CALENDAR VALUES(?, CALENDAR_SEQ.NEXTVAL, ?, ?, ?) ";
	String delete = " DELETE FROM CALENDAR WHERE MEMBER_NO=? AND CALENDAR_NO=?";
	String update = " UPDATE CALENDAR SET CDATE=? WHERE MEMBER_NO=? AND CALENDAR_NO=?";
	String Cdiaryinsert = "INSERT INTO CALENDAR_DIARY VALUES(?, ?, ?, ?, ?)";
	String selectD = "SELECT * FROM CALENDAR_DIARY WHERE MEMBER_NO=?";
	String deleteD = "DELETE FROM CALENDAR_DIARY WHERE DIARY_NO=?";
	
	public List<CalendarDto> selectAll(Connection con, int member_no);
	public boolean updateCalendar(Connection con, int member_no, int calendar_No, String calendardate);
	public boolean insertCalendar(Connection con, CalendarDto dto);
	public boolean deleteCalendar(Connection con, int member_no, int calendar_No);
	public boolean insertCDiary(Connection con, CalendarDiaryDto dto);
	public List<CalendarDiaryDto> selectD(Connection con, int member_no);
	public boolean deleteCalendarDiary(Connection con, int diary_No);
	public boolean deleteChkDiary(Connection con, String[] chks);
}
