package com.example.marco.provainserimentosquadreburrounds;

/**
 * Created by marco on 25/06/2016.
 */
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GiocatoreDetail extends ActionBarActivity implements android.view.View.OnClickListener {

    Button btnSave1, btnDelete1;
    Button btnClose1;
    EditText editTextName1;
    private int _Giocatore_Id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giocatore_detail);

        btnSave1 = (Button) findViewById(R.id.btnSave1);
        btnDelete1 = (Button) findViewById(R.id.btnDelete1);
        btnClose1 = (Button) findViewById(R.id.btnClose1);

        editTextName1 = (EditText) findViewById(R.id.editTextName1);


        btnSave1.setOnClickListener(this);
        btnDelete1.setOnClickListener(this);
        btnClose1.setOnClickListener(this);


        _Giocatore_Id = 0;
        Intent intent = getIntent();
        _Giocatore_Id = intent.getIntExtra("giocatore_Id", 0);
        GiocatoreRepo repo1 = new GiocatoreRepo(this);
        Giocatore giocatore = new Giocatore();
        giocatore = repo1.getGiocatoreById(_Giocatore_Id);

        editTextName1.setText(giocatore.name_GIOCATORE);
    }


    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave1)) {
            GiocatoreRepo repo1 = new GiocatoreRepo(this);
            Giocatore giocatore = new Giocatore();
            giocatore.name_GIOCATORE = editTextName1.getText().toString();
            giocatore.giocatore_ID = _Giocatore_Id;

            if (_Giocatore_Id == 0) {
                _Giocatore_Id = repo1.insert(giocatore);

                Toast.makeText(this, "New Giocatore Insert", Toast.LENGTH_SHORT).show();
            } else {

                repo1.update(giocatore);
                Toast.makeText(this, "Giocatore Record updated", Toast.LENGTH_SHORT).show();
            }
        } else if (view == findViewById(R.id.btnDelete1)) {
            GiocatoreRepo repo1 = new GiocatoreRepo(this);
            repo1.delete(_Giocatore_Id);
            Toast.makeText(this, "Giocatore Record Deleted", Toast.LENGTH_SHORT);
            finish();
        } else if (view == findViewById(R.id.btnClose1)) {
            finish();
        }


    }
}

