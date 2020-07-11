package models;

import java.util.HashMap;
import java.util.Map;

public class BaseModel {
    // Model Table Name
    public static final String TABLE_NAME = "table";

    // Model Table Fields
    public static Map<String, String[]> FIELDS = new HashMap<>();

    static {
        FIELDS.put("id", new String[]{"int"});
    }
}
