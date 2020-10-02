package ingresos.entidades;

public class PlanLamparas {

    private String id;
    private String idDetalle;
    private String lote;
    private String identificacion;
    private String diseno;
    private String zocalo;
    private String tension;
    private String potencia;
    private String chas;
    private String loteId;
    private String identificacionId;
    private String disenoId;
    private String zocaloId;
    private String tensionId;
    private String potenciaId;
    private String chasId;

    public PlanLamparas() {

    }

    public PlanLamparas(String identificacion, String lote, String diseno, String zocalo, String tension, String potencia, String chas,
            String identificacionId, String loteId, String disenoId, String zocaloId, String tensionId, String potenciaId, String chasId) throws Exception {
        setIdentificacion(identificacion);
        setLote(lote);
        setDiseno(diseno);
        setZocalo(zocalo);
        setTension(tension);
        setPotencia(potencia);
        setChas(chas);
        setIdentificacionId(identificacionId);
        setLoteId(loteId);
        setDisenoId(disenoId);
        setZocaloId(zocaloId);
        setTensionId(tensionId);
        setPotenciaId(potenciaId);
        setChasId(chasId);
    }

    @Override
    public String toString() {
        return "{ " + "Identificacion: " + identificacion + ", Lote: " + lote + " }";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) throws Exception {
        if (lote == null || lote.isEmpty()) {
            throw new Exception("El lote no puede estar vacio.");
        } else {
            this.lote = lote;
        }

    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) throws Exception {
        if (identificacion == null || identificacion.isEmpty()) {
            throw new Exception("La identificacion no puede estar vacia.");
        } else {
            this.identificacion = identificacion;
        }

    }

    public String getDiseno() {
        return diseno;
    }

    public void setDiseno(String diseno) throws Exception {
        if (diseno == null || diseno.isEmpty()) {
            throw new Exception("El diseño no puede estar vacio.");
        } else {
            this.diseno = diseno;
        }

    }

    public String getZocalo() {
        return zocalo;
    }

    public void setZocalo(String zocalo) throws Exception {
        if (zocalo == null || zocalo.isEmpty()) {
            throw new Exception("El zocalo no puede estar vacio.");
        } else {
            this.zocalo = zocalo;
        }

    }

    public String getTension() {
        return tension;
    }

    public void setTension(String tension) throws Exception {
        if (tension == null || tension.isEmpty()) {
            throw new Exception("La tension no puede estar vacia.");
        } else {
            this.tension = tension;
        }

    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) throws Exception {
        if (potencia == null || potencia.isEmpty()) {
            throw new Exception("La potencia no puede estar vacio.");
        } else {
            this.potencia = potencia;
        }

    }

    public String getChas() {
        return chas;
    }

    public void setChas(String chas) throws Exception {
        if (chas == null || chas.isEmpty()) {
            throw new Exception("El CHAS no puede estar vacio.");
        } else {
            this.chas = chas;
        }

    }

    public String getLoteId() {
        return loteId;
    }

    public void setLoteId(String loteId) throws Exception {
        if (loteId == null || loteId.isEmpty()) {
            throw new Exception("El loteId no puede estar vacio.");
        } else {
            this.loteId = loteId;
        }

    }

    public String getIdentificacionId() {
        return identificacionId;
    }

    public void setIdentificacionId(String identificacionId) throws Exception {
        if (identificacionId == null || identificacionId.isEmpty()) {
            throw new Exception("El identificacionId no puede estar vacio.");
        } else {
            this.identificacionId = identificacionId;
        }

    }

    public String getDisenoId() {
        return disenoId;
    }

    public void setDisenoId(String disenoId) throws Exception {
        if (disenoId == null || disenoId.isEmpty()) {
            throw new Exception("El disenoId no puede estar vacio.");
        } else {
            this.disenoId = disenoId;
        }

    }

    public String getZocaloId() {
        return zocaloId;
    }

    public void setZocaloId(String zocaloId) throws Exception {
        if (zocaloId == null || zocaloId.isEmpty()) {
            throw new Exception("El zocaloId no puede estar vacio.");
        } else {
            this.zocaloId = zocaloId;
        }

    }

    public String getTensionId() {
        return tensionId;
    }

    public void setTensionId(String tensionId) throws Exception {
        if (tensionId == null || tensionId.isEmpty()) {
            throw new Exception("El tensionId no puede estar vacio.");
        } else {
            this.tensionId = tensionId;
        }

    }

    public String getPotenciaId() {
        return potenciaId;
    }

    public void setPotenciaId(String potenciaId) throws Exception {
        if (potenciaId == null || potenciaId.isEmpty()) {
            throw new Exception("El potenciaId no puede estar vacio.");
        } else {
            this.potenciaId = potenciaId;
        }

    }

    public String getChasId() {
        return chasId;
    }

    public void setChasId(String chasId) throws Exception {
        if (chasId == null || chasId.isEmpty()) {
            throw new Exception("El chasId no puede estar vacio.");
        } else {
            this.chasId = chasId;
        }

    }
}
