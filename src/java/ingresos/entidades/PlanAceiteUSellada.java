package ingresos.entidades;

import java.util.Map;

public class PlanAceiteUSellada {
    private String IdPlanAceiteUSellada;
    private String IdDetalle;
    private String identificacion;
    private String aspectoVisual;
    private String lote;
    private String diametroCarcasa;
    private String altoTotalJunta;
    private String diametrojuntaSello;
    private String alturaJuntaSello;
    private String durezaJuntaSello;
    private String rosca;
    private String brida;
    private String flapper;
    private String valvulaDescarga;
    private String valvulaSeguridad;
    private String tuercaFijacion;
    private String identificacionId;
    private String aspectoVisualId;
    private String loteId;
    private String diametroCarcasaId;
    private String altoTotalJuntaId;
    private String diametrojuntaSelloId;
    private String alturaJuntaSelloId;
    private String durezaJuntaSelloId;
    private String roscaId;
    private String bridaId;
    private String falpperId;
    private String valvulaDescargaId;
    private String valvulaSeguridadId;
    private String tuercaFijacionId;
    private Map medidaDiametroCarcasa;
    private Map medidaAltoTotalJunta;
    private Map medidaDiametroJuntaSello;
    private Map medidaDurezaJuntaSello;
    private Map medidaAlturaJuntaSello;

    public PlanAceiteUSellada(String identificacion, String aspectoVisual, String lote, String diametroCarcasa, String altoTotalJunta,
            String diametrojuntaSello, String durezaJuntaSello, String alturaJuntaSello, String rosca, String brida,String flapper, String valvulaDescarga,
            String valvulaSeguridad, String tuercaFijacion, Map medidaDiametroCarcasa, Map medidaAltoTotalJunta,
            Map medidaDiametroJuntaSello, Map medidaDurezaJuntaSello, Map medidaAlturaJuntaSello) throws Exception {
        setIdentificacion(identificacion);
        setAspectoVisual(aspectoVisual);
        setLote(lote);
        setDiametroCarcasa(diametroCarcasa);
        setAltoTotalJunta(altoTotalJunta);
        setDiametrojuntaSello(diametrojuntaSello);
        setDurezaJuntaSello(durezaJuntaSello);
        setAlturaJuntaSello(alturaJuntaSello);
        setRosca(rosca);
        setBrida(brida);
        setFlapper(flapper);
        setValvulaDescarga(valvulaDescarga);
        setValvulaSeguridad(valvulaSeguridad);
        setTuercaFijacion(tuercaFijacion);
        setMedidaAltoTotalJunta(medidaAltoTotalJunta);
        setMedidaAlturaJuntaSello(medidaAlturaJuntaSello);
        setMedidaDiametroCarcasa(medidaDiametroCarcasa);
        setMedidaDiametroJuntaSello(medidaDiametroJuntaSello);
        setMedidaDurezaJuntaSello(medidaDurezaJuntaSello);
    }

    public String getIdPlanAceiteUSellada() {
        return IdPlanAceiteUSellada;
    }

    public void setIdPlanAceiteUSellada(String IdPlanAceiteUSellada) {
        this.IdPlanAceiteUSellada = IdPlanAceiteUSellada;
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
        if (identificacion == null || identificacion.isEmpty()) {
            throw new Exception("Identificación no puede estar vacia.");
        } else {
            this.identificacion = identificacion;
        }
    }

    public String getAspectoVisual() {
        return aspectoVisual;
    }

    public void setAspectoVisual(String aspectoVisual) throws Exception {
        if (aspectoVisual == null || aspectoVisual.isEmpty()) {
            throw new Exception("Aspecto visual no puede estar vacio.");
        } else {
            this.aspectoVisual = aspectoVisual;
        }
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) throws Exception {
        if (lote == null || lote.isEmpty()) {
            throw new Exception("Lote no puede estar vacio.");
        } else {
            this.lote = lote;
        }
    }

    public String getDiametroCarcasa() {
        return diametroCarcasa;
    }

    public void setDiametroCarcasa(String diametroCarcasa) throws Exception {
        if (diametroCarcasa == null || diametroCarcasa.isEmpty()) {
            throw new Exception("Diametro carcasa no puede estar vacio.");
        } else {
            this.diametroCarcasa = diametroCarcasa;
        }
        
    }

    public String getAltoTotalJunta() {
        return altoTotalJunta;
    }

    public void setAltoTotalJunta(String altoTotalJunta) throws Exception {
        if (altoTotalJunta == null || altoTotalJunta.isEmpty()) {
            throw new Exception("Altura total de junta no puede estar vacio.");
        } else {
            this.altoTotalJunta = altoTotalJunta;
        }
        
    }

    public String getDiametrojuntaSello() {
        return diametrojuntaSello;
    }

