package dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {

    /*Cette classe permet de mémoriser les informations de connexion et de se connecter à la base de données.
     * @author MFillion
     * @version 1.0
     */

    private static MysqlDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new MysqlDataSource();
            dataSource.setServerName("r42ii9gualwp7i1y.chr7pe7iynqr.eu-west-1.rds.amazonaws.com");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("lprazoxf60p9ze12");
            dataSource.setUser("b0lo5x6m6chv9lze");
            dataSource.setPassword("q4vfx7c1t1baknc7");
        }
        return dataSource;
    }
}
