package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorRegistroActividadFisicaDiaria {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposRegistroActividadFisicaDiaria = {
            SaludDB.TablaRegistroActividadFisicaDiaria.ID,
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
}
