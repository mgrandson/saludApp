package control;

import android.content.ContentValues;
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

        //REGISTROS POR DEFECTO
        agregarRegistrosPorDefecto(baseDatos);
        System.out.println("SE CREO BASE DE DATOS...");

        /*
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

        */
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


    public void agregarRegistrosPorDefecto(SQLiteDatabase db){
        //USUARIO PRUEBAS
        db.execSQL("INSERT INTO usuario (nombreUsuario, contrasenia, nombre, apellido, fechaNacimiento, genero)" +
                "VALUES ('juan', 'juan', 'Juan', 'Perez', '2022-05-01', 'Masculino');");

        //RANGOS DE ALTURA
        db.execSQL("INSERT INTO rangos_altura (alturaMinima, alturaMaxima) " +
                "VALUES (1.52, 1.55), (1.57,1.60), (1.62,1.65), (1.68,1.70)," +
                "(1.73,1.75), (1.78,1.80),(1.83,1.85),(1.88,1.90),(1.93,2.10);");

        //RANGOS DE PESO
        db.execSQL("INSERT INTO rangos_peso (pesoMinimo, pesoMaximo) " +
                "VALUES (44.0,58.0), (58.0,69.0), (69.0,82.0), (81.0,93.0), (93.0,250)," +
                "(47.0,61.0), (62.0,74.0), (74.0,87.0), (87.0,100.0), (100.0,250)," +
                "(50.0,65.0), (66.0,79.0), (79.0,93.0), (93.0,106.0), (106.0,250)," +
                "(54.0,69.0), (70.0,84.0), (84.0,98.0), (98.0,113.0), (113.0,250)," +
                "(57.0,74.0), (74.0,89.0), (89.0,104.0), (104.0,119.0), (119.0,250)," +
                "(60.0,78.0), (79.0,94.0), (95.0,110.0), (110.0,127.0), (127.0,250)," +
                "(64.0,83.0), (84.0,99.0), (100.0,117.0), (117.0,134.0), (134.0,250)," +
                "(67.0,87.0), (88.0,105.0), (106.0,123.0), (123.0,141.0), (141.0,250)," +
                "(71.0,89.0), (93.0,108.0), (112.0,127.0), (130.0,145.0), (145.0,250);");

        //RANGOS DE IMC
        db.execSQL("INSERT INTO rangos_imc (sexoImc, rangoAlturaId, rangoPesoId) VALUES " +
                "('F',1,1), ('F',1,2), ('F',1,3), ('F',1,4), ('F',1,5)," +
                "('F',2,6),('F',2,7),('F',2,8),('F',2,9),('F',2,10)," +
                "('F',3,11),('F',3,12),('F',3,13),('F',3,14),('F',3,15)," +
                "('F',4,16),('F',4,17),('F',4,18),('F',4,19),('F',4,20)," +
                "('F',5,21),('F',5,22),('F',5,23),('F',5,24),('F',5,25)," +
                "('F',6,26),('F',6,27),('F',6,28),('F',6,29),('F',6,30)," +
                "('F',7,31),('F',7,32),('F',7,33),('F',7,34),('F',7,35)," +
                "('F',8,36),('F',8,37),('F',8,38),('F',8,39),('F',8,40)," +
                "('F',9,41),('F',9,42),('F',9,43),('F',9,44),('F',9,45)," +
                "('M',1,1),('M',1,2),('M',1,3),('M',1,4),('M',1,5)," +
                "('M',2,6),('M',2,7),('M',2,8),('M',2,9),('M',2,10)," +
                "('M',3,11),('M',3,12),('M',3,13),('M',3,14),('M',3,15)," +
                "('M',4,16),('M',4,17),('M',4,18),('M',4,19),('M',4,20)," +
                "('M',5,21),('M',5,22),('M',5,23),('M',5,24),('M',5,25)," +
                "('M',6,26),('M',6,27),('M',6,28),('M',6,29),('M',6,30)," +
                "('M',7,31),('M',7,32),('M',7,33),('M',7,34),('M',7,35)," +
                "('M',8,36),('M',8,37),('M',8,38),('M',8,39),('M',8,40)," +
                "('M',9,41),('M',9,42),('M',9,43),('M',9,44),('M',9,45);");

        //DIAS DE LA SEMANA
        db.execSQL("INSERT INTO dias_semana (nombreDia) " +
                "VALUES ('DOMINGO'), ('LUNES'),('MARTES'),('MIERCOLES'),('JUEVES'),('VIERNES'),('SABADO');");

        //DEPORTES
        db.execSQL("INSERT INTO deportes (deporte) " +
                "VALUES ('Natación'),('Entrenamiento de pesas'),('Boxeo'),('Tenis'),('Ping-Pong'),('Squash'),('Pádel'),('Caminar'),('Correr'),('Yoga')," +
                "('Crossfit'),('Saltar a la cuerda'),('Escalada'),('Spinning'),('Aquaspinning'),('Baloncesto'),('Bicicleta'),('Patinar'),('Fútbol'),('Remo')," +
                "('Karate'),('Gimnasia'),('Rugby'),('Atletismo'),('Sentadillas con peso'),('Zancadas y saltos'),('Plancha arriba y abajo'),('Subir escaleras'),('Levantamiento de mancuernas'),('Press con barras y pesas')," +
                "('Extensiones de pierna'),('Peso Muerto'),('Remo con barra'),('Battle Rope'),('Face Pull'),('Hip thrust'),('Tenis de mesa'),('Voleibol'),('Pilates'),('Taekwondo')," +
                "('Aerobic'),('Surf'),('Zumba'),('Kick boxing'),('Softbol'),('Salto en estrella'),('Puente'),('Embestida'),('Plancha'),('Flexiones en el suelo');");

        //FACTORES DE ACTIVIDAD
        db.execSQL("INSERT INTO factores_actividad (factor, descripcion) " +
                "VALUES ('LIGERA','Actividades típicas de la vida diaria + 30-60 min (2 veces/semana) de actividad moderadamente activa')," +
                "('MODERADA','Actividades típicas de la vida diaria + al menos 60 min de actividad moderadamente activa o 20 min/día de actividad vigorosa')," +
                "('INTENSA','Actividades típicas de la vida diaria + al menos 60 min de actividad moderadamente activa + 60 min de actividad vigorosa y/o 120 min/día de actividad moderada.');");

        //TIPOS DE COMIDA
        db.execSQL("INSERT INTO tipos_comida (nombreTipoComida, cantidadCalorifica, tamanioPorcion) " +
                "VALUES ('Pan Integral',69.0, '1 Rebanada')," +
                "('Huevo cocido',74.0,'1 huevo grande')," +
                "('Croissant',227.0,'1 porción (56 g)')," +
                "('Manzana Roja', 73.0, '1 manzana mediana')," +
                "('Manzana Verde', 39.0, '1 manzana mediana')," +
                "('Brócoli', 31.0, '1 taza picado')," +
                "('Zanahoria',52.0, '1 taza picado')," +
                "('Arroz frito',333.0,'1 taza')," +
                "('Arroz Blanco',204.0,'1 taza cocido')," +
                "('Pechuga de pollo a la plancha', 178.0,'1 porción')," +
                "('Pollo Guisado',216.0, '1 porción (180 g)')," +
                "('Pollo Rostizado',285.0, '1 porción (120 g)')," +
                "('Nuggets de pollo',285.0,'1 porción (96 g)')," +
                "('Filete de pollo empanizado',174.0,'1 porción (100 g)')," +
                "('Carne de res',348.0,'1 taza cocido')," +
                "('Carne Asada',112.0, '1 porción mediana')," +
                "('Carne de Cerdo',363.0,'1 taza cocida')," +
                "('Albóndigas de carne',39.0,'1 albóndiga pequeña')," +
                "('Ensalada de lechuga con verduras variadas',12.0,'1 taza')," +
                "('Ensalada de lechuga con aguacate, tomate y/o zanahoria',45.0,'1 taza')," +
                "('Ensalada de frutas',108.0,'1 taza')," +
                "('Ensalada de pollo o pavo',58.0,'1 taza')," +
                "('Frijoles cocidos',382.0,'1 taza')," +
                "('Frijoles Refritos',367.0,'1 taza')," +
                "('Frijoles Blancos',249.0,'1 taza')," +
                "('Verdura hervida',132.0,'1 taza')," +
                "('Verdura al vapor',120.0, '1 taza')," +
                "('Verdura salteada',125.0, '1 taza')," +
                "('Ensalada de verduras mixtas',9.0,'1 taza')," +
                "('Pan Dulce',223.0,'1 pan mediano')," +
                "('Pan Blanco',65.0,'1 rebanada')," +
                "('Pan Multigrano',122.0,'1 rebanada')," +
                "('Pan francés',86.0,'1 pan mediano')," +
                "('Baguette',263.0,'1 rebanada grande')," +
                "('Sandwich de jamón y queso',339.0,'1 sandwich')," +
                "('Sandwich de pollo',265.0,'1 sandwich')," +
                "('Mezcla de nueces mixtas y semillas',181.0,'1 porción (30 g)')," +
                "('Mix Semillas',556.0,'1 porción (100 g)')," +
                "('Pepitas de calabaza',60.0,'1 porción (10 g)')," +
                "('Chocolate',219.0,'1 barra')," +
                "('Café',2.0,'1 taza (240 ml)')," +
                "('Agua',0.0,'1 taza (240 ml)')," +
                "('Capuchino',74.0,'1 taza (240 ml)')," +
                "('Ponche de fruta',114.0,'1 taza (240 ml)')," +
                "('Licuado de fruta con leche',198.0,'1 taza (240 ml)')," +
                "('Jugo de manzana',92.0,'1 porción (335 ml)')," +
                "('Jugo de naranja',112.0,'1 taza')," +
                "('Agua de coco',46.0,'1 taza')," +
                "('Almendras',164.0,'23 granos enteros')," +
                "('Plátano Maduro Hervido',230.0,'1 taza')," +
                "('Plátano Frito',456.0,'1 taza')," +
                "('Pupusa de queso',180.0,'1 pupusa')," +
                "('Pupusa variedad',187.0,'1 pupusa')," +
                "('Chocolate caliente',190.0,'1 taza')," +
                "('Atole variedad',209.0,'1 taza')," +
                "('Soda enlatada',140.0,'1 lata (350 ml)')," +
                "('Crema',37.0,'1 porción (15 g)')," +
                "('Leche deslactosada',115.0,'1 taza (250 ml)')," +
                "('Pescado a la plancha',149.0,'1 taza (trozos)')," +
                "('Pescado al horno o hervido',142.0,'1 filete')," +
                "('Pescado frito',117.0,'1 porción (57 g)')," +
                "('Pescado empanizado',181.0,'1 porción (85 g)')," +
                "('Pescado a la parrilla',123.0,'1 filete')," +
                "('Camarones al horno o a la parrilla',117.0,'1 taza')," +
                "('Cóctel de camarones',196.0,'1 taza')," +
                "('Sopa de pollo',75.0,'1 taza')," +
                "('Sopa de verduras',98.0,'1 taza')," +
                "('Tamal de pollo',126.0,'1 tamal')," +
                "('Crema de mariscos',60.0,'1 plato (200 ml)')," +
                "('Aguacate',160.0,'1/2 aguacate')," +
                "('Guacamole',23.0,'1 cucharada')," +
                "('Queso Fresco',41.0,'1 porción (28 g)')," +
                "('Queso Duro',84.0,'1 porción (24 g)')," +
                "('Queso americano',96.0,'1 reabanada')," +
                "('Queso mozzarella',60.0,'1 rebanada')," +
                "('Pizza',280.0,'1 rebanada')," +
                "('Hamburguesa',278.0,'1 unidad');");

        //DETALLES POR FACTOR -- TEMP
        db.execSQL("INSERT INTO detalles_deportes_x_factor (energiaGastada, duracion, factorActividadId, deporteId) " +
                "VALUES " +
                "(5, 10, 1, 1), (5, 10, 1, 2), (5, 10, 1, 3), (5, 10, 1, 4), (5, 10, 1, 5), " +
                "(5, 10, 1, 6), (5, 10, 1, 7), (5, 10, 1, 8), (5, 10, 1, 9), (5, 10, 1, 10), " +
                "(5, 10, 1, 11), (5, 10, 1, 12),(5, 10, 1, 13),(5, 10, 1, 14),(5, 10, 1, 15), " +
                "(10, 15, 2, 16), (10, 15, 2, 17), (10, 15, 2, 18), (10, 15, 2, 19), (10, 15, 2, 20), " +
                "(10, 15, 2, 21), (10, 15, 2, 22), (10, 15, 2, 23), (10, 15, 2, 24), (10, 15, 2, 25), " +
                "(10, 15, 2, 26), (10, 15, 2, 27), (10, 15, 2, 28), (10, 15, 2, 29), (10, 15, 2, 30), " +
                "(20, 20, 3, 31), (20, 20, 3, 32), (20, 20, 3, 33), (20, 20, 3, 34), (20, 20, 3, 35), " +
                "(20, 20, 3, 36), (20, 20, 3, 37), (20, 20, 3, 38), (20, 20, 3, 39), (20, 20, 3, 40), " +
                "(20, 20, 3, 41), (20, 20, 3, 42), (20, 20, 3, 43), (20, 20, 3, 44), (20, 20, 3, 45), " +
                "(20, 20, 3, 46), (20, 20, 3, 47), (20, 20, 3, 48), (20, 20, 3, 49), (20, 20, 3, 50);");
    }
}
