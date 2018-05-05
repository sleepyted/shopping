package servlet;

import bean.Result;
import bean.User;
import com.google.gson.Gson;
import common.Log;
import common.Util;
import dao.PubDefine;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户管理
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet implements PubDefine{
	private final String TAG = "UserServlet";
	UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String page = req.getParameter("page");
		switch (page) {
			case "login":
			case "register":
			case "info":
			case "account":
				req.setAttribute("user", req.getSession().getAttribute("user"));
				req.getRequestDispatcher("/page/user/" + page + ".jsp").forward(req, resp);
				break;
			case "exit":
				req.getSession().removeAttribute("user");
				req.getSession().removeAttribute("admin");
				resp.sendRedirect("home");
				break;
			case "update":
				req.getRequestDispatcher("/page/user/updateAccount.jsp").forward(req, resp);
				break;
			default:
				Log.i(TAG, "No page match -- " + page);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		User user = new User() ;
		switch (action) {
			case "login":
				String email = req.getParameter("email");
				String password = req.getParameter("password");
				user = userService.userLogin(email, password);
				if (null != user) {
					HttpSession session = req.getSession();
					session.setAttribute("user", user);
					Result result = new Result(1, "/home");
					Util.writeJson(resp, result);
				}else {
					Result result = new Result(0, "登录失败，请检查邮箱和密码");
					Util.writeJson(resp,result);
				}
				break;
			case "register":

				Gson gson = new Gson();
				user = gson.fromJson(req.getParameter("userInfo"), User.class);
				Log.i(TAG, user.toString());

				try{
					int rtn = userService.userRegister(user);
					if(rtn == STATUS_ALLREADY_FOUND){
						Util.writeJson(resp, new Result(0,"注册失败，邮箱已被占用"));

					}else if(rtn != STATUS_ERROR){
						Util.writeJson(resp,new Result(1,"注册成功，将跳转到登录页面"));

					}else {
						Util.writeJson(resp, new Result(0,"注册失败，系统错误"));
					}
				}catch (Exception e){
					Log.i(TAG, e.toString());
					Util.writeJson(resp, new Result(0,"注册失败，请重试"));
				}
				break;
			case "update":
				String username = req.getParameter("username");
				String tel = req.getParameter("tel");
				User currUser = (User) req.getSession().getAttribute("user");
				currUser.setUsername(username);
				currUser.setTel(tel);
				userService.updateUser(user);
				resp.sendRedirect("user?page=account");
				break;
		}
	}


}
