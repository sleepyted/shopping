package servlet;

import bean.Good;
import bean.Result;
import bean.User;
import com.google.gson.Gson;
import common.Log;
import common.Util;
import service.GoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 */
@WebServlet("/good")
public class GoodServlet extends HttpServlet {
	private static final String TAG = "GoodServlet";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");
		if (null != page) {
			User user = (User) req.getSession().getAttribute("admin");
			if (null != user) {
				switch (page) {
					case "add":
						req.setAttribute("title", "添加");
						req.getRequestDispatcher("/page/admin/addgood.jsp").forward(req, resp);
						break;
					case "all":
						GoodService service = new GoodService();
						Result result = new Result();
						result.setStatus(1);
						result.setList(service.findAllGood());
						Util.writeJson(resp, result);
						break;
					default:
						req.setAttribute("title", "商品详情");
						try {
							int goodId = Integer.valueOf(page);
							GoodService service1 = new GoodService();
							Good good = service1.findGood(Integer.valueOf(page));
							req.setAttribute("good", good);
						}catch (Exception e){
							Log.i(TAG, e.toString());
						}
						req.getRequestDispatcher("/page/good/view.jsp").forward(req, resp);
						break;
				}
			} else {
				resp.sendRedirect("admin?page=login");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		Log.i("GoodServlet", action);
		if (action.equals("add")) {
			String picId = req.getParameter("picId");
			String name = req.getParameter("name");
			String discription = req.getParameter("discription");
			Double price = Double.valueOf(req.getParameter("price"));

			Good good = new Good();
			good.setName(name);
			good.setDiscription(discription);
			good.setPrice(price);
			if (!picId.equals("")) {
				good.setPicId(Integer.valueOf(picId));
			} else {
				good.setPicId(1);
			}
			System.out.println(good.toString());
			GoodService goodService = new GoodService();
			goodService.addGood(good);
			resp.sendRedirect("good?page=add");
		}
	}
}
