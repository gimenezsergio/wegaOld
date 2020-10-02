/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingresos.presentacion;

import com.google.gson.Gson;
import entidades.MensajeBrowser;
import ingresos.persistencia.PackagingDAO;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Packaging", urlPatterns = {"/Packaging", "/privado/Packaging"})
public class Packaging extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Map miPackaging = convertir.fromJson(new InputStreamReader(req.getInputStream()), Map.class);
        MensajeBrowser mensajeError;
        MensajeBrowser mensajeOk = new MensajeBrowser("Ok", "Packaging guardado", "success");
        try {
            PackagingDAO.insert(miPackaging);
            out.println(convertir.toJson(mensajeOk));
            
        } catch (Exception ex) {
            mensajeError = new MensajeBrowser("Advetencia", ex.getMessage(), "warning");
            System.out.println("Exeption: " + ex.getMessage());
            out.println(convertir.toJson(mensajeError));
        }
    }

}
