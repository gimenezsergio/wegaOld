package persistencia;



import entidades.DB;
import entidades.Origenes;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.Origen;

public class OrigenDao {
     private OrigenDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static OrigenDao INSTANCE = null;

    public static OrigenDao getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new OrigenDao();
        }
        return INSTANCE;
    }
    private final static String SQL_PERSONAS_SELECT = "SELECT * FROM origenes WHERE disponible=1;";

    public ArrayList<Origenes> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<Origenes> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {
           
                c = DB.getInstance().getConnection();
            
            ptsmt = c.prepareStatement(SQL_PERSONAS_SELECT);
            rs = ptsmt.executeQuery();
            Origenes a = null;
            while (rs.next()) {
                try {
                    a = new Origenes();
                    a.setId_origen(rs.getString("id_origen"));
                    a.setOrigen(rs.getString("origen"));
                    
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

