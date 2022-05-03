package control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import entidades.RegistroPresionArterial;
import entidades.RegistroRitmoCardiaco;

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



    /**
     * Busca el ritmo cardiaco del chequeo de salud
     *
     * @param idChqueoSalud
     * @return
     */
    public RegistroPresionArterial buscarPorIdChequeoSalud(Integer idChqueoSalud){
        Cursor cursor = abrirDB().query(SaludDB.TablaRegistroPresionArterial.NOMBRE_TABLA,camposRegistroPresionArterial,"chequeoSaludId =" + idChqueoSalud,null,null,null,null);
        if(cursor.moveToFirst()){
            RegistroPresionArterial registroPresionArterial = new RegistroPresionArterial();
            registroPresionArterial.setId(cursor.getInt(0));
            registroPresionArterial.setSistolica(cursor.getInt(1));
            registroPresionArterial.setDiastolica(cursor.getInt(2));
            registroPresionArterial.setChequeoSaludId(cursor.getInt(3));
            cerrarDB();
            return registroPresionArterial;

        }
        cerrarDB();
        return null;
    }

}




