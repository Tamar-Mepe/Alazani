package models.db;


import java.sql.SQLException;
import java.util.Map;

public interface DB {
    void execute(String query) throws SQLException;

    // Queries
    String getAll(String tableName);

    String getCreateTable(String tableName, Map<String, String> fields);

    // Execute queries
    void createTable(String tableName, Map<String, String> fields) throws SQLException;
}
