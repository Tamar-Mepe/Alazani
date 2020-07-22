package models;

import db.Fields;

import java.util.LinkedHashMap;
import java.util.Map;

public class Category extends BaseModel {
    private  String name;
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

    public Map<String, Object> JavaToDB() {
        return new LinkedHashMap<String, Object>() {
            {
                put("name", name);
            }
        };
    }

}
