package control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import entidades.RegistroPresionArterial;

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

    public long crear(RegistroPresionArterial registroPresionArterial){
        abrirDB();
        Long resultado = instanciaBD.insert(SaludDB.TablaRegistroPresionArterial.NOMBRE_TABLA,null, registroPresionArterial.toContentvalues());
        cerrarDB();
        return resultado;
    }

    public List<RegistroPresionArterial> obtenerRegistros(){
        List<RegistroPresionArterial> listaRegistros = new ArrayList<>();
        Cursor cursor = abrirDB().query(
                SaludDB.TablaRegistroPresionArterial.NOMBRE_TABLA,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            RegistroPresionArterial RegistroPresionArterial = new RegistroPresionArterial(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3)
            );
            listaRegistros.add(RegistroPresionArterial);
        }
        cursor.close();
        cerrarDB();
        return listaRegistros;
    }

    public RegistroPresionArterial consultarPorId(int parametro){
        String [] id = { String.valueOf(parametro) };
        Cursor cursor = abrirDB().query(
                SaludDB.TablaRegistroPresionArterial.NOMBRE_TABLA, camposRegistroPresionArterial,
                "id = ?",
                id,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            RegistroPresionArterial RegistroPresionArterial = new RegistroPresionArterial(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3)
            );
            cursor.close();
            cerrarDB();
            return RegistroPresionArterial;
        }
        else{
            return null;
        }
    }
}
