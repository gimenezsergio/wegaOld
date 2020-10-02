package ingresos.presentacion;

import ingresos.persistencia.PlanControlDisenoDAO;
import com.google.gson.Gson;
import entidades.MensajeBrowser;
import ingresos.entidades.Precargas;
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

@WebServlet(name = "PlanControlDiseno", urlPatterns = {"/PlanControlDiseno", "/privado/PlanControlDiseno"})
public class PlanControlDiseno extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Map miPc = convertir.fromJson(new InputStreamReader(req.getInputStream()), Map.class);
        System.out.println("Plan de control: " + miPc);
        String PlanControlEnviado = (String) miPc.get("plan");
        MensajeBrowser mensajeError;

        try {
            if (PlanControlEnviado.equals("BujiasEncendido")) {
                System.out.println("Plan de control  es BujiasEncendido ");
                PlanControlDisenoDAO.bujiaEncenInsertGenerico(miPc);
                MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Bujia guardado", "success");
                out.println(convertir.toJson(mensajeOk));

            } else if (PlanControlEnviado.equals("BujiasPrecalentamiento")) {
                System.out.println("Plan de control  es BujiasPrecalentamiento ");
                MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Bujia guardado", "success");
                PlanControlDisenoDAO.bujiaPrecaInsertGENERICO(miPc);
                out.println(convertir.toJson(mensajeOk));
            } else if (PlanControlEnviado.equals("Lamparas")) {
                System.out.println("Plan de control es Lamparas ");
                MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Lamparas", "success");
                PlanControlDisenoDAO.lamparasInsertGENERICO(miPc);
                out.println(convertir.toJson(mensajeOk));
            } else if (PlanControlEnviado.equals("Airepanel")) {
                System.out.println("Plan de control es Filtro Panel Aire (FAP) ");
                MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control FAP", "success");
                PlanControlDisenoDAO.aireFAPInsert(miPc);
                out.println(convertir.toJson(mensajeOk));
            } else if (PlanControlEnviado.equals("Airehabitáculo")) {
                System.out.println("Plan de control es Aire habitaculo ");
                MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Aire Habitaculo", "success");
                PlanControlDisenoDAO.aireHabitaculoInsertGENERICO(miPc);
                out.println(convertir.toJson(mensajeOk));
            } else if (PlanControlEnviado.equals("AceiteU. Sellada")) {
                System.out.println("Plan de control es Aceite Unidad Sellada.");
                MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Aceite Unidad Sellada", "success");
                PlanControlDisenoDAO.aceiteUSelladaInsert(miPc);
                out.println(convertir.toJson(mensajeOk));
            } else if (PlanControlEnviado.equals("AceiteEcológico") || PlanControlEnviado.equals("AceiteElemento Filtrante")) {
                System.out.println("Plan de control es Aceite Cartucho.");
                PlanControlDisenoDAO.aceiteCartuchoInsert(miPc);
                MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Aceite Cartucho", "success");
                out.println(convertir.toJson(mensajeOk));
            } else if (PlanControlEnviado.equals("Aireredondos")) {
                System.out.println("Plan de control es Aire Redondo.");
                PlanControlDisenoDAO.aireRedondo(miPc);
                MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Aire Redondo", "success");
                out.println(convertir.toJson(mensajeOk));
            } else if (PlanControlEnviado.equals("Airepesados")) {
                System.out.println("Plan de control es Aire pesados.");
                try {
                    PlanControlDisenoDAO.airePesado(miPc);
                    MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Aire Pesados", "success");
                    out.println(convertir.toJson(mensajeOk));
                } catch (Exception ex) {
                    mensajeError = new MensajeBrowser("Advertencia", ex.getMessage(), "warning");
                    out.println(convertir.toJson(mensajeError));
                }

            } else if (PlanControlEnviado.equals("CombustibleNafta Cartucho") || PlanControlEnviado.equals("AceiteNafta Cartucho")) {
                System.out.println("Plan de control es Nafta Cartucho.");
                try {
                    PlanControlDisenoDAO.naftaCartucho(miPc);
                    MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Nafta Cartucho", "success");
                    out.println(convertir.toJson(mensajeOk));
                } catch (Exception ex) {
                    mensajeError = new MensajeBrowser("Advertencia", ex.getMessage(), "warning");
                    out.println(convertir.toJson(mensajeError));
                }
            } else if (PlanControlEnviado.equals("CombustibleU.Sellada")) {
                System.out.println("Plan de control es Nafta U. Sellada");
                try {
                    PlanControlDisenoDAO.naftaUSellada(miPc);
                    MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Nafta U. Sellada", "success");
                    out.println(convertir.toJson(mensajeOk));
                } catch (Exception ex) {
                    mensajeError = new MensajeBrowser("Advertencia", ex.getMessage(), "warning");
                    out.println(convertir.toJson(mensajeError));
                }

            } else if (PlanControlEnviado.equals("CombustibleDiesel Sellada plastico")) {
                System.out.println("Plan de control es Combustible Diesel Sellada plastico");
                try {
                    PlanControlDisenoDAO.dieselUSelladaPlastico(miPc);
                    MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Combustible Diesel U. Sellada Plástico", "success");
                    out.println(convertir.toJson(mensajeOk));
                } catch (Exception ex) {
                    mensajeError = new MensajeBrowser("Advertencia", ex.getMessage(), "warning");
                    out.println(convertir.toJson(mensajeError));
                }
            } else if (PlanControlEnviado.equals("CombustibleDisel Sellada metalica")) {
                System.out.println("Plan de control es CombustibleDisel Sellada metalica");
                try {
                    PlanControlDisenoDAO.dieselUSelladaMetalica(miPc);
                    MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Combustible Disel U. Sellada Metálica", "success");
                    out.println(convertir.toJson(mensajeOk));
                } catch (Exception ex) {
                    mensajeError = new MensajeBrowser("Advertencia", ex.getMessage(), "warning");
                    out.println(convertir.toJson(mensajeError));
                }

            } else if (PlanControlEnviado.equals("CombustibleNaftaTank")) {
                System.out.println("Plan de control es CombustibleNaftaTank");
                try {
                    PlanControlDisenoDAO.naftaTank(miPc);
                    MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Combustible Disel U. Sellada Metálica", "success");
                    out.println(convertir.toJson(mensajeOk));
                } catch (Exception ex) {
                    mensajeError = new MensajeBrowser("Advertencia", ex.getMessage(), "warning");
                    out.println(convertir.toJson(mensajeError));
                }

            } else if (PlanControlEnviado.equals("CombustibleDisel Cartucho")) {
                System.out.println("Plan de control es Combustible Diesel cartucho");
                try {
                    PlanControlDisenoDAO.dieselCartucho(miPc);
                    MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Combustible Disel Cartucho", "success");
                    out.println(convertir.toJson(mensajeOk));
                } catch (Exception ex) {
                    mensajeError = new MensajeBrowser("Advertencia", ex.getMessage(), "warning");
                    out.println(convertir.toJson(mensajeError));
                }

            } else if (PlanControlEnviado.equals("Airefrenos")) {
                System.out.println("Plan de control es Aire Frenos");
                try {
                    PlanControlDisenoDAO.aireFrenos(miPc);
                    MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Plan de Control Aire Frenos", "success");
                    out.println(convertir.toJson(mensajeOk));
                } catch (Exception ex) {
                    mensajeError = new MensajeBrowser("Advertencia", ex.getMessage(), "warning");
                    out.println(convertir.toJson(mensajeError));
                }

            } else {
                System.out.println("Este producto no tiene plan de control");
                mensajeError = new MensajeBrowser("Advetencia", "Este producto no tiene plan de control", "warning");
                out.println(convertir.toJson(mensajeError));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            mensajeError = new MensajeBrowser("Advetencia", ex.getMessage(), "warning");
            System.out.println("Exeption de plan: " + ex.getMessage());
            out.println(convertir.toJson(mensajeError));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("Id maestro: " + request.getParameter("maestroId"));
        System.out.println("Id detalle: " + request.getParameter("detalleId"));
        System.out.println("Id producto: " + request.getParameter("productoId"));

        PrintWriter out = response.getWriter();
        ArrayList<Precargas> listado;
        try {
            //listado = PlanControlDisenoDAO.getInstance().obtenerUltimos(request.getParameter("id"));
            listado = PlanControlDisenoDAO.getInstance().obtenerIndividual(request.getParameter("maestroId"), request.getParameter("detalleId"), request.getParameter("productoId"));
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

}
