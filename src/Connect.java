import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connect {
    static void connect(Connection conn) throws SQLException {
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        String dbURL = "jdbc:sqlserver://DESKTOP-UNNBSU0\\MSSQLSERVER;user=tuak123ax;password=123456Tu";
        conn = DriverManager.getConnection(dbURL);
        if (conn != null) {
            System.out.println("Connected");
        }

    }
}
