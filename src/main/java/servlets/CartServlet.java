package servlets;

import models.Cart;
import models.Product;
import models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("cartButton") != null) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product prod = Product.get(productId);
            Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
            if (request.getParameter("quantity-select") != null) {
                int quantity = Integer.parseInt(request.getParameter("quantity-select"));
                if (userId == null) {
                    response.sendRedirect("/item-page.jsp?id=" + productId);
                    return;
                }
                /**/
                Cart userProdCart = Cart.getCart(userId, productId);
                if (userProdCart == null)
                    userProdCart = (Cart) new Cart(userId, productId, 0).save();

                // Set correct quantity
                int totalQuantity = prod.getQuantity();
                if (totalQuantity < quantity + userProdCart.getQuantity()){
                    // ToDo: redirect with error
                    response.sendRedirect("/item-page.jsp?id=" + productId);
                    return;
                }
                userProdCart.setQuantity(userProdCart.getQuantity()+quantity);
                userProdCart.update();
                /**/
                prod.update();
                response.sendRedirect("/item-page.jsp?id=" + productId);
                return;
            }
            response.sendRedirect("/item-page.jsp?id=" + productId);
        }
        if (request.getParameter("removeButton") != null) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
            Cart.removeCart(userId, productId);
            response.sendRedirect("/cart.jsp");

        }
    }
}
