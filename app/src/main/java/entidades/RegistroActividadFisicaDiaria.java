package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class RegistroActividadFisicaDiaria {
    private int id;
    private String fechaActividad;
    private String estado;
    private int diaSemanaId;
    private int detalleDeportePorFactorId;
    private int chequeoSaludId;

    public RegistroActividadFisicaDiaria() {
    }

    public RegistroActividadFisicaDiaria(int id, String fechaActividad, String estado, int diaSemanaId, int detalleDeportePorFactorId, int chequeoSaludId) {
        this.id = id;
        this.fechaActividad = fechaActividad;
        this.estado = estado;
        this.diaSemanaId = diaSemanaId;
        this.detalleDeportePorFactorId = detalleDeportePorFactorId;
        this.chequeoSaludId = chequeoSaludId;
    }

    public RegistroActividadFisicaDiaria(String fechaActividad, String estado, int diaSemanaId, int detalleDeportePorFactorId, int chequeoSaludId) {
        this.fechaActividad = fechaActividad;
        this.estado = estado;
        this.diaSemanaId = diaSemanaId;
        this.detalleDeportePorFactorId = detalleDeportePorFactorId;
        this.chequeoSaludId = chequeoSaludId;
    }

    public RegistroActividadFisicaDiaria(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(String fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        valoresEntidad.put(SaludDB.TablaRegistroActividadFisicaDiaria.FECHA_ACTIVIDAD, fechaActividad);
        valoresEntidad.put(SaludDB.TablaRegistroActividadFisicaDiaria.ESTADO, estado);
        valoresEntidad.put(SaludDB.TablaRegistroActividadFisicaDiaria.DIA_SEMANA_ID, diaSemanaId);
        valoresEntidad.put(SaludDB.TablaRegistroActividadFisicaDiaria.DETALLE_DEPORTE_POR_FACTOR_ID, detalleDeportePorFactorId);
        valoresEntidad.put(SaludDB.TablaRegistroActividadFisicaDiaria.CHEQUEO_SALUD_ID, chequeoSaludId);

        return valoresEntidad;
    }

    @Override
    public String toString() {
        return "RegistroActividadFisicaDiaria{" +
                "id=" + id +
                ", fechaActividad='" + fechaActividad + '\'' +
                ", estado='" + estado + '\'' +
                ", diaSemanaId=" + diaSemanaId +
                ", detalleDeportePorFactorId=" + detalleDeportePorFactorId +
                ", chequeoSaludId=" + chequeoSaludId +
                '}';
    }
}
