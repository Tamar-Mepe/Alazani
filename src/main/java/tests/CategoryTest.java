package tests;

import db.DB;
import db.Migration;
import db.MySQL;
import models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CategoryTest {

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        DB db = MySQL.getInstance();
        Migration.createTables(db);
    }

    @Test
    void get() {
        // Save To DB
        Category category = new Category("testCategory");
        category.save();
        int savedId = category.getId();
        Category category1 = Category.get(savedId);

        assertEquals(category1.getId(), savedId);
        assertEquals(category.getName(), category1.getName());
    }

    @Test
    void getAll() {
        // Initialize all users
        List<Category> allCategories = new ArrayList<Category>() {
            {
                add(new Category("axalikategoria1"));
                add(new Category("axalikategoria2"));
                add(new Category("axalikategoria3"));
                add(new Category("axalikategoria4"));
            }
        };

        // Save all of them in DB
        for (Category category : allCategories)
            category.save();

        List<Category> allCategoriesDB = Category.getAll();
        for (int i = 0; i < allCategoriesDB.size(); i++) {
            Category categoryDB = allCategoriesDB.get(i);
            Category category = allCategories.get(i);
            assertEquals(categoryDB.getName(), category.getName());
        }
    }

    @Test
    void update() {
        // Save To DB
        Category category = new Category("axalikategoria");
        category.save();
        int savedId = category.getId();

        // Update user
        category.setName("axalikategoria2");

        // Should fail
        Category newCategory = Category.get(savedId);
        assertEquals(newCategory.getId(), savedId);
        assertNotEquals(newCategory.getName(), category.getName());

        // Should Pass after updating
        category.update();
        newCategory = Category.get(savedId);
        Category newCategory2 = Category.getByName("axalikategoria2");
        assertEquals(savedId, newCategory2.getId());
        assertEquals(newCategory.getId(), savedId);
        assertEquals(newCategory.getName(), category.getName());
        assertNull(Category.getByName("chgrpgr"));
    }
}