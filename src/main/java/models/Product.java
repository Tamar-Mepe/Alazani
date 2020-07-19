package models;

import java.util.LinkedHashMap;
import java.util.Map;

public class Product extends BaseModel {
    public static final String TABLE_NAME = "products";
    public static Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", new String[]{"int", "primary key", "AUTO_INCREMENT"});
        FIELDS.put("name", "varchar(32)");
        FIELDS.put("description", "varchar(256)");
        FIELDS.put("price", "double");
        FIELDS.put("category_id", "int");
        FIELDS.put("quantity", "int");
    }

    private String name;
    private String description;
    private double price;
    private int category_id;
    private int quantity;

    public Product(String name, String description, double price, int category_id, int quantity) {
        super(Product.TABLE_NAME);
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.quantity = quantity;
    }


    public void save() {
        Map<String, Object> field = generateFieldMap();
        id = this.saveIntoTable(field);
    }

    public boolean update() {
        Map<String, Object> field = generateFieldMap();
        return this.updateTable(id, field);
    }

    private Map<String, Object> generateFieldMap() {
        return new LinkedHashMap<String, Object>() {
            {
                put("name", name);
                put("description", description);
                put("price", price);
                put("category_id", category_id);
                put("quantity", quantity);
            }
        };
    }
}
