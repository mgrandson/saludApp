package control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import entidades.RegistroHidratacion;
import entidades.RegistroRitmoCardiaco;

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

    public long crear(RegistroHidratacion registroHidratacion){
        Long resultado = abrirDB().insert(SaludDB.TablaRegistroHidratacion.NOMBRE_TABLA,null, registroHidratacion.toContentvalues());
        cerrarDB();
        return resultado;
    }

    public List<RegistroHidratacion> obtenerRegistros(){
        List<RegistroHidratacion> listaRegistros = new ArrayList<>();
        Cursor cursor = abrirDB().query(
                SaludDB.TablaRegistroHidratacion.NOMBRE_TABLA,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            RegistroHidratacion registroHidratacion = new RegistroHidratacion(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );
            listaRegistros.add(registroHidratacion);
        }
        cursor.close();
        cerrarDB();
        return listaRegistros;
    }

    public RegistroHidratacion consultarPorId(int parametro){
        String [] id = { String.valueOf(parametro) };
        abrirDB();
        Cursor cursor = abrirDB().query(
                SaludDB.TablaRegistroHidratacion.NOMBRE_TABLA, camposRegistroHidratacion,
                "id = ?",
                id,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            RegistroHidratacion registroHidratacion = new RegistroHidratacion(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );
            cursor.close();
            cerrarDB();
            return registroHidratacion;
        }
        else{
            return null;
        }
    }
}
