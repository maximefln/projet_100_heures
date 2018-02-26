package servlets;


import entities.Recommandation;
import managers.RecommandationLibrary;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet{

    private Map<String, String> utilisateursAutorises;

    private String connexion[] = {"test", "test"};

    @Override
    public void init() throws ServletException {
        utilisateursAutorises = new HashMap<>();
        utilisateursAutorises.put("admin", "$argon2i$v=19$m=65536,t=2,p=1$J/P7O6MK9NF4Dda9k4+1iQ$x9BvWUdJGGqmzAfeP8hwrHjK1GIeL4/0MLTdZdThFB0");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String identifiant = (String) req.getSession().getAttribute("utilisateurConnecte");
        if(identifiant == null || "".equals(identifiant)) {
            ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(req.getServletContext());
            resolver.setPrefix("WEB-INF/templates/");
            resolver.setSuffix(".html");
            resolver.setTemplateMode(TemplateMode.HTML);
            resolver.setCharacterEncoding("UTF-8");

            TemplateEngine engine = new TemplateEngine();
            engine.setTemplateResolver(resolver);

            WebContext context = new WebContext(req, resp, req.getServletContext());

            engine.process("connexion", context, resp.getWriter());
        }else {
            ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(req.getServletContext());
            resolver.setPrefix("WEB-INF/templates/");
            resolver.setSuffix(".html");
            resolver.setTemplateMode(TemplateMode.HTML);
            resolver.setCharacterEncoding("UTF-8");

            TemplateEngine engine = new TemplateEngine();
            engine.setTemplateResolver(resolver);

            WebContext context = new WebContext(req, resp, req.getServletContext());

            engine.process("admin", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identifiantFormulaire = req.getParameter("id");
        String motDePasseFormulaire = req.getParameter("mdp");
        /*if (connexion[0].equals(identifiantFormulaire) && connexion[1].equals(motDePasseFormulaire)){
            req.getSession().setAttribute("utilisateurConnecte", identifiantFormulaire);
            }*/
        if(utilisateursAutorises.containsKey(identifiantFormulaire)
                && MotDePasseUtils.validerMotDePasse(motDePasseFormulaire, utilisateursAutorises.get(identifiantFormulaire))) {
            req.getSession().setAttribute("utilisateurConnecte", identifiantFormulaire);
        }

        resp.sendRedirect("connexion");
    }
}
