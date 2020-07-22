package db;

import db.seeder.CategorySeeder;
import db.seeder.ProductSeeder;
import db.seeder.UserSeeder;
import models.Category;
import models.Product;
import models.User;

import java.sql.SQLException;

public class Migration {
    public static void createTables(DB db) throws SQLException {
        db.createTable(User.TABLE_NAME, User.FIELDS);
        db.createTable(Product.TABLE_NAME, Product.FIELDS);
        db.createTable(Category.TABLE_NAME, Category.FIELDS);
    }

    public static void createStartingData(DB db) {
        UserSeeder.Seed(db);
        ProductSeeder.Seed(db);
        CategorySeeder.Seed(db);
    }

}
