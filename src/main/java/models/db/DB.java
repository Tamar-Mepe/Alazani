package models.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DB {
    String getAll(String tableName);
    Connection connectToDatabase() throws ClassNotFoundException, SQLException;
}
