package control;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.io.PrintStream;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import entidades.ChequeoSalud;
import entidades.Deporte;
import entidades.DetalleDietaPorTiempo;
import entidades.DietaAlimenticia;
import entidades.RegistroDietaDiaria;

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
            SaludDB.TablaChequeoSalud.ESTADO,
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
        Long resultado = abrirDB().insert(
                SaludDB.TablaChequeoSalud.NOMBRE_TABLA,
                null,
                chequeoSalud.toContentvalues()
        );
        System.out.println("CHEQUEO SALUD: ID => "+resultado);
        System.out.println(chequeoSalud);
        cerrarDB();
        return resultado;
    }

    //OBTENER TODOS LOS REGISTROS
    public List<ChequeoSalud> obtenerRegistros(){
        List<ChequeoSalud> listaRegistros = new ArrayList<>();
        Cursor cursor = abrirDB().query(
                SaludDB.TablaChequeoSalud.NOMBRE_TABLA,
                null,
                null,
                null,
                null,
                null,
                SaludDB.TablaChequeoSalud.FECHA_CHEQUEO + " DESC");
        while (cursor.moveToNext()){
            ChequeoSalud chequeoSalud = new ChequeoSalud(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8));
            listaRegistros.add(chequeoSalud);
        }
        cursor.close();
        cerrarDB();
        return listaRegistros;
    }

    //OBTENER REGISTRO POR ID
    public ChequeoSalud consultarPorId(int parametro){
        //String [] id = { String.valueOf(parametro) };
        abrirDB();
        Cursor cursor = abrirDB().query(
                SaludDB.TablaChequeoSalud.NOMBRE_TABLA, camposChequeSalud,
                "id=" + parametro,
                null,
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
                    cursor.getString(7),
                    cursor.getString(8));
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
        long resultado = abrirDB().update(
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
        long resultado = abrirDB().delete(
                SaludDB.TablaChequeoSalud.NOMBRE_TABLA, "id = ?",
                id
        );
        return resultado;
    }

    //FUNCIONES ESPECIALES

    public ChequeoSalud consultarPorEstado(String parametro){
        String [] estado = { parametro };
        abrirDB();
        Cursor cursor = abrirDB().query(
                SaludDB.TablaChequeoSalud.NOMBRE_TABLA, camposChequeSalud,
                "estado = ?",
                estado,
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
                    cursor.getString(7),
                    cursor.getString(8));
            cursor.close();
            cerrarDB();
            return chequeoSalud;
        }
        else{
            return null;
        }
    }

}
