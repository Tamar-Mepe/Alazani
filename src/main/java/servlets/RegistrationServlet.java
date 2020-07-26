package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("password_confirmation");
        List<User> allUser = User.getAll();
        boolean foundEMail = false;
        for (User user : allUser) {
            if (user.getUsername().equals(username)) {
                /*TODO*/
                return;
            }
            if (!foundEMail && user.getEmail().equals(email)) {
                foundEMail = true;
            }
        }
        if (foundEMail) {
            return;
        }
        if (!password.equals(confirmPassword)) {
            return;
        }


      /*  request.setAttribute("error", "404");
        RequestDispatcher view = request.getRequestDispatcher("/register.jsp");
        view.forward(request, response);*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
