package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorRegistroRitmoCardiaco {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposRegistroRitmoCardiaco = {
            SaludDB.TablaRegistroRitmoCardiaco.ID,
            SaludDB.TablaRegistroRitmoCardiaco.BPM,
            SaludDB.TablaRegistroRitmoCardiaco.CHEQUEO_SALUD_ID
    };

    public ControladorRegistroRitmoCardiaco(Context context){
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
