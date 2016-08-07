package com.example.marco.provainserimentosquadreburrounds;

import android.app.ListActivity;

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
public class StartGiocatore extends ListActivity implements android.view.View.OnClickListener {

    Button btnAdd1, btnGetAll1;
    TextView giocatore_Id;

    public void onClick(View view) {
        if (view == findViewById(R.id.btnAdd1)) {

            Intent intent = new Intent(this, GiocatoreDetail.class);
            intent.putExtra("giocatore_Id", 0);
            startActivity(intent);
        }


        else
        {




            GiocatoreRepo repo1 = new GiocatoreRepo(this);

            ArrayList<HashMap<String, String>> giocatoreList = repo1.getGiocatoreList();



            if (giocatoreList.size() != 0) {
                ListView lv1 = getListView();
                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        giocatore_Id = (TextView) view.findViewById(R.id.giocatore_Id);
                        String giocatoreId = giocatore_Id.getText().toString();
                        Intent objIndent1 = new Intent(getApplicationContext(), GiocatoreDetail.class);
                        objIndent1.putExtra("giocatore_Id", Integer.parseInt(giocatoreId));
                        startActivity(objIndent1);
                    }
                });
                ListAdapter adapter1 = new SimpleAdapter(StartGiocatore.this, giocatoreList, R.layout.view_giocatore_entry, new String[]{"id", "name"}, new int[]{R.id.giocatore_Id, R.id.giocatore_name});
                setListAdapter(adapter1);
            } else {
                Toast.makeText(this, "No giocatore!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserimento_giocatore);


        btnAdd1 = (Button) findViewById(R.id.btnAdd1);
        btnAdd1.setOnClickListener(this);


        btnGetAll1 = (Button) findViewById(R.id.btnGetAll1);
        btnGetAll1.setOnClickListener(this);

    }



}