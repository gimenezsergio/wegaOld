package ingresos.entidades;

import java.util.Map;

public class PlanAireRedondo {

    private String identificacion;
    private String visual;
    private String lote;
    private String diametroExteriorTapaSuperior;
    private String diametroInteriorTapaSuperior;
    private String diametroExteriorBaseInferior;
    private String diametroInteriorBaseInferior;
    private String alturaTotal;
    private String diametroExteriorJuntaSello;
    private String diametroInteriorJuntaSello;
    private String alturaJuntaSello;
    private String elementoFiltrante;
    private String identificacionId;
    private String visualId;
    private String loteId;
    private String diametroExteriorTapaSuperiorId;
    private String diametroInteriorTapaSuperiorId;
    private String diametroExteriorBaseInferiorId;
    private String diametroInteriorBaseInferiorId;
    private String alturaTotalId;
    private String diametroExteriorJuntaSelloId;
    private String diametroInteriorJuntaSelloId;
    private String alturaJuntaSelloId;
    private String elementoFiltranteId;
    private Map medidaDiametroExteriorTapaSuperior;
    private Map medidaDiametroInteriorTapaSuperior;
    private Map medidaDiametroExteriorBaseInferior;
    private Map medidaDiametroInteriorBaseInferior;
    private Map medidaAlturaTotal;
    private Map medidaDiametroExteriorJuntaSello;
    private Map medidaDiametroInteriorJuntaSello;
    private Map medidaAlturaJuntaSello;
    private String IdDetalle;

