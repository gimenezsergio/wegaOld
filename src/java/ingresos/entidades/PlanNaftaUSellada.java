package ingresos.entidades;

import java.util.Map;

public class PlanNaftaUSellada {

    private String identificacion;
    private String visual;
    private String lote;
    private String diametroCarcasa;
    private String largoFiltro;
    private String picoEntradaRosca;
    private String picoSalidaRosca;
    private String picoRetornoFiltro;
    private String picoRetornoTanque;
    private String valvulaCanister;
    private String soporteFijacion;
    private Map MedidaDiametroCarcasa;
    private Map MedidalargoFiltro;
    private Map MedidaPicoEntradaRosca;
    private Map MedidaPicoSalidaRosca;
    private Map MedidaPicoRetornoFiltro;
    private Map MedidaPicoRetornoTanque;
    private String identificacionId;
    private String visualId;
    private String loteId;
    private String diametroCarcasaId;
    private String largoFiltroId;
    private String picoEntradaRoscaId;
    private String picoSalidaRoscaId;
    private String picoRetornoFiltroId;
    private String picoRetornoTanqueId;
    private String valvulaCanisterId;
    private String soporteFijacionId;
    private String IdDetalle;

    public PlanNaftaUSellada(
            String identificacion,
            String visual,
            String lote,
            String diametroCarcasa,
            String largoFiltro,
            String picoEntradaRosca,
            String picoSalidaRosca,
            String picoRetornoFiltro,
            String picoRetornoTanque,
            String valvulaCanister,
            String soporteFijacion,
            Map MedidaDiametroCarcasa,
            Map MedidalargoFiltro,
            Map MedidaPicoEntradaRosca,
            Map MedidaPicoSalidaRosca,
            Map MedidaPicoRetornoFiltro,
            Map MedidaPicoRetornoTanque
            
    ) throws Exception {
        setIdentificacion(identificacion);
        setVisual(visual);
        setLote(lote);
        setDiametroCarcasa(diametroCarcasa);
        setLargoFiltro(largoFiltro);
        setPicoEntradaRosca(picoEntradaRosca);
        setPicoSalidaRosca(picoSalidaRosca);
        setPicoRetornoFiltro(picoRetornoFiltro);
        setPicoRetornoTanque(picoRetornoTanque);
        setValvulaCanister(valvulaCanister);
        setSoporteFijacion(soporteFijacion);
        setMedidaDiametroCarcasa(MedidaDiametroCarcasa);
        setMedidalargoFiltro(MedidalargoFiltro);
        setMedidaPicoEntradaRosca(MedidaPicoEntradaRosca);
        setMedidaPicoSalidaRosca(MedidaPicoSalidaRosca);
        setMedidaPicoRetornoFiltro(MedidaPicoRetornoFiltro);
        setMedidaPicoRetornoTanque(MedidaPicoRetornoTanque);
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

    public String getLargoFiltro() {
        return largoFiltro;
    }

    public void setLargoFiltro(String largoFiltro) throws Exception {
        if (largoFiltro == null || largoFiltro.isEmpty()) {
            throw new Exception("Largo Filtro no puede estar vacio.");
        } else {
            this.largoFiltro = largoFiltro;
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

    public String getValvulaCanister() {
        return valvulaCanister;
    }

    public void setValvulaCanister(String valvulaCanister) throws Exception {
        if (valvulaCanister == null || valvulaCanister.isEmpty()) {
            throw new Exception("Valvula Canister no puede estar vacio.");
        } else {
            this.valvulaCanister = valvulaCanister;
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

    public Map getMedidaDiametroCarcasa() {
        return MedidaDiametroCarcasa;
    }

    public void setMedidaDiametroCarcasa(Map MedidaDiametroCarcasa) throws Exception {
        String nom = (String) MedidaDiametroCarcasa.get("diametro_carcasa_nafta_u_sellada_Nom");
        String min = (String) MedidaDiametroCarcasa.get("diametro_carcasa_nafta_u_sellada_Min");
        String max = (String) MedidaDiametroCarcasa.get("diametro_carcasa_nafta_u_sellada_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Diametro Carcasa no pueden estar vacias.");
        } else {
            this.MedidaDiametroCarcasa = MedidaDiametroCarcasa;
        }
    }

    public Map getMedidalargoFiltro() {
        return MedidalargoFiltro;
    }

    public void setMedidalargoFiltro(Map MedidalargoFiltro) throws Exception {
        String nom = (String) MedidalargoFiltro.get("largo_tot_fieltro_nafta_u_sellada_Nom");
        String min = (String) MedidalargoFiltro.get("largo_tot_fieltro_nafta_u_sellada_Min");
        String max = (String) MedidalargoFiltro.get("largo_tot_fieltro_nafta_u_sellada_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida largo Filtro no pueden estar vacias.");
        } else {
            this.MedidalargoFiltro = MedidalargoFiltro;
        }
    }

    public Map getMedidaPicoEntradaRosca() {
        return MedidaPicoEntradaRosca;
    }

    public void setMedidaPicoEntradaRosca(Map MedidaPicoEntradaRosca) throws Exception {
        String nom = (String) MedidaPicoEntradaRosca.get("pico_entrada_nafta_u_sellada_Nom");
        String min = (String) MedidaPicoEntradaRosca.get("pico_entrada_nafta_u_sellada_Min");
        String max = (String) MedidaPicoEntradaRosca.get("pico_entrada_nafta_u_sellada_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Pico Entrada Rosca no pueden estar vacias.");
        } else {
            this.MedidaPicoEntradaRosca = MedidaPicoEntradaRosca;
        }
    }

    public Map getMedidaPicoSalidaRosca() {
        return MedidaPicoSalidaRosca;
    }

    public void setMedidaPicoSalidaRosca(Map MedidaPicoSalidaRosca) throws Exception {
        String nom = (String) MedidaPicoSalidaRosca.get("pico_salida_nafta_u_sellada_Nom");
        String min = (String) MedidaPicoSalidaRosca.get("pico_salida_nafta_u_sellada_Min");
        String max = (String) MedidaPicoSalidaRosca.get("pico_salida_nafta_u_sellada_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Pico Salida Rosca no pueden estar vacias.");
        } else {
            this.MedidaPicoSalidaRosca = MedidaPicoSalidaRosca;
        }
    }

    public Map getMedidaPicoRetornoFiltro() {
        return MedidaPicoRetornoFiltro;
    }

    public void setMedidaPicoRetornoFiltro(Map MedidaPicoRetornoFiltro) throws Exception {
        String nom = (String) MedidaPicoRetornoFiltro.get("pico_ret_fieltro_nafta_u_sellada_Nom");
        String min = (String) MedidaPicoRetornoFiltro.get("pico_ret_fieltro_nafta_u_sellada_Min");
        String max = (String) MedidaPicoRetornoFiltro.get("pico_ret_fieltro_nafta_u_sellada_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Pico Retorno Filtro no pueden estar vacias.");
        } else {
            this.MedidaPicoRetornoFiltro = MedidaPicoRetornoFiltro;
        }
    }

