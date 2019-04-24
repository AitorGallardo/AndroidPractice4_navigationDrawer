package com.example.aitor.projectefinal_aitorgallardo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LugaresBDService {

    public static final String table_PLACESLIST = "lugares";
    public static final String PLACESLIST_ID = "_id";
    public static final String PLACESLIST_NOMBRE = "nombre";
    public static final String PLACESLIST_DIRECCION = "direccion";
    public static final String PLACESLIST_LONGITUD = "longitud";
    public static final String PLACESLIST_LATITUD = "latitud";

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
        return dbR.query(table_PLACESLIST, new String[]{PLACESLIST_ID,PLACESLIST_NOMBRE,PLACESLIST_DIRECCION},
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

     public Cursor task(long id) {
         // Retorna un cursor només amb el id indicat
         // Retornem les tasques que el camp DONE = 1
         return dbR.query(table_PLACESLIST, new String[]{PLACESLIST_ID,PLACESLIST_NOMBRE,PLACESLIST_DIRECCION,PLACESLIST_URL,PLACESLIST_TELEFONO, PLACESLIST_TIPO, PLACESLIST_LATITUD, PLACESLIST_LONGITUD, PLACESLIST_VALORACION},
                 PLACESLIST_ID+ "=?", new String[]{String.valueOf(id)},
                 null, null, null);

     }

    // ******************
    // Funciones de manipualación de datos
    // ******************

    public long taskAdd(String name, String description, String web,
                        String telefon,String tipus, String lat, String longit, String valoracio) {
        // Creem una nova tasca i retornem el id crear per si el necessiten
        ContentValues values = new ContentValues();
        values.put(PLACESLIST_NOMBRE, name);
        values.put(PLACESLIST_DIRECCION, description);
        values.put(PLACESLIST_URL,web);
        values.put(PLACESLIST_TELEFONO,telefon);
        values.put(PLACESLIST_TIPO,tipus);
        values.put(PLACESLIST_LATITUD,lat);
        values.put(PLACESLIST_LONGITUD,longit);
        values.put(PLACESLIST_VALORACION,valoracio);
         
         return dbW.insert(table_PLACESLIST,null,values);
     }

    // public void taskUpdate(long id, String title, String description, int level) {
    //     // Modifiquem els valors de las tasca amb clau primària "id"
    //     ContentValues values = new ContentValues();
    //     values.put(PLACESLIST_TITLE, title);
    //     values.put(PLACESLIST_DESCRIPCION, description);
    //     values.put(PLACESLIST_LEVEL,level);
    //     values.put(PLACESLIST_DONE,0);  // Forcem 0 pq si s'està creant la tasca no pot estar finalitzada

    //     dbW.update(table_PLACESLIST,values, PLACESLIST_ID + " = ?", new String[] { String.valueOf(id) });
    // }

    // public void taskDelete(long id) {
    //     // Eliminem la task amb clau primària "id"
    //     dbW.delete(table_PLACESLIST,PLACESLIST_ID + " = ?", new String[] { String.valueOf(id) });
    // }

    // public void taskPending(long id) {
    //     // Modifiquem al estat de pendent la task indicada
    //     ContentValues values = new ContentValues();
    //     values.put(PLACESLIST_DONE,0);

    //     dbW.update(table_PLACESLIST,values, PLACESLIST_ID + " = ?", new String[] { String.valueOf(id) });
    // }

    // public void taskCompleted(long id) {
    //     // Modifiquem al estat de pendent la task indicada
    //     ContentValues values = new ContentValues();
    //     values.put(PLACESLIST_DONE,1);

    //     dbW.update(table_PLACESLIST,values, PLACESLIST_ID + " = ?", new String[] { String.valueOf(id) });
    // }
}
