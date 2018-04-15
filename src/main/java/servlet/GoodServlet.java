package servlet;

import bean.Comment;
import bean.Good;
import bean.Result;
import bean.User;
import com.google.gson.Gson;
import common.Log;
import common.Util;
import dao.CommentDao;
import service.GoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
				switch (page) {
					case "add":
						if(user!=null && user.getUserType().equals("1")) {
							req.setAttribute("title", "添加");
							req.getRequestDispatcher("/page/admin/addgood.jsp").forward(req, resp);
						}else {
							resp.sendRedirect("admin?page=login");
						}
						break;
					case "all":
							GoodService service = new GoodService();
						Result result = new Result();
						result.setStatus(1);
						result.setList(service.findAllGood());
						Util.writeJson(resp, result);

						break;
					case "del":
						if(user!=null && user.getUserType().equals("1")) {
							String goodIdDel = req.getParameter("goodId");
							if (!goodIdDel.equals("")) {
								GoodService goodService = new GoodService();
								goodService.delGood(Integer.valueOf(goodIdDel));
								Util.writeJson(resp, new Result(1, "success"));
							} else {
								Util.writeJson(resp, new Result(0, "fail"));
							}
						}
						break;
					case "buy":
						//遍历购物车 跳转订单页
						break;
					case "update":
						String goodIdUpdate = req.getParameter("goodId");
						if (!goodIdUpdate.equals("")) {
							GoodService service1 = new GoodService();
							Good good = service1.findGood(Integer.valueOf(goodIdUpdate));
							if(good!=null){
								req.setAttribute("good", good);
							}
						}
						req.getRequestDispatcher("/page/admin/updateGood.jsp").forward(req, resp);
						break;
					default:
						req.setAttribute("title", "商品详情");
						try {
							int goodId = Integer.valueOf(page);
							GoodService service1 = new GoodService();
							Good good = service1.findGood(Integer.valueOf(goodId));

							List<Comment> comments = new ArrayList<>();
							comments = new CommentDao().findByGoodId(good.getId());
							req.setAttribute("good", good);
							req.setAttribute("comments", comments);
						} catch (Exception e) {
							Log.i(TAG, e.toString());
						}
						req.getRequestDispatcher("/page/good/view.jsp").forward(req, resp);
						break;
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
			int count = Integer.valueOf(req.getParameter("count"));

			Good good = new Good();
			good.setName(name);
			good.setDiscription(discription);
			good.setPrice(price);
			good.setCount(count);
			if (!picId.equals("")) {
				good.setPicId(Integer.valueOf(picId));
			} else {
				good.setPicId(1);
			}
			System.out.println(good.toString());
			GoodService goodService = new GoodService();
			goodService.addGood(good);
			resp.sendRedirect("good?page=add");
		} else if (action.equals("update")) {
			String goodIdUpdate = req.getParameter("id");
			if (!"".equals(goodIdUpdate)) {
				Good good = new Good();
				good.setId(Integer.valueOf(goodIdUpdate));
				good.setName(req.getParameter("name"));
				good.setDiscription(req.getParameter("discription"));
				good.setPicId(Integer.valueOf(req.getParameter("picId")));
				good.setCount(Integer.valueOf(req.getParameter("count")));
				good.setPrice(Double.valueOf(req.getParameter("price")));
				GoodService service = new GoodService();
				service.updateGood(good);
				resp.sendRedirect("good?page=" + goodIdUpdate);
			} else {

			}

		}
	}
}
