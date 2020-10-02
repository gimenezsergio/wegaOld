package persistencia;

import entidades.DB;
import entidades.Proveedores;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProveedoresDao {

    private final static String SQL_PROVEEDORES_UPDATE = "UPDATE proveedores "
            + " set prov_nombre = ? "
            + " WHERE prov_id = ?;";

    public static void actualizar(Proveedores a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_PROVEEDORES_UPDATE);
        psmt.setString(1, a.getProv_nombre());
        psmt.setString(2, a.getProv_id());

        psmt.execute();
        System.out.println("PUT DAO" + a.getProv_nombre());
        System.out.println("PUT DAO" + a.getProv_id());
    }

    private final static String SQL_PROVEEDOR_DELETE = "UPDATE proveedores SET disponible = 0"
            + " WHERE prov_id = ?;";

    public static void borrar(Proveedores a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement ptsmt = null;
        c = DB.getInstance().getConnection();
        ptsmt = c.prepareStatement(SQL_PROVEEDOR_DELETE);
        ptsmt.setInt(1, Integer.parseInt(a.getProv_id()));
        ptsmt.execute();

    }
    
    private final static String SQL_PROVEEDORES_INSERT = "INSERT INTO proveedores (prov_nombre) VALUES(?)";

    public static void Insertar(Proveedores a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_PROVEEDORES_INSERT);
        psmt.setString(1, a.getProv_nombre());
        
        psmt.execute();
    }

    private ProveedoresDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static ProveedoresDao INSTANCE = null;

    public static ProveedoresDao getInstance() throws ClassNotFoundException, IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new ProveedoresDao();
        }
        return INSTANCE;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private final static String SQL_PROVEEDOR_SELECT = "SELECT * FROM proveedores WHERE disponible=1 ORDER BY prov_nombre ASC;";

    public ArrayList<Proveedores> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<Proveedores> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_PROVEEDOR_SELECT);
            rs = ptsmt.executeQuery();
            Proveedores a = null;
            while (rs.next()) {
                try {
                    a = new Proveedores();
                    a.setProv_id(rs.getString("prov_id"));
                    a.setProv_nombre(rs.getString("prov_nombre"));
                    //a.setFalla_mat_id(rs.getString("falla_mat_id"));
                    //a.setFalla_mat_nombre(rs.getString("falla_mat_nombre"));

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
