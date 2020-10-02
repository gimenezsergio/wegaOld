package ingresos.entidades;

import java.util.Map;

public class PlanBujiaPreca {

    private String id;
    private String identificacion;
    private String lote;
    private String rosca;
    private String hexagono;
    private String L1;
    private String L2;
    private String L3;
    private String perno;
    private String idDetalle;
    private String identificacionId;
    private String loteId;
    private String roscaId;
    private String hexagonoId;
    private String L1Id;
    private String L2Id;
    private String L3Id;
    private Map medidasL1Id;
    private Map medidasL2Id;
    private Map medidasL3Id;
    private String pernoId;

    public PlanBujiaPreca(String identificacion, String lote, String rosca, String hexagono, String L1, String L2,
            String L3, String perno, String identificacionId, String loteId, String roscaId, String hexagonoId, String L1Id, String L2Id, String L3Id,
            String pernoId, Map medidasL1Id, Map medidasL2Id, Map medidasL3Id) throws Exception {
        setIdentificacion(identificacion);
        setLote(lote);
        setRosca(rosca);
        setHexagono(hexagono);
        setL1(L1);
        setL2(L2);
        setL3(L3);
        setPerno(perno);
        setIdentificacionId(identificacionId);
        setLoteId(loteId);
        setRoscaId(roscaId);
        setHexagonoId(hexagonoId);
        setL1Id(L1Id);
        setL2Id(L2Id);
        setL3Id(L3Id);
        setPernoId(pernoId);
        setMedidasL1Id(medidasL1Id);
        setMedidasL2Id(medidasL2Id);
        setMedidasL3Id(medidasL3Id);
    }

//    @Override
//    public String toString() {
//        return "{ " + "Identificacion: " + identificacion +  " }";
//    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) throws Exception {
        if (identificacion == null || identificacion.isEmpty()) {
            throw new Exception("La identificacion no puede estar vacio.");
        } else {
            this.identificacion = identificacion;
        }

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

    public String getRosca() {
        return rosca;
    }

    public void setRosca(String rosca) throws Exception {
        if (rosca == null || rosca.isEmpty()) {
            throw new Exception("La rosca no puede estar vacio.");
        } else {
            this.rosca = rosca;
        }
    }

    public String getHexagono() {
        return hexagono;
    }

    public void setHexagono(String hexagono) throws Exception {
        if (hexagono == null || hexagono.isEmpty()) {
            throw new Exception("El hexagono no puede estar vacio.");
        } else {
            this.hexagono = hexagono;
        }

    }

    public String getL1() {
        return L1;
    }

    public void setL1(String L1) throws Exception {
        if (L1 == null || L1.isEmpty()) {
            throw new Exception("L1 no puede estar vacio.");
        } else {
            this.L1 = L1;
        }

    }

    public String getL2() {
        return L2;
    }

    public void setL2(String L2) throws Exception {
        if (L2 == null || L2.isEmpty()) {
            throw new Exception("L2 no puede estar vacio.");
        } else {
            this.L2 = L2;
        }

    }

    public String getL3() {
        return L3;
    }

    public void setL3(String L3) throws Exception {
        if (L3 == null || L3.isEmpty()) {
            throw new Exception("L3 no puede estar vacio.");
        } else {
            this.L3 = L3;
        }

    }

    public String getPerno() {
        return perno;
    }

    public void setPerno(String perno) throws Exception {
        if (perno == null || perno.isEmpty()) {
            throw new Exception("Perno no puede estar vacio.");
        } else {
            this.perno = perno;
        }
        this.perno = perno;
    }

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getIdentificacionId() {
        return identificacionId;
    }

    public void setIdentificacionId(String identificacionId) throws Exception {
        if (identificacionId == null || identificacionId.isEmpty()) {
            throw new Exception("identificacionId vacio.");
        } else {
            this.identificacionId = identificacionId;
        }
    }

    public String getLoteId() {
        return loteId;
    }

    public void setLoteId(String loteId) throws Exception {
        if (loteId == null || loteId.isEmpty()) {
            throw new Exception("loteId vacio.");
        } else {
            this.loteId = loteId;
        }

    }

    public String getRoscaId() {
        return roscaId;
    }

    public void setRoscaId(String roscaId) throws Exception {
        if (roscaId == null || roscaId.isEmpty()) {
            throw new Exception("roscaId vacio.");
        } else {
            this.roscaId = roscaId;
        }

    }

    public String getHexagonoId() {
        return hexagonoId;
    }

    public void setHexagonoId(String hexagonoId) throws Exception {
        if (hexagonoId == null || hexagonoId.isEmpty()) {
            throw new Exception("hexagonoId vacio.");
        } else {
            this.hexagonoId = hexagonoId;
        }

    }

    public String getL1Id() {
        return L1Id;
    }

    public void setL1Id(String L1Id) throws Exception {
        if (L1Id == null || L1Id.isEmpty()) {
            throw new Exception("L1Id vacio.");
        } else {
            this.L1Id = L1Id;
        }

    }

    public String getL2Id() {
        return L2Id;
    }

    public void setL2Id(String L2Id) throws Exception {
        if (L2Id == null || L2Id.isEmpty()) {
            throw new Exception("L2Id vacio.");
        } else {
            this.L2Id = L2Id;
        }

    }

    public String getL3Id() {
        return L3Id;
    }

    public void setL3Id(String L3Id) throws Exception {
        if (L3Id == null || L3Id.isEmpty()) {
            throw new Exception("L3Id vacio.");
        } else {
            this.L3Id = L3Id;
        }

    }

    public String getPernoId() {
        return pernoId;
    }

    public void setPernoId(String pernoId) throws Exception {
        if (pernoId == null || pernoId.isEmpty()) {
            throw new Exception("L3Id vacio.");
        } else {
            this.pernoId = pernoId;
        }

    }

    public Map getMedidasL1Id() {
        return medidasL1Id;
    }

    public void setMedidasL1Id(Map medidasL1Id) throws Exception {
        
        String nom = (String) medidasL1Id.get("longuitudL1_bujia_preca_Nom");
        String min = (String) medidasL1Id.get("longuitudL1_bujia_preca_Min");
        String max = (String) medidasL1Id.get("longuitudL1_bujia_preca_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida L1 no puede estar vacia.");
        } else {
            this.medidasL1Id = medidasL1Id;
        }
    }

    public Map getMedidasL2Id() {
        return medidasL2Id;
    }

    public void setMedidasL2Id(Map medidasL2Id) throws Exception {        
        String nom = (String) medidasL2Id.get("longuitudL2_bujia_preca_Nom");
        String min = (String) medidasL2Id.get("longuitudL2_bujia_preca_Min");
        String max = (String) medidasL2Id.get("longuitudL2_bujia_preca_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida L2 no puede estar vacia.");
        } else {
            this.medidasL2Id = medidasL2Id;
        }
    }

    public Map getMedidasL3Id() {
        return medidasL3Id;
    }

    public void setMedidasL3Id(Map medidasL3Id) throws Exception {
        String nom = (String) medidasL3Id.get("longuitudL3_bujia_preca_Nom");
        String min = (String) medidasL3Id.get("longuitudL3_bujia_preca_Min");
        String max = (String) medidasL3Id.get("longuitudL3_bujia_preca_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida L3 no puede estar vacia.");
        } else {
            this.medidasL3Id = medidasL3Id;
        }
    }

}
