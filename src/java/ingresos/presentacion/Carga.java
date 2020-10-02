package ingresos.presentacion;

import com.google.gson.Gson;
import entidades.MensajeBrowser;
import ingresos.entidades.CargaParam;
import ingresos.entidades.Precargas;
import ingresos.persistencia.CargaDao;
import ingresos.persistencia.PrecargaDAO;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Carga", urlPatterns = {"/Carga", "/privado/Carga"})
public class Carga extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        MensajeBrowser mensajeOK = new MensajeBrowser("OK", "Carga cerrada", "success");

        //Map cargaParam = convertir.fromJson(new InputStreamReader(req.getInputStream()), Map.class);
        CargaParam cargaDatos = convertir.fromJson(new InputStreamReader(req.getInputStream()), CargaParam.class);
        System.out.println("parametros: " + cargaDatos);
        try {
            System.out.println(cargaDatos.getComentarios());
             for (int x = 0; x < cargaDatos.getComentarios().size(); x++) {
                System.out.println(cargaDatos.getComentarios().get(x).getIdDetale());
                System.out.println(cargaDatos.getComentarios().get(x).getObs());
                System.out.println(cargaDatos.getIdMaestro());
                System.out.println(cargaDatos.getFecha());
            }
             
             cargaDatos.setAnalista(cargaDatos.getAnalista());
            //String fechaRecepcion = (String) cargaParam.get("fecha_recepcion");
            if (cargaDatos.getFecha() == null || cargaDatos.getFecha().isEmpty()) {
                throw new Exception("La fecha de recepción no puede estar vacia.");
            } else {
                CargaDao.updateStatus(cargaDatos.getIdMaestro(), cargaDatos.getFecha(), cargaDatos);
                out.println(convertir.toJson(mensajeOK));
            }
//            System.out.println("id maestro: " + cargaParam.get("itemMaestroId"));
//            System.out.println("fecha: " + cargaParam.get("fecha_recepcion"));
//            System.out.println("comentarios: " + cargaParam.get("comentarios"));
           // ArrayList comentarios = new ArrayList();
           // comentarios = (ArrayList) cargaParam.get("comentarios");
//            for (int x = 0; x < comentarios.size(); x++) {
//                System.out.println(comentarios.get(x));
//            }
//            String fechaRecepcion = (String) cargaParam.get("fecha_recepcion");
//            if (fechaRecepcion == null || fechaRecepcion.isEmpty()) {
//                throw new Exception("La fecha de recepción no puede estar vacia.");
//            } else {
//                CargaDao.updateStatus((String) cargaParam.get("itemMaestroId"), (String) cargaParam.get("fecha_recepcion"));
//                out.println(convertir.toJson(mensajeOK));
//            }
            //}
        } catch (Exception ex) {
            MensajeBrowser mensajeError = new MensajeBrowser("Advertencia", ex.getMessage(), "warning");
            System.out.println("Exeption CARGA: " + ex.getMessage());
            ex.printStackTrace();
            out.println(convertir.toJson(mensajeError));
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        ArrayList<Precargas> listado;
        try {
            
            listado = CargaDao.getInstance().obtenerUltimosConPlanControl(req.getParameter("maestroId"), req.getParameter("detalleId"), req.getParameter("productoId"), req.getParameter("cant"));
            //listado = new ArrayList<>();
            //System.out.println("parametro: " + req.getParameter("cant"));
            
            System.out.println("Mi sesion: " + req.getSession().getId());
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

}
