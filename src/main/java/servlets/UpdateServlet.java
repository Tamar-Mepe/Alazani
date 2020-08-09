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

@WebServlet("/UpdateProfile")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("change-first-name");
        String lastName = request.getParameter("change-last-name");
        String email = request.getParameter("change-email");
        String password = request.getParameter("password");

        Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
        User user = User.get(userId);

        if (!firstName.equals(""))
            user.setFirstName(firstName);
        if (!lastName.equals(""))
            user.setLastName(lastName);
        if (!email.equals(""))
            user.setEmail(email);
        if (!password.equals(""))
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        user.update();

        RequestDispatcher view = request.getRequestDispatcher("/profile.jsp");
        view.forward(request, response);
    }
}
