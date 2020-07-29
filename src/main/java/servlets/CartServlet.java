package servlets;

import models.Cart;
import models.Product;
import models.Review;
import models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
        if(request.getParameter("cartButton") != null){
            if (request.getParameter("quantity-select") != null) {
                int quantity = Integer.parseInt(request.getParameter("quantity-select"));
                if (userId == null) {
                    response.sendRedirect("/itempage.jsp?id=" + productId);
                    return;
                }
                new Cart(userId, productId, quantity).save();
                Product prod = Product.get(productId);
                prod.setQuantity(prod.getQuantity() - quantity);
                prod.update();
                response.sendRedirect("/itempage.jsp?id=" + productId);
                System.out.println("pls ye");
                return;
            }
            response.sendRedirect("/itempage.jsp?id=" + productId);
        }
        if(request.getParameter("removeButton") != null){
            try {
                Cart.removeCart(userId,productId);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            response.sendRedirect("/cart.jsp");
        }
    }
}
