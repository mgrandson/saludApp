package entidades;
import android.content.ContentValues;

import control.SaludDB;

public class ChequeoSalud {
    private int id;
    private String fechaChequeo;
    private double pesoActual;
    private double alturaActual;
    private double valorImcActual;
    private String mensajeImcActual;
    private String rangoImcId;
    private String usuarioId;

    public ChequeoSalud() {
    }

    public ChequeoSalud(int id, String fechaChequeo, double pesoActual, double alturaActual, double valorImcActual, String mensajeImcActual, String rangoImcId, String usuarioId) {
        this.id = id;
        this.fechaChequeo = fechaChequeo;
        this.pesoActual = pesoActual;
        this.alturaActual = alturaActual;
        this.valorImcActual = valorImcActual;
        this.mensajeImcActual = mensajeImcActual;
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
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.ID_CHEQUEO, id);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.FECHA_CHEQUEO, fechaChequeo);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.ID_CHEQUEO, pesoActual);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.ID_CHEQUEO, alturaActual);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.ID_CHEQUEO, valorImcActual);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.ID_CHEQUEO, mensajeImcActual);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.ID_CHEQUEO, rangoImcId);
        chequeoSaludValues.put(SaludDB.TablaChequeoSalud.ID_CHEQUEO, usuarioId);

        return chequeoSaludValues;

    }
}