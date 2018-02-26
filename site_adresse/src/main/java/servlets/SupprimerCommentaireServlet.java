package servlets;

import managers.CommentaireLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/supprimerCommentaire")
public class SupprimerCommentaireServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        CommentaireLibrary.getInstance().deleteCommentaire(id);
        resp.sendRedirect("/admin/commentaire");

    }
}
