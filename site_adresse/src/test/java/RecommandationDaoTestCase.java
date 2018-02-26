import dao.DataSourceProvider;
import dao.RecommandationDao;
import dao.RecommandationDaoImpl;
import entities.Recommandation;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

public class RecommandationDaoTestCase {

    private RecommandationDao recommandationDao = new RecommandationDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate("DROP SCHEMA IF EXISTS projet_100h");
            statement.executeUpdate("CREATE SCHEMA projet_100h");
            statement.executeUpdate("CREATE TABLE projet_100h.adresse(\n" +
                    "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "`date_ajout` date NULL,\n" +
                    "`Nom` VARCHAR(50) NOT NULL,\n" +
                    "`Type` VARCHAR(50) NOT NULL,\n" +
                    "`presentation` VARCHAR(50) NOT NULL,\n" +
                    "`Visiteur` VARCHAR(50) NOT NULL,\n" +
                    "`coordonnee_x` DOUBLE NOT NULL,\n" +
                    "`coordonnee_y` DOUBLE NOT NULL,\n" +
                    "`lien_image` VARCHAR(100),\n" +
                    "`lien_image_accueil` VARCHAR(100),\n" +
                    "`note` DOUBLE NULL DEFAULT 0,\n" +
                    "`nb_like` INT NULL DEFAULT 0,\n" +
                    "`nbdislike` INT NULL DEFAULT 0,\n" +
                    "PRIMARY KEY (`id`))");
            statement.executeUpdate("CREATE TABLE projet_100h.commentaire(\n" +
                    "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "`date` DATE NULL,\n" +
                    "`nom` VARCHAR(50) NOT NULL,\n" +
                    "`prenom` VARCHAR(50) NOT NULL,\n" +
                    "`commentaire` VARCHAR(500) NOT NULL,\n" +
                    "`id_article` INT NOT NULL,\n" +
                    "PRIMARY KEY (`id`))");
            statement.executeUpdate("CREATE TABLE projet_100h.recommandation(\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Nom` VARCHAR(50) NOT NULL,\n" +
                    "  `prenom` VARCHAR(50) NOT NULL,\n" +
                    "  `mail` VARCHAR(50) NOT NULL,\n" +
                    "  `adresse` VARCHAR(50) NOT NULL,\n" +
                    "  `type` VARCHAR(50) NOT NULL,\n" +
                    "  `rue` VARCHAR (80) NOT NULL,\n" +
                    "  `numero` INT NOT NULL,\n" +
                    "  `Ville` VARCHAR (50),\n" +
                    "  PRIMARY KEY  (`id`))");
            statement.executeUpdate("INSERT INTO projet_100h.recommandation(nom, prenom, mail, adresse, type, numero, rue, ville) VALUES ('paul', 'jean', 'paul@jean.fr', 'faluche', 'bar', 3, 'rue esquermoize', 'lille');");
            statement.executeUpdate("INSERT INTO projet_100h.recommandation(nom, prenom, mail, adresse, type, numero, rue, ville) VALUES ('max', 'thomas', 'max@thomas.fr', 'berkeley', 'bar', 3, 'rue esquermoize', 'lille');");

        }
    }

    @Test
    public void ShouldListRecommandation () {
        //WHEN
        List<Recommandation> recommandation = recommandationDao.listRecommandation();
        //THEN
        assertThat(recommandation).hasSize(2);
        assertThat(recommandation).extracting("nom", "prenom", "mail", "adresse", "type", "numero", "rue", "ville").containsOnly(
                tuple("paul", "jean", "paul@jean.fr", "bar", "faluche", 3, "rue esquermoize", "lille"),
                tuple("max", "thomas", "max@thomas.fr", "bar", "berkeley", 3, "rue esquermoize", "lille")
        );
    }

    @Test
    public void shouldAddRecommandation() throws SQLException{
        //GIVEN
        Recommandation recommandation = new Recommandation(null, "jean", "thomas", "jean@thomas.fr", "bar", "beyrout", 3, "vauban", "lille");
        //WHEN
        recommandationDao.addRecommandation(recommandation);
        //THEN
        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM projet_100h.recommandation WHERE mail='jean@thomas.fr'")){
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("id")).isNotNull();
            Assertions.assertThat(resultSet.getString("nom")).isEqualToIgnoringCase("jean");
            Assertions.assertThat(resultSet.getString("prenom")).isEqualToIgnoringCase("thomas");
            Assertions.assertThat(resultSet.getString("mail")).isEqualToIgnoringCase("jean@thomas.fr");
            Assertions.assertThat(resultSet.getString("type")).isEqualToIgnoringCase("bar");
            Assertions.assertThat(resultSet.getString("adresse")).isEqualToIgnoringCase("beyrout");
            Assertions.assertThat(resultSet.getInt("numero")).isEqualTo(3);
            Assertions.assertThat(resultSet.getString("rue")).isEqualToIgnoringCase("vauban");
            Assertions.assertThat(resultSet.getString("ville")).isEqualToIgnoringCase("lille");
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }

    @Test
    public void shouldDeleteREcommandation() {
        //WHEN
        recommandationDao.deleteRecommandation(2);
        List<Recommandation> recommandations = recommandationDao.listRecommandation();
        //THEN
            assertThat(recommandations).hasSize(1);
            assertThat(recommandations).extracting("nom", "prenom", "mail", "adresse", "type", "numero", "rue", "ville").containsOnly(
                    tuple("paul", "jean", "paul@jean.fr", "bar", "faluche", 3, "rue esquermoize", "lille"));
    }

}
