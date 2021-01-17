package model.Dto;

import java.sql.Date;

public class AdminRecipeDto {
	private int recipe_No;
	private int member_No;
	private String recipe_Writer;
	private String recipe_Title;
	private String recipe_Ingredient;
	private String recipe_Text;
	private Date recipe_Date;
	private int recipe_Hits;
	private String recipe_Save;
	private int grade_Code;
	private String recipe_Image_Name;
	private String recipe_Image_Realname;
	private String recipe_Image_Uploadpath;
	
	
	
	public AdminRecipeDto() {
		super();
	}

	
	


	public AdminRecipeDto(int recipe_No, int member_No, String recipe_Writer, String recipe_Title,
			String recipe_Ingredient, String recipe_Text, Date recipe_Date, int recipe_Hits, String recipe_Save,
			int grade_Code, String recipe_Image_Name, String recipe_Image_Realname, String recipe_Image_Uploadpath) {
		super();
		this.recipe_No = recipe_No;
		this.member_No = member_No;
		this.recipe_Writer = recipe_Writer;
		this.recipe_Title = recipe_Title;
		this.recipe_Ingredient = recipe_Ingredient;
		this.recipe_Text = recipe_Text;
		this.recipe_Date = recipe_Date;
		this.recipe_Hits = recipe_Hits;
		this.recipe_Save = recipe_Save;
		this.grade_Code = grade_Code;
		this.recipe_Image_Name = recipe_Image_Name;
		this.recipe_Image_Realname = recipe_Image_Realname;
		this.recipe_Image_Uploadpath = recipe_Image_Uploadpath;
	}





	public AdminRecipeDto(int member_No, String recipe_Writer, String recipe_Title, String recipe_Ingredient,
			String recipe_Text, String recipe_Image_Name, String recipe_Image_Realname,
			String recipe_Image_Uploadpath) {
		super();
		this.member_No = member_No;
		this.recipe_Writer = recipe_Writer;
		this.recipe_Title = recipe_Title;
		this.recipe_Ingredient = recipe_Ingredient;
		this.recipe_Text = recipe_Text;
		this.recipe_Image_Name = recipe_Image_Name;
		this.recipe_Image_Realname = recipe_Image_Realname;
		this.recipe_Image_Uploadpath = recipe_Image_Uploadpath;
	}

	

	public AdminRecipeDto(int recipe_No, String recipe_Title, String recipe_Ingredient, String recipe_Text,
			String recipe_Image_Name, String recipe_Image_Realname, String recipe_Image_Uploadpath) {
		super();
		this.recipe_No = recipe_No;
		this.recipe_Title = recipe_Title;
		this.recipe_Ingredient = recipe_Ingredient;
		this.recipe_Text = recipe_Text;
		this.recipe_Image_Name = recipe_Image_Name;
		this.recipe_Image_Realname = recipe_Image_Realname;
		this.recipe_Image_Uploadpath = recipe_Image_Uploadpath;
	}





	public AdminRecipeDto(int recipe_No, int recipe_Hits) {
		super();
		this.recipe_No = recipe_No;
		this.recipe_Hits = recipe_Hits;
	}


	public int getRecipe_No() {
		return recipe_No;
	}

	public void setRecipe_No(int recipe_No) {
		this.recipe_No = recipe_No;
	}

	public int getMember_No() {
		return member_No;
	}

	public void setMember_No(int member_No) {
		this.member_No = member_No;
	}

	public String getRecipe_Writer() {
		return recipe_Writer;
	}

	public void setRecipe_Writer(String recipe_Id) {
		this.recipe_Writer = recipe_Id;
	}

	public String getRecipe_Title() {
		return recipe_Title;
	}

	public void setRecipe_Title(String recipe_Title) {
		this.recipe_Title = recipe_Title;
	}

	public String getRecipe_Ingredient() {
		return recipe_Ingredient;
	}

	public void setRecipe_Ingredient(String recipe_Ingredient) {
		this.recipe_Ingredient = recipe_Ingredient;
	}

	public String getRecipe_Text() {
		return recipe_Text;
	}

	public void setRecipe_Text(String recipe_Text) {
		this.recipe_Text = recipe_Text;
	}

	public Date getRecipe_Date() {
		return recipe_Date;
	}

	public void setRecipe_Date(Date recipe_Date) {
		this.recipe_Date = recipe_Date;
	}

	public int getRecipe_Hits() {
		return recipe_Hits;
	}

	public void setRecipe_Hits(int recipe_Hits) {
		this.recipe_Hits = recipe_Hits;
	}

	public String getRecipe_Save() {
		return recipe_Save;
	}

	public void setRecipe_Save(String recipe_Save) {
		this.recipe_Save = recipe_Save;
	}

	public int getGrade_Code() {
		return grade_Code;
	}

	public void setGrade_Code(int grade_Code) {
		this.grade_Code = grade_Code;
	}





	public String getRecipe_Image_Name() {
		return recipe_Image_Name;
	}





	public void setRecipe_Image_Name(String recipe_Image_Name) {
		this.recipe_Image_Name = recipe_Image_Name;
	}





	public String getRecipe_Image_Realname() {
		return recipe_Image_Realname;
	}





	public void setRecipe_Image_Realname(String recipe_Image_Realname) {
		this.recipe_Image_Realname = recipe_Image_Realname;
	}





	public String getRecipe_Image_Uploadpath() {
		return recipe_Image_Uploadpath;
	}





	public void setRecipe_Image_Uploadpath(String recipe_Image_Uploadpath) {
		this.recipe_Image_Uploadpath = recipe_Image_Uploadpath;
	}

	
	
	


	
}
