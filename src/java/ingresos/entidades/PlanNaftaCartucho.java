package ingresos.entidades;

import java.util.Map;

public class PlanNaftaCartucho {

    private String identificacion;
    private String visual;
    private String lote;
    private String diametroExternoTapaSuperior;
    private String diametroInternoTapaSuperior;
    private String diametroExternoTapaInferior;
    private String diametroInternoTapaInferior;
    private String alturaTotalFiltro;
    private Map MedidadDametroExternoTapaSuperior;
    private Map MedidaDiametroInternoTapaSuperior;
    private Map MedidaDiametroExternoTapaInferior;
    private Map MedidaDiametroInternoTapaInferior;
    private Map MedidaAlturaTotalFiltro;
    private String identificacionId;
    private String visualId;
    private String loteId;
    private String diametroExternoTapaSuperiorId;
    private String diametroInternoTapaSuperiorId;
    private String diametroExternoTapaInferiorId;
    private String diametroInternoTapaInferiorId;
    private String alturaTotalFiltroId;
    private String IdDetalle;

    public PlanNaftaCartucho(
            String identificacion,
            String visual,
            String lote,
            String diametroExternoTapaSuperior,
            String diametroInternoTapaSuperior,
            String diametroExternoTapaInferior,
            String diametroInternoTapaInferior,
            String alturaTotalFiltro,
            Map MedidadDametroExternoTapaSuperior,
            Map MedidaDiametroInternoTapaSuperior,
            Map MedidaDiametroExternoTapaInferior,
            Map MedidaDiametroInternoTapaInferior,
            Map MedidaAlturaTotalFiltro
    ) throws Exception {
        setIdentificacion(identificacion);
        setVisual(visual);
        setLote(lote);
        setDiametroExternoTapaSuperior(diametroExternoTapaSuperior);
        setDiametroInternoTapaSuperior(diametroInternoTapaSuperior);
        setDiametroExternoTapaInferior(diametroExternoTapaInferior);
        setDiametroInternoTapaInferior(diametroInternoTapaInferior);
        setAlturaTotalFiltro(alturaTotalFiltro);
        setMedidadDametroExternoTapaSuperior(MedidadDametroExternoTapaSuperior);
        setMedidaDiametroInternoTapaSuperior(MedidaDiametroInternoTapaSuperior);
        setMedidaDiametroExternoTapaInferior(MedidaDiametroExternoTapaInferior);
        setMedidaDiametroInternoTapaInferior(MedidaDiametroInternoTapaInferior);
        setMedidaAlturaTotalFiltro(MedidaAlturaTotalFiltro);
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

    public String getDiametroExternoTapaSuperior() {
        return diametroExternoTapaSuperior;
    }

    public void setDiametroExternoTapaSuperior(String diametroExternoTapaSuperior) throws Exception {
        if (diametroExternoTapaSuperior == null || diametroExternoTapaSuperior.isEmpty()) {
            throw new Exception("Diametro Externo Tapa Superior no puede estar vacio.");
        } else {
            this.diametroExternoTapaSuperior = diametroExternoTapaSuperior;
        }
    }

    public String getDiametroInternoTapaSuperior() {
        return diametroInternoTapaSuperior;
    }

    public void setDiametroInternoTapaSuperior(String diametroInternoTapaSuperior) throws Exception {
        if (diametroInternoTapaSuperior == null || diametroInternoTapaSuperior.isEmpty()) {
            throw new Exception("Diametro Interno Tapa Superior no puede estar vacio.");
        } else {
            this.diametroInternoTapaSuperior = diametroInternoTapaSuperior;
        }
    }

    public String getDiametroExternoTapaInferior() {
        return diametroExternoTapaInferior;
    }

    public void setDiametroExternoTapaInferior(String diametroExternoTapaInferior) throws Exception {
        if (diametroExternoTapaInferior == null || diametroExternoTapaInferior.isEmpty()) {
            throw new Exception("Diametro Externo Tapa Inferior no puede estar vacio.");
        } else {
            this.diametroExternoTapaInferior = diametroExternoTapaInferior;
        }
    }

    public String getDiametroInternoTapaInferior() {
        return diametroInternoTapaInferior;
    }

    public void setDiametroInternoTapaInferior(String diametroInternoTapaInferior) throws Exception {
        if (diametroInternoTapaInferior == null || diametroInternoTapaInferior.isEmpty()) {
            throw new Exception("Diametro Interno Tapa Inferior no puede estar vacio.");
        } else {
            this.diametroInternoTapaInferior = diametroInternoTapaInferior;
        }
    }

    public String getAlturaTotalFiltro() {
        return alturaTotalFiltro;
    }

    public void setAlturaTotalFiltro(String alturaTotalFiltro) throws Exception {
        if (alturaTotalFiltro == null || alturaTotalFiltro.isEmpty()) {
            throw new Exception("Altura Total Filtro no puede estar vacio.");
        } else {
            this.alturaTotalFiltro = alturaTotalFiltro;
        }
    }

    public Map getMedidadDametroExternoTapaSuperior() {
        return MedidadDametroExternoTapaSuperior;
    }

    public void setMedidadDametroExternoTapaSuperior(Map MedidadDametroExternoTapaSuperior) throws Exception {
        String nom = (String) MedidadDametroExternoTapaSuperior.get("diametro_ext_tapa_sup_nafta_cartucho_Nom");
        String min = (String) MedidadDametroExternoTapaSuperior.get("diametro_ext_tapa_sup_nafta_cartucho_Min");
        String max = (String) MedidadDametroExternoTapaSuperior.get("diametro_ext_tapa_sup_nafta_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Diametro Externo Tapa Superior no pueden estar vacias.");
        } else {
            this.MedidadDametroExternoTapaSuperior = MedidadDametroExternoTapaSuperior;
        }
    }

