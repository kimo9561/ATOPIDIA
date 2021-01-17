package controller;

import java.io.File;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
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

import model.Biz.DiaryBizImpl;
import model.Biz.UserRecipeBiz;
import model.Dto.AdminRecipeDto;
import model.Dto.UserInfoAnswerDto;
import model.Dto.UserRecipeAnswerDto;
import model.Dto.UserRecipeDto;

@WebServlet("/UserRecipeController")
public class UserRecipeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserRecipeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
			
			String command = request.getParameter("command");
			System.out.println(command);
			UserRecipeBiz biz = new UserRecipeBiz();
			DiaryBizImpl dbiz = new DiaryBizImpl();
			if(command.equals("writeform")) {
				System.out.println("test");
				response.sendRedirect("user_board/UserWriteRecipe.jsp");
				
			}else if(command.equals("write")) {
				
				
				
				String uploadPath = "img";
		        ServletContext context = request.getServletContext();
		        System.out.println("context의 경로 : "+context.getContextPath());
		        System.out.println("context의 경로 : "+context.getRealPath(uploadPath));

		        String realUploadPath = context.getRealPath(uploadPath);
		        int maxSize = 1024 * 1024 * 10;// 한번에 올릴 수 있는 파일 용량 : 10M로 제한
		        String name = "";
		        String recipe_Image_Uploadpath = context.getRealPath(uploadPath);
		        String subject = "";

		        String recipe_Image_Name = "";// 중복처리된 이름
		        String recipe_Image_Realname = "";// 중복 처리전 실제 원본 이름
		        long fileSize = 0;// 파일 사이즈
		        String fileType = "";// 파일 타입

		        MultipartRequest multi = null;
		        try {
		            // request,파일저장경로,용량,인코딩타입,중복파일명에 대한 기본 정책
		            multi = new MultipartRequest(request, recipe_Image_Uploadpath, maxSize, "utf-8",
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
		               recipe_Image_Realname = multi.getOriginalFileName(file1);
		               // 파일명이 중복될 경우 중복 정책에 의해 뒤에 1,2,3 처럼 붙어 unique하게 파일명을 생성하는데
		               // 이때 생성된 이름을 filesystemName이라 하여 그 이름 정보를 가져온다.(중복에 대한 처리)
		               recipe_Image_Name = multi.getFilesystemName(file1);
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
				
				System.out.println("test");
				int member_no = Integer.parseInt( multi.getParameter("member_No"));
				String writer = multi.getParameter("recipe_author");
				String title =  multi.getParameter("recipe_title");
				String ingredient =  multi.getParameter("ingredient");
				String content =  multi.getParameter("content");
				
				UserRecipeDto dto = new UserRecipeDto(member_no,writer,title,ingredient,content, recipe_Image_Name, recipe_Image_Realname, recipe_Image_Uploadpath);
				System.out.println(title);
				boolean res = biz.insert(dto);
				
				if(res) {
					jsResponse("새 글 작성 성공","userrecipecontroller.do?command=list",response);
				}else {
					jsResponse("새 글 작성 실패","userrecipecontroller.do?command=writeform",response);
				}
		
			}else if(command.equals("list")) {
				
				List<UserRecipeDto> list = biz.selectAll();
				request.setAttribute("list", list);
				dispatch("user_board/UserRecipeMain.jsp", request, response);
				
				
			}else if(command.equals("detail")) {
				int recipe_No = Integer.parseInt(request.getParameter("recipe_No"));
				List<UserRecipeAnswerDto> ans = biz.answerlist(recipe_No);
				boolean res = biz.addHits(recipe_No);
				UserRecipeDto dto = biz.selectOne(recipe_No);			
				
				System.out.println(ans.size());
				
				
				request.setAttribute("list", dto);
				request.setAttribute("ans", ans);
				dispatch("user_board/UserRecipe.jsp", request, response);
				System.out.println(dto.getRecipe_Image_Realname());
				
			}else if(command.equals("delete")) {
				int recipe_No = Integer.parseInt(request.getParameter("recipe_No")) ;
				String member_Id = request.getParameter("member_Id");
				String recipe_writer = request.getParameter("recipe_writer");
				int grade_code = Integer.parseInt(request.getParameter("grade_code"));
				
				if(member_Id.equals(recipe_writer) || grade_code == 1) {
					boolean res = biz.delete(recipe_No);
					if(res) {
						jsResponse("글 삭제 성공","userrecipecontroller.do?command=list",response);
					}
				}else {
					jsResponse("작성한 회원만 글을 삭제 할 수있습니다","userrecipecontroller.do?command=detail&recipe_No="+recipe_No,response);
				}
		
				
				
			}else if(command.equals("updateform")) {
				
			
				int recipe_No = Integer.parseInt(request.getParameter("recipe_No"));
				String member_Id = request.getParameter("member_Id");
				String recipe_writer = request.getParameter("recipe_writer");
				UserRecipeDto dto = biz.selectOne(recipe_No);
			
				if(member_Id.equals(recipe_writer) ){
					request.setAttribute("list", dto);
					dispatch("user_board/UserUpdateRecipe.jsp", request, response);
					}else {
					jsResponse("작성한 회원만 글을 수정 할 수있습니다","userrecipecontroller.do?command=detail&recipe_No="+recipe_No,response);
					}
				 
		
		
			
			}else if(command.equals("update")) {
				System.out.println(command);
				String uploadPath = "img";
		        ServletContext context = request.getServletContext();
		        System.out.println("context의 경로 : "+context.getContextPath());
		        System.out.println("context의 경로 : "+context.getRealPath(uploadPath));

		        String realUploadPath = context.getRealPath(uploadPath);
		        int maxSize = 1024 * 1024 * 10;// 한번에 올릴 수 있는 파일 용량 : 10M로 제한
		        String name = "";
		        String recipe_Image_Uploadpath = context.getRealPath(uploadPath);
		        String subject = "";

		        String recipe_Image_Name = "";// 중복처리된 이름
		        String recipe_Image_Realname = "";// 중복 처리전 실제 원본 이름
		        long fileSize = 0;// 파일 사이즈
		        String fileType = "";// 파일 타입
		        
		        MultipartRequest multi = null;

		          try {
		             // request,파일저장경로,용량,인코딩타입,중복파일명에 대한 기본 정책
		             multi = new MultipartRequest(request, recipe_Image_Uploadpath, maxSize, "utf-8",
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
			               recipe_Image_Realname = multi.getOriginalFileName(file1);
			               // 파일명이 중복될 경우 중복 정책에 의해 뒤에 1,2,3 처럼 붙어 unique하게 파일명을 생성하는데
			               // 이때 생성된 이름을 filesystemName이라 하여 그 이름 정보를 가져온다.(중복에 대한 처리)
			               recipe_Image_Name = multi.getFilesystemName(file1);
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

				int recipe_No = Integer.parseInt(multi.getParameter("recipe_No"));
				String title = multi.getParameter("recipe_title");
				String ingredient = multi.getParameter("ingredient");
				String content = multi.getParameter("content");
				
				//이전 이미지 삭제 
		         String oldImageName = multi.getParameter("previous_image");
		         File oldFile = new File(recipe_Image_Uploadpath+"/"+oldImageName);
		         oldFile.delete();
		         
				
		       UserRecipeDto dto = new UserRecipeDto(recipe_No,title,ingredient,content, recipe_Image_Name, recipe_Image_Realname, recipe_Image_Uploadpath);
				boolean res = biz.update(dto);
				
				if(res) {
					jsResponse("글 수정 성공","userrecipecontroller.do?command=detail&recipe_No="+recipe_No,response);
				}else {
					jsResponse("글 수정 실패","userrecipecontroller.do?command=updateform&recipe_No="+recipe_No,response);
				}
				
				
				
			}else if(command.equals("answer")) {
				int recipe_No = Integer.parseInt(request.getParameter("recipe_No"));
				String answer_writer = request.getParameter("answer_writer");
				String answer_content = request.getParameter("answer_content");
				
				UserRecipeAnswerDto dto = new UserRecipeAnswerDto(recipe_No,answer_writer, answer_content);
				
				boolean res = biz.insertAnswer(dto);
				
				if(res) {
					jsResponse("답글 작성 성공","userrecipecontroller.do?command=detail&recipe_No="+recipe_No,response);
				}else {
					jsResponse("답글 작성 실패","userrecipecontroller.do?command=detail&recipe_No="+recipe_No,response);
				}
			}else if(command.equals("deleteanswer")) {
				int recipe_No = Integer.parseInt(request.getParameter("recipe_No"));
				int answer_No = Integer.parseInt(request.getParameter("answer_No"));
				String answer_Writer = request.getParameter("answer_Writer");
				String member_Id = request.getParameter("member_Id");
				int grade_code = Integer.parseInt(request.getParameter("grade_code"));
				
				if(answer_Writer.equals(member_Id)) {				
					boolean res = biz.deleteAnswer(recipe_No,answer_No);
						if(res) {
					jsResponse("답글 삭제 성공","userrecipecontroller.do?command=detail&recipe_No="+recipe_No,response);
							}
				}else {
					jsResponse("작성한 회원만 답글을 삭제 할 수 있습니다","userrecipecontroller.do?command=detail&recipe_No="+recipe_No,response);
				}
			}else if(command.equals("updateanswerform")) {
				int recipe_No = Integer.parseInt(request.getParameter("recipe_No"));
				int answer_No = Integer.parseInt(request.getParameter("answer_No"));
				String member_Id = request.getParameter("member_Id");
				String answer_writer = request.getParameter("answer_writer");
				
				List<UserRecipeAnswerDto> ans = biz.answerlist(recipe_No);
				UserRecipeDto dto = biz.selectOne(recipe_No);			
				UserInfoAnswerDto anw = biz.answerSelectOne(recipe_No, answer_No);
				System.out.println(ans.size());
				System.out.println(anw.toString());
				
				

				if(member_Id.equals(answer_writer) ){
					request.setAttribute("list", dto);
					request.setAttribute("ans", ans);
					request.setAttribute("anw", anw);
					
					dispatch("user_board/UserUpdateRecipeAnswer.jsp", request, response);
					}else {
					jsResponse("작성한 회원만 글을 수정 할 수있습니다","userrecipecontroller.do?command=detail&recipe_No="+recipe_No,response);
					}
				
	
			}else if(command.equals("updateanswer")) {
				int recipe_No = Integer.parseInt(request.getParameter("recipe_No"));
				int answer_No = Integer.parseInt(request.getParameter("answer_No"));
				String answer_Writer = request.getParameter("answer_Writer");
				String updateContent = request.getParameter("updateanswer_content");
				
				UserRecipeAnswerDto dto = new UserRecipeAnswerDto(recipe_No,answer_No,answer_Writer,updateContent);
				
				boolean res = biz.updateAnswer(dto);
				
				if(res) {
					jsResponse("답글 수정 성공","userrecipecontroller.do?command=detail&recipe_No="+recipe_No,response);
				}else {
					jsResponse("답글 수정 실패","userrecipecontroller.do?command=updateanswerform&recipe_No="+recipe_No+"&answer_No="+answer_No,response);
				}

			}else if(command.equals("logout")) {
		    	  jsResponse("로그인을 해주세요","maincontroller.do?command=mainlogout", response);
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

	

