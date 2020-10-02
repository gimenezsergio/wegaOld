package presentacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entidades.Clientes;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import persistencia.ClienteDao;

@WebServlet(name = "cliente", urlPatterns = {"/cliente", "/privado/cliente"})
public class Cliente extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String texto = request.getReader().readLine();
            ArrayList<Clientes> listado = ClienteDao.getInstance().obtener();
            Gson convertir = new Gson();
            String resultado = convertir.toJson(listado);
            out.println("" + resultado);

        } catch (ClassNotFoundException ex) {
            out.println("" + ex.getMessage());
        } catch (SQLException ex) {
            out.println("" + ex.getMessage());
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("Insert ciente");
        
       
        try {
            Clientes parametroCliente = convertir.fromJson(request.getReader(), Clientes.class);
             System.out.println("Json clientes: " + parametroCliente);
            ClienteDao.Insertar(parametroCliente);
            out.println(convertir.toJson("Cliente insertado"));
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
        }


    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            Clientes parametroCliente = convertir.fromJson(request.getReader(), Clientes.class);
            System.out.println(" json clientes :" + parametroCliente);
            ClienteDao.actualizar(parametroCliente);
            out.println(convertir.toJson("Cliente actualizado"));
        } catch (ClassNotFoundException ex) {
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
        } catch (SQLException ex) {
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp); //To change body of generated methods, choose Tools | Templates.
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Clientes clienteParametro = convertir.fromJson(req.getParameter("q"), Clientes.class);
        try {
            ClienteDao.borrar(clienteParametro);
            out.println(convertir.toJson("Cliente borrado"));
        } catch (ClassNotFoundException ex) {
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
        } catch (SQLException ex) {
            System.out.println(" Error: " + ex);
            out.println(convertir.toJson("Error, no se pudo insertar en la base de datos"));
        }
    }

}
