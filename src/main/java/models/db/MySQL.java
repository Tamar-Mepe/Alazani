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
    }

    @Override
    public void execute(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
    }


    // Queries
    @Override
    public String getCreateDatabase() {
        return "CREATE DATABASE " + Environment.DATABASE;
    }

    @Override
    public String getCreateTable(String tableName, Map<String, String> fields) {
        StringBuilder query = new StringBuilder("CREATE TABLE " + tableName + "(\n");
        for (String name : fields.keySet()) {
            query.append(name).append(" ").append(fields.get(name)).append(",\n");
        }
        query.deleteCharAt(query.length() - 2);
        query.append(");");
        return query.toString();
    }

    // Query Executions
    @Override
    public void createDatabase() throws SQLException {
        // Drop existing database
        execute("DROP DATABASE IF EXISTS " + Environment.DATABASE);
        // Create new Database
        String query = getCreateDatabase();
        execute(query);
        // Use created Database
        execute("USE " + Environment.DATABASE);
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