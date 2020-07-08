package models.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL implements DB {
    private Connection connection;


    public MySQL() throws SQLException, ClassNotFoundException {
        connectToDatabase();
    }

    private void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(Environment.URL, Environment.USER, Environment.PASSWORD);
        Statement useStatement = connection.createStatement();
        useStatement.execute("USE " + Environment.DATABASE);
    }


    @Override
    public String getAll(String tableName) {
        return "SELECT * FROM " + tableName;
    }

}
