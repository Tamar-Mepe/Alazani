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

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        if(request.getParameter("buyButton") != null){
            if (request.getParameter("quantity-select") != null) {
                int quantity = Integer.parseInt(request.getParameter("quantity-select"));
                Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
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
        }
        if(request.getParameter("reviewButton") != null){
            String comment = request.getParameter("comment");
            Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
            int points = Integer.parseInt(request.getParameter("points"));
            if(comment != null && userId != null){
                Review currReview = new Review(comment, points, userId, productId);
                currReview.save();
            }
        }
        response.sendRedirect("/itempage.jsp?id=" + productId);
    }
}
