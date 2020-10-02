package presentacion;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.InformesClienteParticulares;
import entidades.MensajeBrowser;
import java.sql.SQLException;
import java.util.ArrayList;
import persistencia.InformeClienteParticularDao;

@WebServlet(name = "InformeClienteParticular", urlPatterns = {"/InformeClienteParticular"})
public class InformeClienteParticular extends HttpServlet {
Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ArrayList<InformesClienteParticulares> listado = InformeClienteParticularDao.getInstance().obtenerUltimos();
            String resultado = convertir.toJson( listado );
            System.out.println("resultado: " + resultado);
            if (resultado!=null){
                out.println(resultado);
                System.out.println("resultado: " + resultado);
            }else{
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("Insert Informe tecnico");
        MensajeBrowser mensajeInsertError = new MensajeBrowser("Advetencia", "Error, no se pudo insertar en la base de datos", "warning");
       MensajeBrowser mensajeInsertOK = new MensajeBrowser("OK", "Informe tecnico guardado", "success");

        try {
            InformesClienteParticulares miInfoTest = new InformesClienteParticulares();
            System.out.println("Mi informe de test: " + convertir.toJson(miInfoTest));
            InformesClienteParticulares parametroInforme = convertir.fromJson(request.getReader(), InformesClienteParticulares.class);
            
             System.out.println("Json informe tecnico: " + convertir.toJson(parametroInforme));
            InformeClienteParticularDao.insertar(parametroInforme);
            out.println(convertir.toJson(mensajeInsertOK));
        } catch (ClassNotFoundException ex) {
            //out.println(convertir.toJson(ex.getMessage()));
            System.out.println("Error, no se pudo insertar en la base de datos: " + ex);
        } catch (SQLException ex) {
            //out.println(convertir.toJson(ex.getMessage()));
            System.out.println("Error, no se pudo insertar en la base de datos: " + ex);
        }

        System.out.println("ok");   
        
    }
    
    


}
