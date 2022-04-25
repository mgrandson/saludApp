package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class TipoComida {
    private int id;
    private String nombreTipoComida;
    private double cantidadCalorifica;
    private int tamanioPorcion;

    public TipoComida() {
    }

    public TipoComida(int id, String nombreTipoComida, double cantidadCalorifica, int tamanioPorcion) {
        this.id = id;
        this.nombreTipoComida = nombreTipoComida;
        this.cantidadCalorifica = cantidadCalorifica;
        this.tamanioPorcion = tamanioPorcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreTipoComida() {
        return nombreTipoComida;
    }

    public void setNombreTipoComida(String nombreTipoComida) {
        this.nombreTipoComida = nombreTipoComida;
    }

    public double getCantidadCalorifica() {
        return cantidadCalorifica;
    }

    public void setCantidadCalorifica(double cantidadCalorifica) {
        this.cantidadCalorifica = cantidadCalorifica;
    }

    public int getTamanioPorcion() {
        return tamanioPorcion;
    }

    public void setTamanioPorcion(int tamanioPorcion) {
        this.tamanioPorcion = tamanioPorcion;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();

        valoresEntidad.put(SaludDB.TablaTipoComida.ID, id);
        valoresEntidad.put(SaludDB.TablaTipoComida.NOMBRE_TIPO_COMIDA, nombreTipoComida);
        valoresEntidad.put(SaludDB.TablaTipoComida.CANTIDAD_CALORIFICA, cantidadCalorifica);
        valoresEntidad.put(SaludDB.TablaTipoComida.TAMANIO_PORCION, tamanioPorcion);

        return valoresEntidad;
    }
}
