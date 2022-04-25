package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorDietaAlimenticia {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposDietaAlimenticia = {
            SaludDB.TablaDietaAlimenticia.ID,
            SaludDB.TablaDietaAlimenticia.DURACION_DIETA,
            SaludDB.TablaDietaAlimenticia.TOTAL_CALORIAS,
            SaludDB.TablaDietaAlimenticia.CHEQUEO_SALUD_ID
    };

    public ControladorDietaAlimenticia(Context context){
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
