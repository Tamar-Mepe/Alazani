package servlets;

import models.Category;
import models.Product;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SellServlet")
@MultipartConfig
public class SellServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("product-name");
        String productDescription = request.getParameter("product-description");
        String productQuantityString = request.getParameter("product-quantity");
        String productPriceString = request.getParameter("product-price");
        String productCategory = request.getParameter("category-select");
        String imageAddress = request.getParameter("image-address");
        String errorMessage;

        if (productName.equals("") || productQuantityString.equals("") || productPriceString.equals("") || imageAddress.equals("")) {
            errorMessage = "Please fill blank spaces";
            request.setAttribute("error", errorMessage);
            RequestDispatcher view = request.getRequestDispatcher("/sell.jsp");
            view.forward(request, response);
            return;
        }

        int productQuantity = Integer.parseInt(productQuantityString);
        double productPrice = Double.parseDouble(productPriceString);

        // add product and save it
        Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
        User user = User.get(userId);  //shouldn't be null
        Category category = Category.getByName(productCategory);
        assert category != null;
        new Product(productName, productDescription, productPrice, category.getId(), productQuantity, user.getId(), imageAddress).save();
        response.sendRedirect("/index.jsp");
    }
}
