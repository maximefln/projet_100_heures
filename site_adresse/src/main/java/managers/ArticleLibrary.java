package managers;

import dao.ArticleDao;
import dao.ArticleDaoImpl;
import entities.Article;

import java.util.List;
import java.util.TreeMap;

/*Cette classe permet de regrouper toutes les méthodes utilisées dans les fonctionnalités du site web, en lien avec les articles sans que les servlets associées ne puisse avoir un accès direct à la base de données.
 * @see projet_100h.servlets.ArticleServlet
 * @see projet_100h.servlets.RestaurantServlet
 * @see projet_100h.servlets.MuseeServlet
 * @see projet_100h.servlets.BarServlet
 * @see projet_100h.dao.ArticleDaoImpl
 *
 * @author MFillion
 * @version 1.0
 */

public class ArticleLibrary {

    /* Cette classe permet de créer une seule et unique instance de Articlelibrary en empechant l'instanciation extérieure
     *
     * @author MFillion
     * @version 1.0
     */

    private static class ArticleLibraryHolder{
        private static final ArticleLibrary instance = new ArticleLibrary();
    }

    /*methode qui permet de récupérer l'instance ArticleLibrary
     *
     * Elle retourne l'instance de ArticleLibrary
     * @return l'instance de ArticleLibrary
     *
     * @author MFillion
     * @version 1.0
     */

    public static ArticleLibrary getInstance(){
        return ArticleLibraryHolder.instance;
    }

    private ArticleDao articleDao = new ArticleDaoImpl();

    /*Contructeur par défaut
     * @author MFillion
     * @version 1.0
     */

    private ArticleLibrary(){
    }

    private TreeMap<Integer, Article> articleList;

    /*methode qui permet de lister les articles stockés en base de données
     *
     * Elle retourne une liste d'article
     * @return une liste d'article (List<Articles>)
     *
     * @author MFillion
     * @version 1.0
     */

    public List<Article> listArticle(){
        return articleDao.listArticle();
    }

    /*methode qui permet d'augmenter le nombre de like d'un article
     *
     * @author MFillion
     * @version 1.0
     */

    public void likeArticle(Integer id){
        articleDao.likeArticle(id);
    }

    /*methode qui permet d'augmenter le nombre de dislike d'un article
     *
     * @author MFillion
     * @version 1.0
     */

    public void dislikeArticle(Integer id){
        articleDao.dislikeArticle(id);
    }

    /*methode qui permet de récupérer un article de la base de données
     *
     * @param id : l'identifiant de l'article à récupérer
     *
     * @retun l'article récupéré (Article)
     *
     * @author MFillion
     * @version 1.0
     */

    public Article getArticle(Integer id){
        if (id == null){
            throw new IllegalArgumentException("Article id must be provided");
        }else{
            return articleDao.getArticle(id);
        }
    }

}
