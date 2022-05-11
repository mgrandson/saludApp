package control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import entidades.RegistroActividadFisicaDiaria;

public class ControladorRegistroActividadFisicaDiaria {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposRegistroActividadFisicaDiaria = {
            SaludDB.TablaRegistroActividadFisicaDiaria.ID,
            SaludDB.TablaRegistroActividadFisicaDiaria.FECHA_ACTIVIDAD,
            SaludDB.TablaRegistroActividadFisicaDiaria.ESTADO,
            SaludDB.TablaRegistroActividadFisicaDiaria.DIA_SEMANA_ID,
            SaludDB.TablaRegistroActividadFisicaDiaria.DETALLE_DEPORTE_POR_FACTOR_ID,
            SaludDB.TablaRegistroActividadFisicaDiaria.CHEQUEO_SALUD_ID
    };

    public ControladorRegistroActividadFisicaDiaria(Context context){
        this.context = context;
    }

    public SQLiteDatabase abrirDB(){
        saludSqliteHelper = new SaludSqliteHelper(context, SaludDB.NOMBRE_BD,null,SaludDB.VERSION_BD);
        instanciaBD = saludSqliteHelper.getWritableDatabase();
        return instanciaBD;
    }

    public void cerrarDB(){
        instanciaBD.close();
    }

    //CRUD

    public long crear(RegistroActividadFisicaDiaria actividadFisica){

        Long resultado = abrirDB().insert(
                SaludDB.TablaRegistroActividadFisicaDiaria.NOMBRE_TABLA,
                null, actividadFisica.toContentvalues()
        );
        abrirDB().close();
        return resultado;
    }

    public long actualizar(RegistroActividadFisicaDiaria actividadFisicaDiaria){
        //VERIFICAR INTEGRIDAD

        String [] id = { String.valueOf(actividadFisicaDiaria.getId()) };
        long resultado = abrirDB().update(
                SaludDB.TablaRegistroActividadFisicaDiaria.NOMBRE_TABLA,
                actividadFisicaDiaria.toContentvalues(),
                "id = ?",
                id
        );
        cerrarDB();
        return resultado;
    }

    public long eliminar(RegistroActividadFisicaDiaria actividadFisicaDiaria){
        String [] id = { String.valueOf(actividadFisicaDiaria.getId()) };
        long resultado = abrirDB().delete(
                SaludDB.TablaRegistroActividadFisicaDiaria.NOMBRE_TABLA, "id = ?",
                id
        );
        return resultado;
    }

    public long actualizarEstado(String id, String estado){
        //VERIFICAR INTEGRIDAD
        ContentValues contentValues = new ContentValues();

        contentValues.put("estado", estado);
        long resultado = abrirDB().update(
                SaludDB.TablaRegistroActividadFisicaDiaria.NOMBRE_TABLA,
                contentValues, "id = ? ",
                new String[]{id}
        );
        cerrarDB();
        return resultado;
    }

    public Cursor getObtenerActividad(String id) {
        //String consultaSQL = "select * from " + SaludDB.TablaRegistroActividadFisicaDiaria.NOMBRE_TABLA + " WHERE id = '" + id + "' order by id desc";

        String consultaSQL = "" +
                "SELECT " +
                "afd.id, " +
                "d.deporte, " +
                "afd.fechaActividad, " +
                "afd.estado, " +
                "ddf.id " +
                "FROM registros_actividad_fisica_diaria afd " +
                "join detalles_deportes_x_factor ddf on ddf.id = afd.detalleDeportePorFactorId " +
                "join deportes d on d.id = ddf.deporteId " +
                "WHERE afd.id = "+id+" order by afd.id desc;";

        Cursor res = abrirDB().rawQuery(consultaSQL, null);
        return res;

    }

    public Cursor getObtenerActvidadActual() {
        //String consultaSQL = "select * from " + SaludDB.TablaRegistroActividadFisicaDiaria.NOMBRE_TABLA + " WHERE date(fechaActividad) = date('now', 'localtime') order by id desc";
        String consultaSQL = "" +
                "SELECT " +
                "afd.id, " +
                "d.deporte, " +
                "afd.fechaActividad, " +
                "afd.estado " +
                "FROM registros_actividad_fisica_diaria afd " +
                "join detalles_deportes_x_factor ddf on ddf.id = afd.detalleDeportePorFactorId " +
                "join deportes d on d.id = ddf.deporteId " +
                "WHERE date(afd.fechaActividad) = date('now', 'localtime') order by afd.id desc;";

        Cursor res = abrirDB().rawQuery(consultaSQL, null);
        return res;

    }

    public Cursor getObtenerActvidadSiguiente() {
        //String consultaSQL = "select * from " + SaludDB.TablaRegistroActividadFisicaDiaria.NOMBRE_TABLA + " WHERE date(fechaActividad) = date('now', '+1 day', 'localtime')  order by id desc";
        String consultaSQL = "" +
                "SELECT " +
                "afd.id, " +
                "d.deporte, " +
                "afd.fechaActividad, " +
                "afd.estado " +
                "FROM registros_actividad_fisica_diaria afd " +
                "join detalles_deportes_x_factor ddf on ddf.id = afd.detalleDeportePorFactorId " +
                "join deportes d on d.id = ddf.deporteId " +
                "WHERE date(afd.fechaActividad) = date('now', '+1 day', 'localtime') order by afd.id desc;";

        Cursor res = abrirDB().rawQuery(consultaSQL, null);
        return res;

    }


    public Cursor getObtenerActividadFisicaFutura() {
        //String consultaSQL = "select * from " + SaludDB.TablaRegistroActividadFisicaDiaria.NOMBRE_TABLA + " WHERE date(fechaActividad) > date('now', '+1 day', 'localtime') order by id desc";
        String consultaSQL = "" +
                "SELECT " +
                "afd.id, " +
                "d.deporte, " +
                "afd.fechaActividad, " +
                "afd.estado " +
                "FROM registros_actividad_fisica_diaria afd " +
                "join detalles_deportes_x_factor ddf on ddf.id = afd.detalleDeportePorFactorId " +
                "join deportes d on d.id = ddf.deporteId " +
                "WHERE date(afd.fechaActividad) > date('now', '+1 day', 'localtime') order by afd.id desc;";
        Cursor res = abrirDB().rawQuery(consultaSQL, null);
        return res;
    }

    public Cursor getActividadesFisicas(int factorActividad){
        String consultaSQL = "" +
                "SELECT " +
                "ddf.id, " +
                "d.deporte " +
                "FROM detalles_deportes_x_factor ddf " +
                "JOIN deportes d ON ddf.deporteId = d.id " +
                "WHERE factorActividadId = "+factorActividad+";";
        Cursor res = abrirDB().rawQuery(consultaSQL, null);
        return res;
    }
}