    public PlanAireRedondo(
            String identificacion,
            String visual,
            String lote,
            String diametroExteriorTapaSuperior,
            String diametroInteriorTapaSuperior,
            String diametroExteriorBaseInferior,
            String diametroInteriorBaseInferior,
            String alturaTotal,
            String diametroExteriorJuntaSello,
            String diametroInteriorJuntaSello,
            String alturaJuntaSello,
            String elementoFiltrante,
            Map medidaDiametroExteriorTapaSuperior,
            Map medidaDiametroInteriorTapaSuperior,
            Map medidaDiametroExteriorBaseInferior,
            Map medidaDiametroInteriorBaseInferior,
            Map medidaAlturaTotal,
            Map medidaDiametroExteriorJuntaSello,
            Map medidaDiametroInteriorJuntaSello,
            Map medidaAlturaJuntaSello) throws Exception {
        setIdentificacion(identificacion);
        setVisual(visual);
        setLote(lote);
        setDiametroExteriorTapaSuperior(diametroExteriorTapaSuperior);
        setDiametroInteriorTapaSuperior(diametroInteriorTapaSuperior);
        setDiametroExteriorBaseInferior(diametroExteriorBaseInferior);
        setDiametroInteriorBaseInferior(diametroInteriorBaseInferior);
        setAlturaTotal(alturaTotal);
        setDiametroExteriorJuntaSello(diametroExteriorJuntaSello);
        setDiametroInteriorJuntaSello(diametroInteriorJuntaSello);
        setAlturaJuntaSello(alturaJuntaSello);
        setElementoFiltrante(elementoFiltrante);
        setMedidaDiametroExteriorTapaSuperior(medidaDiametroExteriorTapaSuperior);
        setMedidaDiametroInteriorTapaSuperior(medidaDiametroInteriorTapaSuperior);
        setMedidaDiametroExteriorBaseInferior(medidaDiametroExteriorBaseInferior);
        setMedidaDiametroInteriorBaseInferior(medidaDiametroInteriorBaseInferior);
        setMedidaAlturaTotal(medidaAlturaTotal);
        setMedidaDiametroExteriorJuntaSello(medidaDiametroExteriorJuntaSello);
        setMedidaDiametroInteriorJuntaSello(medidaDiametroInteriorJuntaSello);
        setMedidaAlturaJuntaSello(medidaAlturaJuntaSello);
        
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

    public String getDiametroExteriorTapaSuperior() {
        return diametroExteriorTapaSuperior;
    }

    public void setDiametroExteriorTapaSuperior(String diametroExteriorTapaSuperior) throws Exception {
        if (diametroExteriorTapaSuperior == null || diametroExteriorTapaSuperior.isEmpty()) {
            throw new Exception("Diametro exterior de tapa superior no puede estar vacio.");
        } else {
            this.diametroExteriorTapaSuperior = diametroExteriorTapaSuperior;
        }

    }

    public String getDiametroInteriorTapaSuperior() {
        return diametroInteriorTapaSuperior;
    }

    public void setDiametroInteriorTapaSuperior(String diametroInteriorTapaSuperior) throws Exception {
        if (diametroInteriorTapaSuperior == null || diametroInteriorTapaSuperior.isEmpty()) {
            throw new Exception("Diametro Interior Tapa Superior no puede estar vacio.");
        } else {
            this.diametroInteriorTapaSuperior = diametroInteriorTapaSuperior;
        }
    }

    public String getDiametroExteriorBaseInferior() {
        return diametroExteriorBaseInferior;
    }

    public void setDiametroExteriorBaseInferior(String diametroExteriorBaseInferior) throws Exception {
        if (diametroExteriorBaseInferior == null || diametroExteriorBaseInferior.isEmpty()) {
            throw new Exception("Diametro Exterior Base Inferior no puede estar vacio.");
        } else {
            this.diametroExteriorBaseInferior = diametroExteriorBaseInferior;
        }
    }

    public String getDiametroInteriorBaseInferior() {
        return diametroInteriorBaseInferior;
    }

    public void setDiametroInteriorBaseInferior(String diametroInteriorBaseInferior) throws Exception {
        if (diametroInteriorBaseInferior == null || diametroInteriorBaseInferior.isEmpty()) {
            throw new Exception("Diametro Interior Base Inferior no puede estar vacio.");
        } else {
            this.diametroInteriorBaseInferior = diametroInteriorBaseInferior;
        }
    }

    public String getAlturaTotal() {
        return alturaTotal;
    }

    public void setAlturaTotal(String alturaTotal) throws Exception {
        if (alturaTotal == null || alturaTotal.isEmpty()) {
            throw new Exception("Altura Total no puede estar vacio.");
        } else {
            this.alturaTotal = alturaTotal;
        }
    }

    public String getDiametroExteriorJuntaSello() {
        return diametroExteriorJuntaSello;
    }

    public void setDiametroExteriorJuntaSello(String diametroExteriorJuntaSello) throws Exception {
        if (diametroExteriorJuntaSello == null || diametroExteriorJuntaSello.isEmpty()) {
            throw new Exception("Diametro Exterior Junta Sello no puede estar vacio.");
        } else {
            this.diametroExteriorJuntaSello = diametroExteriorJuntaSello;
        }
    }

    public String getDiametroInteriorJuntaSello() {
        return diametroInteriorJuntaSello;
    }

    public void setDiametroInteriorJuntaSello(String diametroInteriorJuntaSello) throws Exception {
        if (diametroInteriorJuntaSello == null || diametroInteriorJuntaSello.isEmpty()) {
            throw new Exception("Diametro Interior Junta Sello no puede estar vacio.");
        } else {
            this.diametroInteriorJuntaSello = diametroInteriorJuntaSello;
        }
    }

    public String getAlturaJuntaSello() {
        return alturaJuntaSello;
    }

    public void setAlturaJuntaSello(String alturaJuntaSello) throws Exception {
        if (alturaJuntaSello == null || alturaJuntaSello.isEmpty()) {
            throw new Exception("Altura Junta Sello no puede estar vacio.");
        } else {
            this.alturaJuntaSello = alturaJuntaSello;
        }
    }

    public String getElementoFiltrante() {
        return elementoFiltrante;
    }

    public void setElementoFiltrante(String elementoFiltrante) throws Exception {
        if (elementoFiltrante == null || elementoFiltrante.isEmpty()) {
            throw new Exception("Elemento Filtrante no puede estar vacio.");
        } else {
            this.elementoFiltrante = elementoFiltrante;
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

    public String getDiametroExteriorTapaSuperiorId() {
        return diametroExteriorTapaSuperiorId;
    }

    public void setDiametroExteriorTapaSuperiorId(String diametroExteriorTapaSuperiorId) {
        this.diametroExteriorTapaSuperiorId = diametroExteriorTapaSuperiorId;
    }

    public String getDiametroInteriorTapaSuperiorId() {
        return diametroInteriorTapaSuperiorId;
    }

    public void setDiametroInteriorTapaSuperiorId(String diametroInteriorTapaSuperiorId) {
        this.diametroInteriorTapaSuperiorId = diametroInteriorTapaSuperiorId;
    }

    public String getDiametroExteriorBaseInferiorId() {
        return diametroExteriorBaseInferiorId;
    }

    public void setDiametroExteriorBaseInferiorId(String diametroExteriorBaseInferiorId) {
        this.diametroExteriorBaseInferiorId = diametroExteriorBaseInferiorId;
    }

    public String getDiametroInteriorBaseInferiorId() {
        return diametroInteriorBaseInferiorId;
    }

    public void setDiametroInteriorBaseInferiorId(String diametroInteriorBaseInferiorId) {
        this.diametroInteriorBaseInferiorId = diametroInteriorBaseInferiorId;
    }

    public String getAlturaTotalId() {
        return alturaTotalId;
    }

    public void setAlturaTotalId(String alturaTotalId) {
        this.alturaTotalId = alturaTotalId;
    }

    public String getDiametroExteriorJuntaSelloId() {
        return diametroExteriorJuntaSelloId;
    }

    public void setDiametroExteriorJuntaSelloId(String diametroExteriorJuntaSelloId) {
        this.diametroExteriorJuntaSelloId = diametroExteriorJuntaSelloId;
    }

    public String getDiametroInteriorJuntaSelloId() {
        return diametroInteriorJuntaSelloId;
    }

    public void setDiametroInteriorJuntaSelloId(String diametroInteriorJuntaSelloId) {
        this.diametroInteriorJuntaSelloId = diametroInteriorJuntaSelloId;
    }

    public String getAlturaJuntaSelloId() {
        return alturaJuntaSelloId;
    }

    public void setAlturaJuntaSelloId(String alturaJuntaSelloId) {
        this.alturaJuntaSelloId = alturaJuntaSelloId;
    }

    public String getElementoFiltranteId() {
        return elementoFiltranteId;
    }

    public void setElementoFiltranteId(String elementoFiltranteId) {
        this.elementoFiltranteId = elementoFiltranteId;
    }

    public Map getMedidaDiametroExteriorTapaSuperior() {
        return medidaDiametroExteriorTapaSuperior;
    }

    public void setMedidaDiametroExteriorTapaSuperior(Map medidaDiametroExteriorTapaSuperior) throws Exception {
        String nom = (String) medidaDiametroExteriorTapaSuperior.get("diametro_ext_tapa_sup_filtro_aire_redondos_Nom");
        String min = (String) medidaDiametroExteriorTapaSuperior.get("diametro_ext_tapa_sup_filtro_aire_redondos_Min");
        String max = (String) medidaDiametroExteriorTapaSuperior.get("diametro_ext_tapa_sup_filtro_aire_redondos_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Diametro Exterior Tapa Superior no pueden estar vacias.");
        } else {
            this.medidaDiametroExteriorTapaSuperior = medidaDiametroExteriorTapaSuperior;
        }
    }

    public Map getMedidaDiametroInteriorTapaSuperior() {
        return medidaDiametroInteriorTapaSuperior;
    }

    public void setMedidaDiametroInteriorTapaSuperior(Map medidaDiametroInteriorTapaSuperior) throws Exception {
        String nom = (String) medidaDiametroInteriorTapaSuperior.get("diametro_int_tapa_sup_filtro_aire_redondos_Nom");
        String min = (String) medidaDiametroInteriorTapaSuperior.get("diametro_int_tapa_sup_filtro_aire_redondos_Min");
        String max = (String) medidaDiametroInteriorTapaSuperior.get("diametro_int_tapa_sup_filtro_aire_redondos_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Diametro Interior Tapa Superior no pueden estar vacias.");
        } else {
            this.medidaDiametroInteriorTapaSuperior = medidaDiametroInteriorTapaSuperior;
        }
    }

    public Map getMedidaDiametroExteriorBaseInferior() {
        return medidaDiametroExteriorBaseInferior;
    }

    public void setMedidaDiametroExteriorBaseInferior(Map medidaDiametroExteriorBaseInferior) throws Exception {
        String nom = (String) medidaDiametroExteriorBaseInferior.get("diametro_ext_base_inf_filtro_aire_redondos_Nom");
        String min = (String) medidaDiametroExteriorBaseInferior.get("diametro_ext_base_inf_filtro_aire_redondos_Min");
        String max = (String) medidaDiametroExteriorBaseInferior.get("diametro_ext_base_inf_filtro_aire_redondos_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Diametro Exteior Base Inferior no pueden estar vacias.");
        } else {
            this.medidaDiametroExteriorBaseInferior = medidaDiametroExteriorBaseInferior;
        }
    }

