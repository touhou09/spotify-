import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@14.56.195.170:1521:orcl";
    private static final String USER = "spotify"; 
    private static final String PASS = "oracle"; 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}