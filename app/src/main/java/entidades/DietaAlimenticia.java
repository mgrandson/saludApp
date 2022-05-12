package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class DietaAlimenticia {
    private int id;
    private int duracionDieta;
    private double totalCalorias;
    private int chequeoSaludId;

    public DietaAlimenticia() {
    }

    public DietaAlimenticia(int id, int duracionDieta, double totalCalorias, int chequeoSaludId) {
        this.id = id;
        this.duracionDieta = duracionDieta;
        this.totalCalorias = totalCalorias;
        this.chequeoSaludId = chequeoSaludId;
    }

    public DietaAlimenticia(int duracionDieta, double totalCalorias, int chequeoSaludId) {
        this.duracionDieta = duracionDieta;
        this.totalCalorias = totalCalorias;
        this.chequeoSaludId = chequeoSaludId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracionDieta() {
        return duracionDieta;
    }

    public void setDuracionDieta(int duracionDieta) {
        this.duracionDieta = duracionDieta;
    }

    public double getTotalCalorias() {
        return totalCalorias;
    }

    public void setTotalCalorias(double totalCalorias) {
        this.totalCalorias = totalCalorias;
    }

    public int getChequeoSaludId() {
        return chequeoSaludId;
    }

    public void setChequeoSaludId(int chequeoSaludId) {
        this.chequeoSaludId = chequeoSaludId;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();
        valoresEntidad.put(SaludDB.TablaDietaAlimenticia.DURACION_DIETA, duracionDieta);
        valoresEntidad.put(SaludDB.TablaDietaAlimenticia.TOTAL_CALORIAS, totalCalorias);
        valoresEntidad.put(SaludDB.TablaDietaAlimenticia.CHEQUEO_SALUD_ID, chequeoSaludId);
        return valoresEntidad;
    }

    @Override
    public String toString() {
        return "DietaAlimenticia{" +
                "id=" + id +
                ", duracionDieta=" + duracionDieta +
                ", totalCalorias=" + totalCalorias +
                ", chequeoSaludId=" + chequeoSaludId +
                '}';
    }
}
