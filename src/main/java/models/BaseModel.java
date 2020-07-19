package models;

import db.DB;
import db.MySQL;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BaseModel {
    // Model Table Name
     public static final String TABLE_NAME = "table";

    // Model Table Fields
    public static Map<String, String[]> FIELDS = new HashMap<>();

    static {
        FIELDS.put("id", new String[]{"int"});
    }

    // Instance variables
    private DB db;
    protected int id = -1;
    private final String table_name;

    protected BaseModel(String table_name) {
        this.table_name = table_name;
        try {
            db = MySQL.getInstance();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    protected static Map<String, String> getOneRecord(String tableName, int id) throws SQLException, ClassNotFoundException {
        DB db = MySQL.getInstance();
        return db.get(tableName, id);
    }

    protected int saveIntoTable(Map<String, Object> values) {
        try {
            return this.db.insert(this.table_name, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected boolean updateTable(int id, Map<String, Object> values) {
        if (id <= 0)
            return false;

        try {
            this.db.update(this.table_name, id, values);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
