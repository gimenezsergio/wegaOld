package presentacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entidades.MensajeBrowser;
import entidades.Productos;
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
import persistencia.ProductoDao;

@WebServlet(name = "producto", urlPatterns = {"/producto", "/privado/producto"})
public class Producto extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            if (request.getParameter("q") == null) {
                System.out.println("Sin parametros");
                String texto = request.getReader().readLine();
                ArrayList<Productos> listado = ProductoDao.getInstance().obtener();

                String resultado = convertir.toJson(listado);
                out.println("" + resultado);

            } else {
                System.out.println("Con Parametros");
                String idProduct = request.getParameter("q");
                ArrayList<Productos> listado = ProductoDao.getInstance().obtenerFamilia(idProduct);
                String resultado = convertir.toJson(listado);
                out.println("" + resultado);
                //listado = FamiliaDao.getInstance().obtener();
            }

        } catch (ClassNotFoundException ex) {
            out.println("" + ex.getMessage());
        } catch (SQLException ex) {
            out.println("" + ex.getMessage());
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("Insert producto");

        try {
            
            Productos parametroProducto = convertir.fromJson(request.getReader(), Productos.class);
            Productos productoAux = new Productos(parametroProducto.getId_producto(), parametroProducto.getFamilia(), parametroProducto.getSubFamilia());
            System.out.println("Json clientes: " + convertir.toJson(parametroProducto));
            ProductoDao.insertar(parametroProducto);
            //out.println(convertir.toJson("Producto insertado"));
            MensajeBrowser mensajeProductoOK = new MensajeBrowser("Ok", "Producto agregado", "success");
            out.println(convertir.toJson(mensajeProductoOK));
        } catch (ClassNotFoundException ex) {
            out.println(convertir.toJson("Alerta, no se pudo insertar en la base de datos"));
            MensajeBrowser mensajeReclamoError = new MensajeBrowser("Error", ex.getMessage(), "warning");
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson(mensajeReclamoError));
        } catch (SQLException ex) {
            MensajeBrowser mensajeReclamoError = new MensajeBrowser("Error", ex.getMessage(), "warning");
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson(mensajeReclamoError));
            System.out.println("Error, no se pudo insertar en la base de datos: " + ex);
        } catch (Exception ex) {
            MensajeBrowser mensajeReclamoError = new MensajeBrowser("Error", ex.getMessage(), "warning");
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson(mensajeReclamoError));
        }

        System.out.println("ok");

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("put");

        try {
            

            Productos parametroProducto = convertir.fromJson(request.getReader(), Productos.class);
            Productos productoAux = new Productos(parametroProducto.getId_producto(), parametroProducto.getFamilia(), parametroProducto.getSubFamilia());
            if (parametroProducto.getFamilia() != null && !parametroProducto.getFamilia().isEmpty()) {
                if (parametroProducto.getId_producto() != null && !parametroProducto.getId_producto().isEmpty()) {
                    System.out.println(" Asignar familia :" + parametroProducto);
                    System.out.println(" json clientes :" + parametroProducto);
                    System.out.println("Pasa por uno");
                    ProductoDao.actualizarFamilia(productoAux);
                }

            } else {
                System.out.println("Pasa por dos");
                System.out.println("getFamilia: " + parametroProducto.getFamilia());
                System.out.println(" json clientes :" + parametroProducto);
                ProductoDao.actualizar(parametroProducto);

            }
            MensajeBrowser mensajeReclamoOK = new MensajeBrowser("Ok", "Producto actualizado", "success");
            out.println(convertir.toJson(mensajeReclamoOK));

        } catch (ClassNotFoundException ex) {
            MensajeBrowser mensajeReclamoError = new MensajeBrowser("Error", ex.getMessage(), "warning");
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
            out.println(convertir.toJson(mensajeReclamoError));
        } catch (SQLException ex) {
            MensajeBrowser mensajeReclamoError = new MensajeBrowser("Error", ex.getMessage(), "warning");
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson(mensajeReclamoError));
        } catch (Exception ex) {
            MensajeBrowser mensajeReclamoError = new MensajeBrowser("Error", ex.getMessage(), "warning");
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson(mensajeReclamoError));
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp); //To change body of generated methods, choose Tools | Templates.
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("Producto borrado");

        Productos ProductoParametro = convertir.fromJson(req.getParameter("q"), Productos.class);
        ProductoDao.borrar(ProductoParametro);
    }
}
