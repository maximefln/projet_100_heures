package servlets;

import entities.Article;
import entities.Commentaire;
import managers.ArticleLibrary;
import managers.CommentaireLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/article")
public class ArticleServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Mise en place du resolver pour afficher la page html
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(req.getServletContext());

        //Chemin pour récupérer les pages html
        resolver.setPrefix("WEB-INF/templates/");

        //définition du suffix des pages html
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");

        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);

        //Instanciation du context pour charger des variables et utiliser thymeleaf
        WebContext context = new WebContext(req, resp, req.getServletContext());

        //Récupération des variables nécéssaire pour la page html
        Integer idArticle = Integer.parseInt(req.getParameter("id"));
        Article article = ArticleLibrary.getInstance().getArticle(idArticle);
        List<Commentaire> listofcommentaire = CommentaireLibrary.getInstance().listCommentaire();
        if(article == null){
            resp.sendRedirect("/bars");
        }

        //Envoie les listes dan le context pour les utiliser avec thymeleaf
        context.setVariable("article", article);
        context.setVariable("listCommentaire", listofcommentaire);

        engine.process("article", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prenom = req.getParameter("prenom");
        String nom = req.getParameter("nom");
        String commentaire = req.getParameter("commentaire");
        Integer id_article = Integer.parseInt(req.getParameter("id_article"));

        Commentaire newCommentaire = new Commentaire(null, nom, prenom, commentaire, LocalDate.now(), null);

        CommentaireLibrary.getInstance().addCommentaire(newCommentaire, id_article);

        resp.sendRedirect("/article?id="+id_article);
    }
}
