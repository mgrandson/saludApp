package control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ControladorRangoImc {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposRangoImc = {
            SaludDB.TablaRangoImc.ID,
            SaludDB.TablaRangoImc.SEXO_IMC,
            SaludDB.TablaRangoImc.RANGO_ALTURA_ID,
            SaludDB.TablaRangoImc.RANGO_PESO_ID
    };

    public ControladorRangoImc(Context context){
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

    //OBTENER RANGOS IMC
    public int obtenerRangosIMCId(double imcMinimo, double imcMaximo){
        System.out.println("IMC MINIMO: "+imcMinimo+ "IMC MAXIMO: "+imcMaximo);
        int rangoImcId = 1;
        String consultaSQL = "select" +
                " ri.id," +
                " ra.alturaMinima," +
                " ra.alturaMaxima," +
                " rp.pesoMinimo," +
                " rp.pesoMaximo," +
                " round((rp.pesoMinimo/(ra.alturaMinima*ra.alturaMinima)),2) imcMinimo," +
                " round((rp.pesoMaximo/(ra.alturaMaxima*ra.alturaMaxima)),2) imcMaximo" +
                " from rangos_imc ri" +
                " join rangos_altura ra on ri.rangoAlturaId = ra.id" +
                " join rangos_peso rp on ri.rangoPesoId = rp.id" +
                " where imcMinimo >= "+imcMinimo+" and imcMaximo < "+imcMaximo+" limit 1;";
        Cursor cursor = abrirDB().rawQuery(consultaSQL, null);
        System.out.println("Rango a comparar icmMinimo: "+imcMinimo+"  imcMaximo: "+imcMaximo);
        if(cursor.moveToFirst()){
            rangoImcId = cursor.getInt(0);
            System.out.println("IMC Minimo: "+ cursor.getDouble(5)+ " IMC Maximo: "+cursor.getDouble(6));
        }
        cursor.close();
        cerrarDB();
        System.out.println("obtenerRangosIMCId: "+rangoImcId);
        System.out.println("---------------------------------------------------");
        return  rangoImcId;
    }
}
