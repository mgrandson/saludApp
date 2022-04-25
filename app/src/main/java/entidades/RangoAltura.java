package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class RangoAltura {
    private int id;
    private double alturaMinima;
    private double alturaMaxima;

    public RangoAltura() {
    }

    public RangoAltura(int id, double alturaMinima, double alturaMaxima) {
        this.id = id;
        this.alturaMinima = alturaMinima;
        this.alturaMaxima = alturaMaxima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAlturaMinima() {
        return alturaMinima;
    }

    public void setAlturaMinima(double alturaMinima) {
        this.alturaMinima = alturaMinima;
    }

    public double getAlturaMaxima() {
        return alturaMaxima;
    }

    public void setAlturaMaxima(double alturaMaxima) {
        this.alturaMaxima = alturaMaxima;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();
        valoresEntidad.put(SaludDB.TablaRangoAltura.ID, id);
        valoresEntidad.put(SaludDB.TablaRangoAltura.ALTURA_MINIMA, alturaMinima);
        valoresEntidad.put(SaludDB.TablaRangoAltura.ALTURA_MAXIMA, alturaMaxima);
        return valoresEntidad;
    }
}

