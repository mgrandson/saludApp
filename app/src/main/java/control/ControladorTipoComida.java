package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorTipoComida {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposDTipoComida = {
            SaludDB.TablaTipoComida.ID,
            SaludDB.TablaTipoComida.NOMBRE_TIPO_COMIDA,
            SaludDB.TablaTipoComida.CANTIDAD_CALORIFICA,
            SaludDB.TablaTipoComida.TAMANIO_PORCION
    };

    public ControladorTipoComida(Context context){
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
