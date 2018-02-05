package com.miramicodigo.and_401_09_listassimples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDatos = (ListView) findViewById(R.id.lvDatos);

        ArrayList<String> datos = new ArrayList<String>();

        for (int i=0; i < 500; i++) {
            datos.add("Item en posicion: "+(i+1));
        }

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, datos);

        lvDatos.setAdapter(adaptador);

        lvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        "Click en "+i, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
