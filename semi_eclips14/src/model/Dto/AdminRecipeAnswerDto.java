package model.Dto;

import java.sql.Date;

public class AdminRecipeAnswerDto {
	private int recipe_No;
	private int answer_No;
	private String answer_Writer;
	private String answer_Content;
	private Date answer_Date;
	
	
	public AdminRecipeAnswerDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AdminRecipeAnswerDto(int recipe_No, int answer_No, String answer_Writer, String answer_Content, Date answer_Date) {
		super();
		this.recipe_No = recipe_No;
		this.answer_No = answer_No;
		this.answer_Writer = answer_Writer;
		this.answer_Content = answer_Content;
		this.answer_Date = answer_Date;
	}
	

	


	public AdminRecipeAnswerDto(int recipe_No, int answer_No, String answer_Writer, String answer_Content) {
		super();
		this.recipe_No = recipe_No;
		this.answer_No = answer_No;
		this.answer_Writer = answer_Writer;
		this.answer_Content = answer_Content;
	}


	public AdminRecipeAnswerDto(int recipe_No, String answer_Writer, String answer_Content) {
		super();
		this.recipe_No = recipe_No;
		this.answer_Writer = answer_Writer;
		this.answer_Content = answer_Content;
	}


	public int getRecipe_No() {
		return recipe_No;
	}


	public void setRecipe_No(int recipe_No) {
		this.recipe_No = recipe_No;
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


	@Override
	public String toString() {
		return "AnswerDto [recipe_No=" + recipe_No + ", answer_No=" + answer_No + ", answer_Writer=" + answer_Writer
				+ ", answer_Content=" + answer_Content + ", answer_Date=" + answer_Date + "]";
	}
	
	
	
	
}


