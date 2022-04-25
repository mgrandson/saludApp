package control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import entidades.Usuario;

public class ControladorUsuario {

    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase saludSqlite;
    private Context context;
    private String informacion;

    private static final String[] camposUsuario = new String [] {SaludDB.UsuarioDB.NOMBRE_USUARIO,SaludDB.UsuarioDB.CONTRASENIA,SaludDB.UsuarioDB.NOMBRE,SaludDB.UsuarioDB.APELLIDO,SaludDB.UsuarioDB.FECHA_NACIMIENTO,SaludDB.UsuarioDB.GENERO};

    public ControladorUsuario(Context context) {
        this.context = context;
    }

    /**
     *
     * @return abre la conexion a la BD
     */
    public SQLiteDatabase abrirDB(){
        saludSqliteHelper = new SaludSqliteHelper(context, SaludDB.NOMBRE_BD,null,SaludDB.VERSION_BD);
        saludSqlite = saludSqliteHelper.getWritableDatabase();
        return saludSqlite;
    }

    public void cerrarDB(){
        saludSqlite.close();
    }

    /**
     *
     * @param usuario
     * @return 0 o - 1 error al crear el usuario, valor diferente indica que se registro el usuario;
     */
    public long crearUsuario(Usuario usuario){
        Long resultado = abrirDB().insert(SaludDB.UsuarioDB.NOMBRE_TABLA,null,usuario.toContentvalues());
        cerrarDB();
        return resultado;

    }



    public Usuario buscarUsuario(String nombreUsuario){

        String[] idUsuario = {nombreUsuario};
        Cursor cursor = abrirDB().query(SaludDB.UsuarioDB.NOMBRE_TABLA,camposUsuario,"nombreUsuario = ?",idUsuario,null,null,null);
        if(cursor.moveToFirst()){
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(cursor.getString(0));
            usuario.setContrasenia(cursor.getString(1));
            usuario.setNombre(cursor.getString(2));
            usuario.setApellido(cursor.getString(3));
            usuario.setFechaNacimiento(cursor.getString(4));
            usuario.setGenero(cursor.getString(5));
            cerrarDB();
            return usuario;

        }
        cerrarDB();
        return null;

    }


    /**
     * valida que el usuario y contraseña coincidan para hacer login
     * @return
     */
    public boolean validarLogin(String nombreUsuario,String contrasenia){
        informacion = "";
        Usuario usuario = buscarUsuario(nombreUsuario);
        if(usuario == null){
            informacion ="Usuario no existe.";
            return false;
        }
        else if(!usuario.getContrasenia().equals(contrasenia)) {
            informacion = "Contraseña incorrecta";
        }
        else{
            return true;
        }

        return false;

    }


    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
