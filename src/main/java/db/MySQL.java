package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQL implements DB {
    private Connection connection;

    private static MySQL mysql_single_instance = null;


    private MySQL() throws SQLException, ClassNotFoundException {
        connectToDatabase();
    }

    public static MySQL getInstance() throws SQLException, ClassNotFoundException {
        if (mysql_single_instance == null)
            mysql_single_instance = new MySQL();
        return mysql_single_instance;
    }

    private void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(Environment.URL, Environment.USER, Environment.PASSWORD);
        createDatabase();
    }

    @Override
    public void execute(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
    }

    @Override
    public int executeInsert(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next())
                return generatedKeys.getInt(1);
            else
                throw new SQLException("Creating user failed, no ID obtained.");
        }
    }

    // Queries
    @Override
    public String createDatabaseQuery() {
        return "CREATE DATABASE " + Environment.DATABASE;
    }

    @Override
    public String insertQuery(String tableName, Map<String, Object> fields) {
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
        for (String current : fields.keySet()) {
            query.append(current).append(",");
        }
        query.replace(query.length() - 1, query.length(), ")");
        query.append("\nvalues(");
        for (String current : fields.keySet()) {
            query.append("'").append(fields.get(current)).append("',");
        }
        query.replace(query.length() - 1, query.length(), ")");
        query.append(";");
        return query.toString();
    }

    @Override
    public String createTableQuery(String tableName, Map<String, Object> fields) {
        StringBuilder query = new StringBuilder("CREATE TABLE " + tableName + "(\n");
        for (String name : fields.keySet()) {
            // If Object is Iterable
            StringBuilder constraintString = new StringBuilder();
            Object obj = fields.get(name);
            if (obj instanceof String[]) {
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

    @Override
    public String updateQuery(String tableName, int id, Map<String, Object> fields) {
        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
        for (String current : fields.keySet()) {
            query.append(current).append(" = '").append(fields.get(current)).append("',");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(" WHERE id = ").append(id).append(";");
        return query.toString();
    }

    @Override
    public String getAllQuery(String tableName) {
        return "SELECT * from " + tableName + ";";
    }

    @Override
    public String getQuery(String tableName, int id) {
        return "SELECT * from " + tableName + " WHERE id = " + id + ";";
    }

    // Query Executions
    @Override
    public void createDatabase() throws SQLException {
        // Drop existing database
        execute("DROP DATABASE IF EXISTS " + Environment.DATABASE);
        // Create new Database
        String query = createDatabaseQuery();
        execute(query);
        // Use created Database
        execute("USE " + Environment.DATABASE);
    }

    @Override
    public int insert(String tableName, Map<String, Object> fields) throws SQLException {
        // Create new record
        String query = insertQuery(tableName, fields);
        return executeInsert(query);
    }

    @Override
    public void update(String tableName, int id, Map<String, Object> fields) throws SQLException {
        // Modify existing info
        String query = updateQuery(tableName, id, fields);
        execute(query);
    }

    @Override
    public Map<String, String> get(String tableName, int id) throws SQLException {
        Map<String, String> answer = new HashMap<>();
        String getQuery = getQuery(tableName, id);
        ResultSet rs = executeWithResultSet(getQuery);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String colName = rsmd.getColumnName(i);
                answer.put(colName, rs.getString(colName));
            }
        }
        return answer;
    }

    private ResultSet executeWithResultSet(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery(query);
    }

    @Override
    public List<Map<String, String>> getAll(String tableName) throws SQLException {
        List<Map<String, String>> answer = new ArrayList<>();
        String getAllQuery = getAllQuery(tableName);
        ResultSet resultSet = executeWithResultSet(getAllQuery);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
        while (resultSet.next()) {
            Map<String, String> curr = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String colName = rsmd.getColumnName(i);
                curr.put(colName, resultSet.getString(colName));
            }
            answer.add(curr);
        }
        return answer;
    }

    @Override
    public void createTable(String tableName, Map<String, Object> fields) throws SQLException {
        // Drop existing table
        execute("DROP TABLE IF EXISTS " + tableName);
        // Create new Table
        String query = createTableQuery(tableName, fields);
        execute(query);
    }

}