package entidades;

import android.content.ContentValues;

import control.SaludDB;

public class Usuario {

    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String genero;


    public Usuario() {

    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    /**
     * Almacena los datos del usuario en formato clave,valor para ser ingresados a la BD
     * @return usuarioValues
     */
    public ContentValues toContentvalues(){
        ContentValues usuarioValues = new ContentValues();
        usuarioValues.put(SaludDB.UsuarioDB.NOMBRE_USUARIO, nombreUsuario);
        usuarioValues.put(SaludDB.UsuarioDB.NOMBRE,nombre);
        usuarioValues.put(SaludDB.UsuarioDB.APELLIDO,apellido);
        usuarioValues.put(SaludDB.UsuarioDB.FECHA_NACIMIENTO,fechaNacimiento);
        usuarioValues.put(SaludDB.UsuarioDB.GENERO,genero);

        return usuarioValues;
    }
}
