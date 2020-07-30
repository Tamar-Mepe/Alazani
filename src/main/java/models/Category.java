package models;

import db.Fields;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Category extends BaseModel {
    private String name;
    public static final String TABLE_NAME = "categories";
    public static Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", Fields.ID);
        FIELDS.put("name", Fields.varchar(30));
    }

    public Category(String name) {
        super(TABLE_NAME);
        this.name = name;
    }

    public static Category get(int id) {
        Map<String, String> fields = BaseModel.getGeneric(TABLE_NAME, id);
        assert fields != null;
        return DBToJava(fields);
    }

    public static Category getByName(String name) {
        List<Category> categories = Category.getAll();
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    public static Category DBToJava(Map<String, String> fields) {
        Category category = new Category(fields.get("name"));
        category.setId(Integer.parseInt(fields.get("id")));
        return category;
    }

    public static List<Category> getAll() {
        List<Category> allCategories = new ArrayList<>();
        for (Map<String, String> field : BaseModel.getAllGeneric(TABLE_NAME)) {
            allCategories.add(DBToJava(field));
        }
        return allCategories;
    }

    public Map<String, Object> JavaToDB() {
        return new LinkedHashMap<String, Object>() {
            {
                put("name", name);
            }
        };
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> products() {
        List<Product> allProducts = Product.getAll();
        return allProducts.stream().filter(product -> product.getCategoryId() == this.id).collect(Collectors.toList());
    }
}
