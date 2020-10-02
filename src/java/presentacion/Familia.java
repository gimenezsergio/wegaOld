package presentacion;

import persistencia.FamiliaDao;
import com.google.gson.Gson;
import entidades.Familias;
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

@WebServlet(name = "Familia", urlPatterns = {"/Familia", "/privado/Familia"})
public class Familia extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("q") == null) {
                System.out.println("Sin parametros");
                String texto = request.getReader().readLine();
                ArrayList<Familias> listado;
                listado = FamiliaDao.getInstance().obtener();
                Gson convertir = new Gson();
                String resultado = convertir.toJson(listado);
                out.println("" + resultado);

            } else {
                System.out.println("Con Parametros");
                String idProduct = request.getParameter("q");
                //listado = FamiliaDao.getInstance().obtener();
            }

        } catch (ClassNotFoundException ex) {
            out.println("" + ex.getMessage());
        } catch (SQLException ex) {
            out.println("" + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("Insert familia");
        try {

            Familias parametroFamilia = convertir.fromJson(request.getReader(), Familias.class);
            System.out.println("Json familia: " + parametroFamilia.getNombre());
            FamiliaDao.insertar(parametroFamilia.getNombre());
            out.println(convertir.toJson("Familia insertada"));
        } catch (ClassNotFoundException ex) {
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
            System.out.println("Error, no se pudo insertar en la base de datos: " + ex);
        } catch (SQLException ex) {
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
            System.out.println("Error, no se pudo insertar en la base de datos: " + ex);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("Delete familia");
        try {
            Familias parametroFamilia = convertir.fromJson(request.getReader(), Familias.class);
            System.out.println("Json familia: " + parametroFamilia.getId());
            FamiliaDao.borrar(parametroFamilia.getId());
            out.println(convertir.toJson("Familia borrada"));
        } catch (ClassNotFoundException ex) {
            System.out.println("Familia borrada");
            out.println(convertir.toJson("Error: " + ex.getMessage()));
        } catch (SQLException ex) {
            out.println(convertir.toJson("Error: " + ex.getMessage()));
            System.out.println("Familia borrada");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("Insert familia");
        
       
        try {
             Familias parametroFamilia = convertir.fromJson(request.getReader(), Familias.class);
            System.out.println("Json familia: " + parametroFamilia.getNombre());
            FamiliaDao.actualizar(parametroFamilia.getNombre(), parametroFamilia.getId());
        } catch (ClassNotFoundException ex) {
            System.out.println("Familia actualizada");
            out.println(convertir.toJson("Error: " + ex.getMessage()));
        } catch (SQLException ex) {
            System.out.println("Familia actualizada");
            out.println(convertir.toJson("Error: " + ex.getMessage()));
        }
            out.println(convertir.toJson("Familia actualizada"));
    }

}
