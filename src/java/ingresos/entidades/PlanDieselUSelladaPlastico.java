package ingresos.entidades;

import java.util.Map;

public class PlanDieselUSelladaPlastico {

    private String identificacion;
    private String visual;
    private String lote;
    private String diametroCarcasa;
    private String altoTotalFiltro;
    private String picoEntradaRosca;
    private String picoSalidaRosca;
    private String picoRetornoFiltro;
    private String picoRetornoTanque;
    private String soporte;
    private String controlTemperatura;
    private String calefactor;
    private String elementoFiltrante;
    private Map medidaDiametroCarcasa;
    private Map medidaAltoTotalFiltro;
    private Map medidaPicoEntradaRosca;
    private Map medidaPicoSalidaRosca;
    private Map medidaPicoRetornoFiltro;
    private Map medidaPicoRetornoTanque;
    private String identificacionId;
    private String visualId;
    private String loteId;
    private String diametroCarcasaId;
    private String altoTotalFiltroId;
    private String picoEntradaRoscaId;
    private String picoSalidaRoscaId;
    private String picoRetornoFiltroId;
    private String picoRetornoTanqueId;
    private String soporteId;
    private String controlTemperaturaId;
    private String calefactorId;
    private String elementoFiltranteId;
    private String IdDetalle;

