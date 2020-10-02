package presentacion;

import com.google.gson.Gson;
import entidades.Login;
import entidades.LoginExeption;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginForm", urlPatterns = {"/LoginForm"})
public class LoginForm extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("inputUser");
        String pass = request.getParameter("inputPassword");
        String IdSession = request.getSession().getId();
        System.out.println("Sesion: " + IdSession);
        try {
            Login miLogin = new Login(user, pass, IdSession);
            Cookie miCookie = new Cookie("loginSystem", "loginaccedido");
            response.addCookie(miCookie);
            response.sendRedirect("/wegafinal/privado/principal.html");
        } catch (LoginExeption ex) {
            response.getWriter().println("<!-- mjsError de error -->" + ex.getMessage());
            response.sendRedirect("/wegafinal/publico/index.html");
        }
    }
}
