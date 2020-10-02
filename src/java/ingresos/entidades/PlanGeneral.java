package ingresos.entidades;

public class PlanGeneral {
private String maestroId;
private String detalleId;
private String tipoPlan;
private String tipoPlanId;
private String valor;
private String ValorId;
private String retener;
private String comentarios;

    public String getMaestroId() {
        return maestroId;
    }

    public void setMaestroId(String maestroId) {
        this.maestroId = maestroId;
    }

    public String getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(String detalleId) {
        this.detalleId = detalleId;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public String getTipoPlanId() {
        return tipoPlanId;
    }

    public void setTipoPlanId(String tipoPlanId) {
        this.tipoPlanId = tipoPlanId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValorId() {
        return ValorId;
    }

    public void setValorId(String ValorId) {
        this.ValorId = ValorId;
    }

    public String getRetener() {
        return retener;
    }

    public void setRetener(String retener) {
        this.retener = retener;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
