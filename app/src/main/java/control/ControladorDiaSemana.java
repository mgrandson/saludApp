package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControladorDiaSemana {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposDiaSemana = {
            SaludDB.TablaDiaSemana.ID,
            SaludDB.TablaDiaSemana.NOMBRE_DIA
    };

    public ControladorDiaSemana(Context context){
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
