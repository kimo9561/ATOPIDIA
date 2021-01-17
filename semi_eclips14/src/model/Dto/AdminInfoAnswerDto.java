package model.Dto;

import java.sql.Date;

public class AdminInfoAnswerDto {
	private int info_No;
	private int answer_No;
	private String answer_Writer;
	private String answer_Content;
	private Date answer_Date;
	
	
	public AdminInfoAnswerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public AdminInfoAnswerDto(int info_No, int answer_No, String answer_Writer, String answer_Content) {
		super();
		this.info_No = info_No;
		this.answer_No = answer_No;
		this.answer_Writer = answer_Writer;
		this.answer_Content = answer_Content;
	}



	public AdminInfoAnswerDto(int info_No, int answer_No, String answer_Writer, String answer_Content,
			Date answer_Date) {
		super();
		this.info_No = info_No;
		this.answer_No = answer_No;
		this.answer_Writer = answer_Writer;
		this.answer_Content = answer_Content;
		this.answer_Date = answer_Date;
	}


	public AdminInfoAnswerDto(int info_No, String answer_Writer, String answer_Content) {
		super();
		this.info_No = info_No;
		this.answer_Writer = answer_Writer;
		this.answer_Content = answer_Content;
	}


	public int getInfo_No() {
		return info_No;
	}


	public void setInfo_No(int info_No) {
		this.info_No = info_No;
	}


	public int getAnswer_No() {
		return answer_No;
	}


	public void setAnswer_No(int answer_No) {
		this.answer_No = answer_No;
	}


	public String getAnswer_Writer() {
		return answer_Writer;
	}


	public void setAnswer_Writer(String answer_Writer) {
		this.answer_Writer = answer_Writer;
	}


	public String getAnswer_Content() {
		return answer_Content;
	}


	public void setAnswer_Content(String answer_Content) {
		this.answer_Content = answer_Content;
	}


	public Date getAnswer_Date() {
		return answer_Date;
	}


	public void setAnswer_Date(Date answer_Date) {
		this.answer_Date = answer_Date;
	}

	

	
}


