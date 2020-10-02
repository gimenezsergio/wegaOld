package persistencia;

import entidades.DB;
import entidades.ReclamosOld;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReclamoAllDao {

    private ReclamoAllDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static ReclamoAllDao INSTANCE = null;

    public static ReclamoAllDao getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new ReclamoAllDao();
        }
        return INSTANCE;
    }

    private final static String SQL_RECLAMOS_SELECT = "SELECT * FROM reclamos_encabezado;";

    public ArrayList<ReclamosOld> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<ReclamosOld> listaReclamos = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;

        c = DB.getInstance().getConnection();
        ptsmt = c.prepareStatement(SQL_RECLAMOS_SELECT);
        rs = ptsmt.executeQuery();
        ReclamosOld a = null;
        while (rs.next()) {
                try {
                    a = new ReclamosOld();
                    a.setId_reclamo(rs.getString("id_reclamos_encabezado"));
                    a.setFecha_emision(rs.getString("fecha_emision"));
                    a.setFecha_recepcion(rs.getString("fecha_recepcion"));
                    a.setCliente(rs.getString("id_cliente"));
                   

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                listaReclamos.add(a);
            }
        
        return listaReclamos;
    }

}