    public void setDiametrojuntaSello(String diametrojuntaSello) throws Exception {
        if (diametrojuntaSello == null || diametrojuntaSello.isEmpty()) {
            throw new Exception("Diametro de junta de sello no puede estar vacio.");
        } else {
            this.diametrojuntaSello = diametrojuntaSello;
        }
        
    }

    public String getAlturaJuntaSello() {
        return alturaJuntaSello;
    }

    public void setAlturaJuntaSello(String alturaJuntaSello) throws Exception {
        if (alturaJuntaSello == null || alturaJuntaSello.isEmpty()) {
            throw new Exception("Altura de junta de sello no puede estar vacio.");
        } else {
            this.alturaJuntaSello = alturaJuntaSello;
        }
        
    }

    public String getDurezaJuntaSello() {
        return durezaJuntaSello;
    }

    public void setDurezaJuntaSello(String durezaJuntaSello) throws Exception {
        if (durezaJuntaSello == null || durezaJuntaSello.isEmpty()) {
            throw new Exception("Dureza de junta de sello no puede estar vacio.");
        } else {
            this.durezaJuntaSello = durezaJuntaSello;
        }
        
    }

    public String getRosca() {
        return rosca;
    }

    public void setRosca(String rosca) throws Exception {
        if (rosca == null || rosca.isEmpty()) {
            throw new Exception("Rosca no puede estar vacio.");
        } else {
            this.rosca = rosca;
        }
        
    }

    public String getBrida() {
        return brida;
    }

    public void setBrida(String brida) throws Exception {
        if (brida == null || brida.isEmpty()) {
            throw new Exception("Brida no puede estar vacio.");
        } else {
            this.brida = brida;
        }
        
    }

    public String getFalpper() {
        return flapper;
    }

    public void setFlapper(String flapper) throws Exception {
        if (flapper == null || flapper.isEmpty()) {
            throw new Exception("Flapper no puede estar vacio.");
        } else {
            this.flapper = flapper;
        }
        
    }

    public String getValvulaDescarga() {
        return valvulaDescarga;
    }

    public void setValvulaDescarga(String valvulaDescarga) throws Exception {
        if (valvulaDescarga == null || valvulaDescarga.isEmpty()) {
            throw new Exception("Valvula de descarga no puede estar vacio.");
        } else {
            this.valvulaDescarga = valvulaDescarga;
        }
        
    }

    public String getValvulaSeguridad() {
        return valvulaSeguridad;
    }

    public void setValvulaSeguridad(String valvulaSeguridad) throws Exception {
        if (valvulaSeguridad == null || valvulaSeguridad.isEmpty()) {
            throw new Exception("Valvula de seguridad no puede estar vacio.");
        } else {
            this.valvulaSeguridad = valvulaSeguridad;
        }
    }

    public String getTuercaFijacion() {
        return tuercaFijacion;
    }

    public void setTuercaFijacion(String tuercaFijacion) throws Exception {
        if (tuercaFijacion == null || tuercaFijacion.isEmpty()) {
            throw new Exception("Tuerca de fijacion no puede estar vacio.");
        } else {
            this.tuercaFijacion = tuercaFijacion;
        }
        
    }
    
    public String getIdentificacionId() {
        return identificacionId;
    }

    public void setIdentificacionId(String identificacionId) {
        this.identificacionId = identificacionId;
    }

    public String getAspectoVisualId() {
        return aspectoVisualId;
    }

    public void setAspectoVisualId(String aspectoVisualId) {
        this.aspectoVisualId = aspectoVisualId;
    }

    public String getLoteId() {
        return loteId;
    }

    public void setLoteId(String loteId) {
        this.loteId = loteId;
    }

    public String getDiametroCarcasaId() {
        return diametroCarcasaId;
    }

    public void setDiametroCarcasaId(String diametroCarcasaId) {
        this.diametroCarcasaId = diametroCarcasaId;
    }

    public String getAltoTotalJuntaId() {
        return altoTotalJuntaId;
    }

    public void setAltoTotalJuntaId(String altoTotalJuntaId) {
        this.altoTotalJuntaId = altoTotalJuntaId;
    }

    public String getDiametrojuntaSelloId() {
        return diametrojuntaSelloId;
    }

    public void setDiametrojuntaSelloId(String diametrojuntaSelloId) {
        this.diametrojuntaSelloId = diametrojuntaSelloId;
    }

    public String getAlturaJuntaSelloId() {
        return alturaJuntaSelloId;
    }

    public void setAlturaJuntaSelloId(String alturaJuntaSelloId) {
        this.alturaJuntaSelloId = alturaJuntaSelloId;
    }

    public String getDurezaJuntaSelloId() {
        return durezaJuntaSelloId;
    }

    public void setDurezaJuntaSelloId(String durezaJuntaSelloId) {
        this.durezaJuntaSelloId = durezaJuntaSelloId;
    }

    public String getRoscaId() {
        return roscaId;
    }

    public void setRoscaId(String roscaId) {
        this.roscaId = roscaId;
    }

    public String getBridaId() {
        return bridaId;
    }

