package db.seeder;

import models.Bot;

public class BotSeeder {

    public static void Seed() {
        new Bot("How can I order?", "You can order easily using our online platform. First, sign in and when you find a product you need, you can add it to cart " +
                "and go through the ordering process. " +
                "Your orders will also be stored to your account.").save();
        new Bot("What are the delivery charges?", "This year our shipping is " +
                "absolutely free for everyone.").save();
        new Bot("Do you have all products in stock?", "When you press the item, you can see how much is still available." +
                "If it says \"Out of stock\", that means it is not available at the moment.").save();
        new Bot("Can I return a product?", "If you want to return a product, please contact support@alazani.com").save();
        new Bot("Can I change my profile settings?", "By clicking profile, you can change your profile settings. " +
                "Please note, that usernames cannot be changed.").save();
        new Bot("I can not find my question", "Please contact us at support@alazani.com").save();
    }
}
