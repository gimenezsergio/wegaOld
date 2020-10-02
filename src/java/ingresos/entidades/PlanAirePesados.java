package ingresos.entidades;

import java.util.Map;

public class PlanAirePesados {

    private String identificacion;
    private String visual;
    private String lote;
    private String diametroExteriorTapaSuperior;
    private String diametroAgujeroTapaSuperior;
    private String diametroExteriorBaseInferior;
    private String diametroAgujeroBaseInferior;
    private String alturaTotal;
    private String diametroExteriorJuntaSello;
    private String diametroInteriorJuntaSello;
    private String alturaJuntaSello;
    private String identificacionId;
    private String visualId;
    private String loteId;
    private String diametroExteriorTapaSuperiorId;
    private String diametroAgujeroTapaSuperiorId;
    private String diametroExteriorBaseInferiorId;
    private String diametroAgujeroBaseInferiorId;
    private String alturaTotalId;
    private String diametroExteriorJuntaSelloId;
    private String diametroInteriorJuntaSelloId;
    private String alturaJuntaSelloId;
    private Map MedidadiametroExteriorTapaSuperior;
    private Map medidaDiametroAgujeroTapaSuperior;
    private Map MedidaDiametroExteriorBaseInferior;
    private Map MedidaDiametroAgujeroBaseInferior;
    private Map MedidaAlturaTotal;
    private Map MedidaDiametroExteriorJuntaSello;
    private Map MedidaDiametroInteriorJuntaSello;
    private Map MedidaAlturaJuntaSello;
    private String IdDetalle;

