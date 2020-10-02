package entidades;
public class MensajeBrowser {
    private String titulo;
    private String mensaje;
    private String tipo;
    
    public MensajeBrowser(String ptitulo, String pmensaje, String ptipo){
        setMensaje(pmensaje);
        setTitulo(ptitulo);
        setTipo(ptipo);
    }

    

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void mostrar() {
        
    }
    
    @Override
    public String toString() {
        return "Titulo: " + titulo + " :  Mensaje " + mensaje + " : Tipo: " + tipo ;
    }
}
