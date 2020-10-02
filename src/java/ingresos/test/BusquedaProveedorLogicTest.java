package ingresos.test;

import ingresos.entidades.BusquedaProveedor;
import ingresos.logica.BusquedaProveedorLogic;
import java.util.ArrayList;

public class BusquedaProveedorLogicTest {

    public static void main(String[] args) {
        System.out.println("[...]");
        ArrayList listado = new ArrayList();

        BusquedaProveedor producto1 = new BusquedaProveedor("uno", 2, 5000);

        int proveedorLogic = BusquedaProveedorLogic.comprarYAgregar(listado, producto1.getProductoNombre(), producto1.getCantidad(),"verificar");

        //System.out.println(" proveedorLogic" + proveedorLogic);
        if (proveedorLogic == 1) {

        } else {
            listado.add(producto1);
        }

        BusquedaProveedor producto2 = new BusquedaProveedor("dos", 2, 6000);
        //listado.add(producto2);
        proveedorLogic = BusquedaProveedorLogic.comprarYAgregar(listado, producto2.getProductoNombre(), producto2.getCantidad(),"verificar");
        if (proveedorLogic == 1) {

        } else {
            listado.add(producto2);
        }
//System.out.println(" proveedorLogic" + proveedorLogic);
//        
        BusquedaProveedor producto3 = new BusquedaProveedor("tres", 2, 7000);
        //listado.add(producto3);
        proveedorLogic = BusquedaProveedorLogic.comprarYAgregar(listado, producto3.getProductoNombre(), producto3.getCantidad(),"verificar");
        if (proveedorLogic == 1) {

        } else {
            listado.add(producto3);
        }
        System.out.println(" proveedorLogic" + proveedorLogic);
//        
        BusquedaProveedor producto4 = new BusquedaProveedor("uno", 2, 8000);
//        listado.add(producto4);
        proveedorLogic = BusquedaProveedorLogic.comprarYAgregar(listado, producto4.getProductoNombre(), producto4.getCantidad(),"verificar");                                                
//        System.out.println(" proveedorLogic" + proveedorLogic);
        if (proveedorLogic == 1) {

        } else {
            listado.add(producto4);
        }
        
        
        BusquedaProveedor producto5 = new BusquedaProveedor("uno", 2, 10);
//        listado.add(producto4);
        proveedorLogic = BusquedaProveedorLogic.comprarYAgregar(listado, producto5.getProductoNombre(), producto5.getCantidad(),"verificar");                                                
//        System.out.println(" proveedorLogic" + proveedorLogic);
        if (proveedorLogic == 1) {

        } else {
            listado.add(producto4);
        }
        
        
        System.out.println(listado);

        System.out.println("[OK]");
    }
}
