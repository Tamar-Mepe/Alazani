package models;

import java.util.HashMap;
import java.util.Map;

public class Product extends BaseModel {
    public static final String TABLE_NAME = "products";
    public static Map<String, String[]> FIELDS = new HashMap<String, String[]>();

    static {
        FIELDS.put("id", new String[]{"int"});
        FIELDS.put("name", new String[]{"varchar(32)"});
        FIELDS.put("description", new String[]{"varchar(256)"});
    }
}
