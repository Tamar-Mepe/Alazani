package tests;

import db.DB;
import db.Migration;
import db.MySQL;
import db.seeder.CategorySeeder;
import db.seeder.ProductSeeder;
import db.seeder.UserSeeder;
import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    private DB db;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        db = MySQL.getInstance();
        Migration.createTables(db);
    }

    @Test
    void getAll() throws SQLException {
        // Save To DB
        UserSeeder.Seed(db);
        CategorySeeder.Seed(db);
        ProductSeeder.Seed(db);

        new Cart(1,1,1).save();
        new Cart(2,2,1).save();
        new Cart(3,3,1).save();
        new Cart(1,4,1).save();
        List<Cart> carts = Cart.getAll();
        assertEquals(4,carts.size());
        Random rand = new Random();
        for(int i=0; i<139; i++){
            new Cart(rand.nextInt(10),rand.nextInt(10),rand.nextInt(10)).save();
        }
        List<Cart> carts2 = Cart.getAll();
        assertEquals(143,carts2.size());
    }
    @Test
    void updateQuantity() throws SQLException {
        // Save To DB
        UserSeeder.Seed(db);
        CategorySeeder.Seed(db);
        ProductSeeder.Seed(db);

        Cart cart = (Cart)new Cart(1,1,3).save();
        cart.updateQuantity(10);
        int saved_id = cart.getId();
        assertNotEquals(10,Cart.get(saved_id).getQuantity());
        assertEquals(3,Cart.get(saved_id).getQuantity());
        cart.update();
        assertEquals(10,Cart.get(saved_id).getQuantity());
    }
    @Test
    void getProducts() throws SQLException {
//         Save To DB
        UserSeeder.Seed(db);
        CategorySeeder.Seed(db);
        ProductSeeder.Seed(db);

        new Cart(1,1,1).save();
        new Cart(1,2,1).save();
        new Cart(1,3,1).save();
        new Cart(2,2,1).save();
        new Cart(3,3,1).save();
        new Cart(1,4,1).save();
//        Map<Product, Integer> prodMap=Cart.;

    }

}