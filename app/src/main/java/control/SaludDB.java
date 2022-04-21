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
        public static final String NOMBRE_USUARIO = "nombreUsuario";
        public static final String CONTRASENIA = "contrasenia";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDO = "apellido";
        public static final String FECHA_NACIMIENTO= "fechaNacimiento";
        public static final String GENERO = "genero";

        //sentencia sql para crear la tabla usuario
        public static final String CREAR_TABLA = "CREATE TABLE usuario(nombreUsuario TEXT PRIMARY KEY ,contrasenia TEXT NOT NULL,nombre TEXT NOT NULL,apellido TEXT NOT NULL,fechaNacimiento TEXT NOT NULL,genero TEXT NOT NULL)";
        public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS usuario";


    }






}
