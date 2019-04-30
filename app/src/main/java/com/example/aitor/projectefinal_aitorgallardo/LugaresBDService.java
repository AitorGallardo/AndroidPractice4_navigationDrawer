package com.example.aitor.projectefinal_aitorgallardo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;

public class LugaresBDService {

    public static final String table_PLACESLIST = "lugares";
    public static final String PLACESLIST_ID = "_id";
    public static final String PLACESLIST_NOMBRE = "nombre";
    public static final String PLACESLIST_DIRECCION = "direccion";
    public static final String PLACESLIST_LONGITUD = "longitud";
    public static final String PLACESLIST_LATITUD = "latitud";
    public static final String PLACESLIST_WEATHER = "weather";
    public static final String PLACESLIST_TIPO = "tipo";
    public static final String PLACESLIST_FOTO = "foto";
    public static final String PLACESLIST_TELEFONO = "telefono";
    public static final String PLACESLIST_URL = "url";
    public static final String PLACESLIST_COMENTARIO = "comentario";

    public static final String PLACESLIST_FECHA = "fecha";
    public static final String PLACESLIST_VALORACION = "valoracion";

    private LugaresBD dbHelper;
    private SQLiteDatabase dbW, dbR;

    // CONSTRUCTOR
    public LugaresBDService(Context ctx) {
        // En el constructor directament obro la comunicació amb la base de dades
        dbHelper = new LugaresBD(ctx);

        // amés també construeixo dos databases un per llegir i l'altre per alterar
        open();
    }

    // DESTRUCTOR
    protected void finalize () {
        // Cerramos los databases
        dbW.close();
        dbR.close();
    }

    private void open() {
        dbW = dbHelper.getWritableDatabase();
        dbR = dbHelper.getReadableDatabase();
    }

    // ******************
    // Funcions que retornen cursors de PLACESLIST
    // ******************
    public Cursor placesList() {
        // Retorem totes les tasques
//        return dbR.query(table_PLACESLIST, new String[]{PLACESLIST_ID,PLACESLIST_NOMBRE,PLACESLIST_DIRECCION,PLACESLIST_LONGITUD,PLACESLIST_LATITUD,PLACESLIST_TIPO,PLACESLIST_FOTO,PLACESLIST_TELEFONO,PLACESLIST_URL,PLACESLIST_COMENTARIO,PLACESLIST_FECHA,PLACESLIST_VALORACION},
//                null, null,
//                null, null, PLACESLIST_ID);
        return dbR.query(table_PLACESLIST, new String[]{PLACESLIST_ID,PLACESLIST_NOMBRE,PLACESLIST_DIRECCION, PLACESLIST_FOTO, PLACESLIST_VALORACION, PLACESLIST_LONGITUD, PLACESLIST_LATITUD},
                null, null,
                null, null, PLACESLIST_ID);
    }

    public Cursor getLatLon() {
        return dbR.query(table_PLACESLIST, new String[]{PLACESLIST_ID, PLACESLIST_LATITUD, PLACESLIST_LONGITUD},
                null, null,
                null, null, PLACESLIST_ID);
    }

    // public Cursor placesListPending() {
    //     // Retornem les tasques que el camp DONE = 0
    //     return dbR.query(table_PLACESLIST, new String[]{PLACESLIST_ID,PLACESLIST_TITLE,PLACESLIST_DESCRIPCION,PLACESLIST_LEVEL,PLACESLIST_DONE},
    //             PLACESLIST_DONE + "=?", new String[]{String.valueOf(0)},
    //             null, null, PLACESLIST_ID);
    // }

    // public Cursor placesListCompleted() {
    //     // Retornem les tasques que el camp DONE = 1
    //     return dbR.query(table_PLACESLIST, new String[]{PLACESLIST_ID,PLACESLIST_TITLE,PLACESLIST_DESCRIPCION,PLACESLIST_LEVEL,PLACESLIST_DONE},
    //             PLACESLIST_DONE + "=?", new String[]{String.valueOf(1)},
    //             null, null, PLACESLIST_ID);
    // }

     public Cursor place(long id) {
         // Retorna un cursor només amb el id indicat
         // Retornem les tasques que el camp DONE = 1
         return dbR.query(table_PLACESLIST, new String[]{PLACESLIST_ID,PLACESLIST_NOMBRE,PLACESLIST_DIRECCION,PLACESLIST_URL,PLACESLIST_TELEFONO, PLACESLIST_TIPO, PLACESLIST_LATITUD, PLACESLIST_LONGITUD, PLACESLIST_VALORACION},
                 PLACESLIST_ID+ "=?", new String[]{String.valueOf(id)},
                 null, null, null);

     }

