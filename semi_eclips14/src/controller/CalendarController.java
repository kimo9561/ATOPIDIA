package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.Biz.CalendarBiz;
import model.Dto.CalendarDiaryDto;
import model.Dto.CalendarDto;

@WebServlet("/CalendarController")
public class CalendarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public CalendarController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		CalendarBiz biz = new CalendarBiz();
		
		if(command.equals("calendarmain")) {
			int member_no = Integer.parseInt(request.getParameter("member_no"));
			System.out.println(member_no);
			List<CalendarDto> list = biz.selectAll(member_no);
			List<CalendarDiaryDto> dlist = biz.selectD(member_no);
			request.setAttribute("list", list);
			request.setAttribute("dlist", dlist);
			dispatch("calendar/calendarlogin.jsp", request, response);
		}else if(command.equals("logout")) {
	    	  jsResponse("로그인을 해주세요","maincontroller.do?command=mainlogout", response);
	      
		}else if(command.equals("reservation")) {
			int member_no = Integer.parseInt(request.getParameter("member_no"));
			String title = request.getParameter("calendar_title");
			String r_date = request.getParameter("r_date");
			String r_time = request.getParameter("r_time");
			String content = request.getParameter("content");
			String cdate = r_date+r_time;
			System.out.println(r_date+r_time);
			CalendarDto dto = new CalendarDto();
			dto.setMember_no(member_no);
			dto.setCalendartitle(title);
			dto.setCalendardate(cdate);
			dto.setContent(content);
			PrintWriter out = response.getWriter();
			boolean res = biz.insertCalendar(dto);
			System.out.println(res);
			if(res) {
				out.print("예약 되었습니다");
			}else {
				out.print("실패");
			}
			out.flush();
			out.close();
		}else if(command.equals("delete")) {
			int member_no = Integer.parseInt(request.getParameter("member_no"));
			
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(request.getParameter("id"));
			PrintWriter out = response.getWriter();
			boolean res = biz.deleteCalendar(member_no, id);		
			System.out.println(res);
			
			if(res) {
				out.print("예약 취소되었습니다");
			}else {
				out.print("취소실패");
			}
			out.flush();
			out.close();
		}else if(command.equals("update")) {
			int member_no = Integer.parseInt(request.getParameter("member_no"));
			int id = Integer.parseInt(request.getParameter("id"));
			String u_date = request.getParameter("u_date");
			String u_time = request.getParameter("u_time");
			String cdate = u_date+u_time;
			System.out.println(id+"dsaas"+cdate);
			PrintWriter out = response.getWriter();
			boolean res = biz.updateCalendar(member_no, id, cdate);	
			System.out.println(res);
			if(res) {
				out.print("예약이 변경되었습니다");
			}else {
				out.print("변경 실패");
			}
			out.flush();
			out.close();
		}else if(command.equals("calendardiary")) {
			String cdate = request.getParameter("cdate");
			int member_no = Integer.parseInt(request.getParameter("member_no"));
			int diary_No = Integer.parseInt(request.getParameter("diary_No"));
			String title = request.getParameter("title");
			String url = "diarycontroller.do?command=detailform&diary_No="+diary_No;
			CalendarDiaryDto dto = new CalendarDiaryDto(member_no, diary_No, title, cdate, url);
			boolean res = biz.insertCDiary(dto);
			String script = "<script type='text/javascript'>"+
                    "alert('저장 성공!!');"+
                    "location.href='./diarycontroller.do?command=diarylist&member_no="+member_no+"';"+
                    "</script>";
			PrintWriter out = response.getWriter();
			out.print(script);
		}else if(command.equals("calendardiarydelete")) {
			int dno = Integer.parseInt(request.getParameter("diary_No"));
			int member_no = Integer.parseInt(request.getParameter("member_no"));

			boolean res = biz.deleteCalendarDiary(dno);
			if(res) {
				jsResponse("삭제 성공!", "diarycontroller.do?command=diarylist&member_no="+member_no, response);
			}else {
	         	jsResponse("삭제 실패!", "diarycontroller.do?command=diarylist&member_no="+member_no, response);
			}
		}else if(command.equals("diaryChkdelete")) {
			String[] chks = request.getParameterValues("chk");
			int member_no = Integer.parseInt(request.getParameter("member_no"));
			for(int i=0; i<chks.length; i++) {
	             System.out.println(chks[i]);
	          }
			boolean res = biz.deleteChkDiary(chks);
			if(res) {
				jsResponse("삭제 성공!", "diarycontroller.do?command=diarylist&member_no="+member_no, response);
			}else {
				jsResponse("삭제 실패!", "diarycontroller.do?command=diarylist&member_no="+member_no, response);
			}
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
	
	private void dispatch(String url, HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
