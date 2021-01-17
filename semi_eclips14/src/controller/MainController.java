package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Biz.AdminInfoBiz;
import model.Biz.AdminInfoBizImpl;
import model.Biz.AdminRecipeBiz;
import model.Biz.UserInfoBiz;
import model.Biz.UserInfoBizImpl;
import model.Biz.UserRecipeBiz;
import model.Dto.AdminInfoDto;
import model.Dto.AdminRecipeDto;
import model.Dto.UserInfoDto;
import model.Dto.UserRecipeDto;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
			
			String command = request.getParameter("command");
			System.out.println(command);
		
			
			if(command.equals("mainlogout")) {
				response.sendRedirect("main/mainlogout.jsp");
				
			}if(command.equals("main")) {
				AdminInfoBiz admin_Info_Biz = new AdminInfoBizImpl(); // 관리자 정보
				AdminRecipeBiz admin_Recipe_Biz = new AdminRecipeBiz(); // 관리자 레시피
				UserInfoBiz user_Info_Biz = new UserInfoBizImpl(); // 사용자 정보
				UserRecipeBiz user_Recipe_Biz = new UserRecipeBiz(); // 사용자 레시피
				
				// 관리자 정보 베스트 3
				List<AdminInfoDto> admin_InfoBest = new ArrayList<AdminInfoDto>();
				admin_InfoBest = admin_Info_Biz.admin_bestInfo();
				
				// 관리자 레시피 베스트 3
				List<AdminRecipeDto> admin_RecipeBest = new ArrayList<AdminRecipeDto>();
				admin_RecipeBest = admin_Recipe_Biz.admin_bestRecipe();
				
				// 사용자 정보 베스트 3
				List<UserInfoDto> user_InfoBest = new ArrayList<UserInfoDto>();
				user_InfoBest = user_Info_Biz.user_bestInfo();
				
				// 사용자 레시피 베스트 3
				List<UserRecipeDto> user_RecipeBest = new ArrayList<UserRecipeDto>();
				user_RecipeBest = user_Recipe_Biz.user_bestRecipe();
				
				request.setAttribute("admin_InfoList", admin_InfoBest);
				request.setAttribute("admin_RecipeList", admin_RecipeBest);
				request.setAttribute("user_InfoList", user_InfoBest);
				request.setAttribute("user_RecipieList", user_RecipeBest);
				
				dispatch("main/mainlogin.jsp", request, response);
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
