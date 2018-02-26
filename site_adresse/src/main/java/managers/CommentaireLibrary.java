package managers;


import dao.CommentaireDao;
import dao.CommentaireDaoImpl;
import entities.Commentaire;
import java.util.List;
import java.util.TreeMap;

/*Cette classe permet de regrouper toutes les méthodes utilisées dans les fonctionnalités du site web, en lien avec les commentaires sans que les servlets associées ne puisse avoir un accès direct à la base de données.
 * @see projet_100h.servlets.ArticleServlet
 * @see projet_100h.dao.CommentaireDaoImpl
 *
 * @author MFillion
 * @version 1.0
 */

public class CommentaireLibrary {

    /* Cette classe permet de créer une seule et unique instance de Commentairelibrary en empechant l'instanciation extérieure
     *
     * @author MFillion
     * @version 1.0
     */

    private static class CommentaireLibraryHolder{
        private static final CommentaireLibrary instance = new CommentaireLibrary();
    }

    /*methode qui permet de récupérer l'instance CommentaireLibrary
     *
     * Elle retourne l'instance de CommentaireLibrary
     * @return l'instance de CommentaireLibrary
     *
     * @author MFillion
     * @version 1.0
     */

    public static CommentaireLibrary getInstance(){
        return CommentaireLibrary.CommentaireLibraryHolder.instance;
    }

    private CommentaireDao commentaireDao = new CommentaireDaoImpl();

    /*Contructeur par défaut
     * @author MFillion
     * @version 1.0
     */

    private CommentaireLibrary(){
    }

    private TreeMap<Integer, Commentaire> articleList;

    /*methode qui permet de lister des commentaires en rapport avec un article stockés en base de données
     *
     * Elle retourne une liste de commentaires
     * @return une liste de commentaires (List<Commentaire>)
     *
     * @author MFillion
     * @version 1.0
     */

    public List<Commentaire> listCommentaire(){
        return commentaireDao.listCommentaire();
    }

    /*methode qui permet d'ajouter un commentaire
     *
     * @param newComment : le nouveau commentaire à ajouter (Commentaire)
     * @param id_article : l'article dont le commentaire fait référence
     *
     * @author MFillion
     * @version 1.0
     */

    public void addCommentaire(Commentaire newComment, Integer id_article) {
        commentaireDao.addCommentaire(newComment,id_article);
    }

    /*methode qui permet de supprimer un commentaire de la base de données
     *
     * @param id : l'identifiant du commentaire à supprimer en base de données (int)
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.AdminServlet
     */

    public void deleteCommentaire( int id){
        commentaireDao.deleteCommentaire(id);
    }
}
