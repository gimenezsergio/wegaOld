package ingresos.test;

import ingresos.entidades.Precargas;
import ingresos.entidades.PrecargasDetalle;
import ingresos.entidades.PrecargasMaestro;
import java.util.ArrayList;

public class PrecargasTest {

    public static void main(String[] args) {
        Precargas miPrecarga = new Precargas();
        ArrayList<PrecargasDetalle> misDetalles = new ArrayList();
        PrecargasDetalle detalle1;
        PrecargasDetalle detalle2;
        System.out.println("        [...] ingresos.test.PrecargasTest.main()");
        try {
            System.out.println("[...] Carga maestro.");
            PrecargasMaestro miPrecargaMaestro = new PrecargasMaestro("AD", "13/03/2018", "Ay 5956/3", "14/04/2018", "Sin observaciones", "arribo");
            miPrecarga.setMaestro(miPrecargaMaestro);
            System.out.println("[OK] Carga maestro. " + miPrecargaMaestro);
        } catch (Exception ex) {
            System.out.println("[ERROR] Carga maestro." + ex.getMessage());
        }

        System.out.println("-----------------------------");

        try {
            System.out.println("[...] Carga detalle 1.");
            detalle1 = new PrecargasDetalle("FAP-342", "33", "54", "Observaciones particulares 1");
            System.out.println("Detalle 1: " + detalle1);
            misDetalles.add(detalle1);
            System.out.println("[OK] Carga detalle 1.");
        } catch (Exception ex) {
            System.out.println("[ERROR] Carga detalle 1. " + ex.getMessage());
        }
        
        System.out.println("-----------------------------");

        try {
            System.out.println("[...] Carga detalle 2.");
            detalle2 = new PrecargasDetalle("KX 34", "22", "4", "Observaciones particulares 2");
            System.out.println("Detalle 2: " + detalle2);
            misDetalles.add(detalle2);
            System.out.println("[OK] Carga detalle 2.");
        } catch (Exception ex) {
            System.out.println("[ERROR] Carga detalle 2. " + ex.getMessage());
        }
        
        System.out.println("-----------------------------");
        System.out.println("Listado de detalles. ");
        System.out.println(misDetalles);
        System.out.println("-----------------------------");
        miPrecarga.setDetalles(misDetalles);
        System.out.println(miPrecarga);
        
        

        System.out.println("        [OK] ingresos.test.PrecargasTest.main()");
    }
}
