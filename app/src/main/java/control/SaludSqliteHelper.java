package control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

public class SaludSqliteHelper extends SQLiteOpenHelper {



    public SaludSqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase baseDatos) {
        //generar las tablas de la base de datos
        baseDatos.execSQL(SaludDB.TablaUsuarioDB.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaRangoAltura.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaRangoPeso.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaRangoImc.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaChequeoSalud.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaRegistroHidratacion.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaRegistroRitmoCardiaco.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaRegistroPresionArterial.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaDiaSemana.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaTipoComida.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaDietaAlimenticia.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaRegistroDietaDiaria.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaDetalleDietaPorTiempo.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaDeportes.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaFactorActividad.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaDetalleDeportePorFactor.CREAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaRegistroActividadFisicaDiaria.CREAR_TABLA);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase baseDatos, int versionAntigua, int versionNueva) {
        baseDatos.execSQL(SaludDB.TablaUsuarioDB.ELIMINAR_TABLA);
        baseDatos.execSQL(SaludDB.TablaUsuarioDB.CREAR_TABLA);
    }









}
