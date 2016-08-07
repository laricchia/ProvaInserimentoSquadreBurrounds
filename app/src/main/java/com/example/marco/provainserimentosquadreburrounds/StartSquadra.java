package com.example.marco.provainserimentosquadreburrounds;

import android.app.ListActivity;
import android.widget.TextView;

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

/**
 * Created by marco on 28/06/2016.
 */
public class StartSquadra extends ListActivity implements android.view.View.OnClickListener {

    Button btnAdd, btnGetAll,btnCompose;
    TextView squadra_Id;

    public void onClick(View view) {
        if (view == findViewById(R.id.btnAdd)) {

            Intent intent = new Intent(this, SquadraDetail.class);
            intent.putExtra("squadra_Id", 0);
            startActivity(intent);

        } else

        {

            if (view == findViewById(R.id.btnCompose)) {
                Intent intent = new Intent(this, SquadraDetailComposta.class);
                intent.putExtra("squadra_Id",0);
                intent.putExtra("giocatore_Id",0);
                startActivity(intent);



            } else {


                SquadraRepo repo = new SquadraRepo(this);

                ArrayList<HashMap<String, String>> squadraList = repo.getSquadraList();


                if (squadraList.size() != 0) {
                    ListView lv = getListView();
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            squadra_Id = (TextView) view.findViewById(R.id.squadra_Id);
                            String squadraId = squadra_Id.getText().toString();
                            Intent objIndent = new Intent(getApplicationContext(), SquadraDetail.class);
                            objIndent.putExtra("squadra_Id", Integer.parseInt(squadraId));
                            startActivity(objIndent);
                        }
                    });
                    ListAdapter adapter = new SimpleAdapter(StartSquadra.this, squadraList, R.layout.view_squadra_entry, new String[]{"id", "name"}, new int[]{R.id.squadra_Id, R.id.squadra_name});
                    setListAdapter(adapter);
                } else {
                    Toast.makeText(this, "No squadra!", Toast.LENGTH_SHORT).show();
                }


            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserimento_squadra);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

        btnCompose= (Button) findViewById(R.id.btnCompose);
        btnCompose.setOnClickListener(this);

    }



}