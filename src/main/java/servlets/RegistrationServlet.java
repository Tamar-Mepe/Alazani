package servlets;

import models.User;
import utils.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthTextAreaUI;
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
        String errorMessage = "";

        if(User.getWithUserName(username)!=null){
            errorMessage = "This username already exists";
            //TODO: redirect
            return;
        }
        if(User.getWithEmail(email)!=null){
            errorMessage = "This email already exists";
            //TODO: redirect
            return;
        }
        if(!password.equals(confirmPassword)){
            errorMessage = "Passwords doesn't match";
            //TODO: redirect
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = (User) new User(firstName, lastName,hashedPassword,username,email).save();
        request.getSession().setAttribute(User.ATTRIBUTE_NAME,user.getId());
    }
    
}
