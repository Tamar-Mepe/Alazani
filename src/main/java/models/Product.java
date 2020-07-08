package models;

import java.util.HashMap;
import java.util.Map;

public class Product extends BaseModel {
    public static final String TABLE_NAME = "products";
    public static Map<String, String> FIELDS = new HashMap<>();

    static {
        FIELDS.put("id", "int");
        FIELDS.put("name", "varchar(32)");
        FIELDS.put("description", "varchar(256)");
    }
}
