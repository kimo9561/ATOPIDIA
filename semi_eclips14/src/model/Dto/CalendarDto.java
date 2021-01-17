package model.Dto;

public class CalendarDto {
	private int member_no;
	private int calendar_no;
	private String calendartitle;
	private String calendardate;
	private String content;
	public CalendarDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CalendarDto(int member_no, int calendar_no, String calendartitle, String calendardate, String content) {
		super();
		this.member_no = member_no;
		this.calendar_no = calendar_no;
		this.calendartitle = calendartitle;
		this.calendardate = calendardate;
		this.content = content;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getCalendar_no() {
		return calendar_no;
	}
	public void setCalendar_no(int calendar_no) {
		this.calendar_no = calendar_no;
	}
	public String getCalendartitle() {
		return calendartitle;
	}
	public void setCalendartitle(String calendartitle) {
		this.calendartitle = calendartitle;
	}
	public String getCalendardate() {
		return calendardate;
	}
	public void setCalendardate(String calendardate) {
		this.calendardate = calendardate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
