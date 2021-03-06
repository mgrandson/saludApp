package control;

import android.provider.BaseColumns;

/**
 * 1.Contiene los nombres de las tablas y campos de la base de datos
 * 2. Contiene las sentencias para crear la base de datos
 */
public  class SaludDB {

    public static final String NOMBRE_BD= "salud.db";
    public static final int VERSION_BD= 1;


    public static abstract class TablaUsuarioDB implements BaseColumns{
        //nombre de la tabla usuarios
        public static final String NOMBRE_TABLA = "usuario";
        //nombre de los campos de la tabla usuario

        public static final String ID = "idUsuario";
        public static final String NOMBRE_USUARIO = "nombreUsuario";
        public static final String CONTRASENIA = "contrasenia";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDO = "apellido";
        public static final String FECHA_NACIMIENTO= "fechaNacimiento";
        public static final String GENERO = "genero";

        //sentencia sql para crear la tabla usuario
        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +"  ( "
                + ID +"  INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NOMBRE_USUARIO+" TEXT NOT NULL ,"
                +CONTRASENIA+" TEXT NOT NULL,"
                +NOMBRE+" TEXT NOT NULL,"
                +APELLIDO+" TEXT NOT NULL,"
                +FECHA_NACIMIENTO+" TEXT NOT NULL,"
                +GENERO+" TEXT NOT NULL)";


        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS usuario";


    }

    //laves foraneas OK
    public static abstract class TablaRangoImc implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "rangos_imc";

