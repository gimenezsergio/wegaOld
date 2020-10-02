package entidades;

import java.util.ArrayList;

public class Reclamos {
    private ReclamosEncabezado encabezado;
    private ReclamoDetalle detalle;
    private ArrayList listadoDetalle = new ArrayList();
    
    @Override
    public String toString() {
        return getEncabezado() + " - " + getListadoDetalle();
    }
    
    
    
//    public Reclamos(ReclamosEncabezado pencabezado, ReclamoDetalle pdetalle){
//        setEncabezado(pencabezado);
//        setDetalle(pdetalle);
//    }
//    
//    public Reclamos(ReclamosEncabezado pencabezado){
//        setEncabezado(pencabezado);
//    }
    
//    public Reclamos(ReclamoDetalle pdetalle){
//        setDetalle(pdetalle);
//    }

    public ReclamosEncabezado getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(ReclamosEncabezado encabezado) {
        this.encabezado = encabezado;
    }

    public ReclamoDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(ReclamoDetalle detalle) {
        this.detalle = detalle;
        listadoDetalle.add(detalle);
        System.out.println("Detalle a agregar: " + detalle);
        System.out.println("Listado de productos: " + getListadoDetalle());
    }

    public ArrayList getListadoDetalle() {
        return listadoDetalle;
    }

    private void setListadoDetalle(ArrayList listadoDetalle) {
        this.listadoDetalle = listadoDetalle;
    }

    



   

   
}
