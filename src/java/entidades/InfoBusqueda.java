package entidades;


public class InfoBusqueda {
    private String desde;
    private String hasta;

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    @Override
    public String toString() {
        return "Desde: " + desde + " Hasta: " + hasta;
    }
    
    
}
