package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorFactorActividad {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposFactorActividad = {
            SaludDB.TablaFactorActividad.ID,
            SaludDB.TablaFactorActividad.FACTOR,
            SaludDB.TablaFactorActividad.DESCRIPCION
    };

    public ControladorFactorActividad(Context context){
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
