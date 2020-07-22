package tests;

import db.DB;
import db.Migration;
import db.MySQL;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private DB db;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        db = MySQL.getInstance();
        Migration.createTables(db);
    }

    @Test
    void javaToDB() {
        User user = new User("f_test", "l_test", "p_test");
        Map<String, Object> map = user.JavaToDB();
        assert (map.containsKey("first_name") && map.get("first_name").equals("f_test"));
        assert (map.containsKey("last_name") && map.get("last_name").equals("l_test"));
        assert (map.containsKey("password") && map.get("password").equals("p_test"));
    }

    @Test
    void DBToJava() {
        Map<String, String> map = new LinkedHashMap<String, String>() {
            {
                put("id", "0");
                put("first_name", "f_test");
                put("last_name", "l_test");
                put("password", "p_test");
            }
        };
        User user = User.DBToJava(map);
        assertEquals(user.getFirstName(), "f_test");
        assertEquals(user.getLastName(), "l_test");
        assertEquals(user.getPassword(), "p_test");
        assertEquals(user.getId(), 0);
    }

    @Test
    void get() throws SQLException {
        // Save To DB
        User user = new User("f_test", "l_test", "p_test");
        user.save();
        int saved_id = user.getId();
        User new_user = User.get(saved_id);

        assertEquals(new_user.getId(), saved_id);
        assertEquals(new_user.getFirstName(), user.getFirstName());
        assertEquals(new_user.getLastName(), user.getLastName());
        assertEquals(new_user.getPassword(), user.getPassword());
    }

    @Test
    void getAll() throws SQLException {
        // Initialize all users
        List<User> allUsers = new ArrayList<User>() {
            {
                add(new User("f1", "l1", "p1"));
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

        }
    }

    @Test
    void update() throws SQLException {
        // Save To DB
        User user = new User("f_test", "l_test", "p_test");
        user.save();
        int saved_id = user.getId();

        // Update user
        user.setFirstName("f_changed");
        user.setLastName("l_changed");
        user.setPassword("p_changed");

        // Should fail
        User new_user = User.get(saved_id);
        assertEquals(new_user.getId(), saved_id);
        assertNotEquals(new_user.getFirstName(), user.getFirstName());
        assertNotEquals(new_user.getLastName(), user.getLastName());
        assertNotEquals(new_user.getPassword(), user.getPassword());

        // Should Pass after updating
        user.update();
        new_user = User.get(saved_id);
        assertEquals(new_user.getId(), saved_id);
        assertEquals(new_user.getFirstName(), user.getFirstName());
        assertEquals(new_user.getLastName(), user.getLastName());
        assertEquals(new_user.getPassword(), user.getPassword());

    }

}