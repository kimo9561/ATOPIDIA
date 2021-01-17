package model.Dto;

public class CalendarDiaryDto {
	private int member_no;
	private int diary_no;
	private String calendartitle;
	private String calendardate;
	private String url;
	public CalendarDiaryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CalendarDiaryDto(int member_no, int diary_no, String calendartitle, String calendardate, String url) {
		super();
		this.member_no = member_no;
		this.diary_no = diary_no;
		this.calendartitle = calendartitle;
		this.calendardate = calendardate;
		this.url = url;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getDiary_no() {
		return diary_no;
	}
	public void setDiary_no(int diary_no) {
		this.diary_no = diary_no;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
