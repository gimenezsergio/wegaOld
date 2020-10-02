package presentacion;

import com.google.gson.Gson;
import persistencia.FallaMaterialesDao;
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
import entidades.FallaMateriales;

@WebServlet(name = "FallaMaterial", urlPatterns = {"/FallaMaterial", "/privado/FallaMaterial"})
public class FallaMaterial extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String texto = request.getReader().readLine();
            ArrayList<FallaMateriales> listado;
            listado = FallaMaterialesDao.getInstance().obtener();
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
        System.out.println("Insert ciente");
        
       
        try {
            FallaMateriales parametroFallaMat = convertir.fromJson(request.getReader(), FallaMateriales.class);
             System.out.println("Json clientes: " + parametroFallaMat);
            FallaMaterialesDao.Insertar(parametroFallaMat);
            out.println(convertir.toJson("Falla de Material insertada"));
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

            FallaMateriales parametroFallaMat = convertir.fromJson(request.getReader(), FallaMateriales.class);
            System.out.println(" json Falla Material :" + parametroFallaMat);
            FallaMaterialesDao.actualizar(parametroFallaMat);
            out.println(convertir.toJson("Falla de Material actualizada"));
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

        FallaMateriales FallaMatParametro = convertir.fromJson(req.getParameter("q"), FallaMateriales.class);
        try {
            FallaMaterialesDao.borrar(FallaMatParametro);
            out.println(convertir.toJson("Falla de Material borrada"));
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
