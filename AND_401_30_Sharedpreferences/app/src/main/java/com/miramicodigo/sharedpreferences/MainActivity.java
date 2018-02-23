package com.miramicodigo.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etTextoUno, etTextoDos;
    private Button btnGuardarUno, btnLeerUno, btnGuardarDos, btnLeerDos, btnAbrirConfiguraciones;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTextoUno = (EditText) findViewById(R.id.etTextoUno);
        etTextoDos = (EditText) findViewById(R.id.etTextoDos);

        btnGuardarUno = (Button) findViewById(R.id.btnGuardarUno);
        btnGuardarDos = (Button) findViewById(R.id.btnGuardarDos);
        btnLeerUno = (Button) findViewById(R.id.btnLeerUno);
        btnLeerDos = (Button) findViewById(R.id.btnLeerDos);
        btnAbrirConfiguraciones = (Button) findViewById(R.id.btnAbrirConfiguraciones);

        // Ruta donde se encuentra nuestros archivos internos a la aplicacion
        // /data/data/nombrepaquete/shared_prefs/archivoSharedpreferences.xml
        // /data/data/com.miramicodigo.sharedpreferences/shared_prefs/mis_preferencias.xml

        // Ruta de nuestra memoria externa
        //  /mnt/sdcard/Documents

        sharedPreferences =
                getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE);

        btnGuardarUno.setOnClickListener(this);
        btnGuardarDos.setOnClickListener(this);
        btnLeerUno.setOnClickListener(this);
        btnLeerDos.setOnClickListener(this);
        btnAbrirConfiguraciones.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGuardarUno:
                guardarValor(etTextoUno.getText().toString(), "valor1", etTextoUno);
                break;
            case R.id.btnGuardarDos:
                guardarValor(etTextoDos.getText().toString(), "valor2", etTextoDos);
                break;
            case R.id.btnLeerUno:
                etTextoUno.setText(leerValor("valor1"));
                break;
            case R.id.btnLeerDos:
                etTextoDos.setText(leerValor("valor2"));
                break;
            case R.id.btnAbrirConfiguraciones:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
        }
    }

    public void guardarValor(String texto, String nombre, EditText et) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nombre, texto);
        editor.commit();
        et.setText("");
    }

    public String leerValor(String nombre) {
        String valor = sharedPreferences.getString(nombre, "");
        return valor;
    }
}
