package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class RegistroDietaDiaria {
    private int id;
    private int dietaAlimenticiaId;
    private int diaSemanaId;

    public RegistroDietaDiaria() {
    }

    public RegistroDietaDiaria(int id, int dietaAlimenticiaId, int diaSemanaId) {
        this.id = id;
        this.dietaAlimenticiaId = dietaAlimenticiaId;
        this.diaSemanaId = diaSemanaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDietaAlimenticiaId() {
        return dietaAlimenticiaId;
    }

    public void setDietaAlimenticiaId(int dietaAlimenticiaId) {
        this.dietaAlimenticiaId = dietaAlimenticiaId;
    }

    public int getDiaSemanaId() {
        return diaSemanaId;
    }

    public void setDiaSemanaId(int diaSemanaId) {
        this.diaSemanaId = diaSemanaId;
    }

    public ContentValues toContentvalues(){
        ContentValues valoresEntidad = new ContentValues();

        valoresEntidad.put(SaludDB.TablaRegistroDietaDiaria.ID, id);
        valoresEntidad.put(SaludDB.TablaRegistroDietaDiaria.DIETA_ALIMENTICIA_ID, dietaAlimenticiaId);
        valoresEntidad.put(SaludDB.TablaRegistroDietaDiaria.DIA_SEMANA_ID, diaSemanaId);

        return valoresEntidad;
    }
}
