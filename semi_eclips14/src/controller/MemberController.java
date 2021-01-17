package controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Biz.MemberBiz;
import model.Dto.MemberDto;
import util.Gmail;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public MemberController() { }
   private MemberBiz biz =new MemberBiz();

  
    
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");
   
      String command = request.getParameter("command");
      System.out.println(command);
      
      if(command.equals("loginform")) {
         response.sendRedirect("login/login.jsp");
      }      
      else if(command.equals("login")) {

         HttpSession session=request.getSession();

         String member_Id=request.getParameter("id");
         String member_Pw=request.getParameter("PW");
         
         MemberDto dto = biz.Login(member_Id, member_Pw);
    
         if(dto.getMember_Id() != null) {
            session.setAttribute("dto",dto);
            session.setMaxInactiveInterval(-1);
            
            response.sendRedirect("maincontroller.do?command=main");
          
             
         }else{
             jsResponse("아이디와 비밀번호를 확인해 주세요", "membercontroller.do?command=loginform", response);
          }
         
      }else if(command.equals("idfind")) {
    	  response.sendRedirect("login/idfind.jsp");
      }else if(command.equals("searchid")) {
    	  
    	  String Member_Name=request.getParameter("name");
    	  String memberEmail=request.getParameter("email");
    	  System.out.println(Member_Name+memberEmail);
    	  
    	  MemberDto res=biz.SearchId(Member_Name, memberEmail);
    	  if(res.getMember_Id() == null || res.getMember_Id().equals("")){
    		  jsResponse(Member_Name+"님 가입된 아이디가 없습니다." ,"login/idfind.jsp",response);
    	  }else {
    		  request.setAttribute("selectId", res);
        	  dispatch("login/id.jsp", request, response); 
    	  }
    	  
      }else if(command.equals("searchPw")) {
          String id = request.getParameter("id");
          String rrn = request.getParameter("rrn");
          System.out.println(id+rrn);
          MemberDto res = biz.searchPw(id, rrn);
          if(res.getMember_Pw() == null || res.getMember_Pw().equals("")){
        	  jsResponse(id+"은 가입된 아이디가 아닙니다" ,"login/pw.jsp",response);
          }else {
              request.setAttribute("selectPw", res);
              dispatch("login/pwfind.jsp", request, response);
          }

          
       }else if(command.equals("sendnum")) {
    	String member_Email = request.getParameter("email");
    	     System.out.println("www"+member_Email);
    	          
    	    String dbEmail = biz.selectEmail(member_Email);
    	          System.out.println(dbEmail);
    	  if(dbEmail == null || dbEmail.equals("")){
    	        PrintWriter out = response.getWriter();
    	         String msg = "가입된 이메일이 아닙니다.";
    	          out.print(msg);
    	          out.flush();
    	          out.close();
    	           
    	  }else {
    	               //인증번호 난수
    	   int ramdom = 0;
    	   ramdom = (int)Math.floor((Math.random()*(99999-10000+1)))+10000;
    	                  
    	   String subject = "ATOPDIA에서 보낸 인증번호 입니다.";// 메일 제목
    	  String message = Integer.toString(ramdom); //메일 내용
    	                  
    	                  //메일전송
    	  Properties p = new Properties(); 
    	  p.put("mail.smtp.host","gmail-smtp.l.google.com");         
    	  p.put("mail.smtp.port", "465");
    	  p.put("mail.smtp.starttls.enable", "true");
    	  p.put("mail.smtp.auth", "true");
    	  p.put("mail.smtp.debug", "true");
    	  p.put("mail.smtp.socketFactory.port", "465");
    	  p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	  p.put("mail.smtp.socketFactory.fallback", "false");
    	       try{
    	          Authenticator auth = new Gmail();
    	          Session ses = Session.getInstance(p, auth);
    	           ses.setDebug(true);
    	                      
    	            MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
    	             msg.setSubject(subject); // 제목
    	                      
    	             Address fromAddr = new InternetAddress("atopidia@naver.com");
    	             msg.setFrom(fromAddr); // 보내는 사람
    	                      
    	           Address toAddr = new InternetAddress(member_Email);
    	           msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람
    	                      
    	             msg.setContent("ATOPDIA에서 보낸 인증 번호: "+message, "text/html;charset=UTF-8"); // 내용과 인코딩
    	                      
    	           Transport.send(msg); // 전송
    	      } catch(Exception e){
    	         e.printStackTrace();
    	       System.out.println("error");
    	     }  
    	       
    	       PrintWriter out = response.getWriter();   
    	         out.print(ramdom);
    	         out.flush();
    	         out.close();
    	    	}
    }else if(command.equals("send")) {
    	String member_Email = request.getParameter("email");
	     System.out.println(member_Email);
	          
	    String dbEmail = biz.selectEmail(member_Email);
	          
	  if(dbEmail == null || dbEmail.equals("")){
	        PrintWriter out = response.getWriter();
	         String msg = "존재하지않는이메일";
	          out.print(msg);
	          out.flush();
	          out.close();
	           
	  }else {
	               //인증번호 난수
	   int ramdom = 0;
	   ramdom = (int)Math.floor((Math.random()*(99999-10000+1)))+10000;
	                  
	   String subject = "ATOPDIA에서 보낸 인증번호 입니다.";// 메일 제목
	  String message = Integer.toString(ramdom); //메일 내용
	                  
	                  //메일전송
	  Properties p = new Properties(); 
	  p.put("mail.smtp.host","gmail-smtp.l.google.com");         
	  p.put("mail.smtp.port", "465");
	  p.put("mail.smtp.starttls.enable", "true");
	  p.put("mail.smtp.auth", "true");
	  p.put("mail.smtp.debug", "true");
	  p.put("mail.smtp.socketFactory.port", "465");
	  p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	  p.put("mail.smtp.socketFactory.fallback", "false");
	       try{
	          Authenticator auth = new Gmail();
	          Session ses = Session.getInstance(p, auth);
	           ses.setDebug(true);
	                      
	            MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
	             msg.setSubject(subject); // 제목
	                      
	             Address fromAddr = new InternetAddress("atopidia@naver.com");
	             msg.setFrom(fromAddr); // 보내는 사람
	                      
	           Address toAddr = new InternetAddress(member_Email);
	           msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람
	                      
	             msg.setContent("ATOPDIA에서 보낸 인증 번호: "+message, "text/html;charset=UTF-8"); // 내용과 인코딩
	                      
	           Transport.send(msg); // 전송
	      } catch(Exception e){
	         e.printStackTrace();
	       System.out.println("error");
	     }          	  
    	  
	  }
    
    
    
    }
      else if(command.equals("signupform")) {
         response.sendRedirect("login/JoinForm.jsp");
         
      }else if(command.equals("signup")) {
      
          String member_Id=request.getParameter("userId");
          String member_Pw=request.getParameter("userPw");
          String member_Email=request.getParameter("userMail");
          String member_Name=request.getParameter("userName");
          String member_RRN=request.getParameter("unum1")+"-"+request.getParameter("unum2");
          System.out.println(member_RRN);
          MemberDto dto =new MemberDto();
     
          dto.setMember_Id(member_Id);
          dto.setMember_Pw(member_Pw);
          dto.setMember_Email(member_Email);
          dto.setMember_Name(member_Name);
          dto.setMember_RRN(member_RRN);

          boolean res=biz.InsertUser(dto);
          if(res) {
             jsResponse(member_Name+"님 회원가입을 축하합니다.","membercontroller.do?command=loginform",response);
          }else {
             jsResponse(member_Name+"님 회원가입에 실패하였습니다.","membercontroller.do?command=signupform",response);
          }
          


         
         
      }else if(command.equals("IdChk")){
        String member_Id=request.getParameter("id");
        System.out.println(member_Id);
        boolean res=biz.IdChk(member_Id);
        
        System.out.println(res);
        
        //파라미터값으로 페이지를 넘김
        dispatch("login/IdChk.jsp?isnotused="+res,request,response);
      }
      else if(command.equals("logout")) {
          HttpSession session=request.getSession();
          session.invalidate();
          response.sendRedirect("maincontroller.do?command=mainlogout");
       }else if(command.equals("UserListAll")) {
    	   List<MemberDto>list = biz.selectAll();
    	   
    	   request.setAttribute("list",list);
    	   dispatch("login/UserListAll.jsp", request, response);
    	 
       }else if(command.equals("updateuser")) {
         int memberno=Integer.parseInt(request.getParameter("memberno"));
         MemberDto dto =biz.selectOne(memberno);
         System.out.println(dto.getGrade_Code());
        request.setAttribute("member", dto);
        dispatch("login/UpdateUser.jsp",request,response);
         
       }else if(command.equals("update")) {
    	  int memberno=Integer.parseInt(request.getParameter("memberno"));
    	  System.out.println("command:"+command);
    	  String Member_Pw=request.getParameter("updateval");

    	  MemberDto dto = new MemberDto();
    	  dto.setMember_No(memberno);
    	  
    	  dto.setMember_Pw(Member_Pw);
    	  
    	  boolean  res=biz.UpdateUser(dto);
    	 
    	 if(res) {
    		 jsResponse("수정 성공" ,"membercontroller.do?command=UserListAll",response);
    	 }else {
    		 jsResponse("수정 실패","membercontroller.do?command=update",response);
    	 }
    	
      }else if(command.equals("delete")) {
    	  int memberno=Integer.parseInt(request.getParameter("memberno"));
    	  System.out.println(memberno);
    	  
    	  boolean res=biz.DeleteUser(memberno);
    	  if(res) {
    		 jsResponse("탈퇴 성공","membercontroller.do?command=logout",response);
    	  }
      }else if(command.equals("deletemember")) {
    	  String membern = request.getParameter("chk");
    	  String[] num = membern.split("/");
    	
    	  boolean res = biz.multiDelete(num);
    	  System.out.println(res);
    	  if(res) {
     		 jsResponse("탈퇴 처리완료","membercontroller.do?command=UserListAll",response);
     	  }
      }else if(command.equals("sendemail")) {
          String member_Email = request.getParameter("email");
          System.out.println(member_Email);               
          //인증번호 난수
          int ramdom = 0;
          ramdom = (int)Math.floor((Math.random()*(99999-10000+1)))+10000;
          
          String subject = "ATOPDIA에서 보낸 인증번호 입니다.";// 메일 제목
          String message = Integer.toString(ramdom); //메일 내용
          
          //메일전송
          Properties p = new Properties(); 
          p.put("mail.smtp.host","gmail-smtp.l.google.com");         
          p.put("mail.smtp.port", "465");
          p.put("mail.smtp.starttls.enable", "true");
          p.put("mail.smtp.auth", "true");
          p.put("mail.smtp.debug", "true");
          p.put("mail.smtp.socketFactory.port", "465");
          p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
          p.put("mail.smtp.socketFactory.fallback", "false");
          try{
             Authenticator auth = new Gmail();
             Session ses = Session.getInstance(p, auth);
             ses.setDebug(true);
              
              MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
              msg.setSubject(subject); // 제목
              
              Address fromAddr = new InternetAddress("atopidia@naver.com");
              msg.setFrom(fromAddr); // 보내는 사람
              
              Address toAddr = new InternetAddress(member_Email);
              msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람
              
              msg.setContent("ATOPDIA에서 보낸 인증 번호: "+message, "text/html;charset=UTF-8"); // 내용과 인코딩
              
              Transport.send(msg); // 전송
          } catch(Exception e){
              e.printStackTrace();
              System.out.println("error");
          }
          PrintWriter out = response.getWriter();   
          out.print(ramdom);
          out.flush();
          out.close();
          }else if(command.equals("adminpage")) {
        	  response.sendRedirect("admin_board/adminMypage.jsp");
          }else if(command.equals("changepw")){
        	  response.sendRedirect("login/UpdateUser.jsp");
          }

      }
   
   





   private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
      String s = "<script type='text/javascript'>"
            +"alert('"+msg+"');"
            +"location.href = '"+url+"';"
            +"</script>";
      PrintWriter out = response.getWriter();
      out.print(s);
   }

   private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      RequestDispatcher dispatch = request.getRequestDispatcher(url);
      dispatch.forward(request, response);
   
   }   

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}