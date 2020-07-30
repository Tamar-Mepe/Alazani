package models;

import db.Fields;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    private int userId;
    private int productId;

    public Review(String comment, int points, int userId, int productId) {
        super(TABLE_NAME);
        this.comment = comment;
        this.points = points;
        this.userId = userId;
        this.productId = productId;
    }

    public Map<String, Object> JavaToDB() {
        return new LinkedHashMap<String, Object>() {
            {
                put("user_id", userId);
                put("product_id", productId);
                put("comment", comment);
                put("points", points);
            }
        };
    }

    public static Review DBToJava(Map<String, String> fields) {
        Review review = new Review(fields.get("comment"),
                Integer.parseInt(fields.get("points")),
                Integer.parseInt(fields.get("user_id")),
                Integer.parseInt(fields.get("product_id")));
        review.setId(Integer.parseInt(fields.get("id")));
        return review;
    }

    public static Review get(int id) {
        Map<String, String> fields = BaseModel.getGeneric(TABLE_NAME, id);
        assert fields != null;
        return DBToJava(fields);
    }

    public static List<Review> getAll() {
        List<Map<String, String>> fields = BaseModel.getAllGeneric(TABLE_NAME);
        List<Review> allRewviews = new ArrayList<>();
        for (Map<String, String> field : fields) {
            allRewviews.add(DBToJava(field));
        }
        return allRewviews;
    }

    public static List<Review> getReviewsByProductId(int productId) {
        List<Review> allReviews = Review.getAll();
        List<Review> res = allReviews.stream().filter(review -> review.getProductId() == productId).collect(Collectors.toList());
        Collections.reverse(res);
        return res;
    }

    public static String getAverageReviewByProductId(int productId) {
        List<Review> reviews = Review.getReviewsByProductId(productId);
        double avg = 0;
        for (Review review : reviews) {
            avg += review.getPoints();
        }
        double answer = avg / reviews.size();
        DecimalFormat df = new DecimalFormat("#.0");
        return df.format(answer);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

}