    public PlanAirePesados(
            String identificacion,
            String visual,
            String lote,
            String diametroExteriorTapaSuperior,
            String diametroAgujeroTapaSuperior,
            String diametroExteriorBaseInferior,
            String diametroAgujeroBaseInferior,
            String alturaTotal,
            String diametroExteriorJuntaSello,
            String diametroInteriorJuntaSello,
            String alturaJuntaSello,
            Map MedidadiametroExteriorTapaSuperior,
            Map medidaDiametroAgujeroTapaSuperior,
            Map MedidaDiametroExteriorBaseInferior,
            Map MedidaDiametroAgujeroBaseInferior,
            Map MedidaAlturaTotal,
            Map MedidaDiametroExteriorJuntaSello,
            Map MedidaDiametroInteriorJuntaSello,
            Map MedidaAlturaJuntaSello) throws Exception {
        setIdentificacion(identificacion);
        setVisual(visual);
        setLote(lote);
        setDiametroExteriorTapaSuperior(diametroExteriorTapaSuperior);
        setDiametroAgujeroTapaSuperior(diametroAgujeroTapaSuperior);
        setDiametroExteriorBaseInferior(diametroExteriorBaseInferior);
        setDiametroAgujeroBaseInferior(diametroAgujeroBaseInferior);
        setAlturaTotal(alturaTotal);
        setDiametroExteriorJuntaSello(diametroExteriorJuntaSello);
        setDiametroInteriorJuntaSello(diametroInteriorJuntaSello);
        setAlturaJuntaSello(alturaJuntaSello);
        setMedidadiametroExteriorTapaSuperior(MedidadiametroExteriorTapaSuperior);
        setMedidaDiametroAgujeroTapaSuperior(medidaDiametroAgujeroTapaSuperior);
        setMedidaDiametroExteriorBaseInferior(MedidaDiametroExteriorBaseInferior);
        setMedidaDiametroAgujeroBaseInferior(MedidaDiametroAgujeroBaseInferior);
        setMedidaAlturaTotal(MedidaAlturaTotal);
        setMedidaDiametroExteriorJuntaSello(MedidaDiametroExteriorJuntaSello);
        setMedidaDiametroInteriorJuntaSello(MedidaDiametroInteriorJuntaSello);
        setMedidaAlturaJuntaSello(MedidaAlturaJuntaSello);
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
            throw new Exception("Diametro Exterior Tapa Superior no puede estar vacio.");
        } else {
            this.diametroExteriorTapaSuperior = diametroExteriorTapaSuperior;
        }

    }

    public String getDiametroAgujeroTapaSuperior() {
        return diametroAgujeroTapaSuperior;
    }

    public void setDiametroAgujeroTapaSuperior(String diametroAgujeroTapaSuperior) throws Exception {
        if (diametroAgujeroTapaSuperior == null || diametroAgujeroTapaSuperior.isEmpty()) {
            throw new Exception("Diametro Agujero Tapa Superior no puede estar vacio.");
        } else {
            this.diametroAgujeroTapaSuperior = diametroAgujeroTapaSuperior;
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

    public String getDiametroAgujeroBaseInferior() {
        return diametroAgujeroBaseInferior;
    }

    public void setDiametroAgujeroBaseInferior(String diametroAgujeroBaseInferior) throws Exception {
        if (diametroAgujeroBaseInferior == null || diametroAgujeroBaseInferior.isEmpty()) {
            throw new Exception("Diametro Agujero Base Inferior no puede estar vacio.");
        } else {
            this.diametroAgujeroBaseInferior = diametroAgujeroBaseInferior;
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

    public String getDiametroAgujeroTapaSuperiorId() {
        return diametroAgujeroTapaSuperiorId;
    }

    public void setDiametroAgujeroTapaSuperiorId(String diametroAgujeroTapaSuperiorId) {
        this.diametroAgujeroTapaSuperiorId = diametroAgujeroTapaSuperiorId;
    }

    public String getDiametroExteriorBaseInferiorId() {
        return diametroExteriorBaseInferiorId;
    }

    public void setDiametroExteriorBaseInferiorId(String diametroExteriorBaseInferiorId) {
        this.diametroExteriorBaseInferiorId = diametroExteriorBaseInferiorId;
    }

    public String getDiametroAgujeroBaseInferiorId() {
        return diametroAgujeroBaseInferiorId;
    }

    public void setDiametroAgujeroBaseInferiorId(String diametroAgujeroBaseInferiorId) {
        this.diametroAgujeroBaseInferiorId = diametroAgujeroBaseInferiorId;
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

    public Map getMedidadiametroExteriorTapaSuperior() {
        return MedidadiametroExteriorTapaSuperior;
    }

    public void setMedidadiametroExteriorTapaSuperior(Map MedidadiametroExteriorTapaSuperior) throws Exception {
        String nom = (String) MedidadiametroExteriorTapaSuperior.get("diametro_ext_tapa_sup_filtro_aire_pesados_Nom");
        String min = (String) MedidadiametroExteriorTapaSuperior.get("diametro_ext_tapa_sup_filtro_aire_pesados_Min");
        String max = (String) MedidadiametroExteriorTapaSuperior.get("diametro_ext_tapa_sup_filtro_aire_pesados_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Diametro Exterior Tapa Superior no pueden estar vacias.");
        } else {
            this.MedidadiametroExteriorTapaSuperior = MedidadiametroExteriorTapaSuperior;
        }
    }

    public Map getMedidaDiametroAgujeroTapaSuperior() {
        return medidaDiametroAgujeroTapaSuperior;
    }

    public void setMedidaDiametroAgujeroTapaSuperior(Map medidaDiametroAgujeroTapaSuperior) throws Exception {
        String nom = (String) medidaDiametroAgujeroTapaSuperior.get("diametro_agujero_tapa_superior_filtro_aire_pesados_Nom");
        String min = (String) medidaDiametroAgujeroTapaSuperior.get("diametro_agujero_tapa_superior_filtro_aire_pesados_Min");
        String max = (String) medidaDiametroAgujeroTapaSuperior.get("diametro_agujero_tapa_superior_filtro_aire_pesados_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Diametro Agujero Tapa Superior no pueden estar vacias.");
        } else {
            this.medidaDiametroAgujeroTapaSuperior = medidaDiametroAgujeroTapaSuperior;
        }
    }

    public Map getMedidaDiametroExteriorBaseInferior() {
        return MedidaDiametroExteriorBaseInferior;
    }

    public void setMedidaDiametroExteriorBaseInferior(Map MedidaDiametroExteriorBaseInferior) throws Exception {
        String nom = (String) MedidaDiametroExteriorBaseInferior.get("diametro_ext_base_inf_filtro_aire_pesados_Nom");
        String min = (String) MedidaDiametroExteriorBaseInferior.get("diametro_ext_base_inf_filtro_aire_pesados_Min");
        String max = (String) MedidaDiametroExteriorBaseInferior.get("diametro_ext_base_inf_filtro_aire_pesados_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Diametro Exterior Base Inferior no pueden estar vacias.");
        } else {
            this.MedidaDiametroExteriorBaseInferior = MedidaDiametroExteriorBaseInferior;
        }
    }

    public Map getMedidaDiametroAgujeroBaseInferior() {
        return MedidaDiametroAgujeroBaseInferior;
    }

    public void setMedidaDiametroAgujeroBaseInferior(Map MedidaDiametroAgujeroBaseInferior) throws Exception {
        String nom = (String) MedidaDiametroAgujeroBaseInferior.get("diametro_agujero_base_inf_filtro_aire_pesados_Nom");
        String min = (String) MedidaDiametroAgujeroBaseInferior.get("diametro_agujero_base_inf_filtro_aire_pesados_Min");
        String max = (String) MedidaDiametroAgujeroBaseInferior.get("diametro_agujero_base_inf_filtro_aire_pesados_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Diametro Agujero Base Inferior no pueden estar vacias.");
        } else {
            this.MedidaDiametroAgujeroBaseInferior = MedidaDiametroAgujeroBaseInferior;
        }
    }

    public Map getMedidaAlturaTotal() {
        return MedidaAlturaTotal;
    }

    public void setMedidaAlturaTotal(Map MedidaAlturaTotal) throws Exception {
        String nom = (String) MedidaAlturaTotal.get("altura_total_filtro_aire_pesados_Nom");
        String min = (String) MedidaAlturaTotal.get("altura_total_filtro_aire_pesados_Min");
        String max = (String) MedidaAlturaTotal.get("altura_total_filtro_aire_pesados_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Altura Total no pueden estar vacias.");
        } else {
            this.MedidaAlturaTotal = MedidaAlturaTotal;
        }
    }

    public Map getMedidaDiametroExteriorJuntaSello() {
        return MedidaDiametroExteriorJuntaSello;
    }

    public void setMedidaDiametroExteriorJuntaSello(Map MedidaDiametroExteriorJuntaSello) throws Exception {
        String nom = (String) MedidaDiametroExteriorJuntaSello.get("diametro_ext_junta_sello_filtro_aire_pesados_Nom");
        String min = (String) MedidaDiametroExteriorJuntaSello.get("diametro_ext_junta_sello_filtro_aire_pesados_Min");
        String max = (String) MedidaDiametroExteriorJuntaSello.get("diametro_ext_junta_sello_filtro_aire_pesados_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Diametro Exterior Junta Sello no pueden estar vacias.");
        } else {
            this.MedidaDiametroExteriorJuntaSello = MedidaDiametroExteriorJuntaSello;
        }
    }

    public Map getMedidaDiametroInteriorJuntaSello() {
        return MedidaDiametroInteriorJuntaSello;
    }

    public void setMedidaDiametroInteriorJuntaSello(Map MedidaDiametroInteriorJuntaSello) throws Exception {
        String nom = (String) MedidaDiametroInteriorJuntaSello.get("diametro_int_junta_sello_filtro_aire_pesados_Nom");
        String min = (String) MedidaDiametroInteriorJuntaSello.get("diametro_int_junta_sello_filtro_aire_pesados_Min");
        String max = (String) MedidaDiametroInteriorJuntaSello.get("diametro_int_junta_sello_filtro_aire_pesados_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Diametro Interior Junta Sello no pueden estar vacias.");
        } else {
            this.MedidaDiametroInteriorJuntaSello = MedidaDiametroInteriorJuntaSello;
        }
    }

    public Map getMedidaAlturaJuntaSello() {
        return MedidaAlturaJuntaSello;
    }

    public void setMedidaAlturaJuntaSello(Map MedidaAlturaJuntaSello) throws Exception {
        String nom = (String) MedidaAlturaJuntaSello.get("altura_junta_sello_filtro_aire_pesados_Nom");
        String min = (String) MedidaAlturaJuntaSello.get("altura_junta_sello_filtro_aire_pesados_Min");
        String max = (String) MedidaAlturaJuntaSello.get("altura_junta_sello_filtro_aire_pesados_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de Altura Junta Sello no pueden estar vacias.");
        } else {
            this.MedidaAlturaJuntaSello = MedidaAlturaJuntaSello;
        }
    }

    public String getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(String IdDetalle) {
        this.IdDetalle = IdDetalle;
    }

}
