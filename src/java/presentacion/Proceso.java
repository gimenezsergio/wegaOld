package presentacion;

import com.google.gson.Gson;
import persistencia.ProcesoDao;
import entidades.Procesos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Proceso", urlPatterns = {"/Proceso", "/privado/Proceso"})
public class Proceso extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String texto = request.getReader().readLine();
            ArrayList<Procesos> listado = ProcesoDao.getInstance().obtener();
            String resultado = convertir.toJson(listado);
            out.println("" + resultado);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("Insert Proceso");

        try {
            Procesos parametroProceso = convertir.fromJson(request.getReader(), Procesos.class);
            System.out.println("Json Proceso: " + parametroProceso);
            ProcesoDao.Insertar(parametroProceso);
            out.println(convertir.toJson("Proceso insertado"));
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            Procesos parametroProceso = convertir.fromJson(request.getReader(), Procesos.class);
            System.out.println(" json proceso :" + parametroProceso);
            ProcesoDao.actualizar(parametroProceso);
            out.println(convertir.toJson("Proceso actualizado"));
        } catch (ClassNotFoundException ex) {
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
        } catch (SQLException ex) {
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp); //To change body of generated methods, choose Tools | Templates.
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Procesos procesoParametro = convertir.fromJson(req.getParameter("q"), Procesos.class);
        try {
            ProcesoDao.borrar(procesoParametro);
            out.println(convertir.toJson("Proceso borrado"));
        } catch (ClassNotFoundException ex) {
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
        } catch (SQLException ex) {
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
