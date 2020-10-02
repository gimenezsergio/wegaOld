package ingresos.entidades;

public class PrecargasMaestro {
    private String id;
    private String proveedor;
    private String fechaManifesto;
    private String codigoManifiesto;
    private String fechaRecepcion;
    private String obs;
    private String arribo;
     private String analista;

    public PrecargasMaestro(String proveedor, String fechaManifesto, String codigoManifiesto, String fechaRecepcion, String obs, String arribo) throws Exception{
        setCodigoManifiesto(codigoManifiesto);
        setFechaManifesto(fechaManifesto);
        setFechaRecepcion(fechaRecepcion);
        setObs(obs);
        setProveedor(proveedor);
        setArribo(arribo);
    }

    @Override
    public String toString() {
        return "{id :" + id + ", codigoManifiesto : " + codigoManifiesto + ", fechaManifesto: " + fechaManifesto + ", fechaRecepcion : " + fechaRecepcion + ", proveedor : " + proveedor + ", obs : " + obs + "}";
    }

    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) throws Exception {        
            this.analista = analista;        
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) throws Exception {
        if (proveedor == null || proveedor.isEmpty()) {
            throw new Exception("El proveedor no puede estar vacio");
        }else {
            this.proveedor = proveedor;
        }
        
    }

    public String getFechaManifesto() {
        return fechaManifesto;
    }

    public void setFechaManifesto(String fechaManifesto) throws Exception {
        if (fechaManifesto == null || fechaManifesto.isEmpty()) {
            throw new Exception("La fecha de manifiesto no puede estar vacia.");
        }else{
            this.fechaManifesto = fechaManifesto;
        }
        
    }

    public String getCodigoManifiesto() {
        return codigoManifiesto;
    }

    public void setCodigoManifiesto(String codigoManifiesto) throws Exception {
        if (codigoManifiesto == null || codigoManifiesto.isEmpty()) {
            throw new Exception("El codigo de menifiesto no puede estar vacio.");
        }else {
            this.codigoManifiesto = codigoManifiesto;
        }        
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) throws Exception {
//        if (fechaRecepcion == null || fechaRecepcion.isEmpty()) {
//            throw new Exception("La fecha de recepcion no puede estar vacia.");
//        }else {
//            this.fechaRecepcion = fechaRecepcion;
//        }
        
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getArribo() {
        return arribo;
    }

    public void setArribo(String arribo) throws Exception {
        if (arribo == null || arribo.isEmpty()) {
            throw new Exception("El arribo no puede estar vacio.");
        }else {
            this.arribo = arribo;
        }  
        
    }
    
}