    public Map getMedidaDiametroInteriorBaseInferior() {
        return medidaDiametroInteriorBaseInferior;
    }

    public void setMedidaDiametroInteriorBaseInferior(Map medidaDiametroInteriorBaseInferior) throws Exception {
        String nom = (String) medidaDiametroInteriorBaseInferior.get("diametro_int_base_inf_filtro_aire_redondos_Nom");
        String min = (String) medidaDiametroInteriorBaseInferior.get("diametro_int_base_inf_filtro_aire_redondos_Min");
        String max = (String) medidaDiametroInteriorBaseInferior.get("diametro_int_base_inf_filtro_aire_redondos_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Diametro Interior Base Inferior no pueden estar vacias.");
        } else {
            this.medidaDiametroInteriorBaseInferior = medidaDiametroInteriorBaseInferior;
        }
    }

    public Map getMedidaAlturaTotal() {
        return medidaAlturaTotal;
    }

    public void setMedidaAlturaTotal(Map medidaAlturaTotal) throws Exception {
        String nom = (String) medidaAlturaTotal.get("altura_total_filtro_aire_redondos_Nom");
        String min = (String) medidaAlturaTotal.get("altura_total_filtro_aire_redondos_Min");
        String max = (String) medidaAlturaTotal.get("altura_total_filtro_aire_redondos_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Altura Total no pueden estar vacias.");
        } else {
            this.medidaAlturaTotal = medidaAlturaTotal;
        }
    }

    public Map getMedidaDiametroExteriorJuntaSello() {
        return medidaDiametroExteriorJuntaSello;
    }

    public void setMedidaDiametroExteriorJuntaSello(Map medidaDiametroExteriorJuntaSello) throws Exception {
        String nom = (String) medidaDiametroExteriorJuntaSello.get("diametro_ext_junta_sello_filtro_aire_redondos_Nom");
        String min = (String) medidaDiametroExteriorJuntaSello.get("diametro_ext_junta_sello_filtro_aire_redondos_Min");
        String max = (String) medidaDiametroExteriorJuntaSello.get("diametro_ext_junta_sello_filtro_aire_redondos_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Diametro Exterior junta Sello no pueden estar vacias.");
        } else {
            this.medidaDiametroExteriorJuntaSello = medidaDiametroExteriorJuntaSello;
        }
    }

    public Map getMedidaDiametroInteriorJuntaSello() {
        return medidaDiametroInteriorJuntaSello;
    }

    public void setMedidaDiametroInteriorJuntaSello(Map medidaDiametroInteriorJuntaSello) throws Exception {
        String nom = (String) medidaDiametroInteriorJuntaSello.get("diametro_int_junta_sello_filtro_aire_redondos_Nom");
        String min = (String) medidaDiametroInteriorJuntaSello.get("diametro_int_junta_sello_filtro_aire_redondos_Min");
        String max = (String) medidaDiametroInteriorJuntaSello.get("diametro_int_junta_sello_filtro_aire_redondos_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Diametro Interior Junat Sello no pueden estar vacias.");
        } else {
            this.medidaDiametroInteriorJuntaSello = medidaDiametroInteriorJuntaSello;
        }
    }

    public Map getMedidaAlturaJuntaSello() {
        return medidaAlturaJuntaSello;
    }

    public void setMedidaAlturaJuntaSello(Map medidaAlturaJuntaSello) throws Exception {
        String nom = (String) medidaAlturaJuntaSello.get("altura_junta_sello_filtro_aire_redondos_Nom");
        String min = (String) medidaAlturaJuntaSello.get("altura_junta_sello_filtro_aire_redondos_Min");
        String max = (String) medidaAlturaJuntaSello.get("altura_junta_sello_filtro_aire_redondos_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Altura Junta Sello no pueden estar vacias.");
        } else {
            this.medidaAlturaJuntaSello = medidaAlturaJuntaSello;
        }
    }

    public String getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(String IdDetalle) {
        this.IdDetalle = IdDetalle;
    }

}
