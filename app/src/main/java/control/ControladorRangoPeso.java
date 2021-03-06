package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorRangoPeso {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposRangoPeso = {
            SaludDB.TablaRangoPeso.ID,
            SaludDB.TablaRangoPeso.PESO_MINIMO,
            SaludDB.TablaRangoPeso.PESO_MAXIMO
    };

    public ControladorRangoPeso(Context context){
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
