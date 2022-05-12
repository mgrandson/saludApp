package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import entidades.Deporte;
import entidades.RegistroDietaDiaria;

public class ControladorRegistroDietaDiaria {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposRegistroDietaDiaria = {
            SaludDB.TablaRegistroDietaDiaria.ID,
            SaludDB.TablaRegistroDietaDiaria.DIETA_ALIMENTICIA_ID,
            SaludDB.TablaRegistroDietaDiaria.DIA_SEMANA_ID
    };

    public ControladorRegistroDietaDiaria(Context context){
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

    //CRUD

    public long crear(RegistroDietaDiaria dietaDiaria){
        Long resultado = abrirDB().insert(
                SaludDB.TablaRegistroDietaDiaria.NOMBRE_TABLA,
                null, dietaDiaria.toContentvalues()
        );
        cerrarDB();
        return resultado;
    }
}
