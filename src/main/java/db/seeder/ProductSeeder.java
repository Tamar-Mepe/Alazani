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
        new Product("iPhone 11 Pro Max", "Some Description", 999, 1, 3, user.getId()).save();
        // ToDo: More Fake Products
    }
}
