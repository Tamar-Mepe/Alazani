package models;

import db.DB;
import db.MySQL;

import java.sql.SQLException;
import java.util.*;

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

    protected static List<Map<String, String>> getAllRecords(String tableName) throws SQLException, ClassNotFoundException {
        DB db = MySQL.getInstance();
        return db.getAll(tableName);
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

    public void setId(int id) {
        this.id = id;
    }

    //  Adding to Database
    protected Map<String, Object> JavaToDB() {
        // Translate java instance variables to Database fields
        return new LinkedHashMap<String, Object>() {
            {
                // put("db_field_name", java_var);
            }
        };
    }

    public void save() {
        Map<String, Object> field = JavaToDB();
        id = this.saveIntoTable(field);
    }

    public boolean update() {
        Map<String, Object> field = JavaToDB();
        return this.updateTable(id, field);
    }

    // Getting From Database
    private static Object DBToJava(Map<String, String> fields) {
        return null;
    }

    protected static Object get(int id) {
        try {
            Map<String, String> fields = BaseModel.getOneRecord(TABLE_NAME, id);
            return DBToJava(fields);
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }

    public static List<Object> getAll() {
        List<Object> allEntries = new ArrayList<>();

        try {
            List<Map<String, String>> records = BaseModel.getAllRecords(TABLE_NAME);
            for (Map<String, String> fields : records) {
                allEntries.add(DBToJava(fields));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allEntries;
    }
}