    // ******************
    // Funciones de manipualación de datos
    // ******************


    public void updateWeather(long id, String weather) {
        // Modifiquem els valors de las place amb clau primària "id"
        ContentValues values = new ContentValues();
        values.put(PLACESLIST_WEATHER, weather);
        dbW.update(table_PLACESLIST,values, PLACESLIST_ID + " = ?", new String[] { String.valueOf(id) });
    }


    public long placeAdd(String name, String description, String web,
                         String telefon, int tipus, String lat, String longit, float valoracio) {
        // Creem una nova place i retornem el id crear per si el necessiten
        ContentValues values = new ContentValues();
        values.put(PLACESLIST_NOMBRE, name);
        values.put(PLACESLIST_DIRECCION, description);
        values.put(PLACESLIST_URL,web);
        values.put(PLACESLIST_TELEFONO,telefon);
        values.put(PLACESLIST_TIPO,tipus);
        values.put(PLACESLIST_LATITUD,lat);
        values.put(PLACESLIST_LONGITUD,longit);
        values.put(PLACESLIST_VALORACION,valoracio);


        switch(tipus){
            case 0:
                values.put(PLACESLIST_FOTO,"@drawable/shopping");
                break;
            case 1:
                values.put(PLACESLIST_FOTO,"@drawable/hotel");
                break;
            case 2:
                values.put(PLACESLIST_FOTO,"@drawable/drink_beer_jar");
                break;
            case 3:
                values.put(PLACESLIST_FOTO,"@drawable/cocktail_glass");
                break;
            case 4:
                values.put(PLACESLIST_FOTO,"@drawable/theatre_mask");
                break;
            case 5:
                values.put(PLACESLIST_FOTO,"@drawable/restaurant");
                break;
            case 6:
                values.put(PLACESLIST_FOTO,"@drawable/other");
                break;
            case 7:
                values.put(PLACESLIST_FOTO,"@drawable/book");
                break;
            case 8:
                values.put(PLACESLIST_FOTO,"@drawable/sport");
                break;
            case 9:
                values.put(PLACESLIST_FOTO,"@drawable/nature");
                break;
            case 10:
                values.put(PLACESLIST_FOTO,"@drawable/gas_station");
                break;

        }
         
         return dbW.insert(table_PLACESLIST,null,values);
     }

     public void placeUpdate(long id, String name, String description, String web,
                             String telefon, int tipus, String lat, String longit, float valoracio) {
         // Modifiquem els valors de las place amb clau primària "id"
         ContentValues values = new ContentValues();
         values.put(PLACESLIST_NOMBRE, name);
         values.put(PLACESLIST_DIRECCION, description);
         values.put(PLACESLIST_URL,web);
         values.put(PLACESLIST_TELEFONO,telefon);
         values.put(PLACESLIST_TIPO,tipus);
         values.put(PLACESLIST_LATITUD,lat);
         values.put(PLACESLIST_LONGITUD,longit);
         values.put(PLACESLIST_VALORACION,valoracio);

        switch(tipus){
            case 0:
                values.put(PLACESLIST_FOTO,"@drawable/shopping");
                break;
            case 1:
                values.put(PLACESLIST_FOTO,"@drawable/hotel");
                break;
            case 2:
                values.put(PLACESLIST_FOTO,"@drawable/drink_beer_jar");
                break;
            case 3:
                values.put(PLACESLIST_FOTO,"@drawable/cocktail_glass");
                break;
            case 4:
                values.put(PLACESLIST_FOTO,"@drawable/theatre_mask");
                break;
            case 5:
                values.put(PLACESLIST_FOTO,"@drawable/restaurant");
                break;
            case 6:
                values.put(PLACESLIST_FOTO,"@drawable/other");
                break;
            case 7:
                values.put(PLACESLIST_FOTO,"@drawable/book");
                break;
            case 8:
                values.put(PLACESLIST_FOTO,"@drawable/sport");
                break;
            case 9:
                values.put(PLACESLIST_FOTO,"@drawable/nature");
                break;
            case 10:
                values.put(PLACESLIST_FOTO,"@drawable/gas_station");
                break;

        }

         dbW.update(table_PLACESLIST,values, PLACESLIST_ID + " = ?", new String[] { String.valueOf(id) });
     }

     public void placeDelete(long id) {
         // Eliminem la place amb clau primària "id"
         dbW.delete(table_PLACESLIST,PLACESLIST_ID + " = ?", new String[] { String.valueOf(id) });
     }

}
