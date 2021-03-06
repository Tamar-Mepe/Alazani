package db;

import db.seeder.*;
import models.*;

import java.sql.SQLException;

public class Migration {
    public static void createTables(DB db) throws SQLException {
        db.createTable(User.TABLE_NAME, User.FIELDS);
        db.createTable(Product.TABLE_NAME, Product.FIELDS);
        db.createTable(Category.TABLE_NAME, Category.FIELDS);
        db.createTable(Purchase.TABLE_NAME, Purchase.FIELDS);
        db.createTable(Review.TABLE_NAME, Review.FIELDS);
        db.createTable(Cart.TABLE_NAME, Cart.FIELDS);
        db.createTable(Bot.TABLE_NAME, Bot.FIELDS);
    }

    public static void createStartingData() {
        UserSeeder.Seed();
        CategorySeeder.Seed();
        ProductSeeder.Seed();
        ReviewSeeder.Seed();
        BotSeeder.Seed();
    }

}
