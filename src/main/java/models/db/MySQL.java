package models.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL implements DB {
    private Connection conn;
    @Override
    public String getAll(String tableName) {
        return "SELECT * FROM " + tableName + ";";
    }

    @Override
    public Connection connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection currCon = DriverManager.getConnection(Environment.URL,Environment.USER,Environment.PASSWORD);
        Statement useStatement =  conn.createStatement();
        useStatement.execute( "USE "  + Environment.DATABASE +";");
        return currCon;
    }

    public MySQL() throws SQLException, ClassNotFoundException {
        conn = connectToDatabase();
    }

}
