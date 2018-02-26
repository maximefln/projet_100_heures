package servlets;

import managers.ArticleLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dislikebar")
public class DislikeBarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String type = req.getParameter("type");
        ArticleLibrary.getInstance().dislikeArticle(id);
        resp.sendRedirect("/"+type);

    }
}
