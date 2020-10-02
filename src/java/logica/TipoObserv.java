package logica;

public class TipoObserv {
    private String tipo;
    private String id;

    public TipoObserv(String pnombre, String pid) {
        setId(pid);
        setTipo(pnombre);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" + " tipo: " + tipo + " , ID: " + id + "}";
    }
    
    
}
