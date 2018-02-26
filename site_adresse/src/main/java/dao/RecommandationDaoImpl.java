package dao;

import entities.Recommandation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*Cette classe permet de regrouper toutes les méthodes utilisées dans les fonctionnalités du site web, en lien avec les recommandations.
 *
 * @see projet_100h.servlets.BarServlet
 * @see projet_100h.servlets.RestaurantServlet
 * @see projet_100h.servlets.MuseeServlet
 * @see projet_100h.servlets.AdminServlet
 *
 * @author MFillion
 * @version 1.0
 */

public class RecommandationDaoImpl implements RecommandationDao {

    /*methode qui permet d'afficher une liste de recommandations provenant de la base de données
     *
     * Elle retourne la liste de recommandations de la base de données triés de la plus vieille à la plus récente
     * @return Une liste de recommandation
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.AdminServlet
     */

    @Override
    public List<Recommandation> listRecommandation() {
        String query = "SELECT * FROM recommandation ORDER BY recommandation.id asc;";
        List<Recommandation> listRecommandation = new ArrayList();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()) {
                listRecommandation.add(new Recommandation(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("mail"),
                        resultSet.getString("adresse"),
                        resultSet.getString("type"),
                        resultSet.getInt("numero"),
                        resultSet.getString("rue"),
                        resultSet.getString("ville"))
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listRecommandation;
    }

    /*methode qui permet d'ajouter une recommandation dans la base de données
     *
     * @param recommandation : la recommandation à ajouter en base de données (Recommandation)
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.BarServlet
     * @see projet_100h.servlets.RestaurantServlet
     * @see projet_100h.servlets.MuseeServlet
     */

    @Override
    public void addRecommandation(Recommandation recommandation) {
        String query = "INSERT INTO recommandation(nom, prenom, mail, adresse, type, numero, rue, ville) VALUES (?,?,?,?,?,?,?,?)";
        try( Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, recommandation.getNom());
            statement.setString(2, recommandation.getPrenom());
            statement.setString(3, recommandation.getMail());
            statement.setString(4, recommandation.getAdresse());
            statement.setString(5, recommandation.getType());
            statement.setInt(6, recommandation.getNumero());
            statement.setString(7, recommandation.getRue());
            statement.setString(8, recommandation.getVille());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*methode qui permet de supprimer une recommandation de la base de données
     *
     * @param id : l'identifiant de la recommandation à supprimer en base de données (int)
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.AdminServlet
     */

    @Override
    public void deleteRecommandation(int id) {
        String query = "DELETE FROM recommandation WHERE id=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
