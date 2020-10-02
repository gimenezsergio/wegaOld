package test;

import entidades.ReclamoDetalle;
import entidades.Reclamos;
import entidades.ReclamosEncabezado;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReclamosTest {

    public static void main(String[] args) {
        System.out.println(" [...] test.ReclamosTest.main()");
        Reclamos miReclamo = new Reclamos();
        
        ReclamosEncabezado miEncabezado = new ReclamosEncabezado();
        miEncabezado.setId_reclamo("1");

        
        ReclamoDetalle miDetalleProductos = new ReclamoDetalle();
        miDetalleProductos.setId_reclamo("1");
        try {
            miDetalleProductos.setCodigo_producto("222", 0);
        } catch (Exception ex) {
            System.out.println("Error de producto");
        }

        
        ReclamoDetalle miDetalleProductos2 = new ReclamoDetalle();
        miDetalleProductos2.setId_reclamo("2");
        try {
            miDetalleProductos2.setCodigo_producto("111", 0);
        } catch (Exception ex) {
            System.out.println("Error de producto");
        }

        System.out.println("AGREGAR ENCABEZADO");
        miReclamo.setEncabezado(miEncabezado);
        System.out.println("AGREGAR DETALLE 1");
        miReclamo.setDetalle(miDetalleProductos);
        System.out.println("AGREGAR DETALLE 2");
        miReclamo.setDetalle(miDetalleProductos2);
        
        
        System.out.println("RECLAMO PRINT");
        System.out.println(miReclamo);

        System.out.println(" [OK] test.ReclamosTest.main()");
    }
}
