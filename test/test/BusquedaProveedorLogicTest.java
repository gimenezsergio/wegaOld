package test;

import ingresos.entidades.BusquedaProveedor;
import ingresos.logica.BusquedaProveedorLogic;
import java.util.ArrayList;

public class BusquedaProveedorLogicTest {

    public static void main(String[] args) {
        System.out.println("BusquedaProveedorLogicTest [...]");
        ArrayList<BusquedaProveedor> listado = new ArrayList();
        
        
        BusquedaProveedor miBusqueda1 = new BusquedaProveedor("uno", 4, 10);
        BusquedaProveedor miBusqueda2 = new BusquedaProveedor("dos", 5, 10);
        BusquedaProveedor miBusqueda3 = new BusquedaProveedor("tres", 10, 10);
        BusquedaProveedor miBusqueda4 = new BusquedaProveedor("cuatro", 50, 10);
        BusquedaProveedor miBusqueda5 = new BusquedaProveedor("cinco", 40, 10);
        BusquedaProveedor miBusqueda6 = new BusquedaProveedor("seis", 60, 10);

        listado.add(miBusqueda1);
        
        listado.add(miBusqueda2);
        listado.add(miBusqueda3);
        listado.add(miBusqueda4);
        listado.add(miBusqueda5);
        listado.add(miBusqueda6);
        
        
        if (listado.isEmpty()) {
            System.out.println("Array vacio");
        } else {
            System.out.println("Array con datos");
        }
        
        
        
        
        
        System.out.println("Base original");
        for (int i = 0; i < listado.size(); i++) {
            System.out.println(listado.get(i).getProductoNombre() + " | " + listado.get(i).getNoOk() + " | " + listado.get(i).getCantidad());            
        }
        
        int proveedorLogic = BusquedaProveedorLogic.comprarYAgregar(listado, miBusqueda3.getProductoNombre(), 100, "verificar");
        
        System.out.println("proveedorLogic: " + proveedorLogic);

        if (proveedorLogic == 1) {
            //sumar la cantidad de No Ok al prducto encontrado
            System.out.println("sumar la cantidad de No Ok al prducto encontrado");
            miBusqueda3.setNoOk(miBusqueda3.getNoOk() + 20);
        } else {
            //Agregar producto completo
            System.out.println("Agregar producto completo");
            listado.add(miBusqueda3);
        }
        
        System.out.println("Base modificada");
        for (int i = 0; i < listado.size(); i++) {
            System.out.println(listado.get(i).getProductoNombre() + " | " + listado.get(i).getNoOk() + " | " + listado.get(i).getCantidad());            
        }
        
        
        

        System.out.println("BusquedaProveedorLogicTest [OK]");
    }
}
