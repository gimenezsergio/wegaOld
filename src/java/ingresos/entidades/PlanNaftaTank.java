package ingresos.entidades;

import java.util.Map;

public class PlanNaftaTank {

    private String identificacion;
    private String visual;
    private String lote;
    private String diametro1;
    private String diametro2;
    private String alturaTotal;
    private String diametroConector1;
    private String diametroConector2;
    private String diametroConector3;
    private String diametroAlojamiento;
    private String soporteFijacion;
    
    private String identificacionId;
    private String visualId;
    private String loteId;
    private String diametro1Id;
    private String diametro2Id;
    private String alturaTotalId;
    private String diametroConector1Id;
    private String diametroConector2Id;
    private String diametroConector3Id;
    private String diametroAlojamientoId;
    private String soporteFijacionId;
    
    private Map medidaDiametro1;
    private Map medidaDiametro2;
    private Map medidaAlturaTotal;
    private Map medidaDiametroConector1;
    private Map medidaDiametroConector2;
    private Map medidaDiametroConector3;
    private Map medidaDiametroAlojamiento;
    private String IdDetalle;

    public PlanNaftaTank(String identificacion, String visual, String lote, String diametro1, String diametro2, String alturaTotal, String diametroConector1, 
            String diametroConector2, String diametroConector3, String diametroAlojamiento, String soporteFijacion, Map medidaDiametro1, Map medidaDiametro2, 
            Map medidaAlturaTotal, Map medidaDiametroConector1, Map medidaDiametroConector2, Map medidaDiametroConector3, Map medidaDiametroAlojamiento) throws Exception {
        setIdentificacion(identificacion);
        setVisual(visual);
        setLote(lote);
        setDiametro1(diametro1);
        setDiametro2(diametro2);
        setAlturaTotal(alturaTotal);
        setDiametroConector1(diametroConector1);
        setDiametroConector2(diametroConector2);
        setDiametroConector3(diametroConector3);
        setDiametroAlojamiento(diametroAlojamiento);
        setSoporteFijacion(soporteFijacion);
        setMedidaDiametro1(medidaDiametro1);
        setMedidaDiametro2(medidaDiametro2);
        setMedidaAlturaTotal(medidaAlturaTotal);
        setMedidaDiametroConector1(medidaDiametroConector1);
        setMedidaDiametroConector2(medidaDiametroConector2);
        setMedidaDiametroConector3(medidaDiametroConector3);
        setMedidaDiametroAlojamiento(medidaDiametroAlojamiento);
        
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

    public String getDiametro1() {
        return diametro1;
    }

    public void setDiametro1(String diametro1) throws Exception {
        if (diametro1 == null || diametro1.isEmpty()) {
            throw new Exception("Diametro 1 no puede estar vacio.");
        } else {
            this.diametro1 = diametro1;
        }
    }

    public String getDiametro2() {
        return diametro2;
    }

    public void setDiametro2(String diametro2) throws Exception {
        if (diametro2 == null || diametro2.isEmpty()) {
            throw new Exception("Diametro 2 no puede estar vacio.");
        } else {
            this.diametro2 = diametro2;
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

    public String getDiametroConector1() {
        return diametroConector1;
    }

    public void setDiametroConector1(String diametroConector1) throws Exception {
        if (diametroConector1 == null || diametroConector1.isEmpty()) {
            throw new Exception("Diametro Conector 1 no puede estar vacio.");
        } else {
            this.diametroConector1 = diametroConector1;
        }
    }

    public String getDiametroConector2() {
        return diametroConector2;
    }

    public void setDiametroConector2(String diametroConector2) throws Exception {
        if (diametroConector2 == null || diametroConector2.isEmpty()) {
            throw new Exception("Diametro Conector 2 no puede estar vacio.");
        } else {
            this.diametroConector2 = diametroConector2;
        }
    }

    public String getDiametroConector3() {
        return diametroConector3;
    }

    public void setDiametroConector3(String diametroConector3) throws Exception {
        if (diametroConector3 == null || diametroConector3.isEmpty()) {
            throw new Exception("Diametro Conector 3 no puede estar vacio.");
        } else {
            this.diametroConector3 = diametroConector3;
        }
    }

    public String getDiametroAlojamiento() {
        return diametroAlojamiento;
    }

    public void setDiametroAlojamiento(String diametroAlojamiento) throws Exception {
        if (diametroAlojamiento == null || diametroAlojamiento.isEmpty()) {
            throw new Exception("Diametro Alojamiento no puede estar vacio.");
        } else {
            this.diametroAlojamiento = diametroAlojamiento;
        }
    }

    public String getSoporteFijacion() {
        return soporteFijacion;
    }

    public void setSoporteFijacion(String soporteFijacion) throws Exception {
        if (soporteFijacion == null || soporteFijacion.isEmpty()) {
            throw new Exception("Soporte Fijacion no puede estar vacio.");
        } else {
            this.soporteFijacion = soporteFijacion;
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

    public String getDiametro1Id() {
        return diametro1Id;
    }

    public void setDiametro1Id(String diametro1Id) {
        this.diametro1Id = diametro1Id;
    }

    public String getDiametro2Id() {
        return diametro2Id;
    }

    public void setDiametro2Id(String diametro2Id) {
        this.diametro2Id = diametro2Id;
    }

    public String getAlturaTotalId() {
        return alturaTotalId;
    }

    public void setAlturaTotalId(String alturaTotalId) {
        this.alturaTotalId = alturaTotalId;
    }

    public String getDiametroConector1Id() {
        return diametroConector1Id;
    }

    public void setDiametroConector1Id(String diametroConector1Id) {
        this.diametroConector1Id = diametroConector1Id;
    }

    public String getDiametroConector2Id() {
        return diametroConector2Id;
    }

    public void setDiametroConector2Id(String diametroConector2Id) {
        this.diametroConector2Id = diametroConector2Id;
    }

    public String getDiametroConector3Id() {
        return diametroConector3Id;
    }

    public void setDiametroConector3Id(String diametroConector3Id) {
        this.diametroConector3Id = diametroConector3Id;
    }

    public String getDiametroAlojamientoId() {
        return diametroAlojamientoId;
    }

    public void setDiametroAlojamientoId(String diametroAlojamientoId) {
        this.diametroAlojamientoId = diametroAlojamientoId;
    }

    public String getSoporteFijacionId() {
        return soporteFijacionId;
    }

    public void setSoporteFijacionId(String soporteFijacionId) {
        this.soporteFijacionId = soporteFijacionId;
    }

    public Map getMedidaDiametro1() {
        return medidaDiametro1;
    }

    public void setMedidaDiametro1(Map medidaDiametro1) throws Exception {
        String nom = (String) medidaDiametro1.get("diametro1_nafta_tank_Nom");
        String min = (String) medidaDiametro1.get("diametro1_nafta_tank_Min");
        String max = (String) medidaDiametro1.get("diametro1_nafta_tank_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas Diametro 1 no pueden estar vacias.");
        } else {
            this.medidaDiametro1 = medidaDiametro1;
        }
    }

    public Map getMedidaDiametro2() {
        return medidaDiametro2;
    }

    public void setMedidaDiametro2(Map medidaDiametro2) throws Exception {
        String nom = (String) medidaDiametro2.get("diametro2_nafta_tank_Nom");
        String min = (String) medidaDiametro2.get("diametro2_nafta_tank_Nom");
        String max = (String) medidaDiametro2.get("diametro2_nafta_tank_Nom");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas diametro 2 no pueden estar vacias.");
        } else {
            this.medidaDiametro2 = medidaDiametro2;
        }
    }

    public Map getMedidaAlturaTotal() {
        return medidaAlturaTotal;
    }

    public void setMedidaAlturaTotal(Map medidaAlturaTotal) throws Exception {
        String nom = (String) medidaAlturaTotal.get("altura_total_nafta_tank_Nom");
        String min = (String) medidaAlturaTotal.get("altura_total_nafta_tank_Min");
        String max = (String) medidaAlturaTotal.get("altura_total_nafta_tank_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas altura total no pueden estar vacias.");
        } else {
            this.medidaAlturaTotal = medidaAlturaTotal;
        }
    }

    public Map getMedidaDiametroConector1() {
        return medidaDiametroConector1;
    }

    public void setMedidaDiametroConector1(Map medidaDiametroConector1) throws Exception {
        String nom = (String) medidaDiametroConector1.get("diametro_conector1_nafta_tank_Nom");
        String min = (String) medidaDiametroConector1.get("diametro_conector1_nafta_tank_Min");
        String max = (String) medidaDiametroConector1.get("diametro_conector1_nafta_tank_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas Diametro conector 1 no pueden estar vacias.");
        } else {
            this.medidaDiametroConector1 = medidaDiametroConector1;
        }
    }

    public Map getMedidaDiametroConector2() {
        return medidaDiametroConector2;
    }

    public void setMedidaDiametroConector2(Map medidaDiametroConector2) throws Exception {
        String nom = (String) medidaDiametroConector2.get("diametro_conector2_nafta_tank_Nom");
        String min = (String) medidaDiametroConector2.get("diametro_conector2_nafta_tank_Min");
        String max = (String) medidaDiametroConector2.get("diametro_conector2_nafta_tank_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas Diametro conector 2 no pueden estar vacias.");
        } else {
            this.medidaDiametroConector2 = medidaDiametroConector2;
        }
    }

