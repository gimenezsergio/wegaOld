package presentacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Informes", urlPatterns = {"/Informes", "/privado/Informes"})
public class Informes extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        {
            try {
                Map<String, Map> listado = convertir.fromJson(
                        request.getReader(),
                        new TypeToken<Map<String, Map>>() {
                        }.getType()
                );

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/wega",
                        "educacion",
                        "educacion");
                //response.getWriter().println(convertir.toJson(listado));

                String elSql = " SELECT * FROM reclamos LIMIT 1";

                PreparedStatement sentencia = conn.prepareStatement(elSql);
                String encab = "campos";
                String valores = "datos";
                TreeMap<String, ArrayList> reporte = new TreeMap();
                reporte.put(encab, new ArrayList());
                reporte.put(valores, new ArrayList());
                ResultSet res = sentencia.executeQuery();
                int cantidadCampos = res.getMetaData().getColumnCount();
                for (int ii = 1; ii <= cantidadCampos; ii++) {
                    reporte.get(encab).add(res.getMetaData().getColumnName(ii));
                }
                while (res.next()) {
                    ArrayList unProcucto = new ArrayList();
                    for (int jj = 1; jj <= cantidadCampos; jj++) {
                        unProcucto.add(res.getString(jj));
                    }
                    reporte.get(valores).add(unProcucto);

                }
                
                
            String resultado = convertir.toJson(reporte);
                
                

                response.getWriter().println(resultado);
                
            } catch (ClassNotFoundException ex) {
                response.getWriter().println(convertir.toJson("ERROR: " + ex.getMessage()));
            } catch (InstantiationException ex) {
                response.getWriter().println(convertir.toJson("ERROR: " + ex.getMessage()));
            } catch (IllegalAccessException ex) {
                response.getWriter().println(convertir.toJson("ERROR: " + ex.getMessage()));
            } catch (SQLException ex) {
                response.getWriter().println(convertir.toJson("ERROR: " + ex.getMessage()));
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
