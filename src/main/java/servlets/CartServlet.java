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
            Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
            if (request.getParameter("quantity-select") != null) {
                int quantity = Integer.parseInt(request.getParameter("quantity-select"));
                if (userId == null) {
                    response.sendRedirect("/itempage.jsp?id=" + productId);
                    return;
                }
                new Cart(userId, productId, quantity).save();
                Product prod = Product.get(productId);
                prod.update();
                response.sendRedirect("/itempage.jsp?id=" + productId);
                return;
            }
            response.sendRedirect("/itempage.jsp?id=" + productId);
        }
        if (request.getParameter("removeButton") != null) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
            Cart.removeCart(userId, productId);
            response.sendRedirect("/cart.jsp");

        }
    }
}
