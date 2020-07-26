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
            new Product("iPhone 11 Pro Max", "gacvlac mawyobs tirkmelshi", 999, categoryId.get("Electronics"), 2, userId.get(rand.nextInt(userId.size())), "images/product/iphone.jpeg").save();
            new Product("Yoda Figure", "buds all green like yoda", 25, categoryId.get("Toys"), 16, userId.get(rand.nextInt(userId.size())), "images/product/yoda.png").save();
            new Product("Laptop", "macbook pro desit tishi laria micemuli prosta mechqareba da iafad vyidi", 3000, categoryId.get("Electronics"), 3, userId.get(rand.nextInt(userId.size())), "images/product/laptop.png").save();
            new Product("Converse", "it does not need description", 999, categoryId.get("Shoes"), 3, userId.get(rand.nextInt(userId.size())), "images/product/converse.png").save();
            new Product("Toaster", "tosts bread or other identical things", 999, categoryId.get("Kitchen"), 3, userId.get(rand.nextInt(userId.size())), "images/product/toaster.jpg").save();
            new Product("Owl", "toy owl with ability to kiss", 69, categoryId.get("Toys"), 3, userId.get(rand.nextInt(userId.size())), "images/product/owl.png").save();
            new Product("Hood", "creepy owl hood", 45, categoryId.get("Clothes"), 6, userId.get(rand.nextInt(userId.size())), "images/product/hood.jpg").save();
            new Product("Keyboard", "gaming keyboard", 13, categoryId.get("Gaming"), 1, userId.get(rand.nextInt(userId.size())), "images/product/keyboard.jpg").save();
            new Product("Airpods", "wireless headphones", 11, categoryId.get("Phone Accessories"), 13, userId.get(rand.nextInt(userId.size())), "images/product/airpod.jpeg").save();
            new Product("Owl Case", "case with ability to kiss", 13, categoryId.get("Phone Accessories"), 1, userId.get(rand.nextInt(userId.size())), "images/product/case1.jpg").save();
            new Product("Busu", "Bu with ability to su", 13, categoryId.get("Phone Accessories"), 1, userId.get(rand.nextInt(userId.size())), "images/product/case2.jpg").save();
            new Product("Ass case", "Kiss me owl", 13, categoryId.get("Phone Accessories"), 1, userId.get(rand.nextInt(userId.size())), "images/product/case3.jfif").save();
            new Product("Headset", "Gaming headset", 13, categoryId.get("Gaming"), 1, userId.get(rand.nextInt(userId.size())), "images/product/headset.jpg").save();
            new Product("Mousepad", "Gaming mousepad", 13, categoryId.get("Gaming"), 1, userId.get(rand.nextInt(userId.size())), "images/product/mousepad.jpg").save();
            new Product("Mouse", "Gaming mouse", 13, categoryId.get("Gaming"), 1, userId.get(rand.nextInt(userId.size())), "images/product/mouse.jpg").save();
            new Product("Necklace", "Owl necklace", 13, categoryId.get("Accessories"), 1, userId.get(rand.nextInt(userId.size())), "images/product/necklace.jpg").save();
            new Product("Phone charger", "Wired phone charger", 13, categoryId.get("Phone Accessories"), 1, userId.get(rand.nextInt(userId.size())), "images/product/phone charger.jpg").save();
            new Product("Sapilpilee da Samarile", "Salt and Pepper shakers", 13, categoryId.get("Kitchen"), 1, userId.get(rand.nextInt(userId.size())), "images/product/salt&pepper shaker.jpg").save();
            new Product("Shoe", "owl shoes", 23, categoryId.get("Shoes"), 5, userId.get(rand.nextInt(userId.size())), "images/product/shoes.jpg").save();
            new Product("Sunglasses", "armenian sunglasses", 223, categoryId.get("Accessories"), 1, userId.get(rand.nextInt(userId.size())), "images/product/sunglasses.jpg").save();
            new Product("Swimsuit", "boobs are blessing u and this swimsuit blesses boobs", 223, categoryId.get("Clothes"), 1, userId.get(rand.nextInt(userId.size())), "images/product/swim suit.jpg").save();
            new Product("Owl talisman", "no words needed", 11, categoryId.get("Accessories"), 3, userId.get(rand.nextInt(userId.size())), "images/product/talisman.jpg").save();
            new Product("Wireless charger", "tamaram traki washaleto", 9, categoryId.get("Phone Accessories"), 3, userId.get(rand.nextInt(userId.size())), "images/product/wirelessCharger.jpg").save();

    }
}
