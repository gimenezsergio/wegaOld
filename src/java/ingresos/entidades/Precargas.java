package ingresos.entidades;

import java.util.ArrayList;

public class Precargas {
    private PrecargasMaestro maestro;
    private PrecargasDetalle detalle;
    private ArrayList<PrecargasDetalle> detalles;

    public PrecargasMaestro getMaestro() {
        return maestro;
    }

    public void setMaestro(PrecargasMaestro maestro) {
        this.maestro = maestro;
    }

    public PrecargasDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(PrecargasDetalle detalle) {
        this.detalle = detalle;
    }

    public ArrayList<PrecargasDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<PrecargasDetalle> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "{" + maestro + " " + detalles + "}";
    }
    
    
}
