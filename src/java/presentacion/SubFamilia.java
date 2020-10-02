package presentacion;

import persistencia.SubFamiliaDao;
import com.google.gson.Gson;
import entidades.SubFamilias;
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


@WebServlet(name = "subFamilia", urlPatterns = {"/subFamilia", "/privado/subFamilia"})
public class SubFamilia extends HttpServlet {

   

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("q") == null) {
                System.out.println("Sin parametros");
                String texto = request.getReader().readLine();
                ArrayList<SubFamilias> listado;
                listado = SubFamiliaDao.getInstance().obtener();
                Gson convertir = new Gson();
                String resultado = convertir.toJson(listado);
                out.println("" + resultado);

            } else {
                System.out.println("Con Parametros");
                String idFamilia = request.getParameter("q");
                //listado = FamiliaDao.getInstance().obtener();
                System.out.println("id de familia par filtrar subfamilia: " + idFamilia);
                ArrayList<SubFamilias> listado;
                listado = SubFamiliaDao.getInstance().obtenerByFamilia(idFamilia);
                Gson convertir = new Gson();
                String resultado = convertir.toJson(listado);
                out.println("" + resultado);
            }

        } catch (ClassNotFoundException ex) {
            out.println("" + ex.getMessage());
        } catch (SQLException ex) {
            out.println("" + ex.getMessage());
        } 
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

   
   

}
