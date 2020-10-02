package ingresos.entidades;

import java.util.Map;
import java.util.TreeMap;

public class PlanAireHabit {
    private String IdPlanAireHabit;
    private String IdDetalle;
    private String identificacion;
    private String aspectoVisual;
    private String lote;
    private String largo;
    private String alto;
    private String ancho;
    private String elemFiltrante;
    private String identificacionId;
    private String aspectoVisualId;
    private String loteId;
    private String largoId;
    private String altoId;
    private String anchoId;
    private String elemFiltranteId;
    private Map medidaLargo;
    private Map medidaAlto;
    private Map medidaAncho;
    
    public PlanAireHabit (String identificacion, String aspectoVisual, String lote, String largo, String alto, String ancho, String elemFiltrante,String identificacionId, String aspectoVisualId, String loteId, String largoId, String altoId, String anchoId, String elemFiltranteId, Map medidaLargo, Map medidaAlto, Map medidaAncho) throws Exception{
        setIdentificacion(identificacion);
        setAspectoVisual(aspectoVisual);
        setLote(lote);
        setLargo(largo);
        setAlto(alto);
        setAncho(ancho);
        setElemFiltrante(elemFiltrante);
        setIdentificacionId(identificacionId);
        setAspectoVisualId(aspectoVisualId);
        setLoteId(loteId);
        setLargoId(largoId);
        setAltoId(altoId);
        setAnchoId(anchoId);
        setElemFiltranteId(elemFiltranteId);
        setMedidaLargo(medidaLargo);
        setMedidaAlto(medidaAlto);
        setMedidaAncho(medidaAncho);
    }

    public String getIdPlanAireHabit() {
        return IdPlanAireHabit;
    }

    public void setIdPlanAireHabit(String IdPlanAireHabit) {
        this.IdPlanAireHabit = IdPlanAireHabit;
    }

    public String getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(String IdDetalle) {
        this.IdDetalle = IdDetalle;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) throws Exception {
        if(identificacion == null || identificacion.isEmpty()){
            throw new Exception("Identificación no puede estar vacia.");
        }else{
            this.identificacion = identificacion;
        }
        
    }

    public String getAspectoVisual() {
        return aspectoVisual;
    }

    public void setAspectoVisual(String aspectoVisual) throws Exception {
        if(aspectoVisual == null || aspectoVisual.isEmpty()){
            throw new Exception("Aspecto visual no puede estar vacio.");
        }else{
            this.aspectoVisual = aspectoVisual;
        }
        
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) throws Exception {
        if(lote == null || lote.isEmpty()){
            throw new Exception("Lote no puede estar vacio.");
        }else{
            this.lote = lote;
        }
        
    }

    public String getLargo() {
        return largo;
    }

    public void setLargo(String largo) throws Exception {
        if(largo == null || largo.isEmpty()){
            throw new Exception("Largo no puede estar vacio.");
        }else{
            this.largo = largo;
        }
        
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) throws Exception {
        if(alto == null || alto.isEmpty()){
            throw new Exception("Alto no puede estar vacio.");
        }else{
            this.alto = alto;
        }
        
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) throws Exception {
        if(ancho == null || ancho.isEmpty()){
            throw new Exception("Ancho no puede estar vacio.");
        }else{
            this.ancho = ancho;
        }
        
    }

    public String getElemFiltrante() {
        return elemFiltrante;
    }

    public void setElemFiltrante(String elemFiltrante) throws Exception {
        if(elemFiltrante == null || elemFiltrante.isEmpty()){
            throw new Exception("Elemento filtrante no puede estar vacio.");
        }else{
            this.elemFiltrante = elemFiltrante;
        }
        
    }

    public String getIdentificacionId() {
        return identificacionId;
    }

    public void setIdentificacionId(String identificacionId) throws Exception {
        if(identificacionId == null || identificacionId.isEmpty()){
            throw new Exception("identificacionId no puede estar vacio.");
        }else{
            this.identificacionId = identificacionId;
        }
        
    }

    public String getAspectoVisualId() {
        return aspectoVisualId;
    }

    public void setAspectoVisualId(String aspectoVisualId) throws Exception {
        if(aspectoVisualId == null || aspectoVisualId.isEmpty()){
            throw new Exception("aspectoVisualId no puede estar vacio.");
        }else{
            this.aspectoVisualId = aspectoVisualId;
        }
        
    }

    public String getLoteId() {
        return loteId;
    }

    public void setLoteId(String loteId) throws Exception {
        if(loteId == null || loteId.isEmpty()){
            throw new Exception("loteId no puede estar vacio.");
        }else{
            this.loteId = loteId;
        }
        
    }

    public String getLargoId() {
        return largoId;
    }

    public void setLargoId(String largoId) throws Exception {
        if(largoId == null || largoId.isEmpty()){
            throw new Exception("largoId no puede estar vacio.");
        }else{
            this.largoId = largoId;
        }
        
    }

    public String getAltoId() {
        return altoId;
    }

    public void setAltoId(String altoId) throws Exception {
        if(altoId == null || altoId.isEmpty()){
            throw new Exception("altoId no puede estar vacio.");
        }else{
            this.altoId = altoId;
        }
        
    }

    public String getAnchoId() {
        return anchoId;
    }

    public void setAnchoId(String anchoId) throws Exception {
        if(anchoId == null || anchoId.isEmpty()){
            throw new Exception("anchoId no puede estar vacio.");
        }else{
            this.anchoId = anchoId;
        }
        
    }

    public String getElemFiltranteId() {
        return elemFiltranteId;
    }

    public void setElemFiltranteId(String elemFiltranteId) throws Exception {
        if(elemFiltranteId == null || elemFiltranteId.isEmpty()){
            throw new Exception("elemFiltranteId no puede estar vacio.");
        }else{
            this.elemFiltranteId = elemFiltranteId;
        }
        
    }

    public Map getMedidaLargo() {
        return medidaLargo;
    }

    public void setMedidaLargo(Map medidaLargo) throws Exception {
        String nom = (String) medidaLargo.get("largo_filtro_aire_habit_Nom");
        String min = (String) medidaLargo.get("largo_filtro_aire_habit_Min");
        String max = (String) medidaLargo.get("largo_filtro_aire_habit_Max");
        if( (nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())){
            throw new Exception("Medidas de largo no pueden estar vacias.");
        }else{
            this.medidaLargo = medidaLargo;
        }
        
        
    }

    public Map getMedidaAlto() {
        return medidaAlto;
    }

    public void setMedidaAlto(Map medidaAlto) throws Exception {
        String nom = (String) medidaAlto.get("alto_filtro_aire_habit_Nom");
        String min = (String) medidaAlto.get("alto_filtro_aire_habit_Min");
        String max = (String) medidaAlto.get("alto_filtro_aire_habit_Max");
        if( (nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())){
            throw new Exception("Medidas de alto no pueden estar vacias.");
        }else{
            this.medidaAlto = medidaAlto;
        }
        
    }

    public Map getMedidaAncho() {
        return medidaAncho;
    }

    public void setMedidaAncho(Map medidaAncho) throws Exception {
        String nom = (String) medidaAncho.get("Ancho_filtro_aire_habit_Nom");
        String min = (String) medidaAncho.get("Ancho_filtro_aire_habit_Min");
        String max = (String) medidaAncho.get("Ancho_filtro_aire_habit_Max");
        if( (nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())){
            throw new Exception("Medidas de ancho no pueden estar vacias.");
        }else{
            this.medidaAncho = medidaAncho;
        }
        
    }
    
}
