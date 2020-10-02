package ingresos.entidades;

public class Medidas {
    private String id;
    private String medicion_nombre;
    private String plan_nombre;
    private String medida;
    
    public Medidas(String id, String medicion_nombre, String plan_nombre, String medida){
        setId(id);
        setMedicion_nombre(medicion_nombre);
        setPlan_nombre(plan_nombre);
        setMedida(medida);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicion_nombre() {
        return medicion_nombre;
    }

    public void setMedicion_nombre(String medicion_nombre) {
        this.medicion_nombre = medicion_nombre;
    }

    public String getPlan_nombre() {
        return plan_nombre;
    }

    public void setPlan_nombre(String plan_nombre) {
        this.plan_nombre = plan_nombre;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }
    
}
