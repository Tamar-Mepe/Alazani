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
    private final String table_name;

    public BaseModel(String table_name) {
        this.table_name = table_name;
        try {
            db = MySQL.getInstance();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void saveIntoTable(Map<String, String> values) {
        try {
            this.db.insertInto(this.table_name, values);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
