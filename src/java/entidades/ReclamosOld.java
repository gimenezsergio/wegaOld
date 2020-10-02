package entidades;

public class ReclamosOld {

    private String id_reclamo;
    private String cliente;
    private String fecha;
    private String fecha_emision;
    private String fecha_recepcion;
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
    private String proceso;
    private String falla_mat;
    private String diseno;
    private String diagnostico;
    String userId;

    public ReclamosOld() {
    }

    public String getProceso() {
        return proceso;
    }

    public static void ValidateCant(String cantidad_total, String cant_fundados, String cant_infundados, String cant_tema_comercial, int ItemPodudcto) throws Exception {
        int aux = Integer.parseInt(cant_fundados) + Integer.parseInt(cant_infundados) + Integer.parseInt(cant_tema_comercial);
        System.out.println("Suma cantidades: " + aux);
        System.out.println("cantidad total: " + cantidad_total);
        if (Integer.parseInt(cantidad_total) == aux) {
            System.out.println("Cantidad total igual");
        } else {
            throw new Exception("La Cantidad Total no coincide con la suma del dictamen. (Producto " + (ItemPodudcto + 1) + ")");
        }

    }

    @Override
    public String toString() {
        return "Cliente: " + cliente + " fecha recepcion: " + fecha_recepcion + " Producto: " + codigo_producto;
    }

    public String getId_reclamo() {
        return id_reclamo;
    }

    public void setId_reclamo(String id_reclamo) throws Exception {
        if (id_reclamo == null || id_reclamo.isEmpty()) {
            throw new Exception("El id del reclamos no puede estar vacio.");
        } else {
            this.id_reclamo = id_reclamo;
        }

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) throws Exception {
        if (cliente == null || cliente.isEmpty()) {
            throw new Exception("El cliente no puede estar vacio.");
        } else {
            this.cliente = cliente;
        }

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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) throws Exception {
        if (fecha == null || fecha.isEmpty()) {
            throw new Exception("Fecha no puede estar vacia");
        } else {
            this.fecha = fecha;
        }

    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) throws Exception {
        if (fecha_emision == null || fecha_emision.isEmpty()) {
            throw new Exception("La fecha de emision no puede estar vacia.");
        } else {
            this.fecha_emision = fecha_emision;
        }

    }

    public String getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(String fecha_recepcion) throws Exception {
        if (fecha_recepcion == null || fecha_recepcion.isEmpty()) {
            throw new Exception("La fecha de recepcion no puede estar vacia.");
        } else {
            this.fecha_recepcion = fecha_recepcion;
        }

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
        if (id_diseno == null || id_diseno.isEmpty()) {
            throw new Exception("Diseno no puede estar vacio. (Producto " + (ItemPodudcto + 1) + ")");
            //this.id_diseno = null;
        } else {
            this.id_diseno = id_diseno;
        }

    }

    public String getId_proceso() {
        return id_proceso;
    }

    public void setId_proceso(String id_proceso, int ItemPodudcto) throws Exception {
        if (id_proceso == null || id_proceso.isEmpty()) {
            throw new Exception("Proceso no puede estar vacio. (Producto " + (ItemPodudcto + 1) + ")");
        } else {
            this.id_proceso = id_proceso;
        }

    }

    public String getId_falla_mat() {
        return id_falla_mat;
    }

    public void setId_falla_mat(String id_falla_mat, int ItemPodudcto) throws Exception {
        if (id_falla_mat == null || id_falla_mat.isEmpty()) {
            throw new Exception("Falla de materiales no puede estar vacio. (Producto " + (ItemPodudcto + 1) + ")");
        } else {
            this.id_falla_mat = id_falla_mat;
        }

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

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getFalla_mat() {
        return falla_mat;
    }

    public void setFalla_mat(String falla_mat) {
        this.falla_mat = falla_mat;
    }

    public String getDiseno() {
        return diseno;
    }

    public void setDiseno(String diseno) {
        this.diseno = diseno;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

}
