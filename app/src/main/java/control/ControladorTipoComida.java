package control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import entidades.RegistroDietaDiaria;
import entidades.TipoComida;

public class ControladorTipoComida {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposDTipoComida = {
            SaludDB.TablaTipoComida.ID,
            SaludDB.TablaTipoComida.NOMBRE_TIPO_COMIDA,
            SaludDB.TablaTipoComida.CANTIDAD_CALORIFICA,
            SaludDB.TablaTipoComida.TAMANIO_PORCION
    };

    public ControladorTipoComida(Context context){
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

    public long crear(TipoComida tipoComida){
        abrirDB();
        Long resultado = instanciaBD.insert(
                SaludDB.TablaTipoComida.NOMBRE_TABLA,null,tipoComida.toContentvalues());
        cerrarDB();
        return resultado;
    }

    public List<TipoComida> obtenerTipoComida(){
        List<TipoComida> listaTipoComida = new ArrayList<>();
        abrirDB();
        Cursor cursor = instanciaBD.query(
                SaludDB.TablaTipoComida.NOMBRE_TABLA,
                null,
                null,
                null,
                null,
                null,
                SaludDB.TablaTipoComida.ID
        );
        while (cursor.moveToNext())
        {
            TipoComida tipoComida = new TipoComida(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getInt(3)
            );
            listaTipoComida.add(tipoComida);
        }
        cursor.close();
        cerrarDB();
        return listaTipoComida;
    }

    public TipoComida obtenerTipoComidaId(int parametro){
        //List<TipoComida> listaTipoComida = new ArrayList<>();
        abrirDB();
        Cursor cursor = instanciaBD.query(
                SaludDB.TablaTipoComida.NOMBRE_TABLA,
                camposDTipoComida,
                "id = " + parametro,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst())
        {
            TipoComida tipoComida = new TipoComida(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getInt(3)
            );
            cursor.close();
            cerrarDB();
            return tipoComida;
        }
        else
        {
            return  null;
        }
    }

    public long actualizar(TipoComida tipoComida){

        String [] id = { String.valueOf(tipoComida.getId()) };
        abrirDB();
        long resultado = instanciaBD.update(
                SaludDB.TablaTipoComida.NOMBRE_TABLA,
                tipoComida.toContentvalues(),
                "id = ?",
                id
        );
        cerrarDB();
        return resultado;
    }

    public long eliminar(TipoComida tipoComida){
        abrirDB();
        String [] id = { String.valueOf(tipoComida.getId()) };
        long resultado = instanciaBD.delete(
                SaludDB.TablaTipoComida.NOMBRE_TABLA, "id = ?",
                id
        );
        return resultado;
    }
}
