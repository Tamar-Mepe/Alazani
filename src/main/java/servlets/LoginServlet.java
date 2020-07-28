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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMessage = "";
        if (username == null || password == null) {
            errorMessage = "Please fill blank spaces";
        }
        User user = User.getWithUserName(username);
        if (errorMessage.equals("") && user == null) {
            errorMessage = "Username doesn't exist";
        }
        if (errorMessage.equals("") && BCrypt.checkpw(password, user.getPassword())) {
            request.getSession().setAttribute(User.ATTRIBUTE_NAME, user.getId());
            //TODO: move to front page
            RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
            view.forward(request, response);
            return;
        } else if (errorMessage.equals("")) {
            errorMessage = "Incorrect password";
        }
        // happend if username doesn't exist or password was incorrect
        request.setAttribute("error", errorMessage);
        RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
        view.forward(request, response);
    }
}
