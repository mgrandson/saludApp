package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class RangoImc {
    private int id;
    private String sexoImc;
    private int rangoAlturaId;
    private int rangoPesoId;

    public RangoImc() {
    }

    public RangoImc(int id, String sexoImc, int rangoAlturaId, int rangoPesoId) {
        this.id = id;
        this.sexoImc = sexoImc;
        this.rangoAlturaId = rangoAlturaId;
        this.rangoPesoId = rangoPesoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSexoImc() {
        return sexoImc;
    }

    public void setSexoImc(String sexoImc) {
        this.sexoImc = sexoImc;
    }

    public int getRangoAlturaId() {
        return rangoAlturaId;
    }

    public void setRangoAlturaId(int rangoAlturaId) {
        this.rangoAlturaId = rangoAlturaId;
    }

    public int getRangoPesoId() {
        return rangoPesoId;
    }

    public void setRangoPesoId(int rangoPesoId) {
        this.rangoPesoId = rangoPesoId;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();
        valoresEntidad.put(SaludDB.TablaRangoImc.ID, id);
        valoresEntidad.put(SaludDB.TablaRangoImc.SEXO_IMC, sexoImc);
        valoresEntidad.put(SaludDB.TablaRangoImc.RANGO_ALTURA_ID, rangoAlturaId);
        valoresEntidad.put(SaludDB.TablaRangoImc.RANGO_PESO_ID, rangoPesoId);
        
        return valoresEntidad;
    }
}