package com.miramicodigo.archivos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etInterno;
    private EditText etExterno;

    private Button btnInternoGuardar;
    private Button btnInternoLeer;
    private Button btnExternoGuardar;
    private Button btnExternoLeer;

    private String nombreArchivoInterno = "prueba_archivo_int.txt";
    private String nombreArchivoExterno = "prueba_archivo_ext.txt";
    private String nombreCarpeta = "/COGNOS/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        btnInternoGuardar.setOnClickListener(this);
        btnInternoLeer.setOnClickListener(this);
        btnExternoGuardar.setOnClickListener(this);
        btnExternoLeer.setOnClickListener(this);
    }

    public void initUI(){
        etInterno = (EditText) findViewById(R.id.etInterno);
        etExterno = (EditText) findViewById(R.id.etExterno);

        btnInternoGuardar = (Button) findViewById(R.id.btnInternoGuardar);
        btnInternoLeer = (Button) findViewById(R.id.btnInternoLeer);
        btnExternoGuardar = (Button) findViewById(R.id.btnExternoGuardar);
        btnExternoLeer = (Button) findViewById(R.id.btnExternoLeer);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnInternoGuardar:

                break;
            case R.id.btnInternoLeer:

                break;
            case R.id.btnExternoGuardar:

                break;
            case R.id.btnExternoLeer:

                break;
        }
    }

    public void guardarInterno() {

    }

    public void leerInterno() {

    }

    public void guardarExterno() {
        verificaPermiso();
        if (!etExterno.getText().toString().equals("")) {
            boolean sdDisponible = false;
            boolean sdAccesoEscritura = false;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)){

            }else {
                if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {

                } else {

                }
            }
            if (sdDisponible && sdAccesoEscritura) {
                try {




                } catch (Exception e) {
                    System.out.println("Error: "+e.getMessage());
                }
            } else {
                System.out.println("No se puede escribir en su memoria");
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Debe ingresar datos para guardar",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public String leerExterno() {
        try {

            return "";
        }catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return "";
        }
    }

    public void verificaPermiso() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permiso", "Concedido");
                } else {
                    Log.e("Permiso", "Denegado");
                }
                return;
            }
        }
    }
}
