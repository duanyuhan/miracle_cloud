package miracle.util.course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DBConnection {
    Connection connect;
    ResultSet rSet;
    java.sql.PreparedStatement preStmt;

    public void initializeDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/course?useSSL=false", "root", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

