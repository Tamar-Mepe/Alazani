package models;

import db.Fields;

import java.util.*;

public class User extends BaseModel {
    public static final String TABLE_NAME = "users";
    public static Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", Fields.ID);
        FIELDS.put("first_name", Fields.varchar(30));
        FIELDS.put("last_name", Fields.varchar(30));
        FIELDS.put("password", Fields.varchar(256));
    }

    private String firstName;
    private String lastName;
    private String password;

    public User(String firstName, String lastName, String password) {
        super(User.TABLE_NAME);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    protected Map<String, Object> JavaToDB() {
        return new LinkedHashMap<String, Object>() {
            {
                put("first_name", firstName);
                put("last_name", lastName);
                put("password", password);
            }
        };
    }

    private static Object DBToJava(Map<String, String> fields) {
        User user = new User(fields.get("first_name"), fields.get("last_name"), fields.get("password"));
        user.setId(Integer.parseInt(fields.get("id")));
        return user;
    }

    public static String encrypt(String password) {
        // ToDo: encrpyt
        return password;
    }
}
