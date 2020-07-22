package db.seeder;

import db.DB;
import models.User;

public class UserSeeder {

    public static void Seed(DB db) {
        new User("Admin", "Admin", User.encrypt("admin451")).save();
//        new User("John", "Doe").save();
//        new User("Tamar", "Davitaia").save();
//        new User("Irakli", "Gabelia").save();
//        new User("Davit", "Targamadze").save();
//        new User("Zurab", "Khutsishvili").save();
//        new User("Gauq", "Mebulia").save();
    }
}
