package control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import entidades.Deporte;
import entidades.RegistroDietaDiaria;
import entidades.TipoComida;

public class ControladorRegistroDietaDiaria {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposRegistroDietaDiaria = {
            SaludDB.TablaRegistroDietaDiaria.ID,
            SaludDB.TablaRegistroDietaDiaria.DIETA_ALIMENTICIA_ID,
            SaludDB.TablaRegistroDietaDiaria.DIA_SEMANA_ID
    };

    public ControladorRegistroDietaDiaria(Context context){
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

    public long crear(RegistroDietaDiaria dietaDiaria){
        Long resultado = abrirDB().insert(
                SaludDB.TablaRegistroDietaDiaria.NOMBRE_TABLA,
                null, dietaDiaria.toContentvalues()
        );
        cerrarDB();
        return resultado;
    }

    public List<RegistroDietaDiaria> obtenerRegistroDieta(int idDietaAlimenticia){
        List<RegistroDietaDiaria> registroDietaDiariaList = new ArrayList<>();
        abrirDB();
        Cursor cursor = instanciaBD.query(
                SaludDB.TablaRegistroDietaDiaria.NOMBRE_TABLA,
                null,
                "dietaAlimenticiaId = " + idDietaAlimenticia,
                null,
                null,
                null,
                SaludDB.TablaRegistroDietaDiaria.ID
        );
        while (cursor.moveToNext())
        {
            RegistroDietaDiaria registroDietaDiaria = new RegistroDietaDiaria(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );
            registroDietaDiariaList.add(registroDietaDiaria);
        }
        cursor.close();
        cerrarDB();
        return registroDietaDiariaList;
    }

    public RegistroDietaDiaria obtenerRegistroDietaDia(int dia){
        //List<TipoComida> listaTipoComida = new ArrayList<>();
        abrirDB();
        Cursor cursor = instanciaBD.query(
                SaludDB.TablaRegistroDietaDiaria.NOMBRE_TABLA,
                camposRegistroDietaDiaria,
                "diaSemanaId = " + dia ,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst())
        {
            RegistroDietaDiaria registroDietaDiaria = new RegistroDietaDiaria(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );
            cursor.close();
            cerrarDB();
            return registroDietaDiaria;
        }
        else
        {
            return  null;
        }
    }
}
