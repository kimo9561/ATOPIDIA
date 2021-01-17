package model.Dto;

public class MemberDto {
	private int member_No;
	private String member_Id;
	private String member_Pw;
	private String member_Name;
	private String member_Email;
	private String member_RRN;
	private int grade_Code;
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDto(int member_No, String member_Id, String member_Pw, String member_Name, String member_Email,
			String member_RRN,int grade_Code) {
		super();
		this.member_No = member_No;
		this.member_Id = member_Id;
		this.member_Pw = member_Pw;
		this.member_Name = member_Name;
		this.member_Email = member_Email;
		this.member_RRN = member_RRN;
		this.grade_Code = grade_Code;
	}
	public MemberDto(String member_Name, String member_Email) {
		super();
		this.member_Name = member_Name;
		this.member_Email = member_Email;
	}
	

	public int getMember_No() {
		return member_No;
	}
	public void setMember_No(int member_No) {
		this.member_No = member_No;
	}
	public String getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(String member_Id) {
		this.member_Id = member_Id;
	}
	public String getMember_Pw() {
		return member_Pw;
	}
	public void setMember_Pw(String member_Pw) {
		this.member_Pw = member_Pw;
	}
	public String getMember_Name() {
		return member_Name;
	}
	public void setMember_Name(String member_Name) {
		this.member_Name = member_Name;
	}
	public String getMember_Email() {
		return member_Email;
	}
	public void setMember_Email(String member_Email) {
		this.member_Email = member_Email;
	}
	public String getMember_RRN() {
		return member_RRN;
	}
	public void setMember_RRN(String member_RRN) {
		this.member_RRN = member_RRN;
	}
	public int getGrade_Code() {
		return grade_Code;
	}
	public void setGrade_Code(int grade_Code) {
		this.grade_Code = grade_Code;
	}
	

	@Override
	public String toString() {
		return "MemberDto [member_No=" + member_No + ", member_Id=" + member_Id + ", member_Pw=" + member_Pw
				+ ", member_Name=" + member_Name + ", member_Email=" + member_Email + ", member_RRN=" + member_RRN
				+ ", grade_Code=" + grade_Code + "]";
	}

	

	
}
