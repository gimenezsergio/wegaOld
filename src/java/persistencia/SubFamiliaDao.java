package persistencia;

import entidades.DB;
import entidades.SubFamilias;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SubFamiliaDao {
     private SubFamiliaDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static SubFamiliaDao INSTANCE = null;

    public static SubFamiliaDao getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new SubFamiliaDao();
        }
        return INSTANCE;
    }
    
    private final static String SQL_SUBFAMILIA_SELECT = "SELECT * FROM sub_family_Product WHERE disponible=1 AND family_id = 7 ORDER BY subfami_id ASC;";

    public ArrayList<SubFamilias> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<SubFamilias> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_SUBFAMILIA_SELECT);
            rs = ptsmt.executeQuery();
            SubFamilias a = null;
            while (rs.next()) {
                try {
                    a = new SubFamilias();                    
                    a.setId(rs.getString("subfami_id"));
                    a.setNombre(rs.getString("subfami_nombre"));

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
    
    
    
    private final static String SQL_SUBFAMILIA_SELECT_BY_FAMILIA = "SELECT * FROM sub_family_Product WHERE disponible=1 AND family_id = ? ORDER BY subfami_id ASC;";

    public ArrayList<SubFamilias> obtenerByFamilia(String idFamilia) throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<SubFamilias> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_SUBFAMILIA_SELECT_BY_FAMILIA);
            ptsmt.setString(1, idFamilia);
            rs = ptsmt.executeQuery();
            SubFamilias a = null;
            while (rs.next()) {
                try {
                    a = new SubFamilias();                    
                    a.setId(rs.getString("subfami_id"));
                    a.setNombre(rs.getString("subfami_nombre"));

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
