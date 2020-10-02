/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import com.google.gson.Gson;
import entidades.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sergio
 */
@WebServlet(name = "AccessLogin", urlPatterns = {"/AccessLogin"})
public class AccessLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson convertir = new Gson();

        TreeMap parametrosRecibidos = convertir.fromJson(request.getReader(), TreeMap.class);
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        String user = (String) parametrosRecibidos.get("user");
        String pass = (String) parametrosRecibidos.get("pass");

        //String user = "jony@gmail";
        // String pass = "123";
        //String IdSession = request.getSession().getId();
        String IdSession = user;
        try {

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    System.out.println("cookies: " + cookies);
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            Cookie[] laCookie = request.getCookies();
            if( laCookie != null){
                for (int i = 0; i < laCookie.length; i++) {
                response.getWriter().println(laCookie[i].getName());
                if (laCookie[i].getName().equals("loginSystem")) {
                    laCookie[i].setMaxAge(0);
                    response.addCookie(laCookie[i]);
                    //response.getWriter().println("Sí es! : " + laCookie[i].getName());
                    //response.sendRedirect("index.html");
                    //request.getRequestDispatcher("publico/index.html").forward(request, response);
                } else {
                    //response.getWriter().println("Cookie " + laCookie[i].getName() + " no es.");

                }

            }
            }
            

//            DAO miLogin = new DAO();
//            miLogin.loginDao(user, pass);
            Login miLogin = new Login(user, pass, IdSession);

            System.out.println("Usuario AccessLogin: " + miLogin.getUserNow());

            //////////////////////////////////////////////////
            //////////////////////////////////////////////////
            HttpSession session = request.getSession();
            session.setAttribute("usuario", miLogin.getUserNow().getUser());
            session.setAttribute("firma", miLogin.getUserNow().getFirma());
            session.setAttribute("nombre", miLogin.getUserNow().getNombre());
            session.setAttribute("usuarioId", miLogin.getUserNow().getId());
            session.setAttribute("uname", "SergioTest");
            System.out.println("uname ::" + session.getAttribute("uname"));
            System.out.println("nombre ::" + session.getAttribute("nombre"));
            System.out.println(session.getAttributeNames());
            Cookie miCookie2 = new Cookie("userId", miLogin.getUserNow().getId());
            Cookie miCookie = new Cookie("loginSystem", "loginaccedido");
            response.addCookie(miCookie2);
            response.addCookie(miCookie);
            

            Enumeration e = (Enumeration) (session.getAttributeNames());

            while (e.hasMoreElements()) {
                Object tring;
                if ((tring = e.nextElement()) != null) {
                    System.out.println(session.getValue((String) tring));
                }

            }

//            RequestDispatcher rd = request.getRequestDispatcher("http://localhost:8084/wegafinal/privado/principal.html");
//            rd.forward(request, response);

            ////////////////////////////////////////////////////
            ////////////////////////////////////////////////////
            //Se usa para trabajar dentro del dominio
            //request.getRequestDispatcher("/WEB-INF/privado/principal.html").forward(request, response);
            //response.getRequestDispatcher("/WEB-INF/privado/principal.html").forward(request, response);
            //System.out.println(request.getRequestURI());
//response.sendRedirect("http://localhost:8084/wegafinal/privado/principal.html");
//request.getRequestDispatcher("/WEB-INF/privado/prinml.hmtl").forward(request, response);
            // Redirecciona al navegador dando el segundo impacto de requeste. Sale del dominio, usar en casos
            // como para paypla, mercadopago, etc.
            //response.sendRedirect("http://localhost:8084/LoginSystemTDD/publico/perfilPublicoRedirect.html");
//    response.getWriter().println("MESNAJE");
            //response.getWriter().println("Login OK");
        } catch (LoginExeption ex) {
            response.getWriter().println("<!-- mjsError de error -->" + ex.getMessage());
        }
    }

}
