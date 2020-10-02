/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.DB;
import entidades.Procesos;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class ProcesoDao {

    private final static String SQL_PROCESO_UPDATE = "UPDATE proceso "
            + " set proc_nombre = ? "
            + " WHERE proc_id = ?;";

    public static void actualizar(Procesos a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_PROCESO_UPDATE);
        psmt.setString(1, a.getProc_nombre());
        psmt.setString(2, a.getProc_id());

        psmt.execute();
        System.out.println("PUT DAO" + a.getProc_nombre());
        System.out.println("PUT DAO" + a.getProc_id());
    }

    private final static String SQL_PROCESO_DELETE = "UPDATE proceso SET disponible = 0"
            + " WHERE proc_id = ?;";

    public static void borrar(Procesos a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement ptsmt = null;

        c = DB.getInstance().getConnection();
        ptsmt = c.prepareStatement(SQL_PROCESO_DELETE);
        ptsmt.setInt(1, Integer.parseInt(a.getProc_id()));
        ptsmt.execute();
    }
    
    private final static String SQL_PROCESO_INSERT = "INSERT INTO proceso (proc_nombre) VALUES(?)";

    public static void Insertar(Procesos a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_PROCESO_INSERT);
        psmt.setString(1, a.getProc_nombre());

        psmt.execute();
    }

    private ProcesoDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static ProcesoDao INSTANCE = null;

    public static ProcesoDao getInstance() throws ClassNotFoundException, IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new ProcesoDao();
        }
        return INSTANCE;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private final static String SQL_PROCESO_SELECT = "SELECT * FROM proceso WHERE disponible=1 ORDER BY proc_nombre ASC;";

    public ArrayList<Procesos> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<Procesos> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_PROCESO_SELECT);
            rs = ptsmt.executeQuery();
            Procesos a = null;
            while (rs.next()) {
                try {
                    a = new Procesos();
                    a.setProc_nombre(rs.getString("proc_nombre"));
                    a.setProc_id(rs.getString("proc_id"));

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
