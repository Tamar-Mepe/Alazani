package models;

import java.util.HashMap;
import java.util.Map;

public class User extends BaseModel {
    public static final String TABLE_NAME = "users";
    public static Map<String, Object> FIELDS = new HashMap<>();

    static {
        FIELDS.put("id", new String[]{"int", "primary key"});
        FIELDS.put("first_name", "varchar(30)");
        FIELDS.put("last_name", "varchar(30)");
    }

    // Instance Variables
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
