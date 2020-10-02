package presentacion;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] laCookie = request.getCookies();
        for (int i = 0; i < laCookie.length; i++) {
            response.getWriter().println(laCookie[i].getName());
            if (laCookie[i].getName().equals("loginSystem")) {
                laCookie[i].setMaxAge(0);
                response.addCookie(laCookie[i]);
                //response.getWriter().println("Sí es! : " + laCookie[i].getName());
                response.sendRedirect("index.html");
                //request.getRequestDispatcher("publico/index.html").forward(request, response);
            } else {
                //response.getWriter().println("Cookie " + laCookie[i].getName() + " no es.");

            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
