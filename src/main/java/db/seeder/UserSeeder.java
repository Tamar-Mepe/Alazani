package db.seeder;

import db.DB;
import models.User;

public class UserSeeder {

    public static void Seed(DB db) {
        new User("Admin", "Admin", "admin451", "GM", "admin@gmail").save();
        new User("Tamar", "Davitaia", "101010", "Tamar.Davitaia", "tdavi18@freeuni.edu.ge").save();
        new User("Irakli", "Gabelia", "bubu123", "irakli98", "igabe18@freeuni.edu.ge").save();
        new User("Zura", "Khucishvili", "andzerdzi123", "kakashi", "zkhut16@freeuni.edu.ge").save();
        new User("Saba", "Pochxua", "CkobikobiC", "CSenshi", "spoch16@freeuni.edu.ge").save();
        new User("David", "Targamadze", "Dinamiti", "Va1RuS", "dtarg16@freeuni.edu.ge").save();
    }
}
