package persistencia;


import entidades.DB;
import entidades.Clientes;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.Cliente;

public class ClienteDao {

    
    private final static String SQL_CLIENTE_DELETE = "UPDATE clientes SET disponible = 0"
            + " WHERE id_cliente = ?;";
    public static void borrar(Clientes a) throws ClassNotFoundException, IOException, SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    Connection c = null;
        PreparedStatement ptsmt = null;
      //  try {
            c = DB.getInstance().getConnection();
            ptsmt = c.prepareStatement(SQL_CLIENTE_DELETE);
            ptsmt.setInt(1, Integer.parseInt(a.getId_cliente()));
            ptsmt.execute();
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Error: " + ex);
//        } catch (IOException ex) {
//            System.out.println("Error: " + ex);
//        } catch (SQLException ex) {
//            System.out.println("Error: " + ex);
//        }
        
    }
    
    private final static String SQL_CLIENTE_INSERT = "INSERT INTO clientes (nombre) VALUES(?)";

    public static void Insertar(Clientes a) throws ClassNotFoundException, IOException, SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_CLIENTE_INSERT);
        psmt.setString(1, a.getNombre());
        
        psmt.execute();
    }
     private ClienteDao() throws ClassNotFoundException,
            IOException, SQLException {
    }
    private static ClienteDao INSTANCE = null;

    public static ClienteDao getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new ClienteDao();
        }
        return INSTANCE;
    }
    private final static String SQL_PERSONAS_SELECT = "SELECT * FROM clientes WHERE disponible=1 ORDER BY nombre ASC;";

    public ArrayList<Clientes> obtener() throws ClassNotFoundException,
            IOException, SQLException {
        ArrayList<Clientes> lista = new ArrayList();
        Connection c = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
         try {
                c = DB.getInstance().getConnection();
               ptsmt = c.prepareStatement(SQL_PERSONAS_SELECT);
                rs = ptsmt.executeQuery();
                Clientes a = null;
            while (rs.next()) {
                try {
                    a = new Clientes();
                    a.setId_cliente(rs.getString("id_cliente"));
                    a.setNombre(rs.getString("nombre"));
                    
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
    
    private final static String SQL_PERSONAS_UPDATE = "UPDATE clientes "
            + " set nombre = ? "
            + " WHERE id_cliente = ?;";
    
    public static void actualizar( Clientes a) throws ClassNotFoundException, IOException, SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        c = DB.getInstance().getConnection();
        psmt = c.prepareStatement(SQL_PERSONAS_UPDATE);
        psmt.setString(1, a.getNombre());
        psmt.setString(2, a.getId_cliente());
        
        psmt.execute();
        System.out.println("PUT DAO" + a.getNombre());
        System.out.println("PUT DAO" + a.getId_cliente());
        
    }
}


  


