package ingresos.presentacion;

import com.google.gson.Gson;
import ingresos.entidades.Medidas;
import ingresos.persistencia.MedidasDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Medidas", urlPatterns = {"/Medidas", "/privado/Medidas"})
public class Medida extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        ArrayList<Medidas> listado = new ArrayList<>();
        try {
            System.out.println("idMedida: " + req.getParameter("id"));
            listado = MedidasDAO.getInstance().obtener(req.getParameter("id"));
            out.println(convertir.toJson(listado));
        } catch (Exception ex) {
            out.println("Sin resultados" + ex.getMessage());
        }
    }

}
