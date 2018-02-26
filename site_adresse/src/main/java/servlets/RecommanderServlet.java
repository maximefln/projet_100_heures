package servlets;

import entities.Recommandation;
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
import java.util.List;

@WebServlet("/admin/recommander")
public class RecommanderServlet extends HttpServlet {


        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String identifiantUtilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");
            ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(req.getServletContext());
            resolver.setPrefix("WEB-INF/templates/");
            resolver.setSuffix(".html");
            resolver.setTemplateMode(TemplateMode.HTML);
            resolver.setCharacterEncoding("UTF-8");

            TemplateEngine engine = new TemplateEngine();
            engine.setTemplateResolver(resolver);

            WebContext context = new WebContext(req, resp, req.getServletContext());
            List<Recommandation> recommandationList = RecommandationLibrary.getInstance().listRecommandation();

            context.setVariable("listRecommandation", recommandationList);

            engine.process("recommandation", context, resp.getWriter());


        }
}
