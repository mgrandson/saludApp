package control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import entidades.RegistroRitmoCardiaco;
import entidades.Usuario;

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


    /**
     * Busca el ritmo cardiaco del chequeo de salud
     *
     * @param idChqueoSalud
     * @return
     */
    public RegistroRitmoCardiaco buscarPorIdChequeoSalud(Integer idChqueoSalud){


        Cursor cursor = abrirDB().query(SaludDB.TablaRegistroRitmoCardiaco.NOMBRE_TABLA,camposRegistroRitmoCardiaco,"chequeoSaludId =" + idChqueoSalud,null,null,null,null);
        if(cursor.moveToFirst()){
            RegistroRitmoCardiaco registroRitmoCardiaco = new RegistroRitmoCardiaco();
            registroRitmoCardiaco.setId(cursor.getInt(0));
            registroRitmoCardiaco.setBpm(cursor.getInt(1));
            registroRitmoCardiaco.setChequeoSaludId(cursor.getInt(2));

            cerrarDB();
            return registroRitmoCardiaco;

        }
        cerrarDB();
        return null;

    }

}
