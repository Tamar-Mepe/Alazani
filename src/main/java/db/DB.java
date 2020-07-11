package db;


import java.sql.SQLException;
import java.util.Map;

public interface DB {
    void execute(String query) throws SQLException;

    // Queries
    String getCreateTable(String tableName, Map<String, Object> fields);

    String getCreateDatabase();

    String getInsertInto(String tableName, Map<String, String> fields);

    // Execute queries
    void createTable(String tableName, Map<String, Object> fields) throws SQLException;

    void createDatabase() throws SQLException;

    void insertInto(String tableName, Map<String, String> fields) throws SQLException;
}
