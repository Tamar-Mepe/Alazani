package db.seeder;

import models.User;
import utils.BCrypt;

public class UserSeeder {

    public static void Seed() {

        new User("Admin", "Admin", BCrypt.hashpw("admin451", BCrypt.gensalt()), "GM", "admin@gmail.com").save();
        new User("Tamar", "Davitaia", BCrypt.hashpw("101010", BCrypt.gensalt()), "Tamar.Davitaia", "tdavi18@freeuni.edu.ge").save();
        new User("Irakli", "Gabelia", BCrypt.hashpw("bubu123", BCrypt.gensalt()), "irakli98", "igabe18@freeuni.edu.ge").save();
        new User("Zura", "Khucishvili", BCrypt.hashpw("anderdzi123", BCrypt.gensalt()), "kakashi", "zkhut16@freeuni.edu.ge").save();
        new User("Saba", "Pochxua", BCrypt.hashpw("123", BCrypt.gensalt()), "CSenshi", "spoch16@freeuni.edu.ge").save();
        new User("David", "Targamadze", BCrypt.hashpw("1", BCrypt.gensalt()), "Va1RuS", "dtarg16@freeuni.edu.ge").save();
    }
}
