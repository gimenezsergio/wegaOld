package ingresos.presentacion;

import com.google.gson.Gson;
import ingresos.entidades.Arribos;
import ingresos.persistencia.ArribosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Arribo", urlPatterns = {"/Arribo"})
public class Arribo extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            
                System.out.println("Sin parametros");
                String texto = request.getReader().readLine();
                ArrayList<Arribos> listado = ArribosDAO.getInstance().obtener();

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
