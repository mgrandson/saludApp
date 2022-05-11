package control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import entidades.Deporte;
import entidades.RegistroRitmoCardiaco;

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

    //CRUD

    public long crear(RegistroRitmoCardiaco registroRitmoCardiaco){
        Long resultado = abrirDB().insert(SaludDB.TablaRegistroRitmoCardiaco.NOMBRE_TABLA,null, registroRitmoCardiaco.toContentvalues());
        cerrarDB();
        return resultado;
    }

    public List<RegistroRitmoCardiaco> obtenerRegistros(){
        List<RegistroRitmoCardiaco> listaRegistros = new ArrayList<>();
        abrirDB();
        Cursor cursor = abrirDB().query(
                SaludDB.TablaRegistroRitmoCardiaco.NOMBRE_TABLA,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            RegistroRitmoCardiaco ritmoCardiaco = new RegistroRitmoCardiaco(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );
            listaRegistros.add(ritmoCardiaco);
        }
        cursor.close();
        cerrarDB();
        return listaRegistros;
    }

    public RegistroRitmoCardiaco consultarPorId(int parametro){
        String [] id = { String.valueOf(parametro) };
        abrirDB();
        Cursor cursor = abrirDB().query(
                SaludDB.TablaDeportes.NOMBRE_TABLA, camposRegistroRitmoCardiaco,
                "id = ?",
                id,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            RegistroRitmoCardiaco ritmoCardiaco = new RegistroRitmoCardiaco(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );
            cursor.close();
            cerrarDB();
            return ritmoCardiaco;
        }
        else{
            return null;
        }
    }

}
