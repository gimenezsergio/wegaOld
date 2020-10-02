package entidades;

public class ReclamoDetalle {
    private String id_reclamo;
    private String nro_reclamo;
    private String codigo_producto;
    private String cantidad_total;
    private String motivo;
    private String lote;
    private String origen;
    private String verificacion;
    private String resultado;
    private String observacion;
    private String cant_fundados;
    private String cant_infundados;
    private String cant_tema_comercial;
    private String id_diseno;
    private String id_proceso;
    private String id_falla_mat;
    private String id_provee;
    private String nombre_diseno;
    private String nombre_proceso;
    private String nombre_falla_mat;
    private String nombre_provee;
    private String nombre_origen;
    private String nombre_verificacion;
    private String nombre_resultado;
    private String nombre_producto;

    @Override
    public String toString() {
        return id_reclamo + " :: " + codigo_producto;
    }
    
    
    
    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto, int ItemPodudcto) throws Exception {
        //int ItemPodudcto es para dar el mensaje de a que numero de producto del reclamo pertenece la excepcion
        //Usar cero para GET
        if (codigo_producto == null || codigo_producto.isEmpty()) {
            throw new Exception("El producto no puede estar vacio. (Producto " + (ItemPodudcto + 1) + ")");
        } else {
            this.codigo_producto = codigo_producto;
        }

    }

    public String getVerificacion() {
        return verificacion;
    }

    public void setVerificacion(String verificacion, int ItemPodudcto) throws Exception {
        if (verificacion == null || verificacion.isEmpty()) {
            throw new Exception("Verificacion no puede estar vacio (Producto " + (ItemPodudcto + 1) + ")");
        } else {
            this.verificacion = verificacion;
        }
    }

    public String getCantidad_total() {
        return cantidad_total;
    }

    public void setCantidad_total(String cantidad_total, int ItemPodudcto) throws Exception {
        if (cantidad_total == null || cantidad_total.isEmpty()) {
            throw new Exception("Cantidad total no puede estar vacia (Producto " + (ItemPodudcto + 1) + ")");
        } else {
            this.cantidad_total = cantidad_total;
        }
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

   

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen, int ItemPodudcto) throws Exception {
        if (origen == null || origen.isEmpty()) {
            throw new Exception("El origen no puede estar vacio (Producto " + (ItemPodudcto + 1) + ")");
        } else {
            this.origen = origen;
        }

    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getNro_reclamo() {
        return nro_reclamo;
    }

    public void setNro_reclamo(String nro_reclamo) {
        this.nro_reclamo = nro_reclamo;
    }

    public String getCant_infundados() {
        return cant_infundados;
    }

    public void setCant_infundados(String cant_infundados, int ItemPodudcto) throws Exception {
        if (cant_infundados == null || cant_infundados.isEmpty()) {
            throw new Exception("La cantidad de infundados no puede estar vacia (Producto " + (ItemPodudcto + 1) + ")");
        } else {
            this.cant_infundados = cant_infundados;
        }

    }

    public String getCant_tema_comercial() {
        return cant_tema_comercial;
    }

    public void setCant_tema_comercial(String cant_tema_comercial, int ItemPodudcto) throws Exception {
        if (cant_tema_comercial == null || cant_tema_comercial.isEmpty()) {
            throw new Exception("La cantidad de Tema Comercial no puede estar vacia (Producto " + (ItemPodudcto + 1) + ")");
        } else {
            this.cant_tema_comercial = cant_tema_comercial;
        }

    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado, int ItemPodudcto) throws Exception {
        if (resultado == null || resultado.isEmpty()) {
            throw new Exception("El resultado no puede estar vacio. (Producto " + (ItemPodudcto + 1) + ")");
        } else {

        }
        this.resultado = resultado;
    }

    public String getCant_fundados() {
        return cant_fundados;
    }

    public void setCant_fundados(String cant_fundados, int ItemPodudcto) throws Exception {
        if (cant_fundados == null || cant_fundados.isEmpty()) {
            throw new Exception("La cantidad de fundados no puede estar vacia. (Producto " + (ItemPodudcto + 1) + ")");
        } else {
            this.cant_fundados = cant_fundados;
        }

    }

    public String getId_diseno() {
        return id_diseno;
    }

    public void setId_diseno(String id_diseno, int ItemPodudcto) throws Exception {
//        if (id_diseno == null || id_diseno.isEmpty()) {
//           throw new Exception("Diseno no puede estar vacio. (Producto " + (ItemPodudcto + 1) + ")");
//            
//        } else {
            this.id_diseno = id_diseno;
       // }

    }

    public String getId_proceso() {
        return id_proceso;
    }

    public void setId_proceso(String id_proceso, int ItemPodudcto) throws Exception {
//        if (id_proceso == null || id_proceso.isEmpty()) {
//           throw new Exception("Proceso no puede estar vacio. (Producto " + (ItemPodudcto + 1) + ")");
//        } else {
            this.id_proceso = id_proceso;
     //   }

    }

    public String getId_falla_mat() {
        return id_falla_mat;
    }

    public void setId_falla_mat(String id_falla_mat, int ItemPodudcto) throws Exception {
//        if (id_falla_mat == null || id_falla_mat.isEmpty()) {
//            throw new Exception("Falla de materiales no puede estar vacio. (Producto " + (ItemPodudcto + 1) + ")");
//        } else {
            this.id_falla_mat = id_falla_mat;
      //  }

    }

    public String getId_provee() {
        return id_provee;
    }

    public void setId_provee(String id_provee, int ItemPodudcto) throws Exception {
        if (id_provee == null || id_provee.isEmpty()) {
            throw new Exception("El proveedores no puede estar vacio (Producto " + (ItemPodudcto + 1) + ")");
        } else {
            this.id_provee = id_provee;
        }

    }

    public String getId_reclamo() {
        return id_reclamo;
    }

    public void setId_reclamo(String id_reclamo) {
        this.id_reclamo = id_reclamo;
    }

    public String getNombre_diseno() {
        return nombre_diseno;
    }

    public void setNombre_diseno(String nombre_diseno) {
        this.nombre_diseno = nombre_diseno;
    }

    public String getNombre_proceso() {
        return nombre_proceso;
    }

    public void setNombre_proceso(String nombre_proceso) {
        this.nombre_proceso = nombre_proceso;
    }

    public String getNombre_falla_mat() {
        return nombre_falla_mat;
    }

    public void setNombre_falla_mat(String nombre_falla_mat) {
        this.nombre_falla_mat = nombre_falla_mat;
    }

    public String getNombre_provee() {
        return nombre_provee;
    }

    public void setNombre_provee(String nombre_provee) {
        this.nombre_provee = nombre_provee;
    }

    public String getNombre_origen() {
        return nombre_origen;
    }

    public void setNombre_origen(String nombre_origen) {
        this.nombre_origen = nombre_origen;
    }

    public String getNombre_verificacion() {
        return nombre_verificacion;
    }

    public void setNombre_verificacion(String nombre_verificacion) {
        this.nombre_verificacion = nombre_verificacion;
    }

    public String getNombre_resultado() {
        return nombre_resultado;
    }

    public void setNombre_resultado(String nombre_resultado) {
        this.nombre_resultado = nombre_resultado;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }
}
