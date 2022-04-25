package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class FactorActividad {
    private int id;
    private String factor;
    private String descripcion;

    public FactorActividad() {
    }

    public FactorActividad(int id, String factor, String descripcion) {
        this.id = id;
        this.factor = factor;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();
        valoresEntidad.put(SaludDB.TablaFactorActividad.ID, id);
        valoresEntidad.put(SaludDB.TablaFactorActividad.FACTOR, factor);
        valoresEntidad.put(SaludDB.TablaFactorActividad.DESCRIPCION, descripcion);
        return valoresEntidad;
    }
}
