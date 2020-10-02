package persistencia;

import entidades.Disenos;
import entidades.DB;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DisenoDao {

        private final static String SQL_DISENO_INSERT = "INSERT INTO diseno (dis_nombre ) VALUES(?)";

    public static void Insertar(Disenos a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_DISENO_INSERT);
        psmt.setString(1, a.getDis_nombre());
        
        psmt.execute();
    }
    private final static String SQL_DISENO_UPDATE = "UPDATE diseno "
            + " set dis_nombre = ? "
            + " WHERE dis_id = ?;";

    public static void actualizar(Disenos a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_DISENO_UPDATE);
        psmt.setString(1, a.getDis_nombre());
        psmt.setString(2, a.getDis_id());

        psmt.execute();
        System.out.println("PUT DAO" + a.getDis_nombre());
        System.out.println("PUT DAO" + a.getDis_id());
    }

    private final static String SQL_DISENO_DELETE = "UPDATE diseno SET disponible = 0"
            + " WHERE dis_id = ?;";

    public static void borrar(Disenos a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement ptsmt = null;
        //  try {
        c = DB.getInstance().getConnection();
        ptsmt = c.prepareStatement(SQL_DISENO_DELETE);
        ptsmt.setInt(1, Integer.parseInt(a.getDis_id()));
        ptsmt.execute();

    }

    private DisenoDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static DisenoDao INSTANCE = null;

    public static DisenoDao getInstance() throws ClassNotFoundException, IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new DisenoDao();
        }
        return INSTANCE;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private final static String SQL_DISENO_SELECT = "SELECT * FROM diseno WHERE disponible=1 ORDER BY dis_nombre ASC;";

    public ArrayList<Disenos> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<Disenos> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_DISENO_SELECT);
            rs = ptsmt.executeQuery();
            Disenos a = null;
            while (rs.next()) {
                try {
                    a = new Disenos();
                    a.setDis_nombre(rs.getString("dis_nombre"));
                    a.setDis_id(rs.getString("dis_id"));

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
