package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorRangoImc {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposRangoImc = {
            SaludDB.TablaRangoImc.ID,
            SaludDB.TablaRangoImc.SEXO_IMC,
            SaludDB.TablaRangoImc.RANGO_ALTURA_ID,
            SaludDB.TablaRangoImc.RANGO_PESO_ID
    };

    public ControladorRangoImc(Context context){
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