        //Nombre de campos
        public static final String ID = "id";
        public static final String SEXO_IMC = "sexoImc";
        public static final String RANGO_ALTURA_ID = "rangoAlturaId";
        public static final String RANGO_PESO_ID = "rangoPesoId";


        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                SEXO_IMC + " TEXT NOT NULL," +
                RANGO_ALTURA_ID + " INTEGER NOT NULL," +
                RANGO_PESO_ID + " INTEGER NOT NULL," +
                "FOREIGN KEY("+RANGO_PESO_ID+") REFERENCES "+ TablaRangoPeso.NOMBRE_TABLA+ "("+TablaRangoPeso.ID+"),"
                +"FOREIGN KEY("+ RANGO_ALTURA_ID+") REFERENCES "+TablaRangoAltura.NOMBRE_TABLA+"("+TablaRangoAltura.ID+"));";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }

    //laves foraneas OK
    public static abstract class TablaChequeoSalud implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "chequeos_salud";

        //Nombre de campos
        public static final String ID_CHEQUEO = "id";
        public static final String FECHA_CHEQUEO = "fechaChequeo";
        public static final String PESO_ACTUAL = "pesoActual";
        public static final String ALTURA_ACTUAL = "alturaActual";
        public static final String VALOR_IMC_ACTUAL = "valorImcActual";
        public static final String MENSAJE_IMC_ACTUAL = "mensajeImcActual";
        public static final String ESTADO = "estado";
        public static final String RANGOS_IMC_ID = "rangosImcId";
        public static final String USUARIOS_ID = "usuariosId";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID_CHEQUEO +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                FECHA_CHEQUEO + " TEXT NOT NULL," +
                PESO_ACTUAL + " REAL NOT NULL," +
                ALTURA_ACTUAL + " REAL NOT NULL," +
                VALOR_IMC_ACTUAL + " REAL NOT NULL," +
                MENSAJE_IMC_ACTUAL + " TEXT NOT NULL," +
                ESTADO + " TEXT NOT NULL," +
                RANGOS_IMC_ID +" INTEGER NOT NULL," +
                USUARIOS_ID +" INTEGER NOT NULL," +
                "FOREIGN KEY("+RANGOS_IMC_ID+") REFERENCES "+ TablaRangoImc.NOMBRE_TABLA+ "("+TablaRangoImc.ID+"),"
                +"FOREIGN KEY("+ USUARIOS_ID+") REFERENCES "+TablaUsuarioDB.NOMBRE_TABLA+"("+TablaUsuarioDB.ID+"));";


        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }

    //laves foraneas OK eliminacion en cascada
    public static abstract class TablaRegistroHidratacion implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "registros_hidratacion";

        //Nombre de campos
        public static final String ID = "id";
        public static final String CONSUMO_AGUA = "consumoAgua";
        public static final String CHEQUEO_SALUD_ID = "chequeoSaludId";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                CONSUMO_AGUA + " REAL NOT NULL," +
                CHEQUEO_SALUD_ID + " INTEGER NOT NULL,"+
                "FOREIGN KEY("+ CHEQUEO_SALUD_ID+") REFERENCES "+TablaChequeoSalud.NOMBRE_TABLA+"("+TablaChequeoSalud.ID_CHEQUEO+") ON DELETE CASCADE);";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }
    //laves foraneas OK eliminacion en cascada
    public static abstract class TablaRegistroRitmoCardiaco implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "registros_ritmo_cardiaco";

        //Nombre de campos
        public static final String ID = "id";
        public static final String BPM = "bpm";
        public static final String CHEQUEO_SALUD_ID = "chequeoSaludId";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                BPM + " INT NOT NULL," +
                CHEQUEO_SALUD_ID + " INTEGER NOT NULL,"+
                "FOREIGN KEY("+ CHEQUEO_SALUD_ID+") REFERENCES "+TablaChequeoSalud.NOMBRE_TABLA+"("+TablaChequeoSalud.ID_CHEQUEO+") ON DELETE CASCADE);";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }

    //laves foraneas OK eliminacion en cascada
    public static abstract class TablaRegistroPresionArterial implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "registros_presion_arterial";

        //Nombre de campos
        public static final String ID = "id";
        public static final String SISTOLICA = "sistolica";
        public static final String DIASTOLICA = "diastolica";
        public static final String CHEQUEO_SALUD_ID = "chequeoSaludId";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                SISTOLICA + " INT NOT NULL," +
                DIASTOLICA + " INT NOT NULL," +
                CHEQUEO_SALUD_ID + " INTEGER NOT NULL, "+
                "FOREIGN KEY("+CHEQUEO_SALUD_ID+") REFERENCES "+TablaChequeoSalud.NOMBRE_TABLA+"("+TablaChequeoSalud.ID_CHEQUEO+") ON DELETE CASCADE);";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }

    //laves foraneas OK eliminacion en cascada
    public static abstract class TablaDietaAlimenticia implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "dietas_alimenticias";

        //Nombre de campos
        public static final String ID = "id";
        public static final String DURACION_DIETA = "duracionDieta";
        public static final String TOTAL_CALORIAS = "totalCalorias";
        public static final String CHEQUEO_SALUD_ID = "chequeoSaludId";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                DURACION_DIETA + " INT NOT NULL," +
                TOTAL_CALORIAS + " REAL NOT NULL," +
                CHEQUEO_SALUD_ID + " INTEGER NOT NULL,"+
                "FOREIGN KEY("+ CHEQUEO_SALUD_ID+") REFERENCES "+TablaChequeoSalud.NOMBRE_TABLA+"("+TablaChequeoSalud.ID_CHEQUEO+") ON DELETE CASCADE);";
        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }

    //laves foraneas OK
    public static abstract class TablaRegistroDietaDiaria implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "registros_dieta_diaria";

        //Nombre de campos
        public static final String ID = "id";
        public static final String DIETA_ALIMENTICIA_ID = "dietaAlimenticiaId";
        public static final String DIA_SEMANA_ID = "diaSemanaId";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                DIETA_ALIMENTICIA_ID + " INTEGER NOT NULL," +
                DIA_SEMANA_ID + " INTEGER NOT NULL" +
                ",FOREIGN KEY("+DIETA_ALIMENTICIA_ID+") REFERENCES "+ TablaDietaAlimenticia.NOMBRE_TABLA+ "("+TablaDietaAlimenticia.ID+"),"
                +"FOREIGN KEY("+ DIA_SEMANA_ID+") REFERENCES "+TablaDiaSemana.NOMBRE_TABLA+"("+TablaDiaSemana.ID+"));";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }

    //laves foraneas OK
    public static abstract class TablaDetalleDietaPorTiempo implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "detalles_dieta_x_tiempo";

        //Nombre de campos
        public static final String ID = "id";
        public static final String TIEMPO_COMIDA = "tiempoComida";
        public static final String REGISTRO_DIETA_DIARIA_ID = "registroDietaDiariaId";
        public static final String TIPO_COMIDA_ID = "tipoComidaId";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                TIEMPO_COMIDA + " TEXT NOT NULL," +
                REGISTRO_DIETA_DIARIA_ID + " INTEGER NOT NULL," +
                TIPO_COMIDA_ID + " INTEGER NOT NULL" +
                ",FOREIGN KEY("+REGISTRO_DIETA_DIARIA_ID+") REFERENCES "+ TablaRegistroDietaDiaria.NOMBRE_TABLA+ "("+TablaRegistroDietaDiaria.ID+"),"
                +"FOREIGN KEY("+ TIPO_COMIDA_ID+") REFERENCES "+TablaTipoComida.NOMBRE_TABLA+"("+TablaTipoComida.ID+"));";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }

    //laves foraneas OK
    public static abstract class TablaDetalleDeportePorFactor implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "detalles_deportes_x_factor";

        //Nombre de campos
        public static final String ID = "id";
        public static final String ENERGIA_GASTADA = "energiaGastada";
        public static final String DURACION = "duracion";
        public static final String FACTOR_ACTIVIDAD_ID = "factorActividadId";
        public static final String DEPORTE_ID = "deporteId";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ENERGIA_GASTADA + " REAL NOT NULL," +
                DURACION + " INT NOT NULL," +
                FACTOR_ACTIVIDAD_ID + " INTEGER NOT NULL," +
                DEPORTE_ID + " INTEGER NOT NULL" +
                ",FOREIGN KEY("+FACTOR_ACTIVIDAD_ID+") REFERENCES "+ TablaFactorActividad.NOMBRE_TABLA+ "("+TablaFactorActividad.ID+"),"
                +"FOREIGN KEY("+ DEPORTE_ID+") REFERENCES "+TablaDeportes.NOMBRE_TABLA+"("+TablaDeportes.ID+"));";


        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }

    //laves foraneas OK eliminacion en cascada
    public static abstract class TablaRegistroActividadFisicaDiaria implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "registros_actividad_fisica_diaria";

        //Nombre de campos
        public static final String ID = "id";
        public static final String FECHA_ACTIVIDAD = "fechaActividad";
        public static final String ESTADO = "estado";
        public static final String DIA_SEMANA_ID = "diaSemanaId";
        public static final String DETALLE_DEPORTE_POR_FACTOR_ID = "detalleDeportePorFactorId";
        public static final String CHEQUEO_SALUD_ID = "chequeoSaludId";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                FECHA_ACTIVIDAD + " DATETIME NOT NULL," +
                ESTADO + " TEXT NOT NULL," +
                DIA_SEMANA_ID + " INTEGER NOT NULL," +
                DETALLE_DEPORTE_POR_FACTOR_ID + " INTEGER NOT NULL," +
                CHEQUEO_SALUD_ID + " INTEGER NOT NULL," +
                "FOREIGN KEY("+DIA_SEMANA_ID+") REFERENCES "+ TablaDiaSemana.NOMBRE_TABLA+ "("+TablaDiaSemana.ID+"),"+
                "FOREIGN KEY("+DETALLE_DEPORTE_POR_FACTOR_ID+") REFERENCES "+ TablaDetalleDeportePorFactor.NOMBRE_TABLA+ "("+TablaDetalleDeportePorFactor.ID+"),"+
                "FOREIGN KEY("+ CHEQUEO_SALUD_ID+") REFERENCES "+TablaChequeoSalud.NOMBRE_TABLA+"("+TablaChequeoSalud.ID_CHEQUEO+") ON DELETE CASCADE);";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }


    public static abstract class TablaDeportes implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "deportes";

        //Nombre de campos
        public static final String ID = "id";
        public static final String DEPORTE = "deporte";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                DEPORTE + " TEXT NOT NULL);";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }

    public static abstract class TablaDiaSemana implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "dias_semana";

        //Nombre de campos
        public static final String ID = "id";
        public static final String NOMBRE_DIA = "nombreDia";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOMBRE_DIA + " TEXT NOT NULL" +
                ");";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }

    public static abstract class TablaFactorActividad implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "factores_actividad";

        //Nombre de campos
        public static final String ID = "id";
        public static final String FACTOR = "factor";
        public static final String DESCRIPCION = "descripcion";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                FACTOR + " TEXT NOT NULL," +
                DESCRIPCION + " TEXT NOT NULL" +
                ");";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }

    public static abstract class TablaRangoAltura implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "rangos_altura";

        //Nombre de campos
        public static final String ID = "id";
        public static final String ALTURA_MINIMA = "alturaMinima";
        public static final String ALTURA_MAXIMA = "alturaMaxima";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ALTURA_MINIMA + " REAL NOT NULL," +
                ALTURA_MAXIMA + " REAL NOT NULL" +
                ");";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }

    public static abstract class TablaRangoPeso implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "rangos_peso";

        //Nombre de campos
        public static final String ID = "id";
        public static final String PESO_MINIMO = "pesoMinimo";
        public static final String PESO_MAXIMO = "pesoMaximo";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                PESO_MINIMO + " REAL NOT NULL," +
                PESO_MAXIMO + " REAL NOT NULL" +
                ");";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }


    public static abstract class TablaTipoComida implements BaseColumns{
        //Nombre de la tabla
        public static final String NOMBRE_TABLA = "tipos_comida";

        //Nombre de campos
        public static final String ID = "id";
        public static final String NOMBRE_TIPO_COMIDA = "nombreTipoComida";
        public static final String CANTIDAD_CALORIFICA = "cantidadCalorifica";
        public static final String TAMANIO_PORCION = "tamanioPorcion";

        public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA +" (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOMBRE_TIPO_COMIDA + " TEXT NOT NULL," +
                CANTIDAD_CALORIFICA + " REAL NOT NULL," +
                TAMANIO_PORCION + " INT NOT NULL" +
                ");";

        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS "+ NOMBRE_TABLA +";";
    }
}
