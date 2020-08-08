package db.seeder;

import models.Bot;

public class BotSeeder {

    public static void Seed() {
        new Bot("question 1", "answer 1").save();
        new Bot("question 2", "answer 2").save();
        new Bot("question 3", "answer 3").save();
    }
}
