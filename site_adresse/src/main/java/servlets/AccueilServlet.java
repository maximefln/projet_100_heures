package servlets;

import entities.Article;
import managers.ArticleLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import utils.MotDePasseUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {

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

        //Instanciation du context pour wharger des variables et utiliser thymeleaf
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String Bar = "Bar";
        String Restaurant = "Restaurant";
        String Musee = "Musee";

        //récupération des listes nécéssaires
        List<Article> listofarticle = ArticleLibrary.getInstance().listArticle();
        ListIterator<Article> it = listofarticle.listIterator();

        List<Article> listBar = new ArrayList<>();
        List<Article> listRestaurant = new ArrayList<>();
        List<Article> listMusee = new ArrayList();

        //Séléction des listes de même type
        while(it.hasNext()) {
            Article article = it.next();
            if (article.getType().equals(Bar) && listBar.size()<3) {
                listBar.add(article);
            }else{
                if (article.getType().equals(Restaurant) && listRestaurant.size()<3){
                    listRestaurant.add(article);
                }else{
                    if(article.getType().equals(Musee) && listMusee.size()<3){
                        listMusee.add(article);
                    }
                }
            }
        }

        //Envoie les listes dan le context pour les utiliser avec thymeleaf
        context.setVariable("listBar", listBar);
        context.setVariable("listRestaurant", listRestaurant);
        context.setVariable("listMusee", listMusee);

        engine.process("accueil",context, resp.getWriter());
    }
}