package db.seeder;

import models.Product;
import models.Purchase;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PurchaseSeeder {

    public static void Seed() {
        List<User> users = User.getAll();
        List<Integer> userId = new ArrayList<>();
        for (User user : users) {
            userId.add(user.getId());
        }
        //store category name and ids
        List<Product> products = Product.getAll();
        List<Integer> productId = new ArrayList<>();
        for (Product product : products) {
            productId.add(product.getId());
        }
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            new Purchase(userId.get(rand.nextInt(userId.size())), productId.get(rand.nextInt(productId.size())), rand.nextInt(10)).save();
        }

    }

}
