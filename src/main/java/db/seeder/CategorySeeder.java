package db.seeder;

import db.DB;
import models.Category;

public class CategorySeeder {
    public static void Seed(DB db) {
        //new Category("Texnika").save();
        // ToDo: More Fake Products
        new Category("Electronics").save();
        new Category("Shoes").save();
        new Category("Toys").save();
        new Category("Home & Kitchen").save();
    }
}
