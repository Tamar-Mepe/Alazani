package models.db;

import java.sql.*;
import java.util.Map;

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
    public void execute(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
    }

    @Override
    public String getAll(String tableName) {
        return "SELECT * FROM " + tableName;
    }

    @Override
    public String getCreateTable(String tableName, Map<String, String> fields) {
        StringBuilder query = new StringBuilder("CREATE TABLE " + tableName + "(");
        for (String name : fields.keySet()) {
            query.append(name).append(" ").append(fields.get(name)).append(", ");
        }
        query.deleteCharAt(query.length() - 1);
        query.deleteCharAt(query.length() - 1);
        query.append(");");
        System.out.println(query.toString());
        return query.toString();
    }

    @Override
    public void createTable(String tableName, Map<String, String> fields) throws SQLException {
        // Drop existing table
        execute("DROP TABLE IF EXISTS " + tableName);
        // Create new Table
        String query = getCreateTable(tableName, fields);
        execute(query);
    }

}