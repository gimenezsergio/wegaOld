package entidades;

public class ReclamosEncabezado {
    private String id_reclamo;
    private String cliente;
    private String fecha_emision;
    private String fecha_recepcion;
    private String cli_nombre;
    private int aprobado;
    private boolean aprobadoBoolean;
    private String userOwn;
    private int logedUser;
    private String userName;
    private String userFirma;

    @Override
    public String toString() {
        return id_reclamo + " - " + cliente + " - " + fecha_recepcion;
    }

    

    

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(String fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public String getId_reclamo() {
        return id_reclamo;
    }

    public void setId_reclamo(String id_reclamo) {
        this.id_reclamo = id_reclamo;
    }

    public String getCli_nombre() {
        return cli_nombre;
    }

    public void setCli_nombre(String cli_nombre) {
        this.cli_nombre = cli_nombre;
    }

    public int getAprobado() {
        return aprobado;
    }

    public void setAprobado(int paprobado) {
        if (paprobado == 1) {
            aprobadoBoolean = true;
        }else{
            aprobadoBoolean = false;
        }
        this.aprobado = aprobado;
    }

    public int getLogedUser() {
        return logedUser;
    }

    public void setLogedUser(int logedUser) {
        this.logedUser = logedUser;
    }

    public String getUserOwn() {
        return userOwn;
    }

    public void setUserOwn(String userOwn) {
        this.userOwn = userOwn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirma() {
        return userFirma;
    }

    public void setUserFirma(String userFirma) {
        this.userFirma = userFirma;
    }
}
