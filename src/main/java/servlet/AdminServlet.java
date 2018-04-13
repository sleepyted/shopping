package servlet;

import bean.Result;
import bean.User;
import com.google.gson.Gson;
import common.Util;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet{
	UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");
		switch (page) {
			case "login":
				req.setAttribute("title", "管理员登录");
				req.getRequestDispatcher("/page/admin/login.jsp").forward(req, resp);
				break;

			case "dashboard":
				req.setAttribute("title", "管理");
				req.getRequestDispatcher("/page/admin/dashboard.jsp").forward(req, resp);
				break;
			case "exit":
				req.getSession().removeAttribute("admin");
				resp.sendRedirect("home");
				break;
			default:
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		User admin = new User() ;
		switch (action) {
			case "login":
				String email = req.getParameter("email");
				String password = req.getParameter("password");
				admin = userService.adminLogin(email, password);
				if (null != admin) {
					HttpSession session = req.getSession();
					session.setAttribute("admin", admin);
					Result result = new Result(1, "/admin?page=dashboard");
					Util.writeJson(resp, result);
				} else {
					Result result = new Result(0, "登录失败，请检查用户名和密码");
					Util.writeJson(resp, result);
				}
				break;
		}
	}
}