    public PlanDieselUSelladaPlastico(
            String identificacion,
            String visual,
            String lote,
            String diametroCarcasa,
            String altoTotalFiltro,
            String picoEntradaRosca,
            String picoSalidaRosca,
            String picoRetornoFiltro,
            String picoRetornoTanque,
            String soporte,
            String controlTemperatura,
            String calefactor,
            String elementoFiltrante,
            Map medidaDiametroCarcasa,
            Map medidaAltoTotalFiltro,
            Map medidaPicoEntradaRosca,
            Map medidaPicoSalidaRosca,
            Map medidaPicoRetornoFiltro,
            Map medidaPicoRetornoTanque
    ) throws Exception {
        setIdentificacion(identificacion);
        setVisual(visual);
        setLote(lote);
        setDiametroCarcasa(diametroCarcasa);
        setAltoTotalFiltro(altoTotalFiltro);
        setPicoEntradaRosca(picoEntradaRosca);
        setPicoSalidaRosca(picoSalidaRosca);
        setPicoRetornoFiltro(picoRetornoFiltro);
        setPicoRetornoTanque(picoRetornoTanque);
        setSoporte(soporte);
        setControlTemperatura(controlTemperatura);
        setCalefactor(calefactor);
        setElementoFiltrante(elementoFiltrante);
        setMedidaDiametroCarcasa(medidaDiametroCarcasa);
        setMedidaAltoTotalFiltro(medidaAltoTotalFiltro);
        setMedidaPicoEntradaRosca(medidaPicoEntradaRosca);
        setMedidaPicoSalidaRosca(medidaPicoSalidaRosca);
        setMedidaPicoRetornoFiltro(medidaPicoRetornoFiltro);
        setMedidaPicoRetornoTanque(medidaPicoRetornoTanque);
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

    public String getDiametroCarcasa() {
        return diametroCarcasa;
    }

    public void setDiametroCarcasa(String diametroCarcasa) throws Exception {
        if (diametroCarcasa == null || diametroCarcasa.isEmpty()) {
            throw new Exception("Diametro Carcasa no puede estar vacio.");
        } else {
            this.diametroCarcasa = diametroCarcasa;
        }
    }

    public String getAltoTotalFiltro() {
        return altoTotalFiltro;
    }

    public void setAltoTotalFiltro(String altoTotalFiltro) throws Exception {
        if (altoTotalFiltro == null || altoTotalFiltro.isEmpty()) {
            throw new Exception("Alto total Filtro no puede estar vacio.");
        } else {
            this.altoTotalFiltro = altoTotalFiltro;
        }
    }

    public String getPicoEntradaRosca() {
        return picoEntradaRosca;
    }

    public void setPicoEntradaRosca(String picoEntradaRosca) throws Exception {
       if (picoEntradaRosca == null || picoEntradaRosca.isEmpty()) {
            throw new Exception("Pico Entrada Rosca no puede estar vacio.");
        } else {
            this.picoEntradaRosca = picoEntradaRosca;
        }
    }

    public String getPicoSalidaRosca() {
        return picoSalidaRosca;
    }

    public void setPicoSalidaRosca(String picoSalidaRosca) throws Exception {
        if (picoSalidaRosca == null || picoSalidaRosca.isEmpty()) {
            throw new Exception("Pico Salida Rosca no puede estar vacio.");
        } else {
            this.picoSalidaRosca = picoSalidaRosca;
        }
    }

    public String getPicoRetornoFiltro() {
        return picoRetornoFiltro;
    }

    public void setPicoRetornoFiltro(String picoRetornoFiltro) throws Exception {
        if (picoRetornoFiltro == null || picoRetornoFiltro.isEmpty()) {
            throw new Exception("Pico Retorno Filtro no puede estar vacio.");
        } else {
            this.picoRetornoFiltro = picoRetornoFiltro;
        }
    }

    public String getPicoRetornoTanque() {
        return picoRetornoTanque;
    }

    public void setPicoRetornoTanque(String picoRetornoTanque) throws Exception {
        if (picoRetornoTanque == null || picoRetornoTanque.isEmpty()) {
            throw new Exception("Pico Retorno Tanque no puede estar vacio.");
        } else {
            this.picoRetornoTanque = picoRetornoTanque;
        }
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) throws Exception {
        if (soporte == null || soporte.isEmpty()) {
            throw new Exception("Soporte no puede estar vacio.");
        } else {
            this.soporte = soporte;
        }
    }

    public String getControlTemperatura() {
        return controlTemperatura;
    }

    public void setControlTemperatura(String controlTemperatura) throws Exception {
        if (controlTemperatura == null || controlTemperatura.isEmpty()) {
            throw new Exception("Control Temperatura no puede estar vacio.");
        } else {
            this.controlTemperatura = controlTemperatura;
        }
    }

    public String getCalefactor() {
        return calefactor;
    }

    public void setCalefactor(String calefactor) throws Exception {
        if (calefactor == null || calefactor.isEmpty()) {
            throw new Exception("Calefactor no puede estar vacio.");
        } else {
            this.calefactor = calefactor;
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

    public Map getMedidaDiametroCarcasa() {
        return medidaDiametroCarcasa;
    }

    public void setMedidaDiametroCarcasa(Map medidaDiametroCarcasa) throws Exception {
        String nom = (String) medidaDiametroCarcasa.get("diametroCarcasa_DieselSelladaPlastico_Nom");
        String min = (String) medidaDiametroCarcasa.get("diametroCarcasa_DieselSelladaPlastico_Min");
        String max = (String) medidaDiametroCarcasa.get("diametroCarcasa_DieselSelladaPlastico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Diametro Carcasa no pueden estar vacias.");
        } else {
            this.medidaDiametroCarcasa = medidaDiametroCarcasa;
        }
    }

    public Map getMedidaAltoTotalFiltro() {
        return medidaAltoTotalFiltro;
    }

    public void setMedidaAltoTotalFiltro(Map medidaAltoTotalFiltro) throws Exception {
        String nom = (String) medidaAltoTotalFiltro.get("altoTotal_DieselSelladaPlastico_Nom");
        String min = (String) medidaAltoTotalFiltro.get("altoTotal_DieselSelladaPlastico_Min");
        String max = (String) medidaAltoTotalFiltro.get("altoTotal_DieselSelladaPlastico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Alto Filtro no pueden estar vacias.");
        } else {
            this.medidaAltoTotalFiltro = medidaAltoTotalFiltro;
        }
    }

    public Map getMedidaPicoEntradaRosca() {
        return medidaPicoEntradaRosca;
    }

    public void setMedidaPicoEntradaRosca(Map medidaPicoEntradaRosca) throws Exception {
        String nom = (String) medidaPicoEntradaRosca.get("picoEntrada_DieselSelladaPlastico_Nom");
        String min = (String) medidaPicoEntradaRosca.get("picoEntrada_DieselSelladaPlastico_Min");
        String max = (String) medidaPicoEntradaRosca.get("picoEntrada_DieselSelladaPlastico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Pico Entrada Rosca no pueden estar vacias.");
        } else {
            this.medidaPicoEntradaRosca = medidaPicoEntradaRosca;
        }
    }

    public Map getMedidaPicoSalidaRosca() {
        return medidaPicoSalidaRosca;
    }

