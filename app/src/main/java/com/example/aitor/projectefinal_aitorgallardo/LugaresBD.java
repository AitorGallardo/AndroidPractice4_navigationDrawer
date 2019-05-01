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
                "'C/ Paranimf, 1 46730 Gandia (SPAIN)', '38.9959757', '-0.1680304', "+
                TipoLugar.EDUCACION.ordinal() + ", '@drawable/book', 962849300, "+
                "'http://www.epsg.upv.es', "+
                "'Uno de los mejores lugares para formarse.', "+
                System.currentTimeMillis() +", 3.0, '')");
        bd.execSQL("INSERT INTO lugares VALUES (null, 'Al de siempre', "+
                "'P.Industrial Junto Molí Nou - 46722, Benifla (Valencia)', "+
                "'38.925857','-0.190642', " + TipoLugar.BAR.ordinal() + ", '@drawable/restaurant', "+
                "636472405, '', "+"'No te pierdas el arroz en calabaza.', " +
                System.currentTimeMillis() +", 3.0, '')");
        bd.execSQL("INSERT INTO lugares VALUES (null, 'androidcurso.com', "+
                "'ciberespacio', '51.1828491', '4.6726709',"+TipoLugar.EDUCACION.ordinal()+", '@drawable/book', "+
                "962849300, 'http://androidcurso.com', "+
                "'Amplia tus conocimientos sobre Android.', "+
                System.currentTimeMillis() +", 5.0, '')");
        bd.execSQL("INSERT INTO lugares VALUES (null,'Barranco del Infierno',"+
                "'Vía Verde del río Serpis. Villalonga (Valencia)', '38.867180 ', "+
                "'-0.295058', "+TipoLugar.NATURALEZA.ordinal() + ", '@drawable/restaurant', 911234541, "+
                "'http://sosegaos.blogspot.com.es/2009/02/lorcha-villalonga-via-verde-del-"+
                "rio.html', 'Espectacular ruta para bici o andar', "+
                System.currentTimeMillis() +", 4.0, '')");
        bd.execSQL("INSERT INTO lugares VALUES (null, 'La Vital', "+
                "'Avda. La Vital,0 46701 Gandia (Valencia)','38.9705949','-0.1720092',"+
                TipoLugar.COMPRAS.ordinal() + ", '@drawable/shopping', 962881070, "+
                "'http://www.lavital.es', 'El típico centro comercial', "+
                System.currentTimeMillis() +", 2.0, '')");
        bd.execSQL("INSERT INTO lugares VALUES (null, 'Gelateria Núria', "+
                "'Carrer Marià Cubí, 70, 08380 Malgrat de Mar, Barcelona','41.6421664','2.7389056',"+
                TipoLugar.OTROS.ordinal() + ", '@drawable/other', 937654589, "+
                "'', 'El típico centro comercial', "+
                System.currentTimeMillis() +", 2.0, '')");

        bd.execSQL("INSERT INTO lugares VALUES (null, 'Mandarin Oriental', "+
                "'Passeig de Gràcia, 38, 40, 08007 Barcelona','41.3911782','2.1644851',"+
                TipoLugar.HOTEL.ordinal() + ", '@drawable/hotel', 931518888, "+
                "'', 'Action-packed shopping & cultural quarter with cool bars', "+
                System.currentTimeMillis() +", 2.0, '')");
        bd.execSQL("INSERT INTO lugares VALUES (null, 'Harrods', "+
                "'87-135 Brompton Rd, Knightsbridge, London SW1X 7XL, UK','51.4994055','-0.1654231',"+
                TipoLugar.COMPRAS.ordinal() + ", '@drawable/shopping', 2077301234, "+
                "'www.harrods.com', 'Very large store to suit all. Great collection of designer labels. Lots of staff, very kind, helpful and attentive. ', "+
                System.currentTimeMillis() +", 2.0, '')");
        bd.execSQL("INSERT INTO lugares VALUES (null, 'Chichén Itzá', "+
                "'Yucatan, Mexico','20.6804082','-88.5711331',"+
                TipoLugar.NATURALEZA.ordinal() + ", '@drawable/nature', 9858510137, "+
                "'www.inah.gob.mx', 'Enjoy the time.  See ancient history and the past. The buildings are in great condition and visually stunning.', "+
                System.currentTimeMillis() +", 2.0, '')");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
