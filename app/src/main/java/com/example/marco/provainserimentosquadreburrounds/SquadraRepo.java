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

public class SquadraRepo {
    private DBHelper dbHelper;

    public SquadraRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Squadra squadra) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Squadra.KEY_name_SQUADRA, squadra.name_SQUADRA);

        // Inserting Row
        long squadra_Id = db.insert(Squadra.TABLE_SQUADRA, null, values);
        db.close(); // Closing database connection
        return (int) squadra_Id;
    }

    public void delete(int squadra_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Squadra.TABLE_SQUADRA, Squadra.KEY_ID_SQUADRA + "= ?", new String[]{String.valueOf(squadra_Id)});
        db.close(); // Closing database connection
    }

    public void update(Squadra squadra) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Squadra.KEY_name_SQUADRA, squadra.name_SQUADRA);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Squadra.TABLE_SQUADRA, values, Squadra.KEY_ID_SQUADRA + "= ?", new String[]{String.valueOf(squadra.squadra_ID)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getSquadraList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Squadra.KEY_ID_SQUADRA + "," +
                Squadra.KEY_name_SQUADRA + " " +
                " FROM " + Squadra.TABLE_SQUADRA;

        ArrayList<HashMap<String, String>> squadraList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> squadra = new HashMap<String, String>();
                squadra.put("id", cursor.getString(cursor.getColumnIndex(Squadra.KEY_ID_SQUADRA)));
                squadra.put("name", cursor.getString(cursor.getColumnIndex(Squadra.KEY_name_SQUADRA)));
                squadraList.add(squadra);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return squadraList;

    }

    public Squadra getSquadraById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " + Squadra.KEY_ID_SQUADRA + "," + Squadra.KEY_name_SQUADRA + " " + " FROM " + Squadra.TABLE_SQUADRA + " WHERE " + Squadra.KEY_ID_SQUADRA + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        Squadra squadra = new Squadra();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {
                squadra.squadra_ID = cursor.getInt(cursor.getColumnIndex(Squadra.KEY_ID_SQUADRA));
                squadra.name_SQUADRA = cursor.getString(cursor.getColumnIndex(Squadra.KEY_name_SQUADRA));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return squadra;
    }
}
