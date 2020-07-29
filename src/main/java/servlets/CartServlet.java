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
import java.util.*;

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
                // prod.setQuantity(prod.getQuantity() - quantity);
                prod.update();
                response.sendRedirect("/itempage.jsp?id=" + productId);
                return;
            }
            response.sendRedirect("/itempage.jsp?id=" + productId);
        }
        if(request.getParameter("removeButton") != null){
            Cart.removeCart(userId,productId);
            response.sendRedirect("/cart.jsp");
        }
        if(request.getParameter("bBuy") != null){
            List<Cart> carts = Cart.getCarts(userId);
            HashMap<Integer,Integer> cont = new HashMap<Integer,Integer>();
            for(Cart cart : carts){
                Product product = Product.get(cart.getProductId());
                if(!cont.containsKey(cart.getProductId()))
                cont.put(cart.getProductId(),cart.getQuantity());
                else cont.put(cart.getProductId(),cart.getQuantity()+cont.get(cart.getProductId()));
                product.setQuantity(product.getQuantity()-cart.getQuantity());
                product.update();
                Cart.removeCart(userId,productId);
            }
            Map<Integer,Integer> map = new HashMap<Integer,Integer>();
            for(User user : User.getAll()){
                List<Cart> temp = Cart.getCarts(user.getId());
                Map<Integer,Integer> tmp = new HashMap<Integer,Integer>();
                for(int i = 0 ; i < temp.size();i++){
                    Cart currCart = temp.get(i);
                    if(!tmp.containsKey(currCart.getProductId()))
                        tmp.put(currCart.getProductId(),currCart.getQuantity());
                    else
                        tmp.put(currCart.getProductId(),currCart.getQuantity()+tmp.get(currCart.getProductId()));
                }
                for(Integer product : tmp.keySet()){
                    if(tmp.get(product) > Product.get(product).getQuantity()){
                        int numberToDelete = tmp.get(product) - Product.get(product).getQuantity();
                        for(int i = 0; i < temp.size();i ++){
                            if(temp.get(i).getProductId() == product){
                                if(temp.get(i).getQuantity() >= numberToDelete){
                                    temp.get(i).updateQuantity(temp.get(i).getQuantity()-numberToDelete);
                                    if(temp.get(i).getQuantity() == 0) temp.get(i).deleteRow();
                                    /*gamodzaxeba*/
                                }else{
                                    temp.get(i).updateQuantity(0);
                                    numberToDelete -= temp.get(i).getQuantity();
                                    temp.get(i).deleteRow();
                                    /*gamodzaxeba*/
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
