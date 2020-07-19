package db;


import java.sql.SQLException;
import java.util.Map;

public interface DB {
    void execute(String query) throws SQLException;

    int executeInsert(String query) throws SQLException;

    // Queries
    String getCreateTable(String tableName, Map<String, Object> fields);

    String getCreateDatabase();

    String getInsertInto(String tableName, Map<String, String> fields);

    // Execute queries
    void createTable(String tableName, Map<String, Object> fields) throws SQLException;

    void createDatabase() throws SQLException;

    int insertInto(String tableName, Map<String, String> fields) throws SQLException;

    void updateInfo(String tableName, int id, Map<String, String> fields) throws SQLException;
}
