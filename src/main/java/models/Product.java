package models;

import db.Fields;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Product extends BaseModel {
    public static final String TABLE_NAME = "products";
    public static Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", Fields.ID);
        FIELDS.put("user_id", Fields.INT);
        FIELDS.put("category_id", Fields.INT);
        FIELDS.put("name", Fields.varchar(30));
        FIELDS.put("description", Fields.varchar(250));
        FIELDS.put("price", Fields.DOUBLE);
        FIELDS.put("quantity", Fields.INT);
    }

    private String name;
    private String description;
    private double price;
    private int category_id;
    private int quantity;
    private int user_id;

    public Product(String name, String description, double price, int category_id, int quantity, int user_id) {
        super(Product.TABLE_NAME);
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.user_id = user_id;
        this.quantity = quantity;
    }

    public static Product DBToJava(Map<String, String> fields) {
        Product product = new Product(fields.get("name"),
                fields.get("description"),
                Double.parseDouble(fields.get("price")),
                Integer.parseInt(fields.get("category_id")),
                Integer.parseInt(fields.get("quantity")),
                Integer.parseInt(fields.get("user_id")));
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
        for (Map<String, String> field : fields) {
            allProducts.add(DBToJava(field));
        }
        return allProducts;
    }


    public Map<String, Object> JavaToDB() {
        return new LinkedHashMap<String, Object>() {
            {
                put("name", name);
                put("user_id", user_id);
                put("category_id", category_id);
                put("description", description);
                put("price", price);
                put("quantity", quantity);
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

    public int getCategory_id() {
        return quantity;
    }
    public int getUser_id() {
        return quantity;
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

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
