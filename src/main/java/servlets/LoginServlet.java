package servlets;

import models.User;
import utils.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMessage = "";

        User user = User.getWithUserName(username);
        if (user == null) {
            errorMessage = "Username doesn't exists";
        }
        if (BCrypt.checkpw(password, user.getPassword()) && errorMessage.equals("")) {
            request.getSession().setAttribute(User.ATTRIBUTE_NAME, user.getId());
            //TODO: move to front page
            return;
        } else if(errorMessage.equals("")) {
            errorMessage = "Incorrect password";
        }
        // happend if username doesn't exist or password was incorrect
        request.setAttribute("error", errorMessage);
        RequestDispatcher view = request.getRequestDispatcher("/register.jsp");
        view.forward(request, response);
    }
}
