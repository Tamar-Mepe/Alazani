package tests;

import db.DB;
import db.Migration;
import db.MySQL;
import models.Category;
import models.Product;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        DB db = MySQL.getInstance();
        Migration.createTables(db);
    }

    @Test
    void javaToDB() {
        User user = new User("f_test", "l_test", "p_test", "u_test", "e_test");
        Map<String, Object> map = user.JavaToDB();
        assertTrue(map.containsKey("first_name") && map.get("first_name").equals("f_test"));
        assertTrue(map.containsKey("last_name") && map.get("last_name").equals("l_test"));
        assertTrue(map.containsKey("password") && map.get("password").equals("p_test"));
        assertTrue(map.containsKey("username") && map.get("username").equals("u_test"));
        assertTrue(map.containsKey("email") && map.get("email").equals("e_test"));
    }

    @Test
    void DBToJava() {
        Map<String, String> map = new LinkedHashMap<String, String>() {
            {
                put("id", "0");
                put("first_name", "f_test");
                put("last_name", "l_test");
                put("password", "p_test");
                put("username", "u_test");
                put("email", "e_test");
            }
        };
        User user = User.DBToJava(map);
        assertEquals(user.getFirstName(), "f_test");
        assertEquals(user.getLastName(), "l_test");
        assertEquals(user.getPassword(), "p_test");
        assertEquals(user.getUsername(), "u_test");
        assertEquals(user.getEmail(), "e_test");
        assertEquals(user.getId(), 0);
    }

    @Test
    void get() {
        // Save To DB
        User user = new User("f_test", "l_test", "p_test", "u_test", "e_test");
        user.save();
        int savedId = user.getId();
        User newUser = User.get(savedId);

        assertEquals(newUser.getId(), savedId);
        assertEquals(newUser.getFirstName(), user.getFirstName());
        assertEquals(newUser.getLastName(), user.getLastName());
        assertEquals(newUser.getUsername(), user.getUsername());
        assertEquals(newUser.getEmail(), user.getEmail());
    }

    @Test
    void getAll() {
        // Initialize all users
        List<User> allUsers = new ArrayList<User>() {
            {
                add(new User("f1", "l1", "p1", "u1", "e1"));
                add(new User("f2", "l2", "p2", "u2", "e2"));
                add(new User("f3", "l3", "p3", "u3", "e3"));
                add(new User("f4", "l4", "p4", "u4", "e5"));
            }
        };

        // Save all of them in DB
        for (User user : allUsers)
            user.save();

        List<User> allUsersDB = User.getAll();
        for (int i = 0; i < allUsers.size(); i++) {
            User userDB = allUsersDB.get(i);
            User user = allUsers.get(i);
            assertEquals(userDB.getFirstName(), user.getFirstName());
            assertEquals(userDB.getLastName(), user.getLastName());
            assertEquals(userDB.getPassword(), user.getPassword());
            assertEquals(userDB.getUsername(), user.getUsername());
            assertEquals(userDB.getEmail(), user.getEmail());
        }
    }

    @Test
    void update() {
        // Save To DB
        User user = new User("f_test", "l_test", "p_test", "u_test", "e_test");
        user.save();
        int savedId = user.getId();

        // Update user
        user.setFirstName("f_changed");
        user.setLastName("l_changed");
        user.setPassword("p_changed");
        user.setUsername("u_changed");
        user.setEmail("e_changed");

        // Should fail
        User newUser = User.get(savedId);
        assertEquals(newUser.getId(), savedId);
        assertNotEquals(newUser.getFirstName(), user.getFirstName());
        assertNotEquals(newUser.getLastName(), user.getLastName());
        assertNotEquals(newUser.getPassword(), user.getPassword());
        assertNotEquals(newUser.getUsername(), user.getUsername());
        assertNotEquals(newUser.getEmail(), user.getEmail());

        // Should Pass after updating
        user.update();
        newUser = User.get(savedId);
        assertEquals(newUser.getId(), savedId);
        assertEquals(newUser.getFirstName(), user.getFirstName());
        assertEquals(newUser.getLastName(), user.getLastName());
        assertEquals(newUser.getPassword(), user.getPassword());
        assertEquals(newUser.getUsername(), user.getUsername());
        assertEquals(newUser.getEmail(), user.getEmail());

    }

    @Test
    public void product() {
        // init categories
        Category cat1 = (Category) new Category("cat1").save();
        Category cat2 = (Category) new Category("cat2").save();

        User user1 = (User) new User("f_test_1", "l_test_1", "p_test_1", "u_test_1", "e_test_1").save();
        User user2 = (User) new User("f_test_2", "l_test_2", "p_test_2", "u_test_2", "e_test_2").save();

        Product prod1 = (Product) new Product("prod1", "desc1", 99.99, cat1.getId(), 100, user1.getId(), null).save();
        Product prod2 = (Product) new Product("prod2", "desc2", 99.99, cat1.getId(), 100, user1.getId(), null).save();
        Product prod3 = (Product) new Product("prod3", "desc3", 99.99, cat1.getId(), 100, user2.getId(), null).save();
        Product prod4 = (Product) new Product("prod4", "desc4", 99.99, cat2.getId(), 100, user2.getId(), null).save();
        Product prod5 = (Product) new Product("prod5", "desc5", 99.99, cat2.getId(), 100, user2.getId(), null).save();

        List<Integer> user1SavedProductIds = new ArrayList<>();
        user1SavedProductIds.add(prod1.getId());
        user1SavedProductIds.add(prod2.getId());
        List<Integer> user2SavedProductIds = new ArrayList<>();
        user2SavedProductIds.add(prod3.getId());
        user2SavedProductIds.add(prod4.getId());
        user2SavedProductIds.add(prod5.getId());
        // check Category.products()
        List<Product> user1Products = user1.products();
        List<Product> user2Products = user2.products();

        for (Product product : user1Products) {
            assertTrue(user1SavedProductIds.contains(product.getId()));
        }
        for (Product product : user2Products) {
            assertTrue(user2SavedProductIds.contains(product.getId()));
        }
        assertEquals(user1Products.size(), 2);
        assertEquals(user2Products.size(), 3);
    }

    @Test
    public void checkUsername() {
        List<User> allUsers = new ArrayList<User>() {
            {
                add(new User("f1", "l1", "p1", "u1", "e1"));
                add(new User("f2", "l2", "p2", "u2", "e2"));
                add(new User("f3", "l3", "p3", "u3", "e3"));
                add(new User("f4", "l4", "p4", "u4", "e5"));
            }
        };

        // Save all of them in DB
        for (User user : allUsers)
            user.save();

        for (User user : allUsers)
            assertEquals(Objects.requireNonNull(User.getWithUserName(user.getUsername())).getId(), user.getId());
    }

    @Test
    public void checkEmail() {
        List<User> allUsers = new ArrayList<User>() {
            {
                add(new User("f1", "l1", "p1", "u1", "e1"));
                add(new User("f2", "l2", "p2", "u2", "e2"));
                add(new User("f3", "l3", "p3", "u3", "e3"));
                add(new User("f4", "l4", "p4", "u4", "e5"));
            }
        };

        // Save all of them in DB
        for (User user : allUsers)
            user.save();

        for (User user : allUsers)
            assertEquals(Objects.requireNonNull(User.getWithEmail(user.getEmail())).getId(), user.getId());
        User user = new User("f1", "l1", "p1", "u10", "e10");
        user.save();
        assertEquals(User.getAll().size(), 5);
        user.deleteRow();
        assertEquals(User.getAll().size(), 4);
    }

}