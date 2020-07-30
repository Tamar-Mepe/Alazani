package models;

import db.Fields;

import java.util.*;
import java.util.stream.Collectors;

public class Cart extends BaseModel {
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    private int userId;
    private int productId;
    private int quantity;
    public static final String TABLE_NAME = "carts";
    public static Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", Fields.ID);
        FIELDS.put("user_id", Fields.INT);
        FIELDS.put("product_id", Fields.INT);
        FIELDS.put("quantity", Fields.INT);
    }

    public Cart(int userId, int productId, int quantity) {
        super(TABLE_NAME);
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public static Cart DBToJava(Map<String, String> fields) {
        Cart cart = new Cart(Integer.parseInt(fields.get("user_id")),
                Integer.parseInt(fields.get("product_id")),
                Integer.parseInt(fields.get("quantity")));
        cart.setId(Integer.parseInt(fields.get("id")));
        return cart;
    }

    public static Cart get(int id) {
        Map<String, String> fields = BaseModel.getGeneric(TABLE_NAME, id);
        assert fields != null;
        return DBToJava(fields);
    }

    public Map<String, Object> JavaToDB() {
        return new LinkedHashMap<String, Object>() {
            {
                put("user_id", userId);
                put("product_id", productId);
                put("quantity", quantity);
            }
        };
    }

    public static List<Cart> getAll() {
        List<Map<String, String>> fields = BaseModel.getAllGeneric(TABLE_NAME);
        List<Cart> allCarts = new ArrayList<>();
        for (Map<String, String> field : fields) {
            allCarts.add(DBToJava(field));
        }
        return allCarts;
    }

    public int getUserId() {
        return userId;
    }

    public static List<Cart> getCarts(int userId) {
        List<Cart> Carts = Cart.getAll();
        return Carts.stream().filter(art -> art.getUserId() == userId).collect(Collectors.toList());
    }

    public static Map<Product, Integer> getProductsByUserId(int userId) {
        Map<Integer, Integer> resultProducts = new HashMap<Integer, Integer>();
        List<Cart> carts = getCarts(userId);
        for (Cart currCart : carts) {
            if (resultProducts.containsKey(currCart.productId)) {
                resultProducts.put(currCart.productId, resultProducts.get(currCart.productId) + currCart.quantity);
            } else {
                resultProducts.put(currCart.productId, currCart.quantity);
            }
        }
        Map<Product, Integer> prodMap = new HashMap<Product, Integer>();
        for (int prodId : resultProducts.keySet()) {
            prodMap.put(Product.get(prodId), resultProducts.get(prodId));
        }
        return prodMap;
    }

    public int getQuantity() {
        return quantity;
    }

    public static boolean removeCart(int userId, int productId) {
        List<Cart> carts = Cart.getCarts(userId);
        for (Cart cart : carts) {
            if (cart.getProductId() == productId) {
                Product product = Product.get(productId);
                product.update();
                cart.deleteRow();
            }
        }
        return false;
    }

    public void updateQuantity(int newQuantity) {
        quantity = newQuantity;
    }


}
