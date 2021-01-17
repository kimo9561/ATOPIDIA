package controller;




import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.Biz.DiaryBiz;
import model.Biz.DiaryBizImpl;
import model.Biz.UserInfoBizImpl;
import model.Dao.DiaryDao;
import model.Dao.DiaryDaoImpl;
import model.Dto.DiaryDto;
import model.Dto.MyInfoDto;
import model.Dto.MyRecipeDto;


import static common.JDBCTemplate.*;
@WebServlet("/DiaryController")
public class DiaryController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //인코딩 처리
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
      //command값 담아주기
      String command = request.getParameter("command");
      System.out.println("[command : "+command+"]");
      DiaryBiz biz = new DiaryBizImpl();
      //command값 처리 (if문 이용)
      if(command.equals("diarylist")) { 
    	  //다이어리 목록 보여주기 기능
         int member_no = Integer.parseInt(request.getParameter("member_no"));
         
         List<DiaryDto> diarylist = biz.selectAllDiary(member_no);
         request.setAttribute("diarylist", diarylist);        
         System.out.println(diarylist.size());         
         dispatch("diary/mydiarylist.jsp", request, response);
         ///다이어리 관리 컨트롤러//
      } else if(command.equals("insertdiaryform")) {
    	  response.sendRedirect("diary/mydiary.jsp");      
      }else if(command.equals("insertdiary")) { 
    	  //다이어리 저장 기능
    	  String uploadPath = "diary/upload";
    	  ServletContext context = request.getServletContext();
    	  System.out.println("context의 경로 : "+context.getContextPath());
    	  System.out.println("context의 경로 : "+context.getRealPath(uploadPath));
    	  
    	  String realUploadPath = context.getRealPath(uploadPath);
          int maxSize = 1024 * 1024 * 10;// 한번에 올릴 수 있는 파일 용량 : 10M로 제한
          String name = "";
          String saveFolder = context.getRealPath(uploadPath);
          String subject = "";

          String fileName1 = "";// 중복처리된 이름
          String originalName1 = "";// 중복 처리전 실제 원본 이름
          long fileSize = 0;// 파일 사이즈
          String fileType = "";// 파일 타입

          MultipartRequest multi = null;
          try {
             // request,파일저장경로,용량,인코딩타입,중복파일명에 대한 기본 정책
             multi = new MultipartRequest(request, saveFolder, maxSize, "utf-8",
           		  new DefaultFileRenamePolicy());

             // form내의 input name="name" 인 녀석 value를 가져옴
             name = multi.getParameter("name");
             // name="subject" 인 녀석 value를 가져옴
             subject = multi.getParameter("subject");

             // 전송한 전체 파일이름들을 가져옴
             Enumeration files = multi.getFileNames();

             while (files.hasMoreElements()) {
                // form 태그에서 <input type="file" name="여기에 지정한 이름" />을 가져온다.
                String file1 = (String) files.nextElement();// 파일 input에 지정한 이름을 가져옴
                // 그에 해당하는 실재 파일 이름을 가져옴
                originalName1 = multi.getOriginalFileName(file1);
                // 파일명이 중복될 경우 중복 정책에 의해 뒤에 1,2,3 처럼 붙어 unique하게 파일명을 생성하는데
                // 이때 생성된 이름을 filesystemName이라 하여 그 이름 정보를 가져온다.(중복에 대한 처리)
                fileName1 = multi.getFilesystemName(file1);
                // 파일 타입 정보를 가져옴
                fileType = multi.getContentType(file1);
                // input file name에 해당하는 실재 파일을 가져옴
                File file = multi.getFile(file1);
                // 그 파일 객체의 크기를 알아냄
                fileSize = file.length();
             }
          } catch (Exception e) {
             e.printStackTrace();
          }
          String diary_Title = multi.getParameter("diary_Title");         
          String diary_Content_Morning = multi.getParameter("diary_Content_Morning");
          String diary_Content_Lunch = multi.getParameter("diary_Content_Lunch");
          String diary_Content_Dinner = multi.getParameter("diary_Content_Dinner");
          String diary_Content_Recipe = multi.getParameter("diary_Content_Recipe");
          int member_No = Integer.parseInt(request.getParameter("member_No"));
          String cdate = multi.getParameter("diary_Date");
          System.out.println(realUploadPath);
          DiaryDto dto = new DiaryDto();
          dto.setMember_No(member_No);
          dto.setDiary_Image_Name(fileName1);
          dto.setDiary_Image_RealName(originalName1);
          dto.setDiary_Content_Morning(diary_Content_Morning);
          dto.setDiary_Content_Lunch(diary_Content_Lunch);
          dto.setDiary_Content_Dinner(diary_Content_Dinner);
          dto.setDiary_Content_Recipe(diary_Content_Recipe);
          dto.setDiary_Title(diary_Title);
          dto.setDiary_UploadPath(realUploadPath);

          int res = biz.insertDiary(dto);
          if(res > 0) {
        	  int dno = biz.selectdiaryno();
        	  System.out.println(dno);
        	  dispatch("calendarcontroller.do?command=calendardiary&member_no="+member_No+"&diary_No="+dno+"&cdate="+cdate+"&title="+diary_Title, request, response);
          } else {
             String script = "<script type='text/javascript'>"+
                         "alert('저장 실패!!');"+"</script>";
             PrintWriter out = response.getWriter();
             out.print(script);
          }
      }else if(command.equals("detailform")) { //다이어리 수정 기능
          int diary_No = Integer.parseInt(request.getParameter("diary_No"));
          //데이터 넘어왔는지 체크!
          System.out.println("diary_No : "+diary_No);
          DiaryDto dto = biz.selectOneDiary(diary_No);
          
          request.setAttribute("ddto", dto);
          dispatch("diary/mydiarydetail.jsp", request, response);
       }else if(command.equals("updatediary")) {
    	  String uploadPath = "diary/upload";
    	  ServletContext context = request.getServletContext();
    	  System.out.println("context의 경로 : "+context.getContextPath());
    	  System.out.println("context의 경로 : "+context.getRealPath(uploadPath));
    	  
    	  String realUploadPath = context.getRealPath(uploadPath);
          int maxSize = 1024 * 1024 * 10;// 한번에 올릴 수 있는 파일 용량 : 10M로 제한
          String name = "";
          String saveFolder = context.getRealPath(uploadPath);
          String subject = "";
          
          String fileName1 = "";// 중복처리된 이름
          String originalName1 = "";// 중복 처리전 실제 원본 이름
          long fileSize = 0;// 파일 사이즈
          String fileType = "";// 파일 타입

          MultipartRequest multi = null;
          
          try {
             // request,파일저장경로,용량,인코딩타입,중복파일명에 대한 기본 정책
             multi = new MultipartRequest(request, saveFolder, maxSize, "utf-8",
           		  new DefaultFileRenamePolicy());

             // form내의 input name="name" 인 녀석 value를 가져옴
             name = multi.getParameter("name");
             // name="subject" 인 녀석 value를 가져옴
             subject = multi.getParameter("subject");

             // 전송한 전체 파일이름들을 가져옴
             Enumeration files = multi.getFileNames();

             while (files.hasMoreElements()) {
                // form 태그에서 <input type="file" name="여기에 지정한 이름" />을 가져온다.
                String file1 = (String) files.nextElement();// 파일 input에 지정한 이름을 가져옴
                // 그에 해당하는 실재 파일 이름을 가져옴
                originalName1 = multi.getOriginalFileName(file1);
                // 파일명이 중복될 경우 중복 정책에 의해 뒤에 1,2,3 처럼 붙어 unique하게 파일명을 생성하는데
                // 이때 생성된 이름을 filesystemName이라 하여 그 이름 정보를 가져온다.(중복에 대한 처리)
                fileName1 = multi.getFilesystemName(file1);
                // 파일 타입 정보를 가져옴
                fileType = multi.getContentType(file1);
                // input file name에 해당하는 실재 파일을 가져옴
                File file = multi.getFile(file1);
                // 그 파일 객체의 크기를 알아냄
                fileSize = file.length();
             }
          } catch (Exception e) {
             e.printStackTrace();
          }
         int diary_No = Integer.parseInt(multi.getParameter("diary_No"));
         int member_No = Integer.parseInt(multi.getParameter("member_No"));
         String diary_Title = multi.getParameter("diary_Title");
         String diary_Content_Morning = multi.getParameter("diary_Content_Morning");
         String diary_Content_Lunch = multi.getParameter("diary_Content_Lunch");
         String diary_Content_Dinner = multi.getParameter("diary_Content_Dinner");
         String diary_Content = multi.getParameter("diary_Content_Recipe");
         
         System.out.println("diary_No : "+diary_No);
         //이전 이미지 삭제 
         String oldImageName = multi.getParameter("previous_image");
         File oldFile = new File(saveFolder+"/"+oldImageName);
         oldFile.delete();
         
         DiaryDto dto = new DiaryDto();
         dto.setDiary_No(diary_No);
         dto.setMember_No(member_No);
         dto.setDiary_Title(diary_Title);
         dto.setDiary_Content_Morning(diary_Content_Morning);
         dto.setDiary_Content_Lunch(diary_Content_Lunch);
         dto.setDiary_Content_Dinner(diary_Content_Dinner);
         dto.setDiary_Content_Recipe(diary_Content);
         dto.setDiary_Image_Name(fileName1);
         dto.setDiary_Image_RealName(originalName1);
         dto.setDiary_UploadPath(realUploadPath);
         System.out.println(diary_Content_Morning);
         System.out.println(diary_Content_Lunch);
         System.out.println(diary_Content_Dinner);
         System.out.println(fileName1);
         System.out.println(originalName1);
         System.out.println(realUploadPath);
         //biz(트랜젝션 관리)에 넘겨줌
         int res = biz.updateDiary(dto);
         if(res > 0) {
            String script = "<script type='text/javascript'>"+
                  "alert('수정 성공!!');"+
                  "location.href='diarycontroller.do?command=diarylist&member_no="+member_No+"'"
                  +"</script>";
            PrintWriter out = response.getWriter();
            out.print(script);
         } else {
            String script = "<script type='text/javascript'>"+
                  "alert('수정 실패!!');"+
                  "location.href='diarycontroller.do?command=detailform&diary_No"+diary_No+"'"
                  +"</script>";
            PrintWriter out = response.getWriter();
            out.print(script);
         }

         
      } else if(command.equals("deleteform")) {
    	  int member_no = Integer.parseInt(request.getParameter("member_No"));
          String[] chks = request.getParameterValues("chk");
          for(int i=0; i<chks.length; i++) {
             System.out.println(chks[i]);
          }
          
          int res = biz.deleteAllDiary(chks);
          System.out.println(res);
          if(res == chks.length) {
			  request.setAttribute("chk", chks);
        	  dispatch("calendarcontroller.do?command=diaryChkdelete&member_no="+member_no, request, response);
          } else {
        	  
        	  jsResponse("삭제 실패!", "diarycontroller.do?command=diarylist&member_no="+member_no, response);

          }
       } else if(command.equals("deletediary")) {
    	   int member_no = Integer.parseInt(request.getParameter("member_No"));
    	   int diary_No = Integer.parseInt(request.getParameter("diary_No"));
         
           int res = biz.deleteDiary(diary_No);
           System.out.println(res);
           if(res>0) {
        	   dispatch("calendarcontroller.do?command=calendardiarydelete&diary_No="+diary_No+"&member_no="+member_no, request, response);
           } else {
         	  
         	  jsResponse("삭제 실패!", "diarycontroller.do?command=diarylist&member_no="+member_no, response);

           }
       }
    }
   //리스폰스 스크립트
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"
				+"alert('"+msg+"');"
				+"location.href = '"+url+"';"
				+"</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}
   //포워드 메소드 생성
   private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      RequestDispatcher dispatch = request.getRequestDispatcher(url);
      dispatch.forward(request, response);
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
            
   }

}