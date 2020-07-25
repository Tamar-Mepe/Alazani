package tests;

import db.DB;
import db.Migration;
import db.MySQL;
import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private DB db;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        db = MySQL.getInstance();
        Migration.createTables(db);
    }

    @Test
    void get() throws SQLException {
        // Save To DB
        Product product = new Product("Bu", "fav predator", 100, -1, 3, -1, "image address");
        product.save();
        int savedId = product.getId();
//        TODO:
        Product newProduct = Product.get(savedId);

        assertEquals(newProduct.getId(), savedId);
        assertEquals(newProduct.getName(), product.getName());
        assertEquals(newProduct.getDescription(), product.getDescription());
        assertEquals(newProduct.getQuantity(), product.getQuantity());
        assertEquals(newProduct.getPrice(), product.getPrice());
        assertEquals(newProduct.getCategoryId(), product.getCategoryId());
        assertEquals(newProduct.getUserId(), product.getUserId());
        assertEquals(newProduct.getImageAddress(), product.getImageAddress());
    }

    @Test
    void getAll() throws SQLException {
        // Initialize all products
        List<Product> allProducts = new ArrayList<Product>() {
            {
                add(new Product("p1", "d1", 10, -1, 10000, -1, "address"));
                add(new Product("p2", "d2", 100, -1, 1000, -1, "address"));
                add(new Product("p3", "d3", 1000, -1, 100, -1, "address"));
                add(new Product("p4", "d4", 10000, -1, 10, -1, "address"));
                add(new Product("p5", "d5", 100000, -1, 1, -1, "address"));
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
            assertEquals(productDB.getCategoryId(), product.getCategoryId());
            assertEquals(productDB.getUserId(), product.getUserId());
            assertEquals(productDB.getImageAddress(), product.getImageAddress());
        }
    }

    @Test
    void update() throws SQLException {
        // Save To DB
        Product product = new Product("Bu", "predator breed", 100, -1, 1, -1, "address");
        product.save();
        int savedId = product.getId();

        // Update product
        product.setName("name_changed");
        product.setDescription("description_changed");
        product.setQuantity(3);
        product.setPrice(5);
        product.setCategoryId(7);
        product.setUserId(9);
        product.setImageAddress("image address");
        // Should fail
        Product newProductTmp = Product.get(savedId);
        assertEquals(newProductTmp.getId(), savedId);
        assertNotEquals(newProductTmp.getName(), product.getName());
        assertNotEquals(newProductTmp.getDescription(), product.getDescription());
        assertNotEquals(newProductTmp.getQuantity(), product.getQuantity());
        assertNotEquals(newProductTmp.getPrice(), product.getPrice());
        assertNotEquals(newProductTmp.getCategoryId(), product.getCategoryId());
        assertNotEquals(newProductTmp.getUserId(), product.getUserId());
        assertNotEquals(newProductTmp.getImageAddress(), product.getImageAddress());

        // Should Pass after updating
        product.update();
        Product newProduct = Product.get(savedId);
        assertEquals(newProduct.getId(), savedId);
        assertEquals(newProduct.getName(), product.getName());
        assertEquals(newProduct.getDescription(), product.getDescription());
        assertEquals(newProduct.getQuantity(), product.getQuantity());
        assertEquals(newProduct.getPrice(), product.getPrice());
        assertEquals(newProduct.getCategoryId(), product.getCategoryId());
        assertEquals(newProduct.getUserId(), product.getUserId());
        assertEquals(newProduct.getImageAddress(), product.getImageAddress());
    }
}