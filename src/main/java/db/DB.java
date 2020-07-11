package db;


import java.sql.SQLException;
import java.util.Map;

public interface DB {
    void execute(String query) throws SQLException;

    // Queries
    String getCreateTable(String tableName, Map<String, String[]> fields);

    String getCreateDatabase();

    // Execute queries
    void createTable(String tableName, Map<String, String[]> fields) throws SQLException;

    void createDatabase() throws SQLException;
}
