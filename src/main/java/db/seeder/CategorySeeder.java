package db.seeder;

import models.Category;

public class CategorySeeder {
    public static void Seed() {
        new Category("Accessories").save();
        new Category("Beauty & Personal Care").save();
        new Category("Books").save();
        new Category("Clothes").save();
        new Category("Electronics").save();
        new Category("Food & Drinks").save();
        new Category("Furniture").save();
        new Category("Gaming").save();
        new Category("Health & Household").save();
        new Category("Kitchen").save();
        new Category("Music").save();
        new Category("Office Products").save();
        new Category("Other").save();
        new Category("Phone Accessories").save();
        new Category("Shoes").save();
        new Category("Sport").save();
        new Category("Toys").save();
    }
}
