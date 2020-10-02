package ingresos.persistencia;

import entidades.DB;
import entidades.Productos;
import ingresos.entidades.Arribos;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import persistencia.ProductoDao;

public class ArribosDAO {

    private ArribosDAO() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static ArribosDAO INSTANCE = null;

    public static ArribosDAO getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new ArribosDAO();
        }
        return INSTANCE;
    }
    private final static String SQL_PERSONAS_SELECT = "SELECT * FROM precarga_arribo WHERE disponible=1 ORDER BY arribo_nombre ASC;";

    public ArrayList<Arribos> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<Arribos> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {

            c = DB.getInstance().getConnection();

            ptsmt = c.prepareStatement(SQL_PERSONAS_SELECT);
            rs = ptsmt.executeQuery();
            Arribos a = null;
            while (rs.next()) {
                try {
                    a = new Arribos(rs.getString("arribo_nombre"), rs.getString("arribo_id"), rs.getString("disponible"));
                    
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
}
