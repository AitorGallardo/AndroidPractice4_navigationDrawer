package com.example.aitor.projectefinal_aitorgallardo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LugaresBD extends SQLiteOpenHelper {
    Context contexto;

    public LugaresBD(Context contexto) {
        super(contexto, "lugares", null, 1);
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
 bd.execSQL("CREATE TABLE lugares ("+
                 "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                 "nombre TEXT, " +
                 "direccion TEXT, " +
                 "latitud TEXT, " +
                 "longitud TEXT, " +
                 "tipo INTEGER, " +
                 "foto TEXT, " +
                 "telefono INTEGER, " +
                 "url TEXT, " +
                 "comentario TEXT, " +
                 "fecha BIGINT, " +
                 "valoracion REAL,"+
                 "weather TEXT)");
         bd.execSQL("INSERT INTO lugares VALUES (null, "+
                 "'Escuela Politécnica Superior de Gandía', "+
                 "'C/ Paranimf, 1 46730 Gandia (SPAIN)', '38.995656', '-0.166093', "+
                 TipoLugar.EDUCACION.ordinal() + ", '@drawable/restaurant', 962849300, "+
                 "'http://www.epsg.upv.es', "+
                 "'Uno de los mejores lugares para formarse.', "+
                 System.currentTimeMillis() +", 3.0)");
         bd.execSQL("INSERT INTO lugares VALUES (null, 'Al de siempre', "+
                 "'P.Industrial Junto Molí Nou - 46722, Benifla (Valencia)', "+
                 "38.925857,-0.190642, " + TipoLugar.BAR.ordinal() + ", '@drawable/restaurant', "+
                 "636472405, '', "+"'No te pierdas el arroz en calabaza.', " +
                 System.currentTimeMillis() +", 3.0)");
         bd.execSQL("INSERT INTO lugares VALUES (null, 'androidcurso.com', "+
                 "'ciberespacio', 0.0, 0.0,"+TipoLugar.EDUCACION.ordinal()+", '@drawable/restaurant', "+
                 "962849300, 'http://androidcurso.com', "+
                 "'Amplia tus conocimientos sobre Android.', "+
                 System.currentTimeMillis() +", 5.0)");
         bd.execSQL("INSERT INTO lugares VALUES (null,'Barranco del Infierno',"+
                "'Vía Verde del río Serpis. Villalonga (Valencia)', -0.295058, "+
                 "38.867180, "+TipoLugar.NATURALEZA.ordinal() + ", '@drawable/restaurant', 0, "+
                 "'http://sosegaos.blogspot.com.es/2009/02/lorcha-villalonga-via-verde-del-"+
                 "rio.html', 'Espectacular ruta para bici o andar', "+
                 System.currentTimeMillis() +", 4.0)");
         bd.execSQL("INSERT INTO lugares VALUES (null, 'La Vital', "+
                 "'Avda. La Vital,0 46701 Gandia (Valencia)',-0.1720092,38.9705949,"+
                TipoLugar.COMPRAS.ordinal() + ", '@drawable/restaurant', 962881070, "+
                 "'http://www.lavital.es', 'El típico centro comercial', "+
                 System.currentTimeMillis() +", 2.0)");
         }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
