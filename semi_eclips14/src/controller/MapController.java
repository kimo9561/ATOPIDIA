package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Dto.MapDto;

@WebServlet("/MapController")
public class MapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MapController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String command = request.getParameter("command");
		
		if(command.equals("list")) {
			String hos_list = request.getParameter("hos_list");
			
			String[] hospitalList = hos_list.split("#");
			
			List<MapDto> dtos = new ArrayList<MapDto>();
			
			
			
			for(int i=0; i<hospitalList.length; i++) {
				String[] tmp = hospitalList[i].split("/");
				MapDto map = new MapDto(Integer.parseInt(tmp[0]), Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]),
								tmp[3], tmp[4], tmp[5]);
				
				dtos.add(map);
			}
		
			request.setAttribute("list", dtos);
			dispatch("map/mapList.jsp", request, response);
			
		}else if(command.equals("map")) {
					
			response.sendRedirect("map/Map2.jsp");
			
		}else if(command.equals("logout")) {
	    	  jsResponse("로그인을 해주세요","maincontroller.do?command=mainlogout", response);
	      }
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"
				+"alert('"+msg+"');"
				+"location.href = '"+url+"';"
				+"</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
