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

public class SquadraDetail extends ActionBarActivity implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose;
    EditText editTextName;
    private int _Squadra_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squadra_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextName = (EditText) findViewById(R.id.editTextName);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        _Squadra_Id =0;
        Intent intent = getIntent();
        _Squadra_Id =intent.getIntExtra("squadra_Id", 0);
        SquadraRepo repo = new SquadraRepo(this);
        Squadra squadra = new Squadra();
        squadra = repo.getSquadraById(_Squadra_Id);

        editTextName.setText(squadra.name_SQUADRA);
    }



    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            SquadraRepo repo = new SquadraRepo(this);
            Squadra squadra = new Squadra();
            squadra.name_SQUADRA=editTextName.getText().toString();
            squadra.squadra_ID=_Squadra_Id;

            if (_Squadra_Id==0){
                _Squadra_Id = repo.insert(squadra);

                Toast.makeText(this,"New Squadra Insert",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(squadra);
                Toast.makeText(this,"Squadra Record updated",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            SquadraRepo repo = new SquadraRepo(this);
            repo.delete(_Squadra_Id);
            Toast.makeText(this, "Squadra Record Deleted", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }


    }

}