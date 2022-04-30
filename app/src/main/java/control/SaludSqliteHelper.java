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


        baseDatos.execSQL("INSERT INTO rangos_altura ( alturaMinima, alturaMaxima) VALUES ( '161.0', '163.0');");
        baseDatos.execSQL("INSERT INTO rangos_altura ( alturaMinima, alturaMaxima) VALUES ( '164.0', '168.0');");
        baseDatos.execSQL("INSERT INTO rangos_altura ( alturaMinima, alturaMaxima) VALUES ( '169.0', '170.0');");

        baseDatos.execSQL("INSERT INTO rangos_peso ( pesoMinima, pesoMaxima) VALUES ( '161.0', '171.0');");
        baseDatos.execSQL("INSERT INTO rangos_peso ( pesoMinima, pesoMaxima) VALUES ( '172.0', '175.0');");
        baseDatos.execSQL("INSERT INTO rangos_peso ( pesoMinima, pesoMaxima) VALUES ( '181.0', '191.0');");

        baseDatos.execSQL("INSERT INTO rangos_imc (sexoImc, rangoAlturaId, rangoPesoId) VALUES ( 'MASCULINO', '1', '1');");
        baseDatos.execSQL("INSERT INTO rangos_imc (sexoImc, rangoAlturaId, rangoPesoId) VALUES ( 'MASCULINO', '2', '2');");
        baseDatos.execSQL("INSERT INTO rangos_imc (sexoImc, rangoAlturaId, rangoPesoId) VALUES ( 'MASCULINO', '3', '3');");

        baseDatos.execSQL("INSERT INTO chequeos_salud (fechaChequeo, pesoActual, alturaActual, valorImcActual, mensajeImcActual, rangosImcId, usuariosId) VALUES ('2022-01-28', '150.0', '165.0', '0.25', '0.22', '1', '1');");
        baseDatos.execSQL("INSERT INTO chequeos_salud (fechaChequeo, pesoActual, alturaActual, valorImcActual, mensajeImcActual, rangosImcId, usuariosId) VALUES ('2022-02-21', '150.0', '165.0', '0.25', '0.22', '2', '1');");
        baseDatos.execSQL("INSERT INTO chequeos_salud (fechaChequeo, pesoActual, alturaActual, valorImcActual, mensajeImcActual, rangosImcId, usuariosId) VALUES ('2021-01-24', '150.0', '165.0', '0.25', '0.22', '3', '1');");
        baseDatos.execSQL("INSERT INTO chequeos_salud (fechaChequeo, pesoActual, alturaActual, valorImcActual, mensajeImcActual, rangosImcId, usuariosId) VALUES ('2021-01-23', '150.0', '165.0', '0.25', '0.22', '3', '1');");
        baseDatos.execSQL("INSERT INTO chequeos_salud (fechaChequeo, pesoActual, alturaActual, valorImcActual, mensajeImcActual, rangosImcId, usuariosId) VALUES ('2019-08-12', '150.0', '165.0', '0.25', '0.22', '1', '1');");
        baseDatos.execSQL("INSERT INTO chequeos_salud (fechaChequeo, pesoActual, alturaActual, valorImcActual, mensajeImcActual, rangosImcId, usuariosId) VALUES ('2019-05-15', '150.0', '165.0', '0.25', '0.22', '2', '1');");
        baseDatos.execSQL("INSERT INTO chequeos_salud (fechaChequeo, pesoActual, alturaActual, valorImcActual, mensajeImcActual, rangosImcId, usuariosId) VALUES ('2022-04-27', '150.0', '165.0', '0.25', '0.22', '2', '1');");

        baseDatos.execSQL("INSERT INTO registros_ritmo_cardiaco (bpm, chequeoSaludId) VALUES ( '160', '1');");
        baseDatos.execSQL("INSERT INTO registros_ritmo_cardiaco (bpm, chequeoSaludId) VALUES ( '125', '2');");
        baseDatos.execSQL("INSERT INTO registros_ritmo_cardiaco (bpm, chequeoSaludId) VALUES ( '80', '3');");
        baseDatos.execSQL("INSERT INTO registros_ritmo_cardiaco (bpm, chequeoSaludId) VALUES ( '85', '4');");
        baseDatos.execSQL("INSERT INTO registros_ritmo_cardiaco (bpm, chequeoSaludId) VALUES ( '91', '5');");
        baseDatos.execSQL("INSERT INTO registros_ritmo_cardiaco (bpm, chequeoSaludId) VALUES ( '106', '6');");
        baseDatos.execSQL("INSERT INTO registros_ritmo_cardiaco (bpm, chequeoSaludId) VALUES ( '91', '7');");

        baseDatos.execSQL("INSERT INTO registros_presion_arterial (sistolica, diastolica, chequeoSaludId) VALUES ('80', '110', '1');");
        baseDatos.execSQL("INSERT INTO registros_presion_arterial (sistolica, diastolica, chequeoSaludId) VALUES ('90', '130', '2');");
        baseDatos.execSQL("INSERT INTO registros_presion_arterial (sistolica, diastolica, chequeoSaludId) VALUES ('91', '125', '3');");
        baseDatos.execSQL("INSERT INTO registros_presion_arterial (sistolica, diastolica, chequeoSaludId) VALUES ('88', '128', '4');");
        baseDatos.execSQL("INSERT INTO registros_presion_arterial (sistolica, diastolica, chequeoSaludId) VALUES ('95', '127', '5');");
        baseDatos.execSQL("INSERT INTO registros_presion_arterial (sistolica, diastolica, chequeoSaludId) VALUES ('86', '125', '6');");
        baseDatos.execSQL("INSERT INTO registros_presion_arterial (sistolica, diastolica, chequeoSaludId) VALUES ('70', '121', '7');");















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
