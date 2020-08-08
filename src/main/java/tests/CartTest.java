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
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        DB db = MySQL.getInstance();
        Migration.createTables(db);
        UserSeeder.Seed();
        CategorySeeder.Seed();
        ProductSeeder.Seed();
    }

    @Test
    public void getAll() {
        // Save To DB
        new Cart(1, 1, 1).save();
        new Cart(2, 2, 1).save();
        new Cart(3, 3, 1).save();
        new Cart(1, 4, 1).save();

        List<Cart> carts = Cart.getAll();
        assertEquals(4, carts.size());
        Random rand = new Random();

        for (int i = 0; i < 139; i++) {
            new Cart(rand.nextInt(10), rand.nextInt(10), rand.nextInt(10)).save();
        }

        List<Cart> carts2 = Cart.getAll();
        assertEquals(143, carts2.size());
    }

    @Test
    public void emptyCart() {
        int userId = 123;
        assertNull(Cart.getCart(123, 1));
    }

    @Test
    public void updateQuantity() {
        // Save To DB
        Cart cart = (Cart) new Cart(1, 1, 3).save();
        cart.updateQuantity(10);
        int savedId = cart.getId();

        assertNotEquals(10, Cart.get(savedId).getQuantity());
        assertEquals(3, Cart.get(savedId).getQuantity());
        assertEquals(3, Objects.requireNonNull(Cart.getCart(1, 1)).getQuantity());

        cart.update();
        assertEquals(10, Cart.get(savedId).getQuantity());
        assertEquals(10, Objects.requireNonNull(Cart.getCart(1, 1)).getQuantity());

        cart.setQuantity(15);
        cart.update();

        assertEquals(15, Cart.get(savedId).getQuantity());
        assertEquals(15, Objects.requireNonNull(Cart.getCart(1, 1)).getQuantity());

    }

    @Test
    public void getProducts() {
        // Save To DB
        new Cart(1, 1, 1).save();
        new Cart(1, 2, 1).save();
        new Cart(1, 3, 1).save();
        new Cart(2, 2, 1).save();
        new Cart(3, 3, 1).save();
        new Cart(1, 4, 1).save();

        int lastCartProdId = 2;
        Cart lastCart = new Cart(2, lastCartProdId, 1);
        lastCart.save();
        assertEquals(lastCart.getProductId(), lastCartProdId);

        Map<Product, Integer> user1Products = Cart.getProductsByUserId(1);
        assertEquals(4, user1Products.size());
        new Cart(1, 4, 1).save();
        user1Products = Cart.getProductsByUserId(1);
        assertEquals(4, user1Products.size());

        for (int i = 0; i < 7; i++) {
            new Cart(1, 1, 3).save();
        }

        user1Products = Cart.getProductsByUserId(1);
        for (Product key : user1Products.keySet()) {
            if (key.getId() == 4) {
                assertEquals(2, user1Products.get(key));
            }
            if (key.getId() == 1) {
                assertEquals(22, user1Products.get(key));
            }
        }
    }

    @Test
    public void removeProducts() {
        int quant = 9;
        int userId = 1;
        int prodId = 2;

        Cart currCart = new Cart(userId, prodId, quant);
        currCart.save();
        Cart.removeCart(userId, prodId);
        assertEquals(0, Cart.getCartsByProductID(prodId).size());
    }
}