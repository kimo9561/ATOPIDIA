package model.Dto;

import java.util.Date;

public class DiaryDto {
	private int diary_No;
	private int member_No;
	private String diary_Title;
	private String diary_Content_Morning;
	private String diary_Content_Lunch;
	private String diary_Content_Dinner;
	private String diary_Content_Recipe;
	private Date diary_Date;
	private String diary_Image_Name;
	private String diary_Image_RealName;
	private String diary_UploadPath;
	public DiaryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiaryDto(int diary_No, int member_No, String diary_Title, String diary_Content_Morning,
			String diary_Content_Lunch, String diary_Content_Dinner, String diary_Content_Recipe, Date diary_Date,
			String diary_Image_Name, String diary_Image_RealName, String diary_UploadPath) {
		super();
		this.diary_No = diary_No;
		this.member_No = member_No;
		this.diary_Title = diary_Title;
		this.diary_Content_Morning = diary_Content_Morning;
		this.diary_Content_Lunch = diary_Content_Lunch;
		this.diary_Content_Dinner = diary_Content_Dinner;
		this.diary_Content_Recipe = diary_Content_Recipe;
		this.diary_Date = diary_Date;
		this.diary_Image_Name = diary_Image_Name;
		this.diary_Image_RealName = diary_Image_RealName;
		this.diary_UploadPath = diary_UploadPath;
	}
	public int getDiary_No() {
		return diary_No;
	}
	public void setDiary_No(int diary_No) {
		this.diary_No = diary_No;
	}
	public int getMember_No() {
		return member_No;
	}
	public void setMember_No(int member_No) {
		this.member_No = member_No;
	}
	public String getDiary_Title() {
		return diary_Title;
	}
	public void setDiary_Title(String diary_Title) {
		this.diary_Title = diary_Title;
	}
	public String getDiary_Content_Morning() {
		return diary_Content_Morning;
	}
	public void setDiary_Content_Morning(String diary_Content_Morning) {
		this.diary_Content_Morning = diary_Content_Morning;
	}
	public String getDiary_Content_Lunch() {
		return diary_Content_Lunch;
	}
	public void setDiary_Content_Lunch(String diary_Content_Lunch) {
		this.diary_Content_Lunch = diary_Content_Lunch;
	}
	public String getDiary_Content_Dinner() {
		return diary_Content_Dinner;
	}
	public void setDiary_Content_Dinner(String diary_Content_Dinner) {
		this.diary_Content_Dinner = diary_Content_Dinner;
	}
	public String getDiary_Content_Recipe() {
		return diary_Content_Recipe;
	}
	public void setDiary_Content_Recipe(String diary_Content_Recipe) {
		this.diary_Content_Recipe = diary_Content_Recipe;
	}
	public Date getDiary_Date() {
		return diary_Date;
	}
	public void setDiary_Date(Date diary_Date) {
		this.diary_Date = diary_Date;
	}
	public String getDiary_Image_Name() {
		return diary_Image_Name;
	}
	public void setDiary_Image_Name(String diary_Image_Name) {
		this.diary_Image_Name = diary_Image_Name;
	}
	public String getDiary_Image_RealName() {
		return diary_Image_RealName;
	}
	public void setDiary_Image_RealName(String diary_Image_RealName) {
		this.diary_Image_RealName = diary_Image_RealName;
	}
	public String getDiary_UploadPath() {
		return diary_UploadPath;
	}
	public void setDiary_UploadPath(String diary_UploadPath) {
		this.diary_UploadPath = diary_UploadPath;
	}
	
	
	
}