    public void setBridaId(String bridaId) {
        this.bridaId = bridaId;
    }

    public String getFalpperId() {
        return falpperId;
    }

    public void setFalpperId(String falpperId) {
        this.falpperId = falpperId;
    }

    public String getValvulaDescargaId() {
        return valvulaDescargaId;
    }

    public void setValvulaDescargaId(String valvulaDescargaId) {
        this.valvulaDescargaId = valvulaDescargaId;
    }

    public String getValvulaSeguridadId() {
        return valvulaSeguridadId;
    }

    public void setValvulaSeguridadId(String valvulaSeguridadId) {
        this.valvulaSeguridadId = valvulaSeguridadId;
    }

    public String getTuercaFijacionId() {
        return tuercaFijacionId;
    }

    public void setTuercaFijacionId(String tuercaFijacionId) {
        this.tuercaFijacionId = tuercaFijacionId;
    }
    
    public Map getMedidaDiametroCarcasa() {
        return medidaDiametroCarcasa;
    }

    public void setMedidaDiametroCarcasa(Map medidaDiametroCarcasa) throws Exception {
        String nom = (String) medidaDiametroCarcasa.get("diametro_carcasa_filtro_aceite_u_sellada_Nom");
        String min = (String) medidaDiametroCarcasa.get("diametro_carcasa_filtro_aceite_u_sellada_Min");
        String max = (String) medidaDiametroCarcasa.get("diametro_carcasa_filtro_aceite_u_sellada_Max");
        if( (nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())){
            throw new Exception("Medidas de diametro de carcasa no pueden estar vacias.");
        }else{
            this.medidaDiametroCarcasa = medidaDiametroCarcasa;
        }
        
    }

    public Map getMedidaAltoTotalJunta() {
        return medidaAltoTotalJunta;
    }

    public void setMedidaAltoTotalJunta(Map medidaAltoTotalJunta) throws Exception {
        String nom = (String) medidaAltoTotalJunta.get("alto_total_junta_filtro_aceite_u_sellada_Nom");
        String min = (String) medidaAltoTotalJunta.get("alto_total_junta_filtro_aceite_u_sellada_Min");
        String max = (String) medidaAltoTotalJunta.get("alto_total_junta_filtro_aceite_u_sellada_Max");
        if( (nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())){
            throw new Exception("Medidas de alto total de junta no pueden estar vacias.");
        }else{
            this.medidaAltoTotalJunta = medidaAltoTotalJunta;
        }
    }

    public Map getMedidaDiametroJuntaSello() {
        return medidaDiametroJuntaSello;
    }

    public void setMedidaDiametroJuntaSello(Map medidaDiametroJuntaSello) throws Exception {
        String nom = (String) medidaDiametroJuntaSello.get("diametro_junta_sello_filtro_aceite_u_sellada_Nom");
        String min = (String) medidaDiametroJuntaSello.get("diametro_junta_sello_filtro_aceite_u_sellada_Min");
        String max = (String) medidaDiametroJuntaSello.get("diametro_junta_sello_filtro_aceite_u_sellada_Max");
        if( (nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())){
            throw new Exception("Medidas Diametro Junta sello no pueden estar vacias.");
        }else{
            this.medidaDiametroJuntaSello = medidaDiametroJuntaSello;
        }
        
    }

    public Map getMedidaDurezaJuntaSello() {
        return medidaDurezaJuntaSello;
    }

    public void setMedidaDurezaJuntaSello(Map medidaDurezaJuntaSello) throws Exception {
        String nom = (String) medidaDurezaJuntaSello.get("dureza_junta_sello_filtro_aceite_u_sellada_Nom");
        String min = (String) medidaDurezaJuntaSello.get("dureza_junta_sello_filtro_aceite_u_sellada_Min");
        String max = (String) medidaDurezaJuntaSello.get("dureza_junta_sello_filtro_aceite_u_sellada_Max");
        if( (nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())){
            throw new Exception("Medidas de Dureza de Junta Sello no pueden estar vacias.");
        }else{
            this.medidaDurezaJuntaSello = medidaDurezaJuntaSello;
        }
    }

    public Map getMedidaAlturaJuntaSello() {
        return medidaAlturaJuntaSello;
    }

    public void setMedidaAlturaJuntaSello(Map medidaAlturaJuntaSello) throws Exception {
        String nom = (String) medidaAlturaJuntaSello.get("altura_junta_sello_filtro_aceite_u_sellada_Nom");
        String min = (String) medidaAlturaJuntaSello.get("altura_junta_sello_filtro_aceite_u_sellada_Min");
        String max = (String) medidaAlturaJuntaSello.get("altura_junta_sello_filtro_aceite_u_sellada_Max");
        if( (nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())){
            throw new Exception("Medidas de Altura Junta de Sello no pueden estar vacias.");
        }else{
            this.medidaAlturaJuntaSello = medidaAlturaJuntaSello;
        }
    }
}
