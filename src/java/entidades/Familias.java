package entidades;

public class Familias {
    private String nombre;
    private String id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if( nombre == null || nombre.isEmpty()){
            throw new Exception("El nombre de Familia no puede estar vacio");
        }else{
            this.nombre = nombre;
        }
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new Exception("El ID de Familia no puede estar vacio.");
        } else {
            this.id = id;
        }
        
    }
}
