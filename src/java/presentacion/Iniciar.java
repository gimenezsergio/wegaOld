package presentacion;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;

public class Iniciar extends HttpServlet {   

   public void init() throws ServletException {
       System.out.println("Iniciando aplicacion web.");
       try {
           //Micache.consultaTipoObs();
           MiCache.consultaTipoObs();
       } catch (ClassNotFoundException ex) {
           System.out.println("ClassNotFoundException : " + ex.getMessage() );
       } catch (IOException ex) {
           System.out.println("IOException : " + ex.getMessage() );
       } catch (SQLException ex) {
           System.out.println("SQLException : " + ex.getMessage() );
       }
   }
   
   public void destroy() {
      // do nothing.
   }
}