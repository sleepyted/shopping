package servlet;

import bean.Comment;
import bean.User;
import dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 */
@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action.equals("add")) {
			int goodId = Integer.valueOf(req.getParameter("goodId"));
			String content = req.getParameter("content");
			Comment comment = new Comment();
			User user1 = (User) req.getSession().getAttribute("user");
			comment.setUserId(user1.getId());
			comment.setUsername(user1.getUsername());
			comment.setGoodId(goodId);
			comment.setContent(content);

			CommentDao dao = new CommentDao();
			dao.insert(comment);
			resp.sendRedirect("order?page=user");
		}

	}
}