    public Map getMedidaDiametroInternoTapaSuperior() {
        return MedidaDiametroInternoTapaSuperior;
    }

    public void setMedidaDiametroInternoTapaSuperior(Map MedidaDiametroInternoTapaSuperior) throws Exception {
        String nom = (String) MedidaDiametroInternoTapaSuperior.get("diametro_int_tapa_superior_nafta_cartucho_Nom");
        String min = (String) MedidaDiametroInternoTapaSuperior.get("diametro_int_tapa_superior_nafta_cartucho_Min");
        String max = (String) MedidaDiametroInternoTapaSuperior.get("diametro_int_tapa_superior_nafta_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Diametro Interno Tapa Superior no pueden estar vacias.");
        } else {
            this.MedidaDiametroInternoTapaSuperior = MedidaDiametroInternoTapaSuperior;
        }
    }

    public Map getMedidaDiametroExternoTapaInferior() {
        return MedidaDiametroExternoTapaInferior;
    }

    public void setMedidaDiametroExternoTapaInferior(Map MedidaDiametroExternoTapaInferior) throws Exception {
        String nom = (String) MedidaDiametroExternoTapaInferior.get("diametro_ext_tapa_inf_nafta_cartucho_Nom");
        String min = (String) MedidaDiametroExternoTapaInferior.get("diametro_ext_tapa_inf_nafta_cartucho_Min");
        String max = (String) MedidaDiametroExternoTapaInferior.get("diametro_ext_tapa_inf_nafta_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Diametro Externo Tapa Inferior no pueden estar vacias.");
        } else {
            this.MedidaDiametroExternoTapaInferior = MedidaDiametroExternoTapaInferior;
        }
    }

    public Map getMedidaDiametroInternoTapaInferior() {
        return MedidaDiametroInternoTapaInferior;
    }

    public void setMedidaDiametroInternoTapaInferior(Map MedidaDiametroInternoTapaInferior) throws Exception {
        String nom = (String) MedidaDiametroInternoTapaInferior.get("diametro_int_tapa_inf_nafta_cartucho_Nom");
        String min = (String) MedidaDiametroInternoTapaInferior.get("diametro_int_tapa_inf_nafta_cartucho_Min");
        String max = (String) MedidaDiametroInternoTapaInferior.get("diametro_int_tapa_inf_nafta_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Diametro Interno Tapa Inferior no pueden estar vacias.");
        } else {
            this.MedidaDiametroInternoTapaInferior = MedidaDiametroInternoTapaInferior;
        }
    }

    public Map getMedidaAlturaTotalFiltro() {
        return MedidaAlturaTotalFiltro;
    }

    public void setMedidaAlturaTotalFiltro(Map MedidaAlturaTotalFiltro) throws Exception {
        String nom = (String) MedidaAlturaTotalFiltro.get("altura_total_nafta_cartucho_Nom");
        String min = (String) MedidaAlturaTotalFiltro.get("altura_total_nafta_cartucho_Min");
        String max = (String) MedidaAlturaTotalFiltro.get("altura_total_nafta_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Altura Total Filtro no pueden estar vacias.");
        } else {
            this.MedidaAlturaTotalFiltro = MedidaAlturaTotalFiltro;
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

    public String getDiametroExternoTapaSuperiorId() {
        return diametroExternoTapaSuperiorId;
    }

    public void setDiametroExternoTapaSuperiorId(String diametroExternoTapaSuperiorId) {
        this.diametroExternoTapaSuperiorId = diametroExternoTapaSuperiorId;
    }

    public String getDiametroInternoTapaSuperiorId() {
        return diametroInternoTapaSuperiorId;
    }

    public void setDiametroInternoTapaSuperiorId(String diametroInternoTapaSuperiorId) {
        this.diametroInternoTapaSuperiorId = diametroInternoTapaSuperiorId;
    }

    public String getDiametroExternoTapaInferiorId() {
        return diametroExternoTapaInferiorId;
    }

    public void setDiametroExternoTapaInferiorId(String diametroExternoTapaInferiorId) {
        this.diametroExternoTapaInferiorId = diametroExternoTapaInferiorId;
    }

    public String getDiametroInternoTapaInferiorId() {
        return diametroInternoTapaInferiorId;
    }

    public void setDiametroInternoTapaInferiorId(String diametroInternoTapaInferiorId) {
        this.diametroInternoTapaInferiorId = diametroInternoTapaInferiorId;
    }

    public String getAlturaTotalFiltroId() {
        return alturaTotalFiltroId;
    }

    public void setAlturaTotalFiltroId(String alturaTotalFiltroId) {
        this.alturaTotalFiltroId = alturaTotalFiltroId;
    }

    public String getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(String IdDetalle) {
        this.IdDetalle = IdDetalle;
    }
}
