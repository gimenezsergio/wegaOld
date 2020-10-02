package ingresos.entidades;

import java.util.Map;

public class PlanBujiaEncen {
    private String id;
    private String idDetalle;
    private String identificacion;
    private String lote;
    private String rosca;
    private String largoRosca;
    private String hexagono;
    private String asiento;
    private String resistor;
    private String rangoTermico;
    private String PosicionELectrodo;
    private String CantidadElectrodo;
    private String identificacionId;
    private String loteId;
    private Map medidaResistor;
    private String roscaId;
    private String largoRoscaId;
    
    private String hexagonoId;
    private String asientoId;
    private String resistorId;
    private String rangoTermicoId;
    private String PosicionELectrodoId;
    private String CantidadElectrodoId;
    
    public PlanBujiaEncen(String identificacion, String lote, String rosca, String largoRosca, String hexagono, String asiento, 
            String resistor, String rangoTermico, String PosicionELectrodo, String CantidadElectrodo,
            String identificacionId, String loteId, String roscaId, String largoRoscaId, String hexagonoId, String asientoId, String resistorId,
            String rangoTermicoId, String PosicionELectrodoId, String CantidadElectrodoId, Map medidaResistor) throws Exception{
        setIdentificacion(identificacion);
        setLote(lote);
        setRosca(rosca);
        setLargoRosca(largoRosca);
        setHexagono(hexagono);
        setAsiento(asiento);
        setResistor(resistor);
        setRangoTermico(rangoTermico);
        setPosicionELectrodo(PosicionELectrodo);
        setCantidadElectrodo(CantidadElectrodo);
        setIdentificacionId(identificacionId);
        setLoteId(loteId);
        setRoscaId(roscaId);
        setLargoRoscaId(largoRoscaId);
        setHexagonoId(hexagonoId);
        setAsientoId(asientoId);
        setResistorId(resistorId);
        setRangoTermicoId(rangoTermicoId);
        setRangoTermicoId(rangoTermicoId);
        setPosicionELectrodoId(PosicionELectrodoId);
        setCantidadElectrodoId(CantidadElectrodoId);
        setMedidaResistor(medidaResistor);
    }

    public Map getMedidaResistor() {
        return medidaResistor;
    }

    public void setMedidaResistor(Map medidaResistor) throws Exception {
       String nom = (String) medidaResistor.get("resistor_bujia_encendido_Nom");
        String min = (String) medidaResistor.get("resistor_bujia_encendido_Min");
        String max = (String) medidaResistor.get("resistor_bujia_encendido_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida resistor no puede estar vacia.");
        } else {
            this.medidaResistor = medidaResistor;
        }
    }

    PlanBujiaEncen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) throws Exception {
        if(identificacion == null || identificacion.isEmpty()){
            throw new Exception("La identificacion no puede estar vacia.");
        }else{
            this.identificacion = identificacion;
        }
        
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) throws Exception {
        if(lote == null || lote.isEmpty()){
            throw new Exception("El lote no puede estar vacio.");
        }else{
            this.lote = lote;
        }
        
    }

    public String getRosca() {
        return rosca;
    }

    public void setRosca(String rosca) throws Exception {
        if(rosca == null || rosca.isEmpty()){
            throw new Exception("La rosca no puede estar vacia.");
        }else{
            this.rosca = rosca;
        }
    }

    public String getLargoRosca() {
        return largoRosca;
    }

    public void setLargoRosca(String largoRosca) throws Exception {
        if(largoRosca == null || largoRosca.isEmpty()){
            throw new Exception("El largo de rosca no puede estar vacio.");
        }else{
            this.largoRosca = largoRosca;
        }
        
    }

    public String getHexagono() {
        return hexagono;
    }

    public void setHexagono(String hexagono) throws Exception {
        if(hexagono == null || hexagono.isEmpty()){
            throw new Exception("El hexagono no puede estar vacio.");
        }else{
            this.hexagono = hexagono;
        }
        
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) throws Exception {
        if(asiento == null || asiento.isEmpty()){
            throw new Exception("El asiento no puede estar vacio.");
        }else{
            this.asiento = asiento;
        }
        
    }

    public String getResistor() {
        return resistor;
    }

    public void setResistor(String resistor) throws Exception {
        if(resistor == null || resistor.isEmpty()){
            throw new Exception("El resistor no puede estar vacio.");
        }else{
            this.resistor = resistor;
        }
        
    }

    public String getRangoTermico() {
        return rangoTermico;
    }

    public void setRangoTermico(String rangoTermico) throws Exception {
        if(rangoTermico == null || rangoTermico.isEmpty()){
            throw new Exception("El rango termico no puede estar vacio.");
        }else{
            this.rangoTermico = rangoTermico;
        }
        
    }

    public String getPosicionELectrodo() {
        return PosicionELectrodo;
    }

    public void setPosicionELectrodo(String PosicionELectrodo) throws Exception {
        if(PosicionELectrodo == null || PosicionELectrodo.isEmpty()){
            throw new Exception("La posicion del electrodo no puede estar vacia.");
        }else{
            this.PosicionELectrodo = PosicionELectrodo;
        }
        
    }

    public String getCantidadElectrodo() {
        return CantidadElectrodo;
    }

    public void setCantidadElectrodo(String CantidadElectrodo) throws Exception {
        if(CantidadElectrodo == null || CantidadElectrodo.isEmpty()){
            throw new Exception("La cantidad del electrodo no puede estar vacia.");
        }else{
            this.CantidadElectrodo = CantidadElectrodo;
        }
        
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

    public String getLargoRoscaId() {
        return largoRoscaId;
    }

    public void setLargoRoscaId(String largoRoscaId) throws Exception {
        if (largoRoscaId == null || largoRoscaId.isEmpty()) {
            throw new Exception("largoRoscaId vacio.");
        } else {
            this.largoRoscaId = largoRoscaId;
        }
        
    }

    public String getAsientoId() {
        return asientoId;
    }

    public void setAsientoId(String asientoId) throws Exception {
        if (asientoId == null || asientoId.isEmpty()) {
            throw new Exception("asientoId vacio.");
        } else {
            this.asientoId = asientoId;
        }
        
    }

    public String getResistorId() {
        return resistorId;
    }

    public void setResistorId(String resistorId) throws Exception {
        if (resistorId == null || resistorId.isEmpty()) {
            throw new Exception("resistorId vacio.");
        } else {
            this.resistorId = resistorId;
        }
        
    }

    public String getRangoTermicoId() {
        return rangoTermicoId;
    }

    public void setRangoTermicoId(String rangoTermicoId) throws Exception {
        if (rangoTermicoId == null || rangoTermicoId.isEmpty()) {
            throw new Exception("rangoTermicoId vacio.");
        } else {
            this.rangoTermicoId = rangoTermicoId;
        }
        
    }

    public String getPosicionELectrodoId() {
        return PosicionELectrodoId;
    }

    public void setPosicionELectrodoId(String PosicionELectrodoId) throws Exception {
        if (PosicionELectrodoId == null || PosicionELectrodoId.isEmpty()) {
            throw new Exception("PosicionELectrodoId vacio.");
        } else {
            this.PosicionELectrodoId = PosicionELectrodoId;
        }
        
    }

    public String getCantidadElectrodoId() {
        return CantidadElectrodoId;
    }

    public void setCantidadElectrodoId(String CantidadElectrodoId) throws Exception {
        if (CantidadElectrodoId == null || CantidadElectrodoId.isEmpty()) {
            throw new Exception("CantidadElectrodoId vacio.");
        } else {
            this.CantidadElectrodoId = CantidadElectrodoId;
        }
        
    }

    
}
