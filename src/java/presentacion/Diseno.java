package presentacion;

import persistencia.DisenoDao;
import entidades.Disenos;
import com.google.gson.Gson;
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

@WebServlet(name = "diseno", urlPatterns = {"/diseno", "/privado/diseno"})
public class Diseno extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String texto = request.getReader().readLine();
            ArrayList<Disenos> listado = DisenoDao.getInstance().obtener();
            String resultado = convertir.toJson(listado);
            out.println("" + resultado);
        } catch (ClassNotFoundException ex) {
            out.println("" + ex.getMessage());
        } catch (SQLException ex) {
            out.println("" + ex.getMessage());
        }
    }

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("Insert ciente");

        try {
            Disenos parametroDiseno = convertir.fromJson(request.getReader(), Disenos.class);
            System.out.println("Json diseno: " + parametroDiseno);
            DisenoDao.Insertar(parametroDiseno);
            out.println(convertir.toJson("Diseño insertado"));
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

            Disenos parametroCliente = convertir.fromJson(request.getReader(), Disenos.class);
            System.out.println(" json diseno :" + parametroCliente);
            DisenoDao.actualizar(parametroCliente);
            out.println(convertir.toJson("Diseño actualizado"));
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

        Disenos disenoParametro = convertir.fromJson(req.getParameter("q"), Disenos.class);
        try {
            DisenoDao.borrar(disenoParametro);
            out.println(convertir.toJson("Diseño borrado"));
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
