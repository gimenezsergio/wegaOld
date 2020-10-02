package test;

import ingresos.entidades.BusquedaProveedor;
import java.util.ArrayList;

public class BusquedaProveedorTest {

    public static void main(String[] args) {

        BusquedaProveedor miBusqueda1 = new BusquedaProveedor("uno", 4, 10);
        BusquedaProveedor miBusqueda2 = new BusquedaProveedor("dos", 5, 10);
        BusquedaProveedor miBusqueda3 = new BusquedaProveedor("tres", 10, 10);
        BusquedaProveedor miBusqueda4 = new BusquedaProveedor("cuatro", 50, 10);
        BusquedaProveedor miBusqueda5 = new BusquedaProveedor("cinco", 40, 10);
        BusquedaProveedor miBusqueda6 = new BusquedaProveedor("seis", 60, 10);

        ArrayList<BusquedaProveedor> listadoDB = new ArrayList();
        listadoDB.add(miBusqueda1);
        listadoDB.add(miBusqueda2);
        listadoDB.add(miBusqueda3);
        listadoDB.add(miBusqueda4);
        listadoDB.add(miBusqueda5);
        listadoDB.add(miBusqueda6);

        for (int i = 0; i < listadoDB.size(); i++) {
            System.out.println(listadoDB.get(i).getProductoNombre() + " No Ok: " + listadoDB.get(i).getNoOk() + " Cant: " + listadoDB.get(i).getCantidad());
            if (listadoDB.get(i).getProductoNombre() == "dos") {
                listadoDB.get(i).setNoOk(listadoDB.get(i).getNoOk() + 10);
            } else {

            }

        }
        System.out.println("----------");
        for (int i = 0; i < listadoDB.size(); i++) {
            System.out.println(listadoDB.get(i).getProductoNombre() + " No Ok: " + listadoDB.get(i).getNoOk() + " Cant: " + listadoDB.get(i).getCantidad());           
        }

    }
}
