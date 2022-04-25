package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorRangoAltura {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposRangoAltura = {
            SaludDB.TablaRangoAltura.ID,
            SaludDB.TablaRangoAltura.ALTURA_MINIMA,
            SaludDB.TablaRangoAltura.ALTURA_MAXIMA
    };

    public ControladorRangoAltura(Context context){
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
