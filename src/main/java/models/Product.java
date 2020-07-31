package models;

import db.Fields;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Product extends BaseModel {
    public static final String TABLE_NAME = "products";
    public static final Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", Fields.ID);
        FIELDS.put("user_id", Fields.INT);
        FIELDS.put("category_id", Fields.INT);
        FIELDS.put("name", Fields.varchar(30));
        FIELDS.put("description", Fields.varchar(512));
        FIELDS.put("price", Fields.DOUBLE);
        FIELDS.put("quantity", Fields.INT);
        FIELDS.put("image_address", Fields.varchar(256));
    }

    private String name;
    private String description;
    private double price;
    private int categoryId;
    private int quantity;
    private int userId;
    private String imageAddress;

    public Product(String name, String description, double price, int categoryId, int quantity, int userId, String imageAddress) {
        super(Product.TABLE_NAME);
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.userId = userId;
        this.quantity = quantity;
        this.imageAddress = imageAddress;
    }

    public static Product DBToJava(Map<String, String> fields) {
        Product product = new Product(fields.get("name"),
                fields.get("description"),
                Double.parseDouble(fields.get("price")),
                Integer.parseInt(fields.get("category_id")),
                Integer.parseInt(fields.get("quantity")),
                Integer.parseInt(fields.get("user_id")),
                fields.get("image_address"));
        product.setId(Integer.parseInt(fields.get("id")));
        return product;
    }

    public static Product get(int id) {
        Map<String, String> fields = BaseModel.getGeneric(TABLE_NAME, id);
        assert fields != null;
        return DBToJava(fields);
    }

    public static List<Product> getAll() {
        List<Map<String, String>> fields = BaseModel.getAllGeneric(TABLE_NAME);
        List<Product> allProducts = new ArrayList<>();
        for (Map<String, String> field : fields)
            allProducts.add(DBToJava(field));
        return allProducts;
    }

    public static List<Product> searchProduct(String prodName) {
        List<Product> products = Product.getAll();
        List<Product> result = new ArrayList<>();
        for (Product currProduct : products) {
            if (currProduct.getName().toUpperCase().contains(prodName.toUpperCase()))
                result.add(currProduct);
        }
        return result;
    }

    public Map<String, Object> JavaToDB() {
        return new LinkedHashMap<String, Object>() {
            {
                put("name", name);
                put("user_id", userId);
                put("category_id", categoryId);
                put("description", description);
                put("price", price);
                put("quantity", quantity);
                put("image_address", imageAddress);
            }
        };
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public String getPriceString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(price);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public Category category() {
        return Category.get(this.categoryId);
    }


    public User user() {
        return User.get(this.userId);
    }
}
