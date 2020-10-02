package test;

import entidades.Login;
import entidades.LoginExeption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestLogin {

    public static void main(String[] args) {
        System.out.println("TestLogin: [...]");

        //Login correcto
        try {
            System.out.println("Login correcto:  [...]");
            Login miLogin = new Login("Jorge", "wega2015", "session");
            System.out.println("Login correcto:  [OK]");
        } catch (LoginExeption ex) {
            System.out.println("Login correcto:  [ERROR] " + ex.getMessage());
        }
        
        //Login incorrecto
        try {
            System.out.println("Login incorrecto:  [...]");
            Login miLogin = new Login("jony@gil", "12dfs3", "session");
            System.out.println("Login incorrecto:  [ERROR]");
        } catch (LoginExeption ex) {
            System.out.println("Login incorrecto:  [OK] " + ex.getMessage());
        }

        //Usuario vacio
        try {
            System.out.println("usuario vacio : [...]");
            Login miLogin2 = new Login("", "123", "session");
            System.out.println("usuario vacio : [ERROR]");
        } catch (LoginExeption ex) {
            System.out.println("usuario vacio : [OK] " + ex.getMessage());
        }
        
        try {
            //Clave vacia
            System.out.println("Clave vacia: [...]");
            Login miLogin = new Login("user", "", "session");
            System.out.println("Clave vacia: [ERROR]");
        } catch (LoginExeption ex) {
            System.out.println("Clave vacia: [OK] " +ex.getMessage() );
        }

        System.out.println("TestLogin: [OK]");
    }
}
