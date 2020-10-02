package persistencia;

import entidades.DB;
import entidades.ReclamoBusqueda;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReclamoDaoExtra {

    private ReclamoDaoExtra() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static ReclamoDaoExtra INSTANCE = null;

    public static ReclamoDaoExtra getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new ReclamoDaoExtra();
        }
        return INSTANCE;
    }

    private final static String SQL_PRODUCTO_FUNDADOS = "SELECT productos.codigo_producto, reclamos_detalle.id_producto, SUM(reclamos_detalle.cant_fundados) fundados, reclamos_detalle.id_provee, proveedores.prov_nombre FROM reclamos_encabezado JOIN reclamos_detalle ON reclamos_detalle.id_reclamos_detalle_item = reclamos_encabezado.id_reclamos_encabezado JOIN productos ON reclamos_detalle.id_producto = productos.id_producto JOIN proveedores ON reclamos_detalle.id_provee = proveedores.prov_id WHERE fecha_recepcion >= ? AND fecha_recepcion <= ? AND reclamos_detalle.id_producto = ? AND reclamos_detalle.cant_fundados > 0 GROUP BY reclamos_detalle.id_provee";
    private final static String SQL_PROVEEDOR_FUNDADOS = "SELECT productos.codigo_producto, reclamos_detalle.id_producto, SUM(reclamos_detalle.cant_fundados) fundados, reclamos_detalle.id_provee, proveedores.prov_nombre FROM reclamos_encabezado JOIN reclamos_detalle ON reclamos_detalle.id_reclamos_detalle_item = reclamos_encabezado.id_reclamos_encabezado JOIN productos ON reclamos_detalle.id_producto = productos.id_producto JOIN proveedores ON reclamos_detalle.id_provee = proveedores.prov_id WHERE fecha_recepcion >= ? AND fecha_recepcion <= ? AND reclamos_detalle.id_provee = ? AND reclamos_detalle.cant_fundados > 0 GROUP BY reclamos_detalle.id_producto";

    public ArrayList<ReclamoBusqueda> obtenerBusqueda(String idProduct, String desde, String hasta, String IdSession, String tipo) throws ClassNotFoundException, IOException, SQLException {
        Connection conReclamosNum = null;
        PreparedStatement ptsmtReclmosNum = null;
        ResultSet rsReclamosNum = null;
        ArrayList<ReclamoBusqueda> productosList = new ArrayList();
        String SQL_QUERY_FUNDADOS = "";
        if (tipo.equals("producto")) {
            SQL_QUERY_FUNDADOS = SQL_PRODUCTO_FUNDADOS;
        }else if (tipo.equals("proveedor")){
            SQL_QUERY_FUNDADOS = SQL_PROVEEDOR_FUNDADOS;
            
        }

        conReclamosNum = DB.getInstance().getConnection();
        ptsmtReclmosNum = conReclamosNum.prepareStatement(SQL_QUERY_FUNDADOS);        
        ptsmtReclmosNum.setString(1, desde);
        ptsmtReclmosNum.setString(2, hasta);
        ptsmtReclmosNum.setString(3, idProduct);
        rsReclamosNum = ptsmtReclmosNum.executeQuery();

        while (rsReclamosNum.next()) {
            ReclamoBusqueda producto = new ReclamoBusqueda();
            producto.setFundados(rsReclamosNum.getString("fundados"));
            producto.setProductoId(rsReclamosNum.getString("id_producto"));
            producto.setProductoNmbre(rsReclamosNum.getString("codigo_producto"));
            producto.setProveedorId(rsReclamosNum.getString("id_provee"));
            producto.setProveedorNombre(rsReclamosNum.getString("prov_nombre"));

            productosList.add(producto);
        }
        System.out.println("Listao de FUNDADOS: " + productosList);
        return productosList;
    }

}
