package db;


import java.sql.SQLException;
import java.util.Map;

public interface DB {
    void execute(String query) throws SQLException;

    // Queries
    String getCreateTable(String tableName, Map<String, Object> fields);

    String getCreateDatabase();

    // Execute queries
    void createTable(String tableName, Map<String, Object> fields) throws SQLException;

    void createDatabase() throws SQLException;
}
