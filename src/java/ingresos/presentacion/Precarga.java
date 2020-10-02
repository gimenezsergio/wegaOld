package ingresos.presentacion;

import ingresos.persistencia.PrecargaDAO;
import com.google.gson.Gson;
import entidades.MensajeBrowser;
import ingresos.entidades.Precargas;
import ingresos.entidades.PrecargasMaestro;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Precarga", urlPatterns = {"/Precarga", "/privado/Precarga"})
public class Precarga extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ArrayList<Precargas> listado;
        try {
            listado = PrecargaDAO.getInstance().obtenerUltimos();
            System.out.println("Mi sesion: " + request.getSession().getId());
            String resultado = convertir.toJson(listado);
            System.out.println("resultado: " + resultado);
            if (resultado != null) {
                out.println(resultado);
                System.out.println("resultado: " + resultado);
            } else {
                out.println("Sin resultados");
            }
        } catch (Exception ex) {
            System.out.println("Exeption: " + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        MensajeBrowser mensajeOK = new MensajeBrowser("OK", "Precarga guardada", "success");
        Map precargaDedetalle = convertir.fromJson(new InputStreamReader(request.getInputStream()), Map.class);
        System.out.println(precargaDedetalle);
        Map encabezado = (Map) precargaDedetalle.get("encabezado");
        List productos = (List) precargaDedetalle.get("productos");

        //System.out.println("Encabezado: " + encabezado);
        //System.out.println("products: " + productos);
        try {
            PrecargaDAO.insert(precargaDedetalle);
            out.println(convertir.toJson(mensajeOK));
        } catch (Exception ex) {
            MensajeBrowser mensajeError = new MensajeBrowser("Advetencia", ex.getMessage(), "warning");
            System.out.println("Alerta: " + ex.getMessage());
            out.println(convertir.toJson(mensajeError));
        }
    }

}
