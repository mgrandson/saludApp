package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorDetalleDietaPorTiempo {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposDetalleDietaPorTiempo = {
            SaludDB.TablaDetalleDietaPorTiempo.ID,
            SaludDB.TablaDetalleDietaPorTiempo.TIEMPO_COMIDA,
            SaludDB.TablaDetalleDietaPorTiempo.REGISTRO_DIETA_DIARIA_ID,
            SaludDB.TablaDetalleDietaPorTiempo.TIEMPO_COMIDA
    };

    public ControladorDetalleDietaPorTiempo(Context context){
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
