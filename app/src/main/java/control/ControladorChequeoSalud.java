package control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import entidades.ChequeoSalud;
import entidades.Deporte;

public class ControladorChequeoSalud {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposChequeSalud = {
            SaludDB.TablaChequeoSalud.ID_CHEQUEO,
            SaludDB.TablaChequeoSalud.FECHA_CHEQUEO,
            SaludDB.TablaChequeoSalud.PESO_ACTUAL,
            SaludDB.TablaChequeoSalud.ALTURA_ACTUAL,
            SaludDB.TablaChequeoSalud.VALOR_IMC_ACTUAL,
            SaludDB.TablaChequeoSalud.MENSAJE_IMC_ACTUAL,
            SaludDB.TablaChequeoSalud.RANGOS_IMC_ID,
            SaludDB.TablaChequeoSalud.USUARIOS_ID
    };

    public ControladorChequeoSalud(Context context){
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

    //INSERTAR REGISTRO
    public long crear(ChequeoSalud chequeoSalud){
        abrirDB();
        Long resultado = instanciaBD.insert(
                SaludDB.TablaChequeoSalud.NOMBRE_TABLA,
                null,
                chequeoSalud.toContentvalues()
        );
        cerrarDB();
        return resultado;
    }

    //OBTENER TODOS LOS REGISTROS
    public List<ChequeoSalud> obtenerRegistros(){
        List<ChequeoSalud> listaRegistros = new ArrayList<>();
        abrirDB();
        Cursor cursor = instanciaBD.query(
                SaludDB.TablaChequeoSalud.NOMBRE_TABLA,
                null,
                null,
                null,
                null,
                null,
                SaludDB.TablaChequeoSalud.FECHA_CHEQUEO + " ASC");
        while (cursor.moveToNext()){
            ChequeoSalud chequeoSalud = new ChequeoSalud(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7));
             listaRegistros.add(chequeoSalud);
        }
        cursor.close();
        cerrarDB();
        return listaRegistros;
    }





    //OBTENER REGISTRO POR ID
    public ChequeoSalud consultarPorId(int parametro){
        String [] id = { String.valueOf(parametro) };
        abrirDB();
        Cursor cursor = instanciaBD.query(
                SaludDB.TablaChequeoSalud.NOMBRE_TABLA, camposChequeSalud,
                "id = ?",
                id,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            ChequeoSalud chequeoSalud = new ChequeoSalud(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7));
            cursor.close();
            cerrarDB();
            return chequeoSalud;
        }
        else{
            return null;
        }
    }
    //ACTUALIZAR REGISTRO
    public long actualizar(ChequeoSalud chequeoSalud){
        //VERIFICAR INTEGRIDAD

        String [] id = { String.valueOf(chequeoSalud.getId()) };
        abrirDB();
        long resultado = instanciaBD.update(
                SaludDB.TablaChequeoSalud.NOMBRE_TABLA,
                chequeoSalud.toContentvalues(),
                "id = ?",
                id
        );
        cerrarDB();
        return resultado;
    }
    //ELIMINAR REGISTRO
    public long eliminar(ChequeoSalud chequeoSalud){
        abrirDB();
        String [] id = { String.valueOf(chequeoSalud.getId()) };
        long resultado = instanciaBD.delete(
                SaludDB.TablaChequeoSalud.NOMBRE_TABLA, "id = ?",
                id
        );
        return resultado;
    }

}
