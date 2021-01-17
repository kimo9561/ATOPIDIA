package controller;

import java.io.File;
import java.io.IOException;

import java.io.PrintWriter;
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

import model.Biz.AdminInfoBizImpl;
import model.Biz.DiaryBizImpl;
import model.Dto.AdminInfoAnswerDto;
import model.Dto.AdminInfoDto;
import model.Dto.AdminRecipeAnswerDto;
import model.Dto.AdminRecipeDto;
import model.Dto.MyInfoDto;
import model.Dto.UserInfoAnswerDto;
import model.Dto.UserInfoDto;

public class AdminInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminInfoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		System.out.println(command);

		AdminInfoBizImpl biz = new AdminInfoBizImpl();
		DiaryBizImpl dbiz = new DiaryBizImpl();

		if (command.equals("list")) {

			int currentPage = 1; // 현재페이지

			String page = request.getParameter("page");

			if (page != null) {
				currentPage = Integer.parseInt(page);
			}

			try {
				request.setAttribute("pageDto", biz.service(currentPage));
				RequestDispatcher dis = request.getRequestDispatcher("admin_board/adminInfo_home.jsp");
				dis.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("answer")) {

			int info_No = Integer.parseInt(request.getParameter("info_No"));
			String answer_writer = request.getParameter("answer_writer");
			String answer_content = request.getParameter("answer_content");
			System.out.println(info_No);
			AdminInfoAnswerDto dto = new AdminInfoAnswerDto(info_No, answer_writer, answer_content);

			int res = biz.insertAnswer(dto);

			if (res > 0) {
				jsResponse("답글 작성 성공", "admininfocontroller.do?command=detail&info_No=" + info_No, response);
			} else {
				jsResponse("답글 작성 실패", "adminfocontroller.do?command=detail&info_No=" + info_No, response);
			}

		} else if (command.equals("writeform")) {
			response.sendRedirect("admin_board/admininfo_write.jsp");

		} else if (command.equals("boardwrite")) {
			String uploadPath = "img";
			ServletContext context = request.getServletContext();
			System.out.println("context의 경로 : " + context.getContextPath());
			System.out.println("context의 경로 : " + context.getRealPath(uploadPath));

			String realUploadPath = context.getRealPath(uploadPath);
			int maxSize = 1024 * 1024 * 10;// 한번에 올릴 수 있는 파일 용량 : 10M로 제한
			String name = "";
			String info_Image_Uploadpath = context.getRealPath(uploadPath);
			String subject = "";

			String info_Image_Name = "";// 중복처리된 이름
			String info_Image_Realname = "";// 중복 처리전 실제 원본 이름
			long fileSize = 0;// 파일 사이즈
			String fileType = "";// 파일 타입

			MultipartRequest multi = null;
			try {
				// request,파일저장경로,용량,인코딩타입,중복파일명에 대한 기본 정책
				multi = new MultipartRequest(request, info_Image_Uploadpath, maxSize, "utf-8",
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
					info_Image_Realname = multi.getOriginalFileName(file1);
					// 파일명이 중복될 경우 중복 정책에 의해 뒤에 1,2,3 처럼 붙어 unique하게 파일명을 생성하는데
					// 이때 생성된 이름을 filesystemName이라 하여 그 이름 정보를 가져온다.(중복에 대한 처리)
					info_Image_Name = multi.getFilesystemName(file1);
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

			String title = multi.getParameter("info_Title");
			String content = multi.getParameter("info_Text");
			String writer = multi.getParameter("info_Writer");
			int member_No = Integer.parseInt(multi.getParameter("member_No"));
			
			System.out.println(member_No);
			System.out.println(info_Image_Realname);
			System.out.println(info_Image_Name);
			System.out.println(info_Image_Uploadpath);

			int member_no = Integer.parseInt(request.getParameter("member_No"));

			AdminInfoDto admininfowrite = new AdminInfoDto(member_No, writer, title, content, info_Image_Name,
					info_Image_Realname, info_Image_Uploadpath);

			boolean res = biz.insertInfo(admininfowrite);

			if (res) {
				jsResponse("글 작성 성공", "admininfocontroller.do?command=list", response);
			} else {
				jsResponse("글 작성 실패", "admininfocontroller.do?command=boardwrite", response);
			}

		} else if (command.equals("detail")) {
			int info_No = Integer.parseInt(request.getParameter("info_No"));
			List<AdminInfoAnswerDto> ans = biz.answerlist(info_No);
			boolean res = biz.addHits(info_No);
			AdminInfoDto inf = biz.selectOne(info_No);

			System.out.println("test----------" + inf);
			request.setAttribute("list", inf);
			request.setAttribute("ans", ans);

			dispatch("admin_board/adminInfo_view.jsp", request, response);

		} else if (command.equals("delete")) {
			int no = Integer.parseInt(request.getParameter("info_No"));
			boolean res = biz.deleteInfo(no);
			System.out.println("delete");
			if (res) {
				jsResponse("글 삭제 성공", "admininfocontroller.do?command=list", response);
			} else {
				jsResponse("글 삭제 실패", "admininfocontroller.do?command=list", response);
			}
		} else if (command.equals("updateInfoForm")) {
			int infono = Integer.parseInt(request.getParameter("info_No"));
			AdminInfoDto one = biz.selectOne(infono);
			request.setAttribute("one", one);
			dispatch("admin_board/adminInfo_update.jsp", request, response);


		} else if (command.equals("infoupdate")) {
			String uploadPath = "img";
	          ServletContext context = request.getServletContext();
	          System.out.println("context의 경로 : "+context.getContextPath());
	          System.out.println("context의 경로 : "+context.getRealPath(uploadPath));

	          String realUploadPath = context.getRealPath(uploadPath);
	          int maxSize = 1024 * 1024 * 10;// 한번에 올릴 수 있는 파일 용량 : 10M로 제한
	          String name = "";
	          String info_Image_Uploadpath = context.getRealPath(uploadPath);
	          String subject = "";

	          String info_Image_Name = "";// 중복처리된 이름
	          String info_Image_Realname = "";// 중복 처리전 실제 원본 이름
	          long fileSize = 0;// 파일 사이즈
	          String fileType = "";// 파일 타입

	          MultipartRequest multi = null;
	          try {
	              // request,파일저장경로,용량,인코딩타입,중복파일명에 대한 기본 정책
	              multi = new MultipartRequest(request, info_Image_Uploadpath, maxSize, "utf-8",
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
	                 info_Image_Realname = multi.getOriginalFileName(file1);
	                 // 파일명이 중복될 경우 중복 정책에 의해 뒤에 1,2,3 처럼 붙어 unique하게 파일명을 생성하는데
	                 // 이때 생성된 이름을 filesystemName이라 하여 그 이름 정보를 가져온다.(중복에 대한 처리)
	                 info_Image_Name = multi.getFilesystemName(file1);
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
	         int info_No = Integer.parseInt(request.getParameter("info_No"));
	         int member_No = Integer.parseInt(multi.getParameter("member_No"));
	         System.out.println(info_No);
	         String title = multi.getParameter("info_Title");
	         String text = multi.getParameter("info_Text");
	         
	         AdminInfoDto dto = new AdminInfoDto(info_No, member_No , title, text, info_Image_Name, info_Image_Realname, info_Image_Uploadpath);
	         
	         boolean res = biz.updateInfo(dto);
	         
	         if(res) {
	            jsResponse("글 수정 성공", "admininfocontroller.do?command=list", response);
	         }else {
	            jsResponse("글 수정 실패", "admininfocontroller.do?command=updateInfoForm&info_No="+info_No, response);
	         }
	         
	           
	         
		}else if(command.equals("answer")) {
			int info_No = Integer.parseInt(request.getParameter("info_No"));
			String answer_writer = request.getParameter("answer_writer");
			String answer_content = request.getParameter("answer_content");
			
			AdminInfoAnswerDto dto = new AdminInfoAnswerDto(info_No,answer_writer, answer_content);
			
			int res = biz.insertAnswer(dto);
			
			if(res>0) {
				jsResponse("답글 작성 성공","admininfocontroller.do?command=detail&info_No="+info_No,response);
			}else {
				jsResponse("답글 작성 실패","admininfocontroller.do?command=detail&info_No="+info_No,response);
			}
			
			
		}else if(command.equals("deleteanswer")) {
			int info_No = Integer.parseInt(request.getParameter("info_No"));
			int answer_No = Integer.parseInt(request.getParameter("answer_No"));
			
			boolean res = biz.deleteAnswer(info_No,answer_No);
			
			if(res) {
				jsResponse("답글 삭제 성공","admininfocontroller.do?command=detail&info_No="+info_No,response);
			}else {
				jsResponse("답글 삭제 실패","admininfocontroller.do?command=detail&info_No="+info_No,response);
			}
			
		}else if(command.equals("updateanswerform")) {
			int info_No = Integer.parseInt(request.getParameter("info_No"));
			int answer_No = Integer.parseInt(request.getParameter("answer_No"));
			
			List<AdminInfoAnswerDto> ans = biz.answerlist(info_No);
			AdminInfoDto dto = biz.selectOne(info_No);
			AdminInfoAnswerDto anw = biz.answerSelectOne(info_No, answer_No);
			System.out.println(ans.size());
			System.out.println(anw.toString());
			
			
			request.setAttribute("list", dto);
			request.setAttribute("ans", ans);
			request.setAttribute("anw", anw);
			
			dispatch("admin_board/AdminUpdateInfoAnswer.jsp", request, response);

		}else if(command.equals("updateanswer")) {
			int info_No = Integer.parseInt(request.getParameter("info_No"));
			int answer_No = Integer.parseInt(request.getParameter("answer_No"));
			String answer_Writer = request.getParameter("answer_Writer");
			String updateContent = request.getParameter("updateanswer_content");
			
			AdminInfoAnswerDto dto = new AdminInfoAnswerDto(info_No,answer_No,answer_Writer,updateContent);
			
			boolean res = biz.updateAnswer(dto);
			
			if(res) {
				jsResponse("답글 수정 성공","admininfocontroller.do?command=detail&info_No="+info_No,response);
			}else {
				jsResponse("답글 수정 실패","admininfocontroller.do?command=updateanswerform&info_No="+info_No+"&answer_No="+answer_No,response);
			}
	         


		} else if (command.equals("logout")) {
			jsResponse("로그인을 해주세요", "maincontroller.do?command=mainlogout", response);
		}
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";

		PrintWriter out = response.getWriter();
		out.print(s);
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}