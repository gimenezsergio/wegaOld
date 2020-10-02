package ingresos.entidades;

import java.util.ArrayList;

public class CargaParam {
    private String itemMaestroId;
    private String fecha_recepcion;
    private String analista;  
    private ArrayList<CargaParamComentarios> comentarios = new ArrayList();

    @Override
    public String toString() {
        return "CargaParam{" + "itemMaestroId=" + itemMaestroId + ", fecha_recepcion=" + fecha_recepcion + ", analista=" + analista + ", comentarios=" + comentarios + '}';
    }

    
    
    

    public String getIdMaestro() {
        return itemMaestroId;
    }

    public void setIdMaestro(String idMaestro) {
        this.itemMaestroId = idMaestro;
    }

    public String getFecha() {
        return fecha_recepcion;
    }

    public void setFecha(String fecha) {
        this.fecha_recepcion = fecha;
    }

    public ArrayList<CargaParamComentarios> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<CargaParamComentarios> comentarios) {
        this.comentarios = comentarios;
    }
    
    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) throws Exception {
       if (analista == null || analista.isEmpty()) {
            throw new Exception("El analista no puede estar vacio.");
        }else {
            this.analista = analista;
        }  
    }
}
