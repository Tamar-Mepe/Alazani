package tests;

import db.DB;
import db.Migration;
import db.MySQL;
import db.seeder.CategorySeeder;
import db.seeder.ProductSeeder;
import db.seeder.UserSeeder;
import models.Product;
import models.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        DB db = MySQL.getInstance();
        Migration.createTables(db);
    }

    @Test
    void get() {
        // Save To DB
        UserSeeder.Seed();
        CategorySeeder.Seed();
        ProductSeeder.Seed();
        List<Product> products = Product.getAll();

        Purchase purchase = new Purchase(-1, products.get(0).getId(), 100);
        purchase.save();
        int savedId = purchase.getId();
        Purchase newPurchase = Purchase.get(savedId);

        assertEquals(newPurchase.getId(), savedId);
        assertEquals(newPurchase.getUserId(), purchase.getUserId());
        assertEquals(newPurchase.getProductId(), purchase.getProductId());
        assertEquals(newPurchase.getSoldQuantity(), purchase.getSoldQuantity());
    }

    @Test
    void getAll() {
        // Initialize all products
        List<Purchase> AllPurchase = new ArrayList<Purchase>() {
            {
                add(new Purchase(1, 1, 10));
                add(new Purchase(2, 2, 100));
                add(new Purchase(3, 3, 1000));
                add(new Purchase(4, 4, 10000));
                add(new Purchase(5, 5, 100000));
            }
        };

        // Save all of them in DB
        for (Purchase purchase : AllPurchase)
            purchase.save();

        List<Purchase> AllPurchaseDB = Purchase.getAll();
        for (int i = 0; i < AllPurchaseDB.size(); i++) {
            Purchase purchaseDB = AllPurchaseDB.get(i);
            Purchase purchase = AllPurchase.get(i);
            assertEquals(purchaseDB.getId(), purchase.getId());
            assertEquals(purchaseDB.getProductId(), purchase.getProductId());
            assertEquals(purchaseDB.getUserId(), purchase.getUserId());
            assertEquals(purchaseDB.getSoldQuantity(), purchase.getSoldQuantity());
        }
    }
}