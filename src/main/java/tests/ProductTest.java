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
        Migration.createDatabase(db);
        Migration.createTables(db);
    }
//    public Product(String name, String description, double price, int category_id, int quantity, int user_id)

    @Test
    void get() throws SQLException {
        // Save To DB
        Product product = new Product("Bu", "fav predator", 100,-1,3,-1);
        product.save();
        int saved_id = product.getId();
        User new_user =  User.get(saved_id);

//        assertEquals(new_user.getId(), saved_id);
//        assertEquals(new_user.getFirstName(), user.getFirstName());
//        assertEquals(new_user.getLastName(), user.getLastName());
//        assertEquals(new_user.getPassword(), user.getPassword());
    }


}