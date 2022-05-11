package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class DetalleDeportePorFactor {
    private int id;
    private double energiaGastada;
    private int duracion;
    private int factorActividadId;
    private int deporteId;

    public DetalleDeportePorFactor() {
    }

    public DetalleDeportePorFactor(int id, double energiaGastada, int duracion, int factorActividadId, int deporteId) {
        this.id = id;
        this.energiaGastada = energiaGastada;
        this.duracion = duracion;
        this.factorActividadId = factorActividadId;
        this.deporteId = deporteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getEnergiaGastada() {
        return energiaGastada;
    }

    public void setEnergiaGastada(double energiaGastada) {
        this.energiaGastada = energiaGastada;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getFactorActividadId() {
        return factorActividadId;
    }

    public void setFactorActividadId(int factorActividadId) {
        this.factorActividadId = factorActividadId;
    }

    public int getDeporteId() {
        return deporteId;
    }

    public void setDeporteId(int deporteId) {
        this.deporteId = deporteId;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();
        valoresEntidad.put(SaludDB.TablaDetalleDeportePorFactor.ENERGIA_GASTADA, energiaGastada);
        valoresEntidad.put(SaludDB.TablaDetalleDeportePorFactor.DURACION, duracion);
        valoresEntidad.put(SaludDB.TablaDetalleDeportePorFactor.FACTOR_ACTIVIDAD_ID, factorActividadId);
        valoresEntidad.put(SaludDB.TablaDetalleDeportePorFactor.DEPORTE_ID, deporteId);
        return valoresEntidad;
    }
}
