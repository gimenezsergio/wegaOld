package ingresos.entidades;

import java.util.Map;

public class PlanAceiteCartucho {

    private String IdPlanAceiteCartucho;
    private String IdDetalle;
    private String identificacion;
    private String visual;
    private String lote;
    private String diamTapaSup;
    private String diamIntTapaSup;
    private String diamTapaInf;
    private String diamIntTapaInf;
    private String alturaTotal;
    private String sello1;
    private String sello2;
    private String sello3;
    private String sello4;
    private String arandelas;
    private String identificacionId;
    private String visualId;
    private String loteId;
    private String diamTapaSupId;
    private String diamIntTapaSupId;
    private String diamTapaInfId;
    private String diamIntTapaInfId;
    private String alturaTotalId;
    private String sello1Id;
    private String sello2Id;
    private String sello3Id;
    private String sello4Id;
    private String arandelasId;
    private Map medidaAlturaTotal;
    private Map medidaDiamInteriorTapa;
    private Map medidaDiamTapaInferior;
    private Map medidaDiameInternoTapaSup;
    private Map medidaDiametroTapaSup;
    private Map medidaSello1;
    private Map medidaSello2;
    private Map medidaSello3;
    private Map medidaSello4;

    public PlanAceiteCartucho(
            String identificacion,
            String visual,
            String lote,
            String diamTapaSup,
            String diamIntTapaSup,
            String diamTapaInf,
            String diamIntTapaInf,
            String alturaTotal,
            String sello1,
            String sello2,
            String sello3,
            String sello4,
            String arandelas,
            Map medidaAlturaTotal,
            Map medidaDiamInteriorTapa,
            Map medidaDiamTapaInferior,
            Map medidaDiametroTapa,
            Map medidaDiametroTapaInterno,
            Map medidaSello1,
            Map medidaSello2,
            Map medidaSello3,
            Map medidaSello4) throws Exception {

        setIdentificacion(identificacion);
        setVisual(visual);
        setLote(lote);
        setDiamTapaSup(diamTapaSup);
        setDiamIntTapaSup(diamIntTapaSup);
        setDiamTapaInf(diamTapaInf);
        setDiamIntTapaInf(diamIntTapaInf);
        setAlturaTotal(alturaTotal);
        setSello1(sello1);
        setSello2(sello2);
        setSello3(sello3);
        setSello4(sello4);
        setArandelas(arandelas);
        setMedidaAlturaTotal(medidaAlturaTotal);
        setMedidaDiamInteriorTapa(medidaDiamInteriorTapa);
        setMedidaDiamTapaInferior(medidaDiamTapaInferior);
        setMedidaDiametroTapaSup(medidaDiametroTapa);
        setMedidaDiameInternoTapaSup(medidaDiametroTapaInterno);
        setMedidaSello1(medidaSello1);
        setMedidaSello2(medidaSello2);
        setMedidaSello3(medidaSello3);
        setMedidaSello4(medidaSello4);
    }

    public String getIdPlanAceiteCartucho() {
        return IdPlanAceiteCartucho;
    }

