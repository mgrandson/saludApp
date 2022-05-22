package control;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entidades.Deporte;
import entidades.DetalleDietaPorTiempo;
import entidades.DietaAlimenticia;
import entidades.RegistroDietaDiaria;

public class ControladorDietaAlimenticia {
    private SaludSqliteHelper saludSqliteHelper;
    private SQLiteDatabase instanciaBD;
    private Context context;

    private static final String[] camposDietaAlimenticia = {
            SaludDB.TablaDietaAlimenticia.ID,
            SaludDB.TablaDietaAlimenticia.DURACION_DIETA,
            SaludDB.TablaDietaAlimenticia.TOTAL_CALORIAS,
            SaludDB.TablaDietaAlimenticia.CHEQUEO_SALUD_ID
    };

    public ControladorDietaAlimenticia(Context context){
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

    //CRUD

    public long crear(DietaAlimenticia dietaAlimenticia){
        Long resultado = abrirDB().insert(
                SaludDB.TablaDietaAlimenticia.NOMBRE_TABLA,
                null, dietaAlimenticia.toContentvalues()
        );
        cerrarDB();
        return resultado;
    }

    //INSERTAR DIETA DIARIA
    public void asignarDietaAlimenticia(DietaAlimenticia dietaAlimenticia){

        //DEFINIENDO CONTROLADORES NECESARIOS
        ControladorDietaAlimenticia cDietaAlimenticia;
        ControladorRegistroDietaDiaria cRegistroDietaDiaria;
        ControladorDetalleDietaPorTiempo cDetalleDietaPorTiempo;

        String [] tiempoComida = {"Desayuno", "Almuerzo", "Cena"};

        //OBTENIENDO EL DIA DE LA SEMANA
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        //AGREGANDO DIETA ALIMENTICIA Y OBTENIENDO ID

        cDietaAlimenticia = new ControladorDietaAlimenticia(this.context);
        int dietaAlimenticiaId = (int) cDietaAlimenticia.crear(dietaAlimenticia);

        System.out.println("DIETA ALIMENTICIA => ID: "+dietaAlimenticiaId);
        System.out.println(dietaAlimenticia.toString());

        System.out.println("FECHA INICIAL: "+df.format(c.getTime()));

        //AGREGAR REGISTRO DIETA DIARIA CON BASE EN LA DURACION DE LA DIETA
        for (int diaActual = 1; diaActual <= dietaAlimenticia.getDuracionDieta(); diaActual++){

            c.add(Calendar.DAY_OF_MONTH, 1);
            int diaSemanaId = c.get(Calendar.DAY_OF_WEEK);
            RegistroDietaDiaria dietaDiaria = new RegistroDietaDiaria(dietaAlimenticiaId, diaSemanaId);


            //AGREGANDO DIETA DIARIA Y OBTENIENDO ID

            cRegistroDietaDiaria = new ControladorRegistroDietaDiaria(this.context);
            int registroDietaDiariaId = (int) cRegistroDietaDiaria.crear(dietaDiaria);
            System.out.println("FOR: diaActual => "+diaActual);
            System.out.println("FECHA: "+df.format(c.getTime()));
            System.out.println("REGISTRO DIETA DIARIA => ID: "+registroDietaDiariaId);
            System.out.println(dietaDiaria.toString());


            int min = 1;
            int max = (int) DatabaseUtils.queryNumEntries(abrirDB(), SaludDB.TablaTipoComida.NOMBRE_TABLA);


            for (int tiempo = 0; tiempo < 3; tiempo++) {
                System.out.println("------------------------------");
                System.out.println("FOR: tiempoComida => "+tiempo);
                for (int comida = 0; comida < 3; comida++) {


                    int tipoComidaId = (int)Math.floor(Math.random()*(max-min+1)+min);


                    //AGREGAR DETALLE DIETA POR TIEMPO
                    DetalleDietaPorTiempo dietaPorTiempo = new DetalleDietaPorTiempo(tiempoComida[tiempo], registroDietaDiariaId, tipoComidaId);
                    cDetalleDietaPorTiempo = new ControladorDetalleDietaPorTiempo(this.context);
                    long detallePorTiempoId = cDetalleDietaPorTiempo.crear(dietaPorTiempo);

                    System.out.println("FOR: comida => "+comida);
                    System.out.println("DETALLE DIETA POR TIEMPO => ID: "+detallePorTiempoId);
                    System.out.println(dietaPorTiempo.toString());



                }
                System.out.println("------------------------------");
            }
            System.out.println("\n==========================================================================");
        }
        cerrarDB();
    }

    public List<DietaAlimenticia> obtenerDietaAlimenticia(int chequeo){
        List<DietaAlimenticia> dietaAlimenticiaList = new ArrayList<>();
        abrirDB();
        Cursor cursor = instanciaBD.query(
                SaludDB.TablaDietaAlimenticia.NOMBRE_TABLA,
                null,
                "chequeoSaludId = " + chequeo,
                null,
                null,
                null,
                SaludDB.TablaDietaAlimenticia.ID
        );
        while (cursor.moveToNext())
        {
            DietaAlimenticia dietaAlimenticia = new DietaAlimenticia(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getDouble(2),
                    cursor.getInt(3)
            );
            dietaAlimenticiaList.add(dietaAlimenticia);
        }
        cursor.close();
        cerrarDB();
        return dietaAlimenticiaList;
    }

    public DietaAlimenticia obtenerDietaAlimenticiaId(int idDieta){
        //List<TipoComida> listaTipoComida = new ArrayList<>();
        abrirDB();
        Cursor cursor = instanciaBD.query(
                SaludDB.TablaDietaAlimenticia.NOMBRE_TABLA,
                camposDietaAlimenticia,
                "chequeoSaludId = " + idDieta,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst())
        {
            DietaAlimenticia dietaAlimenticia = new DietaAlimenticia(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getDouble(2),
                    cursor.getInt(3)
            );
            cursor.close();
            cerrarDB();
            return dietaAlimenticia;
        }
        else
        {
            return  null;
        }
    }
}
