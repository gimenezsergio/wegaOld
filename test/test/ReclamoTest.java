package test;

import entidades.ReclamosOld;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReclamoTest {
    public static void main(String[] args) {
        System.out.println("      [...] ReclamoTest");
        ReclamosOld one = new ReclamosOld();
        System.out.println("   [...] Validate null or empty");
        try {
            System.out.println("[...] Fecha emision");
            one.setFecha_emision("2018-05-26");
            System.out.println("[OK] Fecha emision");
        } catch (Exception ex) {
            System.out.println("[ERROR] Fecha emision " + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad fundados");
           one.setCant_fundados("1",0);
            System.out.println("[OK] Cantidad fundados");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad fundados " + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad infundados");
           one.setCant_infundados("1",0);
            System.out.println("[OK] Cantidad infundados");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad infundados " + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad tema comercial");
            one.setCant_tema_comercial("1",0);
            System.out.println("[OK] Cantidad tema comercial");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad tema comercial " + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad total");
            one.setCantidad_total("1",0);
            System.out.println("[OK] Cantidad total");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad total " + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad Cliente");
            one.setCliente("Veronica SA");
            System.out.println("[OK] Cantidad Cliente");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad Cliente " + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad Producto");
            one.setCodigo_producto("1",0);
            System.out.println("[OK] Cantidad Producto");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad Producto " + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad de fecha");
            one.setFecha("1");
            System.out.println("[OK] Cantidad de fecha");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad de fecha" + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad de fecha recepcion");
            one.setFecha_recepcion("1");
            System.out.println("[OK] Cantidad de fecha recepcion");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad de fecha recepcion. " + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad de proveedor");
            one.setId_provee("1",0);
            System.out.println("[OK] Cantidad de proveedor");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad de proveedor. " + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad de reclamo");
            one.setId_reclamo("1");
            System.out.println("[OK] Cantidad de reclamo");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad de reclamo. " + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad de origen");
            one.setOrigen("1",0);
            System.out.println("[OK] Cantidad de origen");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad de origen. " + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad de resultado");
            one.setResultado("1",0);
            System.out.println("[OK] Cantidad de resultado");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad de resultado. " + ex.getMessage());
        }
        
        try {
            System.out.println("[...] Cantidad de verificacion");
            one.setVerificacion("1",0);
            System.out.println("[OK] Cantidad de verificacion");
        } catch (Exception ex) {
            System.out.println("[ERROR] Cantidad de verificacion. " + ex.getMessage());
        }
        System.out.println("   [OK] Validate null or empty");
        System.out.println("      [OK] ReclamoTest");
    }
}
