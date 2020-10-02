package ingresos.entidades;

import java.util.Map;

public class PlanDieselCartucho {

    private String identificacion;
    private String visual;
    private String lote;
    private String diametroExtTapaInf;
    private String diametroExtTapaSup;
    private String diametroIntTapaInf;
    private String diametroIntTapaSup;
    private String sello1;
    private String sello2;
    private String sello3;
    private String sello4;
    private String elementoFiltrante;
    private String identificacionId;
    private String visualId;
    private String loteId;
    private String diametroExtTapaInfId;
    private String diametroExtTapaSupId;
    private String diametroIntTapaInfId;
    private String diametroIntTapaSupId;
    private String sello1Id;
    private String sello2Id;
    private String sello3Id;
    private String sello4Id;
    private String elementoFiltranteId;
    private Map medidaDiametroExtTapaInf;
    private Map medidaDiametroExtTapaSup;

    private Map medidaDiametroIntTapaInf;
    private Map medidaDiametroIntTapaSup;
    private Map medidaSello1;
    private Map medidaSello2;
    private Map medidaSello3;
    private Map medidaSello4;

    private String IdDetalle;

    public PlanDieselCartucho(String identificacion, String visual, String lote, String diametroExtTapaInf, String diametroExtTapaSup,
            String diametroIntTapaInf, String diametroIntTapaSup, String sello1, String sello2, String sello3, String sello4, String elementoFiltrante,
            Map medidaDiametroExtTapaInf, Map medidaDiametroExtTapaSup, Map medidaDiametroIntTapaInf, Map medidaDiametroIntTapaSup, Map medidaSello1,
            Map medidaSello2, Map medidaSello3, Map medidaSello4) throws Exception {
        setIdentificacion(identificacion);
        setVisual(visual);
        setLote(lote);
        setDiametroExtTapaInf(diametroExtTapaInf);
        setDiametroExtTapaSup(diametroExtTapaSup);
        setDiametroIntTapaInf(diametroIntTapaInf);
        setDiametroIntTapaSup(diametroIntTapaSup);
        setSello1(sello1);
        setSello2(sello2);
        setSello3(sello3);
        setSello4(sello4);
        setElementoFiltrante(elementoFiltrante);
        setMedidaDiametroExtTapaInf(medidaDiametroExtTapaInf);
        setMedidaDiametroExtTapaSup(medidaDiametroExtTapaSup);
        setMedidaDiametroIntTapaInf(medidaDiametroIntTapaInf);
        setMedidaDiametroIntTapaSup(medidaDiametroIntTapaSup);
        setMedidaSello1(medidaSello1);
        setMedidaSello2(medidaSello2);
        setMedidaSello3(medidaSello3);
        setMedidaSello4(medidaSello4);

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

    public String getDiametroExtTapaInf() {
        return diametroExtTapaInf;
    }

    public void setDiametroExtTapaInf(String diametroExtTapaInf) throws Exception {
        if (diametroExtTapaInf == null || diametroExtTapaInf.isEmpty()) {
            throw new Exception("Diametro Ext Tapa Inf no puede estar vacio.");
        } else {
            this.diametroExtTapaInf = diametroExtTapaInf;
        }
    }

    public String getDiametroExtTapaSup() {
        return diametroExtTapaSup;
    }

    public void setDiametroExtTapaSup(String diametroExtTapaSup) throws Exception {
        if (diametroExtTapaSup == null || diametroExtTapaSup.isEmpty()) {
            throw new Exception("Diametro Ext Tapa Sup no puede estar vacio.");
        } else {
            this.diametroExtTapaSup = diametroExtTapaSup;
        }
    }

    public String getDiametroIntTapaInf() {
        return diametroIntTapaInf;
    }

    public void setDiametroIntTapaInf(String diametroIntTapaInf) throws Exception {
        if (diametroIntTapaInf == null || diametroIntTapaInf.isEmpty()) {
            throw new Exception("Diametro Int Tapa Inf no puede estar vacio.");
        } else {
            this.diametroIntTapaInf = diametroIntTapaInf;
        }
    }

    public String getDiametroIntTapaSup() {
        return diametroIntTapaSup;
    }

    public void setDiametroIntTapaSup(String diametroIntTapaSup) throws Exception {
        if (diametroIntTapaSup == null || diametroIntTapaSup.isEmpty()) {
            throw new Exception("Diametro Int Tapa Sup no puede estar vacio.");
        } else {
            this.diametroIntTapaSup = diametroIntTapaSup;
        }
    }

    public String getSello1() {
        return sello1;
    }

    public void setSello1(String sello1) throws Exception {
        if (sello1 == null || sello1.isEmpty()) {
            throw new Exception("sello 1 no puede estar vacio.");
        } else {
            this.sello1 = sello1;
        }
    }

    public String getSello2() {
        return sello2;
    }

    public void setSello2(String sello2) throws Exception {
        if (sello2 == null || sello2.isEmpty()) {
            throw new Exception("sello 2 no puede estar vacio.");
        } else {
            this.sello2 = sello2;
        }
    }

    public String getSello3() {
        return sello3;
    }

    public void setSello3(String sello3) throws Exception {
        if (sello3 == null || sello3.isEmpty()) {
            throw new Exception("sello 3 no puede estar vacio.");
        } else {
            this.sello3 = sello3;
        }
    }

    public String getSello4() {
        return sello4;
    }

    public void setSello4(String sello4) throws Exception {
        if (sello4 == null || sello4.isEmpty()) {
            throw new Exception("sello 4 no puede estar vacio.");
        } else {
            this.sello4 = sello4;
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

    public String getDiametroExtTapaInfId() {
        return diametroExtTapaInfId;
    }

    public void setDiametroExtTapaInfId(String diametroExtTapaInfId) {
        this.diametroExtTapaInfId = diametroExtTapaInfId;
    }

    public String getDiametroExtTapaSupId() {
        return diametroExtTapaSupId;
    }

    public void setDiametroExtTapaSupId(String diametroExtTapaSupId) {
        this.diametroExtTapaSupId = diametroExtTapaSupId;
    }

    public String getDiametroIntTapaInfId() {
        return diametroIntTapaInfId;
    }

    public void setDiametroIntTapaInfId(String diametroIntTapaInfId) {
        this.diametroIntTapaInfId = diametroIntTapaInfId;
    }

    public String getDiametroIntTapaSupId() {
        return diametroIntTapaSupId;
    }

    public void setDiametroIntTapaSupId(String diametroIntTapaSupId) {
        this.diametroIntTapaSupId = diametroIntTapaSupId;
    }

    public String getSello1Id() {
        return sello1Id;
    }

    public void setSello1Id(String sello1Id) {
        this.sello1Id = sello1Id;
    }

    public String getSello2Id() {
        return sello2Id;
    }

    public void setSello2Id(String sello2Id) {
        this.sello2Id = sello2Id;
    }

    public String getSello3Id() {
        return sello3Id;
    }

    public void setSello3Id(String sello3Id) {
        this.sello3Id = sello3Id;
    }

    public String getSello4Id() {
        return sello4Id;
    }

    public void setSello4Id(String sello4Id) {
        this.sello4Id = sello4Id;
    }

    public String getElementoFiltranteId() {
        return elementoFiltranteId;
    }

    public void setElementoFiltranteId(String elementoFiltranteId) {
        this.elementoFiltranteId = elementoFiltranteId;
    }

    public Map getMedidaDiametroExtTapaInf() {
        return medidaDiametroExtTapaInf;
    }

    public void setMedidaDiametroExtTapaInf(Map medidaDiametroExtTapaInf) throws Exception {
        String nom = (String) medidaDiametroExtTapaInf.get("diametro_ext_tapa_inf_diesel_cartucho_Nom");
        String min = (String) medidaDiametroExtTapaInf.get("diametro_ext_tapa_inf_diesel_cartucho_Min");
        String max = (String) medidaDiametroExtTapaInf.get("diametro_ext_tapa_inf_diesel_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas Diametro Ext Tapa Inf no pueden estar vacias.");
        } else {
            this.medidaDiametroExtTapaInf = medidaDiametroExtTapaInf;
        }
    }

    public Map getMedidaDiametroExtTapaSup() {
        return medidaDiametroExtTapaSup;
    }

    public void setMedidaDiametroExtTapaSup(Map medidaDiametroExtTapaSup) throws Exception {
        String nom = (String) medidaDiametroExtTapaSup.get("diametro_ext_tapa_sup_diesel_cartucho_Nom");
        String min = (String) medidaDiametroExtTapaSup.get("diametro_ext_tapa_sup_diesel_cartucho_Min");
        String max = (String) medidaDiametroExtTapaSup.get("diametro_ext_tapa_sup_diesel_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas Diametro Ext Tapa Sup no pueden estar vacias.");
        } else {
            this.medidaDiametroExtTapaSup = medidaDiametroExtTapaSup;
        }
    }

    public Map getMedidaDiametroIntTapaInf() {
        return medidaDiametroIntTapaInf;
    }

    public void setMedidaDiametroIntTapaInf(Map medidaDiametroIntTapaInf) throws Exception {
        String nom = (String) medidaDiametroIntTapaInf.get("diametro_int_tapa_inf_diesel_cartucho_Nom");
        String min = (String) medidaDiametroIntTapaInf.get("diametro_int_tapa_inf_diesel_cartucho_Min");
        String max = (String) medidaDiametroIntTapaInf.get("diametro_int_tapa_inf_diesel_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas Diametro Int Tapa Inf no pueden estar vacias.");
        } else {
            this.medidaDiametroIntTapaInf = medidaDiametroIntTapaInf;
        }
    }

