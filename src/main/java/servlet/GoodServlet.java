package servlet;

import bean.Good;
import bean.User;
import com.google.gson.Gson;
import common.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 */
@WebServlet("/good")
public class GoodServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        if(null != page){
            User user = (User) req.getSession().getAttribute("admin");
            if(null != user){
                req.getRequestDispatcher("/page/admin/addgood.jsp").forward(req, resp);
            }else {
                resp.sendRedirect("admin?page=login");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Log.i("GoodServlet", action);
        if(action.equals("add")){
            String picId = req.getParameter("picId");
            String name = req.getParameter("name");
            String discription = req.getParameter("discription");
            Double price  = Double.valueOf(req.getParameter("price"));

        }
    }
}
