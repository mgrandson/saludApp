package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class RegistroPresionArterial {
    private int id;
    private int sistolica;
    private int diastolica;
    private int chequeoSaludId;

    public RegistroPresionArterial() {
    }

    public RegistroPresionArterial(int id, int sistolica, int diastolica, int chequeoSaludId) {
        this.id = id;
        this.sistolica = sistolica;
        this.diastolica = diastolica;
        this.chequeoSaludId = chequeoSaludId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSistolica() {
        return sistolica;
    }

    public void setSistolica(int sistolica) {
        this.sistolica = sistolica;
    }

    public int getDiastolica() {
        return diastolica;
    }

    public void setDiastolica(int diastolica) {
        this.diastolica = diastolica;
    }

    public int getChequeoSaludId() {
        return chequeoSaludId;
    }

    public void setChequeoSaludId(int chequeoSaludId) {
        this.chequeoSaludId = chequeoSaludId;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();

        valoresEntidad.put(SaludDB.TablaRegistroPresionArterial.ID, id);
        valoresEntidad.put(SaludDB.TablaRegistroPresionArterial.SISTOLICA, sistolica);
        valoresEntidad.put(SaludDB.TablaRegistroPresionArterial.DIASTOLICA, diastolica);
        valoresEntidad.put(SaludDB.TablaRegistroPresionArterial.CHEQUEO_SALUD_ID, chequeoSaludId);

        return valoresEntidad;
    }
}
