package ingresos.entidades;

public class Arribos {
private String nombre;
private String arriboId;
private String disponible;

    public Arribos(String nombre, String arriboId, String disponible) {
        setNombre(nombre);
        setArriboId(arriboId);
        setDisponible(disponible);        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArriboId() {
        return arriboId;
    }

    public void setArriboId(String arriboId) {
        this.arriboId = arriboId;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String dispomible) {
        this.disponible = dispomible;
    }
}
