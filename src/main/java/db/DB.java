package db;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DB {
    void execute(String query) throws SQLException;

    int executeInsert(String query) throws SQLException;

    // Queries
    String createTableQuery(String tableName, Map<String, Object> fields);

    String createDatabaseQuery();

    String insertQuery(String tableName, Map<String, Object> fields);

    String updateQuery(String tableName, int id, Map<String, Object> fields);

    String getAllQuery(String tableName);

    String getQuery(String tableName, int id);

    // Execute queries
    void createTable(String tableName, Map<String, Object> fields) throws SQLException;

    void createDatabase() throws SQLException;

    int insert(String tableName, Map<String, Object> fields) throws SQLException;

    void update(String tableName, int id, Map<String, Object> fields) throws SQLException;

    Map<String, String> get(String tableName, int id) throws SQLException;

    List<Map<String, String>> getAll(String tableName) throws SQLException;
}
