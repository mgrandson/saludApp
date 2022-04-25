package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class DetalleDietaPorTiempo {
    private int id;
    private String tiempoComida;
    private int registroDietaDiariaId;
    private int tipoComidaId;

    public DetalleDietaPorTiempo() {
    }

    public DetalleDietaPorTiempo(int id, String tiempoComida, int registroDietaDiariaId, int tipoComidaId) {
        this.id = id;
        this.tiempoComida = tiempoComida;
        this.registroDietaDiariaId = registroDietaDiariaId;
        this.tipoComidaId = tipoComidaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTiempoComida() {
        return tiempoComida;
    }

    public void setTiempoComida(String tiempoComida) {
        this.tiempoComida = tiempoComida;
    }

    public int getRegistroDietaDiariaId() {
        return registroDietaDiariaId;
    }

    public void setRegistroDietaDiariaId(int registroDietaDiariaId) {
        this.registroDietaDiariaId = registroDietaDiariaId;
    }

    public int getTipoComidaId() {
        return tipoComidaId;
    }

    public void setTipoComidaId(int tipoComidaId) {
        this.tipoComidaId = tipoComidaId;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();
        valoresEntidad.put(SaludDB.TablaDetalleDietaPorTiempo.ID, id);
        valoresEntidad.put(SaludDB.TablaDetalleDietaPorTiempo.TIEMPO_COMIDA, tiempoComida);
        valoresEntidad.put(SaludDB.TablaDetalleDietaPorTiempo.REGISTRO_DIETA_DIARIA_ID, registroDietaDiariaId);
        valoresEntidad.put(SaludDB.TablaDetalleDietaPorTiempo.TIPO_COMIDA_ID, tipoComidaId);
        return valoresEntidad;
    }
}
