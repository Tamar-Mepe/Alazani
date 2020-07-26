package db.seeder;

import db.DB;
import models.Category;
import models.Product;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ProductSeeder {

    public static void Seed(DB db) {

        //store user ids
//        User user = users.get(rand.nextInt(users.size()));
        List<User> users = User.getAll();
        List<Integer> userId = new ArrayList<>();
        for(User user : users){
            userId.add(user.getId());
        }
        //store category name and ids
        List<Category> categories = Category.getAll();
        HashMap<String,Integer> categoryId = new HashMap<>();
        for (Category category : categories) {
            categoryId.put(category.getName(), category.getId());
        }
        Random rand = new Random();
        // seed
        for(int i = 0; i < 10; i++) {
            new Product("iPhone 11 Pro Max", "Some Description", 999, categoryId.get("Electronics"), 3, userId.get(rand.nextInt(userId.size())), "images/product/iphone.jpeg").save();
            new Product("Yoda Figure", "Some Description", 25, categoryId.get("Toys"), 3, userId.get(rand.nextInt(userId.size())), "images/product/yoda.png").save();
            new Product("Laptop", "Some Description", 999, categoryId.get("Electronics"), 3, userId.get(rand.nextInt(userId.size())), "images/product/laptop.png").save();
            new Product("Converse", "Some Description", 999, categoryId.get("Shoes"), 3, userId.get(rand.nextInt(userId.size())), "images/product/converse.png").save();
            new Product("Toaster", "Some Description", 999, categoryId.get("Home & Kitchen"), 3, userId.get(rand.nextInt(userId.size())), "images/product/toaster.jpg").save();
        }
    }
}
