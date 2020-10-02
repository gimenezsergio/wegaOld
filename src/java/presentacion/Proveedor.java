package presentacion;

import com.google.gson.Gson;
import persistencia.ProveedoresDao;
import entidades.Proveedores;
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

@WebServlet(name = "Proveedor", urlPatterns = {"/Proveedor", "/privado/Proveedor"})
public class Proveedor extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String texto = request.getReader().readLine();
            ArrayList<Proveedores> listado;
            listado = ProveedoresDao.getInstance().obtener();
            String resultado = convertir.toJson(listado);
            out.println("" + resultado);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("Insert Proveedor");
        
       
        try {
            Proveedores parametroProveedores = convertir.fromJson(request.getReader(), Proveedores.class);
             System.out.println("Json clientes: " + parametroProveedores);
            ProveedoresDao.Insertar(parametroProveedores);
            out.println(convertir.toJson("Proveedor insertado"));
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
        System.out.println("Metodo PUT de proveedor");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            Proveedores parametroProveedor = convertir.fromJson(request.getReader(), Proveedores.class);
            System.out.println(" json proveedor :" + parametroProveedor);
            ProveedoresDao.actualizar(parametroProveedor);
            //ClienteDao.actualizar(parametroCliente);
            out.println(convertir.toJson("Proveedor actualizado"));
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
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Proveedores proveedorParametro = convertir.fromJson(req.getParameter("q"), Proveedores.class);
        try {
            ProveedoresDao.borrar(proveedorParametro);
            out.println(convertir.toJson("Proveedor borrado"));
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
