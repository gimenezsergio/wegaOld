package ingresos.entidades;

public class BusquedProd {
    private String proveedor;
    private int cantidad;

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "BusquedProd{" + "proveedor=" + proveedor + ", cantidad=" + cantidad + '}';
    }
    
}
