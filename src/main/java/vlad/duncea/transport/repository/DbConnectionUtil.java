package vlad.duncea.transport.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import vlad.duncea.transport.repository.PrivateConnData;

public class DbConnectionUtil {

    /**
     * JDBC -> Connection, Statement/ PreparedsTATEMENT, ResultSet, Driver/ DriverManager
     */
    private static final String DATABASE_URL = PrivateConnData.DATABASE_URL;
    private static final String DATABASE_USER = PrivateConnData.DATABASE_USER;
    private static final String DATABASE_PASSWORD = PrivateConnData.DATABASE_PASSWORD;


    public static Connection getDBConnection()  {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static void closeDBConnection(Connection connection)  {
        try {
            if(connection != null) {
                connection.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
