package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorDetalleDeportePorFactor {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposDetalleDeportePorFactor = {
            SaludDB.TablaDetalleDeportePorFactor.ID,
            SaludDB.TablaDetalleDeportePorFactor.ENERGIA_GASTADA,
            SaludDB.TablaDetalleDeportePorFactor.DURACION,
            SaludDB.TablaDetalleDeportePorFactor.FACTOR_ACTIVIDAD_ID,
            SaludDB.TablaDetalleDeportePorFactor.DEPORTE_ID
    };

    public ControladorDetalleDeportePorFactor(Context context){
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
