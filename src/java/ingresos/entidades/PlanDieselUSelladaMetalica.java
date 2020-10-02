package ingresos.entidades;

import java.util.Map;

public class PlanDieselUSelladaMetalica {

    
    private String identificacion;
    private String visual;
    private String lote;
    private String diametroCarcasa;
    private String altoTotal;
    private String roscaBrida;
    private String diametroIntJunta;
    private String altoJunta;
    private String dureza;
    private String roscaPurgador;
    private String RoscaVaso;
    private String vasoDecantador;
    private String picoEntradaRosca;
    private String picoSalidaRosca;
    private String picoRetornoFiltro;
    private String picoRetornoTanque;
    private String temperatura;
    private String calefactor;
    private String elementoFiltrante;
    private Map medidaDiametroCarcasa;
    private Map medidaAltoTotal;
    private Map medidaRoscaBrida;
    private Map medidaDiametroIntJunta;
    private Map medidaAltoJunta;
    private Map medidaDureza;
    private Map medidaPicoEntradaRosca;
    private Map medidaPicoSalidaRosca;
    private Map medidaPicoRetornoFiltro;
    private Map medidaPicoRetornoTanque;
    private String IdDetalle;
    
    private String identificacionId;
    private String visualId;
    private String loteId;
    private String diametroCarcasaId;
    private String altoTotalId;
    private String roscaBridaId;
    private String diametroIntJuntaId;
    private String altoJuntaId;
    private String durezaId;
    private String roscaPurgadorId;
    private String RoscaVasoId;
    private String vasoDecantadorId;
    private String picoEntradaRoscaId;
    private String picoSalidaRoscaId;
    private String picoRetornoFiltroId;
    private String picoRetornoTanqueId;
    private String temperaturaId;
    private String calefactorId;
    private String elementoFiltranteId;

