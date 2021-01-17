package model.Dto;

import java.sql.Date;

public class MyInfoDto {
	private int info_No;
	private int member_No;
	private String info_Writer;
	private String info_Title;
	private String info_Text;
	private Date info_Date;
	private Date info_saveDate;
	private String info_Save;
	private int grade_Code;
	private String info_Image_Name;
	private String info_Image_Realname;
	private String info_Image_Uploadpath;

	
	
	public MyInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public MyInfoDto(int info_No, int member_No, String info_Writer, String info_Title, String info_Text,
			Date info_Date, Date info_saveDate, String info_Save, int grade_Code, String info_Image_Name,
			String info_Image_Realname, String info_Image_Uploadpath) {
		super();
		this.info_No = info_No;
		this.member_No = member_No;
		this.info_Writer = info_Writer;
		this.info_Title = info_Title;
		this.info_Text = info_Text;
		this.info_Date = info_Date;
		this.info_saveDate = info_saveDate;
		this.info_Save = info_Save;
		this.grade_Code = grade_Code;
		this.info_Image_Name = info_Image_Name;
		this.info_Image_Realname = info_Image_Realname;
		this.info_Image_Uploadpath = info_Image_Uploadpath;
	}
	
	



	public MyInfoDto(String info_Writer, String info_Title, String info_Image_Name, String info_Image_Realname,
			String info_Image_Uploadpath) {
		super();
		this.info_Writer = info_Writer;
		this.info_Title = info_Title;
		this.info_Image_Name = info_Image_Name;
		this.info_Image_Realname = info_Image_Realname;
		this.info_Image_Uploadpath = info_Image_Uploadpath;
	}



	public MyInfoDto(int info_No, int member_No, String info_Writer, String info_Title, String info_Text,
			Date info_Date, Date info_saveDate, String info_Save, int grade_Code) {
		super();
		this.info_No = info_No;
		this.member_No = member_No;
		this.info_Writer = info_Writer;
		this.info_Title = info_Title;
		this.info_Text = info_Text;
		this.info_Date = info_Date;
		this.info_saveDate = info_saveDate;
		this.info_Save = info_Save;
		this.grade_Code = grade_Code;
	}

	
	
	public String getInfo_Image_Name() {
		return info_Image_Name;
	}



	public void setInfo_Image_Name(String info_Image_Name) {
		this.info_Image_Name = info_Image_Name;
	}



	public String getInfo_Image_Realname() {
		return info_Image_Realname;
	}



	public void setInfo_Image_Realname(String info_Image_Realname) {
		this.info_Image_Realname = info_Image_Realname;
	}



	public String getInfo_Image_Uploadpath() {
		return info_Image_Uploadpath;
	}



	public void setInfo_Image_Uploadpath(String info_Image_Uploadpath) {
		this.info_Image_Uploadpath = info_Image_Uploadpath;
	}



	public int getInfo_No() {
		return info_No;
	}


	public void setInfo_No(int info_No) {
		this.info_No = info_No;
	}


	public int getMember_No() {
		return member_No;
	}


	public void setMember_No(int member_No) {
		this.member_No = member_No;
	}


	public String getInfo_Writer() {
		return info_Writer;
	}


	public void setInfo_Writer(String info_Writer) {
		this.info_Writer = info_Writer;
	}


	public String getInfo_Title() {
		return info_Title;
	}


	public void setInfo_Title(String info_Title) {
		this.info_Title = info_Title;
	}


	public String getInfo_Text() {
		return info_Text;
	}


	public void setInfo_Text(String info_Text) {
		this.info_Text = info_Text;
	}


	public Date getInfo_Date() {
		return info_Date;
	}


	public void setInfo_Date(Date info_Date) {
		this.info_Date = info_Date;
	}


	public Date getInfo_saveDate() {
		return info_saveDate;
	}


	public void setInfo_saveDate(Date info_saveDate) {
		this.info_saveDate = info_saveDate;
	}


	public String getInfo_Save() {
		return info_Save;
	}


	public void setInfo_Save(String info_Save) {
		this.info_Save = info_Save;
	}


	public int getGrade_Code() {
		return grade_Code;
	}


	public void setGrade_Code(int grade_Code) {
		this.grade_Code = grade_Code;
	}

	
}
