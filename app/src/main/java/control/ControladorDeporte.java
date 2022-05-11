package control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathFunctionResolver;

import entidades.ChequeoSalud;
import entidades.Deporte;
import entidades.Usuario;

public class ControladorDeporte {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposDeporte = {
            SaludDB.TablaDeportes.ID,
            SaludDB.TablaDeportes.DEPORTE
    };

    public ControladorDeporte(Context context){
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

    public long crear(Deporte deporte){

        Long resultado = abrirDB().insert(
                SaludDB.TablaDeportes.NOMBRE_TABLA,
                null, deporte.toContentvalues()
        );
        cerrarDB();
        return resultado;
    }

    public List<Deporte> obtenerRegistros(){
        List<Deporte> lstDeportes = new ArrayList<>();
        abrirDB();
        Cursor cursor = abrirDB().query(
                SaludDB.TablaDeportes.NOMBRE_TABLA,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            Deporte deporte = new Deporte();
            deporte.setId(cursor.getInt(0));
            deporte.setDeporte(cursor.getString(1));
            lstDeportes.add(deporte);
        }
        cursor.close();
        cerrarDB();
        return lstDeportes;
    }

    public Deporte consultarPorId(int parametro){
        Deporte deporte = new Deporte();
        String [] id = { String.valueOf(parametro) };
        abrirDB();
        Cursor cursor = abrirDB().query(
                SaludDB.TablaDeportes.NOMBRE_TABLA, camposDeporte,
                "id = ?",
                id,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            deporte.setId(cursor.getInt(0));
            deporte.setDeporte(cursor.getString(1));
            cursor.close();
            cerrarDB();
            return deporte;
        }
        else{
            return null;
        }
    }

    public long actualizar(Deporte deporte){
        //VERIFICAR INTEGRIDAD

        String [] id = { String.valueOf(deporte.getId()) };
        abrirDB();
        long resultado = abrirDB().update(
                SaludDB.TablaDeportes.NOMBRE_TABLA,
                deporte.toContentvalues(),
                "id = ?",
                id
        );
        cerrarDB();
        return resultado;
    }

    public long eliminar(Deporte deporte){
        abrirDB();
        String [] id = { String.valueOf(deporte.getId()) };
        long resultado = abrirDB().delete(
                SaludDB.TablaDeportes.NOMBRE_TABLA, "id = ?",
                id
        );
        return resultado;
    }
}
