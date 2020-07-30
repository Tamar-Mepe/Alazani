package models;

import db.Fields;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Purchase extends BaseModel {

    public static final String TABLE_NAME = "Purchases";
    public static final Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", Fields.ID);
        FIELDS.put("user_id", Fields.INT);
        FIELDS.put("product_id", Fields.INT);
        FIELDS.put("quantity", Fields.INT);
        FIELDS.put("purchase_date", Fields.varchar(15));
    }

    private int userId;
    private int productId;
    private int quantity;
    private String purchaseDate;

    public Purchase(int userId, int productId, int quantity, String purchaseDate) {
        super(TABLE_NAME);
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }


    public static Purchase DBToJava(Map<String, String> fields) {
        Purchase purchase = new Purchase(
                Integer.parseInt(fields.get("user_id")),
                Integer.parseInt(fields.get("product_id")),
                Integer.parseInt(fields.get("quantity")),
                fields.get("purchase_date"));
        purchase.setId(Integer.parseInt(fields.get("id")));
        return purchase;
    }

    public static Purchase get(int id) {
        Map<String, String> fields = BaseModel.getGeneric(TABLE_NAME, id);
        assert fields != null;
        return DBToJava(fields);
    }

    public static List<Purchase> getAll() {
        List<Map<String, String>> fields = BaseModel.getAllGeneric(TABLE_NAME);
        List<Purchase> allPurchase = new ArrayList<>();
        for (Map<String, String> field : fields) {
            allPurchase.add(DBToJava(field));
        }
        return allPurchase;
    }


    public Map<String, Object> JavaToDB() {
        return new LinkedHashMap<String, Object>() {
            {
                put("user_id", userId);
                put("product_id", productId);
                put("quantity", quantity);
                put("purchase_date", purchaseDate);
            }
        };
    }

    public int getSoldQuantity() {
        return this.quantity;
    }

    public int getUserId() {
        return this.userId;
    }

    public int getProductId() {
        return this.productId;
    }

    public String getPurchaseDate() {
        return this.purchaseDate;
    }
}
