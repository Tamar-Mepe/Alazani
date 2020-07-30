package servlets;

import models.Cart;
import models.Product;
import models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (request.getParameter("bBuy") != null) {
            Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
            List<Cart> carts = Cart.getCarts(userId);
            HashMap<Integer, Integer> cont = new HashMap<>();
            for (Cart cart : carts) {
                Product product = Product.get(cart.getProductId());
                if (!cont.containsKey(cart.getProductId()))
                    cont.put(cart.getProductId(), cart.getQuantity());
                else cont.put(cart.getProductId(), cart.getQuantity() + cont.get(cart.getProductId()));
                product.setQuantity(product.getQuantity() - cart.getQuantity());
                product.update();
                Cart.removeCart(userId, cart.getProductId());
            }
            for (User user : User.getAll()) {
                List<Cart> temp = Cart.getCarts(user.getId());
                Map<Integer, Integer> tmp = new HashMap<>();
                for (Cart currCart : temp) {
                    if (!tmp.containsKey(currCart.getProductId()))
                        tmp.put(currCart.getProductId(), currCart.getQuantity());
                    else
                        tmp.put(currCart.getProductId(), currCart.getQuantity() + tmp.get(currCart.getProductId()));
                }
                for (Integer product : tmp.keySet()) {
                    if (tmp.get(product) > Product.get(product).getQuantity()) {
                        int numberToDelete = tmp.get(product) - Product.get(product).getQuantity();
                        for (Cart cart : temp) {
                            if (cart.getProductId() == product) {
                                if (cart.getQuantity() >= numberToDelete) {
                                    int curr = numberToDelete;
                                    numberToDelete -= cart.getQuantity();
                                    cart.updateQuantity(cart.getQuantity() - curr);
                                    if (cart.getQuantity() == 0) cart.deleteRow();
                                    else cart.update();

                                } else {
                                    numberToDelete -= cart.getQuantity();
                                    cart.deleteRow();
                                }
                            }
                            if (numberToDelete <= 0) {
                                response.sendRedirect("/cart.jsp");
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
