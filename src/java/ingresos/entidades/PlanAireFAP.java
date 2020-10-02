package ingresos.entidades;

import java.util.Map;

public class PlanAireFAP {
    private String identificacion;
    private String visual;
    private String lote;
    private String largo1;
    private String largo2;
    private String ancho1;
    private String ancho2;
    private String alto;
    private String hotmel;
    private String manto;
    private String prefiltro;
    private String manija;
    private String filtrante;
    private String identificacionId;
    private String visualId;
    private String loteId;
    private String largo1Id;
    private String largo2Id;
    private String ancho1Id;
    private String ancho2Id;
    private String altoId;
    private String hotmelId;
    private String mantoId;
    private String prefiltroId;
    private String manijaId;
    private String filtranteId;
    private Map medidaLargo1;
    private Map medidaLargo2;
    private Map medidaAncho1;
    private Map medidaAncho2;
    private Map medidaAlto;
    private String IdDetalle;

    public PlanAireFAP(
            String identificacion,
            String visual,
            String lote,
            String largo1,
            String largo2,
            String ancho1,
            String ancho2,
            String alto,
            String hotmel,
            String manto,
            String prefiltro,
            String manija,
            String filtrante,
            Map medidaLargo1,
            Map medidaLargo2,
            Map medidaAncho1,
            Map medidaAncho2,
            Map medidaAlto) throws Exception {
        setIdentificacion(identificacion);
        setVisual(visual);
        setLote(lote);
        setLargo1(largo1);
        setLargo2(largo2);
        setAncho1(ancho1);
        setAncho2(ancho2);
        setAlto(alto);
        setHotmel(hotmel);
        setManto(manto);
        setPrefiltro(prefiltro);
        setManija(manija);
        setFiltrante(filtrante);
        setMedidaLargo1(medidaLargo1);
        setMedidaLargo2(medidaLargo2);
        setMedidaAncho1(medidaAncho1);
        setMedidaAncho2(medidaAncho2);
        setMedidaAlto(medidaAlto);
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

    public String getLargo1() {
        return largo1;
    }

    public void setLargo1(String largo1) throws Exception {
        if (largo1 == null || largo1.isEmpty()) {
            throw new Exception("Largo 1 no puede estar vacio.");
        } else {
            this.largo1 = largo1;
        }
    }

    public String getLargo2() {
        return largo2;
    }

    public void setLargo2(String largo2) throws Exception {
        if (largo2 == null || largo2.isEmpty()) {
            throw new Exception("Largo 2 no puede estar vacio.");
        } else {
            this.largo2 = largo2;
        }
    }

    public String getAncho1() {
        return ancho1;
    }

    public void setAncho1(String ancho1) throws Exception {
        if (ancho1 == null || ancho1.isEmpty()) {
            throw new Exception("Ancho 1 no puede estar vacio.");
        } else {
            this.ancho1 = ancho1;
        }
    }

    public String getAncho2() {
        return ancho2;
    }

    public void setAncho2(String ancho2) throws Exception {
        if (ancho2 == null || ancho2.isEmpty()) {
            throw new Exception("Ancho 2 no puede estar vacio.");
        } else {
            this.ancho2 = ancho2;
        }
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) throws Exception {
        if (alto == null || alto.isEmpty()) {
            throw new Exception("Alto no puede estar vacio.");
        } else {
            this.alto = alto;
        }
    }

    public String getHotmel() {
        return hotmel;
    }

    public void setHotmel(String hotmel) throws Exception {
        if (hotmel == null || hotmel.isEmpty()) {
            throw new Exception("Hotmel no puede estar vacio.");
        } else {
            this.hotmel = hotmel;
        }
    }

    public String getManto() {
        return manto;
    }

    public void setManto(String manto) throws Exception {
        if (manto == null || manto.isEmpty()) {
            throw new Exception("Manto no puede estar vacio.");
        } else {
            this.manto = manto;
        }
    }

    public String getPrefiltro() {
        return prefiltro;
    }

    public void setPrefiltro(String prefiltro) throws Exception {
        if (prefiltro == null || prefiltro.isEmpty()) {
            throw new Exception("Prefiltro no puede estar vacio.");
        } else {
            this.prefiltro = prefiltro;
        }
    }

    public String getManija() {
        return manija;
    }

    public void setManija(String manija) throws Exception {
        if (manija == null || manija.isEmpty()) {
            throw new Exception("Manija no puede estar vacio.");
        } else {
            this.manija = manija;
        }
    }

    public String getFiltrante() {
        return filtrante;
    }

    public void setFiltrante(String filtrante) throws Exception {
        if (filtrante == null || filtrante.isEmpty()) {
            throw new Exception("Elemento filtrante no puede estar vacio.");
        } else {
            this.filtrante = filtrante;
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

    public String getLargo1Id() {
        return largo1Id;
    }

    public void setLargo1Id(String largo1Id) {
        this.largo1Id = largo1Id;
    }

    public String getLargo2Id() {
        return largo2Id;
    }

    public void setLargo2Id(String largo2Id) {
        this.largo2Id = largo2Id;
    }

    public String getAncho1Id() {
        return ancho1Id;
    }

    public void setAncho1Id(String ancho1Id) {
        this.ancho1Id = ancho1Id;
    }

    public String getAncho2Id() {
        return ancho2Id;
    }

    public void setAncho2Id(String ancho2Id) {
        this.ancho2Id = ancho2Id;
    }

    public String getAltoId() {
        return altoId;
    }

    public void setAltoId(String altoId) {
        this.altoId = altoId;
    }

    public String getHotmelId() {
        return hotmelId;
    }

    public void setHotmelId(String hotmelId) {
        this.hotmelId = hotmelId;
    }

    public String getMantoId() {
        return mantoId;
    }

    public void setMantoId(String mantoId) {
        this.mantoId = mantoId;
    }

    public String getPrefiltroId() {
        return prefiltroId;
    }

    public void setPrefiltroId(String prefiltroId) {
        this.prefiltroId = prefiltroId;
    }

    public String getManijaId() {
        return manijaId;
    }

    public void setManijaId(String manijaId) {
        this.manijaId = manijaId;
    }

    public String getFiltranteId() {
        return filtranteId;
    }

    public void setFiltranteId(String filtranteId) {
        this.filtranteId = filtranteId;
    }

    public Map getMedidaLargo1() {
        return medidaLargo1;
    }

    public void setMedidaLargo1(Map medidaLargo1) throws Exception {
        String nom = (String) medidaLargo1.get("largo1_FAP_Nom");
        String min = (String) medidaLargo1.get("largo1_FAP_Min");
        String max = (String) medidaLargo1.get("largo1_FAP_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de largo 1 no pueden estar vacias.");
        } else {
            this.medidaLargo1 = medidaLargo1;
        }
        
    }

    public Map getMedidaLargo2() {
        return medidaLargo2;
    }

    public void setMedidaLargo2(Map medidaLargo2) throws Exception {
        String nom = (String) medidaLargo2.get("largo2_FAP_Nom");
        String min = (String) medidaLargo2.get("largo2_FAP_Min");
        String max = (String) medidaLargo2.get("largo2_FAP_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de largo 2 no pueden estar vacias.");
        } else {
            this.medidaLargo2 = medidaLargo2;
        }
        
    }

    public Map getMedidaAncho1() {
        return medidaAncho1;
    }

    public void setMedidaAncho1(Map medidaAncho1) throws Exception {
        String nom = (String) medidaAncho1.get("ancho1_FAP_Nom");
        String min = (String) medidaAncho1.get("ancho1_FAP_Min");
        String max = (String) medidaAncho1.get("ancho1_FAP_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de ancho 1 no pueden estar vacias.");
        } else {
            this.medidaAncho1 = medidaAncho1;
        }
    }

    public Map getMedidaAncho2() {
        return medidaAncho2;
    }

    public void setMedidaAncho2(Map medidaAncho2) throws Exception {
        String nom = (String) medidaAncho2.get("ancho2_FAP_Nom");
        String min = (String) medidaAncho2.get("ancho2_FAP_Min");
        String max = (String) medidaAncho2.get("ancho2_FAP_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de ancho 2 no pueden estar vacias.");
        } else {
            this.medidaAncho2 = medidaAncho2;
        }
    }

    public Map getMedidaAlto() {
        return medidaAlto;
    }

    public void setMedidaAlto(Map medidaAlto) throws Exception {
        String nom = (String) medidaAlto.get("alto_FAP_Nom");
        String min = (String) medidaAlto.get("alto_FAP_Min");
        String max = (String) medidaAlto.get("alto_FAP_Max");
        if ((nom == null || nom.isEmpty()) || (min == null || min.isEmpty()) || (max == null || max.isEmpty())) {
            throw new Exception("Medidas de alto no pueden estar vacias.");
        } else {
            this.medidaAlto = medidaAlto;
        }
    }

    public String getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(String IdDetalle) {
        this.IdDetalle = IdDetalle;
    }
    
    
}
