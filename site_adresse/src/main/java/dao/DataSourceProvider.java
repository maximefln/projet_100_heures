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
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("projet_100h");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setDatabaseName("mrn8uciy39g7i8fi");
            dataSource.setUser("whecq0vmcott98zf");
            dataSource.setPassword("oh5q75ezdls9w70y");
        }
        return dataSource;
    }
}
