package ingresos.persistencia;

import entidades.DB;
import ingresos.entidades.BusquedProd;
import ingresos.entidades.BusquedaProveedor;
import ingresos.entidades.Proveedor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BusquedaIngresosProductoDAO {

    private BusquedaIngresosProductoDAO() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static BusquedaIngresosProductoDAO INSTANCE = null;

    public static BusquedaIngresosProductoDAO getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new BusquedaIngresosProductoDAO();
        }
        return INSTANCE;
    }

    private final static String PROVEEDORES_SQL = "SELECT precarga_maestro_idprovee, proveedores.prov_nombre FROM precarga_detalle, precarga_maestro INNER JOIN proveedores ON proveedores.prov_id = precarga_maestro.precarga_maestro_idprovee WHERE ( precarga_detalle.precarga_detalle_idmaestro, precarga_maestro.precarga_maestro_idprovee ) IN( SELECT precarga_imaestro_id, precarga_maestro.precarga_maestro_idprovee FROM precarga_maestro WHERE precarga_maestro_fechamanifesto >= ? AND precarga_maestro_fechamanifesto <= ? ) AND precarga_detalle.precarga_detalle_idproducto = ? GROUP BY precarga_maestro.precarga_maestro_idprovee";
    private final static String CANTIDAD_SQL = "SELECT * FROM precarga_detalle, precarga_maestro WHERE ( precarga_detalle.precarga_detalle_idmaestro, precarga_maestro.precarga_maestro_idprovee ) IN( SELECT precarga_imaestro_id, precarga_maestro.precarga_maestro_idprovee FROM precarga_maestro WHERE precarga_maestro_fechamanifesto >= ? AND precarga_maestro_fechamanifesto <= ? ) AND precarga_detalle.precarga_detalle_idproducto = ? AND precarga_maestro.precarga_maestro_idprovee = ? GROUP BY precarga_detalle.precarga_detalle_idmaestro";

    public ArrayList<BusquedProd> producto(int productoId, String desde, String hasta) throws ClassNotFoundException, IOException, SQLException {
        ArrayList<BusquedProd> listado = new ArrayList();
        ArrayList<Proveedor> proveedoresList = new ArrayList();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Connection conCant = null;
        PreparedStatement pstmCant = null;
        ResultSet rsCant = null;

        con = DB.getInstance().getConnection();
        pstm = con.prepareStatement(PROVEEDORES_SQL);
        pstm.setString(1, desde);
        pstm.setString(2, hasta);
        pstm.setInt(3, productoId);

        rs = pstm.executeQuery();
        while (rs.next()) {
//            BusquedaProveedor unProducto = new BusquedaProveedor();
//            unProducto.setProveedor(rs.getString("prov_nombre"));
//            unProducto.setCantidad(rs.getInt("cant_total"));
//            listado.add(unProducto);
            //System.out.println(rs.getString("precarga_maestro_idprovee"));
            Proveedor miProveedor = new Proveedor();
            miProveedor.setId(rs.getString("precarga_maestro_idprovee"));
            miProveedor.setNombre(rs.getString("prov_nombre"));
            proveedoresList.add(miProveedor);
        }

        for (int i = 0; i < proveedoresList.size(); i++) {
            System.out.println("Prov 2 : " + proveedoresList.get(i));
            conCant = DB.getInstance().getConnection();
            pstmCant = con.prepareStatement(CANTIDAD_SQL);
            pstmCant.setString(1, desde);
            pstmCant.setString(2, hasta);
            pstmCant.setInt(3, productoId);
            pstmCant.setString(4, proveedoresList.get(i).getId());
            rsCant = pstmCant.executeQuery();

            int cantTotal = 0;
            BusquedProd busquedaProveedor = new BusquedProd();
            while (rsCant.next()) {
                //cantTotal = 0;

                //System.out.println(proveedoresList.get(i) + " | " + rsCant.getString("precarga_detalle_cant"));
                cantTotal = cantTotal + Integer.parseInt(rsCant.getString("precarga_detalle_cant"));
                busquedaProveedor.setProveedor(proveedoresList.get(i).getNombre());
                busquedaProveedor.setCantidad(cantTotal);

            }
            listado.add(busquedaProveedor);
            System.out.println(busquedaProveedor);

            if (rsCant != null) {
                try {
                    rsCant.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmCant != null) {
                try {
                    pstmCant.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conCant != null) {
                try {
                    conCant.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        System.out.println(proveedoresList);

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listado;
    }

}
