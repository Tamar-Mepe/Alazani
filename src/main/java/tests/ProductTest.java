package tests;

import db.DB;
import db.Migration;
import db.MySQL;
import models.Category;
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
    void searchProduct(){
        // init categories
        Category cat1 = (Category) new Category("cat1").save();
        Category cat2 = (Category) new Category("cat2").save();

        // init products
        Product prod1 = (Product) new Product("prod1", "desc1", 99.99, cat1.getId(), 100, -1, null).save();
        Product prod2 = (Product) new Product("prod2", "desc2", 99.99, cat1.getId(), 100, -1, null).save();
        Product prod3 = (Product) new Product("prod3", "desc3", 99.99, cat1.getId(), 100, -1, null).save();
        Product prod4 = (Product) new Product("prod4", "desc4", 99.99, cat1.getId(), 100, -1, null).save();
        Product prod5 = (Product) new Product("prod5", "desc5", 99.99, cat2.getId(), 100, -1, null).save();
        Product prod6 = (Product) new Product("termo", "desc5", 99.99, cat2.getId(), 100, -1, null).save();
        Product prod7 = (Product) new Product("puppet", "desc5", 99.99, cat2.getId(), 100, -1, null).save();
        Product prod8 = (Product) new Product("gull", "desc5", 99.99, cat2.getId(), 100, -1, null).save();
        Product prod9 = (Product) new Product("reso", "desc5", 99.99, cat2.getId(), 100, -1, null).save();
        Product prod10 = (Product) new Product("zermoo", "desc5", 99.99, cat2.getId(), 100, -1, null).save();

        // check Product.searchProduct()

        List<Product> list1 = Product.searchProduct("prod");
        List<Product> list2 = Product.searchProduct("termo");
        List<Product> list3 = Product.searchProduct("pupet");
        List<Product> list4 = Product.searchProduct("re");
        List<Product> list5 = Product.searchProduct("mo");
        List<Product> list6 = Product.searchProduct("g");

        //checking result of searchProduct
        assertEquals(list1.size(), 5);
        assertEquals(list2.size(), 1);
        assertEquals(list3.size(), 0);
        assertEquals(list4.size(), 1);
        assertEquals(list5.size(), 2);
        assertEquals(list6.size(), 1);


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

    @Test
    public void category() {
        // init categories
        Category cat1 = (Category) new Category("cat1").save();
        Category cat2 = (Category) new Category("cat2").save();

        // init products
        Product prod1 = (Product) new Product("prod1", "desc1", 99.99, cat1.getId(), 100, -1, null).save();
        Product prod2 = (Product) new Product("prod2", "desc2", 99.99, cat1.getId(), 100, -1, null).save();
        Product prod3 = (Product) new Product("prod3", "desc3", 99.99, cat1.getId(), 100, -1, null).save();
        Product prod4 = (Product) new Product("prod4", "desc4", 99.99, cat1.getId(), 100, -1, null).save();
        Product prod5 = (Product) new Product("prod5", "desc5", 99.99, cat2.getId(), 100, -1, null).save();

        // check Product.category()
        assertEquals(cat1.getId(), prod1.category().getId());
        assertEquals(cat1.getId(), prod2.category().getId());
        assertEquals(cat1.getId(), prod3.category().getId());
        assertEquals(cat1.getId(), prod4.category().getId());
        assertEquals(cat2.getId(), prod5.category().getId());

        // check Category.products()
        List<Product> cat1Products = cat1.products();
        List<Product> cat2Products = cat2.products();

        assertEquals(cat1Products.size(), 4);
        assertEquals(cat2Products.size(), 1);
    }
}