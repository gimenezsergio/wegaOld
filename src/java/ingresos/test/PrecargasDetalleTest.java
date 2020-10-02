package ingresos.test;

import ingresos.entidades.PrecargasDetalle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrecargasDetalleTest {
    public static void main(String[] args) {
        System.out.println("        [...]ingresos.test.PrecargasDetalleTest.main()");
        
        System.out.println("-----------------------------");
        
        PrecargasDetalle detalle1;
        try {
            System.out.println("[...] Producto NO puede estar vacio.");
            detalle1 = new PrecargasDetalle("", "33", "54", "Observaciones particulares");
            System.out.println("Detalle: " + detalle1);
            System.out.println("[ERROR] Producto NO puede estar vacio.");
        } catch (Exception ex) {
            System.out.println("[OK] Producto NO puede estar vacio. " + ex.getMessage() );
        }
        
        System.out.println("-----------------------------");
        
        try {
            System.out.println("[...] Cantidad NO puede ser menor a 1.");
            detalle1 = new PrecargasDetalle("FAP-2016", "0", "54", "Observaciones particulares");
            System.out.println("Detalle: " + detalle1);
            System.out.println("[ERROR] Cantidad NO puede ser menor a 1.");
        } catch (Exception ex) {
            System.out.println("[OK] Cantidad NO puede ser menor a 1. " + ex.getMessage() );
        }
        System.out.println("-----------------------------");
        
        try {
            System.out.println("[...] Muestras NO puede ser menor a 1.");
            detalle1 = new PrecargasDetalle("FAP-2016", "1", "0", "Observaciones particulares");
            System.out.println("Detalle: " + detalle1);
            System.out.println("[ERROR] Muestras NO puede ser menor a 1.");
        } catch (Exception ex) {
            System.out.println("[OK] Muestras NO puede ser menor a 1. " + ex.getMessage() );
        }
        
        
        System.out.println("-----------------------------");        
        System.out.println("        [OK] ingresos.test.PrecargasDetalleTest.main()");
    }
   
}
