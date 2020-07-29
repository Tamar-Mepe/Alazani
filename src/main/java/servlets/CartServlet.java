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
            int quantity = Integer.parseInt(request.getParameter("quantity-select"));
            System.out.println("quantity = " + quantity);
            if (request.getParameter("quantity-select") != null) {
                Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
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
        }
        if(request.getParameter("reviewButton") != null){
            String comment = request.getParameter("comment");
            Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
            int points = Integer.parseInt(request.getParameter("points"));
            if(comment != null && userId != null){
                Review currReview = new Review(comment, points, userId, productId);
                currReview.save();
            }
            System.out.println("points = " + points);
            System.out.println("userId = " + userId);
            System.out.println("comment = " + comment);
            System.out.println("productId = " + productId);
        }
        response.sendRedirect("/itempage.jsp?id=" + productId);
    }
}
