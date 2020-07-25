package db.seeder;

import db.DB;
import models.Product;
import models.User;

import java.util.List;
import java.util.Random;

public class ProductSeeder {

    public static void Seed(DB db) {
        List<User> users = User.getAll();
        Random rand = new Random();
        User user = users.get(rand.nextInt(users.size()));
        for(int i = 0; i < 20; i++) {
            new Product("iPhone 11 Pro Max", "Some Description", 999, 1, 3, user.getId(), "images/product/iphone.jpeg").save();
            new Product("Yoda Figure", "Some Description", 25, 2, 3, user.getId(), "images/product/yoda.png").save();
            new Product("Laptop", "Some Description", 999, 1, 3, user.getId(), "images/product/laptop.png").save();
            new Product("Converse", "Some Description", 999, 3, 3, user.getId(), "images/product/converse.png").save();
            new Product("Toaster", "Some Description", 999, 5, 3, user.getId(), "images/product/toaster.jpg").save();
        }
    }
}
