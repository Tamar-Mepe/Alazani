package models;

import db.Fields;

import java.util.LinkedHashMap;
import java.util.Map;

public class Review extends BaseModel {
    public static final String TABLE_NAME = "reviews";
    public static Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", Fields.ID);
        FIELDS.put("user_id", Fields.INT);
        FIELDS.put("product_id", Fields.INT);
        FIELDS.put("points", Fields.INT);
        FIELDS.put("comment", Fields.varchar(1024));
    }

    private String comment;
    private int points;
    private int user_id;
    private int product_id;

    public Review(String comment, int points, int user_id, int product_id) {
        super(TABLE_NAME);
        this.comment = comment;
        this.points = points;
        this.user_id = user_id;
        this.product_id = product_id;
    }


    public Map<String, Object> JavaToDB() {
        return new LinkedHashMap<String, Object>() {
            {
                put("user_id", user_id);
                put("product_id", product_id);
                put("comment",comment);
                put("points",points);
            }
        };
    }


}
