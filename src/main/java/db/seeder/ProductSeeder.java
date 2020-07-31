package db.seeder;

import models.Category;
import models.Product;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ProductSeeder {

    public static void Seed() {
        //store user ids
        List<User> users = User.getAll();
        List<Integer> userId = new ArrayList<>();
        for (User user : users)
            userId.add(user.getId());

        //store category name and ids
        List<Category> categories = Category.getAll();
        HashMap<String, Integer> categoryId = new HashMap<>();
        for (Category category : categories)
            categoryId.put(category.getName(), category.getId());

        // seed
        Random rand = new Random();
        new Product("iPhone 11 Pro Max", "Gacvlac mawyobs tirkmelshi", 999, categoryId.get("Electronics"), 2, userId.get(rand.nextInt(userId.size())), "/images/product/iphone.jpeg").save();
        new Product("Yoda Figure", "Buds all green like yoda", 25, categoryId.get("Toys"), 50, userId.get(rand.nextInt(userId.size())), "/images/product/yoda.png").save();
        new Product("Laptop", "MacBook Pro desit tishi laria micemuli prosta mechqareba da iafad vyidi", 3000, categoryId.get("Electronics"), 3, userId.get(rand.nextInt(userId.size())), "/images/product/laptop.png").save();
        new Product("Converse", "It does not need description", 999, categoryId.get("Shoes"), 3, userId.get(rand.nextInt(userId.size())), "/images/product/converse.png").save();
        new Product("Toaster", "Toasts bread or other identical things", 999, categoryId.get("Kitchen"), 3, userId.get(rand.nextInt(userId.size())), "/images/product/toaster.jpg").save();
        new Product("Owl", "Toy owl with ability to kiss", 69, categoryId.get("Toys"), 3, userId.get(rand.nextInt(userId.size())), "/images/product/owl.png").save();
        new Product("Hood", "Creepy owl hood", 45, categoryId.get("Clothes"), 6, userId.get(rand.nextInt(userId.size())), "/images/product/hood.jpg").save();
        new Product("Keyboard", "Gaming keyboard", 13, categoryId.get("Gaming"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/keyboard.jpg").save();
        new Product("Airpods", "Wireless headphones", 11, categoryId.get("Phone Accessories"), 13, userId.get(rand.nextInt(userId.size())), "/images/product/airpod.jpeg").save();
        new Product("Owl Case", "Case with ability to kiss", 13, categoryId.get("Phone Accessories"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/case1.jpg").save();
        new Product("Busu", "Bu with ability to su", 13, categoryId.get("Phone Accessories"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/case2.jpg").save();
        new Product("Headset", "Gaming headset", 13, categoryId.get("Gaming"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/headset.jpg").save();
        new Product("Mousepad", "Gaming mousepad", 13, categoryId.get("Gaming"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/mousepad.jpg").save();
        new Product("Mouse", "Gaming mouse", 13, categoryId.get("Gaming"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/mouse.jpg").save();
        new Product("Necklace", "Owl necklace", 13, categoryId.get("Accessories"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/necklace.jpg").save();
        new Product("Phone Charger", "Wired phone charger", 13, categoryId.get("Phone Accessories"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/phone_charger.jpg").save();
        new Product("Sapilpile Da Samarile", "Salt and Pepper shakers", 13, categoryId.get("Kitchen"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/salt_pepper_shaker.jpg").save();
        new Product("Shoes", "Owl shoes", 23, categoryId.get("Shoes"), 5, userId.get(rand.nextInt(userId.size())), "/images/product/shoes.jpg").save();
        new Product("Sunglasses", "Very stylish sunglasses", 223, categoryId.get("Accessories"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/sunglasses.jpg").save();
        new Product("Swimsuit", "Great swimsuit", 223, categoryId.get("Clothes"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/swimsuit.jpg").save();
        new Product("Owl Talisman", "No words needed", 11, categoryId.get("Accessories"), 3, userId.get(rand.nextInt(userId.size())), "/images/product/talisman.jpg").save();
        new Product("Wireless Charger", "Great charger", 9, categoryId.get("Phone Accessories"), 3, userId.get(rand.nextInt(userId.size())), "/images/product/wirelessCharger.jpg").save();
        new Product("Fabio Canavarro", "Iebs icavs", 3, categoryId.get("Toys"), 3, userId.get(rand.nextInt(userId.size())), "/images/product/fabio.jpg").save();
        new Product("NARS Lipstick", "A new lineup of Lipstick featuring 60 shades in matte, satin, and sheer finishes", 72, categoryId.get("Beauty & Personal Care"), 12, userId.get(rand.nextInt(userId.size())), "/images/product/lipstick.jpg").save();
        new Product("Selpak Tissue", "Selpak Pocket Hanky Lotioned Perfumed Tissue - " +
                "10 Sheets/piece", 20, categoryId.get("Beauty & Personal Care"), 5, userId.get(rand.nextInt(userId.size())), "/images/product/selpak.jpg").save();
        new Product("Vepkhistkaosani", "Georgian medieval epic poem, " +
                "written in the 12th century by poet Shota Rustaveli", 30, categoryId.get("Books"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/vepkh.jpg").save();
        new Product("Cheetos", "Cheetos Crunchy Flamin Hot Cheese " +
                "Flavored Snacks, 8.5 oz Bag", 1.85, categoryId.get("Food & Drinks"), 30, userId.get(rand.nextInt(userId.size())), "/images/product/cheetos.jpeg").save();
        new Product("Sofa", "Imagine this tan leather sofa centered on an accent wall or flaunting a beautiful throw", 215, categoryId.get("Furniture"), 1, userId.get(rand.nextInt(userId.size())), "/images/product/sofa.jpg").save();
        new Product("Supplements", "Vitalfuse Biotin 10000mcg Hair Growth Supplements for Women - " +
                "for Healthy Hair Skin Nails Vitamins for Women", 23, categoryId.get("Health & Household"), 55, userId.get(rand.nextInt(userId.size())), "/images/product/hair.jpg").save();
        new Product("Yamaha Guitar", "Great guitar for playing " +
                "Chemo Magnolia", 453, categoryId.get("Music"), 7, userId.get(rand.nextInt(userId.size())), "/images/product/guitar.jpg").save();
        new Product("Magic Pen", "If you have 1000 word essay to write and " +
                "you want to watch Netflix instead, this pen will write it for you", 5637, categoryId.get("Office Products"), 10, userId.get(rand.nextInt(userId.size())), "/images/product/pen.jpg").save();
        new Product("Resistance Band Set", "Get your desired body with this amazing resistance band set " +
                "and make your ex cry for breaking up with you", 14, categoryId.get("Sport"), 15, userId.get(rand.nextInt(userId.size())), "/images/product/band.jpg").save();
        new Product("Firework Box", "Great thing to get on your neighbours nerves", 56.99, categoryId.get("Other"), 4, userId.get(rand.nextInt(userId.size())), "/images/product/firework.jpg").save();
        new Product("Samsung TV", "Series 5 Samsung Smart TV. Thanks to a powerful Quad Core processor, your Samsung Smart TV offers enhanced performance", 899.99, categoryId.get("Electronics"), 11, userId.get(rand.nextInt(userId.size())), "/images/product/tv.jpg").save();


        new Product("Rossignol skis", "Rossignol skis Hero Athlete FIS GS is the new, F.I.S. approved, World Cup GS ski designed for elite-level athletes and competitive racers", 599.99, categoryId.get("Sport"), 10, userId.get(rand.nextInt(userId.size())), "/images/product/ski.jpg").save();
        new Product("Yoga Mat", "Prevent hands and feet slipping during asana practice in modern yoga as exercise. Easy to clean", 12.89, categoryId.get("Sport"), 7, userId.get(rand.nextInt(userId.size())), "/images/product/mat.jpg").save();
        new Product("Programmer T-Shirt", "Buy this creative T-Shirt, so that everyone knows you are not like others", 23.59, categoryId.get("Clothes"), 12, userId.get(rand.nextInt(userId.size())), "/images/product/programmer.jpg").save();
        new Product("Adidas Shoes", "Adidas Superstar shoes made their debut on the " +
                "basketball court and have been an icon ever since", 45.99, categoryId.get("Shoes"), 2, userId.get(rand.nextInt(userId.size())), "/images/product/adidas.jpg").save();
        new Product("Apple Watch Series 4", "Incorporates fitness tracking and health-oriented capabilities with integration with iOS and other Apple products and services.", 72, categoryId.get("Electronics"), 12, userId.get(rand.nextInt(userId.size())), "/images/product/watch.jpg").save();
    }
}
