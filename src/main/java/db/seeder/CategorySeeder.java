package db.seeder;

import models.Category;

public class CategorySeeder {
    public static void Seed() {
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
