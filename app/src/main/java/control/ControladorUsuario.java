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

    private static final String[] camposUsuario = new String [] {SaludDB.UsuarioDB.NOMBRE_USUARIO,SaludDB.UsuarioDB.NOMBRE,SaludDB.UsuarioDB.APELLIDO,SaludDB.UsuarioDB.FECHA_NACIMIENTO,SaludDB.UsuarioDB.GENERO};

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
            usuario.setNombre(cursor.getString(1));
            usuario.setApellido(cursor.getString(2));
            usuario.setFechaNacimiento(cursor.getString(3));
            usuario.setGenero(cursor.getString(4));
            cerrarDB();
            return usuario;

        }
        cerrarDB();
        return null;

    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
