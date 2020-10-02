package entidades;

public class Productos {
    
    private String id_producto;
    private String codigo_producto;
    private String familia;
    private String subFamilia;

    public Productos(String codigo_producto, String familia, String subFamilia) throws Exception {
//        this.id_producto = id_producto;
//        this.codigo_producto = codigo_producto;
//        this.familia = familia;
//        this.subFamilia = subFamilia;
        setSubFamilia(subFamilia);
        setFamilia(familia);
        setCodigo_producto(codigo_producto);
    }

    
    

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) throws Exception {
         if( id_producto == null || id_producto.isEmpty()){
            throw new Exception("El producto no puede estar vacio.");
        }else{
           this.id_producto = id_producto;
        }
        
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) throws Exception {
        if( familia == null || familia.isEmpty()){
            throw new Exception("La familia del prodcuto no puede estar vacia");
        }else{
            this.familia = familia;
        }
        
    }

    public String getSubFamilia() {
        return subFamilia;
    }

    public void setSubFamilia(String subFamilia) throws Exception {
        if ( subFamilia == null || subFamilia.isEmpty()) {
            throw new Exception("La subfamilia del prodcuto no puede estar vacia");
        }else{
        this.subFamilia = subFamilia;
        }
    }

    @Override
    public String toString() {
        return "Producto: " + codigo_producto + " Id: " + id_producto + " Familia: " + familia + " Subfamilia: " + subFamilia;
    }
    
    
    
    
}
