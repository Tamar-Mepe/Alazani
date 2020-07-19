package db.seeder;

import db.DB;
import models.Product;

public class ProductSeeder {

    public static void Seed(DB db){
        new Product("iPhone 11 Pro Max", "Some Description", 999, 1, 3).save();
        // ToDo: More Fake Products
    }
}
