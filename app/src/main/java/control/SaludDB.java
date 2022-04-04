package control;

import android.provider.BaseColumns;

/**
 * 1.Contiene los nombres de las tablas y campos de la base de datos
 * 2. Contiene las sentencias para crear la base de datos
 */
public  class SaludDB {

    public static final String NOMBRE_BD= "salud.db";
    public static final int VERSION_BD= 1;


    public static abstract class UsuarioDB implements BaseColumns{
        //nombre de la tabla usuarios
        public static final String NOMBRE_TABLA = "usuario";
        //nombre de los campos de la tabla usuario
        public static final String NOMBRE = "usuario";
        public static final String APELLIDO = "usuario";
        public static final String FECHA_NACIMIENTO= "fechaNacimiento";
        public static final String GENERO = "genero";

        //sentencia sql para crear la tabla usuario
        public static final String CREAR_TABLA = "CREATE TABLE usuario(id INTEGER NOT NULL,nombre TEXT NOT NULL,PRIMARY KEY(id AUTOINCREMENT))";



    }






}
