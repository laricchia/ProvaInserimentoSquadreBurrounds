package com.example.marco.provainserimentosquadreburrounds;

/**
 * Created by marco on 29/06/2016.
 */
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class SquadraDetailComposta extends ActionBarActivity implements android.view.View.OnClickListener {

    Button random_couple;
    private int _Squadra_Id = 0;
    private int _Giocatore_Id = 0;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.componi_squadra);

        random_couple = (Button) findViewById(R.id.random_couple);
        random_couple.setOnClickListener(this);

        _Squadra_Id = 0;
        Intent intent = getIntent();
        _Squadra_Id = intent.getIntExtra("squadra_Id", 0);
        SquadraRepo repo = new SquadraRepo(this);
        Squadra squadra = new Squadra();
        squadra = repo.getSquadraById(_Squadra_Id);

        _Giocatore_Id = 0;
        _Giocatore_Id = intent.getIntExtra("giocatore_Id", 0);
        GiocatoreRepo repo1 = new GiocatoreRepo(this);
        Giocatore giocatore = new Giocatore();
        giocatore = repo1.getGiocatoreById(_Giocatore_Id);
    }


    public void onClick(View view) {
        if (view == findViewById(R.id.random_couple)) {
            SquadraRepo repo = new SquadraRepo(this);
            Squadra squadra = new Squadra();

            GiocatoreRepo repo1 = new GiocatoreRepo(this);
            Giocatore giocatore = new Giocatore();

            squadra.squadra_ID=_Squadra_Id;
            giocatore.giocatore_ID = _Giocatore_Id;


            ArrayList<HashMap<String, String>> giocatoreList = repo1.getGiocatoreList();

            if(giocatoreList.size()>1)

            {
                if (_Giocatore_Id == 0) {

                   //devo ottenere il nome del giocatore e poi lo cancello

                    giocatore = repo1.getGiocatoreById(_Giocatore_Id);
                    String nome1= giocatore.name_GIOCATORE;
                    repo1.delete(_Giocatore_Id);

                    // devo ottenere il nome del secondo giocatore e poi lo cancello

                    _Giocatore_Id = 0;
                    Intent intent2 = getIntent();
                    _Giocatore_Id = intent2.getIntExtra("giocatore_Id", 0);
                    GiocatoreRepo repo2 = new GiocatoreRepo(this);
                    Giocatore giocatore2 = new Giocatore();
                    giocatore2.giocatore_ID = _Giocatore_Id;
                    giocatore2 = repo2.getGiocatoreById(_Giocatore_Id);

                    String nome2= giocatore2.name_GIOCATORE;

                    repo2.delete(_Giocatore_Id);



                    //devo creare il nome della squadra con i nomi combinati es. Luca-Giacomo



                    squadra.name_SQUADRA=nome1+" - "+nome2;

                    //devo aggiornare il record squadra

                    squadra.squadra_ID=_Squadra_Id;
                    _Squadra_Id = repo.insert(squadra);

                    Toast.makeText(this, "New Squadra Insert", Toast.LENGTH_SHORT).show();
                }




                else {
                    Toast.makeText(this,"Nessuna coppia con cui comporre una squadra!", Toast.LENGTH_SHORT).show();
                }

                }
            }

    }




}



