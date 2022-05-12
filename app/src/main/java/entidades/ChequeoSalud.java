package entidades;
import android.content.ContentValues;

import control.SaludDB;

public class ChequeoSalud {
    private int id;
    private String fechaChequeo;
    private double pesoActual;
    private double alturaActual;
    private double valorImcActual;
    private String mensajeImcActual = "No definido";
    private String estado = "P";
    private String rangoImcId;
    private String usuarioId = "1";

    public ChequeoSalud() {
    }

    public ChequeoSalud(int id, String fechaChequeo, double pesoActual, double alturaActual, double valorImcActual, String mensajeImcActual, String estado, String rangoImcId, String usuarioId) {
        this.id = id;
        this.fechaChequeo = fechaChequeo;
        this.pesoActual = pesoActual;
        this.alturaActual = alturaActual;
        this.valorImcActual = valorImcActual;
        this.mensajeImcActual = mensajeImcActual;
        this.estado = estado;
        this.rangoImcId = rangoImcId;
        this.usuarioId = usuarioId;
    }

    public ChequeoSalud(String fechaChequeo, double pesoActual, double alturaActual, double valorImcActual, String mensajeImcActual, String estado, String rangoImcId, String usuarioId) {
        this.fechaChequeo = fechaChequeo;
        this.pesoActual = pesoActual;
        this.alturaActual = alturaActual;
        this.valorImcActual = valorImcActual;
        this.mensajeImcActual = mensajeImcActual;
        this.estado = estado;
        this.rangoImcId = rangoImcId;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaChequeo() {
        return fechaChequeo;
    }

    public void setFechaChequeo(String fechaChequeo) {
        this.fechaChequeo = fechaChequeo;
    }

    public double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(double pesoActual) {
        this.pesoActual = pesoActual;
    }

    public double getAlturaActual() {
        return alturaActual;
    }

    public void setAlturaActual(double alturaActual) {
        this.alturaActual = alturaActual;
    }

    public double getValorImcActual() {
        return valorImcActual;
    }

    public void setValorImcActual(double valorImcActual) {
        this.valorImcActual = valorImcActual;
    }

    public String getMensajeImcActual() {
        return mensajeImcActual;
    }

    public void setMensajeImcActual(String mensajeImcActual) {
        this.mensajeImcActual = mensajeImcActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRangoImcId() {
        return rangoImcId;
    }

    public void setRangoImcId(String rangoImcId) {
        this.rangoImcId = rangoImcId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ContentValues toContentvalues(){
        ContentValues chequeoSaludValues = new ContentValues();
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.FECHA_CHEQUEO, fechaChequeo);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.PESO_ACTUAL, pesoActual);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.ALTURA_ACTUAL, alturaActual);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.VALOR_IMC_ACTUAL, valorImcActual);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.MENSAJE_IMC_ACTUAL, mensajeImcActual);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.ESTADO, estado);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.RANGOS_IMC_ID, rangoImcId);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.USUARIOS_ID, usuarioId);

        return chequeoSaludValues;

    }

    @Override
    public String toString() {
        return "ChequeoSalud{" +
                "id=" + id +
                ", fechaChequeo='" + fechaChequeo + '\'' +
                ", pesoActual=" + pesoActual +
                ", alturaActual=" + alturaActual +
                ", valorImcActual=" + valorImcActual +
                ", mensajeImcActual='" + mensajeImcActual + '\'' +
                ", estado='" + estado + '\'' +
                ", rangoImcId='" + rangoImcId + '\'' +
                ", usuarioId='" + usuarioId + '\'' +
                '}';
    }
}