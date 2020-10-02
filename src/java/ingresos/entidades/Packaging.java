package ingresos.entidades;

public class Packaging {

   
    private String colBarra = ""; 
    private String indBarra = ""; 
    private String indIdent = ""; 
    private String indEquiv = "";
    private String indAplicaciones = "";
    private String chas = "";
    private String colBarraNombre = ""; 
    private String indBarraNombre = ""; 
    private String indIdentNombre = ""; 
    private String indEquivNombre = "";
    private String indAplicacionesNombre = "";
    private String chasNombre = "";
    private String colBarraComent = ""; 
    private String indBarraComent = ""; 
    private String indIdentComent = ""; 
    private String indEquivComent = "";
    private String indAplicacionesComent = "";
    private String chasComent = "";
    private String idPackaging = "";
    private String idDetalle = "";
    private String retener;
    

    public Packaging(String colBarra, String indBarra, String indIdent, String indEquiv, String indAplicaciones, String chas) throws Exception {
        setColBarra(colBarra);
        setIndBarra(indBarra);
        setIndEquiv(indEquiv);
        setIndIdent(indIdent);
        setAplicaciones(indAplicaciones);
        setChas(chas);
    }

    public Packaging() {
        
    }
    


    public String getColBarra() {
        return colBarra;
    }

    public void setColBarra(String colBarra) throws Exception {
        if(colBarra == null || colBarra.isEmpty()){
            throw new Exception("El codigo de barra de caja colectiva no puede estar vacio.");
        }else{
            this.colBarra = colBarra;
        }
        
    }

    public String getIndBarra() {
        return indBarra;
    }

    public void setIndBarra(String indBarra) throws Exception {
        if(indBarra == null || indBarra.isEmpty()){
            throw new Exception("El codigo de barra de caja individual no puede estar vacio.");
        }else{
            this.indBarra = indBarra;
        }
        
    }

    public String getIndIdent() {
        return indIdent;
    }

    public void setIndIdent(String indIdent) throws Exception {
        if(indIdent == null || indIdent.isEmpty()){
            throw new Exception("La identificacion no puede estar vacia.");
        }else{
            this.indIdent = indIdent;
        }
        
    }

    public String getIndEquiv() {
        return indEquiv;
    }

    public void setIndEquiv(String indEquiv) throws Exception {
        if(indEquiv == null || indEquiv.isEmpty()){
            throw new Exception("La equivalencia no puede estar vacia.");
        }else{
            this.indEquiv = indEquiv;
        }
        
    }

    public String getIdPackaging() {
        return idPackaging;
    }

    public void setIdPackaging(String idPackaging) {
        this.idPackaging = idPackaging;
    }

    
    public String getAplicaciones() {
        return getIndAplicaciones();
    }

    public void setAplicaciones(String aplicaciones) throws Exception {
        if(aplicaciones == null || aplicaciones.isEmpty()){
            throw new Exception("Aplicaciones no puede estar vacia.");
        }else{
            this.setIndAplicaciones(aplicaciones);
        }
        
    }
    
    @Override
    public String toString() {
        return " idPackaging: " + idPackaging + " colBarra: " + colBarra  + " indBarra: " + indBarra + " indEquiv: " + indEquiv + " indIdent: " + indIdent + " Aplicaciones: " + getIndAplicaciones();
    }

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getChas() {
        return chas;
    }

    public void setChas(String chas) throws Exception {
        if(chas == null || chas.isEmpty()){
            throw new Exception("CHAS no puede estar vacio.");
        }else{
            this.chas = chas;
        }
        
    }

    public String getColBarraNombre() {
        return colBarraNombre;
    }

    public void setColBarraNombre(String colBarraNombre) {
        this.colBarraNombre = colBarraNombre;
    }

    public String getIndBarraNombre() {
        return indBarraNombre;
    }

    public void setIndBarraNombre(String indBarraNombre) {
        this.indBarraNombre = indBarraNombre;
    }

    public String getIndIdentNombre() {
        return indIdentNombre;
    }

    public void setIndIdentNombre(String indIdentNombre) {
        this.indIdentNombre = indIdentNombre;
    }

    public String getIndEquivNombre() {
        return indEquivNombre;
    }

    public void setIndEquivNombre(String indEquivNombre) {
        this.indEquivNombre = indEquivNombre;
    }

    public String getIndAplicacionesNombre() {
        return indAplicacionesNombre;
    }

    public void setIndAplicacionesNombre(String indAplicacionesNombre) {
        this.indAplicacionesNombre = indAplicacionesNombre;
    }
    
     public String getIndAplicaciones() {
        return indAplicaciones;
    }

    public void setIndAplicaciones(String indAplicaciones) {
        this.indAplicaciones = indAplicaciones;
    }

    public String getChasNombre() {
        return chasNombre;
    }

    public void setChasNombre(String chasNombre) {
        this.chasNombre = chasNombre;
    }

    public String getColBarraComent() {
        return colBarraComent;
    }

    public void setColBarraComent(String colBarraComent) {
        this.colBarraComent = colBarraComent;
    }

    public String getIndBarraComent() {
        return indBarraComent;
    }

    public void setIndBarraComent(String indBarraComent) {
        this.indBarraComent = indBarraComent;
    }

    public String getIndIdentComent() {
        return indIdentComent;
    }

    public void setIndIdentComent(String indIdentComent) {
        this.indIdentComent = indIdentComent;
    }

    public String getIndEquivComent() {
        return indEquivComent;
    }

    public void setIndEquivComent(String indEquivComent) {
        this.indEquivComent = indEquivComent;
    }

    public String getIndAplicacionesComent() {
        return indAplicacionesComent;
    }

    public void setIndAplicacionesComent(String indAplicacionesComent) {
        this.indAplicacionesComent = indAplicacionesComent;
    }

    public String getChasComent() {
        return chasComent;
    }

    public void setChasComent(String chasComent) {
        this.chasComent = chasComent;
    }

    public String getRetener() {
        return retener;
    }

    public void setRetener(String retener) {
        this.retener = retener;
    }

    
}
