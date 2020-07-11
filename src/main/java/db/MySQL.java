package db;

import javax.crypto.spec.PSource;
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
    public String getCreateTable(String tableName, Map<String, Object> fields) {
        StringBuilder query = new StringBuilder("CREATE TABLE " + tableName + "(\n");
        for (String name : fields.keySet()) {
            // If Object is Iterable
            StringBuilder constraintString = new StringBuilder();
            Object obj = fields.get(name);
            if (obj instanceof  String []) {
                for (String constraint : (String[]) obj)
                    constraintString.append(" ").append(constraint);
            } else if (obj instanceof String) {
                constraintString = new StringBuilder((String) obj);
            }
            query.append(name).append(" ").append(constraintString.toString()).append(",\n");
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
    public void createTable(String tableName, Map<String, Object> fields) throws SQLException {
        // Drop existing table
        execute("DROP TABLE IF EXISTS " + tableName);
        // Create new Table
        String query = getCreateTable(tableName, fields);
        execute(query);

    }


}