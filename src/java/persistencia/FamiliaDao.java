package persistencia;

import entidades.DB;
import entidades.Familias;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import presentacion.Familia;

public class FamiliaDao {

    private FamiliaDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static FamiliaDao INSTANCE = null;

    public static FamiliaDao getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new FamiliaDao();
        }
        return INSTANCE;
    }

    private final static String SQL_PRODUCTO_FAMILIA_UPDATE = "UPDATE family_Product SET fami_nombre = ?"
            + " WHERE fami_id = ?;";

    public static void actualizar(String nombre, String id) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement ptsmt = null;
        c = DB.getInstance().getConnection();
        ptsmt = c.prepareStatement(SQL_PRODUCTO_FAMILIA_UPDATE);
        ptsmt.setString(1, nombre);
        ptsmt.setInt(2, Integer.parseInt(id));
        ptsmt.execute();

    }

    private final static String SQL_PRODUCTO_FAMILIA_DELETE = "UPDATE family_Product SET disponible = 0"
            + " WHERE fami_id = ?;";

    public static void borrar(String id) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement ptsmt = null;
        c = DB.getInstance().getConnection();
        ptsmt = c.prepareStatement(SQL_PRODUCTO_FAMILIA_DELETE);
        ptsmt.setInt(1, Integer.parseInt(id));
        ptsmt.execute();

    }

    private final static String SQL_PRODUCTO_FAMILIA_INSERT = "INSERT INTO family_Product (fami_nombre) VALUES(?)";

    public static void insertar(String parametroFamilia) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_PRODUCTO_FAMILIA_INSERT);
        System.out.println("Nombre producto: " + parametroFamilia);
        psmt.setString(1, parametroFamilia);

        psmt.execute();
    }

    private final static String SQL_FAMILIA_SELECT = "SELECT * FROM family_Product WHERE disponible=1 ORDER BY fami_id ASC;";

    public ArrayList<Familias> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<Familias> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_FAMILIA_SELECT);
            rs = ptsmt.executeQuery();
            Familias a = null;
            while (rs.next()) {
                try {
                    a = new Familias();
                    a.setId(rs.getString("fami_id"));
                    a.setNombre(rs.getString("fami_nombre"));

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
