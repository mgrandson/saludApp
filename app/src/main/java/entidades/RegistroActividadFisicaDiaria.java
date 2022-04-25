package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class RegistroActividadFisicaDiaria {
    private int id;
    private int diaSemanaId;
    private int detalleDeportePorFactorId;
    private int chequeoSaludId;

    public RegistroActividadFisicaDiaria() {
    }

    public RegistroActividadFisicaDiaria(int id, int diaSemanaId, int detalleDeportePorFactorId, int chequeoSaludId) {
        this.id = id;
        this.diaSemanaId = diaSemanaId;
        this.detalleDeportePorFactorId = detalleDeportePorFactorId;
        this.chequeoSaludId = chequeoSaludId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiaSemanaId() {
        return diaSemanaId;
    }

    public void setDiaSemanaId(int diaSemanaId) {
        this.diaSemanaId = diaSemanaId;
    }

    public int getDetalleDeportePorFactorId() {
        return detalleDeportePorFactorId;
    }

    public void setDetalleDeportePorFactorId(int detalleDeportePorFactorId) {
        this.detalleDeportePorFactorId = detalleDeportePorFactorId;
    }

    public int getChequeoSaludId() {
        return chequeoSaludId;
    }

    public void setChequeoSaludId(int chequeoSaludId) {
        this.chequeoSaludId = chequeoSaludId;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();

        valoresEntidad.put(SaludDB.TablaRegistroActividadFisicaDiaria.ID, id);
        valoresEntidad.put(SaludDB.TablaRegistroActividadFisicaDiaria.DIA_SEMANA_ID, diaSemanaId);
        valoresEntidad.put(SaludDB.TablaRegistroActividadFisicaDiaria.DETALLE_DEPORTE_POR_FACTOR_ID, detalleDeportePorFactorId);
        valoresEntidad.put(SaludDB.TablaRegistroActividadFisicaDiaria.CHEQUEO_SALUD_ID, chequeoSaludId);

        return valoresEntidad;
    }
}
