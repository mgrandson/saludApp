package control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import entidades.Deporte;
import entidades.DetalleDietaPorTiempo;
import entidades.RegistroDietaDiaria;

public class ControladorDetalleDietaPorTiempo {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposDetalleDietaPorTiempo = {
            SaludDB.TablaDetalleDietaPorTiempo.ID,
            SaludDB.TablaDetalleDietaPorTiempo.TIEMPO_COMIDA,
            SaludDB.TablaDetalleDietaPorTiempo.REGISTRO_DIETA_DIARIA_ID,
            SaludDB.TablaDetalleDietaPorTiempo.TIEMPO_COMIDA
    };

    public ControladorDetalleDietaPorTiempo(Context context){
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

    public long crear(DetalleDietaPorTiempo dietaPorTiempo){
        Long resultado = abrirDB().insert(
                SaludDB.TablaDetalleDietaPorTiempo.NOMBRE_TABLA,
                null, dietaPorTiempo.toContentvalues()
        );
        cerrarDB();
        return resultado;
    }

    public List<DetalleDietaPorTiempo> obtenerDetallesDietaPorTiempos(int registro){
        List<DetalleDietaPorTiempo> detalleDietaPorTiempoList = new ArrayList<>();
        abrirDB();
        Cursor cursor = instanciaBD.query(
                SaludDB.TablaDetalleDietaPorTiempo.NOMBRE_TABLA,
                null,
                "registroDietaDiariaId = " + registro,
                null,
                null,
                null,
                SaludDB.TablaDetalleDietaPorTiempo.ID
        );
        while (cursor.moveToNext())
        {
            DetalleDietaPorTiempo detalleDietaPorTiempo = new DetalleDietaPorTiempo(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3)
            );
            detalleDietaPorTiempoList.add(detalleDietaPorTiempo);
        }
        cursor.close();
        cerrarDB();
        return detalleDietaPorTiempoList;
    }

    public DetalleDietaPorTiempo obtenerDetallesDietaPorTiemposDia(int registro){
        //List<TipoComida> listaTipoComida = new ArrayList<>();
        abrirDB();
        Cursor cursor = instanciaBD.query(
                SaludDB.TablaDetalleDietaPorTiempo.NOMBRE_TABLA,
                camposDetalleDietaPorTiempo,
                "registroDietaDiariaId = " + registro,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst())
        {
            DetalleDietaPorTiempo detalleDietaPorTiempo = new DetalleDietaPorTiempo(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3)
            );
            cursor.close();
            cerrarDB();
            return detalleDietaPorTiempo;
        }
        else
        {
            return  null;
        }
    }
}
