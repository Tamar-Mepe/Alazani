package models;

import db.Fields;

import java.util.*;
import java.util.stream.Collectors;

import static models.Cart.getProductsByUserId;

public class User extends BaseModel {
    public static final String TABLE_NAME = "users";
    public static final String ATTRIBUTE_NAME = "userID";
    public static Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", Fields.ID);
        FIELDS.put("first_name", Fields.varchar(30));
        FIELDS.put("last_name", Fields.varchar(30));
        FIELDS.put("password", Fields.varchar(512));
        FIELDS.put("username", Fields.varchar(32));
        FIELDS.put("email", Fields.varchar(256));
    }

    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private String email;

    public User(String firstName, String lastName, String password, String username, String email) {
        super(User.TABLE_NAME);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public Map<String, Object> JavaToDB() {
        return new LinkedHashMap<String, Object>() {
            {
                put("first_name", firstName);
                put("last_name", lastName);
                put("password", password);
                put("username", username);
                put("email", email);
            }
        };
    }

    public static User DBToJava(Map<String, String> fields) {
        User user = new User(fields.get("first_name"),
                fields.get("last_name"),
                fields.get("password"),
                fields.get("username"),
                fields.get("email"));
        user.setId(Integer.parseInt(fields.get("id")));
        return user;
    }

    public static User get(int id) {
        Map<String, String> fields = BaseModel.getGeneric(TABLE_NAME, id);
        assert fields != null;
        return DBToJava(fields);
    }

    public static List<User> getAll() {
        List<Map<String, String>> fields = BaseModel.getAllGeneric(TABLE_NAME);
        List<User> allUsers = new ArrayList<>();
        for (Map<String, String> field : fields) {
            allUsers.add(DBToJava(field));
        }
        return allUsers;
    }

    public static User getWithUserName(String username) {
        List<User> allUsers = User.getAll();
        List<User> users = allUsers.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }

    public static User getWithEmail(String email) {
        List<User> allUsers = User.getAll();
        List<User> users = allUsers.stream().filter(user -> user.getEmail().equals(email)).collect(Collectors.toList());
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> products() {
        List<Product> allProducts = Product.getAll();
        return allProducts.stream().filter(product -> product.getUserId() == this.id).collect(Collectors.toList());
    }
}