    public void setMedidaPicoSalidaRosca(Map medidaPicoSalidaRosca) throws Exception {
        String nom = (String) medidaPicoSalidaRosca.get("picoSalida_DieselSelladaPlastico_Nom");
        String min = (String) medidaPicoSalidaRosca.get("picoSalida_DieselSelladaPlastico_Min");
        String max = (String) medidaPicoSalidaRosca.get("picoSalida_DieselSelladaPlastico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Pico Salida Rosca no pueden estar vacias.");
        } else {
            this.medidaPicoSalidaRosca = medidaPicoSalidaRosca;
        }
    }

    public Map getMedidaPicoRetornoFiltro() {
        return medidaPicoRetornoFiltro;
    }

    public void setMedidaPicoRetornoFiltro(Map medidaPicoRetornoFiltro) throws Exception {
        String nom = (String) medidaPicoRetornoFiltro.get("picoRetornoFiltro_DieselSelladaPlastico_Nom");
        String min = (String) medidaPicoRetornoFiltro.get("picoRetornoFiltro_DieselSelladaPlastico_Min");
        String max = (String) medidaPicoRetornoFiltro.get("picoRetornoFiltro_DieselSelladaPlastico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Pico Retorno Filtro no pueden estar vacias.");
        } else {
            this.medidaPicoRetornoFiltro = medidaPicoRetornoFiltro;
        }
    }

    public Map getMedidaPicoRetornoTanque() {
        return medidaPicoRetornoTanque;
    }

    public void setMedidaPicoRetornoTanque(Map medidaPicoRetornoTanque) throws Exception {
        String nom = (String) medidaPicoRetornoTanque.get("picoRetornoTanque_DieselSelladaPlastico_Nom");
        String min = (String) medidaPicoRetornoTanque.get("picoRetornoTanque_DieselSelladaPlastico_Min");
        String max = (String) medidaPicoRetornoTanque.get("picoRetornoTanque_DieselSelladaPlastico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Pico Retorno Tanque no pueden estar vacias.");
        } else {
            this.medidaPicoRetornoTanque = medidaPicoRetornoTanque;
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

    public String getDiametroCarcasaId() {
        return diametroCarcasaId;
    }

    public void setDiametroCarcasaId(String diametroCarcasaId) {
        this.diametroCarcasaId = diametroCarcasaId;
    }

    public String getAltoTotalFiltroId() {
        return altoTotalFiltroId;
    }

    public void setAltoTotalFiltroId(String altoTotalFiltroId) {
        this.altoTotalFiltroId = altoTotalFiltroId;
    }

    public String getPicoEntradaRoscaId() {
        return picoEntradaRoscaId;
    }

    public void setPicoEntradaRoscaId(String picoEntradaRoscaId) {
        this.picoEntradaRoscaId = picoEntradaRoscaId;
    }

    public String getPicoSalidaRoscaId() {
        return picoSalidaRoscaId;
    }

    public void setPicoSalidaRoscaId(String picoSalidaRoscaId) {
        this.picoSalidaRoscaId = picoSalidaRoscaId;
    }

    public String getPicoRetornoFiltroId() {
        return picoRetornoFiltroId;
    }

    public void setPicoRetornoFiltroId(String picoRetornoFiltroId) {
        this.picoRetornoFiltroId = picoRetornoFiltroId;
    }

    public String getPicoRetornoTanqueId() {
        return picoRetornoTanqueId;
    }

    public void setPicoRetornoTanqueId(String picoRetornoTanqueId) {
        this.picoRetornoTanqueId = picoRetornoTanqueId;
    }

    public String getSoporteId() {
        return soporteId;
    }

    public void setSoporteId(String soporteId) {
        this.soporteId = soporteId;
    }

    public String getControlTemperaturaId() {
        return controlTemperaturaId;
    }

    public void setControlTemperaturaId(String controlTemperaturaId) {
        this.controlTemperaturaId = controlTemperaturaId;
    }

    public String getCalefactorId() {
        return calefactorId;
    }

    public void setCalefactorId(String calefactorId) {
        this.calefactorId = calefactorId;
    }

    public String getElementoFiltranteId() {
        return elementoFiltranteId;
    }

    public void setElementoFiltranteId(String elementoFiltranteId) {
        this.elementoFiltranteId = elementoFiltranteId;
    }

    public String getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(String IdDetalle) {
        this.IdDetalle = IdDetalle;
    }
}
