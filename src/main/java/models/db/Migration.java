package models.db;

import models.Product;
import models.User;

import java.sql.SQLException;

public class Migration {
    public static void createDatabase(DB db) throws SQLException {
        db.createDatabase();
    }

    public static void createTables(DB db) throws SQLException {
        db.createTable(User.TABLE_NAME, User.FIELDS);
        db.createTable(Product.TABLE_NAME, Product.FIELDS);
    }
}
