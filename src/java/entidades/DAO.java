
package entidades;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
    
    public Usuario loginDao(String puser, String ppass, String IdSession) 
            throws LoginExeption, ClassNotFoundException, IOException, SQLException {
        Connection laConexion = null;
        PreparedStatement consulta = null;
        ResultSet rs = null;
        try {
            laConexion = DB.getInstance().getConnection();
            consulta = laConexion.prepareStatement("SELECT * FROM usuarios WHERE user = ? AND pass = ?");
            consulta.setString(1, puser);
            consulta.setString(2, ppass);
            rs = consulta.executeQuery();
            
            Usuario userActual = new Usuario();
            if (rs.next()){
               // System.out.println("Conexion OK");
               //
               System.out.println("Id usuario login: " + rs.getString(1));
               //MiUsuario.setIdUsuario(rs.getInt(1));
               
               //para filter (viejo, ahrbia que reemplazarlo)
               MiUsuario.SetUsuario(rs.getInt(1), IdSession);
                    MiUsuario.getUsuario(IdSession);
                    userActual.setId(rs.getString("user_id"));
                    userActual.setNombre(rs.getString("user_nombre"));
                    userActual.setUser(rs.getString("user"));
                    userActual.setFirma("user_firma");
                System.out.println("Usuario " + userActual);
               return userActual;
               
              
            }else{
               // System.out.println("Conexion fail");
                throw new LoginExeption("Usuario no encontrado: ");
            }
            
            
            
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
            throw ex;
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
            throw ex;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            throw ex;
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (laConexion != null) {
                try {
                    laConexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
         
    }
    
    
}
