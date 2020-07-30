package servlets;

import models.Review;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        if (request.getParameter("reviewButton") != null) {
            String comment = request.getParameter("comment");
            Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
            int points = Integer.parseInt(request.getParameter("points"));
            if (comment != null && userId != null) {
                Review currReview = new Review(comment, points, userId, productId);
                currReview.save();
            }
        }
        response.sendRedirect("/itempage.jsp?id=" + productId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
