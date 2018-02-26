package servlets;

import entities.Article;
import entities.Commentaire;
import entities.Recommandation;
import managers.ArticleLibrary;
import managers.CommentaireLibrary;
import managers.RecommandationLibrary;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@WebServlet("/musee")
public class MuseesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(req.getServletContext());
        resolver.setPrefix("WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");

        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);


        WebContext context = new WebContext(req, resp, req.getServletContext());
        String Type = "musee";
        List<Article> listofarticle = ArticleLibrary.getInstance().listArticle();
        ListIterator<Article> it = listofarticle.listIterator();
        List<Article> listMusee = new ArrayList<>();

        while(it.hasNext()) {
            Article article = it.next();
            if (article.getType().equals(Type)) {
                listMusee.add(article);
            }
        }

        context.setVariable("listMusee", listMusee);
        context.setVariable("type_page", Type);


        engine.process("musees", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String mail = req.getParameter("mail");
        String type = req.getParameter("type");
        String adresse = req.getParameter("adresse");
        String rue = req.getParameter("rue");
        Integer numero = Integer.parseInt(req.getParameter("numero"));
        String ville = req.getParameter("ville");
        String type_page = req.getParameter("type_page");

        Recommandation recommandation = new Recommandation(null, nom, prenom, mail, type, adresse, numero, rue, ville);
        RecommandationLibrary.getInstance().AddRecommandation(recommandation);

        resp.sendRedirect("/"+type_page);
    }

}