    public Map getMedidaDiametroIntTapaSup() {
        return medidaDiametroIntTapaSup;
    }

    public void setMedidaDiametroIntTapaSup(Map medidaDiametroIntTapaSup) throws Exception {
        String nom = (String) medidaDiametroIntTapaSup.get("diametro_int_tapa_sup_diesel_cartucho_Nom");
        String min = (String) medidaDiametroIntTapaSup.get("diametro_int_tapa_sup_diesel_cartucho_Min");
        String max = (String) medidaDiametroIntTapaSup.get("diametro_int_tapa_sup_diesel_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas Diametro Int Tapa Sup no pueden estar vacias.");
        } else {
            this.medidaDiametroIntTapaSup = medidaDiametroIntTapaSup;
        }
    }

    public Map getMedidaSello1() {
        return medidaSello1;
    }

    public void setMedidaSello1(Map medidaSello1) throws Exception {
        String nom = (String) medidaSello1.get("sello1_diesel_cartucho_Nom");
        String min = (String) medidaSello1.get("sello1_diesel_cartucho_Min");
        String max = (String) medidaSello1.get("sello1_diesel_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas Sello 1 no pueden estar vacias.");
        } else {
            this.medidaSello1 = medidaSello1;
        }
    }

    public Map getMedidaSello2() {
        return medidaSello2;
    }

    public void setMedidaSello2(Map medidaSello2) throws Exception {
        String nom = (String) medidaSello2.get("sello2_diesel_cartucho_Nom");
        String min = (String) medidaSello2.get("sello2_diesel_cartucho_Min");
        String max = (String) medidaSello2.get("sello2_diesel_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas Sello 2 no pueden estar vacias.");
        } else {
            this.medidaSello2 = medidaSello2;
        }
    }

    public Map getMedidaSello3() {
        return medidaSello3;
    }

    public void setMedidaSello3(Map medidaSello3) throws Exception {
        String nom = (String) medidaSello3.get("sello3_diesel_cartucho_Nom");
        String min = (String) medidaSello3.get("sello3_diesel_cartucho_Min");
        String max = (String) medidaSello3.get("sello3_diesel_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas Sello 3 no pueden estar vacias.");
        } else {
            this.medidaSello3 = medidaSello3;
        }
    }

    public Map getMedidaSello4() {
        return medidaSello4;
    }

    public void setMedidaSello4(Map medidaSello4) throws Exception {
        String nom = (String) medidaSello4.get("sello4_diesel_cartucho_Nom");
        String min = (String) medidaSello4.get("sello4_diesel_cartucho_Nom");
        String max = (String) medidaSello4.get("sello4_diesel_cartucho_Nom");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas Sello 4 no pueden estar vacias.");
        } else {
            this.medidaSello4 = medidaSello4;
        }
    }

    public String getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(String IdDetalle) {
        this.IdDetalle = IdDetalle;
    }

}
