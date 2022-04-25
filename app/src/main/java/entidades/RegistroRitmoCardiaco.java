package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class RegistroRitmoCardiaco {
    private int id;
    private int bpm;
    private int chequeoSaludId;

    public RegistroRitmoCardiaco() {
    }

    public RegistroRitmoCardiaco(int id, int bpm, int chequeoSaludId) {
        this.id = id;
        this.bpm = bpm;
        this.chequeoSaludId = chequeoSaludId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public int getChequeoSaludId() {
        return chequeoSaludId;
    }

    public void setChequeoSaludId(int chequeoSaludId) {
        this.chequeoSaludId = chequeoSaludId;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();

        valoresEntidad.put(SaludDB.TablaRegistroRitmoCardiaco.ID, id);
        valoresEntidad.put(SaludDB.TablaRegistroRitmoCardiaco.BPM, bpm);
        valoresEntidad.put(SaludDB.TablaRegistroRitmoCardiaco.CHEQUEO_SALUD_ID, chequeoSaludId);

        return valoresEntidad;
    }
}
