package db.seeder;

import db.DB;
import models.Category;

public class CategorySeeder {
    public static void Seed(DB db) {
        new Category("Electronics").save();
        new Category("Shoes").save();
        new Category("Toys").save();
        new Category("Kitchen").save();
        new Category("Accessories").save();
        new Category("Clothes").save();
        new Category("Gaming").save();
        new Category("Phone Accessories").save();
    }
}
