package tests;

import db.DB;
import db.Migration;
import db.MySQL;
import models.Product;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private DB db;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        db = MySQL.getInstance();
        Migration.createTables(db);
    }
//    public Product(String name, String description, double price, int category_id, int quantity, int user_id)

    @Test
    void get() throws SQLException {
        // Save To DB
        Product product = new Product("Bu", "fav predator", 100, -1, 3, -1);
        product.save();
        int saved_id = product.getId();
//        TODO:
        Product new_product = Product.get(saved_id);

        assertEquals(new_product.getId(), saved_id);
        assertEquals(new_product.getName(), product.getName());
        assertEquals(new_product.getDescription(), product.getDescription());
        assertEquals(new_product.getQuantity(), product.getQuantity());
        assertEquals(new_product.getPrice(), product.getPrice());
        assertEquals(new_product.getCategory_id(), product.getCategory_id());
        assertEquals(new_product.getUser_id(), product.getUser_id());

    }

    @Test
    void getAll() throws SQLException {
        // Initialize all users
        List<Product> allProducts = new ArrayList<Product>() {
            {
                add(new Product("p1","d1",10,-1,10000,-1));
                add(new Product("p2","d2",100,-1,1000,-1));
                add(new Product("p3","d3",1000,-1,100,-1));
                add(new Product("p4","d4",10000,-1,10,-1));
                add(new Product("p5","d5",100000,-1,1,-1));
            }
        };

        // Save all of them in DB
        for (Product product : allProducts)
            product.save();

        List<Product> allProductsDB = Product.getAll();
        for (int i = 0; i < allProductsDB.size(); i++) {
            Product productDB = allProductsDB.get(i);
            Product product = allProducts.get(i);
            assertEquals(productDB.getId(), product.getId());
            assertEquals(productDB.getName(), product.getName());
            assertEquals(productDB.getDescription(), product.getDescription());
            assertEquals(productDB.getQuantity(), product.getQuantity());
            assertEquals(productDB.getPrice(), product.getPrice());
            assertEquals(productDB.getCategory_id(), product.getCategory_id());
            assertEquals(productDB.getUser_id(), product.getUser_id());
        }
    }
    @Test
    void update() throws SQLException {
        // Save To DB
        Product product = new Product("Bu","predator breed",100,-1,1,-1);
        product.save();
        int saved_id = product.getId();

        // Update user
        product.setName("name_changed");
        product.setDescription("description_changed");
        product.setQuantity(3);
        product.setPrice(5);
        product.setCategory_id(7);
        product.setUser_id(9);

        // Should fail
        Product new_product_tmp = Product.get(saved_id);
        assertEquals(new_product_tmp.getId(), saved_id);
        assertNotEquals(new_product_tmp.getName(), product.getName());
        assertNotEquals(new_product_tmp.getDescription(), product.getDescription());
        assertNotEquals(new_product_tmp.getQuantity(), product.getQuantity());
        assertNotEquals(new_product_tmp.getPrice(), product.getPrice());
        assertNotEquals(new_product_tmp.getCategory_id(), product.getCategory_id());
        assertNotEquals(new_product_tmp.getUser_id(), product.getUser_id());

        // Should Pass after updating
        product.update();
        Product new_product = Product.get(saved_id);
        assertEquals(new_product.getId(), saved_id);
        assertEquals(new_product.getName(), product.getName());
        assertEquals(new_product.getDescription(), product.getDescription());
        assertEquals(new_product.getQuantity(), product.getQuantity());
        assertEquals(new_product.getPrice(), product.getPrice());
        assertEquals(new_product.getCategory_id(), product.getCategory_id());
        assertEquals(new_product.getUser_id(), product.getUser_id());

    }

}