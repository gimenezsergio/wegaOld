package presentacion;

import com.google.gson.Gson;
import com.mysql.jdbc.Buffer;
import entidades.MensajeBrowser;
import entidades.MiUsuario;
import entidades.Reclamos;
import java.io.BufferedReader;
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
import persistencia.ReclamoDao;

@WebServlet(name = "ReclamoUltimos", urlPatterns = {"/ReclamoUltimos"})
public class ReclamoUltimos extends HttpServlet {

    Gson convertir = new Gson();
  
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //String IdSession = request.getSession().getId();
            String IdSession = request.getSession().getId();
            ArrayList<Reclamos> listado = ReclamoDao.getInstance().obtenerUltimos2(IdSession);
            System.out.println("Mi sesion: " + request.getSession().getId());
            //MiUsuario.getUsuario(IdSession);
            String resultado = convertir.toJson(listado);
            System.out.println("resultado: " + resultado);
            if (resultado != null) {
                out.println(resultado);
                System.out.println("resultado: " + resultado);
            } else {
                out.println("Sin resultados");
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        MensajeBrowser mensajeError = new MensajeBrowser("Advetencia", "Error", "warning");
        MensajeBrowser mensajeOK = new MensajeBrowser("OK", "Reclamo aprobado", "success");
        BufferedReader parametro = request.getReader();
        String idReclamo = parametro.readLine();
        try {
            ReclamoDao.actualizar(idReclamo);
            out.println(convertir.toJson(mensajeOK));
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
            out.println(convertir.toJson(mensajeError));
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            out.println(convertir.toJson(mensajeError));
        }

    }

}
