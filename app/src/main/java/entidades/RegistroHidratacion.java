package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class RegistroHidratacion {
    private int id;
    private double consumoAgua;
    private int chequeoSaludId;

    public RegistroHidratacion() {
    }

    public RegistroHidratacion(int id, double consumoAgua, int chequeoSaludId) {
        this.id = id;
        this.consumoAgua = consumoAgua;
        this.chequeoSaludId = chequeoSaludId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getConsumoAgua() {
        return consumoAgua;
    }

    public void setConsumoAgua(double consumoAgua) {
        this.consumoAgua = consumoAgua;
    }

    public int getChequeoSaludId() {
        return chequeoSaludId;
    }

    public void setChequeoSaludId(int chequeoSaludId) {
        this.chequeoSaludId = chequeoSaludId;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();
        valoresEntidad.put(SaludDB.TablaRegistroHidratacion.CONSUMO_AGUA, consumoAgua);
        valoresEntidad.put(SaludDB.TablaRegistroHidratacion.CHEQUEO_SALUD_ID, chequeoSaludId);

        return valoresEntidad;
    }
}