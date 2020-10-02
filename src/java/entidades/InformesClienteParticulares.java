package entidades;

import java.util.ArrayList;

public class InformesClienteParticulares {
    private String cliente;
    private String motivo;
    private String fecha;
    private String producto;
    private String codigo;
    private String cantidad;
    private String lote;
    private String visual;
    private String observaciones;
    private String analisis_ensayos;
    private String inst_medios_control;
    private String conclusiones;
    private ArrayList<String> fotos;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) throws Exception {
        if (fecha == null || fecha.isEmpty()) {
            throw new Exception("La fecha no puede estar vacia.");
        } else {
            this.fecha = fecha;
        }
        
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getVisual() {
        return visual;
    }

    public void setVisual(String visual) {
        this.visual = visual;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getAnalisis_ensayos() {
        return analisis_ensayos;
    }

    public void setAnalisis_ensayos(String analisis_ensayos) {
        this.analisis_ensayos = analisis_ensayos;
    }

    public String getInst_medios_control() {
        return inst_medios_control;
    }

    public void setInst_medios_control(String inst_medios_control) {
        this.inst_medios_control = inst_medios_control;
    }

    public String getConclusiones() {
        return conclusiones;
    }

    public void setConclusiones(String conclusiones) {
        this.conclusiones = conclusiones;
    }

    public ArrayList<String> getFotos() {
        return fotos;
    }

    public void setFotos(ArrayList<String> fotos) {
        this.fotos = fotos;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
