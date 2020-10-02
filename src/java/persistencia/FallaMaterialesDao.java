package persistencia;

import entidades.DB;
import entidades.Procesos;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.FallaMateriales;

public class FallaMaterialesDao {

    private final static String SQL_FALLA_MAT_UPDATE = "UPDATE falla_materiales "
            + " set falla_mat_nombre = ? "
            + " WHERE falla_mat_id = ?;";

    public static void actualizar(FallaMateriales a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_FALLA_MAT_UPDATE);
        psmt.setString(1, a.getFalla_mat_nombre());
        psmt.setString(2, a.getFalla_mat_id());

        psmt.execute();
        System.out.println("PUT DAO" + a.getFalla_mat_nombre());
        System.out.println("PUT DAO" + a.getFalla_mat_id());
    }

    private final static String SQL_FALLA_MAT_DELETE = "UPDATE falla_materiales SET disponible = 0"
            + " WHERE falla_mat_id = ?;";

    public static void borrar(FallaMateriales a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement ptsmt = null;
        //  try {
        c = DB.getInstance().getConnection();
        ptsmt = c.prepareStatement(SQL_FALLA_MAT_DELETE);
        ptsmt.setInt(1, Integer.parseInt(a.getFalla_mat_id()));
        ptsmt.execute();
    }

    private final static String SQL_FALLA_MAT_INSERT = "INSERT INTO falla_materiales (falla_mat_nombre) VALUES(?)";
    public static void Insertar(FallaMateriales a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_FALLA_MAT_INSERT);
        psmt.setString(1, a.getFalla_mat_nombre());

        psmt.execute();
    }

    private FallaMaterialesDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static FallaMaterialesDao INSTANCE = null;

    public static FallaMaterialesDao getInstance() throws ClassNotFoundException, IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new FallaMaterialesDao();
        }
        return INSTANCE;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private final static String SQL_FALLA_MATER_SELECT = "SELECT * FROM falla_materiales WHERE disponible=1 ORDER BY falla_mat_nombre ASC;";

    public ArrayList<FallaMateriales> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<FallaMateriales> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_FALLA_MATER_SELECT);
            rs = ptsmt.executeQuery();
            FallaMateriales a = null;
            while (rs.next()) {
                try {
                    a = new FallaMateriales();
                    a.setFalla_mat_id(rs.getString("falla_mat_id"));
                    a.setFalla_mat_nombre(rs.getString("falla_mat_nombre"));
                    //   a.setFalla_mat_nombre(rs.getString("falla_mat_nombre "));
                    //   a.setFalla_mat_id(rs.getString("falla_mat_id"));

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
