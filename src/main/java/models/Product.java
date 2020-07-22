package models;

import db.Fields;

import java.util.LinkedHashMap;
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
}
