package entidades;

public class ReclamoBusqueda {


    String proveedorId;
    String proveedorNombre;
    String productoNmbre;
    String productoId;
    String fundados;

 

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getProveedorNombre() {
        return proveedorNombre;
    }

    public void setProveedorNombre(String proveedorNombre) {
        this.proveedorNombre = proveedorNombre;
    }

    public String getProductoNmbre() {
        return productoNmbre;
    }

    public void setProductoNmbre(String productoNmbre) {
        this.productoNmbre = productoNmbre;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getFundados() {
        return fundados;
    }

    public void setFundados(String fundados) {
        this.fundados = fundados;
    }

    @Override
    public String toString() {
        return "ReclamoBusqueda{" + "proveedorId=" + proveedorId + ", proveedorNombre=" + proveedorNombre + ", productoNmbre=" + productoNmbre + ", productoId=" + productoId + ", fundados=" + fundados + '}';
    }
    
    
}
