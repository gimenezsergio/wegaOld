package ingresos.test;

import ingresos.entidades.PrecargasMaestro;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrecargasMaestroTest {
    public static void main(String[] args) {
        System.out.println("        [...] ingresos.PrecargasTest.main()");
        System.out.println("-----------------------------");        
        try {
            System.out.println("[...] Provedor NO pueden estar vacio.");
            PrecargasMaestro miPrecarga = new PrecargasMaestro("", "13/03/2018", "Ay 5956/3", "14/04/2018", "Sin observaciones", "arribo");
            System.out.println("[ERROR] Provedor NO pueden estar vacio. " + miPrecarga);
        } catch (Exception ex) {
            System.out.println("[OK] Provedor NO pueden estar vacio." + ex.getMessage());
        }
        
        System.out.println("-----------------------------");        
        try {
            System.out.println("[...] Fecha manifiesto NO pueden estar vacio.");
            PrecargasMaestro miPrecarga = new PrecargasMaestro("AD", "", "Ay 5956/3", "14/04/2018", "Sin observaciones", "arribo");
            System.out.println("[ERROR] Fecha manifiesto NO pueden estar vacio. " + miPrecarga);
        } catch (Exception ex) {
            System.out.println("[OK] Fecha manifiesto NO pueden estar vacio." + ex.getMessage());
        }
        
        System.out.println("-----------------------------");        
        try {
            System.out.println("[...] Codigo manifiesto NO pueden estar vacio.");
            PrecargasMaestro miPrecarga = new PrecargasMaestro("AD", "13/03/2018", "", "14/04/2018", "Sin observaciones", "arribo");
            System.out.println("[ERROR] Codigo manifiesto NO pueden estar vacio. " + miPrecarga);
        } catch (Exception ex) {
            System.out.println("[OK] Codigo manifiesto NO pueden estar vacio." + ex.getMessage());
        }
        
        System.out.println("-----------------------------");        
        try {
            System.out.println("[...] Fecha recepcion NO pueden estar vacio.");
            PrecargasMaestro miPrecarga = new PrecargasMaestro("AD", "13/03/2018", "Ay 5956/3", "", "Sin observaciones", "arribo");
            System.out.println("[ERROR] Fecha recepcion NO pueden estar vacio. " + miPrecarga);
        } catch (Exception ex) {
            System.out.println("[OK] Fecha recepcion NO pueden estar vacio." + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Todos los atributos completos");
            PrecargasMaestro miPrecarga2 = new PrecargasMaestro("AD", "13/03/2018", "Ay 5956/3", "14/04/2018", "Sin observaciones", "arribo");
            System.out.println("[OK] Todos los atributos completos" + miPrecarga2);
        } catch (Exception ex) {
            System.out.println("[ERROR] Todos los atributos completos" + ex.getMessage());
        }
        System.out.println("-----------------------------");        
        System.out.println("        [OK] ingresos.PrecargasTest.main()");
    }
    
}
