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

    public int getId() {
        return id;
    }

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
    public Map<String, Object> JavaToDB() {
        throw new Error("JavaToDB Function should be overrided");
    }

    public Object save() {
        Map<String, Object> field = JavaToDB();
        id = this.saveIntoTable(field);
        return this;
    }

    public boolean update() {
        Map<String, Object> field = JavaToDB();
        return this.updateTable(id, field);
    }

    // Getting From Database
    public static Map<String, String> getGeneric(String table_name, int id) {
        try {
            return BaseModel.getOneRecord(table_name, id);
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }

    public static List<Map<String, String>> getAllGeneric(String table_name) {
        List<Map<String, String>> allEntries = new ArrayList<>();
        try {
            List<Map<String, String>> records = BaseModel.getAllRecords(table_name);
            allEntries.addAll(records);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allEntries;
    }
}
