package persistencia;


import entidades.DB;
import entidades.Resultados;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.Resultado;

public class ResultadoDao {
     private ResultadoDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static ResultadoDao INSTANCE = null;

    public static ResultadoDao getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new ResultadoDao();
        }
        return INSTANCE;
    }
    private final static String SQL_PERSONAS_SELECT = "SELECT * FROM resultados WHERE disponible=1;";

    public ArrayList<Resultados> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<Resultados> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {
          
                c = DB.getInstance().getConnection();
           ptsmt = c.prepareStatement(SQL_PERSONAS_SELECT);
            rs = ptsmt.executeQuery();
            Resultados a = null;
            while (rs.next()) {
                try {
                    a = new Resultados();
                    a.setId(rs.getString("id_resultado"));
                    a.setResultado(rs.getString("resultado"));
                    
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

