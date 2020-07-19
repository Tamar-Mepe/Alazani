package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class User extends BaseModel {
    public static final String TABLE_NAME = "users";
    public static Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", new String[]{"int", "primary key", "AUTO_INCREMENT"});
        FIELDS.put("first_name", "varchar(30)");
        FIELDS.put("last_name", "varchar(30)");
    }

    private int id;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        super(User.TABLE_NAME);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static User get(int id) {
        try {
            Map<String, String> fields = BaseModel.getOneRecord(TABLE_NAME, id);
            User user = new User(fields.get("first_name"), fields.get("last_name"));
            user.setId(Integer.parseInt(fields.get("id")));
            return user;
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }

    public static List<User> getAll() {
        List <User> allEntries = new ArrayList<>();

        try {
            List<Map<String, String>> records = BaseModel.getAllRecords(TABLE_NAME);
            for(Map<String, String> fields : records){
                User user = new User(fields.get("first_name"), fields.get("last_name"));
                user.setId(Integer.parseInt(fields.get("id")));
                allEntries.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allEntries;
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