    public PlanDieselUSelladaMetalica(
            String identificacion,
            String visual,
            String lote,
            String diametroCarcasa,
            String altoTotal,
            String roscaBrida,
            String diametroIntJunta,
            String altoJunta,
            String dureza,
            String roscaPurgador,
            String RoscaVaso,
            String vasoDecantador,
            String picoEntradaRosca,
            String picoSalidaRosca,
            String picoRetornoFiltro,
            String picoRetornoTanque,
            String temperatura,
            String calefactor,
            String elementoFiltrante,
            Map medidaDiametroCarcasa,
            Map medidaAltoTotal,
            Map medidaRoscaBrida,
            Map medidaDiametroIntJunta,
            Map medidaAltoJunta,
            Map medidaDureza,
            Map medidaPicoEntradaRosca,
            Map medidaPicoSalidaRosca,
            Map medidaPicoRetornoFiltro,
            Map medidaPicoRetornoTanque
    ) throws Exception {
        setIdentificacion(identificacion);
             setVisual(visual);
             setLote(lote);
             setDiametroCarcasa(diametroCarcasa);
             setAltoTotal(altoTotal);
             setRoscaBrida(roscaBrida);
             setDiametroIntJunta(diametroIntJunta);
             setAltoJunta(altoJunta);
             setDureza(dureza);
             setRoscaPurgador(roscaPurgador);
             setRoscaVaso(RoscaVaso);
             setVasoDecantador(vasoDecantador);
             setPicoEntradaRosca(picoEntradaRosca);
             setPicoSalidaRosca(picoSalidaRosca);
             setPicoRetornoFiltro(picoRetornoFiltro);
             setPicoRetornoTanque(picoRetornoTanque);
             setTemperatura(temperatura);
             setCalefactor(calefactor);
             setElementoFiltrante(elementoFiltrante);
             setMedidaDiametroCarcasa(medidaDiametroCarcasa);
             setMedidaAltoTotal(medidaAltoTotal);
             setMedidaRoscaBrida(medidaRoscaBrida);
             setmedidaDiametroIntJunta(medidaDiametroIntJunta);
             setMedidaAltoJunta(medidaAltoJunta);
             setMedidaDureza(medidaDureza);
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

    public String getAltoTotal() {
        return altoTotal;
    }

    public void setAltoTotal(String altoTotal) throws Exception {
        if (altoTotal == null || altoTotal.isEmpty()) {
            throw new Exception("Alto total no puede estar vacio.");
        } else {
            this.altoTotal = altoTotal;
        }
    }

    public String getRoscaBrida() {
        return roscaBrida;
    }

    public void setRoscaBrida(String roscaBrida) throws Exception {
        if (roscaBrida == null || roscaBrida.isEmpty()) {
            throw new Exception("Rosca Brida no puede estar vacio.");
        } else {
            this.roscaBrida = roscaBrida;
        }
    }

    public String getDiametroIntJunta() {
        return diametroIntJunta;
    }

    public void setDiametroIntJunta(String diametro) throws Exception {
        if (diametro == null || diametro.isEmpty()) {
            throw new Exception("Diametro Interior Junta no puede estar vacio.");
        } else {
            this.diametroIntJunta = diametro;
        }
    }

    public String getAltoJunta() {
        return altoJunta;
    }

    public void setAltoJunta(String altoJunta) throws Exception {
        if (altoJunta == null || altoJunta.isEmpty()) {
            throw new Exception("Alto Junta no puede estar vacio.");
        } else {
            this.altoJunta = altoJunta;
        }
    }

    public String getDureza() {
        return dureza;
    }

    public void setDureza(String dureza) throws Exception {
        if (dureza == null || dureza.isEmpty()) {
            throw new Exception("Dureza no puede estar vacio.");
        } else {
            this.dureza = dureza;
        }
    }

    public String getRoscaPurgador() {
        return roscaPurgador;
    }

    public void setRoscaPurgador(String roscaPurgador) throws Exception {
        if (roscaPurgador == null || roscaPurgador.isEmpty()) {
            throw new Exception("Rosca Purgador no puede estar vacio.");
        } else {
            this.roscaPurgador = roscaPurgador;
        }
    }

    public String getRoscaVaso() {
        return RoscaVaso;
    }

    public void setRoscaVaso(String RoscaVaso) throws Exception {
        if (RoscaVaso == null || RoscaVaso.isEmpty()) {
            throw new Exception("Rosca Vaso no puede estar vacio.");
        } else {
            this.RoscaVaso = RoscaVaso;
        }
    }

    public String getVasoDecantador() {
        return vasoDecantador;
    }

    public void setVasoDecantador(String vasoDecantador) throws Exception {
        if (vasoDecantador == null || vasoDecantador.isEmpty()) {
            throw new Exception("Vaso Decantador no puede estar vacio.");
        } else {
            this.vasoDecantador = vasoDecantador;
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

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) throws Exception {
        if (temperatura == null || temperatura.isEmpty()) {
            throw new Exception("Temperatura no puede estar vacio.");
        } else {
            this.temperatura = temperatura;
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
        String nom = (String) medidaDiametroCarcasa.get("diametroCarcasa_DieselSelladaMetalico_Nom");
        String min = (String) medidaDiametroCarcasa.get("diametroCarcasa_DieselSelladaMetalico_Min");
        String max = (String) medidaDiametroCarcasa.get("diametroCarcasa_DieselSelladaMetalico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Diametro Carcasa no pueden estar vacias.");
        } else {
            this.medidaDiametroCarcasa = medidaDiametroCarcasa;
        }
    }

    public Map getMedidaAltoTotal() {
        return medidaAltoTotal;
    }

    public void setMedidaAltoTotal(Map medidaAltoTotal) throws Exception {
        String nom = (String) medidaAltoTotal.get("altoTotal_DieselSelladaMetalico_Nom");
        String min = (String) medidaAltoTotal.get("altoTotal_DieselSelladaMetalico_Min");
        String max = (String) medidaAltoTotal.get("altoTotal_DieselSelladaMetalico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Alto Total no pueden estar vacias.");
        } else {
            this.medidaAltoTotal = medidaAltoTotal;
        }
    }

    public Map getMedidaRoscaBrida() {
        return medidaRoscaBrida;
    }

    public void setMedidaRoscaBrida(Map medidaRoscaBrida) throws Exception {
        String nom = (String) medidaRoscaBrida.get("rosca_brida_DieselSelladaMetalico_Nom");
        String min = (String) medidaRoscaBrida.get("rosca_brida_DieselSelladaMetalico_Min");
        String max = (String) medidaRoscaBrida.get("rosca_brida_DieselSelladaMetalico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Rosca Brida no pueden estar vacias.");
        } else {
            this.medidaRoscaBrida = medidaRoscaBrida;
        }
    }

    public Map getmedidaDiametroIntJunta() {
        return medidaDiametroIntJunta;
    }

