package models;

import java.util.HashMap;
import java.util.Map;

public class User extends BaseModel {
    public static final String TABLE_NAME = "users";
    public static Map<String, String> FIELDS = new HashMap<>();

    static {
        FIELDS.put("id", "int");
        FIELDS.put("first_name", "varchar(30)");
        FIELDS.put("last_name", "varchar(30)");
    }
}
