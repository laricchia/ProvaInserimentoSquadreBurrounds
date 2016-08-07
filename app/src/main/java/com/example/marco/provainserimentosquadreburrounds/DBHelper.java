package com.example.marco.provainserimentosquadreburrounds;

/**
 * Created by marco on 25/06/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 5;

    // Database Name
    private static final String DATABASE_NAME = "bur.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_SQUADRA = "CREATE TABLE " + Squadra.TABLE_SQUADRA  + "("
                + Squadra.KEY_ID_SQUADRA  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Squadra.KEY_name_SQUADRA + " TEXT )";

        db.execSQL(CREATE_TABLE_SQUADRA);




        String CREATE_TABLE_GIOCATORE = "CREATE TABLE " + Giocatore.TABLE_GIOCATORE  + "("
                + Giocatore.KEY_ID_GIOCATORE  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Giocatore.KEY_name_GIOCATORE + " TEXT )";

        db.execSQL(CREATE_TABLE_GIOCATORE);






    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Squadra.TABLE_SQUADRA);

      db.execSQL("DROP TABLE IF EXISTS " + Giocatore.TABLE_GIOCATORE);

        // Create tables again
        onCreate(db);

    }

}