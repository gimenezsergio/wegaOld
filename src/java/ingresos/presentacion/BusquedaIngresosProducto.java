/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingresos.presentacion;

import ingresos.persistencia.BusquedaIngresosProductoDAO;
import com.google.gson.Gson;
import ingresos.entidades.BusquedProd;
import ingresos.entidades.BusquedaProveedor;
import ingresos.persistencia.BusquedaIngresosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BusquedaIngresosProducto", urlPatterns = {"/BusquedaIngresosProducto"})
public class BusquedaIngresosProducto extends HttpServlet {

    Gson convertir = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        System.out.println("Del cliente: " + req.getParameter("busqueda"));
        TreeMap parametros = convertir.fromJson(req.getParameter("busqueda"), TreeMap.class);
        System.out.println(parametros);
        System.out.println(parametros.get("id_producto"));
        int productoId = Integer.parseInt((String) parametros.get("id_producto"));
        String desde = (String) parametros.get("fecha_desde");
        String hasta = (String) parametros.get("fecha_hasta");

//        int proveedorId = 37;
//        String desde = "2018-01-01";
//        String hasta = "2019-09-17";
        try {
            //ArrayList<BusquedaProveedor> no_OK = BusquedaIngresosDAO.getInstance().no_ok(proveedorId, desde, hasta);
            ArrayList<BusquedProd> no_OK = BusquedaIngresosProductoDAO.getInstance().producto(productoId, desde, hasta);
            resp.getWriter().println(convertir.toJson(no_OK));
        } catch (ClassNotFoundException ex) {
            out.println(ex.getMessage());
        } catch (SQLException ex) {
            out.println(ex.getMessage());
        }

        //out.println("'No OK'");
    }

}
