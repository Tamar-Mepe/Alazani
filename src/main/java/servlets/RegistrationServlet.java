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

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("password_confirmation");
        String errorMessage = "";

        if (firstName.equals("") || lastName.equals("") || username.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
            errorMessage = "Please fill blank spaces";
        } else if (User.getWithUserName(username) != null) {
            errorMessage = "This username already exists";
        } else if (User.getWithEmail(email) != null) {
            errorMessage = "This email already exists";
        } else if (!password.equals(confirmPassword)) {
            errorMessage = "Passwords don't match";
        }
        if (!errorMessage.equals("")) {
            request.setAttribute("error", errorMessage);
            RequestDispatcher view = request.getRequestDispatcher("/register.jsp");
            view.forward(request, response);
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = (User) new User(firstName, lastName, hashedPassword, username, email).save();
        request.getSession().setAttribute(User.ATTRIBUTE_NAME, user.getId());
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        view.forward(request, response);
    }

}
