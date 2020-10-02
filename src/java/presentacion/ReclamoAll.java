package presentacion;

import com.google.gson.Gson;
import entidades.ReclamosOld;
import persistencia.ReclamoAllDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ReclamoAll", urlPatterns = {"/ReclamoAll"})
public class ReclamoAll extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ArrayList<ReclamosOld> listado;

        try {
            ///////////////////////////////////////////////////
            ///////////////////////////////////////////////////

            

            HttpSession session = request.getSession(false);
            System.out.println("session: " + session.getId());
            //String n = (String) session.getAttribute("uname");
//            String userId = (String) session.getAttribute("usuarioId");
//            String userName = (String) session.getAttribute("nombre");
//            System.out.println("UserId: " + userId);
//            System.out.println("UserId: " + userName);
//            System.out.println("SESSION ::" + session);
//            //System.out.println(session.getAttributeNames());
//            Enumeration e = (Enumeration) (session.getAttributeNames());
//            if (e == null){
//                System.out.println("NO hay atributos");
//            }else{
//                System.out.println("Hay atributos");
//            }
//            System.out.println(e.hasMoreElements());
//
//            while (e.hasMoreElements()) {
//                System.out.println("Adentro");
//                Object tring;
//                if ((tring = e.nextElement()) != null) {
//                    out.println("value " + session.getValue((String) tring));
//                    out.println("<br/>");
//                }else{
//                    System.out.println("NO hay atributos");
//                }
//
//            }

            /////////////////////////////////////////////////
            //////////////////////////////////////////////////
            listado = ReclamoAllDao.getInstance().obtener();
            String resultado = convertir.toJson(listado);
            out.println("" + resultado);
        } catch (ClassNotFoundException ex) {
            System.out.println("Exeption: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Exeption: " + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
