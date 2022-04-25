package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class Deporte {
    private int id;
    private String deporte;

    public Deporte() {
    }

    public Deporte(int id, String deporte) {
        this.id = id;
        this.deporte = deporte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public ContentValues toContentvalues(){
        ContentValues deporteValues = new ContentValues();
        deporteValues.put(SaludDB.TablaDeportes.ID, id);
        deporteValues.put(SaludDB.TablaDeportes.DEPORTE, deporte);
        return deporteValues;

    }
}
