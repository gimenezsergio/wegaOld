
package entidades;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {
    private String user;
    private String pass;
    private int actualUser;
    private Usuario userNow;

    public Login(String puser, String ppass, String IdSession) throws LoginExeption {
        
        setUser(puser);
        setPass(ppass);
        DAO miLogin = new DAO();
        
        try {
            userNow= miLogin.loginDao(puser, ppass, IdSession);
            
            System.out.println("Nuevamente usuario:" + userNow);
            System.out.println("Usuario actual: " + MiUsuario.getIdUsuario());
        } catch (ClassNotFoundException ex) {
            throw new LoginExeption("Intentelo mas tarde ClassNotFoundException. <!-- "+ex.getMessage()+" -->");
        } catch (IOException ex) {
            throw new LoginExeption("Intentelo mas tarde IOException.<!-- "+ex.getMessage()+" -->");
        } catch (SQLException ex) {
            throw new LoginExeption("Intentelo mas tarde. SQLException<!-- "+ex.getMessage()+" -->");
        }
        
    }


    public String getUser() {
        return user;
    }


    public void setUser(String puser) throws LoginExeption {
        if ( puser.isEmpty()){
            throw  new LoginExeption("Usuario no puede estar vacio ");
        }else{
            this.user = puser;
        }
        
    }

    public String getPass() {
        return pass;
    }


    public void setPass(String ppass) throws LoginExeption {
        if ( ppass.isEmpty() ){
            throw new LoginExeption("Clave no puede estar vacia ");
        }else{
            this.pass = ppass;
        }
        
    }

   
    public int getActualUser() {
        return actualUser;
    }


    public void setActualUser(int actualUser) {
        this.actualUser = actualUser;
    }

    public Usuario getUserNow() {
        return userNow;
    }

    public void setUserNow(Usuario userNow) {
        this.userNow = userNow;
    }
    
}
