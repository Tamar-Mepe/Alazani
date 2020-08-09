package servlets;

import models.User;
import utils.BCrypt;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ConfirmPassword")
public class ConfirmPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password = request.getParameter("current-password");
        Integer userId = (Integer) request.getSession().getAttribute(User.ATTRIBUTE_NAME);
        User user = User.get(userId);

        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        if (BCrypt.checkpw(password, user.getPassword()))
            out.print("1");
        else
            out.print("0");

        out.flush();
    }

}
