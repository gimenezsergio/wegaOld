package presentacion;

import com.google.gson.Gson;
import entidades.ReclamoBusqueda;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.ReclamoDaoExtra;

@WebServlet(name = "ReclamosFundados", urlPatterns = {"/ReclamosFundados"})
public class ReclamosFundados extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String paramBusqueda = request.getParameter("busquedaJsonStr");
        System.out.println("BUsqueda: " + paramBusqueda);

        String id = request.getParameter("id");
        String desde = request.getParameter("desde");
        String hasta = request.getParameter("hasta");
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        String IdSession = request.getSession().getId();
        System.out.println("Tipo: " + tipo);

        System.out.println("Producto seleccionado");
        try {
            ArrayList<ReclamoBusqueda> reportFundados = ReclamoDaoExtra.getInstance().obtenerBusqueda(id, desde, hasta, IdSession, tipo);
            System.out.println("REPORTE FUNDADOS: " + convertir.toJson(reportFundados));
            out.println(convertir.toJson(reportFundados));
        } catch (ClassNotFoundException ex) {
            System.out.println("" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }

    }

}
