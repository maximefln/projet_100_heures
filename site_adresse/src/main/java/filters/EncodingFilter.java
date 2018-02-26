package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*Cette classe permet d'établir un filtre pour encoder toutes les pages en UTF-8
 *
 * @author MFillion
 * @version 1.0
 */

@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {

    @Override
    public void destroy() {

    }

    /*methode qui permet de définit le comportement du filtre
     *
     * @param request : la requete envoyée par le client (ServletRequest)
     * @param response : la réponse envoyée par le serveur (ServletResponse)
     * @param filterchain : La chaine de caractère du filtre (FilterChain)
     *
     * @author MFillion
     * @version 1.0
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        servletResponse.setContentType("text/html;charset=UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
