package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorRegistroPresionArterial {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposRegistroPresionArterial = {
            SaludDB.TablaRegistroPresionArterial.ID,
            SaludDB.TablaRegistroPresionArterial.SISTOLICA,
            SaludDB.TablaRegistroPresionArterial.DIASTOLICA,
            SaludDB.TablaRegistroPresionArterial.CHEQUEO_SALUD_ID
    };

    public ControladorRegistroPresionArterial(Context context){
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
