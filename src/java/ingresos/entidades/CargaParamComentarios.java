package ingresos.entidades;

public class CargaParamComentarios {

    private String idDetalle;
    private String obs;

    @Override
    public String toString() {
        return idDetalle + " " + obs;
    }
    
    

    public String getIdDetale() {
        return idDetalle;
    }

    public void setIdDetale(String idDetale) {
        this.idDetalle = idDetale;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
