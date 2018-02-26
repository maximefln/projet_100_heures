package dao;

import Exceptions.CommentaireRunTimeException;
import entities.Commentaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*Cette classe permet de regrouper toutes les méthodes utilisées dans les fonctionnalités du site web, en lien avec les commentaires.
 * @see projet_100h.servlets.ArticleServlet
 * @author MFillion
 * @version 1.0
 */

public class CommentaireDaoImpl implements CommentaireDao {

    /*methode qui permet d'afficher une liste de commentaires provenant de la base de données
     *
     * Elle retourne la liste de commentaire de la base de données triés du plus récent au plus vieux
     * @return Une liste d'articles
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.serlvets.ArticleServlet
     */

    @Override
    public List<Commentaire> listCommentaire() {
        String query = "SELECT * FROM commentaire ORDER BY commentaire.id DESC;";
        List<Commentaire> listCommentaire = new ArrayList();

        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()) {
                listCommentaire.add(new Commentaire(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("commentaire"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getInt("id_article"))

                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listCommentaire;
    }

    /*methode qui permet d'ajouter un commentaire dans la base de données
     *
     * @param id_article : l'identifiant de l'article qui correspond au commentaire (Integer)
     * @param commentaire : le commentaire à ajouter en base de données
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.ArticleServlet
     */

    @Override
    public void addCommentaire(Commentaire commentaire, Integer id_article){
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(("INSERT INTO commentaire(date, nom, prenom, commentaire, id_article) VALUES (?,?,?,?,?)"))){
                statement.setDate(1, Date.valueOf(commentaire.getDate()));
                statement.setString(2, commentaire.getNom());
                statement.setString(3, commentaire.getPrenom());
                statement.setString(4, commentaire.getCommentaire());
                statement.setInt(5, id_article);
                statement.executeUpdate();
             } catch (SQLException e) {
                 throw new CommentaireRunTimeException("Error when getting comments");
        }

    }

    /*methode qui permet de supprimer un commentaire de la base de données
     *
     * @param id : l'identifiant du commentaire à supprimer en base de données (int)
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.AdminServlet
     */

    @Override
    public void deleteCommentaire(int id) {
        String query = "DELETE FROM commentaire WHERE id=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
