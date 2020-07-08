package models.db;

public class MySQL implements DB {
    @Override
    public String getAll(String tableName) {
        return "SELECT * FROM " + tableName + ";";
    }
}
