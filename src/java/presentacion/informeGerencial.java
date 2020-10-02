package presentacion;

import entidades.InfoBusqueda;
import com.google.gson.Gson;
import entidades.FallaMateriales;
import entidades.MensajeBrowser;
import entidades.ReclamosOld;
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
import persistencia.InformeGerencialDao;

@WebServlet(name = "informeGerencial", urlPatterns = {"/informeGerencial"})
public class informeGerencial extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        InfoBusqueda parametros = convertir.fromJson(req.getReader(), InfoBusqueda.class);
        System.out.println("parametros: " + parametros);
       
        try {
            String texto = req.getReader().readLine();
            ArrayList<ReclamosOld> listado;
            listado = InformeGerencialDao.getInstance().obtener();
            String resultado = convertir.toJson(listado);
            out.println("" + resultado);
            

        } catch (ClassNotFoundException ex) {
            System.out.println("Exception: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("doPut");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        
        try {
            InfoBusqueda parametros = convertir.fromJson(req.getReader(), InfoBusqueda.class);           
            if (parametros.getDesde() == null || parametros.getDesde().isEmpty()){
                throw new Exception("La fecha desde no puede estar vacia");
            }
            
            if (parametros.getHasta() == null || parametros.getHasta().isEmpty()){
                throw new Exception("La fecha hasta no puede estar vacia");
            }
            
            ArrayList<ReclamosOld> resultado = InformeGerencialDao.getInstance().busqueda(parametros.getDesde(), parametros.getHasta());
            convertir.toJson(resultado);
            out.println("" + convertir.toJson(resultado));
            
        } catch (ClassNotFoundException ex) {
           MensajeBrowser mensajeReclamoError = new MensajeBrowser("Error", ex.getMessage(), "warning");
           out.println(convertir.toJson( mensajeReclamoError ));
            System.out.println("Error ClassNotFoundException: " + ex.getMessage());
        } catch (SQLException ex) {
            MensajeBrowser mensajeReclamoError = new MensajeBrowser("Error", ex.getMessage(), "warning");
            out.println(convertir.toJson( mensajeReclamoError ));
            System.out.println("Error SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            MensajeBrowser mensajeReclamoError = new MensajeBrowser("Error", ex.getMessage(), "warning");
            out.println(convertir.toJson( mensajeReclamoError ));
            System.out.println("Error Exception: " + ex.getMessage());
        }
    }

}