    public void setmedidaDiametroIntJunta(Map medidaDiametroIntJunta) throws Exception {
        String nom = (String) medidaDiametroIntJunta.get("diametroInterior_DieselSelladaMetalico_Nom");
        String min = (String) medidaDiametroIntJunta.get("diametroInterior_DieselSelladaMetalico_Min");
        String max = (String) medidaDiametroIntJunta.get("diametroInterior_DieselSelladaMetalico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Diametro Interior Junta no pueden estar vacias.");
        } else {
            this.medidaDiametroIntJunta = medidaDiametroIntJunta;
        }
    }

    public Map getMedidaAltoJunta() {
        return medidaAltoJunta;
    }

    public void setMedidaAltoJunta(Map medidaAltoJunta) throws Exception {
        String nom = (String) medidaAltoJunta.get("altoJunta_DieselSelladaMetalico_Nom");
        String min = (String) medidaAltoJunta.get("altoJunta_DieselSelladaMetalico_Min");
        String max = (String) medidaAltoJunta.get("altoJunta_DieselSelladaMetalico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Alto Junta no pueden estar vacias.");
        } else {
            this.medidaAltoJunta = medidaAltoJunta;
        }
    }

    public Map getMedidaDureza() {
        return medidaDureza;
    }

    public void setMedidaDureza(Map medidaDureza) throws Exception {
        String nom = (String) medidaDureza.get("dureza_DieselSelladaMetalico_Nom");
        String min = (String) medidaDureza.get("dureza_DieselSelladaMetalico_Min");
        String max = (String) medidaDureza.get("dureza_DieselSelladaMetalico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Dureza no pueden estar vacias.");
        } else {
            this.medidaDureza = medidaDureza;
        }
    }

    public Map getMedidaPicoEntradaRosca() {
        return medidaPicoEntradaRosca;
    }

    public void setMedidaPicoEntradaRosca(Map medidaPicoEntradaRosca) throws Exception {
        String nom = (String) medidaPicoEntradaRosca.get("picoEntrada_DieselSelladaMetalico_Nom");
        String min = (String) medidaPicoEntradaRosca.get("picoEntrada_DieselSelladaMetalico_Min");
        String max = (String) medidaPicoEntradaRosca.get("picoEntrada_DieselSelladaMetalico_Max");
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
        String nom = (String) medidaPicoSalidaRosca.get("picoSalida_DieselSelladaMetalico_Nom");
        String min = (String) medidaPicoSalidaRosca.get("picoSalida_DieselSelladaMetalico_Min");
        String max = (String) medidaPicoSalidaRosca.get("picoSalida_DieselSelladaMetalico_Max");
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
        String nom = (String) medidaPicoRetornoFiltro.get("picoRetornoFiltro_DieselSelladaMetalico_Nom");
        String min = (String) medidaPicoRetornoFiltro.get("picoRetornoFiltro_DieselSelladaMetalico_Min");
        String max = (String) medidaPicoRetornoFiltro.get("picoRetornoFiltro_DieselSelladaMetalico_Max");
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
        String nom = (String) medidaPicoRetornoTanque.get("picoRetornoTanque_DieselSelladaMetalico_Nom");
        String min = (String) medidaPicoRetornoTanque.get("picoRetornoTanque_DieselSelladaMetalico_Min");
        String max = (String) medidaPicoRetornoTanque.get("picoRetornoTanque_DieselSelladaMetalico_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Pico Retorno Tanque no pueden estar vacias.");
        } else {
            this.medidaPicoRetornoTanque = medidaPicoRetornoTanque;
        }
    }

    public String getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(String IdDetalle) {
        this.IdDetalle = IdDetalle;
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

    public String getAltoTotalId() {
        return altoTotalId;
    }

    public void setAltoTotalId(String altoTotalId) {
        this.altoTotalId = altoTotalId;
    }

    public String getRoscaBridaId() {
        return roscaBridaId;
    }

    public void setRoscaBridaId(String roscaBridaId) {
        this.roscaBridaId = roscaBridaId;
    }

    public String getDiametroIntJuntaId() {
        return diametroIntJuntaId;
    }

    public void setDiametroIntJuntaId(String diametroIntJuntaId) {
        this.diametroIntJuntaId = diametroIntJuntaId;
    }

    public String getAltoJuntaId() {
        return altoJuntaId;
    }

    public void setAltoJuntaId(String altoJuntaId) {
        this.altoJuntaId = altoJuntaId;
    }

    public String getDurezaId() {
        return durezaId;
    }

    public void setDurezaId(String durezaId) {
        this.durezaId = durezaId;
    }

    public String getRoscaPurgadorId() {
        return roscaPurgadorId;
    }

    public void setRoscaPurgadorId(String roscaPurgadorId) {
        this.roscaPurgadorId = roscaPurgadorId;
    }

    public String getRoscaVasoId() {
        return RoscaVasoId;
    }

    public void setRoscaVasoId(String RoscaVasoId) {
        this.RoscaVasoId = RoscaVasoId;
    }

    public String getVasoDecantadorId() {
        return vasoDecantadorId;
    }

    public void setVasoDecantadorId(String vasoDecantadorId) {
        this.vasoDecantadorId = vasoDecantadorId;
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

    public String getTemperaturaId() {
        return temperaturaId;
    }

    public void setTemperaturaId(String temperaturaId) {
        this.temperaturaId = temperaturaId;
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
}
