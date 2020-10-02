package ingresos.entidades;

public class PlanAireFreno {

    private String identificacion;
    private String visual;
    private String lote;
    private String rosca;
    private String identificacionId;
    private String visualId;
    private String loteId;
    private String roscaId;
    private String IdDetalle;
    
    public PlanAireFreno(String identificacion, String visual, String lote, String rosca) throws Exception {
        setIdentificacion(identificacion);
        setVisual(visual);
        setLote(lote);
        setRosca(rosca);
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

    public String getVisual() {
        return visual;
    }

    public void setVisual(String visual) throws Exception {
        if (visual == null || visual.isEmpty()) {
            throw new Exception("Aspecto visual no puede estar vacio.");
        } else {
            this.visual = visual;
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

    public String getIdentificacionId() {
        return identificacionId;
    }

    public void setIdentificacionId(String identificacionId) {
        this.identificacionId = identificacionId;
    }

    public String getVisualId() {
        return visualId;
    }

    public void setVisualId(String visualId) {
        this.visualId = visualId;
    }

    public String getLoteId() {
        return loteId;
    }

    public void setLoteId(String loteId) {
        this.loteId = loteId;
    }

    public String getRoscaId() {
        return roscaId;
    }

    public void setRoscaId(String roscaId) {
        this.roscaId = roscaId;
    }

    public String getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(String IdDetalle) {
        this.IdDetalle = IdDetalle;
    }

    

}