    public void setIdPlanAceiteCartucho(String IdPlanAceiteCartucho) {
        this.IdPlanAceiteCartucho = IdPlanAceiteCartucho;
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

    public String getDiamTapaSup() {
        return diamTapaSup;
    }

    public void setDiamTapaSup(String diamTapaSup) throws Exception {
        if (diamTapaSup == null || diamTapaSup.isEmpty()) {
            throw new Exception("Diametro tapa superior no puede estar vacio.");
        } else {
            this.diamTapaSup = diamTapaSup;
        }

    }

    public String getDiamIntTapaSup() {
        return diamIntTapaSup;
    }

    public void setDiamIntTapaSup(String diamIntTapaSup) throws Exception {
        if (diamIntTapaSup == null || diamIntTapaSup.isEmpty()) {
            throw new Exception("Diametro tapa superior interior no puede estar vacio.");
        } else {
            this.diamIntTapaSup = diamIntTapaSup;
        }

    }

    public String getDiamTapaInf() {
        return diamTapaInf;
    }

    public void setDiamTapaInf(String diamTapaInf) throws Exception {
        if (diamTapaInf == null || diamTapaInf.isEmpty()) {
            throw new Exception("Diametro tapa inferior no puede estar vacia.");
        } else {
            this.diamTapaInf = diamTapaInf;
        }
    }

    public String getDiamIntTapaInf() {
        return diamIntTapaInf;
    }

    public void setDiamIntTapaInf(String diamIntTapaInf) throws Exception {
        if (diamIntTapaInf == null || diamIntTapaInf.isEmpty()) {
            throw new Exception("Diametro interno de tapa inf. no puede estar vacia.");
        } else {
            this.diamIntTapaInf = diamIntTapaInf;
        }
    }

    public String getAlturaTotal() {
        return alturaTotal;
    }

    public void setAlturaTotal(String alturaTotal) throws Exception {
        if (alturaTotal == null || alturaTotal.isEmpty()) {
            throw new Exception("Altura total no puede estar vacia.");
        } else {
            this.alturaTotal = alturaTotal;
        }
    }

    public String getSello1() {
        return sello1;
    }

    public void setSello1(String sello1) throws Exception {
        if (sello1 == null || sello1.isEmpty()) {
            throw new Exception("Sello 1 no puede estar vacio.");
        } else {
            this.sello1 = sello1;
        }
    }

    public String getSello2() {
        return sello2;
    }

    public void setSello2(String sello2) throws Exception {
        if (sello2 == null || sello2.isEmpty()) {
            throw new Exception("Sello 2 no puede estar vacio.");
        } else {
            this.sello2 = sello2;
        }
    }

    public String getSello3() {
        return sello3;
    }

    public void setSello3(String sello3) throws Exception {
        if (sello3 == null || sello3.isEmpty()) {
            throw new Exception("Sello 3 no puede estar vacio.");
        } else {
            this.sello3 = sello3;
        }
    }

    public String getSello4() {
        return sello4;
    }

    public void setSello4(String sello4) throws Exception {
        if (sello4 == null || sello4.isEmpty()) {
            throw new Exception("Sello 4 no puede estar vacio.");
        } else {
            this.sello4 = sello4;
        }
    }

    public String getArandelas() {
        return arandelas;
    }

    public void setArandelas(String arandelas) throws Exception {
        if (arandelas == null || arandelas.isEmpty()) {
            throw new Exception("Arandelas no puede estar vacio.");
        } else {
            this.arandelas = arandelas;
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

    public String getDiamTapaSupId() {
        return diamTapaSupId;
    }

    public void setDiamTapaSupId(String diamTapaSupId) {
        this.diamTapaSupId = diamTapaSupId;
    }

    public String getDiamIntTapaSupId() {
        return diamIntTapaSupId;
    }

    public void setDiamIntTapaSupId(String diamIntTapaSupId) {
        this.diamIntTapaSupId = diamIntTapaSupId;
    }

    public String getDiamTapaInfId() {
        return diamTapaInfId;
    }

    public void setDiamTapaInfId(String diamTapaInfId) {
        this.diamTapaInfId = diamTapaInfId;
    }

    public String getDiamIntTapaInfId() {
        return diamIntTapaInfId;
    }

    public void setDiamIntTapaInfId(String diamIntTapaInfId) {
        this.diamIntTapaInfId = diamIntTapaInfId;
    }

    public String getAlturaTotalId() {
        return alturaTotalId;
    }

    public void setAlturaTotalId(String alturaTotalId) {
        this.alturaTotalId = alturaTotalId;
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

    public String getArandelasId() {
        return arandelasId;
    }

    public void setArandelasId(String arandelasId) {
        this.arandelasId = arandelasId;
    }

    public Map getMedidaAlturaTotal() {
        return medidaAlturaTotal;
    }

    public void setMedidaAlturaTotal(Map medidaAlturaTotal) throws Exception {
        String nom = (String) medidaAlturaTotal.get("altura_tot_filtro_filtro_aceite_cartucho_Nom");
        String min = (String) medidaAlturaTotal.get("altura_tot_filtro_filtro_aceite_cartucho_Min");
        String max = (String) medidaAlturaTotal.get("altura_tot_filtro_filtro_aceite_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de altura total no pueden estar vacias.");
        } else {
            this.medidaAlturaTotal = medidaAlturaTotal;
        }
    }

    public Map getMedidaDiamInteriorTapa() {
        return medidaDiamInteriorTapa;
    }

    public void setMedidaDiamInteriorTapa(Map medidaDiamInteriorTapa) throws Exception {
        String nom = (String) medidaDiamInteriorTapa.get("dia_int_tapa_inf_filtro_aceite_cartucho_Nom");
        String min = (String) medidaDiamInteriorTapa.get("dia_int_tapa_inf_filtro_aceite_cartucho_Min");
        String max = (String) medidaDiamInteriorTapa.get("dia_int_tapa_inf_filtro_aceite_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de diametro interior de tapa inferior no pueden estar vacias.");
        } else {
            this.medidaDiamInteriorTapa = medidaDiamInteriorTapa;
        }
    }

    public Map getMedidaDiamTapaInferior() {
        return medidaDiamTapaInferior;
    }

    public void setMedidaDiamTapaInferior(Map medidaDiamTapaInferior) throws Exception {
        String nom = (String) medidaDiamTapaInferior.get("dia_int_tapa_inf_filtro_aceite_cartucho_Nom");
        String min = (String) medidaDiamTapaInferior.get("dia_int_tapa_inf_filtro_aceite_cartucho_Min");
        String max = (String) medidaDiamTapaInferior.get("dia_int_tapa_inf_filtro_aceite_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de diametro de tapa inf. no pueden estar vacias.");
        } else {
            this.medidaDiamTapaInferior = medidaDiamTapaInferior;
        }
    }

    public Map getMedidaSello1() {
        return medidaSello1;
    }

    public void setMedidaSello1(Map medidaSello1) throws Exception {
        String nom = (String) medidaSello1.get("sello1_filtro_aceite_cartucho_Nom");
        String min = (String) medidaSello1.get("sello1_filtro_aceite_cartucho_Min");
        String max = (String) medidaSello1.get("sello1_filtro_aceite_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de sello 1 no pueden estar vacias.");
        } else {
            this.medidaSello1 = medidaSello1;
        }
    }

