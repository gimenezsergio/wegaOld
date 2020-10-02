package ingresos.entidades;

import java.util.ArrayList;

public class PrecargasDetalle {
    private String id;
    private String producto;
    private String productoId;
    private String cant;
    private String muestras;
    private String Observaciones;
    private String estadoPcking;
    private String estadoDiseno;
    private String familiaId;
    private String subFamiliaId;
    private String familiaNombre;
    private String subfamiliaNombre;
    private String planTemporario;
    private Packaging packaging;
    private String planDiseno;
    private PlanLamparas planLamparas;
    private ArrayList listaPlanGeneral = new ArrayList();
    private String retener;
     private String comentario;
    private PlanGeneral miPlanGeneral;

    public PrecargasDetalle(String producto, String cant, String muestras, String Observaciones) throws Exception {
        setCant(cant);
        setMuestras(muestras);
        setObservaciones(Observaciones);
        setProducto(producto);
    }
    
    public PrecargasDetalle() {
        
    }

    @Override
    public String toString() {
        return "{" + " Producto: " + producto + " , Cant: " + cant + " , Muestras: " + muestras + " , Observaciones: " + Observaciones + " , Estado: " + estadoPcking + " }";
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) throws Exception {
        if (producto == null || producto.isEmpty()) {
            throw new Exception("El producto no puede estar vacio");
        }else{
            this.producto = producto;
        }
        
    }

    public String getCant() {
        return cant;
    }

    public void setCant(String cant) throws Exception {
        if (cant == null || cant.isEmpty()) {
            throw new Exception("Cantidad no puede ser menor a cero.");
        }else{
            this.cant = cant;
        }
        
    }

    public String getMuestras() {
        return muestras;
    }

    public void setMuestras(String muestras) throws Exception {
        if (muestras == null || muestras.isEmpty()) {
            throw new Exception("Muestras no puede ser menor a cero");
        } else {
            this.muestras = muestras;
        }
        
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public String getEstadoPcking() {
        return estadoPcking;
    }

    public void setEstadoPcking(String estado) {
        this.estadoPcking = estado;
    }

    public String getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(String familiaId) {
        this.familiaId = familiaId;
    }

    public String getSubFamiliaId() {
        return subFamiliaId;
    }

    public void setSubFamiliaId(String subFamiliaId) {
        this.subFamiliaId = subFamiliaId;
    }

    public String getFamiliaNombre() {
        return familiaNombre;
    }

    public void setFamiliaNombre(String familiaNombre) {
        this.familiaNombre = familiaNombre;
    }

    public String getSubfamiliaNombre() {
        return subfamiliaNombre;
    }

    public void setSubfamiliaNombre(String subfamiliaNombre) {
        this.subfamiliaNombre = subfamiliaNombre;
    }

    public String getEstadoDiseno() {
        return estadoDiseno;
    }

    public void setEstadoDiseno(String estadoDiseno) {
        this.estadoDiseno = estadoDiseno;
    }

    public String getPlanTemporario() {
        return planTemporario;
    }

    public void setPlanTemporario(String planTemporario) {
        this.planTemporario = planTemporario;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    public String getPlanDiseno() {
        return planDiseno;
    }

    public void setPlanDiseno(String planDiseno) {
        this.planDiseno = planDiseno;
    }

    public PlanLamparas getPlanLamparas() {
        return planLamparas;
    }

    public void setPlanLamparas(PlanLamparas planLamparas) {
        this.planLamparas = planLamparas;
    }

    public ArrayList getListaPlanGeneral() {
        return listaPlanGeneral;
    }

    public void setListaPlanGeneral(ArrayList listaPlanGeneral) {
        this.listaPlanGeneral = listaPlanGeneral;
    }

    public PlanGeneral getMiPlanGeneral() {
        return miPlanGeneral;
    }

    public void setMiPlanGeneral(PlanGeneral miPlanGeneral) {
        this.miPlanGeneral = miPlanGeneral;
        listaPlanGeneral.add(miPlanGeneral);
//        System.out.println(" Agrega Plan General al ArrayList : " + listaPlanGeneral);
    }

    public String getRetener() {
        return retener;
    }

    public void setRetener(String retener) {
        this.retener = retener;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
