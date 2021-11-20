import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public Connection connexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bibliotheque";
        String userName = "root";
        String password = "";

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            return con;
        } catch (SQLException e) {
            System.out.println(e);
            throw new SQLException();
        }
    }
}
