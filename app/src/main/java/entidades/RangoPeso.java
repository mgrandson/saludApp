package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class RangoPeso{
    private int id;
    private double pesoMinimo;
    private double pesoMaximo;

    public RangoPeso() {
    }

    public RangoPeso(int id, double pesoMinimo, double pesoMaximo) {
        this.id = id;
        this.pesoMinimo = pesoMinimo;
        this.pesoMaximo = pesoMaximo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPesoMinimo() {
        return pesoMinimo;
    }

    public void setPesoMinimo(double pesoMinimo) {
        this.pesoMinimo = pesoMinimo;
    }

    public double getPesoMaximo() {
        return pesoMaximo;
    }

    public void setPesoMaximo(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();

        valoresEntidad.put(SaludDB.TablaRangoPeso.ID, id);
        valoresEntidad.put(SaludDB.TablaRangoPeso.PESO_MINIMA, pesoMinimo);
        valoresEntidad.put(SaludDB.TablaRangoPeso.PESO_MAXIMA, pesoMaximo);

        return valoresEntidad;
    }
}
