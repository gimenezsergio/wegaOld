package ingresos.logica;

import ingresos.entidades.BusquedaProveedor;
import java.util.ArrayList;

public class BusquedaProveedorLogic {
    
    public static int comprarYAgregar(ArrayList<BusquedaProveedor> listado, String producto, int cant, String verificado) {
        int resultado = 0;
        if(listado.isEmpty()){
            System.out.println("Es null o esta vacio");
        }else{
            //System.out.println("No es null o tiene elementos");
            for (int i = 0; i < listado.size(); i++) {
                
                if(listado.get(i).getProductoNombre().equals(producto)){
                    //Actualizar producto
                    listado.get(i).setCantidad(listado.get(i).getCantidad()+cant);
                    listado.get(i).setEstadoDiseno(verificado);
                    //System.out.println("Producto del array : " + listado.get(i).getProductoNombre() + " es igual a: " + producto);
                    resultado = 1;
                    break;
                }else{
                    // Agregar producto
                    //System.out.println("Producto del array : " + listado.get(i).getProductoNombre() + " NO es igual a: " + producto);
                    resultado = 2;
                }
            }
            
        }
        return resultado;
    }

    public static int comprarYAgregarBORRAR(ArrayList<BusquedaProveedor> listado, String producto, int cant) {
        int resultado = 0;

        for (int i = 0; i < listado.size(); i++) {
            //System.out.println(listado.get(i).getProductoNombre() + " No Ok: " + listado.get(i).getNoOk());
            if (listado.get(i).getProductoNombre().equals(producto)) {
                resultado = 1;
                listado.get(i).setNoOk(listado.get(i).getNoOk() + 1);
                listado.get(i).setCantidad(cant + listado.get(i).getCantidad());
                //System.out.println("Ahora no ok es: " + listado.get(i).getNoOk());
                System.out.println("Nombre de array : " + listado.get(i).getProductoNombre() + " Producto: " + producto);
                break;
            } else {
                resultado = 2;
                System.out.println("ELSE");
            }

            

        }

        return resultado;
    }

}
