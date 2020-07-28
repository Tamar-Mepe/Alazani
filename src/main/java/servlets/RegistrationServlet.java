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

        if (firstName == null || lastName == null || username == null || email == null || password == null || confirmPassword == null) {
            errorMessage = "Please fill blank spaces";
        }
        if (User.getWithUserName(username) != null) {
            errorMessage = "This username already exists";
        }
        if (errorMessage.equals("") && User.getWithEmail(email) != null) {
            errorMessage = "This email already exists";
        }
        if (errorMessage.equals("") && !password.equals(confirmPassword)) {
            errorMessage = "Passwords don't match";
            //TODO: redirect
        }
        if (!errorMessage.equals("")) {
            request.setAttribute("error", errorMessage);
            RequestDispatcher view = request.getRequestDispatcher("/register.jsp");
            view.forward(request, response);
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = (User) new User(firstName, lastName, hashedPassword, username, email).save();
        request.getSession().setAttribute(User.ATTRIBUTE_NAME, user.getId());
        //TODO: send redirect to main page

    }

}
