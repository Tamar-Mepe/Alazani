package servlets;

import models.User;
import utils.BCrypt;

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
        List<User> users = User.getAll();
        System.out.println(password);
        System.out.println(BCrypt.hashpw(password,BCrypt.gensalt()));
        for (User user : users) {

            if (user.getUsername().equals(username)) {
                //TODO: String hashedPassword;
                if (BCrypt.checkpw(password, user.getPassword())) {
                    //TODO: move to profile page
                    System.out.println("correct");
                } else {
                    //TODO: move to error page (wrong password)
                }
            }
        }
       // System.out.println("correct");
        //TODO: alert("incorrect username");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
