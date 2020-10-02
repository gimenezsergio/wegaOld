package presentacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entidades.MensajeBrowser;
import entidades.ReclamoBusqueda;
import entidades.Reclamos;
import entidades.ReclamosOld;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import logica.ReclamosLogic;
import persistencia.ReclamoDao;
import persistencia.ReclamoDaoExtra;

@WebServlet(name = "reclamo", urlPatterns = {"/reclamo", "/privado/reclamo"})
public class Reclamo extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String paramBusqueda = request.getParameter("busquedaJsonStr");
        System.out.println("BUsqueda: " + paramBusqueda);
        //String texto = request.getParameter("param");
        System.out.println("Cliente: " + request.getParameter("id"));
        System.out.println("Desde: " + request.getParameter("desde"));
        System.out.println("Hasta: " + request.getParameter("hasta"));

        String id = request.getParameter("id");
        String desde = request.getParameter("desde");
        String hasta = request.getParameter("hasta");
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        String IdSession = request.getSession().getId();
        System.out.println("Tipo: " + tipo);
        
       
        try {
            MensajeBrowser mensajeBusquedaVacia = new MensajeBrowser("Advetencia", "Sin resultados", "warning");
            ArrayList<Reclamos> listado = ReclamoDao.getInstance().obtenerBusqueda(id, desde, hasta, IdSession, tipo);
            String resultado = convertir.toJson(listado);
            if (!listado.isEmpty()) {
                System.out.println("listado con resultados");
                out.println(resultado);
            } else {
                out.println(convertir.toJson(mensajeBusquedaVacia));
                System.out.println("listado vacio::: " + mensajeBusquedaVacia);
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
        PrintWriter out = response.getWriter();
        Map reclamoDedetalla = convertir.fromJson(new InputStreamReader(request.getInputStream()), Map.class);
        System.out.println(reclamoDedetalla);
        Map encabezado = (Map) reclamoDedetalla.get("encabezado");
        List productos = (List) reclamoDedetalla.get("productos");

        System.out.println("Encabezado: " + encabezado);
        System.out.println("products: " + productos);

        try {
            ReclamoDao.insertar(reclamoDedetalla);
            MensajeBrowser mensajeReclamoOK = new MensajeBrowser("Ok", "Reclamo guardado", "success");
            ///out.println(convertir.toJson("<!-- mjsError de error -->"  + "Reclamo guardado."));
            out.println(convertir.toJson(mensajeReclamoOK));
            System.out.println("Reclamo guardado");
        } catch (Exception ex) {
            MensajeBrowser mensajeReclamoError = new MensajeBrowser("Error", ex.getMessage(), "warning");
            //out.println(convertir.toJson("<!-- mjsError de error -->" + ex.getMessage() ));
            out.println(convertir.toJson(mensajeReclamoError));
            ex.printStackTrace();
            System.out.println("Exception: " + "<!-- mjsError de error -->" + ex.getStackTrace());
            System.out.println("Exception: " + "<!-- mjsError de error -->" + ex.getMessage());
        }

    }
}
