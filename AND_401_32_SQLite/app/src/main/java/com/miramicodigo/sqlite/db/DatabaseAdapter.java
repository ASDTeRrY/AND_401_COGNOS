package com.miramicodigo.sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter{

    private PersonasDatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public DatabaseAdapter(Context context) {
        databaseHelper = new PersonasDatabaseHelper(context);
    }

    public void abrir() {
        db = databaseHelper.getWritableDatabase();
    }

    public void cerrar() {
        databaseHelper.close();
    }

    public long adicionarPersona(String nombre, long telefono, String correo, String genero) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("telefono", telefono);
        contentValues.put("correo", correo);
        contentValues.put("genero", genero);
        return db.insert("persona", null, contentValues);
    }




    private static class PersonasDatabaseHelper extends SQLiteOpenHelper {

        public PersonasDatabaseHelper(Context context) {
            super(context, "dbpersonas.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE persona (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, " +
                    "telefono INTEGER, correo TEXT, genero TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS persona");
            onCreate(db);
        }
    }
}