    public Map getMedidaSello2() {
        return medidaSello2;
    }

    public void setMedidaSello2(Map medidaSello2) throws Exception {
        String nom = (String) medidaSello2.get("sello2_filtro_aceite_cartucho_Nom");
        String min = (String) medidaSello2.get("sello2_filtro_aceite_cartucho_Min");
        String max = (String) medidaSello2.get("sello2_filtro_aceite_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de sello 2 no pueden estar vacias.");
        } else {
            this.medidaSello2 = medidaSello2;
        }
    }

    public Map getMedidaSello3() {
        return medidaSello3;
    }

    public void setMedidaSello3(Map medidaSello3) throws Exception {
        String nom = (String) medidaSello3.get("sello3_filtro_aceite_cartucho_Nom");
        String min = (String) medidaSello3.get("sello3_filtro_aceite_cartucho_Min");
        String max = (String) medidaSello3.get("sello3_filtro_aceite_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de sello 3 no pueden estar vacias.");
        } else {
            this.medidaSello3 = medidaSello3;
        }
    }

    public Map getMedidaSello4() {
        return medidaSello4;
    }

    public void setMedidaSello4(Map medidaSello4) throws Exception {
        String nom = (String) medidaSello4.get("sello4_filtro_aceite_cartucho_Nom");
        String min = (String) medidaSello4.get("sello4_filtro_aceite_cartucho_Min");
        String max = (String) medidaSello4.get("sello4_filtro_aceite_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de sello 4 no pueden estar vacias.");
        } else {
            this.medidaSello4 = medidaSello4;
        }
    }

    public Map getMedidaDiameInternoTapaSup() {
        return medidaDiameInternoTapaSup;
    }

    public void setMedidaDiameInternoTapaSup(Map medidaDiameInternoTapaSup) throws Exception {
        System.out.println("dia_int_tapa_sup_filtro_aceite_cartucho_Nom: " + (String) medidaDiameInternoTapaSup.get("dia_int_tapa_sup_filtro_aceite_cartucho_Nom"));
        System.out.println("medida " + this.getMedidaDiameInternoTapaSup());
        String nom = (String) medidaDiameInternoTapaSup.get("dia_int_tapa_sup_filtro_aceite_cartucho_Nom");
        String min = (String) medidaDiameInternoTapaSup.get("dia_int_tapa_sup_filtro_aceite_cartucho_Min");
        String max = (String) medidaDiameInternoTapaSup.get("dia_int_tapa_sup_filtro_aceite_cartucho_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de diametro interno de tapa superior no pueden estar vacias.");
        } else {
            this.medidaDiameInternoTapaSup = medidaDiameInternoTapaSup;
        }
    }

    public Map getMedidaDiametroTapaSup() {
        return medidaDiametroTapaSup;
    }

    public void setMedidaDiametroTapaSup(Map medidaDiametroTapaSup) throws Exception {
        String nom = (String) medidaDiametroTapaSup.get("dia_tapa_sup_filtro_aceite_cartucho_Nom");
        String min = (String) medidaDiametroTapaSup.get("dia_tapa_sup_filtro_aceite_cartucho_Min");
        String max = (String) medidaDiametroTapaSup.get("dia_tapa_sup_filtro_aceite_cartucho_Max");
        System.out.println("setMedidaDiametroTapaSup : " + nom + " - " + min + " - " + max);
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de diametro de tapa superior no pueden estar vacias.");
        } else {
            this.medidaDiametroTapaSup = medidaDiametroTapaSup;
        }
    }

}
