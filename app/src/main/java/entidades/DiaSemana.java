package entidades;


import android.content.ContentValues;

import control.SaludDB;

public class DiaSemana {
    private int id;
    private String nombreDia;

    public DiaSemana() {
    }

    public DiaSemana(int id, String nombreDia) {
        this.id = id;
        this.nombreDia = nombreDia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();
        valoresEntidad.put(SaludDB.TablaDiaSemana.ID, id);
        valoresEntidad.put(SaludDB.TablaDiaSemana.NOMBRE_DIA, nombreDia);
        return valoresEntidad;
    }
}
