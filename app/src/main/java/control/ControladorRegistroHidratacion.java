package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorRegistroHidratacion {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposRegistroHidratacion = {
            SaludDB.TablaRegistroHidratacion.ID,
            SaludDB.TablaRegistroHidratacion.CONSUMO_AGUA,
            SaludDB.TablaRegistroHidratacion.CHEQUEO_SALUD_ID
    };

    public ControladorRegistroHidratacion(Context context){
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
