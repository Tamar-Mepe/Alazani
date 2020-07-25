package db.seeder;
import db.DB;
import models.Purchase;
import models.User;
import models.Product;
import java.util.List;
import java.util.Random;

public class PurchaseSeeder {

    public static void Seed(DB db) {
        List<User> users = User.getAll();
        List<Product> Products = Product.getAll();
        Random rand = new Random();
        User user = users.get(rand.nextInt(users.size()));
        Product product = Products.get(rand.nextInt(Products.size()));
        new Purchase(user.getId(),product.getId(), 999, "23/05/2020").save();
        new Purchase(user.getId(),product.getId(), 100, "23/05/2020").save();
        new Purchase(user.getId(),product.getId(), 52, "23/05/2020").save();




    }

}
