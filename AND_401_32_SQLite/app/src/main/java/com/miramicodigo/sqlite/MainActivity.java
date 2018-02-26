package com.miramicodigo.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.miramicodigo.sqlite.adapters.ListaAdapter;
import com.miramicodigo.sqlite.db.DatabaseAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView lista;
    private ListaAdapter adaptadorLista;
    private DatabaseAdapter db;
    private ArrayList<Long> ids = new ArrayList<Long>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("SQLite");

        lista = (ListView) findViewById(R.id.lvLista);
        lista.setOnItemClickListener(this);
        adaptadorLista = new ListaAdapter(this);
        lista.setAdapter(adaptadorLista);



    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void cargarDatosLista() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_adicionar:
                Intent intent = new Intent(this, FormularioActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.item_lista, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        switch (item.getItemId()) {
            case R.id.menu_editar:

                break;
            case R.id.menu_eliminar:

                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, DetalleActivity.class);
        intent.putExtra("id", ids.get(i));
        startActivity(intent);
    }


}
