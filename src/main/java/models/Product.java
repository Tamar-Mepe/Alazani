package models;

import sun.reflect.generics.tree.Tree;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Product extends BaseModel {
    public static final String TABLE_NAME = "products";
    public static Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", new String[]{"int", "primary key"});
        FIELDS.put("name", "varchar(32)");
        FIELDS.put("description", "varchar(256)");
    }

    private final String name;
    private final String description;

    public Product(String name, String description) {
        super(Product.TABLE_NAME);
        this.name = name;
        this.description = description;
    }
}
