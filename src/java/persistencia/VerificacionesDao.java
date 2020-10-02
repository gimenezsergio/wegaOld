package persistencia;


import entidades.DB;
import entidades.Verificaciones;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.Verificacion;

public class VerificacionesDao {
     private VerificacionesDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static VerificacionesDao INSTANCE = null;

    public static VerificacionesDao getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new VerificacionesDao();
        }
        return INSTANCE;
    }
    private final static String SQL_PERSONAS_SELECT = "SELECT * FROM verificaciones WHERE disponible=1;";

    public ArrayList<Verificaciones> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<Verificaciones> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {
            
                c = DB.getInstance().getConnection();
             ptsmt = c.prepareStatement(SQL_PERSONAS_SELECT);
            rs = ptsmt.executeQuery();
            Verificaciones a = null;
            while (rs.next()) {
                try {
                    a = new Verificaciones();
                    a.setId(rs.getString("id"));
                    a.setTipo(rs.getString("tipo"));
                    
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

