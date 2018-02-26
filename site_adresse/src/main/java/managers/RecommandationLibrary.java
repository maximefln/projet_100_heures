package managers;

import dao.RecommandationDao;
import dao.RecommandationDaoImpl;
import entities.Recommandation;

import java.util.List;
import java.util.TreeMap;

/*Cette classe permet de regrouper toutes les méthodes utilisées dans les fonctionnalités du site web, en lien avec les recommandations sans que les servlets associées ne puisse avoir un accès direct à la base de données.
 * @see projet_100h.servlets.RestaurantServlet
 * @see projet_100h.servlets.MuseeServlet
 * @see projet_100h.servlets.BarServlet
 * @see projet_100h.dao.RecommandationDaoImpl
 *
 * @author MFillion
 * @version 1.0
 */

public class RecommandationLibrary {

    /* Cette classe permet de créer une seule et unique instance de Recommandationlibrary en empechant l'instanciation extérieure
     *
     * @author MFillion
     * @version 1.0
     */

    private static class RecommandationLibraryHolder{
        private static final RecommandationLibrary instance = new RecommandationLibrary();
    }

    /*methode qui permet de récupérer l'instance RecommandationLibrary
     *
     * Elle retourne l'instance de RecommandationLibrary
     * @return l'instance de RecommandationLibrary
     *
     * @author MFillion
     * @version 1.0
     */

    public static RecommandationLibrary getInstance(){
        return RecommandationLibraryHolder.instance;
    }

    private RecommandationDao recommandationDao = new RecommandationDaoImpl();

    /*Contructeur par défaut
     * @author MFillion
     * @version 1.0
     */

    private RecommandationLibrary(){
    }

    private TreeMap<Integer, Recommandation> recommandationList;

    /*methode qui permet de lister les recommandation stockées en base de données
     *
     * Elle retourne une liste de recommandations
     * @return une liste de recommandations (List<Recommandation>)
     *
     * @author MFillion
     * @version 1.0
     */

    public List<Recommandation> listRecommandation(){
        return recommandationDao.listRecommandation();
    }

    /*methode qui permet d'ajouter une recommandation
     *
     * @param recommandation : la nouvelle recommandation à ajouter (Recommandation)
     *
     * @author MFillion
     * @version 1.0
     */

    public void AddRecommandation (Recommandation recommandation){
        recommandationDao.addRecommandation(recommandation);
    }

    /*methode qui permet de supprimer une recommandation
     *
     * @param id : l'identification de la recommandation à supprimer (Int)
     *
     * @author MFillion
     * @version 1.0
     */

    public void DeleteRecommandation( int id){
        recommandationDao.deleteRecommandation(id);
    }

}
