package com.example.marco.provainserimentosquadreburrounds;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ListActivity;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            Button vedi_squadre = (Button) findViewById(R.id.vedi_squadre);
            vedi_squadre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent menu= new Intent(MainActivity.this, StartSquadra.class);
                    startActivity(menu);
                }
            });


        Button vedi_giocatori = (Button) findViewById(R.id.vedi_giocatori);
        vedi_giocatori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu= new Intent(MainActivity.this, StartGiocatore.class);
                startActivity(menu);
            }
        });





    }

    @Override
    public void onClick(View v) {

    }
}



