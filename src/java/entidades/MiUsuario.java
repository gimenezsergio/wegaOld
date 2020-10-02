package entidades;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

public class MiUsuario {

    public static int idUsuario;
    public static String idSession;
    public static TreeMap usuarios = new TreeMap();


    public static int getUsuario(String pidSession) throws ClassNotFoundException, IOException, SQLException {
        //System.out.println("Lista usuarios: " + usuarios);
        //System.out.println("Usuario actual: " + usuarios.get(pidSession));
        int resultado = (int) usuarios.get(pidSession);
        return resultado;
    }
    
    public static void SetUsuario(int pIdUsuario, String pIdSession){
        usuarios.put(pIdSession,pIdUsuario);
        System.out.println("Set Usuario: " + " pIdSession: " + pIdSession + " pIdSession " + pIdSession);
        //setIdUsuario(idUsuario); 
    }
    


    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int aIdUsuario) {
        idUsuario = aIdUsuario;
    }

    private String getIdSession() {
        return idSession;
    }

    private void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    private static TreeMap getUsuarios() {
        return usuarios;
    }

    private static void setUsuarios(TreeMap aUsuarios) {
        usuarios = aUsuarios;
    }

    

   
}