    public Map getMedidaPicoRetornoTanque() {
        return MedidaPicoRetornoTanque;
    }

    public void setMedidaPicoRetornoTanque(Map MedidaPicoRetornoTanque) throws Exception {
        String nom = (String) MedidaPicoRetornoTanque.get("pico_ret_tanque_nafta_u_sellada_Nom");
        String min = (String) MedidaPicoRetornoTanque.get("pico_ret_tanque_nafta_u_sellada_Min");
        String max = (String) MedidaPicoRetornoTanque.get("pico_ret_tanque_nafta_u_sellada_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medida Pico Retorno Tanque no pueden estar vacias.");
        } else {
            this.MedidaPicoRetornoTanque = MedidaPicoRetornoTanque;
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

    public String getLargoFiltroId() {
        return largoFiltroId;
    }

    public void setLargoFiltroId(String largoFiltroId) {
        this.largoFiltroId = largoFiltroId;
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

    public String getValvulaCanisterId() {
        return valvulaCanisterId;
    }

    public void setValvulaCanisterId(String valvulaCanisterId) {
        this.valvulaCanisterId = valvulaCanisterId;
    }

    public String getSoporteFijacionId() {
        return soporteFijacionId;
    }

    public void setSoporteFijacionId(String soporteFijacionId) {
        this.soporteFijacionId = soporteFijacionId;
    }

    public String getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(String IdDetalle) {
        this.IdDetalle = IdDetalle;
    }
}
