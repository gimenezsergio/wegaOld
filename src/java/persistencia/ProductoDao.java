package persistencia;

import entidades.DB;
import entidades.Productos;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.Producto;

public class ProductoDao {

   

    

    private ProductoDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static ProductoDao INSTANCE = null;

    public static ProductoDao getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new ProductoDao();
        }
        return INSTANCE;
    }
    private final static String SQL_PERSONAS_SELECT = "SELECT * FROM productos WHERE disponible=1 ORDER BY codigo_producto ASC;";

    public ArrayList<Productos> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<Productos> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_PERSONAS_SELECT);
            rs = ptsmt.executeQuery();
            Productos a = null;
            while (rs.next()) {
                try {
                    a = new Productos(rs.getString("codigo_producto"),rs.getString("family_id"), rs.getString("subfamily_id") );
                    a.setId_producto(rs.getString("id_producto"));
//                    a.setCodigo_producto(rs.getString("codigo_producto"));
//                    a.setFamilia(rs.getString("family_id"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                lista.add(a);
            }
        } finally {
            try {
                rs.close();

            } finally {
                try {

                    ptsmt.close();
                } finally {
                    c.close();

                }
            }
        }
        return lista;
    }

    private final static String SQL_PRODUCTO_INSERT = "INSERT INTO productos (codigo_producto, family_id, subfamily_id) VALUES(?,?,?)";

    public static void insertar(Productos parametroProducto) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_PRODUCTO_INSERT);
        System.out.println("Nombre producto: " + parametroProducto.getCodigo_producto());
        psmt.setString(1, parametroProducto.getCodigo_producto());
        psmt.setString(2, parametroProducto.getFamilia());
        psmt.setString(3, parametroProducto.getSubFamilia());

        psmt.execute();
    }
    
    private final static String SQL_PRODUCTO_DELETE = "UPDATE productos SET disponible = 0"
            + " WHERE id_producto = ?;";
    
    public static void borrar(Productos ProductoParametro) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection c = null;
        PreparedStatement ptsmt = null;
        try {
            c = DB.getInstance().getConnection();
            ptsmt = c.prepareStatement(SQL_PRODUCTO_DELETE);
            ptsmt.setInt(1, Integer.parseInt(ProductoParametro.getId_producto()));
            ptsmt.execute();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }
    
    private final static String SQL_PRODUCTO_UPDATE = "UPDATE productos "
            + " set codigo_producto = ? "
            + " WHERE id_producto = ?;";
    
    public static void actualizar( Productos a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_PRODUCTO_UPDATE);
        psmt.setString(1, a.getCodigo_producto());
        psmt.setString(2, a.getId_producto());
        
        psmt.execute();
        System.out.println("PUT DAO" + a.getCodigo_producto());
        System.out.println("PUT DAO" + a.getId_producto());
        
    }
    
    private final static String SQL_PRODUCTO_UPDATE_BY_FAMILIA = "UPDATE productos "
            + " set family_id = ?, subfamily_id = ? "
            + " WHERE id_producto = ?;";
    
     public static void actualizarFamilia(Productos a) throws ClassNotFoundException, IOException, SQLException {
         Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_PRODUCTO_UPDATE_BY_FAMILIA);
        psmt.setString(1, a.getFamilia());
        psmt.setString(2, a.getSubFamilia());
        psmt.setString(3, a.getCodigo_producto());
        
        
        psmt.execute();
        System.out.println("PUT DAO familia" + a.getFamilia());
        System.out.println("PUT DAO producto" + a.getCodigo_producto());
        System.out.println("PUT DAO producto" + a.getSubFamilia());
    }
    
     private final static String SQL_PRODUCTO_BY_FAMILIA = "SELECT id_producto, codigo_producto, family_Product.fami_nombre, sub_family_Product.subfami_nombre FROM productos JOIN family_Product ON family_Product.fami_id = productos.family_id JOIN sub_family_Product ON sub_family_Product.subfami_id = productos.subfamily_id WHERE id_producto = ? ORDER BY codigo_producto ASC";

    public ArrayList<Productos> obtenerFamilia(String idProduct) throws ClassNotFoundException, IOException, SQLException {
        ArrayList<Productos> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_PRODUCTO_BY_FAMILIA);
            ptsmt.setString(1, idProduct);
            rs = ptsmt.executeQuery();
            Productos a = null;
            while (rs.next()) {
                try {
                    a = new Productos(rs.getString("codigo_producto"), rs.getString("fami_nombre"), rs.getString("subfami_nombre"));
                    a.setId_producto(rs.getString("id_producto"));
//                    a.setCodigo_producto(rs.getString("codigo_producto"));
//                    a.setFamilia(rs.getString("fami_nombre"));
                    

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                lista.add(a);
            }
        } finally {
            try {
                rs.close();

            } finally {
                try {

                    ptsmt.close();
                } finally {
                    c.close();

                }
            }
        }
        return lista;
    }            

}
