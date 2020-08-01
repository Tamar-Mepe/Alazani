package servlets;

import models.Cart;
import models.Product;
import models.Purchase;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
        List<Cart> carts = Cart.getCarts(userId);

        for (Cart cart : carts) {
            // Variables
            int productID = cart.getProductId();
            int quantity = cart.getQuantity();
            String date = "Date";

            // ToDo: add Date
            new Purchase(userId, productID, quantity).save();

            // Decrease Quantity on product
            Product product = Product.get(productID);
            product.setQuantity(product.getQuantity() - quantity);
            product.update();
            cart.deleteRow();

            // Remove from other Carts
            List<Cart> productCarts = Cart.getCartsByProductID(productID);
            for (Cart otherCart : productCarts) {
                int canBePurchasedQuantity = Math.min(otherCart.getQuantity(), product.getQuantity());
                otherCart.setQuantity(canBePurchasedQuantity);
                otherCart.update();
                if (otherCart.getQuantity() <= 0)
                    otherCart.deleteRow();
            }
        }

        RequestDispatcher view = request.getRequestDispatcher("/cart.jsp");
        view.forward(request, response);
    }
}
