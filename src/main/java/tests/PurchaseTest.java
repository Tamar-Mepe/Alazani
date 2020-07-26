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
    private DB db;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        db = MySQL.getInstance();
        Migration.createTables(db);
    }

    @Test
    void get() throws SQLException {
        // Save To DB
        UserSeeder.Seed(db);
        CategorySeeder.Seed(db);
        ProductSeeder.Seed(db);
        List<Product> products  = Product.getAll();

        Purchase purchase = new Purchase(-1,products.get(0).getId(), 100, "22/12/2014");
        purchase.save();
        int savedId = purchase.getId();
//        TODO:
        Purchase newPurchase = Purchase.get(savedId);

        assertEquals(newPurchase.getId(), savedId);
        assertEquals(newPurchase.getUserId(),purchase.getUserId());
        assertEquals(newPurchase.getProductId(), purchase.getProductId());
        assertEquals(newPurchase.getSoldQuantity(), purchase.getSoldQuantity());
        assertEquals(newPurchase.getPurchaseDate(), purchase.getPurchaseDate());
    }

    @Test
    void getAll() throws SQLException {
        // Initialize all products
        List<Purchase> AllPurchase = new ArrayList<Purchase>() {
            {
                add(new Purchase(1, 1, 10, "12/23/2010"));
                add(new Purchase(2, 2, 100, "12/24/2010"));
                add(new Purchase(3, 3, 1000, "12/25/2010"));
                add(new Purchase(4, 4, 10000, "12/26/2010"));
                add(new Purchase(5, 5, 100000, "12/27/2010"));
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
            assertEquals(purchaseDB.getPurchaseDate(), purchase.getPurchaseDate());
        }
    }
}