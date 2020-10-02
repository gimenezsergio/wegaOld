package ingresos.test;

import ingresos.entidades.BusquedaProveedor;

public class BusquedaProveedorTest {
    public static void main(String[] args) {
        System.out.println("[...]");
        BusquedaProveedor prov = new BusquedaProveedor();
        prov.setCantidad(100);
        prov.setComentario("comen");
        prov.setIngresoNro("13");
        prov.setNoOk(1);
        prov.setNoOk(prov.getNoOk()+1);
        prov.setProductoId("12000");
        prov.setProductoNombre("FAP-9000");
        System.out.println(prov);
        System.out.println("[OK]");
    }
}
