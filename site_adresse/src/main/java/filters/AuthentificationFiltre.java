package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*Cette classe permet d'établir un filtre pour privatiser l'espace administrateur du site
 *
 * @author MFillion
 * @version 1.0
 */

public class AuthentificationFiltre implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /*methode qui permet de définit le comportement du filtre
     *
     * @param request : la requete envoyée par le client (ServletRequest)
     * @param response : la réponse envoyée par le serveur (ServletResponse)
     * @param chain : La chaine de caractère du filtre (FilterChain)
     *
     * @author MFillion
     * @version 1.0
     */

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String identifiant = (String) httpRequest.getSession().getAttribute("utilisateurConnecte");
        if(identifiant == null || "".equals(identifiant)) {
            System.out.println("Il faut être connecté pour accéder à cette page !");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("../connexion");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
