package dao;

import entities.Article;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*Cette classe permet de regrouper toutes les méthodes utilisées dans les fonctionnalités du site web, en lien avec les articles nécessitant une connexion à la base de données.
 * @see projet_100h.servlets.ArticleServlet
 * @see projet_100h.servlets.LikeServlet
 * @see projet_100h.servlets.DislikeServlet
 *
 * @author MFillion
 * @version 1.0
 */

public class ArticleDaoImpl implements ArticleDao {

    /*methode qui permet d'afficher une liste d'article provenant de la base de données
     *
     * Elle retourne la liste d'article de la base de données triés du plus récent au plus vieux
     * @return Une liste d'articles
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.BarServlet
     * @see projet_100h.servlets.RestaurantServlet
     * @see projet_100h.servlets.MuseeServlet
     */

    @Override
    public List<Article> listArticle() {
        String query = "SELECT * FROM adresse ORDER BY adresse.date_ajout DESC;";
        List<Article> listArticle = new ArrayList();
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()) {
                listArticle.add(new Article(
                        LocalDate.parse(resultSet.getString("date_ajout")),
                        resultSet.getInt("id"),
                        resultSet.getString("Nom"),
                        resultSet.getString("Type"),
                        resultSet.getString("presentation"),
                        resultSet.getString("Visiteur"),
                        resultSet.getDouble("coordonnee_x"),
                        resultSet.getDouble("coordonnee_y"),
                        resultSet.getString("lien_image"),
                        resultSet.getString("lien_image_accueil"),
                        resultSet.getInt("note"),
                        resultSet.getInt("nb_like"),
                        resultSet.getInt("nbdislike"))
                    );
                }
            } catch (SQLException e){
                e.printStackTrace();
        }
        return listArticle;
    }

    /*methode qui permet d'augmenter le compteur de like d'un article de 1
     *
     * @param id : l'identifiant de l'article qui doit être liké (Integer)
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.ArticleServlet
     */

    @Override
    public void likeArticle(Integer id){
        String query = "UPDATE adresse SET nb_like=nb_like+1 WHERE id=?";
        try( Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*methode qui permet d'augmenter le compteur de dislike d'un article de 1
     *
     * @param id : l'identifiant de l'article qui doit être liké (Integer)
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.ArticleServlet
     */

    @Override
    public void dislikeArticle(Integer id){
        String query = "UPDATE adresse SET nbdislike=nbdislike+1 WHERE id=?";
        try( Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*methode qui permet de récupérer un article précis dans la base de données
     *
     * @param id : l'identifiant de l'article qui doit récupéré (Integer)
     *
     * Elle retourne l'article récupéré
     * @return l'article récupéré en base de données (Article)
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.ArticleServlet
     */

    @Override
    public Article getArticle (Integer id) {
        String query = "SELECT * FROM adresse WHERE id=?";
        try( Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try( ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    return new Article(
                            LocalDate.parse(resultSet.getString("date_ajout")),
                            resultSet.getInt("id"),
                            resultSet.getString("Nom"),
                            resultSet.getString("Type"),
                            resultSet.getString("presentation"),
                            resultSet.getString("Visiteur"),
                            resultSet.getDouble("coordonnee_x"),
                            resultSet.getDouble("coordonnee_y"),
                            resultSet.getString("lien_image"),
                            resultSet.getString("lien_image_accueil"),
                            resultSet.getInt("note"),
                            resultSet.getInt("nb_like"),
                            resultSet.getInt("nbdislike"));
                }
            }
        } catch(SQLException e){
                e.printStackTrace();
        }
        return null;
    }

}
