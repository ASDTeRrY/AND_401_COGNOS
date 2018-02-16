package com.miramicodigo.menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnShowPopup;
    private ListView lvDatos;
    private List<String> datos;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDatos = (ListView) findViewById(R.id.lvDatos);
        btnShowPopup = (Button) findViewById(R.id.btnPopup);
        btnShowPopup.setOnClickListener(this);

        llenarDatos();
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, datos);
        lvDatos.setAdapter(adapter);

        registerForContextMenu(lvDatos);

    }

    public void llenarDatos() {
        datos = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            datos.add("Elemento "+ (i+1)+" de la lista.");
        }
    }

    @Override
    public void onClick(View view) {
        final PopupMenu menu = new PopupMenu(getApplicationContext(), btnShowPopup);
        menu.getMenuInflater().inflate(R.menu.main_menu_popup, menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuPopupUno:
                        Toast.makeText(getApplicationContext(), "Popup Uno",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menuPopupDos:
                        Toast.makeText(getApplicationContext(), "Popup Dos",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        menu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuUno:
                Toast.makeText(getApplicationContext(), "Menu Uno",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuDos:
                Toast.makeText(getApplicationContext(), "Menu Dos",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuTres:
                Toast.makeText(getApplicationContext(), "Menu Tres",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuCuatro:
                Toast.makeText(getApplicationContext(), "Menu Cuatro",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuCinco:
                Toast.makeText(getApplicationContext(), "Menu Cinco",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.submenu1:
                Toast.makeText(getApplicationContext(), "Sub menu Uno",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.submenu2:
                Toast.makeText(getApplicationContext(), "Sub menu Dos",
                        Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuContextUno:
                Toast.makeText(getApplicationContext(), "Compartir",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuContextDos:
                Toast.makeText(getApplicationContext(), "Descargar",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuContextTres:
                Toast.makeText(getApplicationContext(), "Enviar a...",
                        Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }



}
