package presentacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entidades.Origenes;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import persistencia.OrigenDao;

@WebServlet(name = "origen", urlPatterns = {"/origen", "/privado/origen"})
public class Origen extends HttpServlet {

   
  Gson convertir = new Gson();
         
           
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String texto = request.getReader().readLine();
            ArrayList<Origenes> listado = OrigenDao.getInstance().obtener();
            Gson convertir = new Gson();
            String resultado = convertir.toJson(listado);
            out.println("" + resultado);

        } catch (ClassNotFoundException ex) {
            out.println("" + ex.getMessage());
        } catch (SQLException ex) {
            out.println("" + ex.getMessage());
        } finally {
            out.close();
        }
    }

   
}