package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{
	//계정 연결 
	protected PasswordAuthentication getPasswordAuthentication() {
		
		
		
		return new PasswordAuthentication("ehldks2104@gmail.com","eunyou1108!!");//관리자계정 아이디 비밀번호 
	}
	
	
	
}