    public Map getMedidaDiametroConector3() {
        return medidaDiametroConector3;
    }

    public void setMedidaDiametroConector3(Map medidaDiametroConector3) throws Exception {
        String nom = (String) medidaDiametroConector3.get("diametro_conector3_nafta_tank_Nom");
        String min = (String) medidaDiametroConector3.get("diametro_conector3_nafta_tank_Min");
        String max = (String) medidaDiametroConector3.get("diametro_conector3_nafta_tank_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas Diametro conector 3 no pueden estar vacias.");
        } else {
            this.medidaDiametroConector3 = medidaDiametroConector3;
        }
    }

    public Map getMedidaDiametroAlojamiento() {
        return medidaDiametroAlojamiento;
    }

    public void setMedidaDiametroAlojamiento(Map medidaDiametroAlojamiento) throws Exception {
        String nom = (String) medidaDiametroAlojamiento.get("diametro_alojamiento_nafta_tank_Nom");
        String min = (String) medidaDiametroAlojamiento.get("diametro_alojamiento_nafta_tank_Min");
        String max = (String) medidaDiametroAlojamiento.get("diametro_alojamiento_nafta_tank_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Diametro alojamiento no pueden estar vacias.");
        } else {
            this.medidaDiametroAlojamiento = medidaDiametroAlojamiento;
        }
    }

    public String getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(String IdDetalle) {
        this.IdDetalle = IdDetalle;
    }

    @Override
    public String toString() {
        return "PlanNaftaTank{" + "identificacionId=" + identificacionId + ", visualId=" + visualId + ", loteId=" + loteId + ", diametro1Id=" + diametro1Id + ", diametro2Id=" + diametro2Id + ", alturaTotalId=" + alturaTotalId + ", diametroConector1Id=" + diametroConector1Id + ", diametroConector2Id=" + diametroConector2Id + ", diametroConector3Id=" + diametroConector3Id + ", diametroAlojamientoId=" + diametroAlojamientoId + '}';
    }

    
    
    
}
