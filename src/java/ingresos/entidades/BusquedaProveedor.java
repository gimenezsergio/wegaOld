package ingresos.entidades;

import java.util.ArrayList;

public class BusquedaProveedor {

    private String productoId;
    private String productoNombre;
    private String comentario;
    private int cantidad;
    private String ingresoNro;
    private String estadoPakaging;
    private String estadoDiseno = "1";
    public String estadoDisenoYPkg = "";
    private String proveedor;

    private int noOk = 1;
    ArrayList listado = new ArrayList();

    public BusquedaProveedor(String productoNombre, int noOK, int cantidad) {
        this.productoNombre = productoNombre;
        this.noOk = noOK;
        this.cantidad = cantidad;
    }

    public void calcularNoOk2() {
        if (estadoDiseno.equals("2") || estadoPakaging.equals("no_ok")) {
            estadoDisenoYPkg = "no_ok";
        } else {
            estadoDisenoYPkg = "ok";
        }
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    

    public void calcularDisenoNoOk() {

    }

    public BusquedaProveedor() {
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public int getNoOk() {
        return noOk;
    }

    public void setNoOk(int noOk) {
        this.noOk = noOk;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getIngresoNro() {
        return ingresoNro;
    }

    public void setIngresoNro(String ingresoNro) {
        this.ingresoNro = ingresoNro;
    }

    public String getEstadoPakaging() {
        return estadoPakaging;
    }

    public void setEstadoPakaging(String estadoPakaging) {
        this.estadoPakaging = estadoPakaging;
    }

    public String getEstadoDiseno() {
        return estadoDiseno;
    }

    public void setEstadoDiseno(String estadoDiseno) {

        if (estadoDiseno.equals("2")) {
            System.out.println(" ++++ estadoDiseno param: " + estadoDiseno);
            //estadoDiseno = "no_ok";
            this.estadoDisenoYPkg = "roto";
        }else{
           
        }
        //this.estadoDisenoYPkg = "roto";
        this.estadoDiseno = estadoDiseno;
    }

    public String getEstadoDisenoYPkg() {
        return estadoDisenoYPkg;
    }

    public void setEstadoDisenoYPkg(String estadoDisenoYPkg) {
        this.estadoDisenoYPkg = estadoDisenoYPkg;
    }

    @Override
    public String toString() {
        return "{" + "productoId=" + productoId + ", productoNombre=" + productoNombre + ", comentario=" + comentario + ", cantidad=" + cantidad + ", ingresoNro=" + ingresoNro + ", estadoPakaging=" + estadoPakaging + ", estadoDiseno=" + estadoDiseno + ", estadoDisenoYPkg=" + estadoDisenoYPkg + ", noOk=" + noOk + ", listado=" + listado + '}';
    }

}
