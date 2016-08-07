package com.example.marco.provainserimentosquadreburrounds;

/**
 * Created by marco on 25/06/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class GiocatoreRepo {
    private DBHelper dbHelper;

    public GiocatoreRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Giocatore giocatore) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Giocatore.KEY_name_GIOCATORE, giocatore.name_GIOCATORE);

        // Inserting Row
        long giocatore_Id = db.insert(Giocatore.TABLE_GIOCATORE, null, values);
        db.close(); // Closing database connection
        return (int) giocatore_Id;
    }

    public void delete(int giocatore_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Giocatore.TABLE_GIOCATORE, Giocatore.KEY_ID_GIOCATORE + "= ?", new String[]{String.valueOf(giocatore_Id)});
        db.close(); // Closing database connection
    }

    public void update(Giocatore giocatore) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Giocatore.KEY_name_GIOCATORE, giocatore.name_GIOCATORE);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Giocatore.TABLE_GIOCATORE, values, Giocatore.KEY_ID_GIOCATORE + "= ?", new String[]{String.valueOf(giocatore.giocatore_ID)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getGiocatoreList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Giocatore.KEY_ID_GIOCATORE + "," +
                Giocatore.KEY_name_GIOCATORE + " " +
                " FROM " + Giocatore.TABLE_GIOCATORE;

        ArrayList<HashMap<String, String>> giocatoreList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> giocatore = new HashMap<String, String>();
                giocatore.put("id", cursor.getString(cursor.getColumnIndex(Giocatore.KEY_ID_GIOCATORE)));
                giocatore.put("name", cursor.getString(cursor.getColumnIndex(Giocatore.KEY_name_GIOCATORE)));
                giocatoreList.add(giocatore);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return giocatoreList;

    }

    public Giocatore getGiocatoreById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Giocatore.KEY_ID_GIOCATORE + "," +
               Giocatore.KEY_name_GIOCATORE + " " +
                " FROM " + Giocatore.TABLE_GIOCATORE
                + " WHERE " +
                Giocatore.KEY_ID_GIOCATORE + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        Giocatore giocatore = new Giocatore();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {
                giocatore.giocatore_ID = cursor.getInt(cursor.getColumnIndex(Giocatore.KEY_ID_GIOCATORE));
                giocatore.name_GIOCATORE = cursor.getString(cursor.getColumnIndex(Giocatore.KEY_name_GIOCATORE));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return giocatore;
    }
}

