package models;

import java.util.LinkedHashMap;
import java.util.Map;

public class User extends BaseModel {
    public static final String TABLE_NAME = "users";
    public static Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", new String[]{"int", "primary key", "AUTO_INCREMENT"});
        FIELDS.put("first_name", "varchar(30)");
        FIELDS.put("last_name", "varchar(30)");
    }

    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        super(User.TABLE_NAME);
        this.firstName = firstName;
        this.lastName = lastName;
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
                put("first_name", firstName);
                put("last_name", lastName);
            }
        };
    }
}
